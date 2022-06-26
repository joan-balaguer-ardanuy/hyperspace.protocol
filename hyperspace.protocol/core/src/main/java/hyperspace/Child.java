package hyperspace;

public abstract class Child
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Parent<K,V>
			implements TimeListener<K,V> {

	/**
	 * -4078570118307293600L
	 */
	private static final long serialVersionUID = -4078570118307293600L;

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
	 * {@link Child} default class constructor.
	 */
	public Child() {
		super();
	}
	/**
	 * {@link Child} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Child(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Child} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param value the child
	 */
	public Child(Class<? extends K> type, String name, V value) {
		super(type, name, value);
		value.setChild(getParent());
		value.setParent(value);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the parent
	 */
	public Child(K key) {
		super(key);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the parent
	 * @param value the child
	 */
	public Child(K key, V value) {
		super(key, value);
		key.getChild().setChild(getType().cast(this));
	}
	
	// comparison
	@Override
	public abstract int compareTo(V child);
}