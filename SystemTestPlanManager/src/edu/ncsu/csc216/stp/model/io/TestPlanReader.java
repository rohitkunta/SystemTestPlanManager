/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Processes a file containing TestPlan(s) with their respective TestCase(s) and TestResult(s)
 * @author Rohit Kunta
 */
public class TestPlanReader {
	
	
	/**
	 * Reads in the test plans from a file
	 * @param file the file object with the name of the file being read from
	 * @return testPlans the SortedList of TestPlan(s)
	 * @throws IllegalArgumentException if the file cannot be opened or found, or the first character in the file is not '!'.
	 */
	public static SortedList<TestPlan> readTestPlansFile(File file)
	{
		try {
			String fileString = "";
			String line = "";
			
			Scanner fileReader = new Scanner(file); //Create a file scanner to read the file
			
			//Adds \n delimiter to end of each line in file.
			while (fileReader.hasNextLine())
			{
				line = fileReader.nextLine() + "\n";
				fileString += line;
			}
			
			
			SortedList<TestPlan> testPlans = new SortedList<TestPlan>(); //Create an empty array of product objects
			
			if (!fileString.startsWith("!"))
			{
				fileReader.close();
				throw new IllegalArgumentException("Unable to load file.");
			}
			
			Scanner scanner = new Scanner(fileString);
			
			scanner.useDelimiter("\\r?\\n?[!]");
			
			while (scanner.hasNext())
			{
				try {
					
					String testPlanString = scanner.next();
					System.out.println("TestPlanString: " + testPlanString);
					TestPlan testPlan = processTestPlan(testPlanString.trim()); // passes one product
					testPlans.add(testPlan);
					
				} catch (Exception e){
					
					//We leave this empty
				}
			}
			
		    //Close the Scanner b/c we're responsible with our file handles
		    fileReader.close();
		    scanner.close();
		    //Return the ArrayList with all the products we read!
		    for (int i = 0; i < testPlans.size(); i++)
		    {
		    	System.out.println("Test Plan name: " + testPlans.get(i).getTestPlanName());
		    }
		    return testPlans;
			
		} catch (FileNotFoundException | IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}
	
	
	/**
	 * Processes a string with one test plan in it
	 * @param testPlan the string of the test plan from the file
	 * @return testPlan the TestPlan read from the string
	 */
	private static TestPlan processTestPlan(String testPlan)
	{
		
			
			TestPlan testPlanObj;
			//creates abstraction within TestPlan
			Scanner scannerP = new Scanner(testPlan);
			
			// creates entire [TestPlan string] with: TestPlan, TestCases and TestResults
			String testPlanString = testPlan;
			
			//gets name of TestPlan
			Scanner scannerName = new Scanner(testPlanString);
			scannerName.useDelimiter("\n");
			String testPlanName = scannerName.next();
			
		try
		{
			//creates Product object
			testPlanObj = new TestPlan(testPlanName.trim());
			
			Scanner placeholder = new Scanner(testPlanString);
			
			//creates abstraction within task
			Scanner scannerT = new Scanner(testPlanString); // takes one TestPlan String
			scannerT.useDelimiter("\\n"); // removes name of TestPlan
			scannerT.next();	// removes name of TestPlan
			
			scannerT.useDelimiter("\\r?\\n?[#]"); // divides into TestCases with testResults
			
			//creates task object and notes list
			while (scannerT.hasNext()) 
			{
				
				TestCase testCase = processTestCase(scannerT.next().trim());
				
				try
				{
					testPlanObj.addTestCase(testCase);
				} catch (IllegalArgumentException e)
				{
					//ignore dont add test Case
				}
				
			}
			
			scannerP.close();
			scannerT.close();
			scannerName.close();
			placeholder.close();
			
			
			return testPlanObj;
			
		} catch (IllegalArgumentException e) // caught exception
		{
			testPlanObj = new TestPlan(testPlanName.trim());
			
			return testPlanObj;
		}
	}
	
	/**
	 * Processes a string with one test case in it, with its respective TestResults
	 * @param testCase the string of the test plan from the file
	 * @return testCase the TestCase read from the string
	 * @throws IllegalArgumentException if there are any formatting problems in the file or problems while generating objects
	 */
	private static TestCase processTestCase(String testCase)
	{
		
		Scanner newLine = new Scanner(testCase);
		newLine.useDelimiter("\n");
		
		if(!newLine.hasNext()) {
			newLine.close();
			throw new IllegalArgumentException("There is no TestCaseId or TestType.");
		}
		
		String testIdTestType = newLine.next();
		
		if(!newLine.hasNext()) {
			newLine.close();
			throw new IllegalArgumentException("There is no description, expected results, or actual results.");
		}
		
		Scanner taskDelim = new Scanner(testIdTestType);
		taskDelim.useDelimiter(","); // divides one task 
		
		if (!taskDelim.hasNext())
		{
			taskDelim.close();
			newLine.close();
			throw new IllegalArgumentException("Does not have both test id and type.");
		}
		
		String[] parameters = new String[4];
		
		String testId, testType;
		
		try
		{
			int i = 0;
			
			//read in tokens for TestCase fields
			while (taskDelim.hasNext())
			{
				String parameter = taskDelim.next();
				
				parameters[i] = parameter;	
				
				i++;
			}
			
			if (parameters[0] == null || parameters[1] == null)
			{
				newLine.close();
				taskDelim.close();
				throw new IllegalArgumentException("No Test Id or No Test Type, or none of either.");
			}
			
			//Assigns local variables to respective parameters, All variables except notes list are good for one task
			testId = parameters[0].trim();
			testType = parameters[1].trim();
			
			//Changes delimiter of newline to read after justTask String and until next Task
			newLine.useDelimiter("\\r?\\n?[-]");
			
			//String contains the description and expected results that can be broken apart using the delimiter \\r?\\n?[*].
			String testDescExpRes = newLine.next();
			
			Scanner testInfo = new Scanner(testDescExpRes);
			
			//breaks test description and expected results apart
			testInfo.useDelimiter("\\r?\\n?[*]");
			
			while (testInfo.hasNext())
			{
				
				String parameter = testInfo.next();
				
				System.out.println(parameter);
				
				parameters[i] = parameter;	
				
				i++;
			}
			
			if (parameters[3] == null)
			{
				newLine.close();
				taskDelim.close();
				testInfo.close();
				throw new IllegalArgumentException("No Test Desc or No Expected Results, or none of either.");
			}
			
			//now we have all the necessary parameters for testCase construction
			String testDesc = parameters[2].trim();
			String testExpResults = parameters[3].trim();
			
			TestCase testCaseObj = new TestCase(testId, testType, testDesc, testExpResults);
			
			//adds TestResults to ActualResultsList
			//one entire actualResults Line
			while (newLine.hasNext())
			{
				//Contains one entire ActResult String. Ex: "PASS: everything is empty"
				String testResultString = newLine.next().trim();
				
				Scanner actResult = new Scanner(testResultString);
				
				System.out.print("TestResults String: " + testResultString);
				System.out.print("Hello world.");
				
				if ("PASS".equals(testResultString) || "FAIL".equals(testResultString))
				{
					testInfo.close();
					taskDelim.close();
					newLine.close();
					actResult.close();
					throw new IllegalArgumentException("There is no actual result info");
				}
				
				actResult.useDelimiter(":");
				
				boolean passing;
				
				if (actResult.hasNext())
				{
					String passingString = actResult.next();
					
					if ("PASS".equals(passingString))
					{
						passing = true;
					}
					else if ("FAIL".equals(passingString))
					{
						passing = false;
					}
					else
					{
						testInfo.close();
						taskDelim.close();
						newLine.close();
						actResult.close();
						throw new IllegalArgumentException("No PASS or FAIL text");
					}
					
					actResult.useDelimiter(" ");
					
					actResult.next();
					
					actResult.useDelimiter("-");
					
					String actualResults = actResult.next().trim();
					
					if (actualResults.length() == 0)
					{
						testInfo.close();
						taskDelim.close();
						newLine.close();
						actResult.close();
						throw new IllegalArgumentException("There is no actual result info");
					}
					
					System.out.print("Actual Results log: " + actualResults);
					System.out.print(actualResults);
					
					testCaseObj.addTestResult(passing, actualResults);
					
					actResult.close();
					
				}
			}
			
			
			testInfo.close();
			taskDelim.close();
			newLine.close();
			
			return testCaseObj;
		} catch (IndexOutOfBoundsException | IllegalArgumentException e)
		{
			throw new IllegalArgumentException("Invalid TestCase information.");
		}
	
	}
	

}
