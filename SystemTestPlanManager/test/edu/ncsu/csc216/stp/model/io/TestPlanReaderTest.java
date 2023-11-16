package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.SortedList;

class TestPlanReaderTest {

	/**Sets valid file name*/
	private static final String VALID_FILE = "test-files/test-plans0.txt";
	
	/**Sets valid file name*/
	private static final String VALID_FILE2 = "test-files/test-plans1.txt";
	
	/**Sets valid file name*/
	private static final String INVALID_FILE3 = "test-files/test-plans3.txt";
	
	/**Sets valid file name*/
	private static final String INVALID_FILE4 = "test-files/test-plans4.txt";
	
	/**Sets valid file name*/
	private static final String INVALID_FILE5 = "test-files/test-plans5.txt";
	
	/**Sets valid file name*/
	private static final String INVALID_FILE6 = "test-files/test-plans6.txt";
	
	/**Sets valid file name*/
	private static final String INVALID_FILE7 = "test-files/test-plans7.txt";
	
	/**Sets valid file name*/
	private static final String INVALID_FILE8 = "test-files/test-plans8.txt";
	
	/**Sets valid file name*/
	private static final String INVALID_FILE9 = "test-files/test-plans9.txt";
	
	
	
	@Test
	void testReadTestPlansFile() {
		
		File file = new File(VALID_FILE);
		
		SortedList<TestPlan> testPlans = TestPlanReader.readTestPlansFile(file);
		
		assertEquals(2, testPlans.size());
		
		assertEquals(2, testPlans.get(0).getTestCases().size());
		
		assertEquals(3, testPlans.get(1).getTestCases().size());
		
		File file2 = new File(VALID_FILE2);
		
		testPlans = TestPlanReader.readTestPlansFile(file2);
		
		assertEquals(2, testPlans.size());
		
		assertEquals(2, testPlans.get(0).getTestCases().size());
		
		assertEquals(3, testPlans.get(1).getTestCases().size());
		
		File file3 = new File(INVALID_FILE9);
		
		testPlans = TestPlanReader.readTestPlansFile(file3);
		
		assertEquals(1, testPlans.size());
		
		File file4 = new File(INVALID_FILE8);
		
		testPlans = TestPlanReader.readTestPlansFile(file4);
		
		assertEquals(1, testPlans.size());
		
		File file5 = new File(INVALID_FILE7);
		
		testPlans = TestPlanReader.readTestPlansFile(file5);
		
		assertEquals(1, testPlans.size());
		
		File file6 = new File(INVALID_FILE6);
		
		testPlans = TestPlanReader.readTestPlansFile(file6);
		
		assertEquals(1, testPlans.size());
			
		File file7 = new File(INVALID_FILE5);
		
		testPlans = TestPlanReader.readTestPlansFile(file7);
		
		assertEquals(1, testPlans.size());
		
		File file8 = new File(INVALID_FILE4);
		
		testPlans = TestPlanReader.readTestPlansFile(file8);
		
		assertEquals(1, testPlans.size());
		
		File file9 = new File(INVALID_FILE3);
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> TestPlanReader.readTestPlansFile(file9));
		assertEquals("Unable to load file.", e1.getMessage(), "incorrect exception message");
		
		
	}
	
	

}
