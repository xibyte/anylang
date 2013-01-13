package anylang.parser.dom;

import java.util.List;

import mlang.Location2;


public interface Node extends Iterable<Node> {

	List<Node> getChildren();

	Node getParent();

	Enum<?> getType();

	Object getData();
	
	void accept(NodeVisitor visitor);

	boolean hasNext();

	Location2 getLocation();
}
