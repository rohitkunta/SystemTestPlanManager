package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.SortedList;

class TestPlanWriterTest {
	
	/**Stores the valid file to compare to*/
	private static final String VALID_FILE = "test-files/test-plans1.txt";
	
	/**Stores the actual file to compare to*/
	private static final String EXPECTED_RESULTS = "test-files/act_test-plans1.txt";
	
	/**Stores the actual file to compare to*/
	private static final String ACTUAL_RESULTS = "test-files/act_test-plans1.txt";
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "empty-file.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "act_test-plans.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	@Test
	void testWriteTestPlanFile() {
		
		SortedList<TestPlan> testPlans = TestPlanReader.readTestPlansFile(new File(VALID_FILE));
		
		assertEquals(2, testPlans.size());
		
		assertEquals(2, testPlans.get(0).getTestCases().size());
		
		assertEquals(3, testPlans.get(1).getTestCases().size());
		
		TestPlanWriter.writeTestPlanFile(new File(ACTUAL_RESULTS), testPlans);
		
		checkFiles(EXPECTED_RESULTS, ACTUAL_RESULTS);
		
		//incorrect file name
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> TestPlanWriter.writeTestPlanFile(null, testPlans));
		assertEquals("Unable to save file.", e1.getMessage(), "incorrect exception message");
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
