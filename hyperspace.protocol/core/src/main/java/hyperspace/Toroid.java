/**
 * 
 */
package hyperspace;

import java.util.Iterator;

public abstract class Toroid
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Child<K,V>
		 implements TimeListener<K,V> {

	/**
	 * 4428935997216883052L
	 */
	private static final long serialVersionUID = 4428935997216883052L;
	
	/**
	 * {@link Toroid} default class constructor.
	 */
	public Toroid() {
		super();
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Toroid(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param value the Time
	 */
	public Toroid(Class<? extends K> type, String name, V value) {
		super(type, name, value);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the Time
	 */
	public Toroid(K key) {
		super(key);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the Time
	 * @param value the Time
	 */
	public Toroid(K key, V value) {
		super(key, value);
		getChild().getChild().setParent(key.getChild().getChild());
		getChild().getChild().getChild().setParent(value);
	}
	
	@Override
	public Iterator<K> iterator() {
		return new PastIterator(getParent());
	}
	
	/**
	 * @author joan
	 */
	protected class PastIterator implements Iterator<K> {
		
		/**
		 * The current time-listener.
		 */
		protected K current;
		
		/**
		 * The next time-listener.
		 */
		protected K next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		protected boolean hasNext;
		
		public PastIterator(K key) {
			next = current = key;
			hasNext = true;
		}
		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public K next() {
			K k = next;
			current = k;
			next = k.getParent();
			if(k == Toroid.this)
				hasNext = false;
			else hasNext = true;
			return k;
		}
	}
}
