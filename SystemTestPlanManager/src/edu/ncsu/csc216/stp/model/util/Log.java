/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * Implements the ILog interface.
 * List where elements can only be added
 * to the end of the list.  Once an element is added, it cannot
 * be removed.
 * @param <E> The type of object being used in the Log
 * @author Rohit Kunta
 */
public class Log<E> implements ILog<E> {
	
	/**The array of the objects being manipulated*/
	private E[] log;
	/**The number of elements in the array*/
	private int size;
	/**The initialization size of the array*/
	private static final int INIT_CAPACITY = 10;

	
	/**
	 * Constructor for Log List
	 * Initializes array to 10 elements
	 * Initializes size to 0.
	 */
	@SuppressWarnings("unchecked")
	public Log()
	{
		log = (E[]) new Object[INIT_CAPACITY];
		size = 0;

	}
	
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element being added is null
	 */
	@Override
	public void add(E element) {
		
		if (element == null)
		{
			throw new NullPointerException("Cannot add null element.");
		}
		
		if (log[log.length - 1] != null)
		{
			growArray();
		}
		
		log[size++] = element;
	}
	
	
	/**
	 * Grows the ArrayList when necessary
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newArray = (E[]) new Object[log.length * 2];

		for (int i = 0; i < size(); i++)
			newArray[i] = log[i];
		log = newArray;
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is greater than or at size, or below 0
	 */
	@Override
	public E get(int idx) {
		
		if (idx > size - 1 || idx < 0 || log[idx] == null)
		{
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		return log[idx];
	}

	/**
	 * Returns the number of elements in the list.
	 * @return size number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
