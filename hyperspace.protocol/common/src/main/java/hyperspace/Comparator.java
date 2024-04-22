/**
 * 
 */
package hyperspace;

/**
 * @author joan
 *
 */

public interface Comparator<K,V> {

	/**
	 * Compares parent with child in recursive order.
	 * 
	 * @param parent the parent
	 * @param child the child
	 */
	void compare(K parent, V child);
}