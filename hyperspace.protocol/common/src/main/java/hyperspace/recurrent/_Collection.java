package hyperspace.recurrent;

import hyperspace.TimeListener;

public interface _Collection<E> extends TimeListener<_Collection<E>,_Collection<E>>, java.util.Collection<E> {
	E getElement();
	E setElement(E element);
}
