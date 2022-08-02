package hyperspace.time;

/**
 * The recurrent {@link Past} class.
 * 
 * @author joan
 *
 * @param <K> is the key
 */
public interface Recurrent<K> extends Past<K>, Iterable<K>, java.util.concurrent.Callable<K> {

	// properties
	/**
	 * Gets the type corresponding to this past.
	 *
	 * @return the type corresponding to this past
	 * @throws Throwable if something is wrong
	 */
	Class<? extends K> getParentClass();
	
	/**
	 * Sets the type corresponding to this past with the specified type
	 * (not optional operation).
	 * 
	 * @param type new type to be stored in this past
	 * @throws Throwable if something is wrong
	 */
	void setParentClass(Class<? extends K> type);

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