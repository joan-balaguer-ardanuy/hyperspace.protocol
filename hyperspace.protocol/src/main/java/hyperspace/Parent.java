package hyperspace;

import jakarta.xml.bind.annotation.XmlTransient;

/**
 * The parent {@link AbstractListener} class.
 * It is {@link TimeListener}.
 * @author joan
 *
 */
public abstract class Parent
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends AbstractListener
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

	public Parent() {
		super();
	}
	/**
	 * {@link Parent} class constructor.
	 * @param xml {@link XML} the message
	 */
	public Parent(XML message) {
		super(message);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param message {@link XML} the message
	 * @param value the value
	 */
	@SuppressWarnings("unchecked")
	public Parent(XML message, V value) {
		super(message);
		setParent((K) this);
		setChild(value);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the key
	 * @param message {@link XML} the message
	 */
	public Parent(K key, XML message) {
		super(message);
		setParent(key);
		setChild(key.getChild());
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	public Parent(K key, XML message, V value) {
		super(message);
		setParent(key);
		setChild(value);
	}

	@Override
	public abstract TimeListener<K,V> clone();
}