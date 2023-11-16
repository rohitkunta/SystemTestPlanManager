package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortedListTest {

	@Test
	void testSortedList() {
		
		SortedList<Integer> list = new SortedList<Integer>();
		
		assertEquals(0, list.size());
		
	}

	@Test
	void testAdd() {
		
		SortedList<Integer> list = new SortedList<Integer>();
		
		Exception e1 = assertThrows(NullPointerException.class,
				() -> list.add(null));
		assertEquals("Cannot add null element.", e1.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		list.add(1);
		assertEquals(1, list.size());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> list.add(1));
		assertEquals("Cannot add duplicate element.", e2.getMessage(), "Incorrect exception thrown with invalid task id - " + 1);
		
		
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(2);
		
		assertEquals(2, list.get(1));
		
	}

	@Test
	void testRemove() {
		
		SortedList<Integer> list = new SortedList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(7));
		
		assertEquals(5, list.remove(4));
		assertEquals(4, list.size());
		
		assertEquals(1, list.remove(0));
		assertEquals(3, list.size());
		
		assertEquals(3, list.remove(1));
		assertEquals(2, list.size());
		
	}

	@Test
	void testGet() {
		SortedList<Integer> list = new SortedList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(7));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
		
		assertEquals(3, list.get(2));
		assertEquals(2, list.get(1));
		
	}

}
