package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * Represents a test case that can be part of a test plan.
 * @author Rohit Kunta
 */
public class TestCase {
	
	/**The ID of the test case*/
	private String testCaseId;
	/**The type of the test case*/
	private String testType;
	/**The description of the test case*/
	private String testDescription;
	/**The expected results of the test case*/
	private String expectedResults;
	/**The Plan of the test case*/
	private TestPlan testPlan;
	
	/**The Plan of the test case*/
	private Log<TestResult> testResults;
	
	
	/**
	 * Constructor for the Test case 
	 * @param testCaseId the test case to be set
	 * @param testType the type to be set
	 * @param testDescription the description to be set
	 * @param expectedResults the expected results of the test case
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		setTestCaseId(testCaseId);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
		testResults = new Log<TestResult>();
		testPlan = null;
	}


	/**
	 * Returns the test ID
	 * @return testCaseId the ID of the test case
	 */
	public String getTestCaseId() {
		return testCaseId;
	}


	/**
	 * Sets the ID of the test case
	 * @param testCaseId the testCaseId to set
	 * @throws IllegalArgumentException if the testCaseId is null or empty
	 */
	private void setTestCaseId(String testCaseId) {
		if (testCaseId == null || testCaseId.length() == 0)
		{
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.testCaseId = testCaseId;
	}

	
	/**
	 * Returns the type of the test case
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}


	/**
	 * Sets the type of the test case
	 * @param testType the testType to set
	 * @throws IllegalArgumentException if the testType is null or empty
	 */
	private void setTestType(String testType) {
		if (testType == null || testType.length() == 0)
		{
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.testType = testType;
	}


	/**
	 * Returns the description of the test case
	 * @return the testDescription the description of the test case
	 */
	public String getTestDescription() {
		return testDescription;
	}


	/**
	 * Sets the description of the test
	 * @param testDescription the testDescription to set
	 * @throws IllegalArgumentException if the testDescription is null or empty
	 */
	private void setTestDescription(String testDescription) {
		if (testDescription == null || testDescription.length() == 0)
		{
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.testDescription = testDescription;
	}


	/**
	 * Returns the expected results of the test case
	 * @return expectedResults of the test case
	 */
	public String getExpectedResults() {
		return expectedResults;
	}


	/**
	 * Sets the expected results of the test case
	 * @param expectedResults the expectedResults to set
	 * @throws IllegalArgumentException if the expectedResults is null or empty
	 */
	private void setExpectedResults(String expectedResults) {
		if (expectedResults == null || expectedResults.length() == 0)
		{
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.expectedResults = expectedResults;
	}


	/**
	 * Returns the test plan of the test case
	 * @return testPlan the test plan of the test case
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}


	/**
	 * Sets the test plan of the test case
	 * @param testPlan the testPlan to set
	 * @throws IllegalArgumentException if testPlan is null 
	 */
	public void setTestPlan(TestPlan testPlan) {
		
		if (testPlan == null)
		{
			throw new IllegalArgumentException("Invalid test plan.");
		}
		
		this.testPlan = testPlan;
	}
	
	/**
	 * Creates a TestResult from the given values and adds the TestResult to the end of the testResults log.
	 * @param passing whether the test case passed
	 * @param actualResult the actualResults of the test result
	 * @throws IllegalArgumentException if the TestResult object cannot be constructed
	 */
	public void addTestResult(boolean passing, String actualResult)
	{
		try
		{
			TestResult testResult = new TestResult(passing, actualResult);
			testResults.add(testResult);
			
		} catch (Exception e)
		{
			throw new IllegalArgumentException("Invalid test results.");
		}
	}
	
	
	/**
	 * Returns whether the last test case in the testResults log is passing
	 * @return isPassing whether or not the last test case in the testResults log is passing
	 * @throws IllegalArgumentException if the testResult cannot be constructed
	 */
	public boolean isTestCasePassing()
	{
		try
		{
			if (testResults.size() == 0)
			{
				return false;
			}
			
			boolean isPassing = testResults.get(testResults.size() - 1).isPassing();
			return isPassing;
			
		} catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns the status of the TestCase as “PASS” or “FAIL”.
	 * @return constant for whether the test case is passing or failing
	 */
	public String getStatus()
	{
		if (testResults.size() == 0)
		{
			return TestResult.FAIL;
		}
		
		if (testResults.get(testResults.size() - 1).isPassing())
		{
			return TestResult.PASS;
		}
		else 
		{
			return TestResult.FAIL;
		}
	}
	
	/**
	 * Returns a string representation of the testResults Log. Uses TestResult.toString()
	 * @return log the String representation of the testResults log
	 */
	public String getActualResultsLog()
	{
		String log = "";
		
		for (int i = 0; i < testResults.size(); i++)
		{
			log += "- " + testResults.get(i).toString() + "\n";
		}
		
		return log;
	}
	
	
	/**
	 * Returns a string representation of the TestCase for printing to a file
	 * @return toString the String representation of the Test case
	 */
	public String toString()
	{
		String toString = "";
		
		toString += "# " + testCaseId + "," + testType + "\n";
		toString += "* " + testDescription + "\n";
		toString += "* " + expectedResults + "\n";
		toString += getActualResultsLog();
		
		return toString;
	}
	

}
