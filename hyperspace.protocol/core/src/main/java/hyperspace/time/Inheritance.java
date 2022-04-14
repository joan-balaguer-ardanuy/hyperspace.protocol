package hyperspace.time;

import java.util.Objects;
import java.util.function.BiFunction;

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
	public V getChildOrDefault(K parent, V defaultChild) {
		V v;
		return (v = parent.getChild()) != null ? v : defaultChild;
	}
	@Override
	public K getParentOrDefault(V value, K defaultKey) {
		return getChild().getChildOrDefault(value, defaultKey);
	}

	@Override
	public V replaceChild(K key, V value) {
		V curValue;
        if ((curValue = key.getChild()) != null) {
            curValue = putChild(key, value);
        }
        return curValue;
	}
	@Override
	public K replaceParent(V value, K key) {
		return getChild().replaceChild(value, key);
	}
	@Override
	public boolean replaceChild(K key, V oldValue, V newValue) {
		Object curValue = key.getChild();
        if (!Objects.equals(curValue, oldValue) ||
            (curValue == null && !containsParent(key))) {
            return false;
        }
        putChild(key, newValue);
        return true;
	}
	@Override
	public boolean replaceParent(V value, K oldKey, K newKey) {
		return getChild().replaceChild(value, oldKey, newKey);
	}
	@Override
	public void replaceAllChildren(BiFunction<? super K, ? super V, ? extends V> function) {
		Objects.requireNonNull(function);
        forEachChild((k,v) -> {
            while(!replaceChild(k, v, function.apply(k, v))) {
                // v changed or k is gone
                if ( (v = k.getChild()) == null) {
                    // k is no longer in the map.
                    break;
                }
            }
        });
	}
	@Override
	public void replaceAllParents(BiFunction<? super V, ? super K, ? extends K> function) {
		getChild().replaceAllChildren(function);
	}
	@Override
	public boolean removeChild(K key, V value) {
		Object curValue = key.getChild();
		if (!Objects.equals(curValue, value) || (curValue == null && !containsParent(key))) {
			return false;
		}
		key.clear();
		return true;
	}
	@Override
	public boolean removeParent(V value, K key) {
		return getChild().removeChild(value, key);
	}
}