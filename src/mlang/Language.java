package mlang;

import java.util.List;
import java.util.Set;

import mlang.ui.OutlineInfo;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.source.ISourceViewer;

public interface Language {

	Set<String> getPresentationContentTypes();
	
	void collectPartRules(List<IPredicateRule> rules);

	void collectAutoEdits(StrategyCollector strategies);

	IContentAssistant getContentAssistant(ISourceViewer sourceViewer);
	
	void configurePresentationReconciler(PresentationReconcileConfigurator collector);

	void setContext(MLangContext colorManager);

	Parser getParser();
	
	OutlineInfo getOutlineInfo();

	void configureInputReconciler(InputPresentationReconciler reconciler);

	PresentationBuilder getPresentationBuilder();
}
