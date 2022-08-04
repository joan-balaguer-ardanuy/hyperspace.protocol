package hyperspace.recurrent;

public abstract class AbstractQueue<E>
	extends AbstractCollection<E> 
		implements Queue<E> {

	private static final long serialVersionUID = 7287603745781871134L;

	public AbstractQueue() {
		super();
	}
	
	public AbstractQueue(Class<? extends Collection<E>> type, E element) {
		super(type, element);
	}

	public AbstractQueue(Collection<E> parent, E element) {
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