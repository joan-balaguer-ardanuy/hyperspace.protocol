/**
 * 
 */
package hyperspace;

public abstract class Toroid
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Child<K,V>
			implements TimeListener<K,V> {

	/**
	 * 4428935997216883052L
	 */
	private static final long serialVersionUID = 4428935997216883052L;

	/**
	 * {@link Toroid} class constructor.
	 */
	public Toroid() {
		super();
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param message {@link XML} the message
	 */
	public Toroid(XML2<?,?> message) {
		super(message);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param message {@link XML} the message
	 * @param value the value
	 */
	public Toroid(XML2<?,?> message, V value) {
		super(message, value);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the key
	 */
	public Toroid(K key, XML2<?,?> message) {
		super(key, message);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	public Toroid(K key, XML2<?,?> message, V value) {
		super(key, message, value);
		getChild().getChild().setParent(key.getChild().getChild());
		getChild().getChild().getChild().setParent(value);
	}
}