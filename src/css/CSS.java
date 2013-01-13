package css;

import java.util.List;
import java.util.Set;

import mlang.AbstractLanguage;
import mlang.DummyParser;
import mlang.MLangContext;
import mlang.Parser;
import mlang.PresentationReconcileConfigurator;
import mlang.StrategyCollector;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.source.ISourceViewer;

public class CSS extends AbstractLanguage {

	@Override
	public void collectPresentationContentTypes(Set<String> types) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collectPartRules(List<IPredicateRule> rules) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collectAutoEdits(StrategyCollector strategies) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configurePresentationReconciler(
			PresentationReconcileConfigurator collector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContext(MLangContext context) {
		
	}

	@Override
	public Parser getParser() {
		return DummyParser.INSTANCE;
	}
}
