/**
 * 
 */
package hyperspace;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public abstract class Hyperspace
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Child<K,V>
			implements TimeListener<K,V> {

	/**
	 * 4428935997216883052L
	 */
	private static final long serialVersionUID = 4428935997216883052L;

	/**
	 * {@link Hyperspace} class constructor.
	 */
	public Hyperspace() {
		super();
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Hyperspace(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param parity {@link Parity} the parity
	 * @param child the child
	 */
	public Hyperspace(Parity parity, V child) {
		super(parity, child);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param parent the parent
	 */
	public Hyperspace(K parent) {
		super(parent);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	public Hyperspace(K parent, V child) {
		super(parent, child);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 */
	public Hyperspace(K root, Parity parity) {
		super(parity);
	}
	/**
	 * {@link Hyperspace} class constructor.
	 * @param root the root
	 * @param parity {@link Parity} the parity
	 * @param child the child
	 */
	public Hyperspace(K root, Parity parity, V child) {
		super(parity, child);
	}
	
	@Override
	public abstract int compareTo(V o);
	
	@Override
	public abstract Transmitter<K,V> comparator();
	
	@Override
	public abstract Transmitter<K,V> comparator(V source);
	
	/**
	 * Information generator class. Implements {@code TimeListener.Transmitter}.
	 * @author joan
	 */
	public abstract class Generator implements TimeListener.Transmitter<K, V> {
		
		V source;
		
		@Override
		public V source() {
			return source;
		}
		
		public Generator(V source) {
			this.source = source;
		}
		
		@Override
		public void compare(K parent, V child) {
			K p = parent.getParent();
			V c = child.getParent();
			do {
				p.compareTo(c);
				addChild(p.comparator().source());
				p = p.getParent();
				c = c.getParent();
				if ((p != parent && c != child)) {
					c.compareTo(p);
					addParent(c.comparator().source());
					p = p.getParent();
					c = c.getParent();
				}
			} while (p != parent && c != child);
		}

		@Override
		public abstract void addChild(V child);

		@Override
		public abstract void addParent(K parent);
	}
}