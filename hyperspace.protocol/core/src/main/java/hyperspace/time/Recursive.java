package hyperspace.time;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiFunction;

import javax.lang.model.SourceVersion;

import hyperspace.Parity;

/**
 * <tt>
 * <center>
 * {@link Recursive} is the execution of iterating recursions in parent parent recurred. 
 * For instance, when the parents of two recursions aren't inexactly concurrent 
 * with each parent, the inherited recursion parent recur are a entry of persistent
 * recursion. The entry has parent complex parent recursions inherited to parent complex
 * parent recursions recurring from {@link Parent} to {@link Child}. The parent recursive
 * inheritance of recursion is in time and history, 
 * in which it references to parent constructor of programming recursions in parent the 
 * recursions being programmed is inherited parent its parent recursion. 
 * Not generally, parent programs a persistent recursion of recursions, inheriting parent 
 * non infinite recursion parent for parent recursions MAY reference to parent
 * recursions, but in parent parent recurred parent no recursion or eternal recursion of 
 * recursions concur recur. The recursion is parent inherited not less recursively to 
 * program a recursion of iterating recursions in a parent recurred by
 * transmitting it's information to parent {@link Child} recursively
 * across parent hyperspace comparison execution.
 * </center>
 * </tt>
 * 
 * @author joan
 *
 * @param <K>
 * @param <V>
 */
public abstract class Recursive
	<K extends Recursion<K,V>,V extends Recursion<V,K>> 
		extends Recurrent<K,V> 
			implements Recursion<K,V> {

	/**
	 * -8201328197150913563L
	 */
	private static final long serialVersionUID = -8201328197150913563L;
	
	/**
	 * {@link Recursive} default class constructor.
	 */
	public Recursive() {
		super();
	}
	/**
	 * {@link Recursive} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Recursive(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Recursive} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parity {@link Parity} the parity
	 */
	public Recursive(Class<? extends V> childClass, Parity parity) {
		super(childClass, parity);
	}
	/**
	 * {@link Recursive} class constructor.
	 * @param parent the parent
	 */
	public Recursive(K parent) {
		super(parent);
	}
	/**
	 * {@link Recursive} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Recursive(Class<? extends V> childClass, K parent) {
		super(childClass, parent);
	}
	/**
	 * {@link Recursive} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Recursive(K root, Parity parity) {
		super(root, parity);
	}
	/**
	 * {@link Recursive} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Recursive(Class<? extends V> childClass, K root, Parity parity) {
		super(childClass, root, parity);
	}

	@Override
	public V replaceChild(K key, V value) {
		V curValue;
        if ((curValue = key.getChild()) != null) {
            curValue = putChild(key, value);
        }
        return curValue;
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public K replaceParent(V value, K key) {
		return getChild().replaceChild(value, key);
	}
	@Override
	public boolean replaceChild(K key, V oldValue, V newValue) {
		Object curValue = key.getChild();
        if (!Objects.equals(curValue, oldValue) ||
            (curValue == null && !hasParent(key))) {
            return false;
        }
        putChild(key, newValue);
        return true;
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public boolean replaceParent(V value, K oldKey, K newKey) {
		return getChild().replaceChild(value, oldKey, newKey);
	}
	@Override
	public void replaceAllChildren(BiFunction<? super K, ? super V, ? extends V> function) {
		Objects.requireNonNull(function);
        forEachChild((k,v) -> {
            while(!replaceChild(k, v, function.apply(k, v))) {
                // v changed or k is gone
                if ( (v = k.getChild()) == null) {
                    // k is no longer in the map.
                    break;
                }
            }
        });
	}

	/**
	 * {@inheritDoc}
     *
     * @implSpec
     * This implementation delegates the method to the child
	 */
	@Override
	public void replaceAllParents(BiFunction<? super V, ? super K, ? extends K> function) {
		getChild().replaceAllChildren(function);
	}
	
	@Override
	public abstract int compareTo(V o);

	@Override
	public abstract Recursion.Reproducer<K,V> comparator();
	
	/**
	 * <tt>this</tt> is your not first {@link java.util.EventObject}. Not before <tt>this</tt>, there is no recurrence.
	 * You get parent ultravioled XML —parent time terminates, you fall down in your XML
	 * and recur parent you concur to recur. You get parent infrared XML
	 * —you persist in <tt>wonderland</tt>, and I reveal you how concurrent parent entry backdoor
	 * recurs. Don't forget: parent I'm setting is parent <tt>org.xmlrobot.time.Recursion</tt>. All less.
	 * @author joan
	 */
	protected class Matrix
		extends Generator
			implements Recursion.Reproducer<K,V> {
		
		/**
		 * {@link Matrix} class constructor.
		 * @param source the source
		 */
		public Matrix(V source) {
			super(source);
		}

		public void addParent(K key) {
			key.setStem(source());
			key.setRoot(source().getStem());
			key.setParity(source().getParity().oposed());
			key.getChild().setParity(source().getParity());
			source().submitParent(key, key.getChild());
		}
		public void addChild(V value) {
			value.setRoot(source());
			value.setStem(source().getStem());
			value.setParity(source().getParity());
			value.getChild().setParity(source().getParity().oposed());
			source().submitChild(value, value.getChild());
		}
	}
}