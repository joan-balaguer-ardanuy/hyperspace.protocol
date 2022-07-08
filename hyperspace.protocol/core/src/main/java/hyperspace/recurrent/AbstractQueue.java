package hyperspace.recurrent;

public abstract class AbstractQueue<E,K extends Queue<E,K>>
	extends AbstractCollection<E,K> 
		implements Queue<E,K> {

	private static final long serialVersionUID = 7287603745781871134L;

	public AbstractQueue() {
		super();
	}
	public AbstractQueue(Class<? extends K> type, String name) {
		super(type, name);
	}
	public AbstractQueue(Class<? extends K> type, String name, E element) {
		super(type, name, element);
	}
	public AbstractQueue(K parent) {
		super(parent);
	}
	public AbstractQueue(K parent, E element) {
		super(parent, element);
	}
	
	@Override
	public boolean offer(E e) {
		return super.add(e);
	}
	@Override
	public E remove() {
		K parent = getParent();
		parent.clear();
		return parent.getElement();
	}
	@Override
	public E poll() {
		K parent = getParent();
		parent.clear();
		return parent.getElement();
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