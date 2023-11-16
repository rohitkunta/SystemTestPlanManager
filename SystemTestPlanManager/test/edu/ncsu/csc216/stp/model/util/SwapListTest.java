package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwapListTest {

	@Test
	void testSwapList() {
		SwapList<Integer> list = new SwapList<Integer>();
		
		assertEquals(0, list.size());
	}

	@Test
	void testAdd() {
		
		SwapList<Integer> list = new SwapList<Integer>();
		
		Exception e1 = assertThrows(NullPointerException.class,
				() -> list.add(null));
		assertEquals("Cannot add null element.", e1.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		list.add(1);
		assertEquals(1, list.size());
		
		
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(2);
		assertEquals(5, list.size());
		assertEquals(4, list.get(2));
		
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		
		assertEquals(11, list.size());
		
		
	}

	@Test
	void testRemove() {
		SwapList<Integer> list = new SwapList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(7));
		
		assertEquals(3, list.remove(2));
		
		assertEquals(4, list.size());
	}

	@Test
	void testMoveUp() {
		SwapList<Integer> list = new SwapList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		list.moveUp(3);
		assertEquals(4, list.get(2));
		
		list.moveUp(0);
		assertEquals(1, list.get(0));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveUp(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveUp(7));
	}

	@Test
	void testMoveDown() {
		SwapList<Integer> list = new SwapList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		list.moveDown(3);
		assertEquals(4, list.get(4));
		
		list.moveDown(1);
		assertEquals(2, list.get(2));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveUp(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveUp(7));
		
	}

	@Test
	void testMoveToFront() {
		SwapList<Integer> list = new SwapList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		list.moveToFront(0);
		assertEquals(1, list.get(0));
		
		list.moveToFront(3);
		assertEquals(4, list.get(0));
		assertEquals(1, list.get(1));
		
		list.moveToFront(1);
		assertEquals(1, list.get(0));
		assertEquals(4, list.get(1));
		
	}

	@Test
	void testMoveToBack() {
		SwapList<Integer> list = new SwapList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		list.moveToBack(4);
		assertEquals(5, list.get(4));
		
		list.moveToBack(0);
		assertEquals(1, list.get(4));
		assertEquals(2, list.get(0));
		
	}

	@Test
	void testGet() {
		SwapList<Integer> list = new SwapList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(7));
		
		assertEquals(3, list.get(2));
	}

}
