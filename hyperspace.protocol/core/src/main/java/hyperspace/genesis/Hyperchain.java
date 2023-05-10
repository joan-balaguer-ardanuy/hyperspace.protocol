/**
 * 
 */
package hyperspace.genesis;

import hyperspace.Entry;
import hyperspace.Parity;
import hyperspace.XML2;

/**
 * @author joan
 *
 */
public abstract class Hyperchain<K,V> 
	extends XML2<K,V> 
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
	 * @param parity {@link Parity} the parity
	 */
	public Hyperchain(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link Parity} the parity
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> childClass, Parity parity, K key, V value) {
		super(childClass, parity, key, value);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param parent the parent
	 */
	public Hyperchain(Hyperchain<K,V> parent) {
		super(parent);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> childClass, Hyperchain<K,V> parent, K key, V value) {
		super(childClass, parent, key, value);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Hyperchain(Hyperchain<K,V> root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param stem the stem
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> childClass, Hyperchain<K,V> root, Parity parity, K key, V value) {
		super(childClass, root, key, value);
	}

	@Override
	public DNA<V,K> entryDNA() {
		return (DNA<V,K>) getChild();
	}
	@Override
	public boolean contains(Entry<K, V> o) {
		return hasParent(o);
	}
	@Override
	public boolean add(Entry<K, V> e) {
		return putChild(e, e.getChild()) != null;
	}
	@Override
	public boolean remove(Entry<K, V> o) {
		return releaseChild(o, o.getChild());
	}
	@Override
	public boolean containsAll(Entry<K, V> c) {
		return hasParent(c);
	}
	@Override
	public boolean addAll(Entry<K, V> c) {
		return addAllParents(c);
	}
	@Override
	public boolean retainAll(Entry<K, V> c) {
		return retainAllParents(c);
	}
	@Override
	public boolean removeAll(Entry<K, V> c) {
		return releaseAllParents(c);
	}
}