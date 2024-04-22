/**
 * 
 */
package hyperspace.recurrent;

import java.util.Iterator;

/**
 * @author joan
 *
 */

public abstract class AbstractMap<K,V> 
	extends AbstractSet<Mapping<K,V>>
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
	public K setKey(K key) {
		return getEntry().setKey(key);
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
	}
	
	public AbstractMap(AbstractMap<K,V> parent, Entry mapping) {
		super(parent, mapping);
	}
	
	@Override
	public void putAll(Map<K,V> m) {
		Iterator<Mapping<K,V>> it = m.iterator();
		while(it.hasNext()) {
			Mapping<K,V> entry = it.next();
			put(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public boolean containsKey(K key) {
		Iterator<Mapping<K,V>> it = iterator();
        if (key==null) {
            while (it.hasNext()) {
            	Mapping<K,V> e = it.next();
                if (e.getKey()==null)
                    return true;
            }
        } else {
            while (it.hasNext()) {
            	Mapping<K,V> e = it.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
	}
	
	@Override
	public boolean containsValue(V value) {
		Iterator<Mapping<K,V>> it = iterator();
        if (value==null) {
            while (it.hasNext()) {
            	Mapping<K,V> e = it.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (it.hasNext()) {
            	Mapping<K,V> e = it.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
	}
	
	@Override
	public V get(K key) {
		Iterator<Mapping<K,V>> it = iterator();
		if (key == null) {
			while (it.hasNext()) {
				Mapping<K,V> e = it.next();
				if (e.getKey() == null)
					return e.getValue();
			}
		} else {
			while (it.hasNext()) {
				Mapping<K,V> e = it.next();
				if (key.equals(e.getKey()))
					return e.getValue();
			}
		}
		return null;
	}
	
	@Override
	public V put(K key, V value) {
		Iterator<Mapping<K, V>> it = iterator();
		while (it.hasNext()) {
			Mapping<K, V> entry = it.next();
			if(entry.getKey() == key) {
				V old = entry.setValue(value);
				return old;
			}
		}
		add(new Entry(key, value));
		return null;
	}
	
	public final class Entry implements Mapping<K,V> {

		K key;
		V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public K setKey(K key) {
			K old = this.key;
			this.key = key;
			return old;
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
	} 
}