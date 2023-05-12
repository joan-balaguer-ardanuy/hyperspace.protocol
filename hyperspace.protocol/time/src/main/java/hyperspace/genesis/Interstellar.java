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
public class Interstellar extends ScrewNut<Andromeda,MilkyWay> {

	private static final long serialVersionUID = -609824198859953022L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Andromeda,MilkyWay>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Andromeda,MilkyWay> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Andromeda getKey() {
		return super.getKey();
	}
	@Override
	public Andromeda setKey(Andromeda key) {
		return super.setKey(key);
	}
	@Override
	public MilkyWay getValue() {
		return super.getValue();
	}
	@Override
	public MilkyWay setValue(MilkyWay value) {
		return super.setValue(value);
	}
	@XmlElement
	public Interstellar getEntry() {
		return call() == getRoot() ? null : (Interstellar) call();
	}
	
	public Interstellar() {
		this(Supercluster.class, Parity.random());
	}
	public Interstellar(Parity parity) {
		super(parity);
	}
	public Interstellar(Class<Supercluster> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Interstellar(Interstellar parent) {
		super(parent);
	}
	public Interstellar(Interstellar parent, Andromeda key, MilkyWay value) {
		super(Supercluster.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Interstellar(Interstellar root, Parity parity) {
		super(root, parity);
	}
	public Interstellar(Interstellar root, Parity parity, Andromeda key, MilkyWay value) {
		super(Supercluster.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<MilkyWay, Andromeda> o) {
		getKey().comparator(new MilkyWay()).compare(getKey(), o.getKey());
		Entry<Sun,AlphaCentauri> entry = getKey().comparator().source();
		comparator((MilkyWay) entry, (Andromeda) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Sun) {
			Sun entry = (Sun) e.getSource();
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					MilkyWay milkyWay = new MilkyWay();
					milkyWay.putValue(entry, (AlphaCentauri) entry.getChild());
					sendEvent(new EventArgs(milkyWay));
				}
				break;
			default:
				break;
			}
		} 
		else if(e.getSource() instanceof Andromeda) {
			Andromeda entry = (Andromeda) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if (!isRoot() && entry.isRoot()) {
					getKey().comparator(new MilkyWay()).compare(entry, getValue());
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