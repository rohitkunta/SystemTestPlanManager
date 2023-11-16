package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LogTest {

	@Test
	void testLog() {
		Log<Integer> log  = new Log<Integer>();
		
		assertEquals(0, log.size());
	}

	@Test
	void testAdd() {
		Log<Integer> log  = new Log<Integer>();
		
		log.add(3);
		
		log.add(4);
		
		assertEquals(2, log.size());
		assertEquals(3, log.get(0));
		assertEquals(4, log.get(1));
		
		assertThrows(NullPointerException.class, () -> log.add(null));
		assertThrows(IndexOutOfBoundsException.class, () -> log.get(2));
		
		log.add(5);
		log.add(6);
		log.add(7);
		log.add(8);
		log.add(9);
		log.add(10);
		log.add(11);
		log.add(12);
		log.add(13);
		
		assertEquals(11, log.size());
		
		
	}

	@Test
	void testGet() {
		Log<Integer> log  = new Log<Integer>();
		
		assertThrows(IndexOutOfBoundsException.class, () -> log.get(-1));
		
		log.add(3);
		
		log.add(4);
		
		assertThrows(IndexOutOfBoundsException.class, () -> log.get(10));
	}

}
