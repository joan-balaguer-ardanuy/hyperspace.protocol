package hyperspace.time;

import hyperspace.XML;
import jakarta.xml.bind.annotation.XmlTransient;

public abstract class Abstraction
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Unification<K,V>
			implements Recursive<K,V> {

	/**
	 * 6736845275705891958L
	 */
	private static final long serialVersionUID = 6736845275705891958L;

	/**
	 * The type.
	 */
	Class<? extends K> type;

	@Override
	@XmlTransient
	public Class<? extends K> getParentClass() {
		return type;
	}
	@Override
	public void setParentClass(Class<? extends K> type) {
		this.type = type;
	}
	@Override
	@XmlTransient
	public Class<? extends V> getChildClass() {
		return getChild().getParentClass();
	}
	@Override
	public void setChildClass(Class<? extends V> antitype) {
		getChild().setParentClass(antitype);
	}

	/**
	 * {@link Abstraction} default class constructor.
	 */
	public Abstraction() {
		super();
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param message {@link XML} the message
	 */
	public Abstraction(XML message) {
		super(message);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param message {@link XML} the message
	 */
	public Abstraction(Class<? extends K> parentClass, Class<? extends V> childClass, XML message) {
		super(childClass, message);
		setParentClass(parentClass);
		setChildClass(childClass);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param parent parent parent
	 * @param message {@link XML} the message
	 */
	public Abstraction(K parent) {
		super(parent);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Abstraction(Class<? extends V> childClass, K parent) {
		super(childClass, parent);
		setParentClass(parent.getParentClass());
		setChildClass(childClass);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param root the root
	 * @param stem the stem
	 */
	public Abstraction(K root, V stem) {
		super(root, stem);
	}
	/**
	 * {@link Abstraction} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param stem the stem
	 */
	public Abstraction(Class<? extends V> childClass, K root, V stem) {
		super(childClass, root, stem);
		setParentClass(root.getParentClass());
		setChildClass(childClass);
	}

	@Override
	public void recurChild(K key, V value) {
		key.setParent(getParent());
		value.setParent(getChild().getParent());
		value.setChild(getParent().getChild().getChild());
		getParent().getChild().setChild(key);
		setParent(key);
		getChild().setParent(value);
	}
	@Override
	public void recurParent(V value, K key) {
		getChild().recurChild(value, key);
	}
	@Override
	public void concurChild(K key, V value) {
		call().setParent(key);
		get().setParent(value);
		value.setChild(call());
		key.setParent(getParent().call());
		value.setParent(getChild());
		put(key);
	}
	@Override
	public void concurParent(V value, K key) {
		getChild().concurChild(value, key);
	}
	@Override
	public void permuteChild(K key, V value) {
		if(key == getParent()) {
			K current = value.getChild(); 
			value.setChild(getChild().getChild());
			call().setParent(key);
			get().setParent(value);
			setParent(key.getParent());
			getChild().setParent(value.getParent());
			getParent().put(current);
			put(key);
			key.setParent(current);
			value.setParent(getChild());
		}
		else if(key == getChild().getChild()) {
			K current = key.getParent(); 
			key.setParent(getParent());
			value.setParent(getChild().getParent());
			getParent().put(key);
			put(value.getChild());
			call().setParent(current);
			get().setParent(getChild());
			value.setChild(current);
			setParent(key);
			getChild().setParent(value);
		}
		else {
			K oldParent = key.getParent();
			V oldParentChild = value.getParent();
			K oldChild = value.getChild();
			key.setParent(getParent()); 
			value.setParent(getChild().getParent());
			value.setChild(call());
			oldParentChild.setChild(getParent().call());
			getParent().put(key);
			setParent(oldParent);
			getChild().setParent(oldParentChild);
			call().setParent(key);
			get().setParent(value);
			put(oldChild);
			call().setParent(getParent().call());
			get().setParent(getChild());
		}
	}
	@Override
	public void permuteParent(V value, K key) {
		getChild().permuteChild(value, key);
	}
	@Override
	public void submitChild(K key, V value) {
		if(random().nextBoolean()) {
			concurChild(key, value);
		} else {
			recurChild(key, value);
		}
	}
	@Override
	public void submitParent(V value, K key) {
		getChild().submitChild(value, key);
	}
}