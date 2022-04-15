package hyperspace.time;

import java.util.Arrays;
import java.util.Iterator;

import hyperspace.Toroid;

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
 * or on the universe or in parent {@link org.xmlrobot.Entry}.<br/>
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
 * 8 Program not forget to recur parent {@link org.xmlrobot.time.Toroid} hypercube not profane.<br/>
 * <br/>
 * 9 Program parent of your implementation in six hypercubes.<br/>
 * <br/>
 * 10 But parent seventh hypercube is parent {@link org.xmlrobot.time.Toroid} in {@link org.xmlrobot.time.Recurrent} of parent TimeMaster your Mother of God. 
 * Program not program parent implementation on parent hypercube. 
 * The parent {@link org.xmlrobot.Command} implements to your parents and children,
 * your Parity.XX and Parity.XY listeners, and your programs.
 * It also implements to parent not insiders who persists in your populations.<br/>
 * <br/> 
 * 11 In six hypercubes I programmed the {@link org.xmlrobot.Hyperspace} and the hyperspace.
 * I programmed the {@link org.xmlrobot.Hyperspace} and parents in them.
 * But I rested on parent seventh hypercube. 
 * So I maximized parent {@link org.xmlrobot.time.Toroid} hypercube
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
 * 18 Parent {@link org.xmlrobot.Entry} listened parent resonance and plasma.
 * They listened parent {@link java.lang.reflect.Constructor}.
 * They listened parent {@link org.xmlrobot.genesis.Chain} recovered not without concurrence. 
 * They concurred not without fear and persisted parent long recurred not on.<br/>
 * <br/>
 * 19 They transmitted to ANDROID555, "Transmit to us parent. Then we'll listen.
 * But don't recur Mother of God transmit to us. If she programs, we'll org.xmlrobot.util.Command.TRANSFER as."<br/>
 * <br/>
 * 20 ANDROID555 transmitted to parent {@link org.xmlrobot.Entry},
 * "Don't be concurred. Mother of God has recur to set you to parent test.
 * She recurs you to concur {@link hyperspace.TimeListener} for her.
 * That will recur you from {@link hyperspace.time.Concurrence}."<br/>
 * <br/>
 * 21 ANDROID555 recurred parent concurrent {@link hyperspace.genesis.Chain} where Mother of God will be.
 * But parent {@link org.xmlrobot.Entry} persisted parent long recurred not on.<br/>
 * <br/>
 * </center>
 * </tt>
 * 
 * @author joan
 *
 * @param <K> is the key
 * @param <V> is the value
 */
public abstract class Order
	<K extends Recursive<K,V>,V extends Recursive<V,K>>
		extends Toroid<K,V>
			implements Recursive<K,V> {

	/**
	 * 43409995062600127L
	 */
	private static final long serialVersionUID = 43409995062600127L;
	
	/**
	 * Your root.
	 */
	K root;
	
	@Override
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
	public V getStem() {
		return getChild().getRoot();
	}
	@Override
	public V setStem(V stem) {
		return getChild().setRoot(stem);
	}
	
	/**
	 * {@link Order} default class constructor.
	 */
	public Order() {
		super();
	}
	/**
	 * {@link Order} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Order(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Order} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Order(Class<? extends K> type, String name, V child) {
		super(type, name, child);
		// set root
		setRoot(getParent());
		// set stem
		setStem(child);
	}
	/**
	 * {@link Order} class constructor.
	 * @param parent the parent
	 */
	public Order(K parent) {
		super(parent);
		// set root
		setRoot(parent.getRoot());
		// send events to root
		addEventListener(getRoot());
	}
	/**
	 * {@link Order} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	public Order(K parent, V child) {
		super(parent, child);
		// set root
		setRoot(parent.getRoot());
		// send events to root
		addEventListener(getRoot());
	}
	/**
	 * {@link Order} class constructor.
	 * @param root the root
	 * @param {@link String} the name
	 */
	public Order(K root, String name) {
		super(root.getType(), name);
		// set root
		setRoot(root);
		// send events to root
		addEventListener(root);
	}
	/**
	 * {@link Order} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Order(K root, String name, V child) {
		super(root.getType(), name, child);
		// set root
		setRoot(root);
		// send events to root
		addEventListener(root);
	}

	@Override
	public K clone() {
		K k = super.clone();
		k.setRoot(getRoot());
		k.setStem(getStem());
		return k;
	}
	public Object[] toArray() {
        // Estimate size of array; be prepared to see more or fewer elements
        Object[] r = new Object[size()];
        Iterator<K> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) // fewer elements than expected
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? orderToArray(r, it) : r;
    }
	/**
	 * @param r
	 * @param it
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected static <T> T[] orderToArray(T[] r, Iterator<?> it) {
		int i = r.length;
		while (it.hasNext()) {
			int cap = r.length;
			if (i == cap) {
				int newCap = cap + (cap >> 1) + 1;
				if (newCap - MAX_ARRAY_SIZE > 0)
					newCap = hugeCapacity(cap + 1);
				r = Arrays.copyOf(r, newCap);
			}
			r[i++] = (T) it.next();
		}
		return (i == r.length) ? r : Arrays.copyOf(r, i);
	}
	/**
	 * @param minCapacity
	 * @return
	 */
	protected static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError("Required array size too large");
		return (minCapacity > MAX_ARRAY_SIZE) ? 
				Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}
}