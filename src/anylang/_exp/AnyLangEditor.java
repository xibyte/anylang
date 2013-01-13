package anylang._exp;

import static anylang._exp.LexerScopes.DOM;
import static anylang._exp.LexerScopes.HIGHLIGHT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mlang.Logger;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import anylang._exp.dom.Scope;
import anylang._exp.scan.State;
import anylang._exp.style.DefaultStyler;
import anylang._exp.style.Styler;


public abstract class AnyLangEditor extends TextEditor {

	private LanguageOutline2 outlinePage;
	
	private Lexer2 lexer;
	private Styler styler = new DefaultStyler();
	
	class LineInfo {
		
		private State begin = lexer.defaultState();
		private State end = lexer.defaultState();
		private boolean uptodate = false;
		
	}
	
	private final Map<Integer, LineInfo> lines = new HashMap<Integer, LineInfo>();  
	protected final Language2 language;
	
	public AnyLangEditor() {
		language = initLanguage();
		setSourceViewerConfiguration(new SourceViewerConfiguration(){
			
			@Override
			public IReconciler getReconciler(ISourceViewer sourceViewer) {
				Lexer2 lexer = language.makeLexer(DOM);
				Parser2 parser = language.makeParser();
				if (lexer == null || parser == null) {
					return null;
				}
				StructureReconcilingStrategy reconcilingStrategy = new StructureReconcilingStrategy(lexer, parser);
				reconcilingStrategy.addListener(new StructureListener() {
					
					@Override
					public void changed(final Scope root) {
						if (outlinePage != null) {
							PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
								
								public void run() {
									outlinePage.setInput(root);
								}
							});
						}
					}
				});
				
				return new MonoReconciler(reconcilingStrategy, false);
			}
			
		});
	}
	
	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int viewerStyles) {
		lexer = initHighlighter();
		final ISourceViewer sourceViewer = super.createSourceViewer(parent, ruler, viewerStyles);
		sourceViewer.getTextWidget().addLineStyleListener(new LineStyleListener() {
			
			@Override
			public void lineGetStyle(LineStyleEvent event) {
				try {
					IDocument document = sourceViewer.getDocument();
					if (document == null) {
						return;
					}
					int lineIdx = document.getLineOfOffset(event.lineOffset);
					Logger.debug("lineGetStyle for line: %s", lineIdx);
					LineInfo lineInfo = lines.get(lineIdx);
					
					if (lineInfo == null) {
						lineInfo = new LineInfo(); 
						lines.put(lineIdx, lineInfo);
					}
					

					State initState = null;
					if (lineIdx > 0) {
						LineInfo prevLine = lines.get(lineIdx - 1);
						if (prevLine != null) {
							initState = prevLine.end;
						}
					} 
					if (initState == null) {
						initState = lexer.defaultState();
					}
					
					String delimiter = document.getLineDelimiter(lineIdx);
					List<StyleRange> styles = reparse(lineInfo, event.lineText + delimiter, 
							event.lineOffset, initState);
					
					int nextLineIdx = lineIdx + 1;
					if (nextLineIdx < document.getNumberOfLines()) {
						LineInfo nextLine = lines.get(nextLineIdx);
						if (nextLine != null) {
							Logger.debug("End state: " + lineInfo.end + " is connected to " + nextLine.begin);
							if ( !nextLine.begin.same(lineInfo.end) ) {
								Logger.debug("Next line is broken, needs refresh: %s", nextLineIdx);
								nextLine.uptodate = false;
								scheduleRefreshLine(document, sourceViewer.getTextWidget(), nextLineIdx);
							}
						}
					}
					event.styles = new StyleRange[styles.size()]; 
					styles.toArray(event.styles); 
					
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
//				System.out.println("LineStyleEvent");
//				System.out.println(event.lineOffset);
			}

		});
		return sourceViewer;
	}
	

	private List<StyleRange> reparse(LineInfo lineInfo, String lineText, int lineOffset, State initState) {
		List<StyleRange> styles = new ArrayList<StyleRange>();
		
		lexer.init(lineText, initState);
		lineInfo.begin = initState;
		
		for (IToken token = lexer.next(); ! token.isEOF(); token = lexer.next()) {
			if (!token.isUndefined()) { //TODO optimize!!! Don't create stylerange if there are no any style for that
				StyleRange styleRange = new StyleRange();
				styleRange.start = lexer.tokenOffset() + lineOffset;
				styleRange.length = lexer.tokenLength();
				styler.fill(styleRange, token);
				styles.add(styleRange);
			}
		}
		
		lineInfo.uptodate = true;
		lineInfo.end = lexer.state();
		return styles;
	}
	
	private void scheduleRefreshLine(final IDocument document, final StyledText styledText, final int line) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				if (line < document.getNumberOfLines()) {
					LineInfo lineInfo = lines.get(line);
					if (lineInfo != null && !lineInfo.uptodate) {
						IRegion r;
						try {
							r = document.getLineInformation(line);
							//this inits the getLineStyle() proccess
							Logger.debug("Ran scheduleRefreshLine number: %s", line);
							styledText.redrawRange(r.getOffset(), r.getLength(), true);
						} catch (BadLocationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
	}

	protected abstract Language2 initLanguage();
	
	private Lexer2 initHighlighter() {
		return language.makeLexer(HIGHLIGHT);
	}
	
	
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			if (outlinePage == null) {
				outlinePage = new LanguageOutline2(this);
			}
			return outlinePage;
		}
		return super.getAdapter(required);
	}
	
}
