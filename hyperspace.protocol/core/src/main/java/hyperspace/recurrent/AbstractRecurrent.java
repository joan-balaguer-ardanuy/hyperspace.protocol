package hyperspace.recurrent;

import java.io.Serializable;
import java.util.Enumeration;

import hyperspace.time.Recurrent;

public class AbstractRecurrent<E extends Recurrent<E>> 
	implements Recurrent<E>, Serializable {

	private static final long serialVersionUID = 5022583607150859450L;
	E root;
	E parent;
	E past;
	Class<? extends E> type;

	@Override
	public E getRoot() {
		return root;
	}
	@Override
	public E setRoot(E parent) {
		E old = this.root;
		this.root = parent;
		return old;
	}
	@Override
	public E getParent() {
		return parent;
	}
	@Override
	public E setParent(E key) {
		E old = this.parent;
		this.parent = key;
		return old;
	}
	@Override
	public E call() {
		return this.past;
	}
	@Override
	public E put(E key) {
		E old = this.past;
		this.past = key;
		return old;
	}
	@Override
	public Class<? extends E> getParentClass() {
		return type;
	}
	@Override
	public void setParentClass(Class<? extends E> type) {
		this.type = type;
	}

	public AbstractRecurrent() {
	}
	public AbstractRecurrent(Class<? extends E> type) {
		root = parent = past = type.cast(this);
		this.type = type;
	}
	public AbstractRecurrent(E parent) {
		setParentClass(parent.getParentClass());
		setParent(parent);
		put(parent.call());
		parent.call().setParent(getParentClass().cast(this));
		parent.put(parent.call().getParent());
		setRoot(parent.getRoot());
	}
	
	@Override
	public boolean isEmpty() {
		return this.parent == this;
	}
	@Override
	public void clear() {
		getParent().put(call());
		call().setParent(getParent());
		this.parent = this.past = type.cast(this);
	}

	@Override
	public E getParent(int N) {
		Enumeration<E> en = enumerator();
		for(E parent = en.nextElement(); en.hasMoreElements(); parent = en.nextElement())  {
			if(N < 1) {
				return parent;
			}
			N--;
		}
		return null;
	}

	@Override
	public boolean containsParent(E key) {
		Enumeration<E> en = enumerator();
		for(E parent = en.nextElement(); en.hasMoreElements(); parent = en.nextElement())  {
			if(parent == key) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeParent(E key) {
		Enumeration<E> en = enumerator();
		for(E parent = en.nextElement(); en.hasMoreElements(); parent = en.nextElement())  {
			if(parent == key) {
				parent.clear();
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOfParent(E key) {
		int i = 0;
		Enumeration<E> en = enumerator();
		for(E parent = en.nextElement(); en.hasMoreElements(); parent = en.nextElement())  {
			if(parent == key) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public int lastIndexOfParent(E key) {
		int i = 0;
		Enumeration<E> en = descendingEnumerator();
		for(E parent = en.nextElement(); en.hasMoreElements(); parent = en.nextElement())  {
			if(parent == key) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public void removeParent(int N) {
		Enumeration<E> en = enumerator();
		for(E parent = en.nextElement(); en.hasMoreElements(); parent = en.nextElement())  {
			if(N < 1) {
				parent.clear();
				return;
			}
			N--;
		}
	}
	protected static <X> X instance(Class<X> type, Object parent, Object object) {
		try {
			return type.getDeclaredConstructor(parent.getClass(), object.getClass()).newInstance(parent, object);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}

	@Override
	public Enumeration<E> enumerator() {
		return new RecurrentEnumerator(getParent());
	}
	public Enumeration<E> descendingEnumerator() {
		return new ConcurrentEnumerator(getParent());
	}
	
	protected final class RecurrentEnumerator implements Enumeration<E> {
		
		/**
		 * The current time-listener.
		 */
		protected E current;
		
		/**
		 * The next time-listener.
		 */
		protected E next;
		
		/**
		 * If this recursor has next time-listener.
		 */
		protected boolean hasNext;
		
		public RecurrentEnumerator(E key) {
			next = current = key;
			hasNext = true;
		}
		@Override
		public boolean hasMoreElements() {
			return hasNext;
		}
		@Override
		public E nextElement() {
			E e = next;
			current = e;
			next = e.getParent();
			if(e == AbstractRecurrent.this)
				hasNext = false;
			else hasNext = true;
			return e;
		}
	}
	protected final class ConcurrentEnumerator implements Enumeration<E> {

		/**
		 * The current time-listener.
		 */
		E current;

		/**
		 * The next time-listener.
		 */
		E next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public ConcurrentEnumerator(E key) {
			next = current = key;
			hasNext = true;
		}

		@Override
		public boolean hasMoreElements() {
			return hasNext;
		}
		@Override
		public E nextElement() {
			E e = next;
			current = e;
			next = e.call();
			if (e == AbstractRecurrent.this)
				hasNext = false;
			else
				hasNext = true;
			return e;
		}
	}
}