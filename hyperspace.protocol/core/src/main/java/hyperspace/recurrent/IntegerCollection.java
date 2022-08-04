package hyperspace.recurrent;

public class IntegerCollection extends AbstractCollection<Integer> {

	private static final long serialVersionUID = 8329625439490271449L;

	public IntegerCollection() {
	}

	public IntegerCollection(Integer element) {
		super(IntegerCollection.class, element);
	}

	public IntegerCollection(AbstractCollection<Integer> parent, Integer element) {
		super(parent, element);
	}

}