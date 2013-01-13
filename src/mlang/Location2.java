package mlang;

public class Location2 {

	public static final Location2 UNKNOWN = new Location2(0, 0, 0, 0);
	
	public final int line; 
	public final int column; 
	public final int offset;
	public final int length;

	public Location2(int line, int column, int offset, int length) {
		this.line = line;
		this.column = column;
		this.offset = offset;
		this.length = length;
	} 
	
	public boolean isUnknown() {
		return this == UNKNOWN;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Location[");
		if (isUnknown()) {
			str.append("UNKNOWN");
		} else {
			str.append(line)
			.append(", ").append(column)
			.append(", ").append(offset)
			.append(", ").append(length);
		}
		str.append("]");
		return str.toString();
	}
}
