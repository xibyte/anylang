package anylang._exp.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Scope extends Node implements Iterable<Scope> {

	public static final String NAME = "";
	
	private List<Scope> children = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();
	private Map<String, Object> attributes;
	
	public void add(Scope node) {
		node.parent = this;
		getChildren().add(node);
	}
	
	@Override
	public Iterator<Scope> iterator() {
		return getChildren().iterator();
	}

	public <T> T attr(Class<T> type, Enum<?> attr) {
		return attr(type, attr.name());
	}
	
	public <T> T attr(Class<T> type, String name) {
		if (attributes == null) {
			return null;
		}
		return type.cast(attributes.get(name));
	}

	public void setAttribute(Enum<?> attr, Object value) {
		setAttribute(attr.name(), value);
	}
	
	public void setAttribute(String name, Object value) {
		if (attributes == null) {
			attributes = new HashMap<>();
		}
		attributes.put(name, value);
	}

	public List<Variable> getVariables() {
		return variables;
	}
	
	public boolean hasVar(String var) {
		for (Variable variable : variables) {
			if (var.equals(variable.getName())) {
				return true;
			}
		}
		return false;
	}

	public List<Scope> getChildren() {
		return children;
	}
}
