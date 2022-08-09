package hyperspace.time;

public interface Future<V> {
	
	V getChild();
	V setChild(V value);
}
