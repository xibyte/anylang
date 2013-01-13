package anylang._exp;

import java.util.HashSet;
import java.util.Set;

import anylang._exp.scan.Action;


public class ActionDef {

	private final Action action;
	private final Set<String> scopes;

	public ActionDef(Action action, String...scopes) {
		this.action = action;
		if (scopes == null) {
			this.scopes = null;
		} else {
			this.scopes = new HashSet<>(scopes.length);
			for (String scope : scopes) {
				this.scopes.add(scope.trim());
			}
		}
	}

	public Action getAction() {
		return action;
	}
	
	public boolean inScopes(String... scopes) {
		if (this.scopes == null) {
			return true;
		}
		for (String scope : scopes) {
			if (this.scopes.contains(scope)) {
				return true;
			}
		}
		return false;
	}
	
}
