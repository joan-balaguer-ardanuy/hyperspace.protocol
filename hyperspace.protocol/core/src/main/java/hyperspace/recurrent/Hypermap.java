/**
 * 
 */
package hyperspace.recurrent;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import hyperspace.Entry;
import hyperspace.recurrent.Mapping;
import hyperspace.time.Time;

/**
 * @author joan
 *
 */
public class Hypermap<K,V> 
	extends Time<Mapping<K,V>,Mapping<K,V>>
		implements Map<K,V> {

	/**
	 * -7683518704143350984L
	 */
	private static final long serialVersionUID = -7683518704143350984L;
	
	/**
	 * The key.
	 */
	K key;
	V value;
	
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
		return value;
	}
	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}
	
	/**
	 * {@link Hypermap} default class constructor.
	 */
	public Hypermap() {
		super();
	}
	public Hypermap(Class<? extends Mapping<K,V>> type, String name) {
		super(type, name);
		setKey(null);
		setValue(null);
	}
	public Hypermap(Class<? extends Mapping<K,V>> type, String name, K key, V value) {
		super(type, name, instance(type, type, name));
		setKey(key);
		setValue(value);
	}
	public Hypermap(Mapping<K,V> parent) {
		super(parent);
		setKey(null);
		setValue(null);
	}
	public Hypermap(Mapping<K,V> parent, K key, V value) {
		super(parent, instance(parent.getType(), parent.getChild()));
		setKey(key);
		setValue(value);
	}
	public Hypermap(Mapping<K,V> root, String name) {
		super(root, name);
		setKey(null);
		setValue(null);
	}
	public Hypermap(Mapping<K,V> root, String name, K key, V value) {
		super(root, name, instance(root.getType(), root, name));
		setKey(key);
		setValue(value);
	}
	
	@Override
	public int size() {
		int i = 0;
		try {
			Iterator<Mapping<K,V>> it;
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
	public void putAll(java.util.Map<? extends K, ? extends V> m) {
		for(Entry<? extends K,? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}
	@Override
	public boolean containsKey(Object key) {
		Iterator<Entry<K,V>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
	}
	@Override
	public boolean containsValue(Object value) {
		Iterator<Entry<K,V>> i = entrySet().iterator();
        if (value==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
	}
	@Override
	public V get(Object key) {
		Iterator<Entry<K, V>> i = entrySet().iterator();
		if (key == null) {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (e.getKey() == null)
					return e.getValue();
			}
		} else {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (key.equals(e.getKey()))
					return e.getValue();
			}
		}
		return null;
	}
	@Override
	public V put(K key, V value) {
//		Map<K,V> parent = this;
//		do {
//			if(getKey() == key) {
//				setKey(key);
//				return setValue(value);
//			}
//			parent = parent.getParent();
//		} while(parent != this);
		instance(getType(), getParent(), key, value);
		return null;
	}
	@Override
	public V remove(Object key) {
		Iterator<Entry<K,V>> i = entrySet().iterator();
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
	// Views

    /**
     * Each of these fields are initialized to contain an instance of the
     * appropriate view the first time this view is requested.  The views are
     * stateless, so there's no reason to create more than one of each.
     *
     * <p>Since there is no synchronization performed while accessing these fields,
     * it is expected that java.util.Map view classes using these fields have
     * no non-final fields (or any fields at all except for outer-this). Adhering
     * to this rule would make the races on these fields benign.
     *
     * <p>It is also imperative that implementations read the field only once,
     * as in:
     *
     * <pre> {@code
     * public Set<K> keySet() {
     *   Set<K> ks = keySet;  // single racy read
     *   if (ks == null) {
     *     ks = new KeySet();
     *     keySet = ks;
     *   }
     *   return ks;
     * }
     *}</pre>
     */
    transient Set<K>        keySet;
    transient Collection<V> values;
    
    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementation returns a set that subclasses {@link AbstractSet}.
     * The subclass's iterator method returns a "wrapper object" over this
     * map's {@code entrySet()} iterator.  The {@code size} method
     * delegates to this map's {@code size} method and the
     * {@code contains} method delegates to this map's
     * {@code containsKey} method.
     *
     * <p>The set is created the first time this method is called,
     * and returned in response to all subsequent calls.  No synchronization
     * is performed, so there is a slight chance that multiple calls to this
     * method will not all return the same set.
     */
    @Override
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new AbstractSet<K>() {
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        private Iterator<Entry<K,V>> i = entrySet().iterator();

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
                    return Hypermap.this.size();
                }

                public boolean isEmpty() {
                    return Hypermap.this.isEmpty();
                }

                public void clear() {
                	Hypermap.this.clear();
                }

                public boolean contains(Object k) {
                    return Hypermap.this.containsKey(k);
                }
            };
            keySet = ks;
        }
        return ks;
    }
    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementation returns a collection that subclasses {@link
     * AbstractCollection}.  The subclass's iterator method returns a
     * "wrapper object" over this map's {@code entrySet()} iterator.
     * The {@code size} method delegates to this map's {@code size}
     * method and the {@code contains} method delegates to this map's
     * {@code containsValue} method.
     *
     * <p>The collection is created the first time this method is called, and
     * returned in response to all subsequent calls.  No synchronization is
     * performed, so there is a slight chance that multiple calls to this
     * method will not all return the same collection.
     */
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
                    return Hypermap.this.size();
                }

                public boolean isEmpty() {
                    return Hypermap.this.isEmpty();
                }

                public void clear() {
                	Hypermap.this.clear();
                }

                public boolean contains(Object v) {
                    return Hypermap.this.containsValue(v);
                }
            };
            values = vals;
        }
        return vals;
    }
	@Override
	public Set<Entry<K, V>> entrySet() {
		return new AbstractSet<java.util.Map.Entry<K,V>>() {

			@Override
			public Iterator<java.util.Map.Entry<K,V>> iterator() {
				return new Iterator<java.util.Map.Entry<K,V>>() {
					private Iterator<Mapping<K,V>> it = new MappingIterator(Hypermap.this);
					@Override
					public boolean hasNext() {
						return it.hasNext();
					}
					@Override
					public Entry<K, V> next() {
						return it.next();
					}
					@Override
					public void remove() {
						it.remove();
					}
				};
			}
			@Override
			public int size() {
				return Hypermap.this.size();
			}
		};
	}
	@Override
	public Transmitter<Mapping<K, V>, Mapping<K, V>> comparator(Mapping<K, V> source) {
		return null;
	}
	@Override
	public Transmitter<Mapping<K, V>, Mapping<K, V>> comparator() {
		return null;
	}
	protected final class MappingIterator implements Iterator<Mapping<K,V>> {
		
		/**
		 * The current time-listener.
		 */
		protected Mapping<K,V> current;
		
		/**
		 * The next time-listener.
		 */
		protected Mapping<K,V> next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		protected boolean hasNext;
		
		public MappingIterator(Mapping<K,V> key) {
			next = current = key;
			hasNext = true;
		}
		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public Mapping<K, V> next() {
			Mapping<K,V> k = next;
			current = k;
			next = k.getParent();
			if(k == Hypermap.this)
				hasNext = false;
			else hasNext = true;
			return k;
		}
		@Override
		public void remove() {
			Mapping<K,V> k = next;
			current.clear();
			if(!k.isEmpty()) {
				current = k;
				next = k.getParent();
			}
			else hasNext = false;
		}
	}
}