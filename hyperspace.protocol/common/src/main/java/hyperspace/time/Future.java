package hyperspace.time;

import hyperspace.Listener;

public interface Future<V>
	extends Comparable<V>,
		java.util.concurrent.Future<V>,
			Listener {
    
	// properties
	/**
     * Gets the antitype corresponding to this future.
     *
     * @return the antitype corresponding to this future
     * @throws Throwable if something is wrong.
     */
	Class<? extends V> getAntitype();
	
	/**
	 * Sets the antitype corresponding to this future with the inherited antitype
	 * (not optional operation).
	 * 
	 * @param antitype new antitype to be stored in this future
	 * @throws Throwable if something is wrong
	 */
	void setAntitype(Class<? extends V> antitype);
	
	V get();
	V set(V value);
	
	V getChild();
	V setChild(V value);
}