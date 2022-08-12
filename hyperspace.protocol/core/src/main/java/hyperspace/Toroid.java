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
	 * {@link Toroid} default class constructor.
	 */
	public Toroid() {
		super();
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param message {@link String} the name
	 */
	public Toroid(XML message) {
		super(message);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param message {@link String} the name
	 * @param value the value
	 */
	public Toroid(XML message, V value) {
		super(message, value);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the key
	 */
	public Toroid(K key, XML message) {
		super(key, message);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	public Toroid(K key, XML message, V value) {
		super(key, message, value);
		getChild().getChild().setParent(key.getChild().getChild());
		getChild().getChild().getChild().setParent(value);
	}
}