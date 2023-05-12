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
public class Antimatter extends ScrewNut<Interstellar,Supercluster> {

	private static final long serialVersionUID = 3881553887199239558L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Interstellar,Supercluster>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Interstellar,Supercluster> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Interstellar getKey() {
		return super.getKey();
	}
	@Override
	public Interstellar setKey(Interstellar key) {
		return super.setKey(key);
	}
	@Override
	public Supercluster getValue() {
		return super.getValue();
	}
	@Override
	public Supercluster setValue(Supercluster value) {
		return super.setValue(value);
	}
	@XmlElement
	public Antimatter getEntry() {
		return call() == getRoot() ? null : (Antimatter) call();
	}
	
	public Antimatter() {
		this(Matter.class, Parity.random());
	}
	public Antimatter(Parity parity) {
		super(parity);
	}
	public Antimatter(Class<Matter> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Antimatter(Antimatter parent) {
		super(parent);
	}
	public Antimatter(Antimatter parent, Interstellar key, Supercluster value) {
		super(Matter.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Antimatter(Antimatter root, Parity parity) {
		super(root, parity);
	}
	public Antimatter(Antimatter root, Parity parity, Interstellar key, Supercluster value) {
		super(Matter.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<Supercluster, Interstellar> o) {
		getKey().comparator(new Supercluster()).compare(getKey(), o.getKey());
		Entry<MilkyWay,Andromeda> entry = getKey().comparator().source();
		comparator((Supercluster) entry, (Interstellar) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof MilkyWay) {
			MilkyWay entry = (MilkyWay) e.getSource();
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					Supercluster supercluster = new Supercluster();
					supercluster.putValue(entry, (Andromeda) entry.getChild());
					sendEvent(new EventArgs(supercluster));
				}
				break;
			default:
				break;
			}
		} 
		else if(e.getSource() instanceof Interstellar) {
			Interstellar entry = (Interstellar) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if (!isRoot() && entry.isRoot()) {
					getKey().comparator(new Supercluster()).compare(entry, getValue());
					sendEvent(new EventArgs(getKey().comparator().source()));
				}
				break;
			default:
				break;
			}
		}
	}
	public void run() {
		getValue().run();
		super.run();
	}
}
