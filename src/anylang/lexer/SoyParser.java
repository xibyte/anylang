package anylang.lexer;

import static anylang.lexer.SoyElementTypes.DOCLET_END;
import static anylang.lexer.SoyElementTypes.DOCLET_START;
import static anylang.lexer.SoyElementTypes.NAME;
import static anylang.lexer.SoyElementTypes.PARAM;
import static anylang.lexer.SoyElementTypes.SCRIPT_END;
import static anylang.lexer.SoyElementTypes.SCRIPT_NAME;
import static anylang.lexer.SoyElementTypes.SCRIPT_START;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import mlang.DocumentReader;
import mlang.IElementType;
import mlang.Parser;
import mlang.dom.DOM;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipselabs.mlang.models.common.CommonFactory;
import org.eclipselabs.mlang.models.common.Location;
import org.eclipselabs.mlang.models.soy.Doclet;
import org.eclipselabs.mlang.models.soy.NamedTag;
import org.eclipselabs.mlang.models.soy.Param;
import org.eclipselabs.mlang.models.soy.SoyFactory;
import org.eclipselabs.mlang.models.soy.Tag;
import org.eclipselabs.mlang.models.soy.TemplateTag;


public class SoyParser implements Parser {

	public DOM build(IDocument document, IProgressMonitor monitor)
			throws Exception {
		
		_SoyLexer lexer = new _SoyLexer(new DocumentReader(document));	
		IElementType token;
		DOM dom = new DOM();
		List<EObject> root = dom.getContents();
		
		int tagOffset = 0;
		Tag tag = null;
		Doclet doclet = null;
		SoyFactory soyFactory = SoyFactory.eINSTANCE;
		CommonFactory commonFactory = CommonFactory.eINSTANCE;
		Deque<Tag> tagStack = new ArrayDeque<Tag>();
		boolean closing = false;
		while((token = lexer.advance()) != null) {
			if (monitor.isCanceled()) {
				return null;
			}
//			System.out.println(token);
			if (token == DOCLET_START) {
				doclet = soyFactory.createDoclet();
				root.add(doclet);
			} else if (token == DOCLET_END) {
//				doclet = null;
			} else if (token == PARAM) {
				if (doclet != null) {
					Param param = soyFactory.createParam();
					param.setName(lexer.yytext());
					param.setLocation(getLocation(lexer));
//					param.setOptional(????);
					doclet.getParams().add(param);
				}	
			} else if (token == SCRIPT_START) {
				tagOffset = lexer.offset();
				closing = lexer.yytext().endsWith("/");
			} else if (token == SCRIPT_NAME) {
				if (!closing) {
					if ("template".equals(lexer.yytext())) {
						tag = soyFactory.createTemplateTag();
						((TemplateTag) tag).setDoclet(doclet);
						root.add(tag);
						tagStack.clear();
						doclet = null;
					} else {
						tag = soyFactory.createTag();
					}
					root.add(tag);
					tag.setTagName(lexer.yytext());
					tag.setParent(tagStack.peek()); 
					tagStack.push(tag);
				} else {
					if (!tagStack.isEmpty()) {
						tag = tagStack.pop();
					}
				}
			} else if (token == SCRIPT_END) {
				if (tag != null) {
					Location location = commonFactory.createLocation();
					location.setOffset(tagOffset);
					location.setLength(lexer.offset() - tagOffset);
					if (closing) {
						tag.setCloseLocation(location);
					} else {
						tag.setOpenLocation(location);
					}
				}
			} else if (token == NAME) {
				if (tag instanceof NamedTag) {
					((NamedTag) tag).setName(lexer.yytext());
				}
			}
		}
		
		return dom;
	}
	
	public static Location getLocation(_SoyLexer lexer) {
		Location location = CommonFactory.eINSTANCE.createLocation();
		location.setOffset(lexer.offset());
		location.setLength(lexer.yylength());
		return location;
	}
}
