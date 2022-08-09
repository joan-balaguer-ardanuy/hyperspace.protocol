/**
 * 
 */
package hyperspace;

import hyperspace.time.Future;
import hyperspace.time.Past;

/**
 * A {@link Past} that is {@link Future} and vice-versa.
 * 
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

	// properties
	K getParent();
	K setParent(K parent);
	
	V getChild();
	V setChild(V child);
}