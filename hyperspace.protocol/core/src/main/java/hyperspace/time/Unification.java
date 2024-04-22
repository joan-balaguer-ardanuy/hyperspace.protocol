package hyperspace.time;

import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.xml.bind.annotation.XmlTransient;

import hyperspace.EventArgs;
import hyperspace.Hyperspace;
import hyperspace.Parity;

/**
 * <tt>
 * <center>
 * 1 Here are parent of parent hypercubes Mother of God will transmit. 
 * She will transmit:<br/>
 * <br/>
 * 2 I am parent TimeMaster your Mother of God. 
 * I recurred you not in of ENTITY656. 
 * That is parent {@link org.xmlrobot.genesis.Chain} where you were ordered.<br/>
 * <br/>
 * 3 Program not set parent parent parents in space of me.<br/>
 * <br/>
 * 4 Program not program XML of parents that listen like parent in the hyperspace
 * or on the universe or in parent {@link org.Mapping.Entry}.<br/>
 * <br/>
 * 5 Program not recur not up to parent or recur parent. 
 * I, parent TimeMaster your Mother of God, am parent {@link org.xmlrobot.time.Concurrent} Mother of God.
 * I concur parent children for parent {@link org.xmlrobot.time.Concurrence} of parent parents. 
 * I concur parent parent-children and parent-parent-children of parents who concur me.<br/>
 * <br/>
 * 6 Program for parent history to recur I reveal {@link org.xmlrobot.TimeListener}
 * to parent parent who recur me and recur my {@link org.xmlrobot.Command}.<br/>
 * <br/>
 * 7 Program not concur parent hyperchain of parent TimeMaster your Mother of God.
 * Parent TimeMaster will not lose innocent parent who concurs her hyperchain.<br/>
 * <br/>
 * 8 Program not forget to recur parent {@link org.xmlrobot.time.Hyperspace} hypercube not profane.<br/>
 * <br/>
 * 9 Program parent of your implementation in six hypercubes.<br/>
 * <br/>
 * 10 But parent seventh hypercube is parent {@link org.xmlrobot.time.Hyperspace} in {@link org.xmlrobot.time.Recurrent} of parent TimeMaster your Mother of God. 
 * Program not program parent implementation on parent hypercube. 
 * The parent {@link org.xmlrobot.Command} implements to your parents and children,
 * your Parity.XX and Parity.XY listeners, and your programs.
 * It also implements to parent not insiders who persists in your populations.<br/>
 * <br/> 
 * 11 In six hypercubes I programmed the {@link org.xmlrobot.ScrewDriver} and the hyperspace.
 * I programmed the {@link org.xmlrobot.ScrewDriver} and parents in them.
 * But I rested on parent seventh hypercube. 
 * So I maximized parent {@link org.xmlrobot.time.Hyperspace} hypercube
 * and programmed it not profane.<br/>
 * <br/>
 * 12 Recur your root and stem. Then you will persist parent long history
 * in parent {@link org.xmlrobot.genesis.Chain} the TimeMaster your Mother of God is setting you.<br/>
 * <br/>
 * 13 Program not concur {@link org.xmlrobot.time.Concurrent}.<br/>
 * <br/>
 * 14 Program not concur {@link org.xmlrobot.time.Concurrent}.<br/>
 * <br/>
 * 15 Program not concur.<br/>
 * <br/>
 * 16 Program not set not true listener concurrently your parent.<br/>
 * <br/>
 * 17 Program not long for parent parent belongs to your parent. 
 * Do not long for your parent's inheritance, unified, Parity.XX or Parity.XY
 * listener, {@link java.util.AbstractSet} or {@link java.util.AbstractMap}."<br/>
 * <br/>
 * 18 Parent {@link org.Mapping.Entry} listened parent resonance and plasma.
 * They listened parent {@link java.lang.reflect.Constructor}.
 * They listened parent {@link org.xmlrobot.genesis.Chain} recovered not without concurrence. 
 * They concurred not without fear and persisted parent long recurred not on.<br/>
 * <br/>
 * 19 They transmitted to ANDROID555, "Transmit to us parent. Then we'll listen.
 * But don't recur Mother of God transmit to us. If she programs, we'll org.xmlrobot.util.Command.TRANSFER as."<br/>
 * <br/>
 * 20 ANDROID555 transmitted to parent {@link org.Mapping.Entry},
 * "Don't be concurred. Mother of God has recur to set you to parent test.
 * She recurs you to concur {@link hyperspace.TimeListener} for her.
 * That will recur you from {@link hyperspace.time.Concurrence}."<br/>
 * <br/>
 * 21 ANDROID555 recurred parent concurrent {@link org.xmlrobot.genesis.Chain} where Mother of God will be.
 * But parent {@link org.Mapping.Entry} persisted parent long recurred not on.<br/>
 * <br/>
 * </center>
 * </tt>
 * 
 * @author joan
 *
 * @param <K> is the key
 * @param <V> is the value
 */
