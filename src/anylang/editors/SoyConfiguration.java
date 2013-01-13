package anylang.editors;

import static anylang.editors.SoyPartitionScanner.SOY_DOC_U;
import static anylang.editors.SoyPartitionScanner.SOY_TAG;
import static anylang.editors.SoyPartitionScanner.SOY_TAG_U;
import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;

import mlang.MLangContext;
import mlang.dom.DOMProvider;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import anylang.editors.ae.AutoClose;
import anylang.editors.ae.AutoParam;
import anylang.editors.ae.CreateDocletStrategy;
import anylang.editors.assist.ScopeAssistProcessor;


public class SoyConfiguration extends SourceViewerConfiguration {
	private SoyDoubleClickStrategy doubleClickStrategy;
	private SoyTagScanner tagScanner;
	private SoyDocScanner docScanner;
	private SoyScanner scanner;
	private MLangContext colorManager;
	private DOMProvider astProvider;

	public SoyConfiguration(MLangContext colorManager, DOMProvider astProvider) {
		this.colorManager = colorManager;
		this.astProvider = astProvider;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			SoyPartitionScanner.SOY_DOC_U,
			SoyPartitionScanner.SOY_TAG_U,
			SoyPartitionScanner.SOY_DOC,
			SoyPartitionScanner.SOY_TAG };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new SoyDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected SoyScanner getXMLScanner() {
		if (scanner == null) {
			scanner = new SoyScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(Colors.DEFAULT))));
		}
		return scanner;
	}
	protected SoyTagScanner getSoyTagScanner() {
		if (tagScanner == null) {
			tagScanner = new SoyTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(Colors.TAG))));
		}
		return tagScanner;
	}
	
	protected SoyDocScanner getSoyDocScanner() {
		if (docScanner == null) {
			docScanner = new SoyDocScanner(colorManager);
			docScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(Colors.DOC))));
		}
		return docScanner;
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		
		if (SOY_DOC_U.equals(contentType)) {
			return new IAutoEditStrategy[] { 
					new CreateDocletStrategy(), 
					getAutoIndentStrategy(sourceViewer, contentType)};
			
		}

		if (DEFAULT_CONTENT_TYPE.equals(contentType)) {
			return new IAutoEditStrategy[] { 
					new CreateDocletStrategy(), 
					new AutoClose(),
					new AutoParam(SOY_TAG),
					getAutoIndentStrategy(sourceViewer, contentType)};
			
		}

		
		
		if (SOY_TAG_U.equals(contentType)) {
			return new IAutoEditStrategy[] { 
					new AutoClose(),
					new AutoParam(SOY_TAG_U),
					getAutoIndentStrategy(sourceViewer, contentType)};
			
		}

		if (SOY_TAG.equals(contentType)) {
			return new IAutoEditStrategy[] { 
					new AutoClose(),
					new AutoParam(SOY_TAG),
					getAutoIndentStrategy(sourceViewer, contentType)};
			
		}

		
		return new IAutoEditStrategy[] { 
				getAutoIndentStrategy(sourceViewer, contentType
				) };
	}
	
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
	    ContentAssistant assistant = new ContentAssistant();

	    IContentAssistProcessor tagContentAssistProcessor 
	        = new ScopeAssistProcessor(astProvider);
	    assistant.setContentAssistProcessor(tagContentAssistProcessor, SoyPartitionScanner.SOY_TAG);
	    assistant.enableAutoActivation(true);
	    assistant.setAutoActivationDelay(500);
	    assistant.setProposalPopupOrientation(IContentAssistant.CONTEXT_INFO_BELOW);
	    assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_BELOW);
	    return assistant;

	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		
		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getSoyTagScanner());
		reconciler.setDamager(dr, SoyPartitionScanner.SOY_TAG);
		reconciler.setRepairer(dr, SoyPartitionScanner.SOY_TAG);

		dr = new DefaultDamagerRepairer(getSoyTagScanner());
		reconciler.setDamager(dr, SoyPartitionScanner.SOY_TAG_U);
		reconciler.setRepairer(dr, SoyPartitionScanner.SOY_TAG_U);
		
		dr = new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(getSoyDocScanner());
		reconciler.setDamager(dr, SoyPartitionScanner.SOY_DOC);
		reconciler.setRepairer(dr, SoyPartitionScanner.SOY_DOC);

		reconciler.setDamager(dr, SoyPartitionScanner.SOY_DOC_U);
		reconciler.setRepairer(dr, SoyPartitionScanner.SOY_DOC_U);
		
		return reconciler;
	}

	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		//For building AST
		return null;
	}
}