/**
 * 
 */
package hyperspace.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

import hyperspace.Entry;
import hyperspace.Hyperspace;
import hyperspace.XML;

/**
 * @author joan
 *
 */
public class Hyperchain<K,V> 
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
	public Hyperchain(Class<? extends Hyperchain<K,V>> parentClass, Class<? extends Hypercube<V,K>> childClass, XML message) {
		super(parentClass, childClass, message);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param parent {@link Hyperchain} the parent
	 * @param key the key
	 */
	public Hyperchain(Hyperchain<K,V> parent, XML message) {
		super(parent, message);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent {@link Hyperchain} the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> childClass, Hyperchain<K,V> parent, XML message, K key, V value) {
		super(childClass, parent, message, key, value);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param root {@link Hyperchain} the root
	 * @param message {@link String} the name
	 * @param key the key
	 */
	public Hyperchain(Hyperchain<K,V> root, Hypercube<V,K> stem, XML message) {
		super(root, stem, message);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root {@link Hyperchain} the root
	 * @param message {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> childClass, Hyperchain<K,V> root, Hypercube<V,K> stem, XML message, K key, V value) {
		super(childClass, root, stem, message, key, value);
	}
	
	@Override
	public DNA<V,K> entryDNA() {
		return (DNA<V,K>) getChild();
	}
	@Override
	@Deprecated
	public int size() {
		return 0;
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