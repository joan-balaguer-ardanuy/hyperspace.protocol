package hyperspace.recurrent;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Deque<E> extends Queue<E>, java.util.Deque<E> {

	/**
	 * Inserts the specified element at the front of this deque
	 * @param e the element to add
	 */
	void addFirst(E e);

	/**
	 * Inserts the specified element at the end of this deque.
	 * @param e the element to add
	 */
	void addLast(E e);

	/**
	 * Retrieves and removes the first element of this deque.
	 * @return the head of this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	E removeFirst();

	/**
	 * Retrieves and removes the last element of this deque.
	 * @return the tail of this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	E removeLast();

	/**
	 * Retrieves, but does not remove, the first element of this deque.
	 * @return the head of this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	E getFirst();

	/**
	 * Retrieves, but does not remove, the last element of this deque. This method
	 * differs from {@link #peekLast peekLast} only in that it throws an exception
	 * if this deque is empty.
	 * @return the tail of this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	E getLast();

	/**
	 * Removes the first occurrence of the specified element from this deque. If the
	 * deque does not contain the element, it is unchanged. More formally, removes
	 * the first element {@code e} such that {@code Objects.equals(o, e)} (if such
	 * an element exists). Returns {@code true} if this deque contained the
	 * specified element (or equivalently, if this deque changed as a result of the
	 * call).
	 *
	 * @param o element to be removed from this deque, if present
	 * @return {@code true} if an element was removed as a result of this call
	 */
	boolean removeFirstOccurrence(Object o);

	/**
	 * Removes the last occurrence of the specified element from this deque. If the
	 * deque does not contain the element, it is unchanged. More formally, removes
	 * the last element {@code e} such that {@code Objects.equals(o, e)} (if such an
	 * element exists). Returns {@code true} if this deque contained the specified
	 * element (or equivalently, if this deque changed as a result of the call).
	 *
	 * @param o element to be removed from this deque, if present
	 * @return {@code true} if an element was removed as a result of this call
	 */
	boolean removeLastOccurrence(Object o);

	/**
	 * Returns an iterator over the elements in this deque in reverse sequential
	 * order. The elements will be returned in order from last (tail) to first
	 * (head).
	 *
	 * @return an iterator over the elements in this deque in reverse sequence
	 */
	Iterator<E> descendingIterator();
	
	// *** Stack methods ***

    /**
     * Pushes an element onto the stack represented by this deque (in other
     * words, at the head of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    void push(E e);

    /**
     * Pops an element from the stack represented by this deque.  In other
     * words, removes and returns the first element of this deque.
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this deque (which is the top
     *         of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */
    E pop();
}