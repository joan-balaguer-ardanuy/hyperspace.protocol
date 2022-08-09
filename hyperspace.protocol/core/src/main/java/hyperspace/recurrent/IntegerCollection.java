package hyperspace.recurrent;

public class IntegerCollection extends AbstractCollection<Integer> {

	private static final long serialVersionUID = 2595284205255420069L;

	public IntegerCollection() {
	}

	public IntegerCollection(Integer element) {
		super(IntegerCollection.class, element);
	}

	public IntegerCollection(IntegerCollection parent, Integer element) {
		super(parent, element);
	}

}