package hyperspace.recurrent;

import java.util.Iterator;
import java.util.Objects;

import hyperspace.XML;
import jakarta.xml.bind.annotation.XmlTransient;


public abstract class AbstractCollection<E> 
	extends AbstractRecurrent<Collection<E>> 
		implements Collection<E> {

	private static final long serialVersionUID = -7062454426060890047L;
	
	E element;
	
	@Override
	@XmlTransient
	public E getElement() {
		return element;
	}

	@Override
	public E setElement(E element) {
		E old = this.element;
		this.element = element;
		return old;
	}
	
	public AbstractCollection(Class<? extends AbstractCollection<E>> type) {
		super(type);
		setElement(null);
	}
	public AbstractCollection(AbstractCollection<E> parent, E element) {
		super(parent);
		setElement(element);
	}
	
	@Override
	public boolean isEmpty() {
		return super.isEmpty() ? this.element == null : false;
	}
	@Override
	public AbstractCollection<E> clone() {
		try {
			@SuppressWarnings("unchecked")
			AbstractCollection<E> c = (AbstractCollection<E>) getParentClass().getDeclaredConstructor().newInstance();
			c.setElement(getElement());
			return c;
		} catch (Throwable e) {
			return null;
		}
	}

	@Override
	public boolean contains(Object o) {
		Iterator<E> it = iterator();
        if (o==null) {
            while (it.hasNext())
                if (it.next()==null)
                    return true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }
        return false;
	}

	@Override
	public boolean add(E e) {
		if(isEmpty()) {
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
		Iterator<?> it = iterator();
		while (it.hasNext()) {
			if (c.contains(it.next())) {
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
		Iterator<?> it = iterator();
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
	@Override
	@Deprecated
	public int size() {
		return 0;
	}

	@Override
	@Deprecated
	public Object[] toArray() {
		return null;
	}

	@Override
	@Deprecated
	public <T> T[] toArray(T[] a) {
		return null;
	}
}
