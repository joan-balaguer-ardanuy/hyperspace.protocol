package hyperspace;

public class XML2 
	<K extends XML2<K,V>,V extends XML2<V,K>>
		extends AbstractXML<K,V> {

	private static final long serialVersionUID = 6185309865345229452L;
	
	public XML2() {
	}
	public XML2(String name) {
		super(name);
	}
	public XML2(Class<? extends V> valueClass, String name) {
		super(name, XML.instance(valueClass, name));
	}
	public XML2(K key, String name) {
		super(key, name);
	}
	public XML2(Class<? extends V> valueClass, K key, String name) {
		super(key, name, XML.instance(valueClass, key.getValue()));
	}
}
