/**
 * 
 */
package hyperspace.genesis;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hyperspace.XML2;
import hyperspace.recurrent.Enumerator;
import hyperspace.Parity;

/**
 * @author joan
 *
 */
public abstract class Hypercube<K,V> 
	extends XML2<K,V>
		implements DNA<K,V> {

	/**
	 * -1289997593164869118L
	 */
	private static final long serialVersionUID = -1289997593164869118L;

	/**
	 * {@link Hypercube} default class constructor.
	 */
	public Hypercube() {
		super();
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Hypercube(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link Parity} the parity
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> childClass, Parity parity, K key, V value) {
		super(childClass, parity, key, value);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param parent {@link Hypercube} the parent
	 */
	public Hypercube(Hypercube<K,V> parent) {
		super(parent);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> childClass, Hypercube<K,V> parent, K key, V value) {
		super(childClass, parent, key, value);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param root {@link Hypercube} the root
	 * @param parity {@link Parity} the message
	 */
	public Hypercube(Hypercube<K,V> root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> childClass, Hypercube<K,V> root, Parity parity, K key, V value) {
		super(childClass, root, parity, key, value);
	}

	public V get(Object key) {
    	return getValue(key);
    }
    public V put(K key, V value) {
    	V old = get(key);
    	putValue(key, value).getValue();
    	return old;
    }
    
    public void putAll(Map<? extends K,? extends V> m) {
    	Enumerator<hyperspace.Entry<K,V>> en = enumerator();
    	while(en.hasMoreElements())  {
    		Entry<K,V> entry = en.nextElement();
			put(entry.getKey(), entry.getValue());
		}
    }
	@Override
	public V remove(Object key) {
		Enumerator<hyperspace.Entry<K,V>> en = enumerator();
		hyperspace.Entry<K,V> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && en.hasMoreElements()) {
            	hyperspace.Entry<K,V> e = en.nextElement();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && en.hasMoreElements()) {
            	hyperspace.Entry<K,V> e = en.nextElement();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }
        V oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            correctEntry.release();
        }
        return oldValue;
	}
	@Override
	public int size() {
		Enumerator<hyperspace.Entry<K,V>> en = enumerator();
		int i = 0;
		while(en.hasMoreElements()) {
			en.nextElement();
			i++;
		}
		return i;
	}
	@Override
	public void clear() {
		release();
	}
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(getParent());
	}
	
	transient Set<K> keySet;
    transient Collection<V> values;
    transient Set<Entry<K,V>> entrySet;
    
	@Override
	public Set<K> keySet() {
		return keySet == null ? keySet = new AbstractSet<K>() {
			@Override
			public Iterator<K> iterator() {
                return new Iterator<K>() {
                    private Iterator<K> i = new KeyIterator(getParent());

                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    public K next() {
                        return i.next();
                    }

                    public void remove() {
                        i.remove();
                    }
                };
            }

			@Override
			public int size() {
				return Hypercube.this.size();
			}
			public boolean isEmpty() {
                return Hypercube.this.isEmpty();
            }
            public void clear() {
            	Hypercube.this.clear();
            }
            public boolean contains(Object k) {
                return Hypercube.this.containsKey(k);
            }
			
		} : keySet;
	}
	@Override
	public Collection<V> values() {
		return values == null ? values = new AbstractCollection<V>() {
			public Iterator<V> iterator() {
                return new Iterator<V>() {
                    private Iterator<V> i = new ValueIterator(getParent());

                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    public V next() {
                        return i.next();
                    }

                    public void remove() {
                        i.remove();
                    }
                };
            }

            public int size() {
                return Hypercube.this.size();
            }

            public boolean isEmpty() {
                return Hypercube.this.isEmpty();
            }

            public void clear() {
            	Hypercube.this.clear();
            }

            public boolean contains(Object v) {
                return Hypercube.this.containsValue(v);
            }
		}: values;
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet == null ? entrySet = new AbstractSet<Map.Entry<K,V>>() {

			@Override
			public Iterator<Entry<K, V>> iterator() {
				// TODO Auto-generated method stub
				return new Iterator<Map.Entry<K,V>>() {
					private Enumerator<hyperspace.Entry<K,V>> en = enumerator();
					@Override
					public boolean hasNext() {
						return en.hasMoreElements();
					}
					@Override
					public Entry<K, V> next() {
						return en.nextElement();
					}
					@Override
					public void remove() {
						en.remove();
					}
				};
			}

			@Override
			public int size() {
				return Hypercube.this.size();
			}
			public boolean isEmpty() {
                return Hypercube.this.isEmpty();
            }
            public void clear() {
            	Hypercube.this.clear();
            }
            public boolean contains(Object k) {
                return Hypercube.this.containsKey(k);
            }
			
		}: entrySet;
	}
	
	public class KeyIterator implements Iterator<K> {

		/**
		 * The current time-listener.
		 */
		hyperspace.Entry<K,V> current;

		/**
		 * The next time-listener.
		 */
		hyperspace.Entry<K,V> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public KeyIterator(hyperspace.Entry<K,V> parent) {
			next = current = parent;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public K next() {
			hyperspace.Entry<K,V> c = next;
			current = c;
			next = c.getParent();
			if (c == Hypercube.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getKey();
		}

		@Override
		public void remove() {
			hyperspace.Entry<K,V> k = next;
			current.release();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
			} else
				hasNext = false;
		}
	}
	public class ValueIterator implements Iterator<V> {

		/**
		 * The current time-listener.
		 */
		hyperspace.Entry<K,V> current;

		/**
		 * The next time-listener.
		 */
		hyperspace.Entry<K,V> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public ValueIterator(hyperspace.Entry<K,V> parent) {
			next = current = parent;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public V next() {
			hyperspace.Entry<K,V> c = next;
			current = c;
			next = c.getParent();
			if (c == Hypercube.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getValue();
		}

		@Override
		public void remove() {
			hyperspace.Entry<K,V> k = next;
			current.release();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
			} else
				hasNext = false;
		}
	}
}