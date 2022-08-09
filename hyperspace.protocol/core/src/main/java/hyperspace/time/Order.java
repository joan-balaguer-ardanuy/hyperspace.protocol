package hyperspace.time;

import java.util.Enumeration;

import hyperspace.XML;

public abstract class Order
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Inheritance<K,V>
			implements Recursive<K,V> {

	/**
	 * -1495539127840786666L
	 */
	private static final long serialVersionUID = -1495539127840786666L;
	/**
	 * {@link Order} default class constructor.<br/>
	 * 1. instance this and value;<br/>
	 * 2. put(this) and set(value);<br/>
	 * 3. value.set(this);<br/>
	 * 4. setParent(this) and setChild(value);<br/> 
	 */
	public Order() {
		super();
	}
	/**
	 * {@link Order} class constructor.
	 * @param message {@link String} the name
	 */
	public Order(XML message) {
		super(message);
	}
	/**
	 * {@link Order} class constructor.
	 * @param parentClass {@link Class} the type
	 * @param childClass {@link Class} the child class
	 * @param message {@link String} the name
	 */
	public Order(Class<? extends K> parentClass, Class<? extends V> childClass,  XML message) {
		super(parentClass, childClass, message);
	}
	/**
	 * {@link Order} class constructor.
	 * @param parent the parent
	 */
	public Order(K parent) {
		super(parent);
	}
	/**
	 * {@link Order} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Order(Class<? extends V> childClass, K parent) {
		super(childClass, parent);
	}
	/**
	 * {@link Order} class constructor.
	 * @param root the root
	 * @param message {@link String} the name
	 */
	public Order(K root, V stem) {
		super(root, stem);
	}
	/**
	 * {@link Order} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param message {@link String} the name
	 */
	public Order(Class<? extends V> childClass, K root, V stem) {
		super(childClass, root, stem);
	}

	@Override
	public Enumeration<K> enumerator() {
		return new RecurrentEnumerator(getParent());
	}
	
	protected final class RecurrentEnumerator implements Enumeration<K> {
		
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
		
		public RecurrentEnumerator(K key) {
			next = current = key;
			hasNext = true;
		}
		@Override
		public boolean hasMoreElements() {
			return hasNext;
		}
		@Override
		public K nextElement() {
			K k = next;
			current = k;
			next = k.getParent();
			if(k == Order.this)
				hasNext = false;
			else hasNext = true;
			return k;
		}
//		@Override
//		public void remove() {
//			K k = next;
//			current.clear();
//			if(!k.isEmpty()) {
//				current = k;
//				next = k.getParent();
//			}
//			else hasNext = false;
//		}
	}
}