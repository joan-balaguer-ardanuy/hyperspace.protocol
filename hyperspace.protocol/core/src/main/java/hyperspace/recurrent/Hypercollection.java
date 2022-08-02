package hyperspace.recurrent;

import java.util.Iterator;
import java.util.Objects;

import hyperspace.Command;
import hyperspace.Toroid;
import hyperspace.XML;

public class Hypercollection<E>
	extends Toroid<Collection<E>,Collection<E>>
		implements Collection<E> {

	private static final long serialVersionUID = 8031826521414991529L;

	Class<? extends Collection<E>> type;

	E element;
	
	@Override
	public E getElement() {
		return element;
	}
	@Override
	public E setElement(E element) {
		E old = this.element;
		this.element = element;
		return old;
	}
	public Class<? extends Collection<E>> getType() {
		return type;
	}
	public void setType(Class<? extends Collection<E>> type) {
		this.type = type;
	}
	
	public Hypercollection() {
		super();
	}
	public Hypercollection(Class<? extends Collection<E>> type, Collection<E> key, XML message, E element) {
		super(key, message, instance(type, key.getChild(), message));
		this.type = type;
		this.element = element;
	}
	public Hypercollection(Collection<E> key, XML message) {
		super(key, message);
	}
	public Hypercollection(Class<? extends Collection<E>> type, XML message, E element) {
		super(message, instance(type, message));
		this.type = type;
		this.element = element;
	}
	public Hypercollection(XML message) {
		super(message);
	}


	@Override
	public Collection<E> clone() {
		return null;
	}
	@Override
	@Deprecated
	public int size() {
		return 0;
	}
	@Override
	public boolean contains(Object o) {
		Iterator<E> it = iterator();
        if (o==null) {
            while (it.hasNext())
                if (it.next()==null)
                    return true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }
        return false;
	}
	@Override
	public Iterator<E> iterator() {
		return new RecurrentIterator(getParent());
	}
	
    @Deprecated
    public <X> X[] toArray(X[] a) {
		return null;
	}
	@Override
	public boolean add(E e) {
		return instance(type, type, getParent(), getMessage(), e) != null;
	}
	@Override
	public boolean remove(Object o) {
		Iterator<E> it = iterator();
		if (o == null) {
			while (it.hasNext()) {
				if (it.next() == null) {
					it.remove();
					return true;
				}
			}
		} else {
			while (it.hasNext()) {
				if (o.equals(it.next())) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean containsAll(java.util.Collection<?> c) {
		for (Object e : c)
			if (!contains(e))
				return false;
		return true;
	}
	@Override
	public boolean addAll(java.util.Collection<? extends E> c) {
		boolean modified = false;
		for (E e : c)
			if (add(e))
				modified = true;
		return modified;
	}
	@Override
	public boolean removeAll(java.util.Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<?> it = iterator();
		while (it.hasNext()) {
			if (c.contains(it.next())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}
	@Override
	public boolean retainAll(java.util.Collection<?> c) {
		Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
	}
	@Override
	public void run() {
		setCommand(Command.TRANSFER);
	}
	@Override
	public boolean isEmpty() {
		return getParent() == this;
	}
	@Override
	@Deprecated
	public Object[] toArray() {
		return null;
	}
	@Override
	public void clear() {
		getChild().getChild().setParent(getParent());
		getChild().getChild().getChild().setParent(getParent().getChild());
		getParent().getChild().setChild(getChild().getChild());
		getChild().setChild(getParent().getChild().getChild());
		setParent(getChild().getChild());
		getChild().setParent(getChild());
	}
	protected final class RecurrentIterator implements Iterator<E> {

		/**
		 * The current time-listener.
		 */
		Collection<E> current;

		/**
		 * The next time-listener.
		 */
		Collection<E> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public RecurrentIterator(Collection<E> key) {
			next = current = key;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			Collection<E> c = next;
			current = c;
			next = c.getParent();
			if (c == Hypercollection.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getElement();
		}
		@Override
		public void remove() {
			Collection<E> k = next;
			current.clear();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
			} else
				hasNext = false;
		 }
	}
	protected final class ConcurrentIterator implements Iterator<E> {

		/**
		 * The current time-listener.
		 */
		Collection<E> current;

		/**
		 * The next time-listener.
		 */
		Collection<E> next;

		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasNext;

		public ConcurrentIterator(Collection<E> key) {
			next = current = key;
			hasNext = true;
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			Collection<E> c = next;
			current = c;
			next = c.getChild().getChild();
			if (c == Hypercollection.this)
				hasNext = false;
			else
				hasNext = true;
			return c.getElement();
		}
		@Override
		public void remove() {
			Collection<E> k = next;
			current.clear();
			if (!k.isEmpty()) {
				current = k;
				next = k.getChild().getChild();
			} else
				hasNext = false;
		 }
	}
}