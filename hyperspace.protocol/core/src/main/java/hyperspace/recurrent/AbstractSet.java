package hyperspace.recurrent;

public class AbstractSet<E> 
	extends AbstractCollection<E> 
		implements Set<E> {

	private static final long serialVersionUID = 6434843938453234940L;

	public AbstractSet() {
		super();
	}
	public AbstractSet(AbstractSet<E> parent, E element) {
		super(parent, element);
	}
	@Override
	public boolean add(E e) {
		if(!contains(e)) {
			return super.add(e);
		}
		else return false;
	}
}
