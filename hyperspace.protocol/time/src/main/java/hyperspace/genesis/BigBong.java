package hyperspace.genesis;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import hyperspace.Command;
import hyperspace.Entry;
import hyperspace.EventArgs;
import hyperspace.Parity;
import hyperspace.recurrent.Enumerator;

@XmlRootElement
@XmlType(propOrder={"key", "value", "entry"})
public class BigBong extends ScrewNut<Antimatter,Matter> {

	private static final long serialVersionUID = 4131224918311712139L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Antimatter,Matter>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Antimatter,Matter> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Antimatter getKey() {
		return super.getKey();
	}
	@Override
	public Antimatter setKey(Antimatter key) {
		return super.setKey(key);
	}
	@Override
	public Matter getValue() {
		return super.getValue();
	}
	@Override
	public Matter setValue(Matter value) {
		return super.setValue(value);
	}
	@XmlElement
	public BigBong getEntry() {
		return call() == getRoot() ? null : (BigBong) call();
	}
	
	public BigBong() {
		this(BigBang.class, Parity.random());
	}
	public BigBong(Parity parity) {
		super(parity);
	}
	public BigBong(Class<BigBang> childClass, Parity parity) {
		super(childClass, parity);
	}
	public BigBong(BigBong parent) {
		super(parent);
	}
	public BigBong(BigBong parent, Antimatter key, Matter value) {
		super(BigBang.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public BigBong(BigBong root, Parity parity) {
		super(root, parity);
	}
	public BigBong(BigBong root, Parity parity, Antimatter key, Matter value) {
		super(BigBang.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<Matter, Antimatter> o) {
		getKey().comparator(new Matter()).compare(getKey(), o.getKey());
		Entry<Supercluster,Interstellar> entry = getKey().comparator().source();
		comparator((Matter) entry, (Antimatter) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Matter) {
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					Matter entry = (Matter) e.getSource();
					getStem().putValue(entry, (Antimatter) entry.getChild());
				}
				break;
			default:
				break;
			}
		}
		else if(e.getSource() instanceof BigBong) {
			switch (e.getCommand()) {
			case Command.LISTEN:
				BigBong entry = (BigBong) e.getSource();
				comparator(new BigBang()).compare(entry, getStem());
				sendEvent(new EventArgs(comparator().source()));
				break;
			default:
				break;
			}
		}
	}
	public void run() {
		getKey().run();
		super.run();
	}
}