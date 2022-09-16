package hyperspace.recurrent;

public class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {

	private static final long serialVersionUID = 6434843938453234940L;

	public AbstractSet(Class<? extends Set<E>> type) {
		super(type);
	}
	public AbstractSet(Set<E> parent, E element) {
		super(parent, element);
	}
}
