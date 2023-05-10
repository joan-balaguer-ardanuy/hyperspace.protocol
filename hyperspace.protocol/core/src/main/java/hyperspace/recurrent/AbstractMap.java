/**
 * 
 */
package hyperspace.recurrent;

import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author joan
 *
 */
public abstract class AbstractMap<K,V> 
	extends AbstractSet<Entry<K, V>>
		implements Map<K,V> {

	/**
	 * -7683518704143350984L
	 */
	private static final long serialVersionUID = -7683518704143350984L;
	
	@Override
	public K getKey() {
		return getEntry().getKey();
	}
	@Override
	public V getValue() {
		return getEntry().getValue();
	}
	@Override
	public V setValue(V value) {
		return getEntry().setValue(value);
	}
	
	/**
	 * {@link AbstractMap} default class constructor.
	 */
	public AbstractMap() {
		super();
		setEntry(new Entry<K,V>() {
			V value;
			@Override
			public K getKey() {
				return null;
			}
			@Override
			public V getValue() {
				return value;
			}
			@Override
			public V setValue(V value) {
				V old = this.value;
				this.value = value;
				return old;
			}
		});
	}
	public AbstractMap(Map<K,V> parent, K key, V value) {
		super(parent, new java.util.Map.Entry<K, V>() {
			K k = key;
			V v = value;
			@Override
			public K getKey() {
				return this.k;
			}
			@Override
			public V getValue() {
				return this.v;
			}
			@Override
			public V setValue(V value) {
				V old = this.v;
				this.v = value;
				return old;
			}
		});
	}
	
	@Override
	public AbstractSet<Entry<K, V>> clone() {
		return (AbstractSet<Entry<K, V>>) super.clone();
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		Iterator<java.util.Map.Entry<K, V>> it = iterator();
		while(it.hasNext()) {
			java.util.Map.Entry<K,V> entry = it.next();
			put(entry.getKey(), entry.getValue());
		}
	}
	@Override
	public boolean containsKey(K key) {
		Iterator<java.util.Map.Entry<K,V>> it = iterator();
        if (key==null) {
            while (it.hasNext()) {
            	java.util.Map.Entry<K,V> e = it.next();
                if (e.getKey()==null)
                    return true;
            }
        } else {
            while (it.hasNext()) {
            	java.util.Map.Entry<K,V> e = it.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
	}
	@Override
	public boolean containsValue(V value) {
		Iterator<java.util.Map.Entry<K,V>> it = iterator();
        if (value==null) {
            while (it.hasNext()) {
            	java.util.Map.Entry<K,V> e = it.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (it.hasNext()) {
            	java.util.Map.Entry<K,V> e = it.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
	}
	@Override
	public V get(K key) {
		Iterator<java.util.Map.Entry<K,V>> it = iterator();
		if (key == null) {
			while (it.hasNext()) {
				java.util.Map.Entry<K,V> e = it.next();
				if (e.getKey() == null)
					return e.getValue();
			}
		} else {
			while (it.hasNext()) {
				java.util.Map.Entry<K,V> e = it.next();
				if (key.equals(e.getKey()))
					return e.getValue();
			}
		}
		return null;
	}
	@Override
	public V put(K key, V value) {
		// missing something?
		getRoot().getEntry().setValue(value);
		Iterator<java.util.Map.Entry<K, V>> it = iterator();
		while (it.hasNext()) {
			java.util.Map.Entry<K, V> entry = it.next();
			if(entry.getKey() == key) {
				V old = entry.setValue(value);
				return old;
			}
		}
		instance(getClass(), getParent(), key, value);
		return null;
	}
}