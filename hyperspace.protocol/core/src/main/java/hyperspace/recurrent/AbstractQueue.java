package hyperspace.recurrent;

public abstract class AbstractQueue<E>
	extends AbstractCollection<E> 
		implements Queue<E> {

	private static final long serialVersionUID = -9119766514516058287L;
	public AbstractQueue(Class<? extends AbstractQueue<E>> type) {
		super(type);
	}
	public AbstractQueue(Class<? extends AbstractQueue<E>> type, E element) {
		super(type, element);
	}
	public AbstractQueue(AbstractQueue<E> parent, E element) {
		super(parent, element);
	}

	@Override
	public boolean offer(E e) {
		return super.add(e);
	}
	@Override
	public E remove() {
		Collection<E> parent = getParent();
		parent.clear();
		return parent.getElement();
	}
	@Override
	public E poll() {
		Collection<E> parent = getParent();
		parent.clear();
		return parent.getElement() == null ? null : parent.getElement();
	}
	@Override
	public E element() {
		return getParent().getElement();
	}
	@Override
	public E peek() {
		return getParent().getElement();
	}
}