package mlang;

import org.eclipse.jface.text.ITypedRegion;

public class MutableTypedRegion implements ITypedRegion {

	private int offset;
	private int length;
	private String type;

	@Override
	public int getOffset() {
		return offset;
	}
	
	@Override
	public int getLength() {
		return length;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type + " - " + "offset: " + offset + ", length: " + length;
	}
}
