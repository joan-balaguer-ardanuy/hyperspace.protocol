package hyperspace.recurrent;

import hyperspace.time.Recurrent;

public interface Collection<E> extends Recurrent<Collection<E>>, java.util.Collection<E> {

	E getElement();

	E setElement(E element);
}