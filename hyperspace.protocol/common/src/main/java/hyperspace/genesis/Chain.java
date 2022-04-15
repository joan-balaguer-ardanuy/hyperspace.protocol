/**
 * 
 */
package hyperspace.genesis;

import java.util.Set;

import hyperspace.Entry;

/**
 * @author joan
 *
 */
public interface Chain<K,V> extends Entry<K,V>, Set<Entry<K,V>> {

	DNA<V,K> entryDNA();
	
	
}