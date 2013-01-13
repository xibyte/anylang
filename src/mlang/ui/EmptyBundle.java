package mlang.ui;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

public class EmptyBundle extends ResourceBundle {

	@Override
	protected Object handleGetObject(String key) {
		return key;
	}

	@Override
	public Enumeration<String> getKeys() {
		Vector<String> vector = new Vector<String>();
		vector.add("Format");
		return vector.elements();
	}

	
}