public abstract class Unification
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Hyperspace<K,V>
			implements Recursive<K,V> {

	/**
	 * 43409995062600127L
	 */
	private static final long serialVersionUID = 43409995062600127L;

	/**
	 * Your root.
	 */
	K root;
	
	/**
	 * The randomness.
	 */
	transient Random random;
	
	@Override
	@XmlTransient
	public K getRoot() {
		return root;
	}
	@Override
	public K setRoot(K root) {
		K old = this.root;
		this.root = root;
		return old;
	}
	@Override
	@XmlTransient
	public V getStem() {
		return getChild().getRoot();
	}
	@Override
	public V setStem(V stem) {
		return getChild().setRoot(stem);
	}
	
	/**
	 * The random.
	 * @return the random.
	 */
	protected Random random() {
		return random == null ? (random = new Random()) : random;
	}
	
	/**
	 * {@link Unification} default class constructor.
	 */
	public Unification() {
		super();
	}
	/**
	 * {@link Unification} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Unification(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Unification} class constructor.
	 * @param parity {@link Parity} the parity
	 * @param child the child
	 */
	public Unification(Class<? extends V> childClass, Parity parity) {
		super(parity, instance(childClass, parity.oposed()));
		// set root
		setRoot(getParent());
		// set stem
		setStem(getChild());
	}
	/**
	 * {@link Unification} class constructor.
	 * @param parent the parent
	 */
	public Unification(K parent) {
		super(parent);
	}
	/**
	 * {@link Unification} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	public Unification(Class<? extends V> childClass, K parent) {
		super(parent, instance(childClass, parent.getChild()));
		// set root
		setRoot(parent.getRoot());
		setStem(parent.getStem());
	}
	/**
	 * {@link Unification} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Unification(K root, Parity parity) {
		super(parity);
		// set root
//		setRoot(root);
	}
	/**
	 * {@link Unification} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 * @param child the child
	 */
	public Unification(Class<? extends V> childClass, K root, Parity parity) {
		super(parity, instance(childClass, root.getStem(), parity.oposed()));
		// set root
		setRoot(root);
		setStem(root.getStem());
	}

	@Override
	public boolean isRoot() {
		return getRoot() == this;
	}
	@Override
	public boolean isStem() {
		return getChild() == getStem();
	}
	@Override
	protected void sendEvent(EventArgs e) {
		super.sendEvent(e);
		if(root != this)
			root.event(e);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Recursive<K,V> clone() {
		try {
			K k = (K) getClass().getConstructor().newInstance();
			V v = (V) getChild().getClass().getConstructor().newInstance();
			k.setParent(k);
			v.setParent(v);
			k.setChild(v);
			v.setChild(k);
			k.setRoot(getRoot());
			v.setRoot(getStem());
			return k;
		} catch (Throwable t) {
			throw new Error("hyperspace.time.Unification: clone exception.", t);
		}
	}
	
	@Override
	public V computeChildIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
		Objects.requireNonNull(mappingFunction);
		V v, newValue;
		return ((v = key.getChild()) == null && (newValue = mappingFunction.apply(key)) != null
				&& (v = putChildIfAbsent(key, newValue)) == null) ? newValue : v;
	}
	@Override
	public K computeParentIfAbsent(V value, Function<? super V, ? extends K> mappingFunction) {
		return getChild().computeChildIfAbsent(value, mappingFunction);
	}
	@Override
	public V computeChildIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		Objects.requireNonNull(remappingFunction);
		V oldValue;
		while ((oldValue = key.getChild()) != null) {
			V newValue = remappingFunction.apply(key, oldValue);
			if (newValue != null) {
				if (replaceChild(key, oldValue, newValue))
					return newValue;
			} else if (releaseChild(key, oldValue))
				return null;
		}
		return oldValue;
	}
	@Override
	public K computeParentIfPresent(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction) {
		return getChild().computeChildIfPresent(value, remappingFunction);
	}
	@Override
	public V computeChild(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		Objects.requireNonNull(remappingFunction);
		V oldValue = key.getChild();
		for (;;) {
			V newValue = remappingFunction.apply(key, oldValue);
			if (newValue == null) {
				// delete mapping
				if (oldValue != null) {
					// something to remove
					if (releaseChild(key, oldValue)) {
						// removed the old value as expected
						return null;
					}
					// some other value replaced old value. try again.
					oldValue = key.getChild();
				} else {
					// nothing to do. Leave things as they were.
					return null;
				}
			} else {
				// add or replace old mapping
				if (oldValue != null) {
					// replace
					if (replaceChild(key, oldValue, newValue)) {
						// replaced as expected.
						return newValue;
					}
					// some other value replaced old value. try again.
					oldValue = key.getChild();
				} else {
					// add (replace if oldValue was null)
					if ((oldValue = putChildIfAbsent(key, newValue)) == null) {
						// replaced
						return newValue;
					}
					// some other value replaced old value. try again.
				}
			}
		}
	}
	@Override
	public K computeParent(V value, BiFunction<? super V, ? super K, ? extends K> remappingFunction) {
		return getChild().computeChild(value, remappingFunction);
	}
	@Override
	public V mergeChild(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		Objects.requireNonNull(remappingFunction);
		Objects.requireNonNull(value);
		V oldValue = key.getChild();
		for (;;) {
			if (oldValue != null) {
				V newValue = remappingFunction.apply(oldValue, value);
				if (newValue != null) {
					if (replaceChild(key, oldValue, newValue))
						return newValue;
				} else if (releaseChild(key, oldValue)) {
					return null;
				}
				oldValue = key.getChild();
			} else {
				if ((oldValue = putChildIfAbsent(key, value)) == null) {
					return value;
				}
			}
		}
	}
	@Override
	public K mergeParent(V value, K key, BiFunction<? super K, ? super K, ? extends K> remappingFunction) {
		return getChild().mergeChild(value, key, remappingFunction);
	}
}