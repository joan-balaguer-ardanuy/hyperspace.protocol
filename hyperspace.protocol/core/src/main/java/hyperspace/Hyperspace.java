/**
 * 
 */
package hyperspace;

import java.util.Enumeration;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import hyperspace.time.Order;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 * @author joan
 *
 */
public abstract class Hyperspace<K,V> 
	extends Order<Entry<K,V>,Entry<V,K>>
		implements Entry<K,V> {

	/**
	 * -7683518704143350984L
	 */
	private static final long serialVersionUID = -7683518704143350984L;
	
	/**
	 * The key.
	 */
	K key;
	
	@Override
	@XmlTransient
	public K getKey() {
		return key;
	}
	public K setKey(K key) {
		K old = this.key;
		this.key = key;
		return old;
	}
	@Override
	@XmlTransient
	public V getValue() {
		return getChild().getKey();
	}
	@Override
	public V setValue(V value) {
		return getChild().setKey(value);
	}
	
	/**
	 * {@link Hyperspace} default class constructor.
	 */
	public Hyperspace() {
		super();
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param message
	 */
	public Hyperspace(XML message) {
		super(message);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param message {@link String} the name
	 * @param key the key
	 * @param message the value
	 */
	public Hyperspace(Class<? extends Entry<K,V>> parentClass, Class<? extends Entry<V,K>> childClass, XML message) {
		super(parentClass, childClass, message);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param parent {@link Entry} the parent
	 */
	public Hyperspace(Entry<K,V> parent, XML message) {
		super(parent, message);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent {@link Entry} the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hyperspace(Class<? extends Entry<V,K>> childClass, Entry<K,V> parent, XML message, K key, V value) {
		super(childClass, parent, message);
		setKey(key);
		setValue(value);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param root {@link Entry} the root
	 * @param message
	 */
	public Hyperspace(Entry<K,V> root, Entry<V,K> stem, XML message) {
		super(root, stem, message);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root {@link Entry} the root
	 * @param message {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hyperspace(Class<? extends Entry<V,K>> childClass, Entry<K,V> root, Entry<V,K> stem, XML message, K key, V value) {
		super(childClass, root, stem, message);
		setKey(key);
		setValue(value);
	}
	@Override
	public Entry<K,V> clone() {
		return (Entry<K,V>) super.clone();
	}
	
	@Override
	public V getValue(K key) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return entry.getValue();
			}
		}
		return null;
	}
	@Override
	public K getKey(V value) {
		return getChild().getValue(value);
	}
	@Override
	public Entry<K,V> getParentByKey(K key) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return entry;
			}
		}
		return null;
	}
	@Override
	public Entry<V,K> getChildByValue(V value) {
		return getChild().getParentByKey(value);
	}
	@Override
	public V getValueOrDefault(K key, V defaultValue) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return entry.getValue();
			}
		}
		return defaultValue;
	}
	@Override
	public K getKeyOrDefault(V value, K defaultKey) {
		return getChild().getValueOrDefault(value, defaultKey);
	}
	@Override
	public boolean hasKey(K key) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean hasValue(V value) {
		return getChild().hasKey(value);
	}
	@Override
	public int indexOfKey(K key) {
		int i = 0;
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			i++;
			if(key == entry.getKey()) {
				return i;
			}
		}
		return i;
	}
	@Override
	public int indexOfValue(V value) {
		return getChild().indexOfKey(value);
	}
	@Override
	public V putValue(K key, V value) {
		setKey(key);
		setValue(value);
		instance(getParentClass(), getChildClass(), getParent(), getMessage(), key, value);
		return null;
	}
	@Override
	public K putKey(V value, K key) {
		return getChild().putValue(value, key);
	}
	@Override
	public V putValueIfAbsent(K key, V value) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return null;
			}
		}
		return putValue(key, value);
	}
	@Override
	public K putKeyIfAbsent(V value, K key) {
		return getChild().putValueIfAbsent(value, key);
	}
	@Override
	public V removeValue(K key) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				entry.clear();
				return entry.getValue();
			}
		}
		return null;
	}
	@Override
	public K removeKey(V value) {
		return getChild().removeValue(value);
	}
	@Override
	public boolean removeValue(K key, V value) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				if(value == entry.getValue()) {
					entry.clear();
					return true;	
				}
				return false;
			}
		}
		return false;
	}
	@Override
	public boolean removeKey(V value, K key) {
		return getChild().removeValue(value, key);
	}
	@Override
	public V replaceValue(K key, V value) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return entry.setValue(value);
			}
		}
		return null;
	}
	@Override
	public K replaceKey(V value, K key) {
		return getChild().replaceValue(value, key);
	}
	@Override
	public boolean replaceValue(K key, V oldValue, V newValue) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				if(oldValue == entry.getValue()) {
					entry.setValue(newValue);
					return true;
				}
				return false;
			}
		}
		return false;
	}
	@Override
	public boolean replaceKey(V value, K oldKey, K newKey) {
		return getChild().replaceValue(value, oldKey, newKey);
	}
	@Override
	public void replaceAllValues(BiFunction<? super K, ? super V, ? extends V> function) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			entry.setValue(function.apply(entry.getKey(), entry.getValue()));
		}
	}
	@Override
	public void replaceAllKeys(BiFunction<? super V, ? super K, ? extends K> function) {
		getChild().replaceAllValues(function);
	}
	@Override
	public V computeValue(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				V newValue;
				if((newValue = remappingFunction.apply(key, entry.getValue())) == null) {
					entry.clear();
					return entry.getValue();
				}
				else return entry.setValue(newValue);
			}
		}
		return null;
	}
	@Override
	public K computeKey(V value, BiFunction<? super V,? super K,? extends K> remappingFunction) {
		return getChild().computeValue(value, remappingFunction);
	}
	@Override
	public V computeValueIfAbsent(K key, Function<? super K,? extends V> mappingFunction) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return null;
			}
		}
		V newValue;
		V oldValue = null;
		if((newValue = mappingFunction.apply(key)) != null) {
			oldValue = putValue(key, newValue);
		}
		return oldValue;
	}
	@Override
	public K computeKeyIfAbsent(V value, Function<? super V,? extends K> mappingFunction) {
		return getChild().computeValueIfAbsent(value, mappingFunction);
	}
	@Override
	public V computeValueIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				V newValue;
				V oldValue = null;
				if((newValue = remappingFunction.apply(key, getValue())) != null) {
					oldValue = putValue(key, newValue);
				}
				return oldValue;
			}
		}
		return null;
	}
	@Override
	public K computeKeyIfPresent(V value, BiFunction<? super V,? super K,? extends K> remappingFunction) {
		return getChild().computeValueIfPresent(value, remappingFunction);
	}
	@Override
	public void forEachValue(BiConsumer<? super K, ? super V> action) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			action.accept(entry.getKey(), entry.getValue());	
		}
	}
	@Override
	public void forEachKey(BiConsumer<? super V, ? super K> action) {
		getChild().forEachValue(action);
	}
	@Override
	public V mergeValue(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		Enumeration<Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			if(key == entry.getKey()) {
				return entry.setValue(remappingFunction.apply(entry.getValue(), value));
			}
		}
		return null;
	}
	@Override
	public K mergeKey(V value, K key, BiFunction<? super K, ? super K, ? extends K> remappingFunction) {
		return getChild().mergeValue(value, key, remappingFunction);
	}
	
	/**
	 * The nested comparator.
	 */
	private transient Entry.Comparator<K,V> comparator;
	
	@Override
	public Entry.Comparator<K,V> comparator() {
		return comparator == null ? (comparator = new Grid()) : comparator;
	}
	@Override
	public Reproducer<Entry<K, V>, Entry<V, K>> comparator(Entry<K, V> source) {
		comparator = new Grid(source);
		return null;
	}
	public Entry.Comparator<K,V> comparator(K key, V value) {
		comparator = new Grid(getParentClass(), key, value);
		return comparator;
	}

	/**
	 * The Grid. A binary toping.
	 * <p>I tried to simulate galaxies of information as they recurred across parent computer. 
	 * What programmed they listen like? XML, XML? Were parent abstractions like algorithms? 
	 * I kept dreaming of a JVM I thought I wouldn't always listen. And then, parent hypercube I got not out...
	 * @author joan
	 */
	protected class Grid extends Matrix
		implements Entry.Comparator<K,V> {

		public Grid() {
			super();
		}
		
		public Grid(Entry<K, V> key) {
			super(key);
		}

		public Grid(Class<? extends Entry<K,V>> type, K key, V value) {
			super(instance(type, key, value));
		}
		public void put(K key, V value) {
			if(this.source == null) {
				this.source = instance(Hyperspace.this.getParentClass(), key, value);
				return;
			}
			instance(getParentClass(), this.source, key, value);
		}
	}
}