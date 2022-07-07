package hyperspace.recurrent;

import java.util.Iterator;
import hyperspace.time.Time;

public class Hypercollection<E> 
	extends Time<Mapping<E,java.util.Collection<E>>,Mapping<E,java.util.Collection<E>>>
		implements CollectionMapping<E> {

	private static final long serialVersionUID = 8031826521414991529L;

	E key;
	java.util.Collection<E> value;
	
	@Override
	public E getKey() {
		return key;
	}
	@Override
	public E setKey(E key) {
		E old = this.key;
		this.key = key;
		return old;
	}
	@Override
	public java.util.Collection<E> getValue() {
		return value;
	}
	@Override
	public java.util.Collection<E> setValue(java.util.Collection<E> value) {
		java.util.Collection<E> old = this.value;
		this.value = value;
		return old;
	}
	
	public Hypercollection() {
		super();
	}
	public Hypercollection(Class<? extends CollectionMapping<E>> type, String name) {
		super(type, name);
	}
	public Hypercollection(Class<? extends CollectionMapping<E>> type, String name, E key, java.util.Collection<E> value) {
		super(type, name, instance(type, type, name));
		this.key = key;
		this.value = value;
	}
	public Hypercollection(CollectionMapping<E> parent) {
		super(parent);
	}
	public Hypercollection(CollectionMapping<E> parent, E key, java.util.Collection<E> value) {
		super(parent, instance(parent.getType(), parent.getChild()));
		this.key = key;
		this.value = value;
	}
	public Hypercollection(CollectionMapping<E> root, String name) {
	  	super(root, name);
	}
	public Hypercollection(CollectionMapping<E> root, String name, E key, java.util.Collection<E> value) {
		super(root, instance(root.getType(), root.getStem(), name));
		this.key = key;
		this.value = value;
	}
	
	public Hypercollection<E> clone() {
		return (Hypercollection<E>) super.clone();
	}
	@Override
	public int size() {
		return getValue().size();
	}
	@Override
	public boolean contains(Object o) {
		return getValue().contains(o);
	}
	@Override
	public Iterator<E> iterator() {
		return getValue().iterator();
	}
    public <T> T[] toArray(T[] a) {
		return getValue().toArray(a);
	}
	@Override
	public boolean add(E e) {
		return getValue().add(e);
	}
	@Override
	public boolean remove(Object o) {
		return getValue().remove(o);
	}
	@Override
	public boolean containsAll(java.util.Collection<?> c) {
		return getValue().containsAll(c);
	}
	@Override
	public boolean addAll(java.util.Collection<? extends E> c) {
		return getValue().addAll(c);
	}
	@Override
	public boolean removeAll(java.util.Collection<?> c) {
		return getValue().removeAll(c);
	}
	@Override
	public boolean retainAll(java.util.Collection<?> c) {
        return getValue().retainAll(c);
	}

	@Override
	public Transmitter<Mapping<E, java.util.Collection<E>>, Mapping<E, java.util.Collection<E>>> comparator(
			Mapping<E, java.util.Collection<E>> source) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Transmitter<Mapping<E, java.util.Collection<E>>, Mapping<E, java.util.Collection<E>>> comparator() {
		// TODO Auto-generated method stub
		return null;
	}
}