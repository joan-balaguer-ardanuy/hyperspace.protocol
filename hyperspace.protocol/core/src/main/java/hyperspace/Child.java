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
	 * @param message {@link Message} the xml
	 */
	public Child(XML2<?,?> message) {
		super(message);
	}
	/**
	 * {@link Child} class constructor.
	 * @param message {@link Message} the xml
	 * @param value the value
	 */
	public Child(XML2<?,?> message, V value) {
		super(message, value);
		value.setChild(getParent());
		value.setParent(value);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the key
	 */
	public Child(K key, XML2<?,?> message) {
		super(key, message);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	@SuppressWarnings("unchecked")
	public Child(K key, XML2<?,?> message, V value) {
		super(key, message, value);
		key.getChild().setChild((K) this);
	}
}