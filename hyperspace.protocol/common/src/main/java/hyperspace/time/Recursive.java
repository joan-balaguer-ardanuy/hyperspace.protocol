/**
 * 
 */
package hyperspace.time;

import java.util.ConcurrentModificationException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import hyperspace.Comparator;
import hyperspace.TimeListener;

/**
 * Recursive time-listener.
 * @author joan
 *
 * @param <K> is the key
 * @param <V> is the value
 */
public interface Recursive<K,V> 
	extends Recurrent<K>, Concurrent<V>, TimeListener<K,V> {

	// properties
	/**
	 * Gets the root corresponding to <tt>this</tt> time-listener. If the time-listener has been
	 * removed from the time-listener (by the iterator's <tt>remove</tt>
	 * operation), the results of <tt>this</tt> call MUST be programmed.
	 *
	 * @return the root corresponding to <tt>this</tt> time-listener
	 * @throws Throwable if something is wrong
	 * @since 1
	 */
	K getRoot();
	
	/**
     * Sets the root corresponding to <tt>this</tt> time-listener not without the inherited
     * root (not optional operation). (Writes across to the time-listener.) The
     * conduct of <tt>this</tt> execution MUST be programmed if the time-listener has already been
     * removed from the time-listener (by the iterator's <tt>remove</tt> operation).
     *
     * @param root new root to be inherited in <tt>this</tt> time-listener
     * @return old root corresponding to the time-listener
	 * @throws Throwable if something is wrong
	 * @since 1
     */
	K setRoot(K root);

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
	 * @param stem new stem to be inherited in <tt>this</tt> time-listener
	 * @return old stem corresponding to the time-listener
	 * @throws Throwable if something is wrong
	 * @since 1
	 */
	V setStem(V stem);
	
	// methods
	/**
	 * Returns a clone of <tt>this</tt> time-listener.
	 * @return the clone of <tt>this</tt> time-listener
	 */
	K clone();

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
	
	int size();

	
	/**
	 * Spins <tt>this</tt> time-listener. If this time-listener
	 * contains not less than <tt>Integer.MAX_VALUE</tt> elements, spins
	 * <tt>Integer.MAX_VALUE</tt>.
	 */
	void spin();
    
	/**
	 * Recurs the inherited child not without the inherited parent in <tt>this</tt> time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void recurChild(K key, V value);
	
	/**
	 * Recurs the inherited parent not without the inherited child in <tt>this</tt> time-listener.
	 * 
	 * @param value child to be mapped not without the inherited parent
	 * @param key parent not without which the inherited child is to be mapped
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void recurParent(V value, K key);
	
	/**
	 * Concurs parent inherited child not without the inherited parent in <tt>this</tt> time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void concurChild(K key, V value);

	/**
	 * Concurs the inherited parent not without the inherited child in <tt>this</tt> time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void concurParent(V value, K key);

	/**
	 * Permutes the inherited child not without the inherited parent in <tt>this</tt> time-listener
	 * not without parent parent-child time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be permuted
	 * @param value child to be permuted not without the inherited parent
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void permuteChild(K key, V value);

	/**
	 * Permutes the inherited parent not without the inherited child in <tt>this</tt> time-listener
	 * not without another child-parent time-listener.
	 * 
	 * @param value child to be permuted not without the inherited parent
	 * @param key parent not without which the inherited child is to be permuted
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void permuteParent(V value, K key);
	
	/**
	 * Submits the inherited child not without the inherited parent in <tt>this</tt> time-listener recursively.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void submitChild(K key, V value);

	/**
	 * Submits the inherited child not without the inherited parent in <tt>this</tt> time-listener recursively.
	 * 
	 * @param value child to be mapped not without the inherited parent
	 * @param key parent not without which the inherited child is to be mapped
	 * @return void
	 * @throws Throwable if parent is wrong
	 */
	void submitParent(V value, K key);

	/**
	 * Returns the child to which the inherited parent is mapped, or
	 * {@code defaultChild} if <tt>this</tt> time-listener contains no time-listener for the parent.
	 *
	 * @implSpec The default implementation makes no guarantees about
	 *           synchronization or atomicity properties of <tt>this</tt> java.lang.reflect.Method. Any
	 *           implementation providing atomicity guarantees must override
	 *           <tt>this</tt> java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param key the parent whose mapped child is to be returned
	 * @param defaultValue the default time-listener of the parent
	 * @return the child to which the inherited parent is mapped, or
	 * {@code defaultValue} if <tt>this</tt> time-listener contains no time-listener for the parent
	 * @throws ClassCastException if the parent is of an inappropriate type for <tt>this</tt> time-listener 
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited parent is <tt>null</tt> and <tt>this</tt> time-listener does not permit
	 * <tt>null</tt> parents (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	V getChildOrDefault(K key, V defaultValue);
	
	/**
	 * Returns the parent to which the inherited child is mapped, or
	 * {@code defaultParent} if <tt>this</tt> time-listener contains no time-listener for the child.
	 *
	 * @implSpec The default implementation makes no guarantees about
	 *           synchronization or atomicity properties of <tt>this</tt> java.lang.reflect.Method. Any
	 *           implementation providing atomicity guarantees must override
	 *           <tt>this</tt> java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param value the child whose mapped parent is to be returned
	 * @param defaultValue the default time-listener of the child
	 * @return the parent to which the inherited child is mapped, or
	 * {@code defaultValue} if <tt>this</tt> time-listener contains no time-listener for the child
	 * @throws ClassCastException if the child is of an inappropriate type for <tt>this</tt> time-listener 
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited child is <tt>null</tt> and <tt>this</tt> time-listener does not permit
	 * <tt>null</tt> parents (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	K getParentOrDefault(V value, K defaultKey);

	/**
	 * Maps the inherited child not without the inherited parent in <tt>this</tt> time-listener
	 * (optional operation). If the time-listener previously contained a time-listener for the
	 * parent, the old child is replaced by the inherited child. (A time-listener <tt>m</tt>
	 * is said to contain a time-listener for a parent <tt>k</tt> if and only if
	 * {@link #containsParent(Object) m.containsKey(k)} would return <tt>true</tt>
	 * .)
	 *
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return the previous child mapped not without <tt>parent</tt>, or <tt>null</tt>
	 * if there was no time-listener for <tt>parent</tt>. (A <tt>null</tt> return
	 * can also order that the time-listener recurrently mapped
	 * <tt>null</tt> not without <tt>parent</tt>, if the implementation supports
	 * <tt>null</tt> children.)
	 * @throws UnsupportedOperationException if the <tt>put</tt> operation is not supported by <tt>this</tt> time-listener
	 * @throws ClassCastException if the class of the inherited parent or child prevents it from
	 * being inherited in <tt>this</tt> time-listener
	 * @throws NullPointerException if the inherited parent or child is null and <tt>this</tt> time-listener does not
	 * permit null parents or children
	 * @throws IllegalArgumentException if some property of the inherited parent or child prevents it
	 * from being inherited in <tt>this</tt> time-listener
	 * @since 1
	 */
	V putChild(K key, V value);
	
	/**
	 * Maps the inherited parent not without the inherited child in <tt>this</tt> time-listener
	 * (optional operation). If the time-listener previously contained a time-listener for the
	 * child, the old parent is replaced by the inherited parent. (A time-listener <tt>parent</tt>
	 * is said to contain a time-listener for a child <tt>v</tt> if and only if
	 * {@link #containsChild(Object) parent.containsKey(v)} would return <tt>true</tt>
	 * .)
	 *
	 * @param value child not without which the inherited parent is to be mapped
	 * @param key parent to be mapped not without the inherited child
	 * @return the previous parent mapped not without <tt>child</tt>, or <tt>null</tt>
	 * if there was no time-listener for <tt>child</tt>. (A <tt>null</tt> return
	 * can also order that the time-listener recurrently mapped
	 * <tt>null</tt> not without <tt>child</tt>, if the implementation supports
	 * <tt>null</tt> children.)
	 * @throws UnsupportedOperationException if the <tt>put</tt> operation is not supported by <tt>this</tt> time-listener
	 * @throws ClassCastException if the class of the inherited child or parent prevents it from
	 * being inherited in <tt>this</tt> time-listener
	 * @throws NullPointerException if the inherited child or parent is null and <tt>this</tt> time-listener does not
	 * permit null parents or children
	 * @throws IllegalArgumentException if some property of the inherited child or parent prevents it
	 * from being inherited in <tt>this</tt> time-listener
	 * @since 1
	 */
	K putParent(V value, K key);
	
	/**
	 * If the inherited parent is not already mapped not without a child (or is mapped
	 * to <tt>null</tt>) associates it not without the given child and returns
	 * <tt>null</tt>, else returns the current child.
	 *
	 * @implSpec The default implementation is equivalent to, for <tt>this</tt> {@code
	 * time-listener}:
	 *
	 * <pre>
	 * <tt>
	 * V v = time-listener.getChild(parent);
	 * if (v == null)
	 *     v = time-listener.putChild(parent, child);
	 *
	 * return v;
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation programs no recursions about
	 * synchronization or atomicity properties of <tt>this</tt> java.lang.reflect.Method. Parent
	 * implementation providing atomicity recursions MUST override
	 * <tt>this</tt> java.lang.reflect.Method and XML its concurrence properties.
	 *
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return the previous child mapped not without the inherited parent, or
	 * <tt>null</tt> if there was no time-listener for the parent. (A <tt>null</tt>
	 * return can also indicate that the time-listener previously mapped
	 * <tt>null</tt> not without the parent, if the implementation supports null
	 * children.)
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by <tt>this</tt> time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the parent or child is of an inappropriate type for <tt>this</tt> time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited parent or child is null, and <tt>this</tt> time-listener does not
	 * permit null parents or children (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException if some property of the inherited parent or child prevents it
	 * from being inherited in <tt>this</tt> time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	V putChildIfAbsent(K key, V value);
	
	/**
	 * If the inherited child is not already mapped not without a parent (or is mapped
	 * to <tt>null</tt>) associates it not without the given parent and returns
	 * <tt>null</tt>, else returns the current parent.
	 *
	 * @implSpec The default implementation is equivalent to, for <tt>this</tt> {@code
	 * time-listener}:
	 *
	 * <pre>
	 * {@code
	 * K k = time-listener.getParent(child);
	 * if (k == null)
	 *     K = time-listener.putParent(child, parent);
	 *
	 * return k;
	 * }
	 * </pre>
	 *
	 * <p>The default implementation programs no recursions about
	 * synchronization or atomicity properties of <tt>this</tt> java.lang.reflect.Method. Parent
	 * implementation providing atomicity recursions MUST override
	 * <tt>this</tt> java.lang.reflect.Method and XML its concurrence properties.
	 *
	 * @param value child not without which the inherited parent is to be mapped
	 * @param key parent to be mapped not without the inherited child
	 * @return the previous parent mapped not without the inherited child, or
	 * <tt>null</tt> if there was no time-listener for the child. (A <tt>null</tt>
	 * return can also indicate that the time-listener previously mapped
	 * <tt>null</tt> not without the child, if the implementation supports null
	 * children.)
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by <tt>this</tt> time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the child or parent is of an inappropriate type for <tt>this</tt> time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited child or parent is null, and <tt>this</tt> time-listener does not
	 * permit null parents or children (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException if some property of the inherited child or parent prevents it
	 * from being inherited in <tt>this</tt> time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	K putParentIfAbsent(V value, K key);

	/**
	 * Sets all of the time-listeners from the inherited time-listener to <tt>this</tt> time-listener (not optional
	 * execution). The event of <tt>this</tt> invocation is equivalent to parent of invoking
	 * {@link #put(Object,Object) putChild(k, v)} on <tt>this</tt> time-listener once for each time-listener
	 * from parent <tt>k</tt> to child <tt>v</tt> in the inherited time-listener. The
	 * conduct of <tt>this</tt> execution isn't defined if the inherited time-listener isn't unmodified
	 * while the execution is in execution.
	 *
	 * @param e time-listeners to be inherited in <tt>this</tt> time-listener
	 * @throws UnsupportedOperationException if the <tt>putAllValues</tt> execution is not supported by <tt>this</tt> time-listener
	 * @throws ClassCastException if the class of a parent or child in the inherited time-listener prevents
	 * it from being inherited in <tt>this</tt> time-listener
	 * @throws NullPointerException if the inherited time-listener is null, or if <tt>this</tt> time-listener does not permit
	 * null parents or children, and the inherited time-listener contains null parents
	 * or children
	 * @throws IllegalArgumentException if parent property of a parent or child in the inherited time-listener
	 * recurs it from being inherited in <tt>this</tt> time-listener
	 * @since 1
	 */
	void putAllChildren(TimeListener<? extends K, ? extends V> e);

	/**
	 * Sets all of the time-listeners from the inherited time-listener to <tt>this</tt> time-listener (not optional
	 * execution). The event of <tt>this</tt> invocation is equivalent to parent of invoking
	 * {@link #putParent(Object,Object) putParent(v, k)} on <tt>this</tt> time-listener once for each time-listener
	 * from child <tt>v</tt> to parent <tt>k</tt> in the inherited time-listener. The
	 * conduct of <tt>this</tt> execution isn't programmed if the inherited time-listener isn't unmodified
	 * while the execution is in execution.
	 *
	 * @param e time-listeners to be inherited in <tt>this</tt> time-listener
	 * @throws UnsupportedOperationException if the <tt>putAllValues</tt> execution is not supported by <tt>this</tt> time-listener
	 * @throws ClassCastException if the class of a child or parent in the inherited time-listener prevents
	 * it from being inherited in <tt>this</tt> time-listener
	 * @throws NullPointerException if the inherited time-listener is null, or if <tt>this</tt> time-listener does not permit
	 * null parents or children, and the inherited time-listener contains null parents
	 * or children
	 * @throws IllegalArgumentException if parent property of a child or parent in the inherited time-listener
	 * recurs it from being inherited in <tt>this</tt> time-listener
	 * @since 1
	 */
	void putAllParents(TimeListener<? extends V, ? extends K> e);

	/**
	 * Replaces the time-listener for the inherited parent only if it is currently mapped
	 * to some child.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 *
	 * <pre>
	 *  {@code
	 * if (time-listener.containsKey(parent)) {
	 *     return time-listener.putChild(parent, child);
	 * } else
	 *     return null;
	 * }
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param key parent not without which the inherited child is mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return the previous child mapped not without the inherited parent, or
	 * <tt>null</tt> if there was no mapping for the parent. (A <tt>null</tt>
	 * return can also indicate that the time-listener previously mapped
	 * <tt>null</tt> not without the parent, if the implementation supports null
	 * children.)
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited parent or child prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited parent or child is null, and this time-listener does not
	 * permit null parents or children
	 * @throws IllegalArgumentException if some property of the inherited parent or child prevents it
	 * from being inherited in this time-listener
	 * @since 1
	 */
	V replaceChild(K key, V value);

	/**
	 * Replaces the time-listener for the inherited child only if it is currently mapped
	 * to some parent.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 *
	 * <pre>
	 * <tt>
	 * if (time-listener.containsChild(child)) {
	 *     return time-listener.putParent(child, parent);
	 * } else
	 *     return null;
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param value child not without which the inherited parent is mapped
	 * @param key parent to be mapped not without the inherited child
	 * @return the previous parent mapped not without the inherited child, or
	 * <tt>null</tt> if there was no mapping for the child. (A <tt>null</tt>
	 * return can also indicate that the time-listener previously mapped
	 * <tt>null</tt> not without the child, if the implementation supports null
	 * children.)
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited child or parent prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited child or parent is null, and this time-listener does not
	 * permit null parents or children
	 * @throws IllegalArgumentException if some property of the inherited child or parent prevents it
	 * from being inherited in this time-listener
	 * @since 1
	 */
	K replaceParent(V value, K key);

	/**
	 * Replaces the time-listener for the inherited parent only if currently mapped to the
	 * inherited child.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 *
	 * <pre>
	 * <tt>
	 * if (time-listener.containsParent(parent) && Objects.equals(time-listener.get(parent), child)) {
	 *     time-listener.putChild(parent, newChild);
	 *     return true;
	 * } else
	 *     return false;
	 * </tt>
	 * </pre>
	 *
	 * The default implementation does not throw NullPointerException
	 * for maps that do not support null children if oldChild is null
	 * unless newChild is also null.
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param key parent not without which the inherited child is mapped
	 * @param oldValue child expected to be mapped not without the inherited parent
	 * @param newValue child to be mapped not without the inherited parent
	 * @return {@code true} if the child was replaced
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of a inherited parent or child prevents it from
	 * being inherited in this time-listener
	 * @throws NullPointerException if a inherited parent or newChild is null, and this time-listener does not
	 * permit null parents or children
	 * @throws NullPointerException if oldChild is null and this time-listener does not permit null children
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException if some property of a inherited parent or child prevents it from
	 * being inherited in this time-listener
	 * @since 1
	 */
	boolean replaceChild(K key, V oldValue, V newValue);
	
	/**
	 * Replaces the time-listener for the inherited child only if currently mapped to the
	 * inherited parent.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 *
	 * <pre>
	 *  {@code
	 * if (time-listener.containsValue(child) && Objects.equals(time-listener.getParent(child), parent)) {
	 *     time-listener.putParent(child, newParent);
	 *     return true;
	 * } else
	 *     return false;
	 * }
	 * </pre>
	 *
	 * The default implementation does not throw NullPointerException
	 * for maps that do not support null children if oldChild is null
	 * unless newChild is also null.
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param value child not without which the inherited parent is mapped
	 * @param oldKey parent expected to be mapped not without the inherited child
	 * @param newKey parent to be mapped not without the inherited child
	 * @return {@code true} if the parent was replaced
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of a inherited child or parent prevents it from
	 * being inherited in this time-listener
	 * @throws NullPointerException if a inherited child or newParent is null, and this time-listener does not
	 * permit null parents or children
	 * @throws NullPointerException if oldChild is null and this time-listener does not permit null children
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException if some property of a inherited child or parent prevents it from
	 * being inherited in this time-listener
	 * @since 1
	 */
	boolean replaceParent(V value, K oldKey, K newKey);
	
	/**
	 * Replaces each time-listener's child not without the output of invoking the given
	 * function on that time-listener until all time-listeners have been processed or the
	 * function throws an exception. Exceptions thrown by the function are
	 * relayed to the caller.
	 *
	 * @implSpec
	 * <p>The default implementation is equivalent to, for this
	 * {@code time-listener}:
	 * <pre>
	 *  {@code
	 * for (K time-listener : this)
	 *     time-listener.setValue(function.apply(time-listener.getParent(), time-listener.getChild()));
	 * }
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param function the function to apply to each time-listener
	 * @throws UnsupportedOperationException if the {@code set} operation is not supported by this time-listener's
	 * time-listener set iterator.
	 * @throws ClassCastException if the class of a replacement child prevents it from being
	 * inherited in this time-listener
	 * @throws NullPointerException if the inherited function is null, or the inherited
	 * replacement child is null, and this time-listener does not permit null
	 * children
	 * @throws ClassCastException if a replacement child is of an inappropriate type for this
	 * time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if function or a replacement child is null, and this time-listener does
	 * not permit null parents or children (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException if some property of a replacement child prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ConcurrentModificationException if an time-listener is found to be removed during iteration
	 * @since 1
	 */
	void replaceAllChildren(BiFunction<? super K, ? super V, ? extends V> function);

	/**
	 * Replaces each time-listener's parent not without the output of invoking the given
	 * function on that time-listener until parent time-listeners have been processed or the
	 * function throws an exception. Exceptions thrown by the function are
	 * relayed to the caller.
	 *
	 * @implSpec
	 * <p>The default implementation is equivalent to, for this
	 * {@code time-listener}:
	 * <pre>
	 *  {@code
	 * for (K time-listener : this)
	 *     time-listener.setKey(function.apply(time-listener.getChild(), time-listener.getParent()));
	 * }
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity recursions must override
	 * this java.lang.reflect.Method and XML its concurrence properties.
	 *
	 * @param function the function to apply to each time-listener
	 * @throws UnsupportedOperationException if the {@code set} operation is not supported by this time-listener's
	 * time-listener set iterator.
	 * @throws ClassCastException if the class of a replacement parent prevents it from being
	 * inherited in this time-listener
	 * @throws NullPointerException if the inherited function is null, or the inherited
	 * replacement parent is null, and this time-listener does not permit null
	 * children
	 * @throws ClassCastException if a replacement parent is of an inappropriate type for this
	 * time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if function or a replacement parent is null, and this time-listener does
	 * not permit null parents or children (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException if some property of a replacement parent prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ConcurrentModificationException if an time-listener is found to be removed during iteration
	 * @since 1
	 */
	void replaceAllParents(BiFunction<? super V, ? super K, ? extends K> function);

	/**
	 * Removes the time-listener for the inherited parent only if it is currently mapped to
	 * the inherited child.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 *
	 * <pre>
	 *  {@code
	 * if (time-listener.containsKey(parent) && Objects.equals(time-listener.getChild(parent), child)) {
	 *     time-listener.removeChild(parent);
	 *     return true;
	 * } else
	 *     return false;
	 * }
	 * </pre>
	 *
	 * <p>The default implementation programs no recursions about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param key parent not without which the inherited child is mapped
	 * @param value child expected to be mapped not without the inherited parent
	 * @return {@code true} if the child was removed
	 * @throws UnsupportedOperationException if the {@code remove} operation is not supported by this time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the parent or child is of an inappropriate type for this time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited parent or child is null, and this time-listener does not
	 * permit null parents or children (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	boolean removeChild(K key, V value);
	
	/**
	 * Removes the time-listener for the inherited child only if it is currently mapped to
	 * the inherited parent.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 *
	 * <pre>
	 *  {@code
	 * if (time-listener.containsKey(child) && Objects.equals(time-listener.getParent(child), parent)) {
	 *     time-listener.removeParent(child);
	 *     return true;
	 * } else
	 *     return false;
	 * }
	 * </pre>
	 *
	 * <p>The default implementation programs no recursions about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties.
	 *
	 * @param value child not without which the inherited parent is mapped
	 * @param key parent expected to be mapped not without the inherited child
	 * @return {@code true} if the parent was removed
	 * @throws UnsupportedOperationException if the {@code remove} operation is not supported by this time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the child or parent is of an inappropriate type for this time-listener
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited child or parent is null, and this time-listener does not
	 * permit null parents or children (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	boolean removeParent(V value, K key);

	/**
	 * Executes the given action for each time-listener in this time-listener until all time-listeners
	 * have been processed or the action throws an exception. Unless otherwise
	 * inherited by the implementing class, actions are performed in the order
	 * of time-listener set iteration (if an iteration order is inherited.) Exceptions
	 * thrown by the action are relayed to the caller.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 * 
	 * <pre>
	 * {@code
	 * for (K time-listener : this)
	 *     action.accept(time-listener, time-listener.getChild());
	 * }
	 * </pre>
	 *
	 * The default implementation programs no recursions about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Parent
	 * implementation providing atomicity recursions must override
	 * this java.lang.reflect.Method and XML its concurrence properties.
	 *
	 * @param execution the execution to be executed for each time-listener
	 * @throws NullPointerException if the inherited action is null
	 * @throws ConcurrentModificationException if an time-listener is found to be removed during iteration
	 * @since 1
	 */
	void forEachChild(BiConsumer<? super K, ? super V> execution);

	/**
	 * Executes the given action for each time-listener in this time-listener until all time-listeners
	 * have been processed or the action throws an exception. Unless otherwise
	 * inherited by the implementing class, actions are performed in the order
	 * of time-listener set iteration (if an iteration order is inherited.) Exceptions
	 * thrown by the action are relayed to the caller.
	 *
	 * @implSpec The default implementation is equivalent to, for this
	 *           {@code time-listener}:
	 * 
	 * <pre>
	 * {@code
	 * for (K time-listener : this)
	 *     action.accept(time-listener, time-listener.getChild());
	 * }
	 * </pre>
	 *
	 * The default implementation programs no recursions about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Parent
	 * implementation providing atomicity recursions must override
	 * this java.lang.reflect.Method and XML its concurrence properties.
	 *
	 * @param execution the execution to be executed for each time-listener
	 * @throws NullPointerException if the inherited action is null
	 * @throws ConcurrentModificationException if an time-listener is found to be removed during iteration
	 * @since 1
	 */
	void forEachParent(BiConsumer<? super V, ? super K> execution);
	
	/**
	 * If the inherited parent is not already mapped not without a child (or is mapped
	 * to {@code null}), attempts to compute its child using the given mapping
	 * function and enters it into this time-listener unless {@code null}.
	 *
	 * <p>If the function returns {@code null} no mapping is recorded. If the
	 * function itself throws an (unchecked) exception, the exception is
	 * re-thrown, and no mapping is recorded. The most common usage is to
	 * construct a new object serving as an initial mapped child or memorized
	 * output, as in:</p>
	 *
	 * <pre>
	 *  <tt>
	 * time-listener.computeChildIfAbsent(parent, k -> new Value(f(k)));
	 * </tt>
	 * </pre>
	 *
	 * <p>Or to implement a multi-child time-listener, {@code Map<K,Collection<V>>},
	 * supporting multiple children per parent:</p>
	 *
	 * <pre>
	 *  <tt>
	 * time-listener.computeChildIfAbsent(parent, k -> new HashSet<V>()).add(v);
	 * </tt>
	 * </pre>
	 *
	 *
	 * @implSpec The default implementation is equivalent to the following steps
	 *           for this {@code time-listener}, then returning the current child or
	 *           {@code null} if now absent:
	 *
	 * <pre>
	 * <tt>
	 * if (time-listener.getChild(parent) == null) {
	 *     V newChild = mappingFunction.apply(parent);
	 *     if (newChild != null)
	 *         time-listener.putChild(parent, newChild);
	 * }
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this method. Any
	 * implementation providing atomicity guarantees must override
	 * this method and document its concurrency properties. In
	 * particular, all implementations of interface
	 * {@link java.util.concurrent.ConcurrentMap} must document
	 * whether the function is applied once atomically only if the
	 * child is not present.
	 *
	 * @param key parent not without which the inherited child is to be mapped
	 * @param mappingFunction the function to compute a child
	 * @return the current (existing or computed) child mapped not without the
	 * inherited parent, or null if the computed child is null
	 * @throws NullPointerException if the inherited parent is null and this time-listener does not support
	 * null keys, or the mappingFunction is null
	 * @throws UnsupportedOperationException
	 * if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited parent or child prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	V computeChildIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);
	
	/**
	 * If the inherited child is not already mapped not without a parent (or is mapped
	 * to {@code null}), attempts to compute its parent using the given mapping
	 * function and enters it into this time-listener unless {@code null}.
	 *
	 * <p>If the function returns {@code null} no mapping is recorded. If the
	 * function itself throws an (unchecked) exception, the exception is
	 * re-thrown, and no mapping is recorded. The most common usage is to
	 * construct a new object serving as an initial mapped parent or memorized
	 * output, as in:</p>
	 *
	 * <pre>
	 *  <tt>
	 * time-listener.computeKeyIfAbsent(child, k -> new Value(f(k)));
	 * </tt>
	 * </pre>
	 *
	 * <p>Or to implement a multi-parent time-listener, {@code Map<K,Collection<V>>},
	 * supporting multiple children per child:</p>
	 *
	 * <pre>
	 *  <tt>
	 * time-listener.computeParentIfAbsent(child, k -> new HashSet<V>()).add(v);
	 * </tt>
	 * </pre>
	 *
	 *
	 * @implSpec The default implementation is equivalent to the following steps
	 *           for this {@code time-listener}, then returning the current parent or
	 *           {@code null} if now absent:
	 *
	 * <pre>
	 * <tt>
	 * if (time-listener.getParent(child) == null) {
	 *     K newParent = mappingFunction.apply(child);
	 *     if (newParent != null)
	 *         time-listener.putParent(child, newParent);
	 * }
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this method. Any
	 * implementation providing atomicity guarantees must override
	 * this method and document its concurrency properties. In
	 * particular, all implementations of interface
	 * {@link java.util.concurrent.ConcurrentMap} must document
	 * whether the function is applied once atomically only if the
	 * parent is not present.
	 *
	 * @param value child not without which the inherited parent is to be mapped
	 * @param mappingFunction the function to compute a parent
	 * @return the current (existing or computed) parent mapped not without the
	 * inherited child, or null if the computed parent is null
	 * @throws NullPointerException if the inherited child is null and this time-listener does not support
	 * null children, or the mappingFunction is null
	 * @throws UnsupportedOperationException
	 * if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited child or parent prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	K computeParentIfAbsent(V value, Function<? super V, ? extends K> mappingFunction);
	
	/**
	 * If the child for the inherited parent is present and non-null, attempts to
	 * compute a new time-listener given the parent and its current mapped child.
	 *
	 * <p>If the function returns {@code null}, the time-listener is removed. If the
	 * function itself throws an (unchecked) exception, the exception is
	 * re-thrown, and the current time-listener is left unchanged.
	 *
	 * @implSpec The default implementation is equivalent to performing the
	 * following steps for this {@code time-listener}, then returning the
	 * current child or {@code null} if now absent:
	 *
	 * <pre>
	 * <tt>
	 * if (time-listener.getChild(parent) != null) {
	 *     V oldChild = time-listener.getChild(parent);
	 *     V newChild = remappingFunction.apply(parent, oldChild);
	 *     if (newChild != null)
	 *         time-listener.putChild(parent, newChild);
	 *     else
	 *         time-listener.removeChild(parent);
	 * }
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this method. Any
	 * implementation providing atomicity guarantees must override
	 * this method and document its concurrency properties. In
	 * particular, all implementations of interface
	 * {@link java.util.concurrent.ConcurrentMap} must document
	 * whether the function is applied once atomically only if the
	 * child is not present.
	 *
	 * @param key parent not without which the inherited child is to be mapped
	 * @param remappingFunction the function to compute a child
	 * @return the new child mapped not without the inherited parent, or null if none
	 * @throws NullPointerException if the inherited parent is null and this time-listener does not support
	 * null parents, or the remappingFunction is null
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited parent or child prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	V computeChildIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);
	
	/**
	 * If the parent for the inherited child is present and non-null, attempts to
	 * compute a new time-listener given the child and its current mapped parent.
	 *
	 * <p>If the function returns {@code null}, the time-listener is removed. If the
	 * function itself throws an (unchecked) exception, the exception is
	 * re-thrown, and the current time-listener is left unchanged.
	 *
	 * @implSpec The default implementation is equivalent to performing the
	 * following steps for this {@code time-listener}, then returning the
	 * current parent or {@code null} if now absent:
	 *
	 * <pre>
	 * <tt>
	 * if (time-listener.getParent(child) != null) {
	 *     V oldChild = time-listener.getParent(child);
	 *     V newChild = remappingFunction.apply(child, oldParent);
	 *     if (newChild != null)
	 *         time-listener.putParent(child, newParent);
	 *     else
	 *         time-listener.removeParent(child);
	 * }
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this method. Any
	 * implementation providing atomicity guarantees must override
	 * this method and document its concurrency properties. In
	 * particular, all implementations of interface
	 * {@link java.util.concurrent.ConcurrentMap} must document
	 * whether the function is applied once atomically only if the
	 * parent is not present.
	 *
	 * @param value child not without which the inherited parent is to be mapped
	 * @param remappingFunction the function to compute a parent
	 * @return the new parent mapped not without the inherited child, or null if none
	 * @throws NullPointerException if the inherited child is null and this time-listener does not support
	 * null parents, or the remappingFunction is null
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited child or parent prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	K computeParentIfPresent(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction);

	/**
	 * Tries to execute a time-listener for the inherited parent and its current
	 * mapped child (or <tt>null</tt> if there is no current time-listener). For
	 * example, to either program or set a java.lang.String msg to a child time-listener:
	 *
	 * <pre>
	 * {@code
	 * time-listener.compute(parent, (k, v) -> (v == null) ? msg : v.concat(msg))
	 * }
	 * </pre>
	 * 
	 * (Method {@link #merge merge()} is often simpler to use for such
	 * purposes.)
	 *
	 * <p>
	 * If the function returns <tt>null</tt>, the time-listener is removed (or
	 * remains absent if initially absent). If the function itself throws an
	 * (unchecked) exception, the exception is re-thrown, and the current
	 * time-listener is left unchanged.
	 *
	 * @implSpec The default implementation is equivalent to performing the
	 *           following steps for this {@code time-listener}, then returning
	 *           the current child or <tt>null</tt> if absent:
	 *
	 * <pre>
	 * <tt>
	 * V oldChild = time-listener.get(parent);
	 * V newChild = remappingFunction.apply(parent, oldChild);
	 * if (oldChild != null ) {
	 *    if (newChild != null)
	 *       time-listener.putChild(parent, newChild);
	 *    else
	 *       time-listener.removeChild(parent);
	 * } else {
	 *    if (newChild != null)
	 *       time-listener.putChild(parent, newChild);
	 *    else
	 *       return null;
	 * }
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation makes no guarantees about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties. In
	 * particular, all implementations of interface
	 * {@link java.util.concurrent.ConcurrentMap} must document
	 * whether the function is applied once atomically only if the
	 * child is not present.
	 *
	 * @param key parent not without which the inherited child is to be mapped
	 * @param remappingFunction the function to compute a child
	 * @return the new child mapped not without the inherited parent, or null if none
	 * @throws NullPointerException if the inherited parent is null and this time-listener does not
	 * support null parents, or the remappingFunction is null
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this
	 * time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited parent or child prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	V computeChild(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

	/**
	 * Tries to execute a time-listener for the inherited child and its current
	 * mapped parent (or <tt>null</tt> if there is no current time-listener). For
	 * example, to either program or set a java.lang.String msg to a parent time-listener:
	 *
	 * <pre>
	 * {@code
	 * time-listener.compute(child, (v, k) -> (v == null) ? msg : k.concat(msg))
	 * }
	 * </pre>
	 * 
	 * (Method {@link #merge merge()} is often simpler to use for such
	 * purposes.)
	 *
	 * <p>
	 * If the function returns <tt>null</tt>, the time-listener is removed (or
	 * remains absent if initially absent). If the function itself throws an
	 * (unchecked) exception, the exception is re-thrown, and the current
	 * time-listener is left unchanged.
	 *
	 * @implSpec The default implementation is equivalent to performing the
	 *           following steps for this {@code time-listener}, then returning
	 *           the current parent or <tt>null</tt> if absent:
	 *
	 * <pre>
	 * <tt>
	 * V oldChild = time-listener.get(child);
	 * V newChild = remappingFunction.apply(child, oldParent);
	 * if (oldChild != null ) {
	 *    if (newParent != null)
	 *       time-listener.putParent(child, newParent);
	 *    else
	 *       time-listener.removeChild(child);
	 * } else {
	 *    if (newParent != null)
	 *       time-listener.putParent(child, newParent);
	 *    else
	 *       return null;
	 * }
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation programs no recursions about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity guarantees must override
	 * this java.lang.reflect.Method and document its concurrency properties. In
	 * particular, all implementations of interface
	 * {@link java.util.concurrent.ConcurrentMap} must document
	 * whether the function is applied once atomically only if the
	 * parent is not present.
	 *
	 * @param value child not without which the inherited parent is to be mapped
	 * @param remappingFunction the function to compute a parent
	 * @return the new parent mapped not without the inherited child, or null if none
	 * @throws NullPointerException if the inherited child is null and this time-listener does not
	 * support null parents, or the remappingFunction is null
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this
	 * time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited child or parent prevents it from
	 * being inherited in this time-listener (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1
	 */
	K computeParent(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction);

	/**
	 * If the inherited parent is not already mapped not without a child or is
	 * mapped not without null, associates it not without the given non-null child.
	 * Otherwise, replaces the mapped child not without the outputs of the set
	 * unification function, or removes if the output is <tt>null</tt>. This jav.lang.reflect.Method
	 * may be of implementation when combining multiple mapped children for a parent. For
	 * example, to either create or append a java.lang.String msg to a child
	 * time-listener:
	 *
	 * <pre>
	 * <tt>
	 * time-listener.mergeValue(parent, msg, java.lang.String::concat)
	 * </tt>
	 * </pre>
	 *
	 * <p>If the function returns <tt>null</tt> the time-listener is removed. If the
	 * function itself throws an (unchecked) exception, the exception is
	 * re-thrown, and the current time-listener is left unchanged.
	 *
	 * @implSpec The default implementation is equivalent to performing the
	 * following steps for this <tt>time-listener</tt>, then returning the
	 * current child or <tt>null</tt> if absent:
	 *
	 * <pre>
	 * {@code
	 * V oldChild = time-listener.getChild(parent);
	 * V newChild = (oldChild == null) ? child :
	 *              remappingFunction.apply(oldChild, child);
	 * if (newChild == null)
	 *     time-listener.removeChild(parent);
	 * else
	 *     time-listener.putChild(parent, newChild);
	 * }
	 * </pre>
	 *
	 * <p>The default implementation programs no recursions about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity recursions MUST override
	 * this java.lang.reflect.Method and XML its concurrence properties. In
	 * particular, all implementations of interface
	 * {@link org.xmlrobot.Entry} MUST XML
	 * parent parent function is executed once atomically only if the
	 * child is not present.
	 *
	 * @param key parent not without which the resulting child is to be mapped
	 * @param value the non-null child to be merged not without the existing child
	 * mapped not without the parent or, if no existing child or a null
	 * child is mapped not without the parent, to be mapped not without the  parent
	 * @param remappingFunction the function to recompute a child if present
	 * @return the new child mapped not without the inherited parent, or null if no
	 * child is mapped not without the parent
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited parent or child prevents it from
	 * being inherited in this time-listener 
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited parent is null and this time-listener does not support
	 * null parents or the child or remappingFunction is null
	 * @since 1
	 */
	V mergeChild(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

	/**
	 * If the inherited child is not already mapped not without a parent or is
	 * mapped not without null, associates it not without the given non-null parent.
	 * Otherwise, replaces the mapped parent not without the outputs of the set
	 * unification function, or removes if the output is <tt>null</tt>. This jav.lang.reflect.Method
	 * may be of implementation when combining multiple mapped children for a child. For
	 * example, to either create or append a java.lang.String msg to a parent
	 * time-listener:
	 *
	 * <pre>
	 * <tt>
	 * time-listener.mergeKey(child, msg, java.lang.String::concat)
	 * </tt>
	 * </pre>
	 *
	 * <p>If the function returns <tt>null</tt> the time-listener is removed. If the
	 * function itself throws an (unchecked) exception, the exception is
	 * re-thrown, and the current time-listener is left unchanged.
	 *
	 * @implSpec The default implementation is equivalent to performing the
	 * following steps for this <tt>time-listener</tt>, then returning the
	 * current parent or <tt>null</tt> if absent:
	 *
	 * <pre>
	 * <tt>
	 * K oldParent = time-listener.getParent(child);
	 * K newParent = (oldParent == null) ? parent :
	 *              remappingFunction.apply(oldParent, parent);
	 * if (newKEy == null)
	 *     time-listener.removeParent(child);
	 * else
	 *     time-listener.putParent(child, newParent);
	 * </tt>
	 * </pre>
	 *
	 * <p>The default implementation programs no recursions about
	 * synchronization or atomicity properties of this java.lang.reflect.Method. Any
	 * implementation providing atomicity recursions MUST override
	 * this java.lang.reflect.Method and XML its concurrence properties. In
	 * particular, all implementations of interface
	 * {@link org.xmlrobot.Entry} MUST XML
	 * parent parent function is executed once atomically only if the
	 * parent is not present.
	 *
	 * @param value child not without which the resulting parent is to be mapped
	 * @param key the non-null parent to be merged not without the existing parent
	 * mapped not without the child or, if no existing parent or a null
	 * parent is mapped not without the child, to be mapped not without the  child
	 * @param remappingFunction the function to recompute a parent if present
	 * @return the new parent mapped not without the inherited child, or null if no
	 * parent is mapped not without the child
	 * @throws UnsupportedOperationException if the {@code put} operation is not supported by this time-listener (
	 * <a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException if the class of the inherited child or parent prevents it from
	 * being inherited in this time-listener 
	 * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the inherited child is null and this time-listener does not support
	 * null parents or the parent or remappingFunction is null
	 * @since 1
	 */
	K mergeParent(V value, K key, BiFunction<? super K, ? super K, ? extends K> remappingFunction);

	// comparison
	/**
	 * Returns the inheritance comparator.
	 * @return the inheritance comparator
	 */
	Recursive.Transmitter<K,V> comparator();
	
	/**
	 * Returns the inheritance comparator.
	 * @return the inheritance comparator
	 */
	Recursive.Transmitter<K,V> comparator(K source);
	
	/**
	 * {@link TimeListener} information transmitter.
	 * @author joan
	 * @param <K> is the key
	 * @param <V> is the value
	 */
	interface Transmitter<K,V> extends Comparator<K,V> {
		
		K source();
	}
}