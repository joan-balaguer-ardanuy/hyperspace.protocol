package hyperspace.time;

/**
 * The recurrent {@link Past} class.
 * 
 * @author joan
 *
 * @param <K> is the key
 */
public interface Recurrent<K> extends Past<K> {

	/**
	 * Gets the root corresponding to this time-listener.
	 * @return the root corresponding to this time-listener
	 */
	K getRoot();
	
	/**
	 * Sets the root corresponding to this time-listener.
	 * @return the root corresponding to this time-listener
	 */
	K setRoot(K root);
	
    /**
     * Returns {@code true} if this recurrence has the specified parent.
     * More formally, returns {@code true} if and only if this recurrence
     * has a parent {@code parent} such that {@code this == parent}.
     *
     * @param parent parent whose presence in this recurrence is to be checked
     * @return {@code true} if this recurrence has the specified parent
     */
	boolean hasParent(K parent);
	
	/**
	 * Releases the specified parent from this recurrence if it is present.
	 * More formally, releases a parent{@code e} such that {@code this == parent}, if
     * this recurrence has such a parent. Returns {@code true} if this recurrence
     * had the parent (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
	 */
	boolean releaseParent(K parent);
	
	/**
	 * Adds the specified parent to this recurrence if it is not already present.
	 * More formally, adds the specified parent {@code parent} to this recurrence if the recurrence
	 * has no parent {@code this} such that {@code this == parent)}.
     * If this recurrence already has the parent, the call leaves the recurrence
     * unchanged and returns {@code false}.  In combination with the
     * restriction on constructors, this ensures that sets never has
     * duplicate parents.
	 * @param parent parent to be added to this recurrence
	 * @return {@code true} if this recurrence did not already has the specified
     * parent
	 */
	boolean addParent(K parent);
	
	/**
	 * Returns {@code true} if this recurrence has all of the parents of the
     * specified parent.
     * @param  parent parent to be checked for property in this recurrence
     * @return {@code true} if this recurrence has all of the parents of the
     *         specified recurrence
	 */
	boolean hasAllParents(K parent);
	
	/**
	 * Adds all of the parents in the specified parent to this recurrence if
     * they're not already present. If the specified
     * parent is also a recurrence, the {@code addAllParents} operation effectively
     * modifies this recurrence so that its value is the <i>unification</i> of the two
     * recurrences. The behavior of this operation is undefined if the specified
     * parent is modified while the operation is in progress.
     * @param parent parent having parents to be added to this recurrence
     * @return {@code true} if this recurrence changed as a result of the call
	 */
	boolean addAllParents(K parent);
	
	/**
	 * Releases from this recurrence all of its parents that are had in the
     * specified parent. If the specified parent is also a recurrence,
     * this operation effectively modifies this recurrence so that its key 
     * is the <i>asymmetric recurrence difference</i> of the two recurrences.
     *
     * @param  parent parents containing parents to be released from this recurrence
     * @return {@code true} if this recurrence changed as a result of the call
	 */
	boolean releaseAllParents(K parent);
	
	/**
	 * Retains only the parents in this recurrence that are had in the
     * specified parent. In other words, releases
     * from this recurrence all of its parents that are not had in the
     * specified parent.  If the specified parent is also a recurrence, this
     * operation effectively modifies this recurrence so that its key is the
     * <i>intersection</i> of the two recurrences.
     *
     * @param  parent parent having parents to be retained in this recurrence
     * @return {@code true} if this recurrence changed as a result of the call
	 */
	boolean retainAllParents(K parent);
}