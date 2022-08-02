package hyperspace.recurrent;

import java.util.Iterator;

import hyperspace.XML;

public abstract class Hyperdeque<E> 
	extends Hyperqueue<E> 
		implements Deque<E> {

	private static final long serialVersionUID = 5923318731838756664L;

	public Hyperdeque() {
		super();
	}
	public Hyperdeque(XML message) {
		super(message);
	}
	public Hyperdeque(Class<? extends Collection<E>> type, Collection<E> key, XML message, E element) {
		super(type, key, message, element);
	}
	public Hyperdeque(Class<? extends Collection<E>> type, XML message, E element) {
		super(type, message, element);
	}
	public Hyperdeque(Collection<E> key, XML message) {
		super(key, message);
	}
	
	@Override
	public void addFirst(E e) {
		super.offer(e);
	}
	@Override
	public void addLast(E e) {
		instance(getType(), this, e);	
	}
	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}
	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}
	@Override
	public E removeFirst() {
		E element = pollFirst();
		if(getParent() == this) {
			if(getElement() == null) {
				throw new Error("deque is empty");
			}
		}
		return element;
	}
	@Override
	public E removeLast() {
		E element = pollLast();
		if(getParent() == this) {
			if(getElement() == null) {
				throw new Error("deque is empty");
			}
		}
		return element;
	}
	@Override
	public E pollFirst() {
		Collection<E> parent = getParent();
		parent.clear();
		return parent.getElement();
	}
	@Override
	public E pollLast() {
		Collection<E> child = getChild().getChild();
		child.clear();
		return child.getElement();
	}
	@Override
	public E getFirst() {
		return getParent().getElement();
	}
	@Override
	public E getLast() {
		return getElement();
	}
	@Override
	public E peekFirst() {
		return getParent().getElement();
	}
	@Override
	public E peekLast() {
		return getChild().getChild().getElement();
	}
	@Override
	public boolean removeFirstOccurrence(Object o) {
		Iterator<E> it = new RecurrentIterator(getParent());
		for(E element = it.next(); it.hasNext(); element = it.next()) {
			if(element == o) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean removeLastOccurrence(Object o) {
		Iterator<E> it = new ConcurrentIterator(getChild().getChild());
		for(E element = it.next(); it.hasNext(); element = it.next()) {
			if(element == o) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	@Override
	public void push(E e) {
		addFirst(e);
	}
	@Override
	public E pop() {
		return pollFirst();
	}
	@Override
	public Iterator<E> descendingIterator() {
		return new ConcurrentIterator(getChild().getChild());
	}
}