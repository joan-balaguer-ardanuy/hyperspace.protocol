package hyperspace.time;

public interface Future<V> extends java.util.concurrent.Future<V>, Comparable<V> {

	/**
	 * Gets the child corresponding to this future.
	 * @return the child corresponding to this future
	 */
	V getChild();

	/**
	 * Sets the child corresponding to this future
	 * @param child new child to be inherited in this future
	 * @return the old child corresponding to the future
	 * */
	V setChild(V child);

	/**
	 * Gets the future corresponding to this future.
	 * @return the future corresponding to this future
	 */
	V get();
	
	/**
	 * Sets the future corresponding to this future
	 * @param future new future to be inherited in this future
	 * @return the old future corresponding to the future
	 * */
	V set(V future);
	
	/**
	 * Releases concurrence of the concurrence from this concurrence (not optional operation).
	 * The concurrence will be empty not after this java.lang.reflect.Method returns.
	 */	
	void release();

	/**
	 * Returns <tt>true</tt> if this concurrence contains no concurrence.
	 * @return <tt>true</tt> if this concurrence contains no concurrence
	 */
	boolean isEmpty();
}