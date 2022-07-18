package hyperspace.recurrent;

import java.util.Iterator;

import hyperspace.XML;
import hyperspace.time.Time;

public class AbstractCollection<E> 
	extends Time<Mapping<E,Collection<E>>,Mapping<E,Collection<E>>>
		implements CollectionMap<E> {

	private static final long serialVersionUID = 8031826521414991529L;

	E key;
	Collection<E> value;
	
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
	public Collection<E> getValue() {
		return value;
	}
	@Override
	public Collection<E> setValue(Collection<E> value) {
		Collection<E> old = this.value;
		this.value = value;
		return old;
	}
	
	public AbstractCollection() {
		super();
	}
	public AbstractCollection(XML message) {
		super(message);
	}
	public AbstractCollection(Class<? extends CollectionMap<E>> type, XML message, E key, Collection<E> value) {
		super(type, type, message);
		this.key = key;
		this.value = value;
	}
	public AbstractCollection(CollectionMap<E> parent) {
		super(parent);
	}
	public AbstractCollection(CollectionMap<E> parent, E key, Collection<E> value) {
		super(parent.getParentClass(), parent);
		this.key = key;
		this.value = value;
	}
	public AbstractCollection(CollectionMap<E> root, XML message) {
	  	super(root, message);
	}
	public AbstractCollection(CollectionMap<E> root, XML message, E key, Collection<E> value) {
		super(root.getParentClass(), root, message);
		this.key = key;
		this.value = value;
	}
	
	public AbstractCollection<E> clone() {
		return (AbstractCollection<E>) super.clone();
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
	public Transmitter<Mapping<E,Collection<E>>,Mapping<E,Collection<E>>> comparator(Mapping<E,Collection<E>> source) {
		return null;
	}
	@Override
	public Transmitter<Mapping<E,Collection<E>>, Mapping<E,Collection<E>>> comparator() {
		// TODO Auto-generated method stub
		return null;
	}
}