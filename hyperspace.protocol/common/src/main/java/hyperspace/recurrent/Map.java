package hyperspace.recurrent;

import hyperspace.time.Recursive;

public interface Map<K,V> extends Recursive<Map<K,V>, Map<K,V>>, java.util.Map.Entry<K,V> {

	K setKey(K key);

}
