package hyperspace.recurrent;

import java.util.Iterator;
import java.util.ListIterator;

import hyperspace.XML;

public class Hyperlist<E> extends Hypercollection<E> implements List<E> {

	private static final long serialVersionUID = -8462491103350194008L;
	
	public Hyperlist() {
		super();
	}
	public Hyperlist(Class<? extends Collection<E>> type, Collection<E> key, XML message, E element) {
		super(type, key, message, element);
	}
	public Hyperlist(Class<? extends Collection<E>> type, XML message, E element) {
		super(type, message, element);
	}
	public Hyperlist(Collection<E> key, XML message) {
		super(key, message);
	}
	public Hyperlist(XML message) {
		super(message);
	}

	@Override
	public boolean addAll(int index, java.util.Collection<? extends E> c) {
		boolean modified = false;
        for (E e : c) {
            add(index++, e);
            modified = true;
        }
        return modified;
	}

	@Override
	public E get(int index) {
		Iterator<E> it = new RecurrentIterator(getParent());
		for(E element = it.next(); it.hasNext(); element = it.next()) {
			if(index == 0) {
				return element;
			}
			index--;
		}
		return null;
	}

	@Override
	public E set(int index, E element) {
		ListIterator<E> it = new RecursiveListIterator(getParent());
		for(E current = it.next(); it.hasNext(); current = it.next()) {
			if(index == 0) {
				it.set(element);
				return current;
			}
			index--;
		}
		return null;
	}

	@Override
	public void add(int index, E element) {
		ListIterator<E> it = new RecursiveListIterator(getParent());
		for(; it.hasNext();) {
			if(index == 0) {
				it.add(element);
			}
			index--;
		}
	}

	@Override
	public E remove(int index) {
		Iterator<E> it = new RecurrentIterator(getParent());
		for(E element = it.next(); it.hasNext(); element = it.next()) {
			if(index == 0) {
				it.remove();
				return element;
			}
			index--;
		}
		return null;
	}
	@Override
	public int indexOf(Object o) {
		Iterator<E> it;
		int index = 0;
		for(it = new RecurrentIterator(getParent());it.hasNext();it.next()) {
			index++;
		}
        return index;
	}
	@Override
	public int lastIndexOf(Object o) {
		Iterator<E> it;
		int index = 0;
		for(it = new ConcurrentIterator(getChild().getChild());it.hasNext();it.next()) {
			index++;
		}
        return index;
	}
	@Override
	public ListIterator<E> listIterator() {
		return new RecursiveListIterator(getParent());
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}
	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
	
	protected final class RecursiveListIterator implements ListIterator<E> {

		/**
		 * The current time-listener.
		 */
		Collection<E> init;
		
		/**
		 * The current time-listener.
		 */
		Collection<E> current;

		/**
		 * The next time-listener.
		 */
		Collection<E> next;

		/**
		 * The next time-listener.
		 */
		Collection<E> previous;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;
		
		public RecursiveListIterator(Collection<E> source) {
			init = next = previous = current = source;
		}
		
		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			Collection<E> c = next;
			current = c;
			next = c.getParent();
			previous = c.getChild().getChild();
			if (c == Hyperlist.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getElement();
		}
		@Override
		public boolean hasPrevious() {
			return hasNext;
		}
		@Override
		public E previous() {
			Collection<E> c = previous;
			current = c;
			next = c.getParent();
			previous = c.getChild().getChild();
			if (c == Hyperlist.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getElement();
		}
		@Override
		public int nextIndex() {
			return 0;
		}
		@Override
		public int previousIndex() {
			return 0;
		}
		@Override
		public void remove() {
			Collection<E> k = next;
			current.clear();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
				previous = k.getChild().getChild();
			} else
				hasNext = false;
		}
		@Override
		public void set(E e) {
			current.setElement(e);
		}
		@Override
		public void add(E e) {
			current = instance(type, type, current, e);
			next = current.getParent();
			previous = current.getChild().getChild();
		}
	}
}