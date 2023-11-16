package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

class TestCaseTest {

	@Test
	void testTestCase() {
		
		TestCase testCase = new TestCase("Valid File", "Requirements", "1. Run WolfScheduler GUI\r\n"
																	+ "2. In the Load Course Catalog File Chooser dialog select: testfiles/_course_records.txt\r\n"
																	+ "3. Click Select\r\n"
																	+ "4. Check Results\r\n"
																	+ "5. Close GUI", 
																	"Course catalog contains 13 courses");
		
		
		assertEquals("Valid File", testCase.getTestCaseId());
		assertEquals("Requirements", testCase.getTestType());
		assertEquals("1. Run WolfScheduler GUI\r\n"
				+ "2. In the Load Course Catalog File Chooser dialog select: testfiles/_course_records.txt\r\n"
				+ "3. Click Select\r\n"
				+ "4. Check Results\r\n"
				+ "5. Close GUI", testCase.getTestDescription());
		assertEquals("Course catalog contains 13 courses", testCase.getExpectedResults());
		assertNull(testCase.getTestPlan());
		
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("", "Requirements", "Test description", "Course catalog contains 13 courses"));
		assertEquals("Invalid test information.", e1.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(null, "Requirements", "Test description", "Course catalog contains 13 courses"));
		assertEquals("Invalid test information.", e2.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("Valid File", "", "Test description", "Course catalog contains 13 courses"));
		assertEquals("Invalid test information.", e3.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("Valid File", null, "Test description", "Course catalog contains 13 courses"));
		assertEquals("Invalid test information.", e4.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("Valid File", "Requirements", "", "Course catalog contains 13 courses"));
		assertEquals("Invalid test information.", e5.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("Valid File", "Requirements", null, "Course catalog contains 13 courses"));
		assertEquals("Invalid test information.", e6.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("Valid File", "Requirements", "Test description", ""));
		assertEquals("Invalid test information.", e7.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
		Exception e8 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase("Valid File", "Requirements", "Test description", null));
		assertEquals("Invalid test information.", e8.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
		
	}

	@Test
	void testSetTestPlan() {
		TestCase testCase = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		TestPlan testPlan = new TestPlan("PackScheduler");
		
		testCase.setTestPlan(testPlan);
		
		assertEquals(testPlan.getTestPlanName(), testCase.getTestPlan().getTestPlanName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> testCase.setTestPlan(null));
		assertEquals("Invalid test plan.", e1.getMessage(), "Incorrect exception thrown with invalid task id - " + null);
	}

	@Test
	void testAddTestResult() {
		
		TestCase testCase = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		testCase.addTestResult(false, "Test not passing");
		
		assertEquals("- FAIL: Test not passing\n", testCase.getActualResultsLog());
		
		TestCase testCase2 = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		testCase2.addTestResult(true, "Test not passing");
		
		assertEquals("- PASS: Test not passing\n", testCase2.getActualResultsLog());
	}

	@Test
	void testIsTestCasePassing() {
		
		TestCase testCase = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		testCase.addTestResult(false, "Test not passing");
		
		assertFalse(testCase.isTestCasePassing());
		
		testCase.addTestResult(true, "Test not passing");
		
		assertTrue(testCase.isTestCasePassing());
	}

	@Test
	void testGetStatus() {
		TestCase testCase = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		testCase.addTestResult(false, "Test not passing");
		
		assertEquals("FAIL", testCase.getStatus());
		
		testCase.addTestResult(true, "Test not passing");
		
		assertEquals("PASS", testCase.getStatus());
		
	}

	@Test
	void testGetActualResultsLog() {
		TestCase testCase = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		testCase.addTestResult(false, "Test not passing");
		
		assertEquals("- FAIL: Test not passing\n", testCase.getActualResultsLog());
		
		testCase.addTestResult(true, "Test not passing");
		
		assertEquals("- FAIL: Test not passing\n" + "- PASS: Test not passing\n", testCase.getActualResultsLog());
	}

	@Test
	void testToString() {
		
		TestCase testCase = new TestCase("Valid File", "Requirements", "1. Test description", "Course catalog contains 13 courses");
		
		testCase.addTestResult(false, "Test not passing");	
		
		assertEquals("# Valid File,Requirements\n" + 
					 "* 1. Test description\n" + 
					 "* Course catalog contains 13 courses\n" + 
					 "- FAIL: Test not passing\n", testCase.toString());
	}

}
