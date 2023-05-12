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
public class MilkyWay extends Screw<Sun,AlphaCentauri> {

	private static final long serialVersionUID = 1253114982913774706L;
	
	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<hyperspace.Entry<Sun,AlphaCentauri>> en = enumerator();
		while(en.hasMoreElements()) {
			hyperspace.Entry<Sun,AlphaCentauri> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Sun getKey() {
		return super.getKey();
	}
	@Override
	public Sun setKey(Sun key) {
		return super.setKey(key);
	}
	@Override
	public AlphaCentauri getValue() {
		return super.getValue();
	}
	@Override
	public AlphaCentauri setValue(AlphaCentauri value) {
		return super.setValue(value);
	}
	@XmlElement
	public MilkyWay getEntry() {
		return call() == getRoot() ? null : (MilkyWay) call();
	}
	
	public MilkyWay() {
		this(Andromeda.class, Parity.random());
	}
	public MilkyWay(Parity parity) {
		super(parity);
	}
	public MilkyWay(Class<Andromeda> childClass, Parity parity) {
		super(childClass, parity);
	}
	public MilkyWay(MilkyWay parent) {
		super(parent);
	}
	public MilkyWay(MilkyWay parent, Sun key, AlphaCentauri value) {
		super(Andromeda.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public MilkyWay(MilkyWay root, Parity parity) {
		super(root, parity);
	}
	public MilkyWay(MilkyWay root, Parity parity, Sun key, AlphaCentauri value) {
		super(Andromeda.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	@Override
	public int compareTo(hyperspace.Entry<AlphaCentauri,Sun> o) {
		getKey().comparator(new AlphaCentauri()).compare(getKey(), o.getKey());
		hyperspace.Entry<Gliese, Earth> entry = getKey().comparator().source();
		comparator((AlphaCentauri) entry, (Sun) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof MilkyWay) {
			MilkyWay entry = (MilkyWay) e.getSource();
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
		} else if(e.getSource() instanceof Sun) {
			Sun entry = (Sun) e.getSource();
			switch (e.getCommand()) {
			case Command.TRANSFER:
				if(!isRoot() && entry.isRoot()) {
					getKey().comparator(new AlphaCentauri()).compare(entry, getValue());
					AlphaCentauri source = (AlphaCentauri) getKey().comparator().source();
					getStem().putValue(source, (Sun) source.getChild());
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