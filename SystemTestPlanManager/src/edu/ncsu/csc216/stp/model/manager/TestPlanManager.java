/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Manages and integrates all the other model classes to make accessible to SystemPlanGUI
 * @author Rohit Kunta
 */
public class TestPlanManager {
	
	/**checks if TestPlanManager has changed since last save*/
	private boolean isChanged;
	
	/**failing test list*/
	private FailingTestList failingTestList;
	/**SortedList of TestPlan(s)*/
	private SortedList<TestPlan> testPlans;
	/**Contains currentTestList*/
	private AbstractTestPlan currentTestPlan;
	
	/**
	 * Constructor for TestPlanManager
	 * Initializes all fields
	 */
	public TestPlanManager()
	{
		setIsChanged(false);
		this.failingTestList = new FailingTestList();	
		this.testPlans = new SortedList<TestPlan>();
		getFailingTests();
		setCurrentTestPlan("Failing Tests");
	}
	
	
	/**
	 * Loads the TestPlans from a file
	 * Updates failingTestList
	 * Sets the currentTestPlan to the faiingTestList
	 * @param testPlanFile the file with the name of the file being loaded
	 */
	public void loadTestPlans(File testPlanFile)
	{
		this.testPlans = TestPlanReader.readTestPlansFile(testPlanFile);
		getFailingTests(); // updates failing tests
		setCurrentTestPlan("Failing Tests");
		setIsChanged(true);
	}
	
