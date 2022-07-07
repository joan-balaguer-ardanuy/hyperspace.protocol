package hyperspace.recurrent;

import hyperspace.TimeListener;

public interface Collection<E,T> extends TimeListener<T,T>, java.util.Collection<E> {

	E getElement();
	E setElement(E element);
}
