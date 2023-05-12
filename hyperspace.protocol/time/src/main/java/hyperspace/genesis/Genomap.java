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
public class Genomap extends Screw<Hypercube, Hyperchain> {

	private static final long serialVersionUID = -3096574629565456019L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<hyperspace.Entry<Hypercube,Hyperchain>> en = enumerator();
		while(en.hasMoreElements()) {
			hyperspace.Entry<Hypercube,Hyperchain> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	
	@Override
	public Hypercube getKey() {
		return super.getKey();
	}
	@Override
	public Hypercube setKey(Hypercube key) {
		return super.setKey(key);
	}
	@Override
	public Hyperchain getValue() {
		return super.getValue();
	}
	@Override
	public Hyperchain setValue(Hyperchain value) {
		return super.setValue(value);
	}
	@XmlElement
	public Genomap getEntry() {
		return call() == getRoot() ? null : (Genomap) call();
	}

	public Genomap() {
		this(Haploid.class, Parity.random());
	}
	public Genomap(Parity parity) {
		super(parity);
	}
	public Genomap(Class<Haploid> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Genomap(Genomap parent) {
		super(parent);
	}
	public Genomap(Genomap parent, 	Hypercube key, Hyperchain value) {
		super(Haploid.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Genomap(Genomap root, Parity parity) {
		super(root, parity);
	}
	public Genomap(Genomap root, Parity parity, Hypercube key, Hyperchain value) {
		super(Haploid.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}

	@Override
	public int compareTo(hyperspace.Entry<Hyperchain,Hypercube> o) {
		getKey().comparator(new Hyperchain()).compare(getKey(), o.getKey());
		hyperspace.Entry<Integer,Character> entry = getKey().comparator().source();
		comparator((Hyperchain) entry, (Hypercube) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Genomap) {
			Genomap entry = (Genomap) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				entry.permuteChild(call(), get());
				break;
			case Command.TRANSFER:
				entry.release();
				break;
			default:
				break;
			}
		} else if(e.getSource() instanceof Hypercube) {
			Hypercube entry = (Hypercube) e.getSource();
			switch (e.getCommand()) {
			case Command.TRANSFER:
				if(!isRoot()) {
					getKey().comparator(new Hyperchain()).compare(entry, getValue());
					Hyperchain source = (Hyperchain) getKey().comparator().source();
					getStem().putValue(source, (Hypercube) source.getChild());
				}
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