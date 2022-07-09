/**
 * 
 */
package hyperspace.genesis;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
	 * @param key the key
	 */
	public Hyperchain(String name) {
		super(name);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param name {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hyperchain<K,V>> parentClass, Class<? extends Hypercube<V,K>> childClass, String name, K key, V value) {
		super(parentClass, childClass, name, key, value);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param parent {@link Hyperchain} the parent
	 * @param key the key
	 */
	public Hyperchain(Hyperchain<K,V> parent) {
		super(parent);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent {@link Hyperchain} the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> childClass, Hyperchain<K,V> parent, K key, V value) {
		super(childClass, parent, key, value);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param root {@link Hyperchain} the root
	 * @param name {@link String} the name
	 * @param key the key
	 */
	public Hyperchain(Hyperchain<K,V> root, String name) {
		super(root, name);
	}
	/**
	 * {@link Hyperchain} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root {@link Hyperchain} the root
	 * @param name {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hyperchain(Class<? extends Hypercube<V,K>> childClass, Hyperchain<K,V> root, String name, K key, V value) {
		super(childClass, root, name, key, value);
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
		return super.enumerator().asIterator();
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