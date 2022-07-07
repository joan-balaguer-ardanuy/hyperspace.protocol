package hyperspace.recurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class AbstractList<E> extends AbstractCollection<E,List<E>> implements List<E> {

	private static final long serialVersionUID = -8462491103350194008L;
	
	public AbstractList() {
	}
	public AbstractList(Class<? extends List<E>> type, String name) {
		super(type, name);
	}
	public AbstractList(Class<? extends List<E>> type, String name, E element) {
		super(type, name, element);
	}
	public AbstractList(List<E> parent) {
		super(parent);
	}
	public AbstractList(List<E> parent, E element) {
		super(parent, element);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
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
		Iterator<E> it = new RecurrentIterator(getParent());
		for(; it.hasNext();) {
			if(index == 0) {
				it.remove();
				return element;
			}
			index--;
		}
		return null;
	}

	@Override
	public void add(int index, E element) {

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
	public int dimension(List<E> source) {
		Iterator<E> it = new RecurrentIterator(getParent());
		int index = 0;
		E element = null;
		for(element = it.next();it.hasNext();it.next()) {
			if(element == source.getElement()) {
				return index;
			}
			index++;
		}
        return index;
	}
	@Override
	public ListIterator<E> listIterator() {
		return new ListRecurrentIterator(getParent());
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}
	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
	
	protected final class ListRecurrentIterator implements ListIterator<E> {

		/**
		 * The current time-listener.
		 */
		List<E> init;
		
		/**
		 * The current time-listener.
		 */
		List<E> current;

		/**
		 * The next time-listener.
		 */
		List<E> next;

		/**
		 * The next time-listener.
		 */
		List<E> previous;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;
		
		public ListRecurrentIterator(List<E> source) {
			init = next = previous = current = source;
		}
		
		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			List<E> c = next;
			current = c;
			next = c.getParent();
			previous = c.getChild().getChild();
			if (c == AbstractList.this)
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
			List<E> c = previous;
			current = c;
			next = c.getParent();
			previous = c.getChild().getChild();
			if (c == AbstractList.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getElement();
		}
		@Override
		public int nextIndex() {
			return next.dimension(init);
		}
		@Override
		public int previousIndex() {
			return previous.dimension(init);
		}
		@Override
		public void remove() {
			List<E> k = next;
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
			instance(getType(), getType(), current, e);
		}
	}
}