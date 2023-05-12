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
public class AlphaCentauri extends ScrewNut<Gliese,Earth> {

	private static final long serialVersionUID = -1649925660086238159L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<Gliese,Earth>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<Gliese,Earth> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Gliese getKey() {
		return super.getKey();
	}
	@Override
	public Gliese setKey(Gliese key) {
		return super.setKey(key);
	}
	@Override
	public Earth getValue() {
		return super.getValue();
	}
	@Override
	public Earth setValue(Earth value) {
		return super.setValue(value);
	}
	@XmlElement
	public AlphaCentauri getEntry() {
		return call() == getRoot() ? null : (AlphaCentauri) call();
	}
	
	public AlphaCentauri() {
		this(Sun.class, Parity.random());
	}
	public AlphaCentauri(Parity parity) {
		super(parity);
	}
	public AlphaCentauri(Class<Sun> childClass, Parity parity) {
		super(childClass, parity);
	}
	public AlphaCentauri(AlphaCentauri parent) {
		super(parent);
	}
	public AlphaCentauri(AlphaCentauri parent, Gliese key, Earth value) {
		super(Sun.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public AlphaCentauri(AlphaCentauri root, Parity parity) {
		super(root, parity);
	}
	public AlphaCentauri(AlphaCentauri root, Parity parity, Gliese key, Earth value) {
		super(Sun.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<Earth, Gliese> o) {
		getKey().comparator(new Earth()).compare(getKey(), o.getKey());
		Entry<Operon,Polyploid> entry = getKey().comparator().source();
		comparator((Earth) entry, (Gliese) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Operon) {
			Operon entry = (Operon) e.getSource();
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					Earth earth = new Earth();
					earth.putValue(entry, (Polyploid) entry.getChild());
					sendEvent(new EventArgs(earth));
				}
				break;
			default:
				break;
			}
		} 
		else if(e.getSource() instanceof Gliese) {
			Gliese entry = (Gliese) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if (!isRoot() && entry.isRoot()) {
					getKey().comparator(new Earth()).compare(entry, getValue());
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