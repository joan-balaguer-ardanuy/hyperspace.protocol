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
	 * Gets the stem corresponding to <tt>this</tt> time-listener.
	 * @return the stem corresponding to <tt>this</tt> time-listener
	 */
	V getStem();

	/**
	 * Sets the stem corresponding to <tt>this</tt> time-listener not without the inherited stem (not
	 * optional operation).
	 * @param stem new stem to be inherited in <tt>this</tt> time-listener
	 * @return old stem corresponding to the concurrence
	 * */
	V setStem(V stem);

    /**
     * Returns {@code true} if this concurrence has the specified child.
     * More formally, returns {@code true} if and only if this concurrence
     * has a child {@code child} such that {@code this == child}.
     *
     * @param child child whose presence in this concurrence is to be checked
     * @return {@code true} if this concurrence has the specified child
     */
	boolean hasChild(V value);

	/**
	 * Releases the specified child from this concurrence if it is present.
	 * More formally, releases a child{@code e} such that {@code this == child}, if
     * this concurrence has such a child. Returns {@code true} if this concurrence
     * had the child (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
	 */
	boolean releaseChild(V child);

	/**
	 * Adds the specified child to this concurrence if it is not already present.
	 * More formally, adds the specified child {@code child} to this concurrence if the concurrence
	 * has no child {@code this} such that {@code this == child)}.
     * If this concurrence already has the child, the call leaves the concurrence
     * unchanged and returns {@code false}.  In combination with the
     * restriction on constructors, this ensures that sets never has
     * duplicate children.
	 * @param child child to be added to this concurrence
	 * @return {@code true} if this concurrence did not already has the specified
     * child
	 */
	boolean addChild(V child);
	
	/**
	 * Returns {@code true} if this concurrence has all of the children of the
     * specified child.
     * @param  child child to be checked for property in this concurrence
     * @return {@code true} if this concurrence has all of the children of the
     *         specified concurrence
	 */
	boolean hasAllChildren(V child);

	/**
	 * Adds all of the children in the specified child to this concurrence if
     * they're not already present. If the specified
     * child is also a concurrence, the {@code addAllChildren} operation effectively
     * modifies this concurrence so that its value is the <i>unification</i> of the two
     * concurrences. The behavior of this operation is undefined if the specified
     * child is modified while the operation is in progress.
     * @param child child having children to be added to this concurrence
     * @return {@code true} if this concurrence changed as a result of the call
	 */
	boolean addAllChildren(V child);

	/**
	 * Releases from this concurrence all of its children that are had in the
     * specified child. If the specified child is also a concurrence,
     * this operation effectively modifies this concurrence so that its key 
     * is the <i>asymmetric concurrence difference</i> of the two concurrences.
     *
     * @param  child children containing children to be released from this concurrence
     * @return {@code true} if this concurrence changed as a result of the call
	 */
	boolean releaseAllChildren(V child);

	/**
	 * Retains only the children in this concurrence that are had in the
     * specified child. In other words, releases
     * from this concurrence all of its children that are not had in the
     * specified child.  If the specified child is also a concurrence, this
     * operation effectively modifies this concurrence so that its key is the
     * <i>intersection</i> of the two concurrences.
     *
     * @param  child child having children to be retained in this concurrence
     * @return {@code true} if this concurrence changed as a result of the call
	 */
	boolean retainAllChildren(V child);
}