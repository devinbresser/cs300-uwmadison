//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P09 Patient Record System - Devin Bresser
// Files:   PatientRecordTree.java, PatientRecordTreeTester.java
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
// Persons:         danielbritten, andrew1234 (authors of geeksforgeeks article)
// Online Sources:  http://pages.cs.wisc.edu/~cs300/readings/Trees/
//									http://pages.cs.wisc.edu/~cs300/readings/Binary-Search-Trees/
//									https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
//	  							https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2020/chapter/10/section/6
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * PatientRecordTree.
 *
 */

public class PatientRecordTreeTester {

  /**
   * Checks the correctness of the implementation of both addPatientRecord() and toString() methods
   * implemented in the PatientRecordTree class. This unit test considers at least the following
   * scenarios. (1) Create a new empty PatientRecordTree, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one patient record and
   * then check that the addPatientRecord() method call returns true, the tree is not empty, its
   * size is 1, and the .toString() called on the tree returns the expected output. (3) Try adding
   * another patientRecord which is older that the one at the root, (4) Try adding a third patient
   * Record which is younger than the one at the root, (5) Try adding at least two further patient
   * records such that one must be added at the left subtree, and the other at the right subtree.
   * For all the above scenarios, and more, double check each time that size() method returns the
   * expected value, the add method call returns true, and that the .toString() method returns the
   * expected string representation of the contents of the binary search tree in an ascendant order
   * from the oldest patient to the youngest one. (6) Try adding a patient whose date of birth was
   * used as a key for a patient record already stored in the tree. Make sure that the
   * addPatientRecord() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordToStringSize() {
  	//(1)
  	boolean success = false;
  	PatientRecordTree myTree = new PatientRecordTree();
  	if(myTree.size() != 0) return false;
  	if(!myTree.toString().equals("")) return false;
  	//(2)
  	success = myTree.addPatientRecord(new PatientRecord("John Smith", "01/01/1980"));
  	if(myTree.size() != 1) return false;
  	if(!myTree.toString().equals("John Smith(1/1/1980)" + "\n")) return false;
  	//(3)
  	success = myTree.addPatientRecord(new PatientRecord("Old Man Smith", "01/01/1950"));
  	if(!success) return false;
  	if(myTree.size() != 2) return false;
  	System.out.println(myTree.toString());
  	if(!myTree.toString().equals("Old Man Smith(1/1/1950)" + "\n" + 
  				"John Smith(1/1/1980)" + "\n")) return false;
  	//(4)
  	success = myTree.addPatientRecord(new PatientRecord("Kid Smith", "01/01/2000"));
  	if(!success) return false;
  	if(myTree.size() != 3) return false;
  	if(!myTree.toString().equals("Old Man Smith(1/1/1950)" + "\n" + 
				"John Smith(1/1/1980)" + "\n" + "Kid Smith(1/1/2000)" + "\n")) return false;
  	//(5)
  	success = myTree.addPatientRecord(new PatientRecord("Ancient Guardian Smith", "01/01/1243"));
  	if(!success) return false;
  	if(myTree.size() != 4) return false;
  	if(!myTree.toString().equals("Ancient Guardian Smith(1/1/1243)" + 
  			"\n" + "Old Man Smith(1/1/1950)" + "\n" + 
				"John Smith(1/1/1980)" + "\n" + "Kid Smith(1/1/2000)" + "\n")) return false;
  	success = myTree.addPatientRecord(new PatientRecord("Time Traveller Smith", "01/01/4559"));
  	if(!success) return false;
  	if(myTree.size() != 5) return false;	
  	if(!myTree.toString().equals("Ancient Guardian Smith(1/1/1243)" + 
  			"\n" + "Old Man Smith(1/1/1950)" + "\n" + 
				"John Smith(1/1/1980)" + "\n" + "Kid Smith(1/1/2000)" + "\n"
  			+ "Time Traveller Smith(1/1/4559)" + "\n")) return false;
  	//(6)
  	success = myTree.addPatientRecord(new PatientRecord("Clone of Time Traveller Smith", "01/01/4559"));
  	if(success) return false;
  	if(myTree.size() != 5) return false;
  	
    return true;
    
  }

  /**
   * This method checks mainly for the correctness of the PatientRecordTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new PatientRecordTree. Then, check
   * that calling the lookup() method with any valid date must throw a NoSuchElementException. (2)
   * Consider a PatientRecordTree of height 3 which consists of at least 5 PatientRecordNodes. Then,
   * try to call lookup() method to search for the patient record at the root of the tree, then a
   * patient records at the right and left subtrees at different levels. Make sure that the lookup()
   * method returns the expected output for every method call. (3) Consider calling .lookup() method
   * on a non-empty PatientRecordTree with a date of birth not stored in the tree, and ensure that
   * the method call throws a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordAndLookup() {
  	//(1)
  	PatientRecordTree myTree = new PatientRecordTree();
  	try {
  		myTree.lookup("01/01/2000");
  	} catch(NoSuchElementException e) {
  		System.out.println("Caught the appropriate exception.");
  	}
  	//(2)
  	myTree.addPatientRecord(new PatientRecord("John Smith", "01/01/1980"));
  	myTree.addPatientRecord(new PatientRecord("Old Man Smith", "01/01/1950"));
  	myTree.addPatientRecord(new PatientRecord("Kid Smith", "01/01/2000"));
  	myTree.addPatientRecord(new PatientRecord("Ancient Guardian Smith", "01/01/1243"));
  	myTree.addPatientRecord(new PatientRecord("Time Traveller Smith", "01/01/4559"));
  	
  	if(!myTree.lookup("01/01/1980").equals(new PatientRecord("John Smith", "01/01/1980"))) return false;
  	if(!myTree.lookup("01/01/1950").equals(new PatientRecord("Old Man Smith", "01/01/1950"))) return false;
  	if(!myTree.lookup("01/01/2000").equals(new PatientRecord("Kid Smith", "01/01/2000"))) return false;
  	if(!myTree.lookup("01/01/1243").equals(new PatientRecord("Ancient Guardian Smith", "01/01/1243"))) return false;
  	if(!myTree.lookup("01/01/4559").equals(new PatientRecord("Time Traveller Smith", "01/01/4559"))) return false;
  	
  	//(3)
  	try {
  		myTree.lookup("99/99/9999");
  	} catch(NoSuchElementException e) {
  		System.out.println("Caught the appropriate exception again.");
  	}
    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.height() method. This test must consider
   * several scenarios such as, (1) ensures that the height of an empty patient record tree is zero.
   * (2) ensures that the height of a tree which consists of only one node is 1. (3) ensures that
   * the height of a PatientRecordTree with the following structure for instance, is 4.
   *       (*)
   *     /    \
   *  (*)      (*)
   *    \     /  \
   *    (*) (*)  (*)
   *             /
   *           (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
  	PatientRecordTree myTree = new PatientRecordTree();
  	//(1)
  	if(myTree.height()!=0) return false;
  	//(2)
  	myTree.addPatientRecord(new PatientRecord("Old Man Smith", "01/01/1950"));
  	if(myTree.height()!=1) return false;
  	//(3)
  	myTree.addPatientRecord(new PatientRecord("Older Man Smith", "01/01/1940"));
  	if(myTree.height()!=2) return false;  	
  	
  	myTree.addPatientRecord(new PatientRecord("Olderer Man Smith", "01/01/1930"));
  	if(myTree.height()!=3) return false;
  	
  	myTree.addPatientRecord(new PatientRecord("Oldest Man Smith", "01/01/1910"));
  	if(myTree.height()!=4) return false;
  	
  	return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfYoungestPatient() method.
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfYoungestPatient() {
  	PatientRecordTree myTree = new PatientRecordTree();
  	myTree.addPatientRecord(new PatientRecord("Old Man Smith", "01/01/1950"));
  	myTree.addPatientRecord(new PatientRecord("Olderer Man Smith", "01/01/1930"));
  	myTree.addPatientRecord(new PatientRecord("Oldererer Man Smith", "01/01/1910"));
  	if(!myTree.getRecordOfYoungestPatient().getName().equals("Old Man Smith")) return false;
    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfOldestPatient() method.
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfOldestPatient() {
  	PatientRecordTree myTree = new PatientRecordTree();
  	myTree.addPatientRecord(new PatientRecord("Old Man Smith", "01/01/1950"));
  	myTree.addPatientRecord(new PatientRecord("Olderer Man Smith", "01/01/1930"));
  	myTree.addPatientRecord(new PatientRecord("Oldererer Man Smith", "01/01/1910"));
  	if(!myTree.getRecordOfOldestPatient().getName().equals("Oldererer Man Smith")) return false;
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
  	
  	System.out.println("testAddPatientRecordToStringSize(): " + testAddPatientRecordToStringSize());
  	System.out.println("testAddPatientRecordAndLookup(): " + testAddPatientRecordAndLookup());
  	System.out.println("testHeight(): " + testHeight());
  	System.out.println("testGetRecordOfYoungestPatient(): " + testGetRecordOfYoungestPatient());
  	System.out.println("testGetRecordOfOldestPatient(): " + testGetRecordOfOldestPatient());
  	
  }

}
