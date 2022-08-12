package hyperspace.recurrent;

import hyperspace.genesis.Hyperchain;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hyperinteger extends Hyperchain<Character,Integer> {

	private static final long serialVersionUID = 8553758186934274963L;
	
	
	@Override
	@XmlElement
	public Character getKey() {
		return super.getKey();
	}
	@Override
	public Character setKey(Character key) {
		return super.setKey(key);
	}
	@Override
	@XmlElement
	public Integer getValue() {
		return super.getValue();
	}
	@Override
	public Integer setValue(Integer value) {
		return super.setValue(value);
	}
	@XmlElement
	public Hyperinteger getEntry() {
		return getParent() == getRoot() ? null : (Hyperinteger) getParent();
	}
	
	public Hyperinteger() {
		super();
	}
	public Hyperinteger(XMLTest message) {
		super(message);
	}
	public Hyperinteger(Class<Hyperstring> antitype, XMLTest message) {
		super(Hyperinteger.class, antitype, message);
	}
	public Hyperinteger(Hyperinteger parent, XMLTest message) {
		super(parent, message);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperinteger parent, XMLTest message, Character key, Integer value) {
		super(antitype, parent, message, key, value);
	}
	public Hyperinteger(Hyperinteger root, Hyperstring stem, XMLTest message) {
		super(root, stem, message);
	}
	public Hyperinteger(Class<Hyperstring> antitype, Hyperinteger root, Hyperstring stem, XMLTest message, Character key, Integer value) {
		super(antitype, root, stem, message, key, value);
	}
}	