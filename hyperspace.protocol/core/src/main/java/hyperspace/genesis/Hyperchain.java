/**
 * 
 */
package hyperspace.genesis;

import java.util.Iterator;

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
	public Iterator<Entry<K, V>> iterator() {
		return new RecurrentIterator(getParent());
	}

	protected final class RecurrentIterator implements Iterator<Entry<K,V>> {
		
		/**
		 * The current time-listener.
		 */
		protected Entry<K,V> current;
		
		/**
		 * The next time-listener.
		 */
		protected Entry<K,V> next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		protected boolean hasNext;
		
		public RecurrentIterator(Entry<K,V> key) {
			next = current = key;
			hasNext = true;
		}
		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public Entry<K,V> next() {
			Entry<K,V> k = next;
			current = k;
			next = k.getParent();
			if(k == Hyperchain.this)
				hasNext = false;
			else hasNext = true;
			return k;
		}
		@Override
		public void remove() {
			Entry<K,V> k = next;
			current.clear();
			if(!k.isEmpty()) {
				current = k;
				next = k.getParent();
			}
			else hasNext = false;
		}
	}
}