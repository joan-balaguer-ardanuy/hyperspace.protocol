package hyperspace.time;

import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


public abstract class Abstraction
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Order<K,V>
			implements Recursive<K,V> {

	/**
	 * 6736845275705891958L
	 */
	private static final long serialVersionUID = 6736845275705891958L;

	
	/**
	 * {@link Abstraction} default class constructor.
	 */
	public Abstraction() {
		super();
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Abstraction(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Abstraction(Class<? extends K> type, String name, V child) {
		super(type, name, child);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parent parent parent
	 */
	public Abstraction(K parent) {
		super(parent);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	public Abstraction(K parent, V child) {
		super(parent, child);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param root the root
	 * @param {@link String} the name
	 */
	public Abstraction(K root, String name) {
		super(root.getType(), name);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Abstraction(K root, String name, V child) {
		super(root, name, child);
	}

	@Override
	public void forEachChild(BiConsumer<? super K, ? super V> action) {
		Objects.requireNonNull(action);
		for (K entry : this) {
			K k;
			V v;
			try {
				k = entry;
				v = entry.getChild();
			} catch (IllegalStateException ise) {
				// this usually means the entry is no longer in the map.
				throw new ConcurrentModificationException(ise);
			}
			action.accept(k, v);
		}
	}
	@Override
	public void forEachParent(BiConsumer<? super V, ? super K> action) {
		getChild().forEachChild(action);
	}
	@Override
	public V computeChildIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
		Objects.requireNonNull(mappingFunction);
		V v, newValue;
		return ((v = key.getChild()) == null && (newValue = mappingFunction.apply(key)) != null
				&& (v = putChildIfAbsent(key, newValue)) == null) ? newValue : v;
	}
	@Override
	public K computeParentIfAbsent(V value, Function<? super V, ? extends K> mappingFunction) {
		return getChild().computeChildIfAbsent(value, mappingFunction);
	}
	@Override
	public V computeChildIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		Objects.requireNonNull(remappingFunction);
		V oldValue;
		while ((oldValue = key.getChild()) != null) {
			V newValue = remappingFunction.apply(key, oldValue);
			if (newValue != null) {
				if (replaceChild(key, oldValue, newValue))
					return newValue;
			} else if (removeChild(key, oldValue))
				return null;
		}
		return oldValue;
	}
	@Override
	public K computeParentIfPresent(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction) {
		return getChild().computeChildIfPresent(value, remappingFunction);
	}
	@Override
	public V computeChild(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		Objects.requireNonNull(remappingFunction);
		V oldValue = key.getChild();
		for (;;) {
			V newValue = remappingFunction.apply(key, oldValue);
			if (newValue == null) {
				// delete mapping
				if (oldValue != null) {
					// something to remove
					if (removeChild(key, oldValue)) {
						// removed the old value as expected
						return null;
					}
					// some other value replaced old value. try again.
					oldValue = key.getChild();
				} else {
					// nothing to do. Leave things as they were.
					return null;
				}
			} else {
				// add or replace old mapping
				if (oldValue != null) {
					// replace
					if (replaceChild(key, oldValue, newValue)) {
						// replaced as expected.
						return newValue;
					}
					// some other value replaced old value. try again.
					oldValue = key.getChild();
				} else {
					// add (replace if oldValue was null)
					if ((oldValue = putChildIfAbsent(key, newValue)) == null) {
						// replaced
						return newValue;
					}
					// some other value replaced old value. try again.
				}
			}
		}
	}
	@Override
	public K computeParent(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction) {
		return getChild().computeChild(value, remappingFunction);
	}
	@Override
	public V mergeChild(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		Objects.requireNonNull(remappingFunction);
		Objects.requireNonNull(value);
		V oldValue = key.getChild();
		for (;;) {
			if (oldValue != null) {
				V newValue = remappingFunction.apply(oldValue, value);
				if (newValue != null) {
					if (replaceChild(key, oldValue, newValue))
						return newValue;
				} else if (removeChild(key, oldValue)) {
					return null;
				}
				oldValue = key.getChild();
			} else {
				if ((oldValue = putChildIfAbsent(key, value)) == null) {
					return value;
				}
			}
		}
	}
	@Override
	public K mergeParent(V value, K key, BiFunction<? super K, ? super K, ? extends K> remappingFunction) {
		return getChild().mergeChild(value, key, remappingFunction);
	}
}