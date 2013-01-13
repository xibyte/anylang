package anylang;

import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;

import java.util.List;
import java.util.Set;

import mlang.AbstractLanguage;
import mlang.PresentationReconcileConfigurator;
import mlang.Parser;
import mlang.StrategyCollector;
import mlang.ClosedRule;
import mlang.ui.ImmutableOutlineInfo;
import mlang.ui.OutlineInfo;

import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.Color;

import anylang.editors.Colors;
import anylang.editors.SoyDocScanner;
import anylang.editors.SoyTagScanner;
import anylang.editors.ae.AutoClose;
import anylang.editors.ae.AutoParam;
import anylang.editors.ae.CreateDocletStrategy;
import anylang.lexer.SoyParser;
import anylang.ui.SoyContentProvider;
import anylang.ui.SoyLabelProvider;
import anylang.ui.SoyLocationResolver;


public class Soy extends AbstractLanguage {

	public final static String TAG = "__mlang_soy_tag";
	public final static String DOC = "__mlang_soy_doc";
	private SoyTagScanner tagScanner;
	private SoyDocScanner docScanner;
	
	@Override
	public void collectPresentationContentTypes(Set<String> types) {
		types.add(TAG);
		types.add(DOC);
	}

	@Override
	public void collectPartRules(List<IPredicateRule> rules) {
		rules.add(new ClosedRule("/*", "*/", new Token(DOC)));
		rules.add(new ClosedRule("{", "}", new Token(TAG)));
	}

	@Override
	public void collectAutoEdits(StrategyCollector strategies) {
		
		strategies.add(DOC, new CreateDocletStrategy());
//		strategies.add(DOC_U, new DefaultIndentLineAutoEditStrategy());
		
		strategies.add(DEFAULT_CONTENT_TYPE, new CreateDocletStrategy());
		strategies.add(DEFAULT_CONTENT_TYPE, new AutoClose());
		strategies.add(DEFAULT_CONTENT_TYPE, new AutoParam(TAG));
		strategies.add(DEFAULT_CONTENT_TYPE, new CreateDocletStrategy());
		strategies.add(DEFAULT_CONTENT_TYPE, new DefaultIndentLineAutoEditStrategy());

//		strategies.add(TAG_U, new AutoClose());
//		strategies.add(TAG_U, new AutoParam(TAG_U));
//		strategies.add(TAG_U, new DefaultIndentLineAutoEditStrategy());
		
		strategies.add(TAG, new AutoClose());
		strategies.add(TAG, new AutoParam(TAG));
		strategies.add(TAG, new DefaultIndentLineAutoEditStrategy());
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		return null;
	}

	@Override
	public void configurePresentationReconciler(PresentationReconcileConfigurator collector) {

		SoyTagScanner tagScanner = getTagScanner();
		SoyDocScanner docScanner = getDocScanner();
		
		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(tagScanner);
		collector.setDamager(dr, TAG);
		collector.setRepairer(dr, TAG);

//		dr = new DefaultDamagerRepairer(tagScanner);
//		collector.setDamager(dr, TAG_U);
//		collector.setRepairer(dr, TAG_U);
		
		dr = new DefaultDamagerRepairer(docScanner);
		collector.setDamager(dr, DOC);
		collector.setRepairer(dr, DOC);

//		collector.setDamager(dr, DOC_U);
//		collector.setRepairer(dr, DOC_U);
	}

	private SoyDocScanner getDocScanner() {
		if (docScanner == null) {
			docScanner = new SoyDocScanner(context);
			docScanner.setDefaultReturnToken(new Token(new Color(null, Colors.DOC)));
		}
		return docScanner;
	}

	private SoyTagScanner getTagScanner() {
		if (tagScanner == null) {
			tagScanner = new SoyTagScanner(context);
			tagScanner.setDefaultReturnToken(new Token(new Color(null, Colors.TAG)));
		}
		return tagScanner;
	}

	@Override
	public Parser getParser() {
		return new SoyParser();
	}
	
	@Override
	public OutlineInfo getOutlineInfo() {
		return new ImmutableOutlineInfo(new SoyContentProvider(),
				new SoyLabelProvider(), new SoyLocationResolver());
	}
}
