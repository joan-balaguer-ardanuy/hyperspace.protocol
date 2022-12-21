package hyperspace.time;

public interface Future<V> {
	
	V getChild();
	void setChild(V value);
}
