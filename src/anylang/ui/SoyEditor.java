package anylang.ui;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.widgets.Composite;

import anylang.Soy;

import html.Html;
import mlang.Language;
import mlang.TemplateLanguage;
import mlang.ui.LanguageEditor;

public class SoyEditor extends LanguageEditor {

	
	@Override
	protected Language createLanguage() {
		return new TemplateLanguage(new Soy(), new Html());
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		ISourceViewer sourceViewer = super.createSourceViewer(parent, ruler, styles);
//		sourceViewer.addText
		return sourceViewer;
	}
	
}
