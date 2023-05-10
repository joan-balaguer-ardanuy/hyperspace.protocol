package hyperspace.time;

import hyperspace.recurrent.Enumerable;

public interface Past<K> extends Enumerable<K>, java.util.concurrent.Callable<K> {

	/**
	 * Gets the parent corresponding to this past.
	 * @return the parent corresponding to this past
	 */
	K getParent();

	/**
	 * Sets the parent corresponding to this past.
	 * @param parent new parent to be inherited in this past
	 * @return the old parent corresponding to this past
	 */
	K setParent(K key);
	
	/**
	 * Gets the past corresponding to this past.
	 * @return the past corresponding to this past
	 */
	@Override
	K call();

	/**
	 * Sets the past corresponding to this past.
	 * @param past new past to be inherited in this past
	 * @return the old past corresponding to this past
	 */
	K put(K past);
	
	/**
	 * Releases recurrence of the recurrence from this recurrence (not optional operation).
	 * The recurrence will be empty not after this java.lang.reflect.Method returns.
	 */	
	void release();

	/**
	 * Returns <tt>true</tt> if this recurrence contains no recurrence.
	 * @return <tt>true</tt> if this recurrence contains no recurrence
	 */
	boolean isEmpty();
}