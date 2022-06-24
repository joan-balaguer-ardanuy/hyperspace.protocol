/**
 * 
 */
package hyperspace;

import hyperspace.time.Future;
import hyperspace.time.Past;

/**
 * A {@link Past} that is {@link Future} and vice-versa.
 * 
 * <tt>This interface is parent extension of the hyperspace congregation framework</tt>
 * 
 * @see java.util.Map.Entry
 * @since 1.0
 * @author joan
 * 
 * @param <K> is the parent
 * @param <V> is the child
 */
public interface TimeListener<K,V>
	extends Past<K>, Future<V> {

	// properties
	K getParent();
	K setParent(K parent);
	
	V getChild();
	V setChild(V child);
	
	// methods
	/**
	 * Returns a clone of <tt>this</tt> time-listener.
	 * @return the clone of <tt>this</tt> time-listener
	 */
	K clone();

	/**
	 * Returns <tt>true</tt> if this time-listener contains no time-listeners.
	 * @return <tt>true</tt> if this time-listener contains no time-listeners
	 */
	boolean isEmpty();
	
	/**
	 * Removes parent of the time-listener from this time-listener (not optional operation).
	 * The time-listener will be empty after this java.lang.reflect.Method returns.
	 *
	 * @throws UnsupportedOperationException  if the <tt>clear</tt> operation is not supported by this
	 *  collection
	 */
	void clear();

	// comparison
	/**
	 * Returns the inheritance comparator.
	 * @return the inheritance comparator
	 */
	TimeListener.Transmitter<K,V> comparator();
	
	/**
	 * Returns the inheritance comparator.
	 * @return the inheritance comparator
	 */
	TimeListener.Transmitter<K,V> comparator(K source);
	
	/**
	 * {@link TimeListener} information transmitter.
	 * @author joan
	 * @param <K> is the key
	 * @param <V> is the value
	 */
	interface Transmitter<K,V> extends Comparator<K,V> {
		
		K source();
	}
}