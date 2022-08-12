/**
 * 
 */
package hyperspace.genesis;

import java.util.Enumeration;
import java.util.Iterator;

import hyperspace.Entry;
import hyperspace.Hyperspace;
import hyperspace.XML;

/**
 * @author joan
 *
 */
public abstract class Hypercube<K,V> 
	extends Hyperspace<K,V>
		implements DNA<K,V> {

	/**
	 * -1289997593164869118L
	 */
	private static final long serialVersionUID = -1289997593164869118L;

	/**
	 * {@link Hypercube} default class constructor.
	 */
	public Hypercube() {
		super();
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param message {@link String} the name
	 */
	public Hypercube(XML message) {
		super(message);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param message {@link String} the name
	 */
	public Hypercube(Class<? extends Hypercube<K,V>> parentClass, Class<? extends Chain<V,K>> childClass, XML message) {
		super(parentClass, childClass, message);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param parent {@link Hypercube} the parent
	 */
	public Hypercube(Hypercube<K,V> parent, XML message) {
		super(parent, message);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent {@link Hypercube} the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> childClass, Hypercube<K,V> parent, XML message, K key, V value) {
		super(childClass, parent, message, key, value);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param root {@link Hypercube} the root
	 * @param message {@link String} the name
	 * @param key the key
	 */
	public Hypercube(Hypercube<K,V> root, Hyperchain<V,K> stem, XML message) {
		super(root, stem, message);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root {@link Hypercube} the root
	 * @param message {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> childClass, Hypercube<K,V> root, Hyperchain<V,K> stem, XML message, K key, V value) {
		super(childClass, root, stem, message, key, value);
	}

	@SuppressWarnings("unchecked")
	public V get(Object key) {
    	return getValue((K) key);
    }
    public V put(K key, V value) {
    	return putValue(key, value);
    }
    public void putAll(Entry<K,V> m) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			put(entry.getKey(), entry.getValue());
		}
    }
	@Override
	public V remove(K key) {
		Enumeration<Entry<K,V>> en = enumerator();
		Entry<K,V> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && en.hasMoreElements()) {
            	Entry<K,V> e = en.nextElement();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && en.hasMoreElements()) {
            	Entry<K,V> e = en.nextElement();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }

        V oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            correctEntry.clear();
        }
        return oldValue;
	}
	@Override
	public Iterator<K> iterator() {
		Enumeration<Entry<K,V>> en = enumerator();
		return new Iterator<K>() {

			@Override
			public boolean hasNext() {
				return en.hasMoreElements();
			}

			@Override
			public K next() {
				return en.nextElement().getKey();
			}
		};
	}
}