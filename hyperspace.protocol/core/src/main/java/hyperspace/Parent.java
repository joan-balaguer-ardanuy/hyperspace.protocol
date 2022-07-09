package hyperspace;

/**
 * The parent {@link Program} class.
 * It is {@link TimeListener}.
 * @author joan
 *
 */
public abstract class Parent
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Program
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
	 * @param name {@link String} the name
	 */
	public Parent(String name) {
		super(name);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param name {@link String} the name
	 * @param value the value
	 */
	@SuppressWarnings("unchecked")
	public Parent(String name, V value) {
		this(name);
		setParent((K) this);
		setChild(value);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the key
	 */
	public Parent(K key) {
		this(key.getName());
		setParent(key);
		setChild(key.getChild());
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	public Parent(K key, V value) {
		this(key.getName());
		setParent(key);
		setChild(value);
	}

	@Override
	public abstract TimeListener<K,V> clone();
}