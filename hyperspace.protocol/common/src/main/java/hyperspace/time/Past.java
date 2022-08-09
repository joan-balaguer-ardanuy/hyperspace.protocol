package hyperspace.time;

public interface Past<K> {
	
	K getParent();
	K setParent(K key);
}