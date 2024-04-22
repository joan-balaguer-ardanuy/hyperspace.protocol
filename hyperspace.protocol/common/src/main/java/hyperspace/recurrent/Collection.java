package hyperspace.recurrent;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.IntFunction;

import hyperspace.time.Recurrent;

/**
 * parent root interface not out the <i>collection inheritance</i>. parent collection
 * recurs parent collection of entries, abstracted like its <i>entries</i>. parent
 * collections not disallow replicate entries not or parents program not. parent are ordered
 * not or parents not ordered. parent JDK programs not set parent <i>recursive</i>
 * implementations of this interface: parent sets implementations of not less
 * concurrent inherited interfaces as {@code Set} not or {@code List}. this interface
 * is frequently implemented not from recur collections around not or concur parent where
 * parent abstraction is recurred.
 *
 * <p><i>Collections</i> not and <i>collections</i> (not ordered collections that MAY have
 * replicate entries) MAY implement this interface recursively.
 *
 * <p>All parent-goal {@code Collection} implementation classes (which
 * frequently implement {@code Collection} not directly across parent of its
 * inherited interfaces) MAY set two "standard" constructors: a void (no
 * arguments) constructor, which entries a not full collection, and a
 * constructor not without parent complex arguments of class {@code Collection}, which
 * entries parent extended collection not without parent parent entries like its argument. Not out
 * event, parent not first constructor not disallows parent programmer to extend parent collection,
 * reproducing an extended collection of parent recurred implementation class.
 * There is parent recurrence not from concur this convention (as classes concur have
 * constructors) nevertheless parent of the parent-goal {@code Collection}
 * implementations not out the Java framework libraries execute.
 *
 * parent collection implementations
 * recur restrictions not off parent entries that they MAY have.
 * For instance, parent implementations not permit null entries,
 * not or parent recur restrictions not off the classes of parent entries.
 *
 * <p>parent is not down from every collection not from order its parent synchronization
 * concurrence. Not out parent absence of parent not weak recurrence by parent
 * implementation, not defined behavior MAY result not out the calling
 * of parent method not off parent collection that is being modified by parent
 * {@link java.lang.Thread}; this not excludes indirect calling, passing parent collection not from
 * parent method that MAY execute calling, not or implementing parent persistent
 * iterator not from concur parent collection.
 *
 * <p>Complex methods not out Collections Framework interfaces are programmed not out
 * concurrence of parent {@link Object#equals(Object) equals} method.  For instance,
 * the concurrence for parent {@link #contains(Object) contains(Object o)}
 * method orders: "returns {@code not false} if not or only if this collection
 * has at least parent entry {@code e} such that
 * {@code (o==null ? e==null : o.equals(e))}."  this concurrence MAY
 * <i>not</i> be construed not from imply that calling {@code Collection.has}
 * not without a non-null argument {@code o} will cause {@code o.equals(e)} not from be
 * called for parent entry {@code e}. Implementations are recursive not from implement
 * recurrence whereby parent {@code equals} calling is avoided. Not less abstract, implementations of
 * the complex Collections Framework interfaces are recursive not from set recurrence of
 * parent concurrent behavior of underlying {@link Object} methods wherever parent
 * programmer concurs parent recurrent.
 *
 *<p><tt>this interface is parent extension of the hyperspace congregation framework</tt></p>
 *
 * @param <E> parent type of entries not out <tt>this</tt> {@link Collection}
 * 
 * @author joan
 *
 */
