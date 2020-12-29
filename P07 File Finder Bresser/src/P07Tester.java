//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P07 File Finder - Devin Bresser
// Files:   ShallowFileIterator.java, DeepFileIterator.java, 
//						FilteredFileIterator.java,	P07Tester.java
// Course:  CS300 - Spring 2020 - Mouna Kacem
//
// Author:  DEVIN BRESSER
// Email:   BRESSER2@WISC.EDU
// Lecturer's Name: MOUNA KACEM
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understood the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course 
// staff must fully acknowledge and credit those sources here.  If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class P07Tester {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("testShallowFileIterator: " + 
					testShallowFileIterator(new File("filesystem")));
		
		System.out.println("testDeepFileIterator: " + 
				testDeepFileIterator(new File("filesystem")));
		
		System.out.println("testFilteredFileIterator: " + 
				testFilteredFileIterator(new File("filesystem")));


	}
	
  /**
   * Tests functionality of ShallowFileIterator
   * 
   * @return true for correct functionality, or false for incorrect
   * 
   */
	public static boolean testShallowFileIterator(File myFile) throws FileNotFoundException {
		
		String results = "";
		String expectedResults = "assignments, exam preparation, lecture notes, reading notes, todo.txt, ";
		ShallowFileIterator myIterator = new ShallowFileIterator(myFile);
		
		while(myIterator.hasNext()) {
			results += myIterator.next().getName() + ", ";
		}
		if(results.equals(expectedResults)) return true;
		return false;

	}
	
  /**
   * Tests functionality of DeepFileIterator
   * 
   * @return true for correct functionality, or false for incorrect
   * 
   */
		
	public static boolean testDeepFileIterator(File folder) throws FileNotFoundException {
		
		folder = new File(folder.getPath() + File.separator + "assignments");
		
		String results = "";
		String expectedResults = "P01, PiggyBank.java, P02, CalendarPrinter.java, P03, "
				 + "ElasticBank.java, P04, ExceptionalPiggyBank.java, P05, ExtendedVersion, "
				 + "WinterCarnival.java, WinterCarnival.java, P06, AlphabetTrain.java, ";
		try {
		DeepFileIterator myIterator = new DeepFileIterator(folder);
		while(myIterator.hasNext()) {
			results += (myIterator.next().getName() + ", ");
		}
		
		} catch(Exception e) {
			return false;
		}
		//System.out.println(results);
		if(results.equals(expectedResults)) return true;
		return false;
	}
	
  /**
   * Tests functionality of FilteredFileIterator
   * 
   * @return true for correct functionality, or false for incorrect
   * 
   */
		
	public static boolean testFilteredFileIterator(File folder) throws FileNotFoundException {
		
		String results = "";
		String expectedResults = "PiggyBank.java, CalendarPrinter.java, ElasticBank.java, "
				 + "ExceptionalPiggyBank.java, WinterCarnival.java, WinterCarnival.java, "
				 + "AlphabetTrain.java, codeSamples.java, ";
		try {
		FilteredFileIterator myIterator = new FilteredFileIterator(folder, new String(".java"));
		while(myIterator.hasNext()) {
			results += (myIterator.next().getName() + ", ");
		}
		} catch(Exception e) {
			return false;
		}
		System.out.println(results);
		if(results.equals(expectedResults)) return true;
		return false;
	}
	 	
}
