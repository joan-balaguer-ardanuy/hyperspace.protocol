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
public class Chromosome extends Screw<Genomap, Haploid> {

	private static final long serialVersionUID = 4135008320029372246L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<hyperspace.Entry<Genomap,Haploid>> en = enumerator();
		while(en.hasMoreElements()) {
			hyperspace.Entry<Genomap,Haploid> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Genomap getKey() {
		return super.getKey();
	}
	@Override
	public Genomap setKey(Genomap key) {
		return super.setKey(key);
	}
	@Override
	public Haploid getValue() {
		return super.getValue();
	}
	@Override
	public Haploid setValue(Haploid value) {
		return super.setValue(value);
	}
	@XmlElement
	public Chromosome getEntry() {
		return call() == getRoot() ? null : (Chromosome) call();
	}
	
	public Chromosome() {
		this(Diploid.class, Parity.random());
	}
	public Chromosome(Parity parity) {
		super(parity);
	}
	public Chromosome(Class<Diploid> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Chromosome(Chromosome parent) {
		super(parent);
	}
	public Chromosome(Chromosome parent, Genomap key, Haploid value) {
		super(Diploid.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Chromosome(Chromosome root, Parity parity) {
		super(root, parity);
	}
	public Chromosome(Chromosome root, Parity parity, Genomap key, Haploid value) {
		super(Diploid.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(hyperspace.Entry<Haploid, Genomap> o) {
		getKey().comparator(new Haploid()).compare(getKey(), o.getKey());
		hyperspace.Entry<Hyperchain, Hypercube> entry = getKey().comparator().source();
		comparator((Haploid) entry, (Genomap) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Chromosome) {
			Chromosome entry = (Chromosome) e.getSource();
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
		getKey().run();
		super.run();
	}
}