/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Concrete TestPlan class to hold test cases that belong to a project.
 * @author Rohit Kunta
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {

	/**
	 * Constructs the TestPlan with the given name
	 * @param testPlanName the name of the TestPlan
	 * @throws IllegalArgumentException if the proposed name is the same as FailingListTest.FAILING_TEST_LIST_NAME (case insensitive)
	 */
	public TestPlan(String testPlanName) {
		
		super(testPlanName);
		
		if (testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME))
		{
			throw new IllegalArgumentException("Invalid name.");
		}
	}

	/**
	 * Returns a 2D String array where the 
	 * - first column is the test case id, 
	 * - the second column is the test type, 
	 * - and the third column is the status (“PASS” or “FAIL”).
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		
		String[][] testCasesArray = new String[getTestCases().size()][3];
		
		for (int i = 0; i < getTestCases().size(); i++)
		{
			testCasesArray[i][0] = getTestCases().get(i).getTestCaseId();
			testCasesArray[i][1] = getTestCases().get(i).getTestType();
			testCasesArray[i][2] = getTestCases().get(i).getStatus();
		}
		
		return testCasesArray;
	}
	
	/**
	 * Adds test case to the SwapList via call to super
	 * Sets sets the TestCase’s TestPlan to the current TestPlan.
	 */
	@Override
	public void addTestCase(TestCase t)
	{
		super.addTestCase(t);
		t.setTestPlan(this);
	}

	/**
	 * Compares the name of the test plans alphabetically
	 * Comparison is case in-sensitive
	 * @return 1 if the current object is greater than the other object o
	 * 		 - 0 if the 2 objects are equal
	 * 		 - -1 of the current object is less than the other object o
	 */
	@Override
	public int compareTo(TestPlan o) {
		if (o == null)
		{
			throw new NullPointerException();
		}
		
		if (o.getClass() != getClass()) {
			throw new ClassCastException();
		}
		
		return getTestPlanName().compareToIgnoreCase(o.getTestPlanName());
		
	}
	
	

}
