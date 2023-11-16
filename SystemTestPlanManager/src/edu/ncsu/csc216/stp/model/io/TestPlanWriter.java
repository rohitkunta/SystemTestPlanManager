/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Writes the open test plan’s to the given file.
 * @author Rohit Kunta
 */
public class TestPlanWriter {
	
	/**
	 * Writes a SortedList of TestPlan(s) to a specific file
	 * @param file the File object with the file name
	 * @param testPlans the SortedList of TestPlans
	 * @throws IllegalArgumentException if there are any errors or exceptions while writing
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> testPlans)
	{
		try
		{
			PrintStream fileWriter = new PrintStream(file);
			
			writeTestPlans(testPlans, fileWriter);

			fileWriter.close();
			
		} catch(Exception e)
		{
			throw new IllegalArgumentException("Unable to save file.");
		}
		
	}
	
	/**
	 * Writes Individual TestPlans from a SortedList of TestPlans in alphabetical order
	 * @param testPlans the SortedList of TestPlans that will be used to write to a file
	 * @param fileWriters the PrintStream object that will be used to write to a particular file
	 */
	private static void writeTestPlans(ISortedList<TestPlan> testPlans, PrintStream fileWriters)
	{
		
		for (int i = 0; i < testPlans.size(); i++) {
			
			fileWriters.printf("! %s\r\n", testPlans.get(i).getTestPlanName());
			
			for (int j = 0; j < testPlans.get(i).getTestCases().size(); j++)
			{
				fileWriters.print(testPlans.get(i).getTestCases().get(j).toString());
			}
		}
	}

}
