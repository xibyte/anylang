package anylang._exp.editors;

import anylang._exp.AnyLangEditor;
import anylang._exp.ClassPathInputProvider;
import anylang._exp.Language2;
import anylang._exp.LanguagesRegistry;
import anylang._exp.TokenRegistry;
import anylang._exp.XMLBasedLanguageProvider;


public class BashEditor extends AnyLangEditor {

	@Override
	protected Language2 initLanguage() {
		LanguagesRegistry languagesRegistry = new LanguagesRegistry();
		XMLBasedLanguageProvider lp = new XMLBasedLanguageProvider(languagesRegistry, new TokenRegistry());
		lp.addInput("bash", new ClassPathInputProvider("/grammar/bash.lang"));
		languagesRegistry.register(lp);
		return lp.make("bash");
	}

}
