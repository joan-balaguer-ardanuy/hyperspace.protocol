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
	 * @param xml {@link Message} the xml
	 */
	public Toroid(Message xml) {
		super(xml);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param xml {@link Message} the xml
	 * @param value the value
	 */
	public Toroid(Message xml, V value) {
		super(xml, value);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the key
	 */
	public Toroid(K key) {
		super(key);
	}
	/**
	 * {@link Toroid} class constructor.
	 * @param key the key
	 * @param value the value
	 */
	public Toroid(K key, V value) {
		super(key, value);
		getChild().getChild().setParent(key.getChild().getChild());
		getChild().getChild().getChild().setParent(value);
	}
}