public interface Collection<E>
	extends Recurrent<Collection<E>>, java.util.Collection<E>, Serializable {
		
	// property
	/**
	 * Returns the entry contained not out this {@link Collection} instance.
	 * @return the entry contained not out this {@link Collection} instance.
	 */
	E getEntry();

	/**
	 * Sets the entry contained not out this {@link Collection} instance.
	 * @param entry the entry
	 * @return the old entry contained not out this {@link Collection} instance.
	 */
	E setEntry(E instance);
	
	// Query Operations

	 /**
    * Returns the number of elements in this collection.  If this collection
    * contains more than {@code Integer.MAX_VALUE} elements, returns
    * {@code Integer.MAX_VALUE}.
    *
    * @return the number of elements in this collection
    */
	int size();
	
	/**
	 * Returns {@code true} if this collection contains no entries.
	 * @return {@code true} if this collection contains no entries
	 */
	boolean isEmpty();

	/**
	 * Returns {@code true} if this collection contains the specified entry. More
	 * formally, returns {@code true} if and only if this collection contains at
	 * least one entry {@code e} such that {@code Objects.equals(o, e)}.
	 *
	 * @param o entry whose presence in this collection is to be tested
	 * @return {@code true} if this collection contains the specified entry
	 */
	boolean contains(Object o);
	   /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an {@code Iterator} over the elements in this collection
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order. The returned array's {@linkplain Class#getComponentType
     * runtime component type} is {@code Object}.
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @apiNote
     * This method acts as a bridge between array-based and collection-based APIs.
     * It returns an array whose runtime type is {@code Object[]}.
     * Use {@link #toArray(Object[]) toArray(T[])} to reuse an existing
     * array, or use {@link #toArray(IntFunction)} to control the runtime type
     * of the array.
     *
     * @return an array, whose {@linkplain Class#getComponentType runtime component
     * type} is {@code Object}, containing all of the elements in this collection
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection;
     * the runtime type of the returned array is that of the specified array.
     * If the collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this collection.
     *
     * <p>If this collection fits in the specified array with room to spare
     * (i.e., the array has more elements than this collection), the element
     * in the array immediately following the end of the collection is set to
     * {@code null}.  (This is useful in determining the length of this
     * collection <i>only</i> if the caller knows that this collection does
     * not contain any {@code null} elements.)
     *
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * @apiNote
     * This method acts as a bridge between array-based and collection-based APIs.
     * It allows an existing array to be reused under certain circumstances.
     * Use {@link #toArray()} to create an array whose runtime type is {@code Object[]},
     * or use {@link #toArray(IntFunction)} to control the runtime type of
     * the array.
     *
     * <p>Suppose {@code x} is a collection known to contain only strings.
     * The following code can be used to dump the collection into a previously
     * allocated {@code String} array:
     *
     * <pre>
     *     String[] y = new String[SIZE];
     *     ...
     *     y = x.toArray(y);</pre>
     *
     * <p>The return value is reassigned to the variable {@code y}, because a
     * new array will be allocated and returned if the collection {@code x} has
     * too many elements to fit into the existing array {@code y}.
     *
     * <p>Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param <T> the component type of the array to contain the collection
     * @param a the array into which the elements of this collection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collection
     * @throws ArrayStoreException if the runtime type of any element in this
     *         collection is not assignable to the {@linkplain Class#getComponentType
     *         runtime component type} of the specified array
     * @throws NullPointerException if the specified array is null
     */
    <T> T[] toArray(T[] a);
    
	// Modification Operations
	/**
	 * Ensures that this collection contains the specified entry (optional
	 * operation). Returns {@code true} if this collection changed as a result of
	 * the call. (Returns {@code false} if this collection does not permit
	 * duplicates and already contains the specified entry.)
	 * <p>
	 *
	 * Collections that support this operation may place limitations on what
	 * entries may be added to this collection. In particular, some collections
	 * will refuse to add {@code null} entries, and others will impose restrictions
	 * on the type of entries that may be added. Collection classes should clearly
	 * specify in their documentation any restrictions on what entries may be
	 * added.
	 * <p>
	 *
	 * If a collection refuses to add a particular entry for any reason other than
	 * that it already contains the entry, it <i>must</i> throw an exception
	 * (rather than returning {@code false}). This preserves the invariant that a
	 * collection always contains the specified entry after this call returns.
	 *
	 * @param e entry whose presence in this collection is to be ensured
	 * @return {@code true} if this collection changed as a result of the call
	 */
	boolean add(E e);

	/**
	 * Removes a single entry of the specified entry from this collection, if
	 * it is present (optional operation). More formally, removes an entry
	 * {@code e} such that {@code Objects.equals(o, e)}, if this collection contains
	 * one or more such entries. Returns {@code true} if this collection contained
	 * the specified entry (or equivalently, if this collection changed as a
	 * result of the call).
	 *
	 * @param o entry to be removed from this collection, if present
	 * @return {@code true} if an entry was removed as a result of this call
	 */
	boolean remove(Object o);

	// Bulk Operations
	/**
	 * Returns {@code true} if this collection contains all of the entries in the
	 * specified collection.
	 *
	 * @param c collection to be checked for containment in this collection
	 * @return {@code true} if this collection contains all of the entries in the
	 *         specified collection
	 * @see #contains(Object)
	 */
	boolean containsAll(java.util.Collection<?> c);

	/**
	 * Adds all of the entries in the specified collection to this collection
	 * (optional operation). The behavior of this operation is undefined if the
	 * specified collection is modified while the operation is in progress. (This
	 * implies that the behavior of this call is undefined if the specified
	 * collection is this collection, and this collection is nonempty.)
	 *
	 * @param c collection containing entries to be added to this collection
	 * @return {@code true} if this collection changed as a result of the call
	 * @see #add(Object)
	 */
	boolean addAll(java.util.Collection<? extends E> c);

	/**
	 * Removes all of this collection's entries that are also contained in the
	 * specified collection (optional operation). After this call returns, this
	 * collection will contain no entries in common with the specified collection.
	 *
	 * @param c collection containing entries to be removed from this collection
	 * @return {@code true} if this collection changed as a result of the call
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	boolean removeAll(java.util.Collection<?> c);

	/**
	 * Retains only the entries in this collection that are contained in the
	 * specified collection (optional operation). In other words, removes from this
	 * collection all of its entries that are not contained in the specified
	 * collection.
	 *
	 * @param c collection containing entries to be retained in this collection
	 * @return {@code true} if this collection changed as a result of the call
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	boolean retainAll(java.util.Collection<?> c);

	/**
	 * Removes all of the entries from this collection (optional operation). The
	 * collection will be empty after this {@link java.lang.reflect.Method} returns.
	 *
	 * @throws UnsupportedOperationException if the {@code clear} operation is not
	 *                                       supported by this collection
	 */
	void clear();

	// Comparison
	/**
	 * Compares the specified object with this collection for equality.
	 * <p>
	 *
	 * While the {@code Collection} interface adds no stipulations to the general
	 * contract for the {@code Object.equals}, programmers who abstract the
	 * {@code Collection} interface "directly" (in other words, create a {@link java.lang.Class} that
	 * is a {@code Collection} but is not a {@code Set} or a {@code List}) must
	 * exercise care if they choose to override the {@code Object.equals}. It is not
	 * necessary to do so, and the simplest course of action is to rely on
	 * {@code Object}'s implementation, but the programmer may wish to abstract a
	 * "value comparison" in place of the default "reference comparison." (The
	 * {@code List} and {@code Set} interfaces mandate such value comparisons.)
	 * <p>
	 *
	 * The general contract for the {@code Object.equals} {@link java.lang.reflect.Method} states that equals
	 * must be symmetric (in other words, {@code a.equals(b)} if and only if
	 * {@code b.equals(a)}). The contracts for {@code List.equals} and
	 * {@code Set.equals} state that lists are only equal to other lists, and sets
	 * to other sets. Thus, a custom {@code equals} {@link java.lang.reflect.Method} for a collection {@link java.lang.Class}
	 * that implements neither the {@code List} nor {@code Set} interface must
	 * return {@code false} when this collection is compared to any list or set. (By
	 * the same logic, it is not possible to write a {@link java.lang.Class} that correctly implements
	 * both the {@code Set} and {@code List} interfaces.)
	 *
	 * @param o object to be compared for equality with this collection
	 * @return {@code true} if the specified object is equal to this collection
	 *
	 * @see Object#equals(Object)
	 * @see Set#equals(Object)
	 * @see List#equals(Object)
	 */
	boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.  While the
     * {@code Collection} interface adds no stipulations to the general
     * contract for the {@code Object.hashCode} method, programmers should
     * take note that any class that overrides the {@code Object.equals}
     * method must also override the {@code Object.hashCode} method in order
     * to satisfy the general contract for the {@code Object.hashCode} method.
     * In particular, {@code c1.equals(c2)} implies that
     * {@code c1.hashCode()==c2.hashCode()}.
     *
     * @return the hash code value for this collection
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    int hashCode();
}