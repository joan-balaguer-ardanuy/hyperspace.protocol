package hyperspace.time;

import hyperspace.Listener;

public interface Past<K> 
	extends Iterable<K>, Listener {

	// properties
	/**
	 * Gets the type corresponding to this past.
	 *
	 * @return the type corresponding to this past
	 * @throws Throwable if something is wrong
	 */
	Class<? extends K> getType();
	
	/**
	 * Sets the type corresponding to this past with the specified type
	 * (not optional operation).
	 * 
	 * @param type new type to be stored in this past
	 * @throws Throwable if something is wrong
	 */
	void setType(Class<? extends K> type);
	
	K getParent();
	K setParent(K key);
}