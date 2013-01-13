package html;

import static mlang.utils.Styles.makeStyle;
import mlang.InputPresentationReconciler.InputContext;
import mlang.InputPresentationReconciler.InputHandler;
import mlang.MLangContext;
import mlang.utils.Scan;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.custom.StyleRange;

public class TagHandler implements InputHandler {

	private final MLangContext context;
	
	public TagHandler(MLangContext context) {
		this.context = context;
	}

	@Override
	public TextPresentation textChanged(InputContext ctx) {
		
		IRegion word = ctx.word();
		if (word == null || word.getLength() == 0) {
			return null;
		}
		int next = Scan.read(ctx.document(), word.getOffset() + word.getLength());
		System.out.println((char) next);
		if (next != -1 && next != '=') {
			TextPresentation presentation = new TextPresentation();
			
			StyleRange styleRange = makeStyle(HtmlStyles.TAG.getDefault().toJFaceAttr(context));
			styleRange.start = word.getOffset();
			styleRange.length = word.getLength();
			presentation.addStyleRange(styleRange);
			return presentation;
		}
		return null;
	}
}
