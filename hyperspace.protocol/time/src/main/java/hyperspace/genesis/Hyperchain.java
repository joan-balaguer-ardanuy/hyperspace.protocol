package hyperspace.genesis;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import hyperspace.Command;
import hyperspace.Entry;
import hyperspace.Parity;
import hyperspace.recurrent.Enumerator;

@XmlRootElement
@XmlType(propOrder={"key", "value", "entry"})
public class Hyperchain extends ScrewNut<Integer,Character> {

	private static final long serialVersionUID = -626650173493948273L;
	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Integer,Character>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Integer,Character> entrada = en.nextElement();
			stringBuilder.append(entrada.getValue());
		}
		return stringBuilder.toString();
	}
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
	public Hyperchain getEntry() {
		return call() == getRoot() ? null : (Hyperchain) call();
	}
	
	public Hyperchain() {
		this(Hypercube.class, Parity.random());
	}
	public Hyperchain(Parity parity) {
		super(parity);
	}
	public Hyperchain(Class<Hypercube> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Hyperchain(Hyperchain parent) {
		super(parent);
	}
	public Hyperchain(Hyperchain parent, Integer key, Character value) {
		super(Hypercube.class, parent, key, value);
	}
	public Hyperchain(Hyperchain root, Parity parity) {
		super(root, parity);
	}
	public Hyperchain(Hyperchain root, Parity parity, Integer key, Character value) {
		super(Hypercube.class, root, parity, key, value);
	}
	
	@Override
	public synchronized int compareTo(Entry<Character, Integer> o) {
		switch (getParity()) {
		case XY:
			if(getKey() < o.getValue()) {
				comparator(getValue(), getKey());
				return -1;
			} else {
				comparator(o.getKey(), o.getValue());
				return 1;
			}
		default:
			if(getKey() > o.getValue()) {
				comparator(getValue(), getKey());
				return -1;
			} else {
				comparator(o.getKey(), o.getValue());
				return 1;
			}
		}
	}
	@Override
	public void run() {
		try {
			Thread.sleep(getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if((isRoot() && !isEmpty()) || !isFinal()) {
			getChild().run();
		}
		switch (getCommand()) {
		case Command.LISTEN:
			setCommand(Command.TRANSFER);
			break;
		default:
			setCommand(Command.LISTEN);
			break;
		}
	}
}