/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 *  Represents a test plan in the SystemTestPlan system. 
 *  There are two main types of test plans: 
 *  		- normal test plans associated with a named project under development where test cases can be added to the end of the list 
 *  		- the failing test list that holds test cases from across all test plans that are currently failing.
 *  @author Rohit Kunta
 */
public abstract class AbstractTestPlan {
	
	/**The name of the test plan*/
	private String testPlanName;
	/**The SwapList of the test cases*/
	private SwapList<TestCase> testCases;
	
	/**
	 * Constructor for AbstractTestPlan
	 * Sets the fields from the parameters and constructs a SwapList for the TestCases.
	 * @param testPlanName the name of the TestPlan
	 * @throws IllegalArgumentException if the testPlanName is null or empty String with message "Invalid name."
	 */
	public AbstractTestPlan(String testPlanName)
	{
		setTestPlanName(testPlanName);
		testCases = new SwapList<TestCase>();
	}

	/**
	 * Returns the name of the test plan
	 * @return testPlanName the name of the test Plan
	 */
	public String getTestPlanName() {
		return testPlanName;
	}

	/**
	 * Sets the testPlanName of the test case
	 * @param testPlanName the testPlanName to set
	 * @throws IllegalArgumentException if the testPlanName is null or empty String with message "Invalid name."
	 */
	public void setTestPlanName(String testPlanName) {
		if (testPlanName == null || testPlanName.length() == 0)
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		
		this.testPlanName = testPlanName;
	}

	/**
	 * Returns the SwapList of the test cases 
	 * @return testCases the SwapList of the test cases
	 */
	public SwapList<TestCase> getTestCases() {
		return testCases;
	}
	
	/**
	 * Adds a test case t to the SwapList testCases
	 * @param t the test case to be added
	 * Any exceptions are thrown out of the method
	 * @throws Exception if any exceptions are thrown while adding to the list
	 */
	public void addTestCase(TestCase t)
	{
		testCases.add(t);
	}
	
	/**
	 * Adds a test case t to the SwapList testCases
	 * @param idx the index of the test case in the SwapList to be removed
	 * Any exceptions are thrown out of the method
	 * @throws Exception if any exceptions are thrown while removing from the list
	 */
	public void removeTestCase(int idx)
	{
		testCases.remove(idx);
	}
	
	/**
	 * Returns the test case through index
	 * @param idx the index of the test case
	 * @return returns the test case
	 */
	public TestCase getTestCase(int idx)
	{
		return testCases.get(idx);
	}
	
	/**
	 * Counts the number of TestCases that are failing. 
	 * @return numFailing the number of test cases in the test plan that are failing
	 */
	public int getNumberOfFailingTests()
	{
		int numFailing = 0;
		
		for (int i = 0; i < testCases.size(); i++)
		{
			if (!testCases.get(i).isTestCasePassing())
			{
				numFailing++;
			}
		}
		
		return numFailing;
	}
	
	/**
	 * Sends the test result parameters to the TestCase at the given index.
	 * @param idx the index of the test case
	 * @param passing whether the testResulyt is failing or not
	 * @param actualResults the Actual results of the test result
	 * Any exceptions are thrown out of the method
	 * @throws Exception if any exceptions are thrown while adding to the list
	 */
	public void addTestResult(int idx, boolean passing, String actualResults)
	{	
		testCases.get(idx).addTestResult(passing, actualResults);	
	}
	
	/**
	 * An abstract method that returns a 2D String array of the test cases in a test plan.
	 * @return the 2D string Array of the test cases in a test plan
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * Case-insensitive hashcode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.toLowerCase().hashCode());
		return result;
	}

	/**
	 * Case-insensitive
	 * Compares this TestPlan to another based off of testPlanName
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AbstractTestPlan))
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		if (testPlanName == null) {
			if (other.testPlanName != null)
				return false;
		} else if (!testPlanName.equalsIgnoreCase(other.testPlanName))
			return false;
		return true;
	}
	
	
	
	

	
	

}
