package hyperspace.time;

import hyperspace.Listener;

public interface Past<K> extends Listener {
	
	K getParent();
	K setParent(K key);
}