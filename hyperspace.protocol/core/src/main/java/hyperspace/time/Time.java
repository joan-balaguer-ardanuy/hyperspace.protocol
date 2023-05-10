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
	public Time(Class<? extends V> parentClass, Parity parity) {
		super(parentClass, parity);
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
	public boolean releaseChild(K key, V value) {
		Object curValue = key.getChild();
		if (!Objects.equals(curValue, value) || (curValue == null && !hasParent(key))) {
			return false;
		}
		key.release();
		return true;
	}
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
	@Override
	public void forEachParent(BiConsumer<? super V, ? super K> action) {
		getChild().forEachChild(action);
	}
}