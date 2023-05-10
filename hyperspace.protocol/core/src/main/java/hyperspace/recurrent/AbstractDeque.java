package hyperspace.recurrent;

import java.util.Iterator;

public class AbstractDeque<E> 
	extends AbstractQueue<E> 
		implements Deque<E> {

	private static final long serialVersionUID = 4488580658335223540L;
	
	public AbstractDeque() {
		super();
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
		instance(getClass(), this, e);	
	}
	@Override
	public E removeFirst() {
		Collection<E> parent = getParent();
		parent.release();
		return parent.getEntry();
	}
	@Override
	public E removeLast() {
		Collection<E> child = call();
		child.release();
		return child.getEntry();
	}
	@Override
	public E getFirst() {
		return getParent().getEntry();
	}
	@Override
	public E getLast() {
		return getEntry();
	}
	@Override
	public boolean removeFirstOccurrence(Object o) {
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			if(it.next() == o) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean removeLastOccurrence(Object o) {
		Iterator<E> it = descendingIterator();
		while(it.hasNext()) {
			if(it.next() == o) {
				it.remove();
				return true;
			}
		}
		return false;
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
	public E pollFirst() {
		return removeFirst();
	}
	@Override
	public E pollLast() {
		return removeLast();
	}
	@Override
	public E peekFirst() {
		return getFirst();
	}
	@Override
	public E peekLast() {
		return getLast();
	}
	@Override
	public Iterator<E> descendingIterator() {
		return new ConcurrentIterator(call());
	}
	@Override
	public void push(E e) {
		addFirst(e);
	}
	@Override
	public E pop() {
		return removeFirst();
	}
}