/**
 * 
 */
package hyperspace.genesis;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hyperspace.Hyperspace;
import hyperspace.XML;

/**
 * @author joan
 *
 */
public abstract class Hypercube<K,V> 
	extends Hyperspace<K,V>
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
	 * @param message {@link String} the name
	 */
	public Hypercube(XML message) {
		super(message);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param message {@link String} the name
	 */
	public Hypercube(Class<? extends Hypercube<K,V>> parentClass, Class<? extends Chain<V,K>> childClass, XML message) {
		super(parentClass, childClass, message);
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
	 * @param parent {@link Hypercube} the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> childClass, Hypercube<K,V> parent, K key, V value) {
		super(childClass, parent, key, value);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param root {@link Hypercube} the root
	 * @param message {@link String} the name
	 * @param key the key
	 */
	public Hypercube(Hypercube<K,V> root, Hyperchain<V,K> stem) {
		super(root, stem);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root {@link Hypercube} the root
	 * @param message {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> childClass, Hypercube<K,V> root, Hyperchain<V,K> stem, K key, V value) {
		super(childClass, root, stem, key, value);
	}
	@Override
	@Deprecated
	public int size() {
		return 0;
	}
	@SuppressWarnings("unchecked")
	public V get(Object key) {
    	return getValue((K) key);
    }
    public V put(K key, V value) {
    	return putValue(key, value);
    }
    public void putAll(hyperspace.Entry<K,V> m) {
		Enumeration<hyperspace.Entry<K,V>> en = enumerator();
		for(Entry<K,V> entry = en.nextElement(); en.hasMoreElements(); entry = en.nextElement())  {
			put(entry.getKey(), entry.getValue());
		}
    }
	@Override
	public V remove(Object key) {
		Enumeration<hyperspace.Entry<K,V>> i = enumerator();
		hyperspace.Entry<K,V> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && i.hasMoreElements()) {
            	hyperspace.Entry<K,V> e = i.nextElement();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && i.hasMoreElements()) {
            	hyperspace.Entry<K,V> e = i.nextElement();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }

        V oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            correctEntry.clear();
        }
        return oldValue;
	}
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(Entry<? extends K, ? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}
	@Override
	public Iterator<K> iterator() {
		return keySet().iterator();
	}

	transient Set<K> keySet;
	transient Collection<V> values;
   
	@Override
	public Set<K> keySet() {
		Set<K> ks = keySet;
		if (ks == null) {
			ks = new AbstractSet<K>() {
				public Iterator<K> iterator() {
					return new Iterator<K>() {
						private Iterator<Entry<K, V>> i = entrySet().iterator();

						public boolean hasNext() {
							return i.hasNext();
						}

						public K next() {
							return i.next().getKey();
						}

						public void remove() {
							i.remove();
						}
					};
				}
				@Deprecated
				public int size() {
					return 0;
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
			};
			keySet = ks;
		}
		return ks;
	}
	@Override
	public Collection<V> values() {
		Collection<V> vals = values;
        if (vals == null) {
            vals = new AbstractCollection<V>() {
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        private Iterator<Entry<K,V>> i = entrySet().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public V next() {
                            return i.next().getValue();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                @Deprecated
                public int size() {
                    return 0;
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
            };
            values = vals;
        }
        return vals;
	}
	
	transient Set<Entry<K,V>> entrySet;
	
	@Override
	public Set<Entry<K,V>> entrySet() {
		
		return entrySet == null ? entrySet = new AbstractSet<Map.Entry<K,V>>() {

			@Override
			@Deprecated
			public int size() {
				return 0;
			}
			
			transient Iterator<Map.Entry<K,V>> iterator;
			
			@Override
			public Iterator<Entry<K,V>> iterator() {
				Enumeration<hyperspace.Entry<K,V>> it = Hypercube.this.enumerator();
				return iterator == null ? iterator = new Iterator<Entry<K,V>>() {

					@Override
					public boolean hasNext() {
						return it.hasMoreElements();
					}

					@Override
					public Entry<K, V> next() {
						return it.nextElement();
					}
					
				}: iterator;
			}
			@Override
			@Deprecated
			public Object[] toArray() {
				return null;
			}
			@Override
			@Deprecated
		    public <T> T[] toArray(T[] a) {
		        return null;
		    }
		} : entrySet;
	}
}