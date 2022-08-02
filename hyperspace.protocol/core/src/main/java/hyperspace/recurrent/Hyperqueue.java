package hyperspace.recurrent;

import hyperspace.XML;

public abstract class Hyperqueue<E>
	extends Hypercollection<E> 
		implements Queue<E> {

	private static final long serialVersionUID = 7287603745781871134L;

	public Hyperqueue() {
		super();
	}
	public Hyperqueue(XML message) {
		super(message);
	}
	public Hyperqueue(Class<? extends Collection<E>> type, Collection<E> key, XML message, E element) {
		super(type, key, message, element);
	}
	public Hyperqueue(Class<? extends Collection<E>> type, XML message, E element) {
		super(type, message, element);
	}
	public Hyperqueue(Collection<E> key, XML message) {
		super(key, message);
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