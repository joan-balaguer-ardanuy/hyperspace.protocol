package hyperspace.recurrent;

import hyperspace.EventArgs;
import hyperspace.Program;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestCollection extends Program {

	private static final long serialVersionUID = 6554912827689942381L;

	IntegerCollection integerCollection;
	IntegerCollection integerCollection2;
	
	@XmlElement
	public IntegerCollection getIntegerCollection() {
		return integerCollection;
	}
	public void setIntegerCollection(IntegerCollection intengerCollection) {
		this.integerCollection = intengerCollection;
	}
	@XmlElement
	public IntegerCollection getIntegerCollection2() {
		return integerCollection2;
	}
	public void setIntegerCollection2(IntegerCollection integerCollection2) {
		this.integerCollection2 = integerCollection2;
	}

	public TestCollection() {
		super(new XMLTest());
		integerCollection = new IntegerCollection(0);
		integerCollection2 = new IntegerCollection(0);
		
		for(int i = 1; i < 10; i++) {
			integerCollection.add(i);
		}
		for(int i = 1; i < 10; i++) {
			integerCollection2.add(i);
		}
		System.out.println(this.toString());
	}

	public static void main(String[] args) {
		new TestCollection();
	}

	@Override
	public void run() {
		
	}

	@Override
	protected void sendEvent(EventArgs e) {
		
	}

}
