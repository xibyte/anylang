package html.lexer;

import static html.lexer.HTMLElementTypes.CLOSING_SCRIPT_TAG;
import static html.lexer.HTMLElementTypes.END_TAG;
import html.HtmlTokens;

import java.io.IOException;
import java.io.Reader;

import js.JS;
import js.JSTokens;
import js.lexer.JSPartitionScanner;
import mlang.FlexPartitionTokenScanner;
import mlang.IElementType;
import mlang.Lexer;
import mlang.Logger;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class HTMLPartitionScanner extends FlexPartitionTokenScanner {
	
	private boolean startjs;
	private JSPartitionScanner jsScanner;
	
	@Override
	protected void reset() {
		startjs = false;
		releaseJsScanner();
	}

	private void releaseJsScanner() {
		if (jsScanner != null) {
			jsScanner.dispose();
			jsScanner = null;
		}
	}
	
	@Override
	public IToken doNextToken() throws Exception {

		if (startjs) {
			startjs = false;			
			int pos = lexer.offset() + lexer.yylength();
			upto(CLOSING_SCRIPT_TAG);
			int jslength = lexer.offset() - pos; 
			if (jslength <= 0) {
				return JSTokens.DEFAULT;
			}
			jsScanner = new JSPartitionScanner();
			jsScanner.setRange(document,  pos + offset, jslength);
		}

		if (jsScanner != null) {
			IToken token = jsScanner.nextToken();
			if (token.isEOF()) {
				releaseJsScanner();
				tokenOffset = lexer.offset();
				tokenLength = lexer.yylength();
				return HtmlTokens.TAG;
			} else {
				tokenOffset = jsScanner.getTokenOffset() - offset;
				tokenLength = jsScanner.getTokenLength();
				return token; 
			}
		}
		
		HTMLElementTypes type = (HTMLElementTypes) readFirst();
		tokenOffset = lexer.offset();
		tokenLength = lexer.yylength();
		
		if (type == null) {
			return Token.EOF;
		}
		
		switch (type) {
		case START_SCRIPT_TAG:
			startjs = true;
			upto(END_TAG);
			return HtmlTokens.SCRIPT_START;
		case START_TAG:
			upto(END_TAG);
			return HtmlTokens.TAG;
		case OTHER:
			consume(type);
			return HtmlTokens.DEFAULT;
		}
		
		return Token.UNDEFINED;
	}

	@Override
	public void setPartialRange(IDocument document, int offset, int length,
			String contentType, int partitionOffset) {
		super.setPartialRange(document, offset, length, contentType, partitionOffset);
		if (new JS().getPresentationContentTypes().contains(contentType)) {
			
			try {
				upto(CLOSING_SCRIPT_TAG);
			} catch (IOException e) {
				Logger.log(e);
			}
			int jslength = lexer.offset(); 
			if (jslength <= 0) {
				return;
			}
			jsScanner = new JSPartitionScanner();
			jsScanner.setRange(document,  this.offset, jslength);
		}	
	}
	
	private void upto(IElementType token) throws IOException {
		for (;;) {
			IElementType type = lexer.advance();
			System.out.println(type + " ---- " + ((_HTMLPartitionLexer) lexer).yytext());
			if (type == null) {
				tokenLength = lexer.offset() - tokenOffset;
				return;
			}
			if (type.equals(token)) {
				tokenLength = lexer.offset() - tokenOffset + lexer.yylength();
				return;
			}
		}
	}

	private HTMLElementTypes advance() throws IOException {
		return (HTMLElementTypes) lexer.advance();
	}
	
	@Override
	protected Lexer createLexer(Reader reader) {
		return new _HTMLPartitionLexer(reader);
	}
}
