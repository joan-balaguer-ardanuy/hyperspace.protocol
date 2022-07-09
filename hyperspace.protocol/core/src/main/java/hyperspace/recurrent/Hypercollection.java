package hyperspace.recurrent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import hyperspace.Command;
import hyperspace.Entry;
import hyperspace.Hyperspace;

public abstract class Hypercollection<E> 
	extends Hyperspace<E,E>
		implements Collection<E> {

	private static final long serialVersionUID = 8031826521414991529L;

	public Hypercollection() {
		super();
	}
	public Hypercollection(String name) {
		super(name);
	}
	public Hypercollection(Class<? extends Collection<E>> type, Class<? extends Collection<E>> antitype, String name, E element) {
		super(type, antitype, name, element, element);
	}
	public Hypercollection(Collection<E> parent) {
		super(parent);
	}
	public Hypercollection(Class<? extends Entry<E, E>> antitype, Entry<E, E> parent, E element) {
		super(antitype, parent, element, element);
	}
	public Hypercollection(Collection<E> root, String name) {
		super(root, name);
	}
	public Hypercollection(Class<? extends Collection<E>> antitype, Collection<E> root, String name, E element) {
		super(antitype, root, name, element, element);
	}
	@Override
	public Collection<E> clone() {
		return (Collection<E>) super.clone();
	}
	@Override
	public int size() {
		Entry<E,E> parent = getParent();
		int size = 0;
		do {
			size++;
			parent = parent.getParent();
		} while(parent != this);
		return size;
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
	public Iterator<E> iterator() {
		return new RecurrentIterator(getParent());
	}
	
	public static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
	
    @SuppressWarnings({ "unchecked" })
    public <X> X[] toArray(X[] a) {
        // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        X[] r = a.length >= size ? a :
                  (X[])java.lang.reflect.Array
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
            r[i] = (X)it.next();
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
        int len = r.length;
        int i = len;
        while (it.hasNext()) {
            if (i == len) {
                len = newLength(len,
                        1,             /* minimum growth */
                        (len >> 1) + 1 /* preferred growth */);
                r = Arrays.copyOf(r, len);
            }
            r[i++] = (T)it.next();
        }
        // trim if overallocated
        return (i == len) ? r : Arrays.copyOf(r, i);
    }
    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // preconditions not checked because of inlining
        // assert oldLength >= 0
        // assert minGrowth > 0

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            // put code cold in a separate method
            return hugeLength(oldLength, minGrowth);
        }
    }
    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError(
                "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
            return SOFT_MAX_ARRAY_LENGTH;
        } else {
            return minLength;
        }
    }
	@Override
	public boolean add(E e) {
		return instance(getParentClass(), getChildClass(), getParent(), e, e) != null;
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
	public void run() {
		setCommand(Command.TRANSFER);
	}
	@Override
	public boolean isEmpty() {
		return getParent() == this;
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
	@Override
	public void clear() {
		getChild().getChild().setParent(getParent());
		getChild().getChild().getChild().setParent(getParent().getChild());
		getParent().getChild().setChild(getChild().getChild());
		getChild().setChild(getParent().getChild().getChild());
		setParent(getChild().getChild());
		getChild().setParent(getChild());
	}
	protected final class ParentIterator implements Iterator<E> {

		/**
		 * The current time-listener.
		 */
		Entry<E,E> current;

		/**
		 * The next time-listener.
		 */
		Entry<E,E> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public ParentIterator(Entry<E,E> key) {
			next = current = key;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			Entry<E,E> c = next;
			current = c;
			next = c.getParent();
			if (c == Hypercollection.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getKey();
		}
		@Override
		public void remove() {
			Entry<E,E> k = next;
			current.clear();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
			} else
				hasNext = false;
		 }
	}
	protected final class RecurrentIterator implements Iterator<E> {

		/**
		 * The current time-listener.
		 */
		Entry<E,E> current;

		/**
		 * The next time-listener.
		 */
		Entry<E,E> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public RecurrentIterator(Entry<E,E> key) {
			next = current = key;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			Entry<E,E> c = next;
			current = c;
			next = c.getParent();
			if (c == Hypercollection.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getKey();
		}
		@Override
		public void remove() {
			Entry<E,E> k = next;
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
		Entry<E,E> current;

		/**
		 * The next time-listener.
		 */
		Entry<E,E> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public ConcurrentIterator(Entry<E,E> key) {
			next = current = key;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			Entry<E,E> c = next;
			current = c;
			next = c.getChild().getChild();
			if (c == Hypercollection.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getKey();
		}
		@Override
		public void remove() {
			Entry<E,E> k = next;
			current.clear();
			if (!k.isEmpty()) {
				current = k;
				next = k.getChild().getChild();
			} else
				hasNext = false;
		 }
	}
}