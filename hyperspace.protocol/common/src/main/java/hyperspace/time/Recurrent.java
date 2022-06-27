package hyperspace.time;


/**
 * The recurrent {@link Past} class.
 * 
 * @author joan
 *
 * @param <K> is the key
 */
public interface Recurrent<K> extends Past<K>, Iterable<K>, java.util.concurrent.Callable<K> {

	K getRoot();
	K setRoot(K parent);
	
	@Override
	K call();
	K put(K key);

	// methods
	K getParent(int N);
	boolean containsParent(K parent);
	boolean removeParent(K parent);
	int indexOfParent(K parent);
	int lastIndexOfParent(K parent);
	void removeParent(int N);
}