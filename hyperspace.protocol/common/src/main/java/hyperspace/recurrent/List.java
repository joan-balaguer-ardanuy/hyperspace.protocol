package hyperspace.recurrent;

public interface List<E> extends Collection<E,List<E>>, java.util.List<E> {
	
	E getElement();
	E setElement(E element);
	
	int dimension(List<E> source);
}