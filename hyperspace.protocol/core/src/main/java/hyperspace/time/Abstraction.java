package hyperspace.time;

import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


public abstract class Abstraction
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Order<K,V>
			implements Recursive<K,V> {

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
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Abstraction(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Abstraction(Class<? extends K> type, String name, V child) {
		super(type, name, child);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parent parent parent
	 */
	public Abstraction(K parent) {
		super(parent);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	public Abstraction(K parent, V child) {
		super(parent, child);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param root the root
	 * @param {@link String} the name
	 */
	public Abstraction(K root, String name) {
		super(root.getType(), name);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Abstraction(K root, String name, V child) {
		super(root, name, child);
	}

	@Override
	public void recurChild(K key, V value) {
		key.setParent(getParent());
		value.setParent(getChild().getParent());
		value.setChild(getParent().getChild().getChild());
		getParent().getChild().setChild(key);
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
			K current = value.setChild(getChild().getChild());
			call().setParent(key);
			get().setParent(value);
			setParent(key.getParent());
			getChild().setParent(value.getParent());
			getParent().put(current);
			put(key);
			key.setParent(current);
			value.setParent(getChild());
		}
		else if(key == getChild().getChild()) {
			K current = key.setParent(getParent());
			value.setParent(getChild().getParent());
			getParent().put(key);
			put(value.getChild());
			call().setParent(current);
			get().setParent(getChild());
			value.setChild(current);
			setParent(key);
			getChild().setParent(value);
		}
		else {
			K oldParent = key.setParent(getParent());
			V oldParentChild = value.setParent(getChild().getParent());
			K oldChild = value.setChild(call());
			oldParentChild.setChild(getParent().call());
			getParent().put(key);
			setParent(oldParent);
			getChild().setParent(oldParentChild);
			call().setParent(key);
			get().setParent(value);
			put(oldChild);
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
			concurChild(key, value);
		} else {
			recurChild(key, value);
		}
	}
	@Override
	public void submitParent(V value, K key) {
		getChild().submitChild(value, key);
	}
}