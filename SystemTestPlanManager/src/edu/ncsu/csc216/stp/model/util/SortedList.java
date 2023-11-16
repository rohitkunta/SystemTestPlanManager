/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * Implements the ISortedList interface.
 * LinkedList implementation that maintains Sorted order of elements as they are added, removed, and manipulated.
 * @param <E> The object type 
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	
	/**The number of elements in the SortedList*/
	private int size;
	
	/**front of linked list*/
	private ListNode front;
	
	
	/**
	 * Constructor for SortedList
	 * Initializes size to 0 and front pointer to null
	 */
	public SortedList()
	{
		this.size = 0;
		this.front = null;
	}
	

	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added due to duplicate
	 */
	@Override
	public void add(E element) {
		
		if (element == null)
		{
			throw new NullPointerException("Cannot add null element.");
		}
		
		if (contains(element))
		{
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		
	    // Add to front of a list, including an empty list
		if (front == null || element.compareTo(front.data) < 0) 
		{
			front = new ListNode(element, front);
		} 
		else 
		{ 	// insert into middle or end of list
			ListNode current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0)
			{
				current = current.next;
			}
			
			current.next = new ListNode(element, current.next);
		}
		
		size++; 
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
		if (idx < 0 || idx >= size())
		{
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		E removedValue = null;
		if (idx == 0) 
		{ 	// Special Case: front of list
			removedValue = front.data;
			front = front.next;
		} 
		else 
		{
			// removing from elsewhere in the list
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) 
			{
			current = current.next;
			}
			
			removedValue = current.next.data;
			current.next = current.next.next;
		}
		
		this.size--;
		return removedValue;
	}

	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found, false if there is no equivalent method
	 */
	@Override
	public boolean contains(E element) {
		
		for (int i = 0; i < size; i++)
		{
			if (get(i).compareTo(element) == 0)
			{
				return true;
			}
		}
		
		
		return false;
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
		if (idx < 0 || idx >= size())
			throw new IndexOutOfBoundsException("Invalid index.");

		ListNode node = front;
		for (int i = 0; i < idx; i++) {
			node = node.next;
		}

		return node.data;
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Private inner list node class for SortedList linkedlist
	 * Node class for the LinkedList
	 * @author Rohit Kunta	
	 */
	private class ListNode
	{
		/**The data the list node will hold*/
		public E data;
		/**The next pointer to the next ListNode in the List*/
		public ListNode next;
		
		/**
		 * Constructor for 
		 * @param data the data of the node
		 * @param next the next node in the list
		 */
		public ListNode(E data, ListNode next)
		{
			this.data = data;
			this.next = next;
		}

	}

	

}
