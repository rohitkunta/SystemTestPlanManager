package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

class TestPlanTest {

	@Test
	void testTestPlan() {
		
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		assertEquals("PackScheduler", testPlan.getTestPlanName());
		assertEquals(0, testPlan.getTestCases().size());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestPlan("failing tests"));
		assertEquals("Invalid name.", e1.getMessage(), "Incorrect exception thrown with invalid test plan name - " + "failing tests");
	}
	
	@Test
	void testAddTestCase() {
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		testPlan.addTestCase(t);
		
		assertEquals(t.getTestCaseId(), testPlan.getTestCase(0).getTestCaseId());
		assertEquals(t.getTestPlan().getTestPlanName(), testPlan.getTestPlanName());
	}

	@Test
	void testGetTestCasesAsArray() {
		
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");	
		
		testPlan.addTestCase(t);
		
		TestCase t2 = new TestCase("Test Id", "Test Type", "Test Description", "Test Expected Results");
		
		t2.addTestResult(true, "Passing for test");
		
		testPlan.addTestCase(t2);
		
		String [][] testCaseArray = new String[][] {{"Valid File", "Requirements", "FAIL"},
				{"Test Id", "Test Type", "PASS"}};
		
		boolean equal = true;
		
		assertEquals(2, testPlan.getTestCasesAsArray().length);
		
		if (testCaseArray.length != testPlan.getTestCasesAsArray().length || testCaseArray[0].length != testPlan.getTestCasesAsArray()[0].length) {
            equal = false;
        }
		
		
		for (int i = 0; i < testCaseArray.length; i++) {
            for (int j = 0; j < testCaseArray[i].length; j++) {
                if (!testCaseArray[i][j].equals(testPlan.getTestCasesAsArray()[i][j])) {
                    equal = false;
                    if (!equal)
                    {
                    	System.out.println("i: " + i + "\n j: " + j);
                    }
                }
            }
        }
		
		assertTrue(equal);	
	}

	@Test
	void testCompareTo() {
		
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestPlan t;
		
		assertThrows(NullPointerException.class, () -> testPlan.compareTo(null));
		
		t = new TestPlan("PackScheduler");
		
		assertEquals(0, testPlan.compareTo(t));
		
		t = new TestPlan("WolfScheduler");
		
		assertTrue(testPlan.compareTo(t) < 0);
		
		t = new TestPlan("AssasinsCreedIII");
		
		assertTrue(testPlan.compareTo(t) > 0);
		
	}

	@Test
	void testSetTestPlanName() {
		
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		testPlan.setTestPlanName("WolfScheduler");
		
		assertEquals("WolfScheduler", testPlan.getTestPlanName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> testPlan.setTestPlanName(""));
		assertEquals("Invalid name.", e1.getMessage(), "Incorrect exception thrown with invalid test plan name - " + "[empty string]");
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> testPlan.setTestPlanName(null));
		assertEquals("Invalid name.", e2.getMessage(), "Incorrect exception thrown with invalid test plan name - " + null);
	}

	@Test
	void testRemoveTestCase() {
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");	
		
		testPlan.addTestCase(t);
		
		assertEquals(1, testPlan.getTestCases().size());
		
		testPlan.removeTestCase(0);
		
		assertEquals(0, testPlan.getTestCases().size());
	}

	@Test
	void testGetNumberOfFailingTests() {
		
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");	
		
		TestCase t2 = new TestCase("Invalid File", "Requirements", "Test description", "Course catalog should be empty");	
		
		TestCase t3 = new TestCase("Test Id", "Test Type", "Test Description", "Test Expected Results");	
		
		t3.addTestResult(true, "Passing for test");
		
		testPlan.addTestCase(t);
		testPlan.addTestCase(t2);
		testPlan.addTestCase(t3);
		
		assertEquals(2, testPlan.getNumberOfFailingTests());
		
	}

	@Test
	void testEqualsObject() {
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestPlan t2 = new TestPlan("PackScheduler");
		
		assertTrue(testPlan.equals(t2));
		
		t2 = new TestPlan("WolfScheduler");
		
		assertFalse(testPlan.equals(t2));
	}
	
	@Test
	void testAddTestResult()
	{
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		testPlan.addTestCase(t);
		
		assertEquals(1, testPlan.getNumberOfFailingTests());
		
		testPlan.addTestResult(0, true, "passing for Test");
		
		assertEquals(0, testPlan.getNumberOfFailingTests());
	}
	
	@Test
	void testHashCode()
	{
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestPlan testPlan2 = new TestPlan("PackScheduler");
		
		assertEquals(testPlan.hashCode(), testPlan2.hashCode());
	}
	
	
}
