package hyperspace.time;

import java.util.Objects;
import java.util.function.BiConsumer;

import hyperspace.Parity;
import hyperspace.recurrent.Enumerator;

public abstract class Time
	<K extends Recursion<K,V>,V extends Recursion<V,K>>
		extends Inheritance<K,V>
			implements Recursion<K,V> {

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
	 * @param parity {@link Parity} the parity
	 */
	public Time(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Time} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link Parity} the parity
	 */
	public Time(Class<? extends V> childClass, Parity parity) {
		super(childClass, parity);
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
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Time(Class<? extends V> childClass, K parent) {
		super(childClass, parent);
	}
	/**
	 * {@link Time} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Time(K root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link Time} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Time(Class<? extends V> childClass, K root, Parity parity) {
		super(childClass, root, parity);
	}
	@Override
	public void spin() {
		if(random().nextBoolean()) {
			// rotate
			getParent().permuteChild(call(), get());
		} else {
			// revolve  
			call().permuteChild(getParent(), getParent().getChild());
		}
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
	@Override
	public boolean releaseChild(K key, V value) {
		Object curValue = key.getChild();
		if (!Objects.equals(curValue, value) || (curValue == null && !hasParent(key))) {
			return false;
		}
		key.release();
		return true;
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public boolean releaseParent(V value, K key) {
		return getChild().releaseChild(value, key);
	}
	@Override
	public void forEachChild(BiConsumer<? super K, ? super V> action) {
		Objects.requireNonNull(action);
		Enumerator<K> en = enumerator();
		while(en.hasMoreElements())  {
			K parent = en.nextElement();
			action.accept(parent, parent.getChild());
		}
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public void forEachParent(BiConsumer<? super V, ? super K> action) {
		getChild().forEachChild(action);
	}
}