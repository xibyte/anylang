package mlang;

import static mlang.utils.Loc.end;
import mlang.dom.DOM;
import mlang.dom.DOMProvider;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipselabs.mlang.models.common.Scope;

public class ContentFormatter implements IContentFormatter {

	private final DOMProvider domProvider;

	public ContentFormatter(DOMProvider domProvider) {
		this.domProvider = domProvider;
	}

	@Override
	public void format(IDocument document, IRegion region) {
		try {
			
			DOM dom = domProvider.getDOM();
			if (dom == null) {
				return;
			}

			int offset = region.getOffset();
			int length = region.getLength();

			offset = document.getLineInformationOfOffset(offset).getOffset();

			Scope scope = dom.findClosestScope(offset);
			if (scope == null) {
				return;
			}

			StringBuilder text = new StringBuilder();
			Formatter formatter = new Formatter(document, text, scope, offset, offset + length);
			formatter.format();
			document.replace(offset, length, text.toString());
		} catch (BadLocationException e) {

		}
	}

	 private int getStartLevel(IDocument document, Scope scope) throws BadLocationException {
		int level = -1;
		do {
			scope = scope.getParent();
			++ level;
		} while (scope != null);
		if (level < 0) {
			level = 0;
		}
		return level;
	}

	@Override
	public IFormattingStrategy getFormattingStrategy(String contentType) {
		return null;
	}

	private static void appendTabulation(StringBuilder text) {
		text.append('\t');
	}

	class Formatter {
		
		final IDocument document; 
		final SBuilder text; 
		final int stop;

		public Formatter(IDocument document, StringBuilder text, Scope scope,
				int offset, int stop) {
			this.document = document;
			this.text = new SBuilder(text, getLineDelimiter());
			this.scope = scope;
			this.offset = offset;
			this.stop = stop;
		}

		int offset;
		Scope scope;
		
		void format() throws BadLocationException {
			text.setLevel(getStartLevel(document, scope));
			text.indent();
			int wrap;
			int lineIdx = document.getLineOfOffset(offset);
			{
				IRegion line = document.getLineInformation(lineIdx);
				wrap = line.getOffset() + line.getLength();
			}
			Scope nextScope = findNextScope(scope, offset);
			
			boolean consuming = true;
			while (offset < stop) {
				try {
					
					char c = document.getChar(offset);
					
					if (consuming) {
						if (offset == wrap) {
							text.flush(false);
						}
						switch (c) {
						case '\t':
						case ' ':
							continue;
						default:
							consuming = false;
						}
					}
					
					if (nextScope != null && offset == nextScope.getOffset()) {
						scope = nextScope;
						nextScope = findNextScope(scope, offset);
						text.up();
						text.newLine();
					}
					
					if (offset == end(scope)) {
						scope = scope.getParent();
						if (scope == null) {
							return;
						}
						nextScope = findNextScope(scope, offset);
						text.down();
						text.newLine();
					}
					
					if (offset == wrap) {
						++ lineIdx;
						if (lineIdx < document.getNumberOfLines()) {
							IRegion line = document.getLineInformation(lineIdx);
							wrap = line.getOffset() + line.getLength();
							text.newLine();
							consuming = true; 
						}
					} else {
						text.append(c);
					}
				} finally {
					++ offset;
				}
			}
			text.flush();
			int rest = stop - offset;
			if (rest > 0) {
				text.append(document.get(offset, rest));
			}
		}
		
		private Scope findNextScope(Scope s, int offset) {
			for (EObject obj : s.getBody()) {
				if (obj instanceof Scope) {
					Scope scope = (Scope) obj;
					if (offset > scope.getOffset()) {
						continue;
					}
					return scope;
				}
			}
			return null;
		}

		private String getLineDelimiter() {
			@SuppressWarnings("deprecation")
			IScopeContext scope = new InstanceScope();
			IEclipsePreferences node = scope.getNode(Platform.PI_RUNTIME);
			return node.get(Platform.PREF_LINE_SEPARATOR, "\n");
		}
	}

	static class SBuilder {
		
		private final StringBuilder text;
		private int level;
		private boolean newLineComming = false;
		private final String lineDelimiter;
		
		public SBuilder(StringBuilder text, String lineDelimiter) {
			this.text = text;
			this.lineDelimiter = lineDelimiter;
		}

		public SBuilder append(char c) {
			checkNewLine();
			text.append(c);
			return this;
		}

		public SBuilder append(String str) {
			checkNewLine();
			text.append(str);
			return this;
		}

		public SBuilder newLine() {
			newLineComming = true;
			return this;
		}

		public SBuilder up() {
			++ level;
			return this;
		}

		public SBuilder down() {
			-- level;
			return this;
		}

		public SBuilder flush() {
			return flush(true);
		}
		
		public SBuilder flush(boolean indent) {
			checkNewLine(indent);
			return this;
		}
		
		private void checkNewLine() {
			checkNewLine(true);
		}
		
		private void checkNewLine(boolean indent) {
			if (newLineComming) {
				text.append(lineDelimiter);
				if (indent) {
					indent();
				}
				newLineComming = false;
			}
		}
		
		public void indent() {
			for (int i = 0;i < level; ++i) {
				appendTabulation(text);
			}
		}
		
		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}
	}
}
