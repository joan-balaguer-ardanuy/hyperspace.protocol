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
	 * {@link Child} default class constructor.
	 */
	public Child() {
		super();
	}
	/**
	 * {@link Child} class constructor.
	 * @param mmessage {@link String} the name
	 */
	public Child(XML mmessage) {
		super(mmessage);
	}
	/**
	 * {@link Child} class constructor.
	 * @param message {@link String} the name
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
	public Child(K key) {
		super(key);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	@SuppressWarnings("unchecked")
	public Child(K key, V value) {
		super(key, value);
		key.getChild().setChild((K) this);
	}
}