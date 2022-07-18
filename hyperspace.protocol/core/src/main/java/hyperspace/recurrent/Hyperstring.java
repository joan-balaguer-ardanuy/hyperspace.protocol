package hyperspace.recurrent;

import hyperspace.genesis.Hypercube;

public class Hyperstring extends Hypercube<Integer, Character> {
	
	private static final long serialVersionUID = -4580763218051397676L;

	public Hyperstring() {
		super();
	}
	public Hyperstring(XMLTest message) {
		super(message);
	}
	public Hyperstring(Class<Hyperinteger> antitype, XMLTest message) {
		super(Hyperstring.class, antitype, message);
	}
	public Hyperstring(Hyperstring parent) {
		super(parent);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hyperstring parent, Integer key, Character value) {
		super(antitype, parent, key, value);
	}
	public Hyperstring(Hyperstring root, XMLTest message) {
		super(root, message);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hyperstring root, XMLTest message, Integer key, Character value) {
		super(antitype, root, message, key, value);
	}
}