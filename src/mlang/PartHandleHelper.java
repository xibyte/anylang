package mlang;

import java.util.List;

import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TypedRegion;
import org.eclipselabs.mlang.models.common.Located;

public class PartHandleHelper {

	public final List<ITypedRegion> parts;
	public final List<ITypedRegion> zones;
	public final Located block;
	public final String defaultType;
	public MutableTypedRegion dpart;
	private int zoneOffset;

	public PartHandleHelper(Located forBlock, List<ITypedRegion> parts,
			List<ITypedRegion> zones, String defaultType) {
		this.parts = parts;
		this.block = forBlock;
		this.zones = zones;
		this.defaultType = defaultType;
	}
	
	public void begin() {
		beginZone(block.getOffset());
	}
	
	public void beginZone(int offset) {
		zoneOffset = offset;
	}

	public void endZone(int offset) {
		int length = offset - zoneOffset;
		if (length >= 0) {
			zones.add(new TypedRegion(zoneOffset, length, defaultType));
		}
	}
	
	public void done() {
		endZone(block.getOffset() + block.getLength());
	}

	public void add(Located l, String type) {
		parts.add(new TypedRegion(l.getOffset(), l.getLength(), type));
	}
}
