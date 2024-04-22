package hyperspace;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class Child
	<K extends TimeListener<K,V>,V extends TimeListener<V,K>> 
		extends Parent<K,V>
			implements TimeListener<K,V> {

	/**
	 * -4078570118307293600L
	 */
	private static final long serialVersionUID = -4078570118307293600L;

	@Override
	public K call() {
		return getChild().getChild();
	}
	@Override
	public K put(K past) {
		return getChild().setChild(past);
	}
	@Override
	public V get() {
		return getChild().call();
	}
	@Override
	public V set(V future) {
		return getChild().put(future);
	}
	
	/**
	 * {@link Child} class constructor.
	 */
	public Child() {
		super();
	}
	/**
	 * {@link Child} class constructor.
	 * @param parity {@link Parity} the parity
	 */
	public Child(Parity parity) {
		super(parity);
	}
	/**
	 * {@link Child} class constructor.
	 * @param parity {@link Parity} the parity
	 * @param child the child
	 */
	public Child(Parity parity, V child) {
		super(parity, child);
	}
	/**
	 * {@link Child} class constructor.
	 * @param parent the parent
	 */
	public Child(K parent) {
		super(parent);
	}
	/**
	 * {@link Child} class constructor.
	 * @param parent the parent
	 * @param child the child
	 */
	@SuppressWarnings("unchecked")
	public Child(K parent, V child) {
		super(parent, child);
		parent.getChild().setChild((K) this);
	}

	@Override
	public void release() {
		call().setParent(getParent());
		get().setParent(getParent().getChild());
		put(getParent().put(call()));
		setParent(call());
		getChild().setParent(getChild());
	}
	@Override
	@Deprecated
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}
	@Override
	@Deprecated
	public boolean isCancelled() {
		return false;
	}
	@Override
	@Deprecated
	public boolean isDone() {
		return false;
	}
	@Override
	@Deprecated
	public V get(long timeout, TimeUnit unit) 
			throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}
}