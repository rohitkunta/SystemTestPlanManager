/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * Implements the ISwapList interface.
 * @param <E> The object type being used in the list
 * @author Rohit Kunta
 */
public class SwapList<E> implements ISwapList<E> {
	
	/**Initial capacity of the array of elements*/
	private static final int INIT_CAPACITY = 10;
	/**Array of elements*/
	private E[] list;
	/**number of elements in the array of elements*/
	private int size;
	
	/**
	 * Constructor for SwapList 
	 * Initializes array 
	 * Initializes size to 0
	 */
	@SuppressWarnings("unchecked")
	public SwapList() 
	{
		list = (E[]) new Object[INIT_CAPACITY];
		this.size = 0;
	}
	

	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		try
		{
			if (element == null)
			{
				throw new NullPointerException("Cannot add null element.");
			}
			
			if (list[list.length - 1] != null)
			{
				growArray();
			}
			
			list[size++] = element;
			
		} catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("Cannot add element.");
		}
			
	}
	
	/**
	 * Grows the ArrayList when necessary
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newArray = (E[]) new Object[list.length * 2];

		for (int i = 0; i < size(); i++)
			newArray[i] = list[i];
		list = newArray;
	}
	

	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		
		checkIndex(idx);
		
		if (idx == size - 1) {
			E object = list[idx];
			list[idx] = null;
			size--;
			return object;
		}
		
		
		E object = list[idx];
		list[idx] = null;
		for (int i = idx; i < list.length - 1; i++) {
			list[i] = list[i + 1];
		}
		
		list[list.length - 1] = null;
		size--;
		return object;
	}
	
	/**
	 * Checks if the index is null
	 * @param idx the index being checked
	 */
	private void checkIndex(int idx)
	{
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	

	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) {
		
		checkIndex(idx);
		
		if (idx != 0)
		{
			E temp;
			
			temp = list[idx - 1];
			
			list[idx - 1] = list[idx];
			
			list[idx] = temp;
		}
	}

	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) {
		
		checkIndex(idx);
		
		if (idx != size - 1)
		{
			E temp;
			
			temp = list[idx + 1];
			
			list[idx + 1] = list[idx];
			
			list[idx] = temp;
		}
		
	}

	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToFront(int idx) {
		
		checkIndex(idx); 
		
		if (idx != 0) // move to front and then push everything until idx back one to fill in the space
		{	
			
			E temp = list[idx];
			
			//Shifts everything right until index(idx)
			for (int i = idx; i > 0; i--) {
				list[i] = list[i - 1];
			}
			
			list[0] = temp;
			
		}
		
	}

	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx); 
		
		if (idx != size - 1) // move to front and then push everything until idx back one to fill in the space
		{	
			//stores element at idx
			E temp = list[idx];
			
			//Shifts everything left until index(idx), even the last element of the original list
			for (int i = idx; i < size; i++) {
				list[i] = list[i + 1];
			}
			
			//sets last element to element at idx
			list[size - 1] = temp;
			
		}
		
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */	
	@Override
	public E get(int idx) {
		if (idx > size - 1 || idx < 0 || list[idx] == null)
		{
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		return list[idx];
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return this.size;
	}

}
