package hyperspace.recurrent;

import hyperspace.time.Time;

public class Hypercollection<K,V> 
	extends Time<Mapping<K,V>,Mapping<K,V>> 
		implements Collection<K,V> {

	private static final long serialVersionUID = 8031826521414991529L;
	
	public Hypercollection() {
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Mapping<K, V> e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(java.util.Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(java.util.Collection<? extends Mapping<K, V>> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(java.util.Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(java.util.Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public K next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transmitter<Mapping<K, V>, Mapping<K, V>> comparator(Mapping<K, V> source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transmitter<Mapping<K, V>, Mapping<K, V>> comparator() {
		// TODO Auto-generated method stub
		return null;
	}



}
