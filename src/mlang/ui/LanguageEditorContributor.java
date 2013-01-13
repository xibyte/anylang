package mlang.ui;

import java.util.ResourceBundle;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;

/**
 * @deprecated Remove
 */
@Deprecated
public class LanguageEditorContributor extends TextEditorActionContributor {

	protected RetargetTextEditorAction formatProposal;

	public LanguageEditorContributor() {
		ResourceBundle bundle = new EmptyBundle();

		formatProposal = new RetargetTextEditorAction(bundle,
				"ContentFormatProposal.");
	}

	public void contributeToMenu(IMenuManager mm) {
		super.contributeToMenu(mm);
		IMenuManager editMenu = mm
				.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			editMenu.add(new Separator());
			editMenu.add(formatProposal);
		}
	}

	public void setActiveEditor(IEditorPart part) {

		super.setActiveEditor(part);

		ITextEditor editor = null;
		if (part instanceof ITextEditor) {
			editor = (ITextEditor) part;
		}

		formatProposal.setAction(getAction(editor, "ContentFormatProposal"));
	}

	public void contributeToToolBar(IToolBarManager tbm) {
		super.contributeToToolBar(tbm);
		tbm.add(new Separator());
	}

}
