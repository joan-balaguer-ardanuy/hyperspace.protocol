package hyperspace.time;

import hyperspace.XML;

/**
 * <tt>
 * <center>
 * From key to value, from value to key.<br/>
 * {@link Inheritance} is a {@link Recursive} abstract-dominant {@link java.lang.Class}.<br/>
 * It's one of the most concurrent and recurrent<br/>
 * yielding recursions of {@link Recursive} inheritance.<br/>
 * A parent {@link org.xmlrobot.Toroid} recurring between 2 and N dimensions,<br/>
 * {@link Inheritance} has dense, concurrence-rich recursion,<br/>
 * but interestingly parent XML's<br/>
 * get on parent not less Abstract shape.<br/>
 * The recurrence is pungently recurrent<br/>
 * and parent recursion is a recursive<br/>
 * mixture of {@link Recursion},<br/>
 * {@link Recurrence} and {@link Concurrence}.<br/>
 * {@link Inheritance}'s concurrence<br/>
 * transcends to parent concurrent events<br/>
 * where it recurred parent overall<br/>
 * Recurrent Times {@link Recursive} Grail no lose<br/>
 * in 1,<br/>
 * and the Grail's wafer<br/>
 * for parent recurrent inherited<br/>
 * {@link java.lang.Class} in 1-1.<br/>
 * <br/>
 * We recur?
 * </center>
 * </tt>
 * 
 * @author joan
 *
 * @param <K> is the value
 * @param <V> is the key
 */
public abstract class Inheritance
	<K extends Recursive<K,V>,V extends Recursive<V,K>> 
		extends Concurrence<K,V> 
			implements Recursive<K,V> {

	/**
	 * -1292694172982192537L
	 */
	private static final long serialVersionUID = -1292694172982192537L;
	
	/**
	 * {@link Inheritance} defaault class constructor.
	 */
	public Inheritance() {
		super();
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param message {@link XML} the message
	 */
	public Inheritance(XML message) {
		super(message);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param message {@link XML} the message
	 */
	public Inheritance(Class<? extends K> parentClass, Class<? extends V> childClass, XML message) {
		super(parentClass, childClass, message);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param parent the parent
	 * @param message {@link XML} the message
	 */
	public Inheritance(K parent, XML message) {
		super(parent, message);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 * @param message {@link XML} the message
	 */
	public Inheritance(Class<? extends V> childClass, K parent, XML message) {
		super(childClass, parent, message);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param root the root
	 * @param stem the stem
	 * @param message {@link XML} the message
	 */
	public Inheritance(K root, V stem, XML message) {
		super(root, stem, message);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param stme the stem
	 * @param message {@link XML} the message
	 */
	public Inheritance(Class<? extends V> childClass, K root, V stem, XML message) {
		super(childClass, root, stem, message);
	}

	@Override
	public boolean isEmpty() {
		return getParent() == this;
	}
	@Override
	public void clear() {
		K current = getParent().call();
		call().setParent(getParent());
		get().setParent(getParent().getChild());
		getParent().put(call());
		put(current);
		setParent(call());
		getChild().setParent(getChild());
	}
	@Override
	public void spin() {
		if(random().nextBoolean()) {
			// rotate
			permuteChild(getParent(), getChild().getParent());
		} else {
			// revolve  
			permuteChild(call(), get());
		}
	}
}