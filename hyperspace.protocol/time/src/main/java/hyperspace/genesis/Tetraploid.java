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
public class Tetraploid extends ScrewNut<Diploid, Chromosome> {

	private static final long serialVersionUID = 5662230592227137983L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Diploid,Chromosome>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Diploid,Chromosome> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Diploid getKey() {
		return super.getKey();
	}
	@Override
	public Diploid setKey(Diploid key) {
		return super.setKey(key);
	}
	@Override
	public Chromosome getValue() {
		return super.getValue();
	}
	@Override
	public Chromosome setValue(Chromosome value) {
		return super.setValue(value);
	}
	@XmlElement
	public Tetraploid getEntry() {
		return call() == getRoot() ? null : (Tetraploid) call();
	}
	
	public Tetraploid() {
		this(Ribosome.class, Parity.random());
	}
	public Tetraploid(Parity parity) {
		super(parity);
	}
	public Tetraploid(Class<Ribosome> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Tetraploid(Tetraploid parent) {
		super(parent);
	}
	public Tetraploid(Tetraploid parent, Diploid key, Chromosome value) {
		super(Ribosome.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Tetraploid(Tetraploid root, Parity parity) {
		super(root, parity);
	}
	public Tetraploid(Tetraploid root, Parity parity, Diploid key, Chromosome value) {
		super(Ribosome.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<Chromosome, Diploid> o) {
		getKey().comparator(new Chromosome()).compare(getKey(), o.getKey());
		Entry<Genomap,Haploid> entry = getKey().comparator().source();
		comparator((Chromosome) entry, (Diploid) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Genomap) {
			Genomap entry = (Genomap) e.getSource();
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					Chromosome chromosome = new Chromosome();
					chromosome.putValue(entry, (Haploid) entry.getChild());
					sendEvent(new EventArgs(chromosome));
				}
				break;
			default:
				break;
			}
		} 
		else if(e.getSource() instanceof Diploid) {
			Diploid entry = (Diploid) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if (!isRoot()) {
					getKey().comparator(new Chromosome()).compare(entry, getValue());
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