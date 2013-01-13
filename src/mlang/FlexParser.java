package mlang;

import static mlang.utils.Loc.setLength;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import mlang.dom.DOM;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipselabs.mlang.models.common.CommonFactory;
import org.eclipselabs.mlang.models.common.Located;
import org.eclipselabs.mlang.models.common.Location;
import org.eclipselabs.mlang.models.common.Scope;

public abstract class FlexParser implements Parser {

	protected static final CommonFactory cf = CommonFactory.eINSTANCE;
	
	protected Lexer lexer;
	protected DocumentReader reader;
	protected IProgressMonitor monitor;
	protected DOM dom;
	protected Deque<Scope> scopes;
	protected Scope root;

	@Override
	public void init(Scope root, DocumentReader reader, IProgressMonitor monitor) throws Exception {
		this.root = root;
		this.reader = reader;
		this.monitor = monitor;
		initLexer(reader);
		scopes = new ArrayDeque<Scope>();
		scopes.add(root);
		reset();
	}
	
	@Override
	public void build() throws Exception {
		IElementType token;
		root.setOffset(lexer.offset());
		try {
			while((token = lexer.advance()) != null) {
				if (monitor.isCanceled()) {
					System.out.println("PARSER CANCELED");
					return;
				}
				if (!handle(token)) {
					return;
				}
			}
		} finally {
			setLength(root, lexer.offset());
		}
	}

	private void initLexer(DocumentReader reader) {
		if (lexer == null) {
			lexer = createLexer(reader);
		} else {
			lexer.yyreset(reader);
		}
	}

	public boolean inRoot() {
		return scopes.size() == 1;
	}
	
	public Scope close() {
		if (inRoot()) {
			return null;
		}
		Scope scope = scopes.pop();
		setLength(scope, lexer.offset());
		return scope;
	}

	public void open(Scope scope) {
		scope.setOffset(end());
		Scope parent = scopes.peek();
		parent.getBody().add(scope);
		scope.setParent(parent); 
		scopes.push(scope);
	}
	
	public void add(Located block) {
		scopes.peek().getBody().add(block);
		setLocation(block);
	}
	
	protected abstract boolean handle(IElementType token) throws Exception;

	protected abstract Lexer createLexer(DocumentReader reader);
		
	protected abstract void reset();

	protected IElementType consume(IElementType type) throws IOException {
		if (type == null) {
			return null;
		}
		IElementType token;
		while (type.equals(token = lexer.advance())) {
		}
		return token;
	}

	protected void upto(IElementType token) throws IOException {
		for (;;) {
			IElementType type = lexer.advance();
			if (type == null || type.equals(token)) {
				return;
			}
		}
	}

	@Override
	public void shiftLexer(int value) {
		if (lexer instanceof ShiftedLexer) {
			((ShiftedLexer) lexer).shift(value);
		} else {
			lexer = new ShiftedLexer(lexer, value);
		}
	}

	public int end() {
		return lexer.offset() + lexer.yylength();
	}
	
	public Location location() {
		Location location = cf.createLocation();
		setLocation(location);
		return location;
	}
	
	public void setLocation(Location location) {
		location.setOffset(lexer.offset());
		location.setLength(lexer.yylength());
	}

}