package anylang.editors;

import mlang.MLangContext;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class SoyScanner extends RuleBasedScanner {

	public SoyScanner(MLangContext manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(Colors.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new CommonWhitespaceDetector());

		setRules(rules);
	}
}
