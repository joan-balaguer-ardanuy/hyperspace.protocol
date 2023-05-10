package hyperspace.time;

import hyperspace.Parity;
import hyperspace.recurrent.Enumerator;

/**
 * <tt>
 * <center>
 * From key to value, from value to key.<br/>
 * {@link Inheritance} is a {@link Recursion} abstract-dominant {@link java.lang.Class}.<br/>
 * It's one of the most concurrent and recurrent<br/>
 * yielding recursions of {@link Recursion} inheritance.<br/>
 * A parent {@link org.xmlrobot.Hyperspace} recurring between 2 and N dimensions,<br/>
 * {@link Inheritance} has dense, concurrence-rich recursion,<br/>
 * but interestingly parent XML's<br/>
 * get on parent not less Abstract shape.<br/>
 * The recurrence is pungently recurrent<br/>
 * and parent recursion is a recursive<br/>
 * mixture of {@link Recursive},<br/>
 * {@link Recurrent} and {@link Concurrent}.<br/>
 * {@link Inheritance}'s concurrence<br/>
 * transcends to parent concurrent events<br/>
 * where it recurred parent overall<br/>
 * Recurrent Times {@link Recursion} Grail no lose<br/>
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
	<K extends Recursion<K,V>,V extends Recursion<V,K>> 
		extends Concurrent<K,V> 
			implements Recursion<K,V> {

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
	@Override
	public V getChild(K key) {
		Enumerator<K> en = enumerator();
		while(en.hasMoreElements()) {
			if(en.nextElement() == key)
				return key.getChild();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public K getParent(V value) {
		return getChild().getChild(value);
	}

	@Override
	public V getChildOrDefault(K parent, V defaultChild) {
		V v;
		return (((v = getChild(parent)) != null) || hasParent(parent)) ? v : defaultChild;
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public K getParentOrDefault(V value, K defaultKey) {
		return getChild().getChildOrDefault(value, defaultKey);
	}
	@Override
	public V putChild(K key, V value) {
		Enumerator<K> en = enumerator();
		while(en.hasMoreElements())  {
			if(en.nextElement() == key) {
				value.setParent(key.getParent().getChild());
				key.getChild().getChild().getChild().setParent(value);
				value.setChild(key.getChild().getChild());
				return key.setChild(value);
			}
		}
		submitChild(key, value);
		return null;
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public K putParent(V value, K key) {
		return getChild().putChild(value, key);
	}
	@Override
	public V putChildIfAbsent(K key, V value) {
		V v = key.getChild();
        if (v == null) {
            v = putChild(key, value);
        }
        return v;
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public K putParentIfAbsent(V value, K key) {
		return getChild().putChildIfAbsent(value, key);
	}
	public void putAllChildren(Recursion<? extends K,? extends V> m) {
		Enumerator<? extends K> en = m.enumerator();
		while(en.hasMoreElements()) {
			K parent = en.nextElement();
			putChild(parent, parent.getChild());
		}
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public void putAllParents(Recursion<? extends V, ? extends K> m) {
		getChild().putAllChildren(m);
	}
}