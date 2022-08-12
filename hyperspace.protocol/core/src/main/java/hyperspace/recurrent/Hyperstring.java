package hyperspace.recurrent;

import hyperspace.genesis.Hypercube;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hyperstring extends Hypercube<Integer, Character> {
	
	private static final long serialVersionUID = -4580763218051397676L;

	@Override
	@XmlElement
	public Integer getKey() {
		return super.getKey();
	}
	@Override
	public Integer setKey(Integer key) {
		return super.setKey(key);
	}
	@Override
	@XmlElement
	public Character getValue() {
		return super.getValue();
	}
	@Override
	public Character setValue(Character value) {
		return super.setValue(value);
	}
	@XmlElement
	public Hyperstring getEntry() {
		return getParent() != getRoot() ? (Hyperstring) getParent(): null;
	}
	
	public Hyperstring() {
		super();
	}
	public Hyperstring(XMLTest message) {
		super(message);
	}
	public Hyperstring(Class<Hyperinteger> antitype, XMLTest message) {
		super(Hyperstring.class, antitype, message);
	}
	public Hyperstring(Hyperstring parent, XMLTest message) {
		super(parent, message);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hyperstring parent, XMLTest message, Integer key, Character value) {
		super(antitype, parent, message, key, value);
	}
	public Hyperstring(Hyperstring root, Hyperinteger stem, XMLTest message) {
		super(root, stem, message);
	}
	public Hyperstring(Class<Hyperinteger> antitype, Hyperstring root, Hyperinteger stem, XMLTest message, Integer key, Character value) {
		super(antitype, root, stem, message, key, value);
	}
}