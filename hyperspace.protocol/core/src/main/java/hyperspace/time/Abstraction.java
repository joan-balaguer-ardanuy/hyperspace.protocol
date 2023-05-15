package hyperspace.time;

import hyperspace.Parity;

public abstract class Abstraction
	<K extends Recursion<K,V>,V extends Recursion<V,K>>
		extends Unification<K,V>
			implements Recursion<K,V> {

	/**
	 * 6736845275705891958L
	 */
	private static final long serialVersionUID = 6736845275705891958L;

	/**
	 * {@link Abstraction} default class constructor.
	 */
	public Abstraction() {
		super();
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Abstraction(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link XML} the message
	 */
	public Abstraction(Class<? extends V> childClass, Parity parity) {
		super(childClass, parity);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parent the parent
	 */
	public Abstraction(K parent) {
		super(parent);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Abstraction(Class<? extends V> childClass, K parent) {
		super(childClass, parent);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} parity
	 */
	public Abstraction(K root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param parity {@link Parity} parity
	 */
	public Abstraction(Class<? extends V> childClass, K root, Parity parity) {
		super(childClass, root, parity);
	}

	@Override
	public void recurChild(K key, V value) {
		key.setParent(getParent());
		value.setParent(getChild().getParent());
		value.setChild(getParent().call());
		getParent().put(key);
		setParent(key);
		getChild().setParent(value);
	}
	@Override
	public void recurParent(V value, K key) {
		getChild().recurChild(value, key);
	}
	@Override
	public void concurChild(K key, V value) {
		call().setParent(key);
		get().setParent(value);
		value.setChild(call());
		key.setParent(getParent().call());
		value.setParent(getChild());
		put(key);
	}
	@Override
	public void concurParent(V value, K key) {
		getChild().concurChild(value, key);
	}
	@Override
	public void permuteChild(K key, V value) {
		if(key == getParent()) {
			call().setParent(key);
			get().setParent(value);
			setParent(key.getParent());
			getChild().setParent(value.getParent());
			getParent().put(value.setChild(call()));
			put(key);
			key.setParent(getParent().call());
			value.setParent(getChild());
		}
		else if(key == getChild().getChild()) {
			key.setParent(getParent());
			value.setParent(getChild().getParent());
			put(value.getChild());
			call().setParent(getParent().put(key));
			get().setParent(getChild());
			value.setChild(getParent().call());
			setParent(key);
			getChild().setParent(value);
		}
		else {
			V childParent = value.setParent(getChild().getParent());
			K childChild = value.setChild(call());
			childParent.setChild(getParent().call());
			getParent().put(key);
			setParent(key.setParent(getParent()));
			getChild().setParent(childParent);
			call().setParent(key);
			get().setParent(value);
			put(childChild);
			call().setParent(getParent().call());
			get().setParent(getChild());
		}
	}
	@Override
	public void permuteParent(V value, K key) {
		getChild().permuteChild(value, key);
	}
	@Override
	public void submitChild(K key, V value) {
		if(random().nextBoolean()) {
			recurChild(key, value);
		} else {
			concurChild(key, value);
		}
	}
	@Override
	public void submitParent(V value, K key) {
		getChild().submitChild(value, key);
	}
}