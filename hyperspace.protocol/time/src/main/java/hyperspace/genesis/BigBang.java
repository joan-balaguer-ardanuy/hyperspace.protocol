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
public class BigBang extends Screw<Matter,Antimatter> {

	private static final long serialVersionUID = 7325459897854775266L;
	@Override
	public String getName() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumerator<hyperspace.Entry<Matter,Antimatter>> en = enumerator();
		while(en.hasMoreElements()) {
			hyperspace.Entry<Matter,Antimatter> entry = en.nextElement();
			stringBuilder.append(entry.getKey().getName());
		}
		return stringBuilder.toString();
	}
	@Override
	public Matter getKey() {
		return super.getKey();
	}
	@Override
	public Matter setKey(Matter key) {
		return super.setKey(key);
	}
	@Override
	public Antimatter getValue() {
		return super.getValue();
	}
	@Override
	public Antimatter setValue(Antimatter value) {
		return super.setValue(value);
	}
	@XmlElement
	public BigBang getEntry() {
		return call() == getRoot() ? null : (BigBang) call();
	}
	
	public BigBang() {
		this(BigBong.class, Parity.random());
	}
	public BigBang(Parity parity) {
		super(parity);
	}
	public BigBang(Class<BigBong> childClass, Parity parity) {
		super(childClass, parity);
	}
	public BigBang(BigBang parent) {
		super(parent);
	}
	public BigBang(BigBang parent, Matter key, Antimatter value) {
		super(BigBong.class, parent, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	public BigBang(BigBang root, Parity parity) {
		super(root, parity);
	}
	public BigBang(BigBang root, Parity parity, Matter key, Antimatter value) {
		super(BigBong.class, root, parity, key, value);
		key.addEventListener(this);
		value.addEventListener(getChild());
	}
	
	@Override
	public int compareTo(hyperspace.Entry<Antimatter,Matter> o) {
		getKey().comparator(new Antimatter()).compare(getKey(), o.getKey());
		hyperspace.Entry<Interstellar, Supercluster> entry = getKey().comparator().source();
		comparator((Antimatter) entry, (Matter) entry.getChild());
		return 0;
	}
	@Override
	public void event(EventArgs e) {
		super.event(e);
		if(e.getSource() instanceof BigBang) {
			switch (e.getCommand()) {
			case Command.LISTEN:
				BigBang entry = (BigBang) e.getSource();
				entry.permuteChild(call(), get());
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