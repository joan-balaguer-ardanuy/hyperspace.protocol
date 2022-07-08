package hyperspace.time;

import java.util.Enumeration;

public abstract class Time
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Inheritance<K,V>
			implements Recursive<K,V> {

	/**
	 * -1495539127840786666L
	 */
	private static final long serialVersionUID = -1495539127840786666L;
	/**
	 * {@link Time} default class constructor.<br/>
	 * 1. instance this and value;<br/>
	 * 2. put(this) and set(value);<br/>
	 * 3. value.set(this);<br/>
	 * 4. setParent(this) and setChild(value);<br/> 
	 */
	public Time() {
		super();
	}
	/**
	 * {@link Time} class constructor.
	 * @param name {@link String} the name
	 */
	public Time(String name) {
		super(name);
	}
	/**
	 * {@link Time} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Time(Class<? extends K> type, Class<? extends V> antitype,  String name) {
		super(type, antitype, name);
	}
	/**
	 * {@link Time} class constructor.
	 * @param parent the parent
	 */
	public Time(K parent) {
		super(parent);
	}
	/**
	 * {@link Time} class constructor.
	 * @param parent the key
	 * @param child the value
	 */
	public Time(Class<? extends V> antitype, K parent) {
		super(antitype, parent);
	}
	/**
	 * {@link Time} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 */
	public Time(K root, String name) {
		super(root, name);
	}
	/**
	 * {@link Time} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param child the value
	 */
	public Time(Class<? extends V> antitype, K root, String name) {
		super(antitype, root, name);
	}

	@Override
	public Enumeration<K> enumerator() {
		return new ParentEnumerator(getParent());
	}
	
	protected final class ParentEnumerator implements Enumeration<K> {
		
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
		
		public ParentEnumerator(K key) {
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
			if(k == Time.this)
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