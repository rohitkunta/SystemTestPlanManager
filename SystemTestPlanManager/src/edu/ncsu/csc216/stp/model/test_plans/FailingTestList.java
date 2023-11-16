/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Concrete class that embodies the failingTestList in GUI and TestPlanManager
 * Contains a list of ONLY failing TestCases across all TestPlan(s) in the TestPlanManager
 * @author Rohit Kunta
 */
public class FailingTestList extends AbstractTestPlan {

	/**Name of the failing test plan*/
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * Constructor for FailingTestList
	 * Sets name to FAILING_TEST_LIST_NAME constant
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
	}

	/**
	 * Returns a 2D String array where: 
	 * - the first column is the test case id, 
	 * - the second column is the test type, and 
	 * - the third column is the test plan name associated with the TestCase. 
	 * If the test plan is null, then use empty string for the test plan name.
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		
		String[][] testCasesArray = new String[getTestCases().size()][3];
		
		for (int i = 0; i < getTestCases().size(); i++)
		{
			testCasesArray[i][0] = getTestCases().get(i).getTestCaseId();
			testCasesArray[i][1] = getTestCases().get(i).getTestType();
			if (getTestCases().get(i).getTestPlan() == null)
			{
				testCasesArray[i][2] = "";
			}
			else 
			{
				testCasesArray[i][2] = getTestCases().get(i).getTestPlan().getTestPlanName();
			}
		}
		
		return testCasesArray;
	}
	
	/**
	 * Adds test case to end of SwapList.
	 * Checks if test case is failing before adding'
	 * @throws IllegalArgumentException w/ message: "Cannot add passing test case." 
	 * 		- if the test case is not failing 
	 */
	@Override
	public void addTestCase(TestCase t)
	{
		if (t.isTestCasePassing())
		{
			throw new IllegalArgumentException("Cannot add passing test case.");
		}
		
		getTestCases().add(t);
		
	}
	
	/**
	 * Sets the name of the Test Plan to FAILING_TEST_LIST_NAME
	 * Checks if parameter is equal to FAILING_TEST_LIST_NAME (case in-sensitive)
	 * @throws IllegalArgumentException if the parameter is not equal to FAILING_TEST_LIST_NAME (case in-sensitive)
	 */
	@Override
	public void setTestPlanName(String testPlanName)
	{
		if (!testPlanName.equalsIgnoreCase(FAILING_TEST_LIST_NAME))
		{
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
		
		super.setTestPlanName(FAILING_TEST_LIST_NAME);
	}
	
	/**
	 *  Clears the FailingTestList of all TestCases.
	 */
	public void clearTests()
	{
		for (int i = getTestCases().size() - 1; i >= 0; i--)
		{
			getTestCases().remove(i);
		}
	}
	
	

}
