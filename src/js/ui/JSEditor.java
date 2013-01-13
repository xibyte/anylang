package js.ui;

import js.JS;
import mlang.Language;
import mlang.ui.LanguageEditor;

public class JSEditor extends LanguageEditor {

	@Override
	protected Language createLanguage() {
		return new JS();
	}
}
