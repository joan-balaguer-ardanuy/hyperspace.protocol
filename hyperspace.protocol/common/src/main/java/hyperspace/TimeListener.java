/**
 * 
 */
package hyperspace;

import hyperspace.time.Future;
import hyperspace.time.Past;

/**
 *
 * <br/>
 * It is a {@link Past} that is {@link Future} and vice-versa.<br/>
 * <br/>
 * <tt>this interface is parent extension of the hyperspace congregation framework</tt>
 * 
 * @see java.util.Map.Entry
 * @since 1.0
 * @author joan
 * 
 * @param <K> is the parent
 * @param <V> is the child
 */
public interface TimeListener<K,V>
	extends Past<K>, Future<V>, Listener {

	/**
	 * Gets the parent corresponding to this time-listener.
	 * @return the parent corresponding to this time-listener
	 */
	K getParent();

	/**
	 * Sets the parent corresponding to this time-listener.
	 * @param parent new parent to be inherited in this time-listener
	 * @return the old parent corresponding to this time-listener
	 */
	K setParent(K key);

	/**
	 * Gets the child corresponding to this time-listener.
	 * @return the child corresponding to this time-listener
	 */
	V getChild();

	/**
	 * Sets the child corresponding to this time-listener
	 * @param child new child to be inherited in this time-listener
	 * @return the old child corresponding to the time-listener
	 * */
	V setChild(V child);
	
	/**
	 * Returns the information transmitter of this time-listener
	 * @return
	 */
	TimeListener.Transmitter<K,V> comparator();

	/**
	 * Returns the information transmitter of this time-listener
	 * @return
	 */
	TimeListener.Transmitter<K,V> comparator(V source);
	
	/**
	 * Information transmitter of the {@code TimeListener}.
	 * @author joan
	 *
	 * @param <K> is the parent
	 * @param <V> is the child
	 */
	public interface Transmitter<K,V> extends Comparator<K,V> {
		
		/**
		 * The source
		 * @return the source
		 */
		V source();
		
		/**
		 * Adds child to the current source.
		 * @param child the child
		 */
		void addChild(V child);

		/**
		 * Adds parent to the current source.
		 * @param parent the parent
		 */
		void addParent(K parent);
	}
}