package mlang;

public class SchemeImpl implements Scheme {

	@Override
	public TextAttribute get(Enum<?> key) {
		if (key instanceof DefaultAttrAware) {
			return ((DefaultAttrAware) key).getDefault();
		}
		return null;
	}

}
