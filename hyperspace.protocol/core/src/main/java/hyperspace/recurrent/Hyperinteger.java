package hyperspace.recurrent;

import hyperspace.genesis.Hyperchain;

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
	public Hyperinteger(Hyperinteger parent) {
		super(parent);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperinteger parent, Character key, Integer value) {
		super(antitype, parent, key, value);
	}
	public Hyperinteger(Hyperinteger root, Hyperstring stem) {
		super(root, stem);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperinteger root, Hyperstring stem, Character key, Integer value) {
		super(antitype, root, stem, key, value);
	}
}	