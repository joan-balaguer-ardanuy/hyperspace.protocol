package hyperspace.time;

import java.util.Iterator;

import hyperspace.TimeListener;

public abstract class Time
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Inheritance<K,V>
			implements Recursive<K,V> {

	/**
	 * -1495539127840786666L
	 */
	private static final long serialVersionUID = -1495539127840786666L;
	/**
	 * {@link Time} default class constructor.<br/>
	 * 1. instance this and value;<br/>
	 * 2. put(this) and set(value);<br/>
	 * 3. value.set(this);<br/>
	 * 4. setParent(this) and setChild(value);<br/> 
	 */
	public Time() {
		super();
	}
	/**
	 * {@link Time} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Time(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Time} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Time(Class<? extends K> type, String name, V child) {
		super(type, name, child);
	}
	/**
	 * {@link Time} class constructor.
	 * @param parent the parent
	 */
	public Time(K parent) {
		super(parent);
	}
	/**
	 * {@link Time} class constructor.
	 * @param parent the key
	 * @param child the value
	 */
	public Time(K parent, V child) {
		super(parent, child);
	}
	/**
	 * {@link Time} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 */
	public Time(K root, String name) {
		super(root, name);
	}
	/**
	 * {@link Time} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param child the value
	 */
	public Time(K root, String name, V child) {
		super(root, name, child);
	}
	@Override
	public Iterator<K> iterator() {
		return new PastIterator(getRoot());
	}
	@Override
	public V putChild(K key, V value) {
		for(K listener : this){
			if(listener == key) {
				value.setParent(key.getParent().getChild());
				key.getChild().getChild().getChild().setParent(value);
				value.setChild(key.getChild().getChild());
				return key.setChild(value);
			}
		}
		submitChild(key, value);
		return null;
	}
	@Override
	public K putParent(V value, K key) {
		return getChild().putChild(value, key);
	}
	@Override
	public V putChildIfAbsent(K key, V value) {
		V v = key.getChild();
        if (v == null) {
            v = putChild(key, value);
        }
        return v;
	}
	@Override
	public K putParentIfAbsent(V value, K key) {
		return getChild().putChildIfAbsent(value, key);
	}
	public void putAllChildren(TimeListener<? extends K,? extends V> m) {
		for(TimeListener<K,V> l : m) {
			putChild(getType().cast(l), l.getChild());
		}
	}
	@Override
	public void putAllParents(TimeListener<? extends V, ? extends K> m) {
		getChild().putAllChildren(m);
	}
}