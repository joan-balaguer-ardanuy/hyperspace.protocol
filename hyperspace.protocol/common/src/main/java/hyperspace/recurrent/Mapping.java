package hyperspace.recurrent;

public interface Mapping<K,V> extends java.util.Map.Entry<K,V> {

	/**
     * Replaces the key corresponding to this entry with the specified
     * key (optional operation).  (Writes through to the map.)  The
     * behavior of this call is undefined if the mapping has already been
     * removed from the map (by the iterator's {@code remove} operation).
     *
     * @param key new key to be stored in this entry
     * @return old key corresponding to the entry
     */
    K setKey(K key);
    
}
