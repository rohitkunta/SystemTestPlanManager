package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestResultTest {

	@Test
	void testTestResult() {

		TestResult testResult = new TestResult(true, "No actual results");
		
		assertEquals("No actual results", testResult.getActualResults());
		assertTrue(testResult.isPassing());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestResult(true, ""));
		assertEquals("Invalid test results.", e1.getMessage(), "Incorrect exception thrown with invalid task id - " + "");
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new TestResult(true, null));
		assertEquals("Invalid test results.", e2.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
	}

	@Test
	void testToString() {
		
		TestResult testResult = new TestResult(true, "This test is passing");
		assertEquals("PASS: This test is passing", testResult.toString());
		
		TestResult testResult2 = new TestResult(false, "This test is failing");
		assertEquals("FAIL: This test is failing", testResult2.toString());
	}

}
