/**
 * 
 */
package hyperspace.genesis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import hyperspace.Entry;
import hyperspace.Hyperspace;
import hyperspace.XML;

/**
 * @author joan
 *
 */
public abstract class Hyperchain<K,V> 
	extends Hyperspace<K,V> 
		implements Chain<K,V> {

	/**
	 * 2606906200987294519L
	 */
	private static final long serialVersionUID = 2606906200987294519L;

	/**
	 * {@link Hyperchain} default class constructor.
	 */
	public Hyperchain() {
		super();
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param type {@link Class} the type
	 * @param message {@link String} the name
	 * @param key the key
	 */
	public Hyperchain(XML message) {
		super(message);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param message {@link String} the name
	 */
	public Hyperchain(Class<? extends Chain<K,V>> parentClass, Class<? extends DNA<V,K>> childClass, XML message) {
		super(parentClass, childClass, message);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param parent {@link Hyperchain} the parent
	 * @param key the key
	 */
	public Hyperchain(Chain<K,V> parent) {
		super(parent);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent {@link Hyperchain} the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends DNA<V,K>> childClass, Chain<K,V> parent, K key, V value) {
		super(childClass, parent, key, value);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param root {@link Hyperchain} the root
	 * @param message {@link String} the name
	 * @param key the key
	 */
	public Hyperchain(Chain<K,V> root, XML message) {
		super(root, message);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root {@link Hyperchain} the root
	 * @param message {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends DNA<V,K>> childClass, Chain<K,V> root, XML message, K key, V value) {
		super(childClass, root, message, key, value);
	}
	
	@Override
	public DNA<V,K> entryDNA() {
		return (DNA<V,K>) getChild();
	}
	@Override
	public int size() {
		int i = 0;
		try {
			Enumeration<Entry<K,V>> en;
			for(en = enumerator();en.hasMoreElements();en.nextElement())  {
				i++;
			}
		}
		catch(NoSuchElementException e) {
			return Integer.MAX_VALUE;
		}
		
		return i;
	}
	@Override
	public boolean contains(Object o) {
		Iterator<Entry<K,V>> it = iterator();
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
	public Iterator<Entry<K,V>> iterator() {
		return super.enumerator().asIterator();
	}
	public static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
	@Override
	public Object[] toArray() {
		// Estimate size of array; be prepared to see more or fewer elements
		Object[] r = new Object[size()];
		Iterator<Entry<K, V>> it = iterator();
		for (int i = 0; i < r.length; i++) {
			if (!it.hasNext()) // fewer elements than expected
				return Arrays.copyOf(r, i);
			r[i] = it.next();
		}
		return it.hasNext() ? finishToArray(r, it) : r;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <X> X[] toArray(X[] a) {
		// Estimate size of array; be prepared to see more or fewer elements
		int size = size();
		X[] r = a.length >= size ? a : (X[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		Iterator<Entry<K,V>> it = iterator();

		for (int i = 0; i < r.length; i++) {
			if (!it.hasNext()) { // fewer elements than expected
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
			r[i] = (X) it.next();
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
	public boolean add(Entry<K, V> e) {
		submitChild(e, e.getChild());
		return true;
	}
	@Override
	public boolean remove(Object o) {
		Iterator<Entry<K,V>> it = iterator();
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
	public boolean containsAll(Collection<?> c) {
		for (Object e : c)
			if (!contains(e))
				return false;
		return true;
	}
	@Override
	public boolean addAll(Collection<? extends Entry<K, V>> c) {
		boolean modified = false;
		for (Entry<K,V> e : c)
			if (add(e))
				modified = true;
		return modified;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<Entry<K,V>> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
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
}