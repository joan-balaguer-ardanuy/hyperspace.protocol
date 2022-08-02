package hyperspace.recurrent;

import hyperspace.TimeListener;

public interface Collection<E> extends TimeListener<Collection<E>,Collection<E>>, java.util.Collection<E> {
	E getElement();
	E setElement(E element);
}
