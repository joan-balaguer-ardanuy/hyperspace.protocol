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
	public K put(K key) {
		return getChild().setChild(key);
	}
	@Override
	public V get() {
		return getChild().call();
	}
	@Override
	public V set(V value) {
		return getChild().put(value);
	}
	
	/**
	 * {@link Child} default class constructor.
	 */
	public Child() {
		super();
	}
	/**
	 * {@link Child} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 */
	public Child(Class<? extends K> type, String name) {
		super(type, name);
	}
	/**
	 * {@link Child} class constructor.
	 * @param type {@link Class} the type
	 * @param name {@link String} the name
	 * @param value the child
	 */
	public Child(Class<? extends K> type, String name, V value) {
		super(type, name, value);
		value.setChild(getParent());
		value.setParent(value);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the parent
	 */
	public Child(K key) {
		super(key);
	}
	/**
	 * {@link Child} class constructor.
	 * @param key the parent
	 * @param value the child
	 */
	public Child(K key, V value) {
		super(key, value);
		key.put(getType().cast(this));
	}
	

	@Override
	public void run() {
		super.run();
		// execute the future
		execute(getChild());
	}
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		try {
			if(mayInterruptIfRunning && Thread.currentThread().isAlive())
				setCommand(Command.TRANSFER);
			return true;
		}
		catch(Throwable t) {
			return false;
		}
	}
	@Override
	public boolean isCancelled() {
		return getCommand() == Command.TRANSFER;
	}
	@Override
	public boolean isDone() {
		return getCommand() == Command.TRANSFER;
	}
	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return getChild();
	}

}