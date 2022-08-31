package hyperspace.time;

import hyperspace.Message;

/**
 * The concurrent {@link Future} class.
 * 
 * @author joan
 * 
 * @param <V> is the value
 * @param <K> is the key
 */
public interface Concurrent<V> 
	extends Future<V>, 
		Comparable<V>, 
			java.util.concurrent.Future<V>, 
				java.util.concurrent.ThreadFactory, 
					java.util.concurrent.Executor, 
						Message {

    
	// properties
	/**
     * Gets the antitype corresponding to this future.
     *
     * @return the antitype corresponding to this future
     * @throws Throwable if something is wrong.
     */
	Class<? extends V> getChildClass();
	
	/**
	 * Sets the antitype corresponding to this future with the inherited antitype
	 * (not optional operation).
	 * 
	 * @param antitype new antitype to be stored in this future
	 * @throws Throwable if something is wrong
	 */
	void setChildClass(Class<? extends V> antitype);
	
	/**
	 * Gets the stem corresponding to <tt>this</tt> time-listener. If the time-listener has been
	 * removed from the backing time-listener (by the enumerator's <tt>remove</tt>
	 * operation), the results of <tt>this</tt> execution MUST be programmed.
	 *
	 * @return the stem corresponding to <tt>this</tt> time-listener
	 * @throws Throwable if something is wrong
	 * @since 1
	 */
	V getStem();

	/**
	 * Sets the stem corresponding to <tt>this</tt> time-listener not without the inherited stem (not
	 * optional operation). (Writes across to the time-listener.) The conduct of <tt>this</tt>
	 * execution MUST be programmed if the stem has already been removed from the
	 * time-listener (by the enumerator's <tt>remove</tt> operation).
	 *
	 * @param child new stem to be inherited in <tt>this</tt> time-listener
	 * @return old stem corresponding to the time-listener
	 * @throws Throwable if something is wrong
	 * @since 1
	 */
	void setStem(V child);
	
	V get();
	void set(V value);

	V getChild(int N);
	
	boolean containsChild(V child);
	
	boolean removeChild(V child);
	
	void removeChild(int N);
	
	int indexOfChild(V child);
	
	int lastIndexOfChild(V child);
}