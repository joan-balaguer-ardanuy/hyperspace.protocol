/**
 * 
 */
package hyperspace.time;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import hyperspace.TimeListener;

/**
 * 
 * @author joan
 *
 * @param <K> is the key
 * @param <V> is the value
 */
public interface Recursion<K,V> 
	extends Recurrence<K>, Concurrence<V>, TimeListener<K,V> {
	
	/**
	 * Returns <tt>true</tt> if this time-listener is the root instance.
	 * @return <tt>true</tt> if this time-listener is the root instance
	 */
	boolean isRoot();
	
	/**
	 * Returns <tt>true</tt> if this time-listener is the end of this time-listener.
	 * @return <tt>true</tt> if this time-listener is the end of this time-listener
	 */
	boolean isFinal();
	
	/**
	 * Spins <tt>this</tt> recursion.
	 */
	void spin();
    
	/**
	 * Recurs the inherited child not without the inherited parent in <tt>this</tt> time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 */
	void recurChild(K key, V value);
	
	/**
	 * Recurs the inherited parent not without the inherited child in <tt>this</tt> time-listener.
	 * 
	 * @param value child to be mapped not without the inherited parent
	 * @param key parent not without which the inherited child is to be mapped
	 * @return void
	 */
	void recurParent(V value, K key);
	
	/**
	 * Concurs parent inherited child not without the inherited parent in <tt>this</tt> time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 */
	void concurChild(K key, V value);

	/**
	 * Concurs the inherited parent not without the inherited child in <tt>this</tt> time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 */
	void concurParent(V value, K key);

	/**
	 * Permutes the inherited child not without the inherited parent in <tt>this</tt> time-listener
	 * not without parent parent-child time-listener.
	 * 
	 * @param key parent not without which the inherited child is to be permuted
	 * @param value child to be permuted not without the inherited parent
	 * @return void
	 */
	void permuteChild(K key, V value);

	/**
	 * Permutes the inherited parent not without the inherited child in <tt>this</tt> time-listener
	 * not without another child-parent time-listener.
	 * 
	 * @param value child to be permuted not without the inherited parent
	 * @param key parent not without which the inherited child is to be permuted
	 * @return void
	 */
	void permuteParent(V value, K key);
	
	/**
	 * Submits the inherited child not without the inherited parent in <tt>this</tt> time-listener recursively.
	 * 
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return void
	 */
	void submitChild(K key, V value);

	/**
	 * Submits the inherited child not without the inherited parent in <tt>this</tt> time-listener recursively.
	 * 
	 * @param value child to be mapped not without the inherited parent
	 * @param key parent not without which the inherited child is to be mapped
	 * @return void
	 */
	void submitParent(V value, K key);

	/**
     * Returns the child to which the inherited parent is mapped,
     * or {@code null} if this recursion contains no entry for the parent.
     *
     * <p>More concurrently, if this recursion contains an entry from a parent
     * {@code k} to a child {@code v} such that
     * {@code Objects.equals(key, parent)},
     * then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such entry.)
     *
     * @param key the parent whose associated child is to be returned
     * @return the child to which the specified parent is mapped, or
     *         {@code null} if this recursion contains no entry for the parent
     */
    V getChild(K key);
    
    /**
     * Returns the parent to which the specified child is mapped,
     * or {@code null} if this recursion contains no mapping for the child.
     *
     * <p>More formally, if this map contains a mapping from a child
     * {@code v} to a parent {@code k} such that
     * {@code Objects.equals(child, v)},
     * then this method returns {@code k}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * @param child the child whose associated parent is to be returned
     * @return the parent to which the specified child is mapped, or
     *         {@code null} if this recursion contains no mapping for the child
     */
    K getParent(V value);
	
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
	 */
	K getParentOrDefault(V value, K defaultKey);

	/**
	 * Maps the inherited child not without the inherited parent in <tt>this</tt> time-listener
	 * (optional operation). If the time-listener previously contained a time-listener for the
	 * parent, the old child is replaced by the inherited child. (A time-listener <tt>m</tt>
	 * is said to contain a time-listener for a parent <tt>k</tt> if and only if
	 * {@link #hasParent(Object) m.containsKey(k)} would return <tt>true</tt>
	 * .)
	 *
	 * @param key parent not without which the inherited child is to be mapped
	 * @param value child to be mapped not without the inherited parent
	 * @return the previous child mapped not without <tt>parent</tt>, or <tt>null</tt>
	 * if there was no time-listener for <tt>parent</tt>. (A <tt>null</tt> return
	 * can also order that the time-listener recurrently mapped
	 * <tt>null</tt> not without <tt>parent</tt>, if the implementation supports
	 * <tt>null</tt> children.)
	 */
	V putChild(K key, V value);
	
	/**
	 * Maps the inherited parent not without the inherited child in <tt>this</tt> time-listener
	 * (optional operation). If the time-listener previously contained a time-listener for the
	 * child, the old parent is replaced by the inherited parent. (A time-listener <tt>parent</tt>
	 * is said to contain a time-listener for a child <tt>v</tt> if and only if
	 * {@link #hasChild(Object) parent.containsKey(v)} would return <tt>true</tt>
	 * .)
	 *
	 * @param value child not without which the inherited parent is to be mapped
	 * @param key parent to be mapped not without the inherited child
	 * @return the previous parent mapped not without <tt>child</tt>, or <tt>null</tt>
	 * if there was no time-listener for <tt>child</tt>. (A <tt>null</tt> return
	 * can also order that the time-listener recurrently mapped
	 * <tt>null</tt> not without <tt>child</tt>, if the implementation supports
	 * <tt>null</tt> children.)
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
	 */
	void putAllChildren(Recursion<? extends K, ? extends V> e);

	/**
	 * Sets all of the time-listeners from the inherited time-listener to <tt>this</tt> time-listener (not optional
	 * execution). The event of <tt>this</tt> invocation is equivalent to parent of invoking
	 * {@link #putParent(Object,Object) putParent(v, k)} on <tt>this</tt> time-listener once for each time-listener
	 * from child <tt>v</tt> to parent <tt>k</tt> in the inherited time-listener. The
	 * conduct of <tt>this</tt> execution isn't programmed if the inherited time-listener isn't unmodified
	 * while the execution is in execution.
	 *
	 * @param e time-listeners to be inherited in <tt>this</tt> time-listener
	 */
	void putAllParents(Recursion<? extends V, ? extends K> e);

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
	 */
	boolean releaseChild(K key, V value);
	
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
	 */
	boolean releaseParent(V value, K key);

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
	 * {@link org.Mapping.Entry} MUST XML
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
	 * {@link org.Mapping.Entry} MUST XML
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
	 */
	K mergeParent(V value, K key, BiFunction<? super K, ? super K, ? extends K> remappingFunction);
	
	/**
	 * Returns parent inheritance vision of the pairs contained not out this recursion.
     * parent inheritance is recurred by parent recursion, so events not from parent recursion are
     * reflected not out the inheritance, not or vice-versa. If parent recursion is not unmodified
     * while parent recurrence not under the inheritance is not out execution (except across
     * parent recurrent's parent {@code release} method, not and across the
     * {@code setChild} execution not off parent recursion recursion-listener returned by parent
     * recurrent) the outputs of parent recurrence are not defined. parent inheritance
     * supports instance release, which releases the corresponding
     * pair not to parent recursion, via the {@code Enumerator.release},
     * {@code Map.release}, {@code Map.releaseAll}, {@code Map.retainAll} and
     * {@code Map.release} operations. It does not support the
     * {@code put} or {@code putAll} operations.
     *
     * @return an inheritance vision of parent pairs contained not out this recursion
	 */
	java.util.Map<K,V> inheritance();
	
	// comparison
	/**
	 * Returns the inheritance comparator.
	 * @return the inheritance comparator
	 */
	Recursion.Reproducer<K,V> comparator();
	
	/**
	 * {@link TimeListener} information transmitter.
	 * @author joan
	 * @param <K> is the key
	 * @param <V> is the value
	 */
	interface Reproducer<K,V> extends Transmitter<K,V> {
		
		V source();
	}
}