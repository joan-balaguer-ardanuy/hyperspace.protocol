package hyperspace.recurrent;

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
		integerCollection2 = new IntegerCollection();
		
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
}
