package hyperspace;

import jakarta.xml.bind.annotation.XmlTransient;

public abstract class Child
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Parent<K,V>
			implements TimeListener<K,V> {

	/**
	 * -4078570118307293600L
	 */
	private static final long serialVersionUID = -4078570118307293600L;

	/**
	 * The key.
	 */
	private K parent;
	
	/**
	 * The value.
	 */
	private V child;
	
	@XmlTransient
	public K getParent() {
		return parent;
	}
	@Override
	public void setParent(K key) {
		this.parent = key;
	}
	@XmlTransient
	public V getChild() {
		return child;
	}
	@Override
	public void setChild(V value) {
		this.child = value;
	}

	/**
	 * {@link Child} class constructor.
	 */
	public Child() {
		super();
	}
	/**
	 * {@link Child} class constructor.
	 * @param xml {@link Message} the xml
	 */
	public Child(Message xml) {
		super(xml);
	}
	/**
	 * {@link Child} class constructor.
	 * @param xml {@link Message} the xml
	 * @param value the value
	 */
	public Child(Message xml, V value) {
		super(xml, value);
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