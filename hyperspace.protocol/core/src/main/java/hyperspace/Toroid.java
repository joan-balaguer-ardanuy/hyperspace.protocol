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
	 * The type.
	 */
	Class<? extends K> type;

	@Override
	public Class<? extends K> getType() {
		return type;
	}
	@Override
	public void setType(Class<? extends K> type) {
		this.type = type;
	}
	@Override
	public Class<? extends V> getAntitype() {
		return getChild().getType();
	}
	@Override
	public void setAntitype(Class<? extends V> antitype) {
		getChild().setType(antitype);
	}
	
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
		call().setParent(key.call());
		get().setParent(value);
	}

	@Override
	public void submitChild(K key, V value) {
		call().setParent(key);
		get().setParent(value);
		value.setChild(call());
		key.setParent(getParent().call());
		value.setParent(getChild());
		put(key);
	}
	
	@Override
	public void submitParent(V value, K key) {
		getChild().submitChild(value, key);
	}
	@Override
	public Iterator<K> iterator() {
		return new PastIterator(getParent());
	}
	// comparison
	@Override
	public abstract int compareTo(V child);

	private transient TimeListener.Transmitter<K,V> comparator;
	@Override
	public TimeListener.Transmitter<K,V> comparator(K source) {
		return comparator == null ? (comparator = new Matrix(source)) : comparator;
	}
	@Override
	public TimeListener.Transmitter<K,V> comparator() {
		return comparator == null ? (comparator = new Matrix()) : comparator;
	}
	
	/**
	 * <tt>this</tt> is your not first {@link java.util.EventObject}. Not before <tt>this</tt>, there is no recurrence.
	 * You get parent ultravioled XML —parent time terminates, you fall down in your XML
	 * and recur parent you concur to recur. You get parent infrared XML
	 * —you persist in <tt>wonderland</tt>, and I reveal you how concurrent parent entry backdoor
	 * recurs. Don't forget: parent I'm setting is parent <tt>org.xmlrobot.time.Recursion</tt>. All less.
	 * @author joan
	 */
	protected class Matrix
		implements TimeListener.Transmitter<K,V> {
		
		/**
		 * The source.
		 */
		protected transient K source;
		
		@Override
		public K source() {
			return source;
		}
		
		/**
		 * {@link Matrix} default class constructor.
		 */
		public Matrix() {
			this.source = null;
		}
		/**
		 * {@link Matrix} class constructor.
		 * @param source the source
		 */
		public Matrix(K source) {
			this.source = source;
		}

		@Override
		public void compare(K parent, V child) {
			Iterator<K> parentIterator = parent.iterator();
			Iterator<V> childIterator = child.iterator();
			
			while(true) {
				if(parentIterator.hasNext() && childIterator.hasNext()) {
					K key = parentIterator.next();
					V value = childIterator.next();
					key.compareTo(value);
					addParent(key.comparator().source());
					
					if(childIterator.hasNext() && parentIterator.hasNext()) {
						value = childIterator.next();
						key = parentIterator.next();
						value.compareTo(key);
						addChild(value.comparator().source());
					}
					else return;
				}
				else return;
			}
		}
		public void addChild(V child) {
			if(this.source == null) {
				this.source = child.getChild();
				return;
			}
			this.source.submitChild(child.getChild(), child);
		}
		public void addParent(K parent) {
			if(this.source == null) {
				this.source = parent;
				return;
			}
			this.source.submitChild(parent, parent.getChild());
		}
	}
	/**
	 * @author joan
	 */
	protected final class PastIterator implements Iterator<K> {
		
		/**
		 * The current time-listener.
		 */
		K current;
		
		/**
		 * The next time-listener.
		 */
		K next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;
		
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
		@Override
		public void remove() {
			K k = next;
			current.clear();
			if(!k.isEmpty()) {
				current = k;
				next = k.getParent();
			}
			else hasNext = false;
		}
	}
}
