/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          X Team #4 Dictionary Graph
// FILES:            GraphProcessor.java, GraphProcessorTest.java, Graph.java, GraphTest.java
//					 WordProcessor.java, 
//
// USER:             Jake Rymsza, Eden Schuette, Ben Schulman, Andrew Schaefer, Dawanit Satitsumpun
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             no known bugs
//
//////////////////////////// 80 columns wide //////////////////////////////////

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class is in charge of testing the GraphProcessor class to ensure it properly functions. Each test displays whether it
 * passed or failed, and any additional information required to understand the results.
 * @author Jake
 *
 */
public class GraphProcessorTest {
	
	GraphProcessor graphProc = null;
	String expected = null; // Used in tests where a string is returned
	String actual = null; // Used in tests where a string is returned
	int expectedInt; // Used in tests where an int is returned
	int actualInt; // Used in tests where an int is returned
	List<String> expectedList = null; // Used in tests where a list is returned
	List<String> actualList = null; // Used in tests where a list is returned
	String nameFile = "TestFile.txt"; // Name of the first test file
	String emptyFile = "EmptyFile.txt"; // Name of an empty test file

	/**
	 * The setup method for each test. This is called before each test runs, its purpose is to
	 * create a fresh, graphProcessor before each test.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		graphProc = new GraphProcessor();
	}
	
	/**
	 * This method is called after every test method. It cleans up after the test, and resets the 
	 * graphProcessor
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		graphProc =null;
		expectedList = null;
		actualList = null;
		expected = null;
		actual = null;
		expectedInt = 0;
		actualInt = 0;
	}

	/**
	 * Tests that the populateGraph method adds the correct number of vertices.
	 */
	@Test
	public void test01_populateGraph_AddsCorrectNumVertices() {
		expectedInt=44;
		
		actualInt= graphProc.populateGraph(nameFile);
		if(expectedInt!=actualInt){
			fail("expected: "+expected+ " actual: "+actual);
		}
	}
	
	/**
	 * Checks that the populateGraph method returns a -1 when the file sent to it is not found.
	 */
	@Test
	public void test02_populateGraph_WhenFileNotFound() {
		expectedInt=-1;
		actualInt= graphProc.populateGraph("Bad File Name");
		if(expectedInt!=actualInt){
			fail("expected: "+expected+ " actual: "+actual);
		}
	}
	
	/**
	 * Tests that the populateGraph method returns a 0 when an empty file is sent.
	 */
	@Test
	public void test03_populateGraph_WhenFileIsEmpty() {
		expectedInt=0;
		actualInt = 5;
		actualInt= graphProc.populateGraph(emptyFile);
		System.out.println(actualInt);
		if(expectedInt!=actualInt){
			fail("expected: "+expected+ " actual: "+actual);
		}
	}
	
	/**
	 * Tests that the getShortestPath method works correctly with a small file of Strings
	 */
	@Test
	public void test04_getShortestPath_WithSmallFile(){
		expectedList = new ArrayList<String>();
		
		expectedList.add("CAT");
		expectedList.add("HAT");
		expectedList.add("HEAT");
		expectedList.add("WHEAT");
		
		graphProc.populateGraph(nameFile);
		actualList = graphProc.getShortestPath("CAT", "WHEAT");
		
		
		if(!expectedList.equals(actualList)){
			fail("expected: "+expectedList.toString()+ " actual: "+actualList.toString());
		}
	}
	
	/**
	 * Tests that the getShortestPath method returns an empty list when the same two words are
	 * sent to the method.
	 */
	@Test
	public void test05_getShortestPath_SameWords(){
		expectedList = new ArrayList<String>();
		
		graphProc.populateGraph(nameFile);
		actualList = graphProc.getShortestPath("CAT", "CAT");
		
		if(!expectedList.equals(actualList)){
			fail("expected: "+expectedList.toString()+ " actual: "+actualList.toString());
		}
	}
	
	/**
	 * Tests that the getShortestPath method returns null when no path exists between the two
	 * specified words
	 */
	@Test
	public void test06_getShortestPath_NoPath() {
		expectedList = null;
		
		graphProc.populateGraph(nameFile);
		actualList = graphProc.getShortestPath("CAT", "ELEPHANT");
		
		if(actualList != null){
			fail("expected: null"+ " actual: "+actualList.toString());
		}
	}
	
	/**
	 * Tests that the getSHortestDistance file correctly returns the path length when sent a small
	 * file of Strings.
	 */
	@Test
	public void test07_getShortestDistance_SmallFile() {
		expectedInt =3;
		graphProc.populateGraph(nameFile);
		actualInt = graphProc.getShortestDistance("CAT", "WHEAT");
		
		if(expectedInt!=actualInt){
			fail("expected: "+expected+ " actual: "+actual);
		}
	}
	
	/**
	 * Tests that the getSHortestDistance file returns -1 when two of the same word are sent to
	 * the method.
	 */
	@Test
	public void test08_getShortestDistance_SameWord() {
		expectedInt = -1;
		graphProc.populateGraph(nameFile);
		actualInt = graphProc.getShortestDistance("CAT", "CAT");
		
		if(expectedInt!=actualInt){
			fail("expected: "+expected+ " actual: "+actual);
		}
	}
	
	/**
	 * Tests that the getSHortestDistance file returns -1 when no path exists between the two
	 * specified words.
	 */
	@Test
	public void test09_getShortestDistance_NoPath() {
		expectedInt = -1;
		graphProc.populateGraph(nameFile);
		actualInt = graphProc.getShortestDistance("CAT", "ELEPHANT");
		
		if(expectedInt!=actualInt){
			fail("expected: "+expected+ " actual: "+actual);
		}
	}
}
