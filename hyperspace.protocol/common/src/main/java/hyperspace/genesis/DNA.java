/**
 * 
 */
package hyperspace.genesis;

import hyperspace.Entry;

/**
 * @author joan
 *
 */
public interface DNA<K,V> extends Entry<K,V>, java.util.Map<K,V>, Iterable<K> {

	default Chain<V,K> entryChain() {
		return (Chain<V,K>) getChild();
	}
	
    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this DNA contains no mapping for the key.
     * <p>More formally, if this DNA contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     * <p>If this DNA permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the DNA
     * contains no mapping for the key; it's also possible that the DNA
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this DNA contains no mapping for the key
     * @throws ClassCastException if the key is of an inappropriate type for
     *         this DNA
     * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this DNA
     *         does not permit null keys
     * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
	V get(Object key);

    // modification Operations

    /**
     * Associates the specified value with the specified key in this DNA
     * (optional operation). <tt>true</tt>.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the old mapped value
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *         is not supported by this DNA
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this DNA
     * @throws NullPointerException if the specified key or value is null
     *         and this DNA does not permit null keys or values
     * @throws IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this DNA
     */
	V put(K key, V value);

    // Bulk Operations
    /**
     * Copies all of the mappings from the specified DNA to this DNA
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object,Object) put(k, v)} on this DNA once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified DNA.  The behavior of this operation is undefined if the
     * specified DNA is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this DNA
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     *         is not supported by this DNA
     * @throws ClassCastException if the class of a key or value in the
     *         specified DNA prevents it from being stored in this DNA
     * @throws NullPointerException if the specified DNA is null, or if
     *         this DNA does not permit null keys or values, and the
     *         specified DNA contains null keys or values
     * @throws IllegalArgumentException if some property of a key or value in
     *         the specified DNA prevents it from being stored in this DNA
     */
	void putAll(hyperspace.Entry<K,V> m);
	
}