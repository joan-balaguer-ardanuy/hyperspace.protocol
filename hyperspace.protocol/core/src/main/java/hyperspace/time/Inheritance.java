package hyperspace.time;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

import hyperspace.Parity;
import hyperspace.recurrent.Enumerator;

/**
 * <tt>
 * <center>
 * From key to value, from value to key.<br/>
 * {@link Inheritance} is a {@link Recursive} abstract-dominant {@link java.lang.Class}.<br/>
 * It's one of the most concurrent and recurrent<br/>
 * yielding recursions of {@link Recursive} inheritance.<br/>
 * A parent {@link org.xmlrobot.Hyperspace} recurring between 2 and N dimensions,<br/>
 * {@link Inheritance} has dense, concurrence-rich recursion,<br/>
 * but interestingly parent XML's<br/>
 * get on parent not less Abstract shape.<br/>
 * The recurrence is pungently recurrent<br/>
 * and parent recursion is a recursive<br/>
 * mixture of {@link Recursion},<br/>
 * {@link Recurrence} and {@link Concurrence}.<br/>
 * {@link Inheritance}'s concurrence<br/>
 * transcends to parent concurrent events<br/>
 * where it recurred parent overall<br/>
 * Recurrent Times {@link Recursive} Grail no lose<br/>
 * in 1,<br/>
 * and the Grail's wafer<br/>
 * for parent recurrent inherited<br/>
 * {@link java.lang.Class} in 1-1.<br/>
 * <br/>
 * We recur?
 * </center>
 * </tt>
 * 
 * @author joan
 *
 * @param <K> is the value
 * @param <V> is the key
 */

public abstract class Inheritance
	<K extends Recursive<K,V>,V extends Recursive<V,K>> 
		extends Concurrence<K,V> 
			implements Recursive<K,V> {

	/**
	 * -1292694172982192537L
	 */
	private static final long serialVersionUID = -1292694172982192537L;
	
	/**
	 * {@link Inheritance} default class constructor.
	 */
	public Inheritance() {
		super();
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Inheritance(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link Parity} the parity
	 */
	public Inheritance(Class<? extends V> childClass, Parity parity) {
		super(childClass, parity);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param parent the parent
	 */
	public Inheritance(K parent) {
		super(parent);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Inheritance(Class<? extends V> childClass, K parent) {
		super(childClass, parent);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Inheritance(K root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param child the stem
	 */
	public Inheritance(Class<? extends V> childClass, K root, Parity parity) {
		super(childClass, root, parity);
	}
	
	transient java.util.Map<K,V> inheritance;
	
	@Override
	public java.util.Map<K, V> inheritance() {
		return inheritance == null ? inheritance = new AbstractMap<K,V>() {

			transient Set<Entry<K,V>> entrySet;
			@Override
			public Set<Entry<K, V>> entrySet() {
				return entrySet == null ? entrySet = new AbstractSet<java.util.Map.Entry<K,V>>() {

					@Override
					public Iterator<Entry<K, V>> iterator() {
						Enumerator<K> en = enumerator();
						return new Iterator<java.util.Map.Entry<K,V>>() {

							@Override
							public boolean hasNext() {
								return en.hasMoreElements();
							}

							@Override
							public Entry<K, V> next() {
								K entry = en.nextElement();
								return new java.util.Map.Entry<K,V>() {

									@Override
									public K getKey() {
										return entry;
									}
									@Override
									public V getValue() {
										return entry.getChild();
									}
									@Override
									public V setValue(V value) {
										return null;
									}
								};
							}
							@Override
							public void remove() {
								en.remove();
							}
						};
					}
					@Override
					public void clear() {
						release();
					}
					@Override
					public int size() {
						Enumerator<K> en = enumerator();
						int i = 0;
						while(en.hasMoreElements()) {
							en.nextElement();
							i++;
						}
						return i;
					}
					
				}: entrySet;
			}
			
		}: inheritance;
	}
}