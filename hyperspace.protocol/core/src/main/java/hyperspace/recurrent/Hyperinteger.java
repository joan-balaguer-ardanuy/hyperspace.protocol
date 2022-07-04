package hyperspace.recurrent;

import hyperspace.genesis.Hyperchain;

public class Hyperinteger extends Hyperchain<Character,Integer> {

	private static final long serialVersionUID = 8553758186934274963L;
	
	public Hyperinteger() {
		super();
	}
	public Hyperinteger(String name, Character key, Integer value) {
		super(Hyperinteger.class, name, key, value);
	}
	public Hyperinteger(Class<Hyperstring> antitype, String name, Character key, Integer value) {
		super(Hyperinteger.class, antitype, name, key, value);
	}
	public Hyperinteger(Hyperinteger parent, Character key, Integer value) {
		super(parent, key, value);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperinteger parent, Character key, Integer value) {
		super(antitype, parent, key, value);
	}
	public Hyperinteger(Hyperinteger root, String name, Character key, Integer value) {
		super(root, name, key, value);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperinteger root, String name, Character key, Integer value) {
		super(antitype, root, name, key, value);
	}
}