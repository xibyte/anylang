package html.ui;

import html.Html;
import mlang.Language;
import mlang.ui.LanguageEditor;

public class HtmlEditor extends LanguageEditor {

	@Override
	protected Language createLanguage() {
		return new Html();
	}
}