	/**
	 * Saves the testPlans SortedList field to a file
	 * Updates isChanged to false
	 * @param testPlanFile the file with the name of the file to save to
	 */
	public void saveTestPlans(File testPlanFile)
	{
		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);
		setIsChanged(false);
		
	}
	
	
	/**
	 * Checks if the TestPlanManager has been changed since the last save 
	 * @return true if TestPlanManager has been saved, false otherwise
	 */
	public boolean isChanged()
	{
		return this.isChanged;
	}
	
	/**
	 * Sets the value of isChanged
	 * @param isChanged the value to set isChanged to
	 */
	private void setIsChanged(boolean isChanged)
	{
		this.isChanged = isChanged;
	}
	
	/**
	 * Adds the test plan to the SortedList of TestPlans
	 * Sets isChanged to true
	 * @param testPlanName the name of the TestPlan being added
	 * @throws IllegalArgumentException if the testPlanName is FAILING_TEST_LIST_NAME 
	 * 											- Duplicate of another TestPlan
	 */
	public void addTestPlan(String testPlanName)
	{
		
		TestPlan testPlan = new TestPlan(testPlanName);
		try
		{
			testPlans.add(testPlan);
		} catch (IllegalArgumentException e)
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		setCurrentTestPlan(testPlanName);
		setIsChanged(true);
		getFailingTests();
	}
	
	
	/**
	 * Returns a list of test plan names. The “Failing Tests” list is always listed first.
	 * @return testPlanNames the String[] array with all the names of the TestPlan
	 */
	public String[] getTestPlanNames()
	{
		String[] testPlanNames = new String[testPlans.size() + 1];
		
		testPlanNames[0] = failingTestList.getTestPlanName();
		
		for (int i = 0; i < testPlans.size(); i++)
		{
			testPlanNames[i + 1] = testPlans.get(i).getTestPlanName();
		}
		
		return testPlanNames;
	}
	
	
	/**
	 * Updates the failingTestList field to be current with the the failing tests in the testPlans SortedList
	 * Rebuilds failingTestList each time to make maintaining a sorted order within the failingTestList easier
	 * Clears failingTestList first
	 */
	private void getFailingTests()
	{
		failingTestList.clearTests();
		failingTestList = new FailingTestList();
		
		//iterates through each testPlan and their SwapList of TestCases
		for (int i = 0; i < testPlans.size(); i++)
		{
			for (int j = 0; j < testPlans.get(i).getTestCases().size(); j++)
			{
				//checks if the testCase is passing
				if (!testPlans.get(i).getTestCases().get(j).isTestCasePassing())
				{
					failingTestList.addTestCase(testPlans.get(i).getTestCases().get(j));
				}
			}
		}
	}
	
	/**
	 * Sets the currentTestPlan to the AbstractTestPlan with the given name. 
	 * If a TestPlan with that name is not found, then the currentTestPlan is set to the failingTestList.
	 * @param testPlanName the name of the TestPlan being set to currentTestPlan
	 */
	public void setCurrentTestPlan(String testPlanName)
	{
		
		getFailingTests();
		
		this.currentTestPlan = failingTestList;
		
		for (int i = 0; i < testPlans.size(); i++)
		{
			if (testPlanName.equals(testPlans.get(i).getTestPlanName()))
			{
				this.currentTestPlan = testPlans.get(i);
			}
		}
		
	}
	
	
	/**
	 * Returns the current TestPlan
	 * @return currentTestPlan the test case being returned
	 */
	public AbstractTestPlan getCurrentTestPlan()
	{
		return this.currentTestPlan;
	}
	
	/**
	 * Edits the currentTestPlan. isChanged is set to true
	 * @param testPlanName the new name for the TestPlan
	 * @throws IllegalArgumentException if:
	 * 				- currentTestPlan is a FailingTestList object
	 * 				- if the new name matches "Failing Tests"
	 * 				- is a duplicate of another TestPlan name
	 */
	public void editTestPlan(String testPlanName)
	{
		if ("Failing Tests".equalsIgnoreCase(currentTestPlan.getTestPlanName()))
		{
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		
		if ("Failing Tests".equalsIgnoreCase(testPlanName))
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		
		if (currentTestPlan.getTestPlanName().equalsIgnoreCase(testPlanName) || isDuplicate(testPlanName))
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		
		currentTestPlan.setTestPlanName(testPlanName);
		
		setIsChanged(true);
		
	}
	
	/**
	 * Returns whether or not the testPlanName is already taken in the TestPlan (case insensitive), excluding failing Test plan name
	 * @param testPlanName the testPlanName being checked for being a duplicate name 
	 * @return true if the testPlanNam is equal to the currentTestPlan name or one of the names in the testPlans SortedList
	 * 		 - false if a duplicate name is not found
	 */
	private boolean isDuplicate(String testPlanName)
	{
		if (testPlanName.equalsIgnoreCase(currentTestPlan.getTestPlanName()))
		{
			return true;
		}
		
		for (int i = 0; i < testPlans.size(); i++)
		{
			if (testPlanName.equalsIgnoreCase(testPlans.get(i).getTestPlanName()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Removes the currentTestPlan from the SortedList
	 * currentTestPlan is set to the FailingTestList
	 * isChanged is set to true
	 * @throws IllegalArgumentException if currentTestPlan is failingTestList
	 */
	public void removeTestPlan()
	{
		if (currentTestPlan.equals(failingTestList))
		{
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		
		for (int i = 0; i < testPlans.size(); i++)
		{
			if (currentTestPlan.getTestPlanName().equals(testPlans.get(i).getTestPlanName()))
			{
				testPlans.remove(i);
			}
		}
		
		setCurrentTestPlan("Failing Tests");
		setIsChanged(true);
	}
	
	/**
	 * Adds a TestCase to currentTestPlan.
	 * If currentTestPlan is not a TestPlan, do nothing
	 * Otherwise, it adds the test case/
	 * if TestCase is failing, then updates failingTestList
	 * isChanged() is updated to true
	 * @param t the TestCase being added to the currentTestPlan
	 */
	public void addTestCase(TestCase t)
	{
		if (!currentTestPlan.equals(failingTestList))
		{
			currentTestPlan.addTestCase(t);
			
			if (!t.isTestCasePassing())
			{
				getFailingTests();
			}
			
			setIsChanged(true);
		}
	}
	
	/**
	 * Adds the test result to the test case at the given index in the current test plan.
	 * If the tests are failing, then the Failing Test List should be updated. 
	 * @param idx the index of the TestCase in the currentTestPlan
	 * @param passing whether the TestResult is passing or not
	 * @param actualResult the actual result of the test result
	 */
	public void addTestResult(int idx, boolean passing, String actualResult)
	{
		currentTestPlan.getTestCase(idx).addTestResult(passing, actualResult);
		
		
		getFailingTests();
		
		
	}
	
	
	/**
	 * Clears out the TestPlanManager by setting testPlans to an empty SortedList, 
	 * failingTestList to an empty FailingTestList(), 
	 * currentTestPlan to the failingTestList, 
	 * and isChanged to false.
	 */
	public void clearTestPlans()
	{
		testPlans = new SortedList<TestPlan>();
		failingTestList = new FailingTestList();
		setCurrentTestPlan("Failing Tests");
	}

}
