package hyperspace.recurrent;

import java.util.Iterator;

import hyperspace.Entry;

public abstract class Hyperdeque<E> 
	extends Hyperqueue<E> 
		implements Deque<E> {

	private static final long serialVersionUID = 5923318731838756664L;

	public Hyperdeque() {
		super();
	}
	public Hyperdeque(String name) {
		super(name);
	}
	public Hyperdeque(Class<? extends Deque<E>> type, Class<? extends Deque<E>> antitype, String name,
			E element) {
		super(type, antitype, name, element);
	}
	public Hyperdeque(Class<? extends Deque<E>> antitype, Deque<E> root, String name, E element) {
		super(antitype, root, name, element);
	}
	public Hyperdeque(Class<? extends Deque<E>> antitype, Deque<E> parent, E element) {
		super(antitype, parent, element);
	}
	public Hyperdeque(Deque<E> root, String name) {
		super(root, name);
	}
	public Hyperdeque(Deque<E> parent) {
		super(parent);
	}
	@Override
	public void addFirst(E e) {
		super.offer(e);
	}
	@Override
	public void addLast(E e) {
		instance(getParentClass(), this, e);	
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
			if(getKey() == null) {
				throw new Error("deque is empty");
			}
		}
		return element;
	}
	@Override
	public E removeLast() {
		E element = pollLast();
		if(getParent() == this) {
			if(getKey() == null) {
				throw new Error("deque is empty");
			}
		}
		return element;
	}
	@Override
	public E pollFirst() {
		Entry<E,E> parent = getParent();
		parent.clear();
		return parent.getKey();
	}
	@Override
	public E pollLast() {
		Entry<E,E> child = getChild().getChild();
		child.clear();
		return child.getKey();
	}
	@Override
	public E getFirst() {
		return getParent().getKey();
	}
	@Override
	public E getLast() {
		return getKey();
	}
	@Override
	public E peekFirst() {
		return getParent().getKey();
	}
	@Override
	public E peekLast() {
		return getChild().getChild().getKey();
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