package hyperspace.recurrent;

import hyperspace.time.Recursive;

public interface Map<K,V>
	extends Recursive<Map<K,V>,Map<K,V>>, 
		java.util.Map.Entry<K,V>, java.util.Map<K,V> {

	/**
     * Replaces the key corresponding to this entry with the specified
     * key (optional operation).  (Writes through to the map.)  The
     * behavior of this call is undefined if the mapping has already been
     * removed from the map (by the iterator's {@code remove} operation).
     *
     * @param key new key to be stored in this entry
     * @return old key corresponding to the entry
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by the backing map
     * @throws ClassCastException if the class of the specified key
     *         prevents it from being stored in the backing map
     * @throws NullPointerException if the backing map does not permit
     *         null values, and the specified key is null
     * @throws IllegalArgumentException if some property of this key
     *         prevents it from being stored in the backing map
     * @throws IllegalStateException implementations may, but are not
     *         required to, throw this exception if the entry has been
     *         removed from the backing map.
     */
    K setKey(K key);
}