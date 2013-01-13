package anylang.editors;

import mlang.ClosedRule;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class SoyPartitionScanner extends RuleBasedPartitionScanner {
	public final static String SOY_DOC = "__mlang_soy_doc";
	public final static String SOY_TAG = "__mlang_soy_tag";
	
	//will be contributed
	public final static String XML_TAG = "__mlang_xml_tag";
	public final static String XML_DOC = "__mlang_xml_doc";
	
	public final static String SOY_DOC_U = "__soy_doc_u";
	public final static String SOY_TAG_U = "__soy_tag_u";

	public SoyPartitionScanner() {

		IToken doc = new Token(SOY_DOC);
		IToken tag = new Token(SOY_TAG);
		

		IToken docU = new Token(SOY_DOC_U);
		IToken tagU = new Token(SOY_TAG_U);

		IPredicateRule[] rules = {
				new ClosedRule("/*", "*/", docU, false, true),
				new MultiLineRule("/*", "*/", doc),
				new ClosedRule("{", "}", tag, false, false),
		};
		setPredicateRules(rules);
	}
}
