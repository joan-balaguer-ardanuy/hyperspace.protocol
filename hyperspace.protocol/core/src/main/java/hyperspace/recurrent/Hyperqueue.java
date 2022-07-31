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
	public Hyperqueue(Class<? extends Hyperqueue<E>> type, Class<? extends Hyperqueue<E>> antitype, XML message) {
		super(type, antitype, message);
	}
	public Hyperqueue(Hyperqueue<E> parent, XML message) {
		super(parent, message);
	}
	public Hyperqueue(Class<? extends Hyperqueue<E>> antitype, Hyperqueue<E> parent, XML message, E element) {
		super(antitype, parent, message, element);
	}
	public Hyperqueue(Hyperqueue<E> root, Hyperqueue<E> stem, XML message) {
		super(root, stem, message);
	}
	public Hyperqueue(Class<? extends Hyperqueue<E>> antitype, Hyperqueue<E> root, Hyperqueue<E> stem, XML message, E element) {
		super(antitype, root, stem, message, element);
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