package anylang._exp.js;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.rules.IToken;

import anylang._exp.Lexer2;
import anylang._exp.scan.State;


public class JSFormatter {

	private final Lexer2 lexer;
	private StringBuilder code;
	private int offset;
	private IDocument document;
 	
	enum FormatterState {
		DEFAULT, SKIPING_WS;
	}
	
	public String format(IDocument document, int docOffset, int length, State initialState, String tabulation, String newLine) throws BadLocationException {
		
		this.document = document;
		code = new StringBuilder();
		offset = docOffset;
		
		int lineIdx = document.getLineOfOffset(offset);
		int endLineIdx = document.getLineOfOffset(offset + length);
		
		IRegion line = document.getLineInformation(lineIdx);
		line = new Region(offset, length - (offset - line.getOffset()));
		lexer.init(document.get(offset, length), initialState);
		
		FormatterState state = FormatterState.DEFAULT; 
		for (IToken itoken = lexer.next(); !itoken.isEOF(); itoken = lexer.next()) {
			Object data = itoken.getData();
			if (!(data instanceof String)) {
				continue;
			}
			String token = (String) data;

			switch (state) {
			case DEFAULT:
				switch (token) {
				case "NEW_LINE":
					flash();
					newLine();
					tabulation();
					state = FormatterState.SKIPING_WS;
					break;
				case "SEMICOLON":
					flash(); 
					writeMatched();
					newLine();
					tabulation();
					state = FormatterState.SKIPING_WS;
					break;
				}
				break;
			case SKIPING_WS:
				switch (token) {
				case "SEMICOLON":
					writeMatched();
					newLine();
					tabulation();
					break;
				case "NEW_LINE":
					newLine();
					tabulation();
					break;
				}
				
				break;
			}
			
		}
		
		return code.toString();
	}

	private void tabulation() {
		// TODO Auto-generated method stub
		
	}

	private void newLine() {
		// TODO Auto-generated method stub
		
	}

	private void flash() throws BadLocationException {
		code.append(document.get(offset, lexer.tokenOffset() - offset));
		offset = lexer.tokenOffset();
	}

	private void writeMatched() {
		code.append(lexer.value());
	}
	
}
