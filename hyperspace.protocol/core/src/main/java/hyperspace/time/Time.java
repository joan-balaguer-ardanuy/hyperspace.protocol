package hyperspace.time;

import java.util.Enumeration;

import hyperspace.XML;

public abstract class Time
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Inheritance<K,V>
			implements Recursive<K,V> {

	/**
	 * -1495539127840786666L
	 */
	private static final long serialVersionUID = -1495539127840786666L;
	
	/**
	 * {@link Time} default class constructor.
	 */
	public Time() {
		super();
	}
	/**
	 * {@link Time} class constructor.
	 * @param message {@link XML} the message
	 */
	public Time(XML message) {
		super(message);
	}
	/**
	 * {@link Time} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param message {@link XML} the message
	 */
	public Time(Class<? extends K> parentClass, XML message, V child) {
		super(parentClass, message, child);
	}
	/**
	 * {@link Time} class constructor.
	 * @param parent the parent
	 */
	public Time(K parent, XML message) {
		super(parent, message);
	}
	/**
	 * {@link Time} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Time(K parent, XML message, V child) {
		super(parent, message, child);
	}
	/**
	 * {@link Time} class constructor.
	 * @param root the root
	 * @param stem the stem
	 */
	public Time(K root, V stem, XML message) {
		super(root, stem, message);
	}
	/**
	 * {@link Time} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param child the stem
	 */
	public Time(K root, V stem, XML message, V child) {
		super(root, stem, message, child);
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
			if(k == Time.this.getRoot())
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