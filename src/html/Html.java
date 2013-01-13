package html;

import static java.util.Collections.unmodifiableSet;
import html.lexer.HTMLParser;
import html.lexer.HtmlPresentationBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import js.JS;
import mlang.AbstractLanguage;
import mlang.AttributeDetector;
import mlang.ClosedRule;
import mlang.InjectedLanguage;
import mlang.InputPresentationReconciler;
import mlang.Language;
import mlang.MLangContext;
import mlang.MultiLineRuleWithLiterals;
import mlang.OpenedTagRule;
import mlang.Parser;
import mlang.PresentationBuilder;
import mlang.PresentationReconcileConfigurator;
import mlang.SchemeImpl;
import mlang.StrategyCollector;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.Color;

import anylang.editors.Colors;
import anylang.editors.CommonWhitespaceDetector;
import anylang.editors.NonRuleBasedDamagerRepairer;

import css.CSS;

public class Html extends AbstractLanguage implements InjectedLanguage {

	public final static String TAG = "__mlang_html_tag";
	public final static String COMMENT = "__mlang_html_doc";
	public final static String SCRIPT = "__mlang_html_script";
	public final static String SCRIPT_START = "__mlang_html_script_start";
	public final static String SCRIPT_END = "__mlang_html_script_end";
	public final static String OUTER = "__mlang_html_outer";
	public final static String DEFAULT = "__mlang_html_default";

	public final static String JS_DEFAULT = "__mlang_html_js_default";

	public final static String JS_PARTITIONING = "__mlang_html_js_partitioning";
	public final static String JS_OUTER_PARTITIONING = "__mlang_html_js_outer_partitioning";

	
	private RuleBasedScanner commentScanner;
	private RuleBasedScanner tagScanner;
	
	private final Set<Language> includingLanguages;
	private final JS jsLang;
	private final CSS cssLang;
	{
		Set<Language> langs = new HashSet<Language>();
		langs.add(jsLang = new JS());
		langs.add(cssLang  = new CSS());
		includingLanguages = unmodifiableSet(langs);
	}
	
	@Override
	public void setContext(MLangContext context) {
		super.setContext(context);
		jsLang.setContext(context);
		cssLang.setContext(context);
	}
	
	@Override
	public void collectPresentationContentTypes(Set<String> types) {
		types.add(TAG);
		types.add(DEFAULT);
		types.add(COMMENT);
		jsLang.collectPresentationContentTypes(types);
	}

	@Override
	public void collectPartRules(List<IPredicateRule> types) {
		types.add(new ClosedRule("<!--", "-->", new Token(COMMENT)));
//		types.add(new ClosedRule("<script", ">", new Token(SCRIPT_START)));
		types.add(new ClosedRule("<", ">", new Token(TAG)));
	}

	@Override
	public void collectAutoEdits(StrategyCollector strategies) {
		
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		return null;
	}

	@Override
	public void configurePresentationReconciler(
			PresentationReconcileConfigurator collector) {

		{
			IRule[] defaultRules = {
					new OpenedTagRule('<', context.makeToken(HtmlStyles.TAG)),
					//new ClosedRule("<", ">", context.makeToken(HtmlStyles.TAG)),
					//new ClosedRule("<!--", "-->", context.makeToken(HtmlStyles.COMMENT)),
					new MultiLineRuleWithLiterals("<", ">", context.makeToken(HtmlStyles.TAG)),
					new WhitespaceRule(new CommonWhitespaceDetector()),
			};
			
			RuleBasedScanner defaultScanner = new RuleBasedScanner();
			defaultScanner.setRules(defaultRules);
	
			DefaultDamagerRepairer dr = new DefaultDamagerRepairer(defaultScanner);
			collector.setDamager(dr, DEFAULT);
			collector.setRepairer(dr, DEFAULT);

		}

		{
			IRule[] tagRules = {
					new OpenedTagRule('<', context.makeToken(HtmlStyles.TAG)),
					new WordRule(new AttributeDetector(), context.makeToken(HtmlStyles.PARAM)),
					new SingleLineRule("\"", "\"", context.makeToken(HtmlStyles.STRING), '\\'),
					new SingleLineRule("'", "'", context.makeToken(HtmlStyles.STRING), '\\'),
					new WhitespaceRule(new CommonWhitespaceDetector()),
			};
			
			RuleBasedScanner tagScanner = new RuleBasedScanner();
			tagScanner.setDefaultReturnToken(context.makeToken(HtmlStyles.TAG));
			tagScanner.setRules(tagRules);
			
			DefaultDamagerRepairer dr = new DefaultDamagerRepairer(tagScanner);
			collector.setDamager(dr, TAG);
			collector.setRepairer(dr, TAG);
		}
		
		{
			NonRuleBasedDamagerRepairer dr = new NonRuleBasedDamagerRepairer(
					context.textAttrForStyle(HtmlStyles.COMMENT));
			collector.setDamager(dr, COMMENT);
			collector.setRepairer(dr, COMMENT);
		}
		
		jsLang.configurePresentationReconciler(collector);
	}

	private ITokenScanner getCommentScanner() {
		if (commentScanner == null) {
			commentScanner = new RuleBasedScanner();
			commentScanner.setDefaultReturnToken(new Token(new Color(null, Colors.DOC)));
		}
		return commentScanner;
	}

	private ITokenScanner getTagScanner() {
		if (tagScanner == null) {
			tagScanner = new RuleBasedScanner();
			tagScanner.setDefaultReturnToken(new Token(new Color(null, Colors.TAG)));
		}		
		return tagScanner;
	}

	@Override
	public Set<Language> getProvidedLanguages() {
		return includingLanguages;
	}

	@Override
	public Parser getParser() {
		return new HTMLParser();
	}
	
	@Override
	public void configureInputReconciler(InputPresentationReconciler reconciler) {
		reconciler.addHandler(TAG, new TagHandler(context));
		jsLang.configureInputReconciler(reconciler);
	}

	@Override
	public PresentationBuilder getPresentationBuilder() {
		return new HtmlPresentationBuilder(new SchemeImpl());
	}
	
}
