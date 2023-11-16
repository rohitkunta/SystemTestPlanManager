package edu.ncsu.csc216.stp.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;


class TestPlanManagerTest {
	
	/**Stores the valid file to compare to*/
	private static final String VALID_FILE = "test-files/test-plans1.txt";
	
	/**Stores the valid file to compare to*/
	private static final String EXPECTED_RESULTS = "test-files/exp_test-plans1.txt";
	
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
	void testTestPlanManager() {
		
		TestPlanManager manager = new TestPlanManager();
		
		assertFalse(manager.isChanged());
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
	}

	@Test
	void testLoadTestPlans() {
		
		TestPlanManager manager = new TestPlanManager();
		
		File file = new File(VALID_FILE);
		
		manager.loadTestPlans(file);
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
		assertEquals(3, manager.getCurrentTestPlan().getTestCases().size());
		
	}

	@Test
	void testSaveTestPlans() {
		
		TestPlanManager manager = new TestPlanManager();
		
		File file = new File(VALID_FILE);
		
		manager.loadTestPlans(file);
		
		File fileOutput = new File(ACTUAL_RESULTS);
		
		manager.saveTestPlans(fileOutput);
		
		assertFalse(manager.isChanged());
		
		checkFiles(EXPECTED_RESULTS, ACTUAL_RESULTS);
	}

	@Test
	void testAddTestPlan() {
		
		TestPlanManager manager = new TestPlanManager();
		
		manager.addTestPlan("PackScheduler");
		
		assertEquals("PackScheduler", manager.getCurrentTestPlan().getTestPlanName());
		
	}

	@Test
	void testGetTestPlanNames() {
		
		TestPlanManager manager = new TestPlanManager();
		
		manager.addTestPlan("PackScheduler");
		
		manager.addTestPlan("WolfScheduler");
		
		manager.addTestPlan("Lil Xan");
		
		System.out.println("Index 1 of manager: " + manager.getTestPlanNames()[1]);
		System.out.println("Index 2 of manager: " + manager.getTestPlanNames()[2]);
		System.out.println("Index 3 of manager: " + manager.getTestPlanNames()[3]);
		
		boolean equal = true;
		
		String[] testPlanNameArray = new String[] {"Failing Tests", "Lil Xan", "PackScheduler", "WolfScheduler"};
		
		assertEquals(4, manager.getTestPlanNames().length);
		
		if (testPlanNameArray.length != manager.getTestPlanNames().length) {
            equal = false;
        }
		
		for (int i = 0; i < testPlanNameArray.length; i++) {
			
			System.out.println("testPlanNameArray: " + testPlanNameArray[i] + " manager name: " + manager.getTestPlanNames()[i]);
            
			if (!testPlanNameArray[i].equals(manager.getTestPlanNames()[i])) 
			{
				System.out.println("testPlanNameArray: " + testPlanNameArray[i] + " manager name: " + manager.getTestPlanNames()[i]);
                equal = false;
                System.out.println(equal);
            }
        }
		
		assertTrue(equal);
		
	}

	@Test
	void testSetCurrentTestPlan() {
		
		TestPlanManager manager = new TestPlanManager();
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
		manager.addTestPlan("PackScheduler");
		
		manager.addTestPlan("WolfScheduler");
		
		manager.setCurrentTestPlan("PackScheduler");
		
		assertEquals("PackScheduler", manager.getCurrentTestPlan().getTestPlanName());
		
		manager.setCurrentTestPlan("not found");
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
	}

	@Test
	void testEditTestPlan() {
		
		TestPlanManager manager = new TestPlanManager();
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> manager.editTestPlan("Will fail"));
		assertEquals("The Failing Tests list may not be edited.", e1.getMessage(), "Incorrect exception thrown with invalid test plan name - " + "Will fail");
		
		manager.addTestPlan("PackScheduler");
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> manager.editTestPlan("Failing Tests"));
		assertEquals("Invalid name.", e2.getMessage(), "Incorrect exception thrown with invalid test plan name - " + "Failing Tests");
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> manager.editTestPlan("PackScheduler"));
		assertEquals("Invalid name.", e3.getMessage(), "Incorrect exception thrown with invalid test plan name - " + "Failing Tests");
		
		manager.editTestPlan("WolfScheduler");
		
		assertTrue(manager.isChanged());
		
	}

	@Test
	void testRemoveTestPlan() {
		
		TestPlanManager manager = new TestPlanManager();
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> manager.removeTestPlan());
		assertEquals("The Failing Tests list may not be deleted.", e1.getMessage(), "Incorrect exception thrown with invalid remove - ");
		
		manager.addTestPlan("PackScheduler");
		
		manager.addTestPlan("WolfScheduler");
		
		assertEquals("WolfScheduler", manager.getCurrentTestPlan().getTestPlanName());
		
		manager.removeTestPlan();
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
	}

	@Test
	void testAddTestCase() {
		
		TestPlanManager manager = new TestPlanManager();
		
		manager.addTestPlan("PackScheduler");
		
		manager.addTestPlan("WolfScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		TestCase t2 = new TestCase("Test ID", "Test Type", "Test description", "Test Expected Results");
		
		t2.addTestResult(true, "Passing for test");
		
		manager.addTestCase(t);
		manager.addTestCase(t2);
		
		assertEquals(2, manager.getCurrentTestPlan().getTestCases().size());
		
		manager.setCurrentTestPlan("Failing Tests");
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
		assertEquals(1, manager.getCurrentTestPlan().getTestCases().size());
		
	}

	@Test
	void testAddTestResult() {
		
		TestPlanManager manager = new TestPlanManager();
		
		manager.addTestPlan("PackScheduler");
		
		manager.addTestPlan("WolfScheduler");
		
		TestCase t = new TestCase("Valid File", "Requirements", "Test description", "Course catalog contains 13 courses");
		
		manager.addTestCase(t);
		
		manager.setCurrentTestPlan("Failing Tests");
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
		assertEquals(1, manager.getCurrentTestPlan().getTestCases().size());
		
		manager.setCurrentTestPlan("WolfScheduler");
		
		manager.addTestResult(0, true, "passing for test");
		
		manager.setCurrentTestPlan("Failing Tests");
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
		assertEquals(0, manager.getCurrentTestPlan().getTestCases().size());
	}

	@Test
	void testClearTestPlans() {
		
		TestPlanManager manager = new TestPlanManager();
		
		manager.addTestPlan("PackScheduler");
		
		manager.addTestPlan("WolfScheduler");
		
		assertEquals("WolfScheduler", manager.getCurrentTestPlan().getTestPlanName());
		
		manager.clearTestPlans();
		
		assertEquals("Failing Tests", manager.getCurrentTestPlan().getTestPlanName());
		
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
