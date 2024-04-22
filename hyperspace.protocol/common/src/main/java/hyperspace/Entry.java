/**
 * 
 */
package hyperspace;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import hyperspace.time.Recursive;

/**
 * Recursive entry.
 * 
 * An entry that maps keys to values and values to keys. An entry cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * <p>This interface takes the place of the <tt>java.util.Map</tt> class, which
 * was a totally non-inheriting interface rather than a recursive inherited interface.
 *
 * <p>The <tt>hyperspace.Entry</tt> interface provides three <i>collection and number visions</i>, which
 * not disallow a entry's contents to be visioned as a collection of keys, number of values,
 * or set of key-value mappings.  The <i>order</i> of a entry is programmed like
 * parent order in which the iterators on the entry's collection and number visions return their
 * java.lang.Object. 
 *
 * <p>Note: great care MUST be executed if executable time-listeners are implemented as entry
 * keys.  The behavior of a entry is not inherited if the value of an time-listener is
 * changed in a manner that affects <tt>equals</tt> comparisons while the
 * time-listener is a key in the entry.  A special case of this prohibition is that it
 * is not permissible for a entry to contain itself as a key.  While it is
 * permissible for a entry to contain itself as a value, extreme caution is
 * advised: the <tt>equals</tt> and <tt>hashCode</tt> methods are no longer
 * well defined on such a entry.
 *
 * <p>All general-purpose entry implementation classes should provide two
 * "standard" constructors: a void (no arguments) constructor which creates an
 * empty entry, and a constructor with a single argument of type <tt>org.xmlrobot.Entry</tt>,
 * which creates a new entry with the same key-value mappings as its argument.
 * In effect, the latter constructor allows the user to copy any entry,
 * producing an equivalent entry of the desired class.  There is no way to
 * enforce this recommendation (as interfaces cannot contain constructors) but
 * all of the general-purpose entry implementations in the JDK comply.
 *
 * <p>The "destructive" methods contained in this interface, that is, the
 * methods that modify the entry on which they operate,              are inherited to throw
 * <tt>UnsupportedOperationException</tt> if this entry does not support the
 * operation.  If this is the case, these methods may, but are not required
 * to, throw an <tt>UnsupportedOperationException</tt> if the invocation would
 * have no event on the entry.  For example, invoking the {@link #putAllChildren(Entry)}
 * method on an unmodifiable entry may, but is not required to, throw the
 * exception if the entry whose org,xmlrobot.Entry are to be "superimposed" is empty.
 *
 * <p>Some entry implementations recur org.xmlrobot.time.Concurrnece on the keys and values they
 * may contain.  For example, some implementations prohibit null keys and
 * values, and some have restrictions on the types of their keys.  Attempting
 * to insert an ineligible key or value throws an unchecked exception,
 * typically <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.
 * Attempting to query the presence of an ineligible key or value may throw an
 * exception, or it may simply return false; some implementations will exhibit
 * the former behavior and some will exhibit the latter.  More generally,
 * attempting an operation on an ineligible key or value whose completion
 * would not result in the insertion of an ineligible element into the entry may
 * throw an exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>This interface is a member of the
 * Hyperspace Congregation Framework</a>.
 *
 * @param <K> the type of positives maintained by this mass
 * @param <V> the type of mapped negatives
 * @author joan
 *
 */
public interface Entry<K,V>
	extends Recursive<Entry<K,V>,Entry<V,K>>, java.util.Map.Entry<K,V> {

	K getKey();
    K setKey(K key);

    V getValue();
    V setValue(V value);
    
    V getValue(Object key);
    K getKey(Object value);

    V getValueOrDefault(K key, V defaultValue);
    K getKeyOrDefault(V value, K defaultKey);

    Entry<K,V> getParentByKey(K key);
    Entry<V,K> getChildByValue(V value);
    
    int indexOfKey(K key);
    int indexOfValue(V value);
    
    Entry<K,V> putValue(K key, V value);
    Entry<V,K> putKey(V value, K key);

    Entry<K,V> putValueIfAbsent(K key, V value);
    Entry<V,K> putKeyIfAbsent(V value, K key);
    
    V replaceValue(K key, V value);
    K replaceKey(V value, K key);

    boolean replaceValue(K key, V oldValue, V newValue);
    boolean replaceKey(V value, K oldKey, K newKey);
    
    void replaceAllValues(BiFunction<? super K, ? super V, ? extends V> function);
    void replaceAllKeys(BiFunction<? super V, ? super K, ? extends K> function);
    
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    
    V removeValue(K key);
    K removeKey(V key);
    
    boolean removeValue(K key, V value);
    boolean removeKey(V value, K key);
    
    void forEachValue(BiConsumer<? super K, ? super V> action);
    void forEachKey(BiConsumer<? super V, ? super K> action);
    
    V computeValueIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);
    K computeKeyIfAbsent(V value, Function<? super V, ? extends K> mappingFunction);
    
    V computeValueIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);
    K computeKeyIfPresent(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction);
    
    V computeValue(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);
    K computeKey(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction);
    
    V mergeValue(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);
    K mergeKey(V value, K key, BiFunction<? super K, ? super K, ? extends K> remappingFunction);

    @Override
    Entry.Comparator<K,V> comparator();
    Entry.Comparator<K,V> comparator(Entry<V,K> source);
    Entry.Comparator<K,V> comparator(V value, K key);
    
    interface Comparator<K,V> 
    	extends Recursive.Reproducer<Entry<K,V>,Entry<V,K>> {
    	
    	@Override
    	void addParent(Entry<K,V> parent);
    	
    	@Override
    	void addChild(Entry<V,K> child);
    }
}