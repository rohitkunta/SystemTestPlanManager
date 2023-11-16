package edu.ncsu.csc216.stp.model.tests;

/**
 * Represents the result of a test case execution.
 * A TestCase can contain one or many of these
 * @author Rohit Kunta
 */
public class TestResult {
	
	/**Constant value for PASS String*/
	public static final String PASS = "PASS";
	/**Constant value for PASS String*/
	public static final String FAIL = "FAIL";
	
	/**stores whether the test case is passing*/
	private boolean passing;

	/**stores the actual test results for the test results*/
	private String actualResults;
	
	/**
	 * Constructor for TestResult
	 * @param passing whether the test case is passing
	 * @param actualResults the actual results for a test results
	 */
	public TestResult(boolean passing, String actualResults)
	{
		
		setActualResults(actualResults);
		setPassing(passing);
		
	}
	
	/**
	 * Returns the actual results of the test case
	 * @return actualResults the actual results of test results
	 */
	public String getActualResults()
	{
		return actualResults;
	}
	
	/**
	 * Sets the actual result of the test results
	 * @param actualResult the actual result to be set 
	 * @throws IllegalArgumentException if the actualResult string is null or empty string
	 */
	private void setActualResults(String actualResult)
	{
		if (actualResult == null || actualResult.length() == 0)
		{
			throw new IllegalArgumentException("Invalid test results.");
		}
		
		this.actualResults = actualResult;
	}
	
	/**
	 * Returns true if the test results is passing, false if otherwise
	 * @return passing whether the test is passing
	 */
	public boolean isPassing() {
		return passing;
	}

	/**
	 * Sets whether the test is passing or not
	 * @param passing the passing to set
	 */
	public void setPassing(boolean passing) {
		this.passing = passing;
	}
	
	/**
	 * Returns String representation of Test result
	 * @return toString the String representation of Test result
	 */
	public String toString()
	{
		String toString = "";
		
		if (isPassing())
		{
			toString = PASS + ": " + actualResults;
		}
		else
		{
			toString = FAIL + ": " + actualResults;
		}
		
		return toString;
	}
	
	
	
	
	
	
	
	
	
	

}
