package mlang;

import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.source.ISourceViewer;

public class MultiLanguage extends AbstractLanguage {

	protected final List<Language> languages;
	
	public MultiLanguage(List<Language> languages) {
		this.languages = languages;
	}

	@Override
	public void collectPresentationContentTypes(Set<String> types) {
		for (Language lang : languages) {
			types.addAll(lang.getPresentationContentTypes());
		}
	}

	@Override
	public void collectPartRules(List<IPredicateRule> rules) {
		for (Language lang : languages) {
			lang.collectPartRules(rules);
		}
	}

	@Override
	public void collectAutoEdits(StrategyCollector strategies) {
		for (Language lang : languages) {
			lang.collectAutoEdits(strategies);
		}
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		for (Language lang : languages) {
			lang.getContentAssistant(sourceViewer); //???
		}
		return null;
	}


	@Override
	public void configurePresentationReconciler(
			PresentationReconcileConfigurator collector) {
		for (Language lang : languages) {
			lang.configurePresentationReconciler(collector);
		}
	}

	
	@Override
	public void setContext(MLangContext colorManager) {
		for (Language lang : languages) {
			lang.setContext(colorManager);
		}
	}

	@Override
	public Parser getParser() {
		return DummyParser.INSTANCE;
	}
}
