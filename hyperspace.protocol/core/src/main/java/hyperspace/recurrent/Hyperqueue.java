package hyperspace.recurrent;

import hyperspace.Entry;
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
	public Hyperqueue(Class<? extends Queue<E>> type, Class<? extends Queue<E>> antitype, XML message) {
		super(type, antitype, message);
	}
	public Hyperqueue(Queue<E> parent) {
		super(parent);
	}
	public Hyperqueue(Class<? extends Queue<E>> antitype, Queue<E> parent, E element) {
		super(antitype, parent, element);
	}
	public Hyperqueue(Collection<E> root, XML message) {
		super(root, message);
	}
	public Hyperqueue(Class<? extends Queue<E>> antitype, Queue<E> root, XML message, E element) {
		super(antitype, root, message, element);
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