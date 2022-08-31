package hyperspace.recurrent;

import hyperspace.EventArgs;
import hyperspace.Time;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestCollection extends Time {

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
		super();
		integerCollection = new IntegerCollection();
		
		integerCollection.add(1);
		integerCollection.add(2);
		
		System.out.println(this.toString());
	}

	public static void main(String[] args) {
		new TestCollection();
	}
	@Override
	public void event(EventArgs e) {
		// TODO Auto-generated method stub
		
	}
}
