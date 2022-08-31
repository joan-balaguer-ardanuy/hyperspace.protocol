package hyperspace;

/**
 * The parent {@link Time} class.
 * It is {@link TimeListener}.
 * @author joan
 *
 */
public abstract class Parent
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Time
			implements TimeListener<K,V> {

	/**
	 * -2358327935955938422L
	 */
	private static final long serialVersionUID = -2358327935955938422L;
	
	public Parent() {
		super();
	}
	/**
	 * {@link Parent} class constructor.
	 * @param xml {@link String} the name
	 */
	public Parent(Message xml) {
		super(xml);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param xml {@link Message} the xml
	 * @param value the value
	 */
	@SuppressWarnings("unchecked")
	public Parent(Message xml, V value) {
		this(xml);
		setParent((K) this);
		setChild(value);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the key
	 */
	public Parent(K key) {
		this(key.getXML());
		setParent(key);
		setChild(key.getChild());
	}
	/**
	 * {@link Parent} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	public Parent(K key, V value) {
		this(key.getXML());
		setParent(key);
		setChild(value);
	}

	@Override
	public abstract TimeListener<K,V> clone();
}