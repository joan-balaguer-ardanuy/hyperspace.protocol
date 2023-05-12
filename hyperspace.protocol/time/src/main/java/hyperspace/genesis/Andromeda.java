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
public class Andromeda extends ScrewNut<AlphaCentauri,Sun> {

	private static final long serialVersionUID = 1951089975362507507L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<AlphaCentauri,Sun>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<AlphaCentauri,Sun> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public AlphaCentauri getKey() {
		return super.getKey();
	}
	@Override
	public AlphaCentauri setKey(AlphaCentauri key) {
		return super.setKey(key);
	}
	@Override
	public Sun getValue() {
		return super.getValue();
	}
	@Override
	public Sun setValue(Sun value) {
		return super.setValue(value);
	}
	@XmlElement
	public Andromeda getEntry() {
		return call() == getRoot() ? null : (Andromeda) call();
	}
	
	public Andromeda() {
		this(MilkyWay.class, Parity.random());
	}
	public Andromeda(Parity parity) {
		super(parity);
	}
	public Andromeda(Class<MilkyWay> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Andromeda(Andromeda parent) {
		super(parent);
	}
	public Andromeda(Andromeda parent, AlphaCentauri key, Sun value) {
		super(MilkyWay.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Andromeda(Andromeda root, Parity parity) {
		super(root, parity);
	}
	public Andromeda(Andromeda root, Parity parity, AlphaCentauri key, Sun value) {
		super(MilkyWay.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<Sun, AlphaCentauri> o) {
		getKey().comparator(new Sun()).compare(getKey(), o.getKey());
		Entry<Earth,Gliese> entry = getKey().comparator().source();
		comparator((Sun) entry, (AlphaCentauri) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Earth) {
			Earth entry = (Earth) e.getSource();
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					Sun sun = new Sun();
					sun.putValue(entry, (Gliese) entry.getChild());
					sendEvent(new EventArgs(sun));
				}
				break;
			default:
				break;
			}
		} 
		else if(e.getSource() instanceof AlphaCentauri) {
			AlphaCentauri entry = (AlphaCentauri) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if (!isRoot()) {
					getKey().comparator(new Sun()).compare(entry, getValue());
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