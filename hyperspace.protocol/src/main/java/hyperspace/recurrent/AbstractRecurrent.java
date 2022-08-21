package hyperspace.recurrent;

import java.util.Enumeration;

import hyperspace.Listener;
import hyperspace.Time;
import hyperspace.time.Recurrent;
import jakarta.xml.bind.annotation.XmlTransient;

public class AbstractRecurrent<E extends Recurrent<E>>
	extends Time
		implements Recurrent<E>, Listener {
	
	E root;
	E parent;
	E past;
	Class<? extends E> type;

	@Override
	@XmlTransient
	public E getRoot() {
		return root;
	}
	@Override
	public void setRoot(E parent) {
		this.root = parent;
	}
	@Override
	@XmlTransient
	public E getParent() {
		return parent;
	}
	@Override
	public void setParent(E key) {
		this.parent = key;
	}
	@Override
	public E call() {
		return this.past;
	}
	@Override
	public void put(E key) {
		this.past = key;
	}
	@Override
	@XmlTransient
	public Class<? extends E> getParentClass() {
		return type;
	}
	@Override
	public void setParentClass(Class<? extends E> type) {
		this.type = type;
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

	/**
	 * Intances new object.
	 * @param <X> the parameter type of the returned object
	 * @param type the {@link Class} of the object.
	 * @param object the arguments of the construction of the object
	 * @return the new <X> instance
	 */
	protected static <X> X instance(Class<X> type, Object... objects) {
		try {
			return type.getDeclaredConstructor(getClasses(objects)).newInstance(objects);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	private static Class<?>[] getClasses(Object... objects) {
		Class<?>[] classes = new Class<?>[objects.length];
		for(int i = 0; i < objects.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
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