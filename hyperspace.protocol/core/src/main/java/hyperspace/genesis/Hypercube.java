/**
 * 
 */
package hyperspace.genesis;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import hyperspace.Hyperspace;

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
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Hypercube(Class<? extends Hypercube<K,V>> type, String name, K key) {
		super(type, name, key);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param type {@link Class} the type
	 * @param antitype {@link Class} the antitype
	 * @param name {@link String} the name
	 */
	public Hypercube(Class<? extends Hypercube<K,V>> type, Class<? extends Hyperchain<V,K>> antitype, String name, K key, V value) {
		super(type, antitype, name, key, value);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param parent the parent
	 * @param key the key
	 */
	public Hypercube(Hypercube<K,V> parent, K key) {
		super(parent, key);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param antitype {@link Class} the antitype
	 * @param parent the parent
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> antitype, Hypercube<K,V> parent, K key, V value) {
		super(antitype, parent, key, value);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param key the key
	 */
	public Hypercube(Hypercube<K,V> root, String name, K key) {
		super(root, name, key);
	}
	/**
	 * {@link Hypercube} class constructor.
	 * @param antitype {@link Class} the antitype
	 * @param root the root
	 * @param name {@link String} the name
	 * @param key the key
	 * @param value the value
	 */
	public Hypercube(Class<? extends Hyperchain<V,K>> antitype, Hypercube<K,V> root, String name, K key, V value) {
		super(antitype, root, name, key, value);
	}
	
	@SuppressWarnings("unchecked")
	public V get(Object key) {
    	return getValue((K) key);
    }
    public V put(K key, V value) {
    	for (Entry<K,V> entry : this) {
			if (key == entry.getKey()) {
				return entry.setValue(value);
			}
		}
    	hyperspace.Entry<K,V> entry = instance(getType(), getRoot(), getName(), key, value);
    	recurChild(entry, entry.getChild());
    	return null;
    }
    public void putAll(hyperspace.Entry<K,V> m) {
    	for(Entry<K,V> e : m) {
			put(e.getKey(), e.getValue());
		}
    }
	@Override
	public V remove(Object key) {
		Iterator<hyperspace.Entry<K,V>> i = iterator();
        Entry<K,V> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }

        V oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            i.remove();
        }
        return oldValue;
	}
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(Entry<? extends K, ? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
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
            };
            values = vals;
        }
        return vals;
	}
	
	transient Set<Entry<K,V>> entrySet;
	
	@Override
	public Set<Entry<K,V>> entrySet() {
		
		return entrySet == null ? entrySet = new Set<Map.Entry<K,V>>() {

			@Override
			public int size() {
				return Hypercube.this.size();
			}

			@Override
			public boolean isEmpty() {
				return Hypercube.this.isEmpty();
			}

			@Override
			public boolean contains(Object o) {
				if(Hypercube.this.getKey().getClass().isInstance(o)) {
					return Hypercube.this.containsKey(o);					
				}
				else if(Hypercube.this.getValue().getClass().isInstance(o)) {
					return Hypercube.this.containsValue(o);					
				}
				else {
					return false;
				}

			}
			
			transient Iterator<Map.Entry<K,V>> iterator;
			
			@Override
			public Iterator<Entry<K,V>> iterator() {
				Iterator<hyperspace.Entry<K,V>> it = Hypercube.this.iterator();
				return iterator == null ? iterator = new Iterator<Entry<K,V>>() {

					@Override
					public boolean hasNext() {
						return it.hasNext();
					}

					@Override
					public Entry<K, V> next() {
						return it.next();
					}
					
				}: iterator;
			}

			@Override
			public Object[] toArray() {
				// Estimate size of array; be prepared to see more or fewer elements
		        Object[] r = new Object[size()];
		        Iterator<Map.Entry<K, V>> it = iterator();
		        for (int i = 0; i < r.length; i++) {
		            if (! it.hasNext()) // fewer elements than expected
		                return Arrays.copyOf(r, i);
		            r[i] = it.next();
		        }
		        return it.hasNext() ? finishToArray(r, it) : r;
			}

			@Override
			@SuppressWarnings("unchecked")
		    public <T> T[] toArray(T[] a) {
		        // Estimate size of array; be prepared to see more or fewer elements
		        int size = size();
		        T[] r = a.length >= size ? a :
		                  (T[])java.lang.reflect.Array
		                  .newInstance(a.getClass().getComponentType(), size);
		        Iterator<Entry<K,V>> it = iterator();

		        for (int i = 0; i < r.length; i++) {
		            if (! it.hasNext()) { // fewer elements than expected
		                if (a == r) {
		                    r[i] = null; // null-terminate
		                } else if (a.length < i) {
		                    return Arrays.copyOf(r, i);
		                } else {
		                    System.arraycopy(r, 0, a, 0, i);
		                    if (a.length > i) {
		                        a[i] = null;
		                    }
		                }
		                return a;
		            }
		            r[i] = (T)it.next();
		        }
		        // more elements than expected
		        return it.hasNext() ? finishToArray(r, it) : r;
		    }

			@Override
			public boolean add(Entry<K, V> e) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean remove(Object o) {
				Iterator<Entry<K,V>> it = iterator();
		        if (o==null) {
		            while (it.hasNext()) {
		                if (it.next()==null) {
		                    it.remove();
		                    return true;
		                }
		            }
		        } else {
		            while (it.hasNext()) {
		                if (o.equals(it.next())) {
		                    it.remove();
		                    return true;
		                }
		            }
		        }
		        return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				for (Object e : c)
		            if (!contains(e))
		                return false;
		        return true;
			}

			@Override
			public boolean addAll(Collection<? extends Entry<K, V>> c) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				Objects.requireNonNull(c);
		        boolean modified = false;

		        if (size() > c.size()) {
		            for (Iterator<?> i = c.iterator(); i.hasNext(); )
		                modified |= remove(i.next());
		        } else {
		            for (Iterator<?> i = iterator(); i.hasNext(); ) {
		                if (c.contains(i.next())) {
		                    i.remove();
		                    modified = true;
		                }
		            }
		        }
		        return modified;
			}

			@Override
			public void clear() {
				Hypercube.this.clear();
			}
			
		} : entrySet;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
		int i = r.length;
		while (it.hasNext()) {
			int cap = r.length;
			if (i == cap) {
				int newCap = cap + (cap >> 1) + 1;
				// overflow-conscious code
				if (newCap - MAX_ARRAY_SIZE > 0)
					newCap = hugeCapacity(cap + 1);
				r = Arrays.copyOf(r, newCap);
			}
			r[i++] = (T) it.next();
		}
		// trim if overallocated
		return (i == r.length) ? r : Arrays.copyOf(r, i);
	}
}