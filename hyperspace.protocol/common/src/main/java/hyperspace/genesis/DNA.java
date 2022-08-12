/**
 * 
 */
package hyperspace.genesis;

import hyperspace.Entry;

/**
 * @author joan
 *
 */
public interface DNA<K,V> extends Entry<K,V>, Iterable<K> {

	default Chain<V,K> entryChain() {
		return (Chain<V,K>) getChild();
	}

	/**
	 * Returns {@code true} if this map contains no key-value mappings.
	 *
	 * @return {@code true} if this map contains no key-value mappings
	 */
	boolean isEmpty();

	/**
	 * Returns {@code true} if this map contains a mapping for the specified key.
	 * More formally, returns {@code true} if and only if this map contains a
	 * mapping for a key {@code k} such that {@code Objects.equals(key, k)}. (There
	 * can be at most one such mapping.)
	 *
	 * @param key key whose presence in this map is to be tested
	 * @return {@code true} if this map contains a mapping for the specified key
	 * @throws ClassCastException   if the key is of an inappropriate type for this
	 *                              map (<a href=
	 *                              "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified key is null and this map does
	 *                              not permit null keys (<a href=
	 *                              "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	boolean hasKey(K key);

	/**
	 * Returns {@code true} if this map maps one or more keys to the specified
	 * value. More formally, returns {@code true} if and only if this map contains
	 * at least one mapping to a value {@code v} such that
	 * {@code Objects.equals(value, v)}. This operation will probably require time
	 * linear in the map size for most implementations of the {@code Map} interface.
	 *
	 * @param value value whose presence in this map is to be tested
	 * @return {@code true} if this map maps one or more keys to the specified value
	 * @throws ClassCastException   if the value is of an inappropriate type for
	 *                              this map (<a href=
	 *                              "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified value is null and this map does
	 *                              not permit null values (<a href=
	 *                              "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	boolean hasValue(V value);

	/**
	 * Returns the value to which the specified key is mapped, or {@code null} if
	 * this map contains no mapping for the key.
	 *
	 * <p>
	 * More formally, if this map contains a mapping from a key {@code k} to a value
	 * {@code v} such that {@code Objects.equals(key, k)}, then this method returns
	 * {@code v}; otherwise it returns {@code null}. (There can be at most one such
	 * mapping.)
	 *
	 * <p>
	 * If this map permits null values, then a return value of {@code null} does not
	 * <i>necessarily</i> indicate that the map contains no mapping for the key;
	 * it's also possible that the map explicitly maps the key to {@code null}. The
	 * {@link #containsKey containsKey} operation may be used to distinguish these
	 * two cases.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or {@code null} if
	 *         this map contains no mapping for the key
	 * @throws ClassCastException   if the key is of an inappropriate type for this
	 *                              map (<a href=
	 *                              "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified key is null and this map does
	 *                              not permit null keys (<a href=
	 *                              "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	V get(K key);

	// Modification Operations

	/**
	 * Associates the specified value with the specified key in this map (optional
	 * operation). If the map previously contained a mapping for the key, the old
	 * value is replaced by the specified value. (A map {@code m} is said to contain
	 * a mapping for a key {@code k} if and only if {@link #hasKey(Object)
	 * m.containsKey(k)} would return {@code true}.)
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with {@code key}, or {@code null} if
	 *         there was no mapping for {@code key}. (A {@code null} return can also
	 *         indicate that the map previously associated {@code null} with
	 *         {@code key}, if the implementation supports {@code null} values.)
	 * @throws UnsupportedOperationException if the {@code put} operation is not
	 *                                       supported by this map
	 * @throws ClassCastException            if the class of the specified key or
	 *                                       value prevents it from being stored in
	 *                                       this map
	 * @throws NullPointerException          if the specified key or value is null
	 *                                       and this map does not permit null keys
	 *                                       or values
	 * @throws IllegalArgumentException      if some property of the specified key
	 *                                       or value prevents it from being stored
	 *                                       in this map
	 */
	V put(K key, V value);

	/**
	 * Removes the mapping for a key from this map if it is present (optional
	 * operation). More formally, if this map contains a mapping from key {@code k}
	 * to value {@code v} such that {@code Objects.equals(key, k)}, that mapping is
	 * removed. (The map can contain at most one such mapping.)
	 *
	 * <p>
	 * Returns the value to which this map previously associated the key, or
	 * {@code null} if the map contained no mapping for the key.
	 *
	 * <p>
	 * If this map permits null values, then a return value of {@code null} does not
	 * <i>necessarily</i> indicate that the map contained no mapping for the key;
	 * it's also possible that the map explicitly mapped the key to {@code null}.
	 *
	 * <p>
	 * The map will not contain a mapping for the specified key once the call
	 * returns.
	 *
	 * @param key key whose mapping is to be removed from the map
	 * @return the previous value associated with {@code key}, or {@code null} if
	 *         there was no mapping for {@code key}.
	 * @throws UnsupportedOperationException if the {@code remove} operation is not
	 *                                       supported by this map
	 * @throws ClassCastException            if the key is of an inappropriate type
	 *                                       for this map (<a href=
	 *                                       "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException          if the specified key is null and this
	 *                                       map does not permit null keys (<a href=
	 *                                       "{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	V remove(K key);

	/**
	 * Removes all of the mappings from this map (optional operation). The map will
	 * be empty after this call returns.
	 *
	 * @throws UnsupportedOperationException if the {@code clear} operation is not
	 *                                       supported by this map
	 */
	void clear();
}