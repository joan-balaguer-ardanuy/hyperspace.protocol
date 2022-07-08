package hyperspace.recurrent;

import hyperspace.TimeListener;

public interface Collection<E,K> extends TimeListener<K,K>, java.util.Collection<E> {

	E getElement();
	E setElement(E element);
}
