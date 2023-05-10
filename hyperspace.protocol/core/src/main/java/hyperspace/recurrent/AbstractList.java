package hyperspace.recurrent;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AbstractList<E> extends AbstractCollection<E> implements List<E> {

	private static final long serialVersionUID = -6164865301667522745L;

	public AbstractList() {
		super();
	}

	public AbstractList(AbstractList<E> parent, E entry) {
		super(parent, entry);
	}
	
	@Override
	public boolean addAll(int index, java.util.Collection<? extends E> c) {
		boolean modified = false;
		for (E e : c) {
			add(index++, e);
			modified = true;
		}
		return modified;
	}

	@Override
	public void add(int index, E element) {
		ListIterator<E> it = new RecursiveListIterator(getParent(), 0);
		do {
			it.next();
			if (index == 0) {
				it.add(element);
			}
			index--;
		} while (it.hasNext());
	}

	@Override
	public E get(int index) {
		ListIterator<E> it = new RecursiveListIterator(getParent(), 0);
		do {
			E element = it.next();
			if (index == 0) {
				return element;
			}
			index--;
		} while (it.hasNext());
		return null;
	}

	@Override
	public E set(int index, E element) {
		ListIterator<E> it = new RecursiveListIterator(getParent(), 0);
		do {
			E current = it.next();
			if (index == 0) {
				it.set(element);
				return current;
			}
			index--;
		} while (it.hasNext());
		return null;
	}

	@Override
	public E release(int index) {
		ListIterator<E> it = new RecursiveListIterator(getParent(), 0);
		do {
			E element = it.next();
			if (index == 0) {
				it.remove();
				return element;
			}
			index--;
		} while (it.hasNext());
		return null;
	}

	@Override
	public int indexOf(Object o) {
		ListIterator<E> it = new RecursiveListIterator(getParent(), 0);
		int index = 0;
		do {
			index++;
			E element = it.next();
			if (element == o) {
				return index;
			}
		} while (it.hasNext());
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		ListIterator<E> it = new RecursiveListIterator(getParent(), size());
		int index = 0;
		do {
			E element = it.previous();
			if (element == o) {
				return index;
			}
			index++;
		} while (it.hasPrevious());
		return 0;
	}

	@Override
	public E remove(int index) {
		ListIterator<E> it = new RecursiveListIterator(getParent(), 0);
		do {
			E element = it.next();
			if (index == 0) {
				it.remove();
				return element;
			}
			index--;
		} while (it.hasNext());
		return null;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new RecursiveListIterator(getParent(), 0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new RecursiveListIterator(getParent(), index);
	}

	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		return new SubList<>(this, fromIndex, toIndex);
	}

	protected final class RecursiveListIterator implements ListIterator<E> {

		/**
		 * The current time-listener.
		 */
		Collection<E> init;

		/**
		 * The current time-listener.
		 */
		Collection<E> current;

		/**
		 * The next time-listener.
		 */
		Collection<E> next;

		/**
		 * The next time-listener.
		 */
		Collection<E> previous;
		
		/**
         * Index of element to be returned by subsequent call to next.
         */
		int cursor;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        int lastRet = -1;
		/**
		 * If this recursor has next time-listener.
		 */
		boolean hasMoreElements;

		public RecursiveListIterator(Collection<E> source, int index) {
			init = next = previous = current = source;
			cursor = index;
		}

		@Override
		public boolean hasNext() {
			return hasMoreElements;
		}

		@Override
		public E next() {
			int i = cursor;
			Collection<E> c = next;
			current = c;
			next = c.getParent();
			previous = c.call();
			lastRet = i;
			cursor = i + 1;
			if (c == AbstractList.this)
				hasMoreElements = false;
			else
				hasMoreElements = true;
			return c.getEntry();
		}

		@Override
		public boolean hasPrevious() {
			return hasMoreElements;
		}

		@Override
		public E previous() {
			int i = cursor - 1;
			Collection<E> c = previous;
			current = c;
			next = c.getParent();
			previous = c.call();
			lastRet = cursor = i;
			if (c == AbstractList.this)
				hasMoreElements = false;
			else
				hasMoreElements = true;
			return c.getEntry();
		}

		@Override
		public void remove() {
			Collection<E> k = next;
			current.release();
			if(lastRet < cursor)
				cursor--;
			lastRet = -1;
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
				previous = k.call();
			} else
				hasMoreElements = false;
		}

		@Override
		public void set(E e) {
			current.setEntry(e);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void add(E e) {
			int i = cursor;
			current = (Collection<E>) instance(current.getClass(), current, e);
			next = current.getParent();
			previous = current.call();
			lastRet = -1;
			cursor = i + 1;
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public int previousIndex() {
			return cursor-1;
		}
	}

	private static class SubList<E> extends AbstractList<E> {
		private static final long serialVersionUID = 2197865914854831213L;
		private final AbstractList<E> root;
		private final SubList<E> parent;
		private final int offset;
		protected int size;

		/**
		 * Constructs a sublist of an arbitrary AbstractList, which is not a SubList
		 * itself.
		 */
		public SubList(AbstractList<E> root, int fromIndex, int toIndex) {
			this.root = root;
			this.parent = null;
			this.offset = fromIndex;
			this.size = toIndex - fromIndex;
		}

		/**
		 * Constructs a sublist of another SubList.
		 */
		protected SubList(SubList<E> parent, int fromIndex, int toIndex) {
			this.root = parent.root;
			this.parent = parent;
			this.offset = parent.offset + fromIndex;
			this.size = toIndex - fromIndex;
		}

		public E set(int index, E element) {
			Objects.checkIndex(index, size);
			return root.set(offset + index, element);
		}

		public E get(int index) {
			Objects.checkIndex(index, size);
			return root.get(offset + index);
		}

		public int size() {
			return size;
		}

		public void add(int index, E element) {
			rangeCheckForAdd(index);
			root.add(offset + index, element);
			updateSizeAndModCount(1);
		}

		public E remove(int index) {
			Objects.checkIndex(index, size);
			E result = root.remove(offset + index);
			updateSizeAndModCount(-1);
			return result;
		}

		protected void removeRange(int fromIndex, int toIndex) {
			root.removeRange(offset + fromIndex, offset + toIndex);
			updateSizeAndModCount(fromIndex - toIndex);
		}
		
		public Iterator<E> iterator() {
			return listIterator();
		}

		public ListIterator<E> listIterator(int index) {
			rangeCheckForAdd(index);

			return new ListIterator<E>() {
				private final ListIterator<E> i = root.listIterator(offset + index);

				public boolean hasNext() {
					return nextIndex() < size;
				}

				public E next() {
					if (hasNext())
						return i.next();
					else
						throw new NoSuchElementException();
				}

				public boolean hasPrevious() {
					return previousIndex() >= 0;
				}

				public E previous() {
					if (hasPrevious())
						return i.previous();
					else
						throw new NoSuchElementException();
				}

				public int nextIndex() {
					return i.nextIndex() - offset;
				}

				public int previousIndex() {
					return i.previousIndex() - offset;
				}

				public void remove() {
					i.remove();
					updateSizeAndModCount(-1);
				}

				public void set(E e) {
					i.set(e);
				}

				public void add(E e) {
					i.add(e);
					updateSizeAndModCount(1);
				}
			};
		}

		public List<E> subList(int fromIndex, int toIndex) {
			subListRangeCheck(fromIndex, toIndex, size);
			return new SubList<>(this, fromIndex, toIndex);
		}

		private void rangeCheckForAdd(int index) {
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}

		private String outOfBoundsMsg(int index) {
			return "Index: " + index + ", Size: " + size;
		}

		private void updateSizeAndModCount(int sizeChange) {
			SubList<E> slist = this;
			do {
				slist.size += sizeChange;
				slist = slist.parent;
			} while (slist != null);
		}
	}

	static void subListRangeCheck(int fromIndex, int toIndex, int size) {
		if (fromIndex < 0)
			throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
		if (toIndex > size)
			throw new IndexOutOfBoundsException("toIndex = " + toIndex);
		if (fromIndex > toIndex)
			throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
	}

	/**
	 * Removes from this list all of the elements whose index is between
	 * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive. Shifts any
	 * succeeding elements to the left (reduces their index). This call shortens the
	 * list by {@code (toIndex - fromIndex)} elements. (If
	 * {@code toIndex==fromIndex}, this operation has no effect.)
	 *
	 * <p>
	 * This method is called by the {@code clear} operation on this list and its
	 * subLists. Overriding this method to take advantage of the internals of the
	 * list implementation can <i>substantially</i> improve the performance of the
	 * {@code clear} operation on this list and its subLists.
	 *
	 * @implSpec This implementation gets a list iterator positioned before
	 *           {@code fromIndex}, and repeatedly calls {@code ListIterator.next}
	 *           followed by {@code ListIterator.remove} until the entire range has
	 *           been removed. <b>Note: if {@code ListIterator.remove} requires
	 *           linear time, this implementation requires quadratic time.</b>
	 *
	 * @param fromIndex index of first element to be removed
	 * @param toIndex   index after last element to be removed
	 */
	protected void removeRange(int fromIndex, int toIndex) {
		ListIterator<E> it = listIterator(fromIndex);
		for (int i = 0, n = toIndex - fromIndex; i < n; i++) {
			it.next();
			it.remove();
		}
	}
}