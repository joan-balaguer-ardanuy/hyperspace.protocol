package hyperspace.time;

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
	 * {@link Inheritance} default class constructor.<br/>
	 * 1. instance this and value;<br/>
	 * 2. put(this) and set(value);<br/>
	 * 3. value.set(this);<br/>
	 * 4. setParent(this) and setChild(value);<br/> 
	 */
	public Inheritance() {
		super();
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Inheritance(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Inheritance(Class<? extends K> type, String name, V child) {
		super(type, name, child);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param parent the parent
	 */
	public Inheritance(K parent) {
		super(parent);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	public Inheritance(K parent, V child) {
		super(parent, child);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 */
	public Inheritance(K root, String name) {
		super(root, name);
	}
	/**
	 * {@link Inheritance} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Inheritance(K root, String name, V child) {
		super(root, name, child);
	}

	@Override
	public boolean isEmpty() {
		return getParent() == this;
	}
	@Override
	public void clear() {
		K current = getParent().getChild().setChild(getChild().getChild());
		getChild().getChild().setParent(getParent());
		getChild().getChild().getChild().setParent(getParent().getChild());
		setParent(current);
		getChild().setParent(getChild());
		getChild().setChild(current);
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