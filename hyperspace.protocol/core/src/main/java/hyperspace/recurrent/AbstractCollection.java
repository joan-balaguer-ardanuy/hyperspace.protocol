package hyperspace.recurrent;

import java.util.Iterator;
import java.util.Objects;

public class AbstractCollection<E> 
	extends AbstractRecurrence<Collection<E>> 
		implements Collection<E> {

	private static final long serialVersionUID = -3175794994944973984L;

	E element;
	
	@Override
	public E getElement() {
		return element;
	}

	@Override
	public E setElement(E element) {
		E old = this.element;
		this.element = element;
		return old;
	}
	
	public AbstractCollection() {
		super();
	}

	public AbstractCollection(Class<? extends Collection<E>> type, E element) {
		super(type);
		setElement(element);
	}

	public AbstractCollection(Collection<E> parent, E element) {
		super(parent);
		setElement(element);
	}

	@Override
	@Deprecated
	public int size() {
		return 1;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	@Deprecated
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		if(getElement() == null) {
			setElement(e);
			return true;
		}
		return instance(getParentClass(), getParent(), e) != null;
	}
	@Override
	public boolean remove(Object o) {
		Iterator<E> it = iterator();
		if (o == null) {
			while (it.hasNext()) {
				if (it.next() == null) {
					it.remove();
					return true;
				}
			}
		} else {
			while (it.hasNext()) {
				if (o.equals(it.next())) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean containsAll(java.util.Collection<?> c) {
		for (Object e : c)
			if (!contains(e))
				return false;
		return true;
	}
	@Override
	public boolean addAll(java.util.Collection<? extends E> c) {
		boolean modified = false;
		for (E e : c)
			if (add(e))
				modified = true;
		return modified;
	}
	@Override
	public boolean removeAll(java.util.Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<E> it = iterator();
		while (it.hasNext()) {
			if (!c.contains(it.next())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}
	@Override
	public boolean retainAll(java.util.Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<E> it = iterator();
		while (it.hasNext()) {
			if (!c.contains(it.next())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}
	@Override
	public Iterator<E> iterator() {
		return new RecurrentIterator(getParent());
	}
	protected final class RecurrentIterator implements Iterator<E> {

		/**
		 * The current time-listener.
		 */
		Collection<E> current;

		/**
		 * The next time-listener.
		 */
		Collection<E> next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public RecurrentIterator(Collection<E> key) {
			next = current = key;
			hasNext = true;
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
			if (c == AbstractCollection.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getElement();
		}
		@Override
		public void remove() {
			Collection<E> k = next;
			current.clear();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
			} else
				hasNext = false;
		 }
	}
	protected final class ConcurrentIterator implements Iterator<E> {

		/**
		 * The current time-listener.
		 */
		Collection<E> current;

		/**
		 * The next time-listener.
		 */
		Collection<E> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public ConcurrentIterator(Collection<E> key) {
			next = current = key;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			Collection<E> c = next;
			current = c;
			next = c.call();
			if (c == AbstractCollection.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getElement();
		}
		@Override
		public void remove() {
			Collection<E> k = next;
			current.clear();
			if (!k.isEmpty()) {
				current = k;
				next = k.call();
			} else
				hasNext = false;
		 }
	}
}
