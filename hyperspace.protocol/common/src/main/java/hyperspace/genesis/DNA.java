/**
 * 
 */
package hyperspace.genesis;

import hyperspace.Entry;

/**
 * The DNA interface. Inherits {@link hyperspace.Entry} interface that it's a recursive {@link java.util.Map.Entry}.
 * It is a {@link java.util.Map} and is {@link Iterable} of its keys.
 * 
 * An object that maps keys to values. A DNA cannot contain duplicate keys;
 * each key can DNA to at most one value.
 *
 * <p>This interface takes the place of the {@code Dictionary} class, which
 * was a totally abstract class rather than an interface.
 *
 * <p>The {@code DNA} interface provides three <i>collection views</i>, which
 * allow a DNA's contents to be viewed as a set of keys, collection of values,
 * or set of key-value entries.  The <i>order</i> of a DNA is defined as
 * the order in which the iterators on the DNA's collection views return their
 * elements.  Some DNA implementations, like the {@code TreeMap} class, make
 * specific guarantees as to their order; others, like the {@code HashMap}
 * class, do not.
 *
 * <p>Note: great care must be exercised if mutable objects are used as DNA
 * keys.  The behavior of a DNA is not specified if the value of an object is
 * changed in a manner that affects {@code equals} comparisons while the
 * object is a key in the DNA.  A special case of this prohibition is that it
 * is not permissible for a DNA to contain itself as a key.  While it is
 * permissible for a DNA to contain itself as a value, extreme caution is
 * advised: the {@code equals} and {@code hashCode} methods are no longer
 * well defined on such a DNA.
 *
 * <p>All general-purpose DNA implementation classes should provide two
 * "standard" constructors: a void (no arguments) constructor which creates an
 * empty DNA, and a constructor with a single argument of type {@code DNA},
 * which creates a new DNA with the same key-value entries as its argument.
 * In effect, the latter constructor allows the user to copy any DNA,
 * producing an equivalent DNA of the desired class.  There is no way to
 * enforce this recommendation (as interfaces cannot contain constructors) but
 * all of the general-purpose DNA implementations in the JDK comply.
 *
 * <p>The "destructive" methods contained in this interface, that is, the
 * methods that modify the DNA on which they operate, are specified to throw
 * {@code UnsupportedOperationException} if this DNA does not support the
 * operation.  If this is the case, these methods may, but are not required
 * to, throw an {@code UnsupportedOperationException} if the invocation would
 * have no effect on the DNA.  For example, invoking the {@link #putAll(java.util.Map)}
 * method on an unmodifiable DNA may, but is not required to, throw the
 * exception if the DNA whose entries are to be "superimposed" is empty.
 *
 * <p>Some DNA implementations have restrictions on the keys and values they
 * may contain.  For example, some implementations prohibit null keys and
 * values, and some have restrictions on the types of their keys.  Attempting
 * to insert an ineligible key or value throws an unchecked exception,
 * typically {@code NullPointerException} or {@code ClassCastException}.
 * Attempting to query the presence of an ineligible key or value may throw an
 * exception, or it may simply return false; some implementations will exhibit
 * the former behavior and some will exhibit the latter.  More generally,
 * attempting an operation on an ineligible key or value whose completion
 * would not result in the insertion of an ineligible element into the DNA may
 * throw an exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>Many methods in Collections Framework interfaces are defined
 * in terms of the {@link Object#equals(Object) equals} method.  For
 * example, the specification for the {@link #containsKey(Object)
 * containsKey(Object key)} method says: "returns {@code true} if and
 * only if this DNA contains a entry for a key {@code k} such that
 * {@code (key==null ? k==null : key.equals(k))}." This specification should
 * <i>not</i> be construed to imply that invoking {@code DNA.containsKey}
 * with a non-null argument {@code key} will cause {@code key.equals(k)} to
 * be invoked for any key {@code k}.  Implementations are free to
 * implement optimizations whereby the {@code equals} invocation is avoided,
 * for example, by first comparing the hash codes of the two keys.  (The
 * {@link Object#hashCode()} specification guarantees that two objects with
 * unequal hash codes cannot be equal.)  More generally, implementations of
 * the various Collections Framework interfaces are free to take advantage of
 * the specified behavior of underlying {@link Object} methods wherever the
 * implementor deems it appropriate.
 *
 * <p>Some DNA operations which perform recursive traversal of the DNA may fail
 * with an exception for self-referential instances where the DNA directly or
 * indirectly contains itself. This includes the {@code clone()},
 * {@code equals()}, {@code hashCode()} and {@code toString()} methods.
 * Implementations may optionally handle the self-referential scenario, however
 * most current implementations do not do so.
 *
 * <h2><a id="unmodifiable">Unmodifiable Maps</a></h2>
 * <p>The {@link DNA#of() DNA.of},
 * {@link DNA#ofEntries(DNA.Mapping...) DNA.ofEntries}, and
 * {@link DNA#copyOf DNA.copyOf}
 * static factory methods provide a convenient way to create unmodifiable maps.
 * The {@code DNA}
 * instances created by these methods have the following characteristics:
 *
 * <ul>
 * <li>They are <a href="Collection.html#unmodifiable"><i>unmodifiable</i></a>. Keys and values
 * cannot be added, removed, or updated. Calling any mutator method on the DNA
 * will always cause {@code UnsupportedOperationException} to be thrown.
 * However, if the contained keys or values are themselves mutable, this may cause the
 * DNA to behave inconsistently or its contents to appear to change.
 * <li>They disallow {@code null} keys and values. Attempts to create them with
 * {@code null} keys or values result in {@code NullPointerException}.
 * <li>They are serializable if all keys and values are serializable.
 * <li>They reject duplicate keys at creation time. Duplicate keys
 * passed to a static factory method result in {@code IllegalArgumentException}.
 * <li>The iteration order of entries is unspecified and is subject to change.
 * <li>They are <a href="../lang/doc-files/ValueBased.html">value-based</a>.
 * Programmers should treat instances that are {@linkplain #equals(Object) equal}
 * as interchangeable and should not use them for synchronization, or
 * unpredictable behavior may occur. For example, in a future release,
 * synchronization may fail. Callers should make no assumptions
 * about the identity of the returned instances. Factories are free to
 * create new instances or reuse existing ones.
 * <li>They are serialized as specified on the
 * <a href="{@docRoot}/serialized-form.html#java.util.CollSer">Serialized Form</a>
 * page.
 * </ul>
 *
 * <p>This interface is a member of the Hyperspeace Congregation Framework</a>.
 * 
 * @author joan
 *
 * @param <K> is the key class
 * @param <V> is the value class
 */
