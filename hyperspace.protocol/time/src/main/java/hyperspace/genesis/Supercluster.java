package hyperspace.genesis;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import hyperspace.Command;
import hyperspace.EventArgs;
import hyperspace.Parity;
import hyperspace.recurrent.Enumerator;

@XmlRootElement
@XmlType(propOrder={"key", "value", "entry"})
public class Supercluster extends Screw<MilkyWay,Andromeda> {

	private static final long serialVersionUID = 306393406087450209L;
	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<hyperspace.Entry<MilkyWay,Andromeda>> en = enumerator();
		while(en.hasMoreElements()) {
			hyperspace.Entry<MilkyWay,Andromeda> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public MilkyWay getKey() {
		return super.getKey();
	}
	@Override
	public MilkyWay setKey(MilkyWay key) {
		return super.setKey(key);
	}
	@Override
	public Andromeda getValue() {
		return super.getValue();
	}
	@Override
	public Andromeda setValue(Andromeda value) {
		return super.setValue(value);
	}
	@XmlElement
	public Supercluster getEntry() {
		return call() == getRoot() ? null : (Supercluster) call();
	}
	
	public Supercluster() {
		this(Interstellar.class, Parity.random());
	}
	public Supercluster(Parity parity) {
		super(parity);
	}
	public Supercluster(Class<Interstellar> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Supercluster(Supercluster parent) {
		super(parent);
	}
	public Supercluster(Supercluster parent, MilkyWay key, Andromeda value) {
		super(Interstellar.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Supercluster(Supercluster root, Parity parity) {
		super(root, parity);
	}
	public Supercluster(Supercluster root, Parity parity, MilkyWay key, Andromeda value) {
		super(Interstellar.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(hyperspace.Entry<Andromeda,MilkyWay> o) {
		getKey().comparator(new Andromeda()).compare(getKey(), o.getKey());
		hyperspace.Entry<AlphaCentauri, Sun> entry = getKey().comparator().source();
		comparator((Andromeda) entry, (MilkyWay) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Supercluster) {
			Supercluster entry = (Supercluster) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				entry.permuteChild(call(), get());
				break;
			default:
				break;
			}
		}
	}
	@Override
	public void run() {
		getValue().run();
		super.run();
	}
}