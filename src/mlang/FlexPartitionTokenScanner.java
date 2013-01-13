package mlang;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public abstract class FlexPartitionTokenScanner implements IPartitionTokenScanner {

	protected IElementType readed;
	
	protected Lexer lexer;
	protected int tokenOffset;
	protected int tokenLength;
	protected int offset;
	protected DocumentReader reader;
	protected IDocument document;
	protected int length;
	private CharSequence terminal;
	
	@Override
	public IToken nextToken() {
		if (terminal != null) {
			int pos = lexer.offset() + lexer.yylength() + offset;
			int len = terminal.length();
			if (pos + len <= offset + length) {
				try {
					String term = document.get(pos , len);
					System.out.println(term);
					if (terminal.equals(term)) {
						tokenOffset = pos + 1; 
						tokenLength = 0;
						return Token.EOF;
					}
				} catch (BadLocationException e) {
				}
			}
		}
		
		try {
			
			IToken token = doNextToken();
			System.out.println(token.getData()+ " : "+ token.isEOF()+" [!"+getTokenOffset()+" : "+tokenLength+"]");
			return token;
		} catch (Exception e) {
			Logger.log(e);
			return Token.UNDEFINED;
		}
	}
	
	protected abstract IToken doNextToken() throws Exception;
	
	protected abstract Lexer createLexer(Reader reader);

	protected abstract void reset();

	
	@Override
	public void setRange(IDocument document, int offset, int length) {
		
		this.document = document;
		this.offset = offset;
		this.length = length;
		System.out.println("   =============================      ");
		System.out.println("           ["+offset+" : "+length);
		System.out.println("       ======================      ");
		
		resetInternal();
		
		tokenOffset = 0;
		tokenLength = 0;
		dispose();
		reader = new DocumentReader(document, offset, length);
		lexer = createLexer(reader);
	}

	private void resetInternal() {
		readed = null;
		reset();
	}

	@Override
	public void setPartialRange(IDocument document, int offset, int length,
			String contentType, int partitionOffset) {
		System.out.println("CONTENT TYPE " + contentType);
		//we don't try to resume...
		
		if (partitionOffset > -1) {
			int delta= offset - partitionOffset;
			if (delta > 0) {
				length += delta;
			}
			setRange(document, partitionOffset, length);
		} else {
			setRange(document, offset, length);
		}
	}
	
	protected void consume(IElementType type) throws IOException {
		while (type.equals(readed = lexer.advance())) {
		}
		tokenLength = lexer.offset() - tokenOffset;
	}

	protected IElementType readFirst() throws IOException {
		try {
			return readed == null ? lexer.advance() : readed;
		} finally {
			readed = null;
		}
	}
	
	public void dispose() {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Override
	public int getTokenOffset() {
		return tokenOffset + offset;
	}

	@Override
	public int getTokenLength() {
		return tokenLength;
	}

	public CharSequence getTerminal() {
		return terminal;
	}

	public void setTerminal(CharSequence terminal) {
		this.terminal = terminal;
	}
}
