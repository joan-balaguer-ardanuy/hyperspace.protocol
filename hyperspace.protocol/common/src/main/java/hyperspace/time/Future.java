package hyperspace.time;

import hyperspace.Listener;

public interface Future<V> extends Listener {
	
	V getChild();
	V setChild(V value);
}
