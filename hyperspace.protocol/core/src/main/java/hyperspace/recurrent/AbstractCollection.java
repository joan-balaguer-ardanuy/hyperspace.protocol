package hyperspace.recurrent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import javax.xml.bind.annotation.XmlTransient;

public class AbstractCollection<E> 
	extends AbstractRecurrence<Collection<E>> 
		implements Collection<E> {

	private static final long serialVersionUID = -7062454426060890047L;
	
	public static final int MAX_ARRAY_SIZE = 2147483647;
		
	E entry;
	
	@Override
	@XmlTransient
	public E getEntry() {
		return entry;
	}
	@Override
	public E setEntry(E entry) {
		if(entry == null)
			throw new NullPointerException();
		E old = this.entry;
		this.entry = entry;
		return old;
	}
	
	public AbstractCollection() {
		super();
	}
	
	public AbstractCollection(Collection<E> parent, E entry) {
		super(parent);
		setEntry(entry);
		getRoot().setEntry(entry);
	}

	@Deprecated
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
	public boolean contains(Object o) {
		Objects.requireNonNull(o);
		Iterator<E> en = iterator();
		while (en.hasNext())
            if (o.equals(en.next()))
                return true;
        return false;
	}

	@Override
	public boolean add(E e) {
		Objects.requireNonNull(e);
		return instance(getClass(), getRoot(), e) != null;
	}
	
	@Override
	public boolean remove(Object o) {
		Objects.requireNonNull(o);
		Iterator<E> en = iterator();
		while (en.hasNext()) {
			if (o.equals(en.next())) {
				en.remove();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean containsAll(java.util.Collection<?> c) {
		Objects.requireNonNull(c);
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}
	
	@Override
	public boolean addAll(java.util.Collection<? extends E> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<? extends E> en = c.iterator();
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
		 * The current collection.
		 */
		private Collection<E> current;
		
		/**
		 * The next collection.
		 */
		private Collection<E> next;
		
		/**
		 * If this recursor has next collection.
		 */
		private boolean hasNext;
		
		public RecurrentIterator(Collection<E> c) {
			next = current = c;
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
			if(c == AbstractCollection.this)
				hasNext = false;
			else hasNext = true;
			return c.getEntry();
		}
		@Override
		public void remove() {
			Collection<E> c = next;
			current.release();
			if (!c.isEmpty()) {
				current = c;
				next = c.getParent();
			} else
				hasNext = false;
		}
	}
	protected final class ConcurrentIterator implements Iterator<E> {
		
		/**
		 * The current collection.
		 */
		private Collection<E> current;
		
		/**
		 * The next collection.
		 */
		private Collection<E> next;
		
		/**
		 * If this recursor has next collection.
		 */
		private boolean hasNext;
		
		public ConcurrentIterator(Collection<E> c) {
			next = current = c;
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
			if(c == AbstractCollection.this)
				hasNext = false;
			else hasNext = true;
			return c.getEntry();
		}
		@Override
		public void remove() {
			Collection<E> c = next;
			current.release();
			if (!c.isEmpty()) {
				current = c;
				next = c.call();
			} else
				hasNext = false;
		}
	}
	@Deprecated
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

	@Deprecated
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
	@Deprecated
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
	@Deprecated
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError
                ("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
}