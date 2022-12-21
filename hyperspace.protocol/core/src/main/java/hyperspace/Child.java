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
	 * {@link Child} class constructor.
	 */
	public Child() {
		super();
	}
	/**
	 * {@link Child} class constructor.
	 * @param message {@link XML} the message
	 */
	public Child(XML message) {
		super(message);
	}
	/**
	 * {@link Child} class constructor.
	 * @param message {@link XML} the message
	 * @param value the value
	 */
	public Child(XML message, V value) {
		super(message, value);
		value.setChild(getParent());
		value.setParent(value);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the key
	 */
	public Child(K key, XML message) {
		super(key, message);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the key
	 * @param message {@link XML} the message
	 * @param value the value
	 */
	@SuppressWarnings("unchecked")
	public Child(K key, XML message, V value) {
		super(key, message, value);
		key.getChild().setChild((K) this);
	}
}