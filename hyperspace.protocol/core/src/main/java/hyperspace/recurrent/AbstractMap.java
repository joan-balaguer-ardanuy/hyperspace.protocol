/**
 * 
 */
package hyperspace.recurrent;

import java.util.Iterator;
import java.util.Objects;
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
		return getElement().getKey();
	}
	@Override
	public V getValue() {
		return getElement().getValue();
	}
	@Override
	public V setValue(V value) {
		return getElement().setValue(value);
	}
	
	/**
	 * {@link AbstractMap} default class constructor.
	 */
	public AbstractMap(Class<? extends AbstractMap<K,V>> type) {
		super(type);
	}
	public AbstractMap(AbstractMap<K,V> parent, java.util.Map.Entry<K, V> entry) {
		super(parent, entry);
	}
	
	@Override
	public AbstractSet<Entry<K, V>> clone() {
		return (AbstractSet<Entry<K, V>>) super.clone();
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(java.util.Map.Entry<? extends K,? extends V> entry : m) {
			put(entry.getKey(), entry.getValue());
		}
	}
	@Override
	public boolean containsKey(K key) {
		Iterator<java.util.Map.Entry<K,V>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
            	java.util.Map.Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
            	java.util.Map.Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
	}
	@Override
	public boolean containsValue(V value) {
		Iterator<java.util.Map.Entry<K,V>> i = entrySet().iterator();
        if (value==null) {
            while (i.hasNext()) {
            	java.util.Map.Entry<K,V> e = i.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
            	java.util.Map.Entry<K,V> e = i.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
	}
	@Override
	public V get(K key) {
		Iterator<java.util.Map.Entry<K,V>> i = new MapIterator(getParent());
		if (key == null) {
			while (i.hasNext()) {
				java.util.Map.Entry<K,V> e = i.next();
				if (e.getKey() == null)
					return e.getValue();
			}
		} else {
			while (i.hasNext()) {
				java.util.Map.Entry<K,V> e = i.next();
				if (key.equals(e.getKey()))
					return e.getValue();
			}
		}
		return null;
	}
	@Override
	public abstract V put(K key, V value);
//	{
//		if(isEmpty()) {
//			setElement(new Entry<K,V>() {
//				K k = key;
//				V v = value;
//				
//				@Override
//				public K getKey() {
//					return k;
//				}
//
//				@Override
//				public V getValue() {
//					return v;
//				}
//
//				@Override
//				public V setValue(V value) {
//					V old = v;
//					v = value;
//					return old;
//				}
//				
//			});
//			return null;
//		} else {
//			Enumeration<Collection<Entry<K,V>>> en = enumerator();
//			for(Collection<Entry<K,V>> parent = en.nextElement(); en.hasMoreElements(); parent = en.nextElement())  {
//				if(parent.getElement().getKey() == key) {
//					return parent.getElement().setValue(value);
//				}
//			}
//			instance(getParentClass(), getParent(), key, value);
//			return null;
//		}
//	}

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
     * <p>It is also imperative that abstractions read the field only once,
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
    transient java.util.Set<K>        keySet;
    transient java.util.Collection<V> values;
    
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
    public java.util.Set<K> keySet() {
    	java.util.Set<K> ks = keySet;
        if(ks == null) {
        	ks = new java.util.Set<K>() {

    			@Override
    			public boolean isEmpty() {
    				return AbstractMap.this.isEmpty();
    			}

    			@Override
    			public boolean contains(Object o) {
    				for(K k : this) {
    					if(k == o) {
    						return true;
    					}
    				}
    				return false;
    			}

    			@Override
    			public Iterator<K> iterator() {
    				Iterator<java.util.Map.Entry<K, V>> it = AbstractMap.this.iterator();
    				return new Iterator<K>() {

    					@Override
    					public boolean hasNext() {
    						return it.hasNext();
    					}

    					@Override
    					public K next() {
    						return it.next().getKey();
    					}
    					@Override
    					public void remove() {
    						it.remove();
    					}
    				};
    			}

    			@Override
    			public boolean add(K e) {
    				throw new UnsupportedOperationException();
    			}

    			@Override
    			public boolean remove(Object o) {
    				Iterator<?> it = iterator();
    				if (o == null) {
    					while (it.hasNext()) {
    						if (it.next() == null) {
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
    			public boolean containsAll(java.util.Collection<?> c) {
    				for (Object e : c)
    					if (!contains(e))
    						return false;
    				return true;
    			}

    			@Override
    			public boolean addAll(java.util.Collection<? extends K> c) {
    				throw new UnsupportedOperationException();
    			}

    			@Override
    			public boolean removeAll(java.util.Collection<?> c) {
    				Objects.requireNonNull(c);
    				boolean modified = false;
    				Iterator<?> it = iterator();
    				while (it.hasNext()) {
    					if (c.contains(it.next())) {
    						it.remove();
    						modified = true;
    					}
    				}
    				return modified;
    			}

    			@Override
    			public boolean retainAll(java.util.Collection<?> c) {
    				Objects.requireNonNull(c);
    				boolean modified = false;
    				Iterator<?> it = iterator();
    				while (it.hasNext()) {
    					if (!c.contains(it.next())) {
    						it.remove();
    						modified = true;
    					}
    				}
    				return modified;
    			}

    			@Override
    			public void clear() {
    				 AbstractMap.this.clear();
    			}

				@Override
				@Deprecated
				public int size() {
					return 0;
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
    public java.util.Collection<V> values() {
    	java.util.Collection<V> values = this.values;
    	if(values == null) {
    		values = new java.util.Collection<V>() {
    			
				@Override
				public boolean isEmpty() {
					return AbstractMap.this.isEmpty();
				}

				@Override
				public boolean contains(Object o) {
					for(V v : this) {
    					if(v == o) {
    						return true;
    					}
    				}
    				return false;
				}

				@Override
				public Iterator<V> iterator() {
					Iterator<java.util.Map.Entry<K, V>> it = AbstractMap.this.iterator();
    				return new Iterator<V>() {

    					@Override
    					public boolean hasNext() {
    						return it.hasNext();
    					}

    					@Override
    					public V next() {
    						return it.next().getValue();
    					}
    					@Override
    					public void remove() {
    						it.remove();
    					}
    				};
				}

				@Override
				public boolean add(V e) {
    				throw new UnsupportedOperationException();
				}

				@Override
				public boolean remove(Object o) {
    				Iterator<V> it = iterator();
    				if (o == null) {
    					while (it.hasNext()) {
    						if (it.next() == null) {
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
				public boolean containsAll(java.util.Collection<?> c) {
					for (Object e : c)
    					if (!contains(e))
    						return false;
    				return true;
				}

				@Override
				public boolean addAll(java.util.Collection<? extends V> c) {
    				throw new UnsupportedOperationException();
				}

				@Override
				public boolean removeAll(java.util.Collection<?> c) {
    				Objects.requireNonNull(c);
    				boolean modified = false;
    				Iterator<?> it = iterator();
    				while (it.hasNext()) {
    					if (c.contains(it.next())) {
    						it.remove();
    						modified = true;
    					}
    				}
    				return modified;
				}

				@Override
				public boolean retainAll(java.util.Collection<?> c) {
					Objects.requireNonNull(c);
    				boolean modified = false;
    				Iterator<?> it = iterator();
    				while (it.hasNext()) {
    					if (!c.contains(it.next())) {
    						it.remove();
    						modified = true;
    					}
    				}
    				return modified;
				}

				@Override
				public void clear() {
					AbstractMap.this.clear();
				}

				@Override
				@Deprecated
				public int size() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				@Deprecated
				public Object[] toArray() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				@Deprecated
				public <T> T[] toArray(T[] a) {
					// TODO Auto-generated method stub
					return null;
				}
			};
			this.values = values;
    	}
        return values;
    }
	@Override
	public java.util.Set<Entry<K,V>> entrySet() {
		return this;
	}
	protected final class MapIterator implements Iterator<Entry<K,V>> {
		
		/**
		 * The current time-listener.
		 */
		protected Collection<Entry<K,V>> current;
		
		/**
		 * The next time-listener.
		 */
		protected Collection<Entry<K,V>> next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		protected boolean hasNext;
		
		public MapIterator(Collection<Entry<K,V>> key) {
			next = current = key;
			hasNext = true;
		}
		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public Entry<K,V> next() {
			Collection<Entry<K,V>> k = next;
			current = k;
			next = k.getParent();
			if(k == AbstractMap.this)
				hasNext = false;
			else hasNext = true;
			return k.getElement();
		}
		@Override
		public void remove() {
			Collection<Entry<K,V>> k = next;
			current.clear();
			if(!k.isEmpty()) {
				current = k;
				next = k.getParent();
			}
			else hasNext = false;
		}
	}
}