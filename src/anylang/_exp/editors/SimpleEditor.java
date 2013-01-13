package anylang._exp.editors;

import anylang._exp.AnyLangEditor;
import anylang._exp.ClassPathInputProvider;
import anylang._exp.Language2;
import anylang._exp.LanguagesRegistry;
import anylang._exp.Lexer2;
import anylang._exp.TokenRegistry;
import anylang._exp.XMLBasedLanguageProvider;

public class SimpleEditor extends AnyLangEditor {

	@Override
	protected Language2 initLanguage() {
//		lexer = TestLang.makeLexer();
		
		LanguagesRegistry languagesRegistry = new LanguagesRegistry();
		XMLBasedLanguageProvider lp = new XMLBasedLanguageProvider(languagesRegistry, new TokenRegistry());
		lp.addInput("string", new ClassPathInputProvider("/grammar/string.lang"));
		lp.addInput("js", new ClassPathInputProvider("/grammar/js.lang"));
		lp.addInput("html", new ClassPathInputProvider("/grammar/html.lang"));
		lp.addInput("test", new ClassPathInputProvider("/grammar/test.lang"));
		languagesRegistry.register(lp);
		
		return lp.make("test");
	}

	
}
