package hyperspace.time;

import hyperspace.Listener;
import hyperspace.recurrent.Enumerable;

/**
 * The recurrent {@link Past} class.
 * 
 * @author joan
 *
 * @param <K> is the key
 */
public interface Recurrent<K> extends Past<K>, Enumerable<K>, java.util.concurrent.Callable<K>, Listener {

	// properties
	/**
	 * Gets the type corresponding to this past.
	 *
	 * @return the type corresponding to this past
	 * @throws Throwable if something is wrong
	 */
	Class<? extends K> getParentClass();
	
	/**
	 * Sets the type corresponding to this past with the specified type
	 * (not optional operation).
	 * 
	 * @param type new type to be stored in this past
	 * @throws Throwable if something is wrong
	 */
	void setParentClass(Class<? extends K> type);

	K getRoot();
	void setRoot(K parent);
	
	@Override
	K call();
	void put(K key);

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
	
	// methods
	K getParent(int N);
	boolean containsParent(K parent);
	boolean removeParent(K parent);
	int indexOfParent(K parent);
	int lastIndexOfParent(K parent);
	void removeParent(int N);
}