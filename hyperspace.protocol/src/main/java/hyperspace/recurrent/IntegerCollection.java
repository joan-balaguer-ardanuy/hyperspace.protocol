package hyperspace.recurrent;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IntegerCollection extends AbstractCollection<Integer> {

	private static final long serialVersionUID = 2595284205255420069L;

	@Override
	@XmlElement
	public Integer getElement() {
		return super.getElement();
	}
	@Override
	public Integer setElement(Integer element) {
		return super.setElement(element);
	}
	@XmlElement
	public IntegerCollection getExtension() {
		IntegerCollection parent = (IntegerCollection) getParent();
		return parent == getRoot() ? null : parent;
	}
	
	public IntegerCollection() {
		super(IntegerCollection.class);
	}

	public IntegerCollection(IntegerCollection parent, Integer element) {
		super(parent, element);
	}

}