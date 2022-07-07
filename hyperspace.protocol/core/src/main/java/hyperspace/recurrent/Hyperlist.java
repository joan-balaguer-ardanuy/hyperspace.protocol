package hyperspace.recurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import hyperspace.time.Time;

public class Hyperlist<E> 
	extends Time<Mapping<E,java.util.List<E>>,Mapping<E,java.util.List<E>>>
		implements ListMapping<E> {

	private static final long serialVersionUID = -391622303135762258L;

	E key;
	java.util.List<E> value;

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
	public java.util.List<E> getValue() {
		return value;
	}
	@Override
	public java.util.List<E> setValue(java.util.List<E> value) {
		java.util.List<E> old = this.value;
		this.value = value;
		return old;
	}

	public Hyperlist() {
		super();
	}
	public Hyperlist(Class<? extends Mapping<E, java.util.List<E>>> type, String name) {
		super(type, name);
	}
	public Hyperlist(Class<? extends Mapping<E, java.util.List<E>>> type, String name, E key) {
		super(type, name, instance(type, type, name));
		this.key = key;
		this.value = new LinkedList<E>();
	}
	public Hyperlist(Mapping<E, java.util.List<E>> parent) {
		super(parent);
	}
	public Hyperlist(Mapping<E, java.util.List<E>> parent, E key) {
		super(parent, instance(parent.getType(), parent.getChild()));
		this.key = key;
		this.value = new LinkedList<E>();
	}
	public Hyperlist(ListMapping<E> root, String name) {
		super(root, name);
	}
	public Hyperlist(ListMapping<E> root, String name, E key) {
		super(root, name, instance(root.getType(), root.getStem(), name));
		this.key = key;
		this.value = new LinkedList<E>();
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
	@Override
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
	public boolean containsAll(Collection<?> c) {
		return getValue().containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return getValue().addAll(c);
	}
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return getValue().addAll(index, c);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return getValue().removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return getValue().retainAll(c);
	}
	@Override
	public E get(int index) {
		return getValue().get(index);
	}
	@Override
	public E set(int index, E element) {
		return getValue().set(index, element);
	}
	@Override
	public void add(int index, E element) {
		getValue().add(index, element);
	}
	@Override
	public E remove(int index) {
		return getValue().remove(index);
	}
	@Override
	public int indexOf(Object o) {
		return getValue().indexOf(o);
	}
	@Override
	public int lastIndexOf(Object o) {
		return getValue().lastIndexOf(o);
	}
	@Override
	public ListIterator<E> listIterator() {
		return getValue().listIterator();
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		return getValue().listIterator(index);
	}
	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		return getValue().subList(fromIndex, toIndex);
	}
	@Override
	public Transmitter<Mapping<E, java.util.List<E>>, Mapping<E, java.util.List<E>>> comparator(
			Mapping<E, java.util.List<E>> source) {
		return null;
	}
	@Override
	public Transmitter<Mapping<E, java.util.List<E>>, Mapping<E, java.util.List<E>>> comparator() {
		return null;
	}
}