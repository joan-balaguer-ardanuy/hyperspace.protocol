package hyperspace.recurrent;

public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E>{

	private static final long serialVersionUID = 6434843938453234940L;

	public AbstractSet(Class<? extends AbstractCollection<E>> type) {
		super(type);
	}
	public AbstractSet(Class<? extends AbstractCollection<E>> type, E element) {
		super(type, element);
	}
	public AbstractSet(AbstractCollection<E> parent, E element) {
		super(parent, element);
	}

}
