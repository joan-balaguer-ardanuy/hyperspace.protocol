package hyperspace.recurrent;

import java.util.Iterator;

public abstract class AbstractDeque<E> 
	extends AbstractQueue<E,Deque<E>> 
		implements Deque<E> {

	private static final long serialVersionUID = 5923318731838756664L;

	public AbstractDeque() {
		super();
	}
	public AbstractDeque(Class<? extends Deque<E>> type, String name) {
		super(type, name);
	}
	public AbstractDeque(Class<? extends Deque<E>> type, String name, E element) {
		super(type, name, element);
	}
	public AbstractDeque(Deque<E> parent) {
		super(parent);
	}
	public AbstractDeque(Deque<E> parent, E element) {
		super(parent, element);
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
		Deque<E> parent = getParent();
		parent.clear();
		return parent.getElement();
	}
	@Override
	public E pollLast() {
		Deque<E> child = getChild().getChild();
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