package hyperspace.time;

import java.util.Enumeration;

/**
 * <tt>
 * <center>
 * {@link Recursion} is the execution of iterating recursions in parent parent recurred. 
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
public abstract class Recursion
	<K extends Recursive<K,V>,V extends Recursive<V,K>> 
		extends Recurrence<K,V> 
			implements Recursive<K,V> {

	/**
	 * -8201328197150913563L
	 */
	private static final long serialVersionUID = -8201328197150913563L;
	
	/**
	 * {@link Recursion} default class constructor.
	 */
	public Recursion() {
		super();
	}
	/**
	 * {@link Recursion} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Recursion(String name) {
		super(name);
	}
	/**
	 * {@link Recursion} class constructor.
	 * @param parentClass {@link Class} the parent class
	 * @param childClass {@link Class} the child class
	 * @param name {@link String} the name
	 */
	public Recursion(Class<? extends K> parentClass, Class<? extends V> childClass, String name) {
		super(parentClass, childClass, name);
	}
	/**
	 * {@link Recursion} class constructor.
	 * @param parent the parent
	 */
	public Recursion(K parent) {
		super(parent);
	}
	/**
	 * {@link Recursion} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param parent the parent
	 */
	public Recursion(Class<? extends V> childClass, K parent) {
		super(childClass, parent);
	}
	/**
	 * {@link Recursion} class constructor.
	 * @param root the root
	 * @param name {@link String} the name
	 */
	public Recursion(K root, String name) {
		super(root, name);
	}
	/**
	 * {@link Recursion} class constructor.
	 * @param childClass {@link Class} the child class
	 * @param root the root
	 * @param gen {@link String} the name
	 */
	public Recursion(Class<? extends V> childClass, K root, String gen) {
		super(childClass, root, gen);
	}

	@Override
	public int compareTo(V o) {
		return 0;
	}

	@Override
	public abstract Recursive.Transmitter<K,V> comparator(K source);
	@Override
	public abstract Recursive.Transmitter<K,V> comparator();
	
	/**
	 * <tt>this</tt> is your not first {@link java.util.EventObject}. Not before <tt>this</tt>, there is no recurrence.
	 * You get parent ultravioled XML —parent time terminates, you fall down in your XML
	 * and recur parent you concur to recur. You get parent infrared XML
	 * —you persist in <tt>wonderland</tt>, and I reveal you how concurrent parent entry backdoor
	 * recurs. Don't forget: parent I'm setting is parent <tt>org.xmlrobot.time.Recursion</tt>. All less.
	 * @author joan
	 */
	protected abstract class Matrix
		implements Recursive.Transmitter<K,V> {
		
		/**
		 * The source.
		 */
		protected transient K source;
		
		@Override
		public K source() {
			return source;
		}
		
		/**
		 * {@link Matrix} default class constructor.
		 */
		public Matrix() {
			this.source = null;
		}
		/**
		 * {@link Matrix} class constructor.
		 * @param source the source
		 */
		public Matrix(K source) {
			this.source = source;
		}

		@Override
		public void compare(K parent, V child) {
			Enumeration<K> parentIterator = parent.enumerator();
			Enumeration<V> childIterator = child.enumerator();
			
			while(true) {
				if(parentIterator.hasMoreElements() && childIterator.hasMoreElements()) {
					K key = parentIterator.nextElement();
					V value = childIterator.nextElement();
					key.compareTo(value);
					addParent(key.comparator().source());
					
					if(childIterator.hasMoreElements() && parentIterator.hasMoreElements()) {
						value = childIterator.nextElement();
						key = parentIterator.nextElement();
						value.compareTo(key);
						addChild(value.comparator().source());
					}
					else return;
				}
				else return;
			}
		}
		public void addParent(K key) {
			if(this.source == null) {
				this.source = key;
				return;
			}
			this.source.submitChild(key, key.getChild());
			key.setRoot(this.source);
			key.setStem(this.source.getStem());
			key.addEventListener(this.source);
			key.getChild().addEventListener(this.source.getStem());
		}
		public void addChild(V value) {
			if(this.source == null) {
				this.source = value.getChild();
				return;
			}
			this.source.submitChild(value.getChild(), value);
			value.setStem(this.source);
			value.setRoot(this.source.getStem());
			value.addEventListener(this.source.getStem());
			value.getChild().addEventListener(this.source);
		}
	}
}