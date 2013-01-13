package anylang.parser.dom;

import java.util.Iterator;
import java.util.List;

import mlang.Location2;
import mlang.utils.LazyList;


public class DefaultNode extends LazyList<Node> implements Node {

	private final Enum<?> type;
	private DefaultNode parent;
	private final Object data;
	private Location2 location = Location2.UNKNOWN;
	
	public DefaultNode(Enum<?> type) {
		this(type, null);
	}
	
	public DefaultNode(Enum<?> type, Object data) {
		this.type = type;
		this.data = data;
	}

	public void setParent(DefaultNode parent) {
		if (this.parent != null) {
			this.parent.remove(this);
		}
		this.parent = parent;
		if (parent != null) {
			parent.add(this);
		}
	}

	public Node getParent() {
		return parent;
	}
	
	public List<Node> getChildren() {
		return list();
	}
	
	public Iterator<Node> iterator() {
		return super.iterator();
	}
	
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
		if (list != null) {
			for (Node child : list) {
				child.accept(visitor);
			}
		}
	}

	public Enum<?> getType() {
		return type;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public boolean hasNext() {
		return !isEmpty();
	}

	public Location2 getLocation() {
		return location;
	}

	public void setLocation(Location2 location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return type + " NODE. "+ location;
	}
}

