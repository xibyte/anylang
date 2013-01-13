package mlang;

import static mlang.utils.Styles.makeStyle;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TypedRegion;
import org.eclipse.swt.custom.StyleRange;

public abstract class FlexPresentationBuilder implements PresentationBuilder  {

	protected final Scheme scheme;
	protected Lexer lexer;
	protected DocumentReader reader;
	protected IProgressMonitor monitor;
	protected List<StyleRange> presentation;
	protected List<ITypedRegion> partitiong;

	public FlexPresentationBuilder(Scheme scheme) {
		this.scheme = scheme;
	}

	@Override
	public void build() throws IOException {
		try {
			doBuild();
		} finally {
			reader.close();
		}
	}
	
	protected void doBuild() throws IOException {
		for (;;) {
			IElementType type = lexer.advance();
			if (type == null) {
				return;
			}
			if (monitor.isCanceled()) {
				System.out.println("CANCELED");
				return;
			}
			if (!handle(type)) {
				return;
			}
		}
	}

	protected abstract boolean handle(IElementType type) throws IOException;

	protected void style(Enum<?> styleKey) {
		style(lexer.offset(), lexer.yylength(), styleKey);	
	}

	protected void style(int from, int length, Enum<?> styleKey) {
		TextAttribute style = scheme.get(styleKey);
		if (style == null) {
			return;
		}
		if (consistentForStyle(from, length)) {
			StyleRange styleRange = makeStyle(style);
			styleRange.start = from;
			styleRange.length = length;
			presentation.add(styleRange);
		}
	}

	protected void part(String contentType) {
		part(lexer.offset(), lexer.yylength(), contentType);	
	}
	
	protected void part(int from, int length, String contentType) {
		if (length == 0) {
			return;
		}
		if (consistentForPartitiong(from, length)) {
			partitiong.add(new TypedRegion(from, length, contentType));
		}
	}

	protected void combine(String contentType) {
		combine(lexer.offset(), lexer.yylength(), contentType);	
	}
	
	protected void combine(int from, int length, String contentType) {
		if (!partitiong.isEmpty()) {
			int lastIdx = partitiong.size() - 1;
			ITypedRegion last = partitiong.get(lastIdx);
			if (last.getType().equals(contentType)) {
				partitiong.remove(lastIdx);
				int newLength = from - last.getOffset() + length;
				partitiong.add(new TypedRegion(last.getOffset(), newLength, last.getType()));
				return;
			}
		}
		part(from, length, contentType);
	}
	
	protected boolean consistentForStyle(int from, int length) {
		if (length == 0) {
			return false;
		}
		if (presentation.isEmpty()) {
			return true;
		}
		StyleRange last = presentation.get(presentation.size() - 1);
		int border = last.start + last.length;
		return from >= border;
	}
	
	protected boolean consistentForPartitiong(int from, int length) {
		if (length == 0) {
			return false;
		}
		if (partitiong.isEmpty()) {
			return true;
		}
		ITypedRegion last = partitiong.get(partitiong.size() - 1);
		int border = last.getOffset() + last.getLength();
		return from >= border;
	}
	
	public void init(DocumentReader reader, List<StyleRange> presentation,
			List<ITypedRegion> partitioning, IProgressMonitor monitor) {
		this.reader = reader;
		this.presentation = presentation;
		this.partitiong = partitioning;
		this.monitor = monitor;
		if (lexer == null) {
			lexer = createLexer(reader);
		} else {
			lexer.yyreset(reader);
		}
		reset();
	}

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
}