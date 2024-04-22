package hyperspace.recurrent;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
		Objects.requireNonNull(e);
		instance(getClass(), getRoot().getParent(), e);
	}
	@Override
	public void addLast(E e) {
		super.offer(e);
	}
	@Override
	public E removeFirst() {
		return super.remove();
	}
	@Override
	public E removeLast() {
		Collection<E> past = call();
		past.release();
		return past.getEntry();
	}
	@Override
	public E getFirst() {
		return super.element();
	}
	@Override
	public E getLast() {
		if(isEmpty())
			throw new NoSuchElementException();
		return call().getEntry();
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
		Objects.requireNonNull(e);
		return instance(getClass(), getRoot().getParent(), e) != null;
	}
	@Override
	public boolean offerLast(E e) {
		return super.offer(e);
	}
	@Override
	public E pollFirst() {
		return super.poll();
	}
	@Override
	public E pollLast() {
		if(isEmpty())
			return null;
		Collection<E> past = call();
		past.release();
		return past.getEntry();
	}
	@Override
	public E peekFirst() {
		return super.peek();
	}
	@Override
	public E peekLast() {
		if(isEmpty())
			return null;
		return call().getEntry();
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