public interface DNA<K,V> extends Entry<K, V>, java.util.Map<K,V>, Iterable<K> {

	default Chain<V, K> entryChain() {
		return (Chain<V, K>) getChild();
	}

	/**
	 * Returns {@code true} if this DNA contains no key-value entries.
	 *
	 * @return {@code true} if this DNA contains no key-value entries
	 */
	boolean isEmpty();

	/**
	 * Returns {@code true} if this DNA contains a entry for the specified key.
	 * More formally, returns {@code true} if and only if this DNA contains a
	 * entry for a key {@code k} such that {@code Objects.equals(key, k)}. (There
	 * can be at most one such entry.)
	 *
	 * @param key key whose presence in this DNA is to be tested
	 * @return {@code true} if this DNA contains a entry for the specified key
	 */
	boolean containsKey(Object key);

	/**
	 * Returns {@code true} if this DNA maps one or more keys to the specified
	 * value. More formally, returns {@code true} if and only if this DNA contains
	 * at least one entry to a value {@code v} such that
	 * {@code Objects.equals(value, v)}. This operation will probably require time
	 * linear in the DNA size for most implementations of the {@code DNA} interface.
	 *
	 * @param value value whose presence in this DNA is to be tested
	 * @return {@code true} if this DNA maps one or more keys to the specified value
	 */
	boolean containsValue(Object value);

	/**
	 * Returns the value to which the specified key is mapped, or {@code null} if
	 * this DNA contains no entry for the key.
	 *
	 * <p>
	 * More formally, if this DNA contains a entry from a key {@code k} to a value
	 * {@code v} such that {@code Objects.equals(key, k)}, then this method returns
	 * {@code v}; otherwise it returns {@code null}. (There can be at most one such
	 * entry.)
	 *
	 * <p>
	 * If this DNA permits null values, then a return value of {@code null} does not
	 * <i>necessarily</i> indicate that the DNA contains no entry for the key;
	 * it's also possible that the DNA explicitly maps the key to {@code null}. The
	 * {@link #containsKey containsKey} operation may be used to distinguish these
	 * two cases.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or {@code null} if
	 *         this DNA contains no entry for the key
	 */
	V get(Object key);

	// Modification Operations
	/**
	 * Associates the specified value with the specified key in this DNA (optional
	 * operation). If the DNA previously contained a entry for the key, the old
	 * value is replaced by the specified value. (A DNA {@code m} is said to contain
	 * a entry for a key {@code k} if and only if {@link #containsKey(Object)
	 * m.containsKey(k)} would return {@code true}.)
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with {@code key}, or {@code null} if
	 *         there was no entry for {@code key}. (A {@code null} return can also
	 *         indicate that the DNA previously associated {@code null} with
	 *         {@code key}, if the implementation supports {@code null} values.)
	 */
	V put(K key, V value);

	/**
	 * Removes the entry for a key from this DNA if it is present (optional
	 * operation). More formally, if this DNA contains a entry from key {@code k}
	 * to value {@code v} such that {@code Objects.equals(key, k)}, that entry is
	 * removed. (The DNA can contain at most one such entry.)
	 *
	 * <p>
	 * Returns the value to which this DNA previously associated the key, or
	 * {@code null} if the DNA contained no entry for the key.
	 *
	 * <p>
	 * If this DNA permits null values, then a return value of {@code null} does not
	 * <i>necessarily</i> indicate that the DNA contained no entry for the key;
	 * it's also possible that the DNA explicitly mapped the key to {@code null}.
	 *
	 * <p>
	 * The DNA will not contain a entry for the specified key once the call
	 * returns.
	 *
	 * @param key key whose entry is to be removed from the DNA
	 * @return the previous value associated with {@code key}, or {@code null} if
	 *         there was no entry for {@code key}.
	 */
	V remove(Object key);
}