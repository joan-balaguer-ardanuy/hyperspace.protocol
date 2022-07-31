package hyperspace.recurrent;

import hyperspace.genesis.Hyperchain;
import hyperspace.genesis.Hypercube;

public class Hyperinteger extends Hyperchain<Character,Integer> {

	private static final long serialVersionUID = 8553758186934274963L;
	
	public Hyperinteger() {
		super();
	}
	public Hyperinteger(XMLTest message) {
		super(message);
	}
	public Hyperinteger(Class<Hyperstring> antitype, XMLTest message) {
		super(Hyperinteger.class, antitype, message);
	}
	public Hyperinteger(Hyperchain<Character,Integer> parent, XMLTest message) {
		super(parent, message);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperchain<Character,Integer> parent, XMLTest message, Character key, Integer value) {
		super(antitype, parent, message, key, value);
	}
	public Hyperinteger(Hyperchain<Character,Integer> root, Hypercube<Integer, Character> stem, XMLTest message) {
		super(root, stem, message);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperchain<Character,Integer> root, Hypercube<Integer, Character> stem, XMLTest message, Character key, Integer value) {
		super(antitype, root, stem, message, key, value);
	}
}	