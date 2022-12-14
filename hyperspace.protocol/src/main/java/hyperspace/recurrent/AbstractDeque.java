package hyperspace.recurrent;

import java.util.Iterator;

public abstract class AbstractDeque<E> 
	extends AbstractQueue<E> 
		implements Deque<E> {

	private static final long serialVersionUID = 4488580658335223540L;
	
	public AbstractDeque(Class<? extends AbstractDeque<E>> type) {
		super(type);
	}
	public AbstractDeque(AbstractDeque<E> parent, E element) {
		super(parent, element);
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
		Collection<E> child = call();
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
		return call().getElement();
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
		Iterator<E> it = new ConcurrentIterator(call());
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
		return new ConcurrentIterator(call());
	}
}