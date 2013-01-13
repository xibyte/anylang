package js;

import static java.util.Collections.unmodifiableSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import js.lexer.JSParser;
import js.lexer.JSPresentationBuilder;
import js.ui.JSContentProvider;
import js.ui.JsLabelProvider;
import js.ui.JsLocationResolver;
import mlang.AbstractLanguage;
import mlang.ClosedRule;
import mlang.InputPresentationReconciler;
import mlang.KeyWordInputHandler;
import mlang.Parser;
import mlang.PresentationBuilder;
import mlang.PresentationReconcileConfigurator;
import mlang.SchemeImpl;
import mlang.StrategyCollector;
import mlang.ui.ImmutableOutlineInfo;
import mlang.ui.OutlineInfo;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.source.ISourceViewer;

import anylang.editors.CommonWhitespaceDetector;


public class JS extends AbstractLanguage {

	public final static String MULTI_COMMENT = "__mlang_js_mcomment";
	public final static String SINGLE_COMMENT = "__mlang_js_scomment";
	public final static String DOCLET = "__mlang_js_doclet";
	public final static String STRING = "__mlang_js_string";
	public final static String DEFAULT = "__mlang_js_default";
	
	public static final Set<String> KEYWORDS; 
	
	static {
		String [] keywords = { "break", "case", "catch",
		"const", "continue", "debugger", "default", "delete", "do",
		"else", "false", "finally", "for", "function", "if", "in",
		"instanceof", "new", "null", "return", "switch", "this",
		"throw", "true", "try", "typeof", "var", "void", "while",
		"with", "__proto__", "prototype"};
		
		KEYWORDS = unmodifiableSet(new HashSet<String>(Arrays.asList(keywords)));
	}
			

	
	@Override
	public void collectPresentationContentTypes(Set<String> types) {
		types.add(MULTI_COMMENT);
		types.add(SINGLE_COMMENT);
		types.add(DOCLET);
		types.add(STRING);
		types.add(DEFAULT);
	}
	
	@Override
	public void collectPartRules(List<IPredicateRule> rules) {
		rules.add(new ClosedRule("/**", "*/", new Token(DOCLET)));
		rules.add(new ClosedRule("/*", "*/", new Token(MULTI_COMMENT)));
		rules.add(new EndOfLineRule("//", new Token(SINGLE_COMMENT)));
		rules.add(new SingleLineRule("'", "'", new Token(STRING), '\\'));
		rules.add(new SingleLineRule("\"", "\"", new Token(STRING), '\\'));
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

		IRule[] defaultRules = {
				new SingleLineRule("'", "'", context.makeToken(JSStyles.STRING), '\\'),
				new SingleLineRule("\"", "\"", context.makeToken(JSStyles.STRING), '\\'),
				new EndOfLineRule("//", context.makeToken(JSStyles.COMMENT)),
				new MultiLineRule("/**", "*/", context.makeToken(JSStyles.DOCLET)),
				new MultiLineRule("/*", "*/", context.makeToken(JSStyles.COMMENT)),

				new KeywordRule(context.makeToken(JSStyles.KEYWORD)),
				new WhitespaceRule(new CommonWhitespaceDetector()),
		};
		
		RuleBasedScanner defaultScanner = new RuleBasedScanner();
		defaultScanner.setRules(defaultRules);
		
		
		DefaultDamagerRepairer dr =
				new DefaultDamagerRepairer(defaultScanner);
		collector.setDamager(dr, DEFAULT);
		collector.setRepairer(dr, DEFAULT);
		
		
		DefaultDamagerRepairer docletRepairer = repairer(makeToken(JSStyles.DOCLET));
		collector.setDamager(docletRepairer, DOCLET);
		collector.setRepairer(docletRepairer, DOCLET);

		DefaultDamagerRepairer commentRepairer = repairer(makeToken(JSStyles.COMMENT));
		collector.setDamager(commentRepairer, MULTI_COMMENT);
		collector.setRepairer(commentRepairer, MULTI_COMMENT);

		collector.setDamager(commentRepairer, SINGLE_COMMENT);
		collector.setRepairer(commentRepairer, SINGLE_COMMENT);

		DefaultDamagerRepairer stringRepairer = repairer(makeToken(JSStyles.STRING));
		collector.setDamager(stringRepairer, STRING);
		collector.setRepairer(stringRepairer, STRING);
		
	}

	public DefaultDamagerRepairer repairer(IToken token) {
		RuleBasedScanner scanner = new RuleBasedScanner();
		scanner.setDefaultReturnToken(token);
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
		return dr;
	}
	
	@Override
	public Parser getParser() {
		return new JSParser();
	}
	
	@Override
	public OutlineInfo getOutlineInfo() {
		return new ImmutableOutlineInfo(new JSContentProvider(),
				new JsLabelProvider(), new JsLocationResolver());
	}
	
	@Override
	public void configureInputReconciler(InputPresentationReconciler reconciler) {
		reconciler.addHandler(JS.DEFAULT, new KeyWordInputHandler(
				JSStyles.KEYWORD.getDefault().toJFaceAttr(context), KEYWORDS));
	}
	
	@Override
	public PresentationBuilder getPresentationBuilder() {
		return new JSPresentationBuilder(new SchemeImpl());
	}
}
