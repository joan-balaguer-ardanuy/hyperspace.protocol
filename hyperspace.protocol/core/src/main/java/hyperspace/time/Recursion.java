package hyperspace.time;

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
	public Recursion(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Recursion} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param child the child
	 */
	public Recursion(Class<? extends K> type, String name, V child) {
		super(type, name, child);
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
	 * @param parent the parent
	 * @param child the child
	 */
	public Recursion(K parent, V child) {
		super(parent, child);
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
	 * @param root the root
	 * @param child the child
	 * @param gen {@link String} the name
	 */
	public Recursion(K root, String gen, V child) {
		super(root, gen, child);
	}

	@Override
	public void recurChild(K key, V value) {
		key.setParent(getParent());
		value.setParent(getChild().getParent());
		value.setChild(getParent().getChild().getChild());
		getParent().getChild().setChild(key);
		setParent(key);
		getChild().setParent(value);
	}
	@Override
	public void recurParent(V value, K key) {
		getChild().recurChild(value, key);
	}
	@Override
	public void concurChild(K key, V value) {
		call().setParent(key);
		get().setParent(value);
		value.setChild(call());
		key.setParent(getParent().call());
		value.setParent(getChild());
		put(key);
	}
	@Override
	public void concurParent(V value, K key) {
		getChild().concurChild(value, key);
	}
	@Override
	public void permuteChild(K key, V value) {
		if(key == getParent()) {
			K current = value.setChild(getChild().getChild());
			call().setParent(key);
			get().setParent(value);
			setParent(key.getParent());
			getChild().setParent(value.getParent());
			getParent().put(current);
			put(key);
			key.setParent(current);
			value.setParent(getChild());
		}
		else if(key == getChild().getChild()) {
			K current = key.setParent(getParent());
			value.setParent(getChild().getParent());
			getParent().put(key);
			put(value.getChild());
			call().setParent(current);
			get().setParent(getChild());
			value.setChild(current);
			setParent(key);
			getChild().setParent(value);
		}
		else {
			K oldParent = key.setParent(getParent());
			V oldParentChild = value.setParent(getChild().getParent());
			K oldChild = value.setChild(call());
			oldParentChild.setChild(getParent().call());
			getParent().put(key);
			setParent(oldParent);
			getChild().setParent(oldParentChild);
			call().setParent(key);
			get().setParent(value);
			put(oldChild);
			call().setParent(getParent().call());
			get().setParent(getChild());
		}
	}
	@Override
	public void permuteParent(V value, K key) {
		getChild().permuteChild(value, key);
	}
	@Override
	public void submitChild(K key, V value) {
		if(random().nextBoolean()) {
			concurChild(key, value);
		} else {
			recurChild(key, value);
		}
	}
	@Override
	public void submitParent(V value, K key) {
		getChild().submitChild(value, key);
	}
	@Override
	public void spin() {
		if(random().nextBoolean()) {
			// rotate
			permuteChild(getParent(), getChild().getParent());
		} else {
			// revolve  
			permuteChild(call(), get());
		}
	}
	
	public abstract class Reproducer extends Matrix {
		
		public Reproducer() {
			super();
		}
		public Reproducer(K key) {
			super(key);
		}
		
		@Override
		public void addParent(K key) {
			super.addParent(key);
			key.setRoot(this.source);
			key.setStem(this.source.getStem());
			key.addEventListener(this.source);
			key.getChild().addEventListener(this.source.getStem());
		}
		@Override
		public void addChild(V value) {
			super.addChild(value);
			value.setStem(this.source);
			value.setRoot(this.source.getStem());
			value.addEventListener(this.source.getStem());
			value.getChild().addEventListener(this.source);
		}
	}
}