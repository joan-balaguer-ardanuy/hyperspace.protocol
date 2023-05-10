package hyperspace.recurrent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import javax.xml.bind.annotation.XmlTransient;

public class AbstractCollection<E> 
	extends AbstractRecurrence<Collection<E>> 
		implements Collection<E> {

	private static final long serialVersionUID = -7062454426060890047L;
	
	public static int MAX_ARRAY_SIZE = 2^31-1;
	
	
	E element;
	
	@Override
	@XmlTransient
	public E getEntry() {
		return element;
	}

	@Override
	public E setEntry(E element) {
		E old = this.element;
		this.element = element;
		return old;
	}
	
	public AbstractCollection() {
		super();
	}
	public AbstractCollection(Collection<E> parent, E element) {
		super(parent);
		setEntry(element);
	}
	
	@Override
	public boolean isEmpty() {
		return super.isEmpty() ? this.element == null : false;
	}
	@Override
	public int size() {
		int i = 0;
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			it.next();
			i++;
		}
		return i;
	}
	@Override
	public void clear() {
		release();
	}
	@Override
	public Collection<E> clone() {
		try {
			@SuppressWarnings("unchecked")
			Collection<E> c = (Collection<E>) getClass().getDeclaredConstructor().newInstance();
			c.setEntry(getEntry());
			return c;
		} catch (Throwable e) {
			return null;
		}
	}

	@Override
	public boolean contains(Object o) {
		Iterator<E> en = iterator();
        if (o==null) {
            while (en.hasNext())
                if (en.next()==null)
                    return true;
        } else {
            while (en.hasNext())
                if (o.equals(en.next()))
                    return true;
        }
        return false;
	}

	@Override
	public boolean add(E e) {
		if(isEmpty()) {
			setEntry(e);
			return true;
		}
		return instance(getClass(), getParent(), e) != null;
	}
	
	@Override
	public boolean remove(Object o) {
		Iterator<E> en = iterator();
		if (o == null) {
			while (en.hasNext()) {
				if (en.next() == null) {
					en.remove();
					return true;
				}
			}
		} else {
			while (en.hasNext()) {
				if (o.equals(en.next())) {
					en.remove();
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean containsAll(java.util.Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}
	@Override
	public boolean addAll(java.util.Collection<? extends E> c) {
		boolean modified = false;
		Iterator<E> en = iterator();
		while(en.hasNext())
			if (add(en.next()))
				modified = true;
		return modified;
	}
	@Override
	public boolean removeAll(java.util.Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<E> it = iterator();
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
		Iterator<E> en = iterator();
		while (en.hasNext()) {
			if (!c.contains(en.next())) {
				en.remove();
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
		protected Collection<E> current;
		
		/**
		 * The next time-listener.
		 */
		protected Collection<E> next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		protected boolean hasNext;
		
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
			Collection<E> e = next;
			current = e;
			next = e.getParent();
			if(e == AbstractCollection.this)
				hasNext = false;
			else hasNext = true;
			return e.getEntry();
		}
		@Override
		public void remove() {
			Collection<E> k = next;
			current.release();
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
		protected Collection<E> current;
		
		/**
		 * The next time-listener.
		 */
		protected Collection<E> next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		protected boolean hasNext;
		
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
			Collection<E> e = next;
			current = e;
			next = e.call();
			if(e == AbstractCollection.this)
				hasNext = false;
			else hasNext = true;
			return e.getEntry();
		}
		@Override
		public void remove() {
			Collection<E> k = next;
			current.release();
			if (!k.isEmpty()) {
				current = k;
				next = k.call();
			} else
				hasNext = false;
		}
	}
	@Override
	public Object[] toArray() {
		// Estimate size of array; be prepared to see more or fewer elements
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) // fewer elements than expected
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		 // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        T[] r = a.length >= size ? a :
                  (T[])java.lang.reflect.Array
                  .newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) { // fewer elements than expected
                if (a == r) {
                    r[i] = null; // null-terminate
                } else if (a.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T)it.next();
        }
        // more elements than expected
        return it.hasNext() ? finishToArray(r, it) : r;
	}
	 /**
     * Reallocates the array being used within toArray when the iterator
     * returned more elements than expected, and finishes filling it from
     * the iterator.
     *
     * @param r the array, replete with previously stored elements
     * @param it the in-progress iterator over this collection
     * @return array containing the elements in the given array, plus any
     *         further elements returned by the iterator, trimmed to size
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                int newCap = cap + (cap >> 1) + 1;
                // overflow-conscious code
                if (newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T)it.next();
        }
        // trim if overallocated
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError
                ("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
}