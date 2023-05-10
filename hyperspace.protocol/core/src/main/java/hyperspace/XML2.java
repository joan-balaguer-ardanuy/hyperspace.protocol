/**
 * 
 */
package hyperspace;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import hyperspace.recurrent.Enumerator;
import hyperspace.time.Time;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author joan
 *
 */
public abstract class XML2<K,V> 
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
	 * {@link XML2} default class constructor.
	 */
	public XML2() {
		super();
	}
	/**
	 * {@link XML2} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public XML2(Parity parity) {
		super(parity);
	}
	/**
	 * {@link XML2} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link Parity} the parity
	 * @param key the key
	 * @param value the value
	 */
	public XML2(Class<? extends Entry<V,K>> childClass, Parity parity, K key, V value) {
		super(childClass, parity);
		setKey(key);
		setValue(value);
	}
	/**
	 * {@link XML2} class constructor.
	 * @param parent the parent
	 */
	public XML2(Entry<K,V> parent) {
		super(parent);
	}
	/**
	 * {@link XML2} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 * @param key the key
	 * @param value the value
	 */
	public XML2(Class<? extends Entry<V,K>> childClass, Entry<K,V> parent, K key, V value) {
		super(childClass, parent);
		setKey(key);
		setValue(value);
	}
	/**
	 * {@link XML2} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public XML2(Entry<K,V> root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link XML2} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 * @param key the key
	 * @param value the value
	 */
	public XML2(Class<? extends Entry<V,K>> childClass, Entry<K,V> root, Parity parity, K key, V value) {
		super(childClass, root, parity);
		setKey(key);
		setValue(value);
	}
	
	@Override
	public Entry<K,V> clone() {
		return (Entry<K,V>) super.clone();
	}
	
	@Override
	public V getValue(Object key) {
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			if(key == entry.getKey()) {
				return entry.getValue();
			}
		}
		return null;
	}
	@Override
	public K getKey(Object value) {
		return getChild().getValue(value);
	}
	@Override
	public Entry<K,V> getParentByKey(K key) {
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
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
		int i = 0;
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
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
	@SuppressWarnings("unchecked")
	@Override
	public Entry<K,V> putValue(K key, V value) {
		return (Entry<K,V>) instance(getClass(), getParent(), key, value);
	}
	@Override
	public Entry<V,K> putKey(V value, K key) {
		return getChild().putValue(value, key);
	}
	@Override
	public Entry<K,V> putValueIfAbsent(K key, V value) {
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			if(key == entry.getKey()) {
				return null;
			}
		}
		return putValue(key, value);
	}
	@Override
	public Entry<V,K> putKeyIfAbsent(V value, K key) {
		return getChild().putValueIfAbsent(value, key);
	}
	@Override
	public V removeValue(K key) {
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements()) {
			Entry<K,V> entry = en.nextElement();
			if(key == entry.getKey()) {
				entry.release();
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			if(key == entry.getKey()) {
				if(value == entry.getValue()) {
					entry.release();
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			entry.setValue(function.apply(entry.getKey(), entry.getValue()));
		}
	}
	@Override
	public void replaceAllKeys(BiFunction<? super V, ? super K, ? extends K> function) {
		getChild().replaceAllValues(function);
	}
	@Override
	public V computeValue(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			if(key == entry.getKey()) {
				V newValue;
				if((newValue = remappingFunction.apply(key, entry.getValue())) == null) {
					entry.release();
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			if(key == entry.getKey()) {
				return null;
			}
		}
		V newValue;
		V oldValue = null;
		if((newValue = mappingFunction.apply(key)) != null) {
			oldValue = getValue(key);
			removeValue(key);
			putValue(key, newValue);
		}
		return oldValue;
	}
	@Override
	public K computeKeyIfAbsent(V value, Function<? super V,? extends K> mappingFunction) {
		return getChild().computeValueIfAbsent(value, mappingFunction);
	}
	@Override
	public V computeValueIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			if(key == entry.getKey()) {
				V newValue;
				V oldValue = null;
				if((newValue = remappingFunction.apply(key, getValue())) != null) {
					oldValue = getValue(key);
					removeValue(key);
					putValue(key, newValue);
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
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
			action.accept(entry.getKey(), entry.getValue());	
		}
	}
	@Override
	public void forEachKey(BiConsumer<? super V, ? super K> action) {
		getChild().forEachValue(action);
	}
	@Override
	public V mergeValue(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		Enumerator<Entry<K,V>> en = enumerator();
		while(en.hasMoreElements())  {
			Entry<K,V> entry = en.nextElement();
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
		return comparator;
	}
	@Override
	public Entry.Comparator<K,V> comparator(Entry<V, K> source) {
		comparator = new Grid(source);
		return comparator;
	}
	@Override
	public Entry.Comparator<K,V> comparator(V value, K key) {
		comparator = new Grid(value, key);
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

		public Grid(Entry<V,K> source) {
			super(source);
		}
		@SuppressWarnings("unchecked")
		public Grid(V value, K key) {
			super((Entry<V,K>) instance(XML2.this.getChild().getClass(), Parity.random(), value, key));
		}
	}
	
}