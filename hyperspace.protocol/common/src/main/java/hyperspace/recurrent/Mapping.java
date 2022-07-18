package hyperspace.recurrent;

import hyperspace.time.Recursive;

public interface Mapping<K,V> extends Recursive<Mapping<K,V>, Mapping<K,V>>, java.util.Map.Entry<K,V> {

	K setKey(K key);

}
