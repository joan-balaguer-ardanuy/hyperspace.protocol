/**
 * 
 */
package hyperspace.genesis;

import java.util.Collection;
import java.util.Iterator;

import hyperspace.Entry;
import hyperspace.Hyperspace;

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
	 * @param name {@link String} the name
	 */
	public Hyperchain(Class<? extends Hyperchain<K,V>> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param type {@link Class} the type
	 * @param antitype {@link Class} the antitype
	 * @param name {@link String} the name
	 */
	public Hyperchain(Class<? extends Hyperchain<K,V>> type, Class<? extends Hypercube<V,K>> antitype, String name) {
		super(type, antitype, name);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param parent the parent
	 * @param key the key
	 */
	public Hyperchain(Hyperchain<K,V> parent, K key) {
		super(parent, key);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param antitype {@link Class} the antitype
	 * @param parent the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> antitype, Hyperchain<K,V> parent, K key, V value) {
		super(antitype, parent, key, value);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param key the key
	 */
	public Hyperchain(Hyperchain<K,V> root, String name, K key) {
		super(root, name, key);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param antitype {@link Class} the antitype
	 * @param root the root
	 * @param name {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> antitype, Hyperchain<K,V> root, String name, K key, V value) {
		super(antitype, root, name, key, value);
	}
	
	/* (non-Javadoc)
	 * @see org.xmlrobot.genesis.Chain#dna()
	 */
	@Override
	public DNA<V,K> entryDNA() {
		return (DNA<V,K>) getChild();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<Entry<K,V>> iterator() {
		return super.iterator();
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean add(Entry<K, V> e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends Entry<K, V>> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		super.clear();
	}
}