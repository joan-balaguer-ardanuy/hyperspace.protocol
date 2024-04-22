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
public class Ribosome extends Screw<Chromosome,Diploid> {
	
	private static final long serialVersionUID = -126921398816799L;
	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<hyperspace.Entry<Chromosome,Diploid>> en = enumerator();
		while(en.hasMoreElements()) {
			hyperspace.Entry<Chromosome,Diploid> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Chromosome getKey() {
		return super.getKey();
	}
	@Override
	public Chromosome setKey(Chromosome key) {
		return super.setKey(key);
	}
	@Override
	public Diploid getValue() {
		return super.getValue();
	}
	@Override
	public Diploid setValue(Diploid value) {
		return super.setValue(value);
	}
	@XmlElement
	public Ribosome getEntry() {
		return call() == getRoot() ? null : (Ribosome) call();
	}
	
	public Ribosome() {
		this(Tetraploid.class, Parity.random());
	}
	public Ribosome(Parity parity) {
		super(parity);
	}
	public Ribosome(Class<Tetraploid> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Ribosome(Ribosome parent) {
		super(parent);
	}
	public Ribosome(Ribosome parent, Chromosome key, Diploid value) {
		super(Tetraploid.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Ribosome(Ribosome root, Parity parity) {
		super(root, parity);
	}
	public Ribosome(Ribosome root, Parity parity, Chromosome key, Diploid value) {
		super(Tetraploid.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(hyperspace.Entry<Diploid, Chromosome> o) {
		getKey().comparator(new Diploid()).compare(getKey(), o.getKey());
		hyperspace.Entry<Haploid, Genomap> entry = getKey().comparator().source();
		comparator((Diploid) entry, (Chromosome) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Ribosome) {
			Ribosome entry = (Ribosome) e.getSource();
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