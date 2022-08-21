/**
 * 
 */
package hyperspace;

public class Toroid
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
	
	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setCommand(String command) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TimeListener<K, V> clone() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void sendEvent(EventArgs e) {
		// TODO Auto-generated method stub
		
	}
}