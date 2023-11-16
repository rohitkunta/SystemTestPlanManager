package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

class FailingTestListTest {

	@Test
	void testSetTestPlanName() {
		FailingTestList failingTest = new FailingTestList();
		
		assertEquals("Failing Tests", failingTest.getTestPlanName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> failingTest.setTestPlanName("PackScheduler"));
		assertEquals("The Failing Tests list cannot be edited.", e1.getMessage(), "Incorrect exception thrown with invalid test plan name - " + "PackScheduler");
		
		failingTest.setTestPlanName("failing tests");
		
		assertEquals("Failing Tests", failingTest.getTestPlanName());
	}

	@Test
	void testAddTestCase() {
		
		FailingTestList failingTest = new FailingTestList();
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		failingTest.addTestCase(t);
		
		TestCase t2 = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		t2.addTestResult(true, "Passing for test");
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> failingTest.addTestCase(t2));
		assertEquals("Cannot add passing test case.", e1.getMessage(), "Incorrect exception thrown with invalid test plan name - " + "PackScheduler"); 
	}

	@Test
	void testGetTestCasesAsArray() {
		
		FailingTestList failingTests = new FailingTestList();
		
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		t.setTestPlan(testPlan);
		
		failingTests.addTestCase(t);
		
		TestCase t2 = new TestCase("Test Id", "Test Type", "Test Description", "Test Expected Results");
		
		failingTests.addTestCase(t2);
		
		String [][] testCaseArray = new String[][] {{"Valid File", "Requirements", "PackScheduler"},
				{"Test Id", "Test Type", ""}};
		
		boolean equal = true;
		
		if (testCaseArray.length != failingTests.getTestCasesAsArray().length || testCaseArray[0].length != failingTests.getTestCasesAsArray()[0].length) {
            equal = false;
        }
		
		
		for (int i = 0; i < testCaseArray.length; i++) {
            for (int j = 0; j < testCaseArray[i].length; j++) {
                if (!testCaseArray[i][j].equals(failingTests.getTestCasesAsArray()[i][j])) {
                    equal = false;
                }
            }
        }
		
		assertTrue(equal);
		
	}

	@Test
	void testClearTestCases() {
		FailingTestList failingTests = new FailingTestList();
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		failingTests.addTestCase(t);
		
		TestCase t2 = new TestCase("Test Id", "Test Type", "Test Description", "Test Expected Results");
		
		failingTests.addTestCase(t2);
		
		assertEquals(2, failingTests.getTestCases().size());
		
		failingTests.clearTests();
		
		assertEquals(0, failingTests.getTestCases().size());
		
	}

}
