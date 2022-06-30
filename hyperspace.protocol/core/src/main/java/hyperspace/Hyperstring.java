package hyperspace;

import hyperspace.genesis.Hypercube;

public class Hyperstring extends Hypercube<Integer, String> {
	
	private static final long serialVersionUID = -4580763218051397676L;

	public Hyperstring() {
		super();
	}
	public Hyperstring(String name, Integer key) {
		super(Hyperstring.class, name, key);
	}
	public Hyperstring(Class<Hyperinteger> antitype, String name, Integer key, String value) {
		super(Hyperstring.class, antitype, name, key, value);
	}
	public Hyperstring(Hyperstring parent, Integer key) {
		super(parent, key);
	}
	public Hyperstring(Hyperstring parent, Integer key, String value) {
		super(Hyperinteger.class, parent, key, value);
	}
	public Hyperstring(Hyperstring root, String name, Integer key) {
		super(root, name, key);
	}
	public Hyperstring(Hyperstring root, String name, Integer key, String value) {
		super(Hyperinteger.class, root, name, key, value);
	}
}