package mlang;

import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;
import html.Html;
import html.HtmlPartitionsExtractor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mlang.dom.DOMProvider;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import anylang.Soy;
import anylang.editors.Colors;
import anylang.editors.SoyDoubleClickStrategy;
import anylang.editors.SoyScanner;
import anylang.editors.assist.ScopeAssistProcessor;


public class ContributedConfiguration extends SourceViewerConfiguration {

	private final Language language;
	private ITextDoubleClickStrategy doubleClickStrategy;
	private StrategyCollector autoEditCollector;
	private final MLangContext context;
	private SoyScanner scanner;
	private BuildDomReconcilingStrategy reconcilingStrategy;
	
	public ContributedConfiguration(MLangContext colorManager,
			Language language) {
		this.context = colorManager;
		this.language = language;
		language.setContext(colorManager);
		reconcilingStrategy = new BuildDomReconcilingStrategy(language.getParser());
	}
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		Set<String> types = new HashSet<String>();
		types.add(DEFAULT_CONTENT_TYPE);
		types.addAll(language.getPresentationContentTypes());
		return types.toArray(new String[types.size()]);
	}

	//TODO make configurable
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null) {
			doubleClickStrategy = new SoyDoubleClickStrategy();
		}
		return doubleClickStrategy;
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		
		StrategyCollector autoEdit = collectAutoEdit();
		List<IAutoEditStrategy> strategies = autoEdit.map().get(contentType);
		if (strategies != null) {
			return strategies.toArray(new IAutoEditStrategy[0]);
		}
		return super.getAutoEditStrategies(sourceViewer, contentType);
	}
	
	private StrategyCollector collectAutoEdit() {
		if (autoEditCollector == null) {
			autoEditCollector = new StrategyCollector();
			language.collectAutoEdits(autoEditCollector);
		}
		return autoEditCollector;
	}

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
	    ContentAssistant assistant = new ContentAssistant();

	    IContentAssistProcessor tagContentAssistProcessor 
	        = new ScopeAssistProcessor(reconcilingStrategy);
	    assistant.setContentAssistProcessor(tagContentAssistProcessor, Soy.TAG);
	    assistant.setContentAssistProcessor(tagContentAssistProcessor, Html.TAG);
	    assistant.enableAutoActivation(true);
	    assistant.setAutoActivationDelay(500);
	    assistant.setProposalPopupOrientation(IContentAssistant.CONTEXT_INFO_BELOW);
	    assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_BELOW);
	    return assistant;
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer viewer) {
		LangPresentationReconciler reconciler = new LangPresentationReconciler();
		reconciler.setDocumentPartitioning(DocumentPartitioner.PARTITIONING);
		language.configurePresentationReconciler(new DirectPresentationReconcileConfigurator(reconciler));
		PartitioningBuildListener partitioningBuildListener = new PartitioningBuildListener(
				new HtmlPartitionsExtractor(), viewer, reconciler);
		reconcilingStrategy.addListener(partitioningBuildListener);
		viewer.addTextListener(partitioningBuildListener);
		boolean DEBUG = false;
		if (DEBUG) {
			((ITextViewerExtension4) viewer).addTextPresentationListener(new DebugPartitiongListener(viewer, context));
		}
		
		return reconciler;
	}

	private SoyScanner getScanner() {
		if (scanner == null) {
			scanner = new SoyScanner(context);
			scanner.setDefaultReturnToken(new Token(new TextAttribute(context
					.getColor(Colors.DEFAULT))));
		}
		return scanner;
	}

	@Override
	public IReconciler getReconciler(final ISourceViewer sourceViewer) {
//		language.attachReconcilers(sourceViewer);
//		PresentationBuilder pb = language.getPresentationBuilder();
//		if (pb != null) {
//			final UIReconciling uiReconciling = new UIReconciling(sourceViewer, context, pb);
//			MonoReconciler reconciler = new MonoReconciler(uiReconciling, false);
//			reconciler.setDelay(100);
//			reconciler.install(sourceViewer);
//			sourceViewer.addTextListener(new ITextListener() {
//				
//				@Override
//				public void textChanged(TextEvent e) {
//					if (e.getViewerRedrawState() && e.getOffset() == 0 && e.getLength() == 0 && e.getText() == null) {
//						IDocument document = sourceViewer.getDocument();
//						if (document == null) {
//							return;
//						}
//						uiReconciling.setDocument(document);
//						uiReconciling.setProgressMonitor(new NullProgressMonitor());
//						uiReconciling.reconcile(false);
//						System.out.println("REDRAW");
//					}
//				}
//			});
//		}
		
		return new MonoReconciler(reconcilingStrategy, false);
	}
	
	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		return new ContentFormatter(getDomProvider());
	}
	
	public DOMProvider getDomProvider() {
		return reconcilingStrategy;
	}
}
