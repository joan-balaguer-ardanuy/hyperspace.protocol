package hyperspace.time;

public interface Past<K> {
	
	K getParent();
	void setParent(K key);
}