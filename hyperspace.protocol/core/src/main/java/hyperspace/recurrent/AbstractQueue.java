package hyperspace.recurrent;

public abstract class AbstractQueue<E>
	extends AbstractCollection<E> 
		implements Queue<E> {

	private static final long serialVersionUID = -9119766514516058287L;
	
	public AbstractQueue() {
		super();
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
		parent.release();
		return parent.getEntry();
	}
	@Override
	public E poll() {
		Collection<E> parent = getParent();
		parent.release();
		return parent.getEntry();
	}
	@Override
	public E element() {
		return getParent().getEntry();
	}
	@Override
	public E peek() {
		return getParent().getEntry();
	}
}