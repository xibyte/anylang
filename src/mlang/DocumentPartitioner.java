package mlang;

import static mlang.utils.Ranges.end;
import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;

import java.util.ArrayList;
import java.util.List;

import mlang.utils.Ranges;

import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TypedPosition;
import org.eclipse.jface.text.TypedRegion;

public class DocumentPartitioner implements IDocumentPartitioner {

	public static final String PARTITIONING = "__mlang_partitioning";
	public static final String CATEGORY_PARTS = "__mlang_positions_parts";
	public static final String CATEGORY_ZONES = "__mlang_positions_zones";
	private IDocument document;
	private final String[] contentTypes;
	private final IPositionUpdater updater = new CompositePositionUpdater(
			new DefaultPositionUpdater(CATEGORY_PARTS),
			new StaticPositionUpdater(CATEGORY_ZONES));
	
	public DocumentPartitioner(String[] contentTypes) {
		this.contentTypes = contentTypes;
	}
	
	public static void setPartitioning(IDocument document, List<ITypedRegion> partitioning, List<ITypedRegion> zones) {
		setPartitioning(document, partitioning, CATEGORY_PARTS);
		setPartitioning(document, zones, CATEGORY_ZONES);
	}

	public static void setPartitioning(IDocument document, List<ITypedRegion> partitioning, String category) {
		try {
			try {
				document.removePositionCategory(category);
			} catch (BadPositionCategoryException e) {
			}
			document.addPositionCategory(category);
			
			for (ITypedRegion region : partitioning) {
				if (Ranges.behind(document.getLength(), region.getOffset(), region.getLength())) {
					break;
				}
				document.addPosition(category, new TypedPosition(region));
			}
		} catch (Exception e) {
			Logger.log(e);
		}
	}

	
	
	@Override
	public void connect(IDocument document) {
		this.document = document;
		document.addPositionCategory(CATEGORY_PARTS);
		document.addPositionCategory(CATEGORY_ZONES);
		document.addPositionUpdater(updater);
	}

	@Override
	public void disconnect() {
		try {
			document.removePositionCategory(CATEGORY_PARTS);
			document.removePositionCategory(CATEGORY_ZONES);
			document.removePositionUpdater(updater);
		} catch (BadPositionCategoryException e) {
		}
	}

	@Override
	public void documentAboutToBeChanged(DocumentEvent event) {
	}

	@Override
	public boolean documentChanged(DocumentEvent event) {
		return false;
	}

	@Override
	public String[] getLegalContentTypes() {
		return TextUtilities.copy(contentTypes);
	}

	@Override
	public String getContentType(int offset) {
		return getPartition(offset).getType();
	}

	@Override
	public ITypedRegion[] computePartitioning(int offset, int length) {
		
		Position[] category;
		try {
			category = getPositions();
		} catch (BadPositionCategoryException e) {
			return new ITypedRegion[0]; 
		}
		
		List<ITypedRegion> partitioning = new ArrayList<ITypedRegion>();

		PartitioningIterator it = new PartitioningIterator(category, document.getLength());
		TypedPosition pos;
		while ((pos = it.next()) != null) {
			if (pos.overlapsWith(offset, length)) {
				partitioning.add(makeRegion(pos));
			}
			if (pos.offset >= offset + length) {
				break;
			}
		}
		ITypedRegion[] result = new ITypedRegion[partitioning.size()];
		partitioning.toArray(result);
		return result;
	}

	@Override
	public ITypedRegion getPartition(int offset) {
		
		try {
			Position[] category = getPositions();
			
			PartitioningIterator it = new PartitioningIterator(category, document.getLength());
			TypedPosition pos;
			while ((pos = it.next()) != null) {
				if (pos.includes(offset)) {
					return makeRegion(pos);
				} 
			}
		} catch (BadPositionCategoryException x) {
		}
		return new TypedRegion(0, document.getLength(), IDocument.DEFAULT_CONTENT_TYPE);
	}


	private TypedRegion makeRegion(TypedPosition pos) {
		String type = pos.getType();
		if (DEFAULT_CONTENT_TYPE.equals(type)) {
			type = getDefaultType(pos.offset, pos.length);
		}
		return new TypedRegion(pos.offset, pos.length, type);
	}

	private String getDefaultType(int offset, int length) {
		Position[] zones;
		try {
			zones = getZones();
		} catch (BadPositionCategoryException e) {
			return DEFAULT_CONTENT_TYPE;
		}
		for (Position pos : zones) {
			if (pos.includes(offset) && pos.includes(offset + length - 1)) {
				return ((TypedPosition)pos).getType();
			}
		}
		return DEFAULT_CONTENT_TYPE;
	}

	protected final Position[] getPositions() throws BadPositionCategoryException {
		return document.getPositions(CATEGORY_PARTS);
	}

	protected final Position[] getZones() throws BadPositionCategoryException {
		return document.getPositions(CATEGORY_ZONES);
	}

	static class PartitioningIterator {
		
		private static final Position ZERO_POSITION = new Position(0);
		private final Position[] positions;
		private final int end;
		
		public PartitioningIterator(Position[] positions, int end) {
			this.positions = positions;
			this.end = end;
		}

		private int idx = 0;
		private Position prev = ZERO_POSITION; 
		
		public TypedPosition next() {
			if (prev != null) {
				try {
					int nextOffset;
					if (idx < positions.length) {
						nextOffset = positions[idx].offset;
					} else {
						nextOffset = end;
					}
					int prevEnd = end(prev);
					if (prevEnd != nextOffset) {
						int length = nextOffset - prevEnd;
						if (length > 0) {
							return new TypedPosition(prevEnd, length, DEFAULT_CONTENT_TYPE);
						}
					}
				} finally {
					prev = null;
				}
			}
			if (idx < positions.length) {
				return (TypedPosition) (prev = positions[idx++]);
			} else {
				return null;
			}
		}
	}
}
