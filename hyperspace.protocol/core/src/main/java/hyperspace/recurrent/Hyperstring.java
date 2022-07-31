package hyperspace.recurrent;

import hyperspace.genesis.Hyperchain;
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
	public Hyperstring(Hypercube<Integer, Character> parent, XMLTest message) {
		super(parent, message);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hypercube<Integer, Character> parent, XMLTest message, Integer key, Character value) {
		super(antitype, parent, message, key, value);
	}
	public Hyperstring(Hypercube<Integer, Character> root, Hyperchain<Character,Integer> stem, XMLTest message) {
		super(root, stem, message);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hypercube<Integer, Character> root, Hyperchain<Character,Integer> stem, XMLTest message, Integer key, Character value) {
		super(antitype, root, stem, message, key, value);
	}
}