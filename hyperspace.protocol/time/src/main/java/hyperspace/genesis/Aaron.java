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
public class Aaron extends Screw<BigBang,BigBong> {

	private static final long serialVersionUID = -4885033226486761983L;

	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<hyperspace.Entry<BigBang,BigBong>> en = enumerator();
		while(en.hasMoreElements()) {
			hyperspace.Entry<BigBang,BigBong> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public BigBang getKey() {
		return super.getKey();
	}
	@Override
	public BigBang setKey(BigBang key) {
		return super.setKey(key);
	}
	@Override
	public BigBong getValue() {
		return super.getValue();
	}
	@Override
	public BigBong setValue(BigBong value) {
		return super.setValue(value);
	}
	@XmlElement
	public Aaron getEntry() {
		return call() == getRoot() ? null : (Aaron) call();
	}
	
	public Aaron() {
		this(TimeMaster.class, Parity.random());
	}
	public Aaron(Parity parity) {
		super(parity);
	}
	public Aaron(Class<TimeMaster> childClass, Parity parity) {
		super(childClass, parity);
	}
	public Aaron(Aaron parent) {
		super(parent);
	}
	public Aaron(Aaron parent, BigBang key, BigBong value) {
		super(TimeMaster.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public Aaron(Aaron root, Parity parity) {
		super(root, parity);
	}
	public Aaron(Aaron root, Parity parity, BigBang key, BigBong value) {
		super(TimeMaster.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(hyperspace.Entry<BigBong,BigBang> o) {
		getKey().comparator(new BigBong()).compare(getKey(), o.getKey());
		hyperspace.Entry<Antimatter,Matter> entry = getKey().comparator().source();
		comparator((BigBong) entry, (BigBang) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof Aaron) {
			Aaron entry = (Aaron) e.getSource();
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
