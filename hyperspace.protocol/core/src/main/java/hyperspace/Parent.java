package hyperspace;

import javax.xml.bind.annotation.XmlTransient;

import hyperspace.recurrent.Enumerator;

/**
 * The parent {@link AbstractListener} class.
 * It is {@link TimeListener}.
 * @author joan
 *
 */
public abstract class Parent
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends AbstractListener
			implements TimeListener<K,V> {

	/**
	 * -2358327935955938422L
	 */
	private static final long serialVersionUID = -2358327935955938422L;

	/**
	 * The key.
	 */
	private K parent;
	
	/**
	 * The value.
	 */
	private V child;

	@Override
	@XmlTransient
	public K getParent() {
		return parent;
	}
	@Override
	public K setParent(K key) {
		K old = this.parent;
		this.parent = key;
		return old;
	}
	@Override
	@XmlTransient
	public V getChild() {
		return child;
	}
	@Override
	public V setChild(V value) {
		V old = this.child;
		this.child = value;
		return old;
	}

	public Parent() {
		super();
	}
	/**
	 * {@link Parent} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Parent(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param parity {@link Parity} the parity
	 * @param child the child
	 */
	@SuppressWarnings("unchecked")
	public Parent(Parity parity, V child) {
		super(parity);
		setParent((K) this);
		setChild(child);
	}
	/**
	 * {@link Parent} class constructor.
	 * @param parent the parent
	 */
	public Parent(K parent) {
		super(parent.getParity());
		setParent(parent);
		setChild(parent.getChild());
	}
	/**
	 * {@link Parent} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	public Parent(K parent, V child) {
		super(parent.getParity());
		setParent(parent);
		setChild(child);
	}
	
	@Override
	public abstract TimeListener<K,V> clone();

	@Override
	public boolean isEmpty() {
		return getParent() == this;
	}
	
	@Override
	public Enumerator<K> enumerator() {
		return new ParentEnumerator(getParent());
	}

	protected final class ParentEnumerator implements Enumerator<K> {

		/**
		 * The current time-listener.
		 */
		K current;

		/**
		 * The next time-listener.
		 */
		K next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public ParentEnumerator(K parent) {
			next = current = parent;
			hasNext = true;
		}

		@Override
		public boolean hasMoreElements() {
			return hasNext;
		}

		@Override
		public K nextElement() {
			K c = next;
			current = c;
			next = c.getParent();
			if (c == Parent.this)
				hasNext = false;
			else
				hasNext = true;
			return c;
		}

		@Override
		public void remove() {
			K k = next;
			current.release();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
			} else
				hasNext = false;
		}
	}
}