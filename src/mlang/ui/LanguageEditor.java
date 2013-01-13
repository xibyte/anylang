package mlang.ui;

import static org.eclipse.jface.text.source.ISourceViewer.FORMAT;
import mlang.ContributedConfiguration;
import mlang.Language;
import mlang.LanguageDocumentProvider;
import mlang.MLangContext;
import mlang.dom.DOM;
import mlang.dom.DOMListener;
import mlang.dom.DOMProvider;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;


public abstract class LanguageEditor extends TextEditor implements DOMListener {

	private final MLangContext context;
	private final DOMProvider domProvider;
	private final Language language;

	private LanguageOutline outlinePage;
	private ProjectionSupport projectionSupport;
	private ProjectionAnnotationModel annotationModel;
	
	// public LanguageEditor() {
	// colorManager = new ColorManager();
	// setSourceViewerConfiguration(new SoyConfiguration(colorManager, this));
	// setDocumentProvider(new SoyDocumentProvider(this));
	// }

	public LanguageEditor() {
		context = new MLangContext();

		language = createLanguage();
		ContributedConfiguration configuration = new ContributedConfiguration(
				context, language);
		domProvider = configuration.getDomProvider();
		domProvider.addListener(this);
		setSourceViewerConfiguration(configuration);
		setDocumentProvider(new LanguageDocumentProvider(language));
	}

	protected abstract Language createLanguage();

	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		fAnnotationAccess= getAnnotationAccess();
		fOverviewRuler= createOverviewRuler(getSharedColors());
		
		ISourceViewer viewer= new ProjectionViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(), styles);
		// ensure decoration support has been created and configured.
		getSourceViewerDecorationSupport(viewer);
		return viewer;
	}
	
	@Override
	public void createPartControl(Composite parent) {
	    super.createPartControl(parent);
	    ProjectionViewer viewer =(ProjectionViewer)getSourceViewer();

	    projectionSupport = new ProjectionSupport(viewer,getAnnotationAccess(),getSharedColors());
	    projectionSupport.install();

	    //turn projection mode on
	    viewer.doOperation(ProjectionViewer.TOGGLE);
	    
	    annotationModel = viewer.getProjectionAnnotationModel();
	    ProjectionAnnotation pa = new ProjectionAnnotation();
	    annotationModel.addAnnotation(pa, new  Position(5, 300));
	}
	
	
	@Override
	protected void createActions() {
		super.createActions();
		TextOperationAction action = new TextOperationAction(new EmptyBundle(),
				"ContentFormatProposal.", this, FORMAT);
		action.setAccelerator(SWT.CTRL | SWT.SHIFT | 'F');
		setAction("ContentFormatProposal", action);
	}
	
	@Override
	protected void editorContextMenuAboutToShow(IMenuManager menu) {
		super.editorContextMenuAboutToShow(menu);
		addAction(menu, ITextEditorActionConstants.GROUP_EDIT, "ContentFormatProposal");
	}
	
	public void dispose() {
		context.dispose();
		super.dispose();
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			if (outlinePage == null) {
				outlinePage = new LanguageOutline(this, language.getOutlineInfo());
			}
			return outlinePage;
		}
		return super.getAdapter(required);
	}
	
	@Override
	public void changed(final DOM dom) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			
			public void run() {
				if (outlinePage != null) {
					outlinePage.setInput(dom);
				}
			}
		});
	}

	public DOMProvider getDomProvider() {
		return domProvider;
	}
}
