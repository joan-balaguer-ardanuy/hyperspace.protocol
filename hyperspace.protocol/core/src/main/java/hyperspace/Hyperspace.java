/**
 * 
 */
package hyperspace;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import hyperspace.time.Time;

/**
 * @author joan
 *
 */
public abstract class Hyperspace<K,V> 
	extends Time<Entry<K,V>,Entry<V,K>>
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
	public K getKey() {
		return key;
	}
	public K setKey(K key) {
		K old = this.key;
		this.key = key;
		return old;
	}
	@Override
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
	public Hyperspace(Class<? extends Entry<K,V>> type, String name) {
		super(type, name);
	}
	public Hyperspace(Class<? extends Entry<K,V>> type, Class<? extends Entry<V,K>> antitype, String name) {
		super(type, name, instance(antitype, name));
	}
	public Hyperspace(Entry<K,V> parent, K key) {
		super(parent);
		setKey(key);
	}
	public Hyperspace(Class<? extends Entry<V,K>> antitype, Entry<K,V> parent, K key, V value) {
		super(parent, instance(antitype, parent.getChild(), value));
		setKey(key);
	}
	public Hyperspace(Entry<K,V> root, String name, K key) {
		super(root, name);
		setKey(key);
	}
	public Hyperspace(Class<? extends Entry<V,K>> antitype, Entry<K,V> root, String name, K key, V value) {
		super(root, name, instance(antitype, root.getStem(), name, value));
		setKey(key);
	}
	@Override
	public int size() {
		int i = 0;
		try {
			Iterator<Entry<K,V>> it;
			for(it = iterator();it.hasNext();it.next())  {
				i++;
			}	
		}
		catch(NoSuchElementException e) {
			return Integer.MAX_VALUE;
		}
		
		return i;
	}
	@Override
	public V getValue(K key) {
		for(Entry<K,V> entry : this) {
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
		for(Entry<K,V> entry : this) {
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
		for(Entry<K,V> entry : this) {
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
	public boolean containsKey(Object key) {
		for(Entry<K,V> entry : this) {
			if(key == entry.getKey()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean containsValue(Object value) {
		return getChild().containsKey(value);
	}
	@Override
	public int indexOfKey(K key) {
		int i = -1;
		for(Entry<K,V> entry : this) {
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
		getRoot().setKey(key);
		getStem().setKey(value);
		for (Entry<K, V> entry : this) {
			if (key == entry.getKey()) {
				return entry.setValue(value);
			}
		}
		instance(getType(), getParent(), key, value);
		return null;
	}
	@Override
	public K putKey(V value, K key) {
		return getChild().putValue(value, key);
	}
	@Override
	public V putValueIfAbsent(K key, V value) {
		for(Entry<K,V> entry : this) {
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
	public void removeValue(V value) {
		for(Entry<K,V> entry : this) {
			if(value == entry.getValue()) {
				entry.clear();
				return;
			}
		}
	}
	@Override
	public void removeKey(K key) {
		getChild().removeValue(key);
	}
	@Override
	public boolean removeValue(K key, V value) {
		for(Entry<K,V> mass : this) {
			if(key == mass.getKey()) {
				if(value == mass.getValue()) {
					mass.clear();
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
		for(Entry<K,V> entry : this) {
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
		for(Entry<K,V> entry : this) {
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
		for(Entry<K,V> mass : this) {
			mass.setValue(function.apply(mass.getKey(), mass.getValue()));
		}
	}
	@Override
	public void replaceAllKeys(BiFunction<? super V, ? super K, ? extends K> function) {
		getChild().replaceAllValues(function);
	}
	@Override
	public V computeValue(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
		for(Entry<K,V> entry : this) {
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
		for(Entry<K,V> entry : this) {
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
		for(Entry<K,V> entry : this) {
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
	public void forEachKey(BiConsumer<? super K, ? super V> action) {
		for(Entry<K,V> mass : this) {
			action.accept(mass.getKey(), mass.getValue());	
		}
	}
	@Override
	public void forEachValue(BiConsumer<? super V, ? super K> action) {
		getChild().forEachKey(action);
	}
	@Override
	public V mergeValue(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		for(Entry<K,V> entry : this) {
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
	public Transmitter<Entry<K, V>, Entry<V, K>> comparator(Entry<K, V> source) {
		comparator = new Grid(source);
		return null;
	}
	public Entry.Comparator<K,V> comparator(K key, V value) {
		comparator = new Grid(getType(), key, value);
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
				this.source = instance(Hyperspace.this.getType(), key, value);
				return;
			}
			instance(getType(), this.source, key, value);
		}
	}
}
