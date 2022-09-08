package hyperspace;

public class AbstractXML
	<K extends AbstractXML<K,V>,V extends AbstractXML<V,K>> 
		extends XML 
			implements java.util.Map.Entry<K,V> {

	private static final long serialVersionUID = 2782873816943240641L;

	K key;
	V value;
	
	@Override
	public K getKey() {
		return key;
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
	
	public AbstractXML() {
		super();
	}
	public AbstractXML(String name) {
		super(name);
	}
	@SuppressWarnings("unchecked")
	public AbstractXML(String name, V value) {
		super(name);		
		this.key = (K) this;
		this.value = value;
		value.value = key;
		value.key = value;
	}
	public AbstractXML(K key, String name) {
		super(name);
		this.key = key;
		this.value = key.value;
	}
	@SuppressWarnings("unchecked")
	public AbstractXML(K key, String name, V value) {
		super(name);
		this.key = key;
		this.value = value;
		key.value.value = (K) this;
		value.value.key = key.value.value;
		value.value.value.key = value;
	}

	@Override
	public XML clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
