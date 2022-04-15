package hyperspace;

import java.util.Random;

/**
 * The parent {@link XML} class.
 * It is {@link TimeListener}.
 * @author joan
 *
 */
public abstract class Parent
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends XML
			implements TimeListener<K,V> {

	/**
	 * -2358327935955938422L
	 */
	private static final long serialVersionUID = -2358327935955938422L;

	/**
	 * The key.
	 */
	private K parent;
	
	/**
	 * The value.
	 */
	private V child;
	
	public K getParent() {
		return parent;
	}
	@Override
	public K setParent(K key) {
		K old = this.parent;
		this.parent = key;
		return old;
	}
	public V getChild() {
		return child;
	}
	@Override
	public V setChild(V value) {
		V old = this.child;
		this.child = value;
		return old;
	}
	
	/**
	 * {@link Parent} default class constructor.
	 */
	public Parent() {
		super();
	}
	/**
	 * {@link Parent} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Parent(Class<? extends K> type, String name) {
		super(name);
		setType(type);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param value the child
	 */
	public Parent(Class<? extends K> type, String name, V value) {
		this(type, name);
		setParent(type.cast(this));
		setChild(value);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the parent
	 */
	public Parent(K key) {
		this(key.getType(), key.getName());
		setParent(key);
		setChild(key.getChild());
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the parent
	 * @param value the child
	 */
	public Parent(K key, V value) {
		this(key.getType(), key.getName());
		setParent(key);
		setChild(value);
	}

	@Override
	public K clone() {
		try {
			K k = getType().getConstructor().newInstance();
			V v = getAntitype().getConstructor().newInstance();
			k.setName(getName());
			k.setParent(k);
			v.setParent(v);
			k.setChild(v);
			v.setChild(k);
			return k;
		}
		catch (Throwable t) {
			throw new Error("hyperspace.Parent: clone exception.", t);
		}
	}
	@Override
	public boolean isEmpty() {
		return getParent() == this;
	}
	@Override
	public void clear() {
		K current = getParent().put(call());
		call().setParent(getParent());
		get().setParent(getParent().getChild());
		setParent(current);
		getChild().setParent(getChild());
		put(current);
	}

	/**
	 * The randomness.
	 */
	transient Random random;
	
	/**
	 * The random.
	 * @return the random.
	 */
	protected Random random() {
		return random == null ? (random = new Random()) : random;
	}
}
