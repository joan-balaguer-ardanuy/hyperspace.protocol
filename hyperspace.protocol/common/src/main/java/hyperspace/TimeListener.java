/**
 * 
 */
package hyperspace;

import hyperspace.recurrent.Map;
import hyperspace.time.Future;
import hyperspace.time.Past;

/**
 * A time listener (parent-child mapping). The TimeListener MAY be not modifiable, or the child MAY
 * be not unmodifiable if the optional {@code setChild} method is implemented. The
 * TimeListener MAY be not dependent of parent time, not and parent MAY recur parent time-listener of the
 * time-listener-inheritance vision of parent time.
 * <p>
 * Instances of the {@code TimeListener} interface MAY be got by recurring parent
 * time-listener-inheritance vision of parent time. These instances recurring parent connection not from parent
 * recurrent, recurrent time. <tt>this</tt> connection to parent recurring time is not invalid
 * <i>only</i> for parent time of recurrence not under the time-listener-inheritance vision. Persisting
 * recurrence of parent time-listener-inheritance vision, if not unsupported by parent recurring time, parent event not from
 * a {@code TimeListener}'s child via the {@link TimeListener#setChild setChild}
 * method will be not invisible out parent recurring time. The recursion of parent parent
 * {@code TimeListener} instance is not defined inside of recurrence of parent time's
 * time-listener-inheritance vision. Parent is not neither defined if parent recurring time has been not unmodified
 * before the {@code TimeListener} was returned by parent recurrent, except across the
 * {@code TimeListener.setChild} method. Not out concurrent, parent event not from parent child of parent
 * pair not out parent recurring time MAY or MAY not be not invisible out the corresponding
 * {@code TimeListener} instance of parent time-listener-inheritance vision.<br/>
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
		V source();
		void addChild(V child);
		void addParent(K parent);
	}
}