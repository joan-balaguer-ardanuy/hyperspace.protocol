package hyperspace.recurrent;

import hyperspace.Entry;

public abstract class AbstractQueue<E>
	extends AbstractCollection<E> 
		implements Queue<E> {

	private static final long serialVersionUID = 7287603745781871134L;

	public AbstractQueue() {
		super();
	}
	public AbstractQueue(String name) {
		super(name);
	}
	public AbstractQueue(Class<? extends Queue<E>> type, Class<? extends Queue<E>> antitype, String name, E element) {
		super(type, antitype, name, element);
	}
	public AbstractQueue(Queue<E> parent) {
		super(parent);
	}
	public AbstractQueue(Class<? extends Queue<E>> antitype, Queue<E> parent, E element) {
		super(antitype, parent, element);
	}
	public AbstractQueue(Collection<E> root, String name) {
		super(root, name);
	}
	public AbstractQueue(Class<? extends Queue<E>> antitype, Queue<E> root, String name, E element) {
		super(antitype, root, name, element);
	}
	@Override
	public boolean offer(E e) {
		return super.add(e);
	}
	@Override
	public E remove() {
		Entry<E,E> parent = getParent();
		parent.clear();
		return parent.getKey();
	}
	@Override
	public E poll() {
		Entry<E,E> parent = getParent();
		parent.clear();
		return parent.getKey() == null ? null : parent.getKey();
	}
	@Override
	public E element() {
		return getParent().getKey();
	}
	@Override
	public E peek() {
		return getParent().getKey();
	}
}