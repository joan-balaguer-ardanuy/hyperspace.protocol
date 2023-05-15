/**
 * 
 */
package hyperspace.genesis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

import hyperspace.Entry;
import hyperspace.Parity;
import hyperspace.ScrewDriver;
import hyperspace.recurrent.Enumerator;

/**
 * @author joan
 *
 */
public abstract class ScrewNut<K,V> 
	extends ScrewDriver<K,V> 
		implements Chain<K,V> {

	/**
	 * 2606906200987294519L
	 */
	private static final long serialVersionUID = 2606906200987294519L;

	public static final int MAX_ARRAY_SIZE = 2^31-1;
	
	/**
	 * {@link ScrewNut} default class constructor.
	 */
	public ScrewNut() {
		super();
	}
	/**
	 * {@link ScrewNut} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public ScrewNut(Parity parity) {
		super(parity);
	}
	/**
	 * {@link ScrewNut} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link Parity} the parity
	 */
	public ScrewNut(Class<? extends Screw<V,K>> childClass, Parity parity) {
		super(childClass, parity);
	}
	/**
	 * {@link ScrewNut} class constructor.
	 * @param parent the parent
	 */
	public ScrewNut(ScrewNut<K,V> parent) {
		super(parent);
	}
	/**
	 * {@link ScrewNut} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 * @param key the key
	 * @param value the value
	 */
	public ScrewNut(Class<? extends Screw<V,K>> childClass, ScrewNut<K,V> parent, K key, V value) {
		super(childClass, parent, key, value);
	}
	/**
	 * {@link ScrewNut} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public ScrewNut(ScrewNut<K,V> root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link ScrewNut} class constructor.
	 * @param childClass  {@link Class} the child class
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 * @param key the key
	 * @param value the value
	 */
	public ScrewNut(Class<? extends Screw<V,K>> childClass, ScrewNut<K,V> root, Parity parity, K key, V value) {
		super(childClass, root, parity, key, value);
	}

	@Override
	public DNA<V,K> entryDNA() {
		return (DNA<V,K>) getChild();
	}

	public void clear() {
		release();
	}
	@Override
	public boolean contains(Object o) {
		Iterator<Entry<K,V>> en = iterator();
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
	public boolean add(Entry<K, V> e) {
		return putChild(e, e.getChild()) != null;
	}
	@Override
	public boolean remove(Object o) {
		Iterator<Entry<K,V>> en = iterator();
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
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}
	@Override
	public boolean addAll(Collection<? extends Entry<K, V>> c) {
		boolean modified = false;
		Iterator<Entry<K,V>> en = iterator();
		while(en.hasNext())
			if (add(en.next()))
				modified = true;
		return modified;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<Entry<K,V>> en = iterator();
		while (en.hasNext()) {
			if (!c.contains(en.next())) {
				en.remove();
				modified = true;
			}
		}
		return modified;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<Entry<K,V>> it = iterator();
		while (it.hasNext()) {
			if (c.contains(it.next())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}
	public int size() {
		int i = 0;
		Iterator<?> it = iterator();
		while(it.hasNext()) {
			it.next();
			i++;
		}
		return i;
	}

	public Iterator<Entry<K,V>> iterator() {
		Enumerator<Entry<K,V>> en = enumerator();
		return new Iterator<Entry<K,V>>() {

			@Override
			public boolean hasNext() {
				return en.hasMoreElements();
			}
			@Override
			public Entry<K, V> next() {
				return en.nextElement();
			}
			@Override
			public void remove() {
				en.remove();
			}
		};
	}

	public Object[] toArray() {
		// Estimate size of array; be prepared to see more or fewer elements
        Object[] r = new Object[size()];
        Iterator<Entry<K,V>> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) // fewer elements than expected
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		 // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        T[] r = a.length >= size ? a :
                  (T[])java.lang.reflect.Array
                  .newInstance(a.getClass().getComponentType(), size);
        Iterator<Entry<K,V>> it = iterator();

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