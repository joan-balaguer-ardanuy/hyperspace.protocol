package hyperspace.recurrent;

import hyperspace.genesis.Hypercube;

public class Hyperstring extends Hypercube<Integer, Character> {
	
	private static final long serialVersionUID = -4580763218051397676L;

	public Hyperstring() {
		super();
	}
	public Hyperstring(String name) {
		super(name);
	}
	public Hyperstring(Class<Hyperinteger> antitype, String name, Integer key, Character value) {
		super(Hyperstring.class, antitype, name, key, value);
	}
	public Hyperstring(Hyperstring parent) {
		super(parent);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hyperstring parent, Integer key, Character value) {
		super(antitype, parent, key, value);
	}
	public Hyperstring(Hyperstring root, String name) {
		super(root, name);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hyperstring root, String name, Integer key, Character value) {
		super(antitype, root, name, key, value);
	}
}