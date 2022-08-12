/**
 * 
 */
package hyperspace.genesis;

import hyperspace.Entry;

/**
 * @author joan
 *
 */
public interface Chain<K,V> extends Entry<K,V>, Iterable<Entry<K,V>> {

	default DNA<V,K> entryDNA() {
		return (DNA<V,K>) getChild();
	}
}