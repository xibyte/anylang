package anylang._exp.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import anylang._exp.AnyLangEditor;
import anylang._exp.ClassPathInputProvider;
import anylang._exp.Language2;
import anylang._exp.LanguagesRegistry;
import anylang._exp.Lexer2;
import anylang._exp.TokenRegistry;
import anylang._exp.XMLBasedLanguageProvider;


public class XMLEditor extends AnyLangEditor {

	@Override
	protected Language2 initLanguage() {
		
		LanguagesRegistry languagesRegistry = new LanguagesRegistry();
		XMLBasedLanguageProvider lp = new XMLBasedLanguageProvider(languagesRegistry, new TokenRegistry());
		lp.addInput("xml", new ClassPathInputProvider("/grammar/xml.lang"));
		languagesRegistry.register(lp);
		return lp.make("xml");
	}

}
