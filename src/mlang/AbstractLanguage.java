package mlang;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

import mlang.ui.OutlineInfo;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.source.ISourceViewer;

public abstract class AbstractLanguage implements Language {

	private Set<String> presentationContentTypes;
	protected MLangContext context;

	public abstract void collectPresentationContentTypes(Set<String> types);
	
	@Override
	public Set<String> getPresentationContentTypes() {
		if (presentationContentTypes == null) {
			Set<String> types = new HashSet<String>();
			collectPresentationContentTypes(types);
			presentationContentTypes = unmodifiableSet(types);
		}
		return presentationContentTypes;
	}
	
	@Override
	public void setContext(MLangContext context) {
		this.context = context;
	}
	
	protected IToken makeToken(DefaultAttrAware attr) {
		return context.makeToken(attr.getDefault());
	}
	
	@Override
	public OutlineInfo getOutlineInfo() {
		return EmptyOutlineInfo.INSTANCE;
	}
	
	
	@Override
	public void configureInputReconciler(InputPresentationReconciler reconciler) {
	}
	
	@Override
	public PresentationBuilder getPresentationBuilder() {
		return null;
	}
}
