package hyperspace;

import hyperspace.genesis.Hyperchain;

public class Hyperinteger extends Hyperchain<String, Integer>{

	private static final long serialVersionUID = 8553758186934274963L;
	public Hyperinteger() {
		super();
	}
	public Hyperinteger(String name, String key) {
		super(Hyperinteger.class, name, key);
	}
	public Hyperinteger(Class<Hyperstring> antitype, String name, String key, Integer value) {
		super(Hyperinteger.class, antitype, name, key, value);
	}
	public Hyperinteger(Hyperinteger parent, String key) {
		super(parent, key);
	}
	public Hyperinteger(Hyperinteger parent, String key, Integer value) {
		super(Hyperstring.class, parent, key, value);
	}
	public Hyperinteger(Hyperinteger root, String name, String key) {
		super(root, name, key);
	}
	public Hyperinteger(Hyperinteger root, String name, String key, Integer value) {
		super(Hyperstring.class, root, name, key, value);
	}
}