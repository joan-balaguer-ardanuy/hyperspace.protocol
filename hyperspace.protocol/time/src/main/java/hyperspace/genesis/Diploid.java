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
public class Diploid extends ScrewNut<Haploid, Genomap> {

	private static final long serialVersionUID = -5814839388008604606L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Haploid,Genomap>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Haploid,Genomap> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Haploid getKey() {
		return super.getKey();
	}
	@Override
	public Haploid setKey(Haploid key) {
		return super.setKey(key);
	}
	@Override
	public Genomap getValue() {
		return super.getValue();
	}
	@Override
	public Genomap setValue(Genomap value) {
		return super.setValue(value);
	}
	@XmlElement
	public Diploid getEntry() {
		return call() == getRoot() ? null : (Diploid) call();
	}
	
	public Diploid() {
		this(Chromosome.class, Parity.random());
	}
	public Diploid(Parity parity) {
		super(parity);
	}
	public Diploid(Class<Chromosome> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Diploid(Diploid parent) {
		super(parent);
	}
	public Diploid(Diploid parent, Haploid key, Genomap value) {
		super(Chromosome.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Diploid(Diploid root, Parity parity) {
		super(root, parity);
	}
	public Diploid(Diploid root, Parity parity, Haploid key, Genomap value) {
		super(Chromosome.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<Genomap, Haploid> o) {
		getKey().comparator(new Genomap()).compare(getKey(), o.getKey());
		Entry<Hypercube,Hyperchain> entry = getKey().comparator().source();
		comparator((Genomap) entry, (Haploid) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Hypercube) {
			Hypercube entry = (Hypercube) e.getSource();
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					Genomap genomap = new Genomap();
					genomap.putValue(entry, (Hyperchain) entry.getChild());
					sendEvent(new EventArgs(genomap));
				}
				break;
			default:
				break;
			}
		} 
		else if(e.getSource() instanceof Haploid) {
			Haploid entry = (Haploid) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if (!isRoot() && entry.isRoot()) {
					getKey().comparator(new Genomap()).compare(entry, getValue());
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