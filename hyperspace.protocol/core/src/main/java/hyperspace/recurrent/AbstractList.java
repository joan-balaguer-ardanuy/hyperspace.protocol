package hyperspace.recurrent;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AbstractList<E> 
	extends AbstractCollection<E> 
		implements List<E> {

	private static final long serialVersionUID = -6164865301667522745L;

	public AbstractList() {
		super();
	}

	public AbstractList(AbstractList<E> parent, E entry) {
		super(parent, entry);
	}

	@Override
	public E get(int index) {
		ListIterator<E> it = listIterator();
		do {
			E entry = it.next();
			if (index == 0) {
				return entry;
			}
			index--;
		} while (it.hasNext());
		return null;
	}

	@Override
	public void add(int index, E entry) {
		ListIterator<E> it = listIterator();
		do {
			it.next();
			if (index == 0) {
				it.add(entry);
			}
			index--;
		} while (it.hasNext());
	}

	@Override
	public E set(int index, E entry) {
		ListIterator<E> it = listIterator();
		do {
			E current = it.next();
			if (index == 0) {
				it.set(entry);
				return current;
			}
			index--;
		} while (it.hasNext());
		return null;
	}

	@Override
	public int indexOf(Object o) {
		ListIterator<E> it = listIterator();
		int index = 0;
		do {
			index++;
			E element = it.next();
			if (element == o) {
				return index;
			}
		} while (it.hasNext());
		return -1;
	}

	@Override
	@Deprecated
	public int lastIndexOf(Object o) {
		ListIterator<E> it = listIterator();
		int index = 0;
		do {
			E element = it.previous();
			if (element == o) {
				return size() - index;
			}
			index++;
		} while (it.hasPrevious());
		return -1;
	}

	@Override
	public E release(int index) {
		ListIterator<E> it = listIterator();
		do {
			E entry = it.next();
			if (index == 0) {
				it.remove();
				return entry;
			}
			index--;
		} while (it.hasNext());
		return null;
	}

	@Override
	public E remove(int index) {
		return release(index);
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
	public ListIterator<E> listIterator() {
		return new RecursiveListIterator(getParent());
	}

	@Override
	@Deprecated
	public ListIterator<E> listIterator(int index) {
		return new RecursiveListIterator(getParent(), index);
	}

	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		return new SubList<>(this, fromIndex, toIndex);
	}

	protected final class RecursiveListIterator implements ListIterator<E> {

		/**
		 * The current collection.
		 */
		private Collection<E> current;

		/**
		 * The next collection.
		 */
		private Collection<E> next;

		/**
		 * The previous collection.
		 */
		private Collection<E> previous;
		
		/**
		 * If this recursor has more entries.
		 */
		private boolean hasMoreEntries;

		public RecursiveListIterator(Collection<E> source) {
			next = previous = current = source;
		}
		
		@Deprecated
		public RecursiveListIterator(Collection<E> source, int index) {
			Collection<E> parent = source;
			for(int i = 0; i < index; i++) {
				next = previous = current = parent;
				parent = parent.getParent();
			}
		}

		@Override
		public boolean hasNext() {
			return hasMoreEntries;
		}

		@Override
		public E next() {
			Collection<E> c = next;
			current = c;
			next = c.getParent();
			previous = c.call();
			if (c == AbstractList.this)
				hasMoreEntries = false;
			else
				hasMoreEntries = true;
			return c.getEntry();
		}

		@Override
		public boolean hasPrevious() {
			return hasMoreEntries;
		}

		@Override
		public E previous() {
			Collection<E> c = previous;
			current = c;
			next = c.getParent();
			previous = c.call();
			if (c == AbstractList.this)
				hasMoreEntries = false;
			else
				hasMoreEntries = true;
			return c.getEntry();
		}

		@Override
		public void remove() {
			Collection<E> k = next;
			current.release();
			if (!k.isEmpty()) {
				current = k;
				next = k.getParent();
				previous = k.call();
			} else
				hasMoreEntries = false;
		}

		@Override
		public void set(E e) {
			current.setEntry(e);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void add(E e) {
			if(e == null)
				throw new NullPointerException();
			current = (Collection<E>) instance(current.getClass(), current, e);
			next = current.getParent();
			previous = current.call();
		}

		@Override
		@Deprecated
		public int nextIndex() {
			return -1;
		}

		@Override
		@Deprecated
		public int previousIndex() {
			return -1;
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