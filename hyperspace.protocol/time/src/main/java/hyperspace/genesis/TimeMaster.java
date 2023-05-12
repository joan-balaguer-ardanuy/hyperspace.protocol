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
public class TimeMaster extends ScrewNut<BigBong,BigBang> {

	private static final long serialVersionUID = 8232838480308268761L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<Entry<BigBong,BigBang>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<BigBong,BigBang> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public BigBong getKey() {
		return super.getKey();
	}
	@Override
	public BigBong setKey(BigBong key) {
		return super.setKey(key);
	}
	@Override
	public BigBang getValue() {
		return super.getValue();
	}
	@Override
	public BigBang setValue(BigBang value) {
		return super.setValue(value);
	}
	@XmlElement
	public TimeMaster getEntry() {
		return call() == getRoot() ? null : (TimeMaster) call();
	}
	
	public TimeMaster() {
		this(Aaron.class, Parity.random());
	}
	public TimeMaster(Parity parity) {
		super(parity);
	}
	public TimeMaster(Class<Aaron> childClass, Parity parity) {
		super(childClass, parity);
	}
	public TimeMaster(TimeMaster parent) {
		super(parent);
	}
	public TimeMaster(TimeMaster parent, BigBong key, BigBang value) {
		super(Aaron.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public TimeMaster(TimeMaster root, Parity parity) {
		super(root, parity);
	}
	public TimeMaster(TimeMaster root, Parity parity, BigBong key, BigBang value) {
		super(Aaron.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(Entry<BigBang, BigBong> o) {
		getKey().comparator(new BigBang()).compare(getKey(), o.getKey());
		Entry<Matter,Antimatter> entry = getKey().comparator().source();
		comparator((BigBang) entry, (BigBong) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Matter) {
			Matter entry = (Matter) e.getSource();
			switch (e.getCommand()) {
			case Command.INSTANCE:
				if(isRoot()) {
					BigBang bigBang = new BigBang();
					bigBang.putValue(entry, (Antimatter) entry.getChild());
					sendEvent(new EventArgs(bigBang));
				}
				break;
			default:
				break;
			}
		} 
		else if(e.getSource() instanceof BigBong) {
			BigBong entry = (BigBong) e.getSource();
			switch (e.getCommand()) {
			case Command.LISTEN:
				if (!isRoot()) {
					getKey().comparator(new BigBang()).compare(entry, getValue());
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