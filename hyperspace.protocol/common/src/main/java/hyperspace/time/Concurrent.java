package hyperspace.time;

/**
 * The concurrent {@link Future} class.
 * 
 * @author joan
 * 
 * @param <V> is the value
 * @param <K> is the key
 */
public interface Concurrent<V> extends Future<V> {

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
	V setStem(V child);

	V getChild(int N);
	
	boolean containsChild(V child);
	
	boolean removeChild(V child);
	
	void removeChild(int N);
	
	int indexOfChild(V child);
	
	int lastIndexOfFuture(V child);
}