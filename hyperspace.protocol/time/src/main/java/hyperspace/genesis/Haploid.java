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
public class Haploid extends ScrewNut<Hyperchain, Hypercube> {

	private static final long serialVersionUID = 6871541139973062247L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Hyperchain,Hypercube>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Hyperchain,Hypercube> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Hyperchain getKey() {
		return super.getKey();
	}
	@Override
	public Hyperchain setKey(Hyperchain key) {
		return super.setKey(key);
	}
	@Override
	public Hypercube getValue() {
		return super.getValue();
	}
	@Override
	public Hypercube setValue(Hypercube value) {
		return super.setValue(value);
	}
	@XmlElement
	public Haploid getEntry() {
		return call() == getRoot() ? null : (Haploid) call();
	}
	
	public Haploid() {
		this(Genomap.class, Parity.random());
	}
	public Haploid(Parity parity) {
		super(parity);
	}
	public Haploid(Class<Genomap> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Haploid(Haploid parent) {
		super(parent);
	}
	public Haploid(Haploid parent, Hyperchain key, Hypercube value) {
		super(Genomap.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Haploid(Haploid root, Parity parity) {
		super(root, parity);
	}
	public Haploid(Haploid root, Parity parity, Hyperchain key, Hypercube value) {
		super(Genomap.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<Hypercube, Hyperchain> o) {
		getKey().comparator(new Hypercube()).compare(getKey(), o.getKey());
		Entry<Character,Integer> entry = getKey().comparator().source();
		comparator((Hypercube) entry, (Hyperchain) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Hyperchain) {
			Hyperchain entry = (Hyperchain) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if(!isRoot() && entry.isRoot()) {
					getKey().comparator(new Hypercube()).compare(entry, getValue());
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
