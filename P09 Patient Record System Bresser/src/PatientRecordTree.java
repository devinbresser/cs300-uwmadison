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
 * This class implements a binary search tree (BST) which stores a set of patient records.
 * The left subtree contains the patient records of all the patients who are older than the
 * patient who's PatientRecord is stored at a parent node.
 * The right subtree contains the patient records of all the patients who are younger than the 
 * patient who's PatientRecord is stored at a parent node.
 *
 */

public class PatientRecordTree {
  private PatientRecordNode root; // root of this binary search tree
  private int size; // total number of patient records stored in this tree.

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this PatientRecordTree is empty, false otherwise
   */
  
  public boolean isEmpty() {
    if(size==0) return true;
    return false;
  }

  /**
   * Returns the number of patient records stored in this BST.
   * 
   * @return the size of this PatientRecordTree
   */
  
  public int size() {
    return this.size;
  }

  /**
   * Recursive helper method to add a new PatientRecord to a PatientRecordTree rooted at current.
   * 
   * @param current The "root" of the subtree we are inserting newRecord into.
   * @param newRecord The PatientRecord to be added to a BST rooted at current.
   * @return true if the newRecord was successfully added to this PatientRecordTree, false otherwise
   */
  
  public static boolean addPatientRecordHelper(PatientRecord newRecord, PatientRecordNode current) {
  	
    //create a new node, it will be added somewhere. Future code will determine where...
		PatientRecordNode nodeToBeAdded = new PatientRecordNode(newRecord);
  	
  	// if the comparison statement is negative, our new patient is older and should be added to the left
  	if(newRecord.compareTo(current.getPatientRecord()) < 0) {
  		
  		//System.out.println("This patient, " + newRecord.getName() + ", is older than the previous patient, " + current.getPatientRecord().getName() + ". Thus, it shall be added to the left of the tree.");//testcode
  		if(current.getLeftChild() == null) {
  			//System.out.println("The left child is currently null. Setting and returning...");//testcode
  			current.setLeftChild(nodeToBeAdded);
  			return true;
  		}
  		
  		// if current's left child is not null, we must use recursion
  		// to run a addPatientRecordHelper on current's left child
  		current = current.getLeftChild();
  		addPatientRecordHelper(newRecord, current);
  		return true;
  		
  	}
  	
  	// if the comparison statement is positive, our new patient is younger and should be added to the right
  	if(newRecord.compareTo(current.getPatientRecord()) > 0) {
  		
  		//System.out.println("This patient, " + newRecord.getName() + ", is younger than the previous patient, " + current.getPatientRecord().getName() + ". Thus, it shall be added to the right of the tree.");//testcode
  		if(current.getRightChild() == null) {
  			//System.out.println("The right child is currently null. Setting and returning...");//testcode
  			current.setRightChild(nodeToBeAdded);
  			return true;
  		}
  		
  		// if current's right child is not null, we must use recursion
  		// to run a addPatientRecordHelper on current's left child
  		current = current.getRightChild();
  		addPatientRecordHelper(newRecord, current);
  		return true;
  		
  	}
  	
  	return false;
  	
  }

  /**
   * Adds a new PatientRecord to this PatientRecordTree
   * 
   * @param newRecord a new PatientRecord to add to this BST.
   * @return true if the newRecord was successfully added to this BST, and returns false if there is
   *         a match with this PatientRecord already already stored in this BST.
   */
  
  public boolean addPatientRecord(PatientRecord newRecord) {
  	
  	//first check to see if the lookup method returns something not null
  	if(lookupHelper(newRecord, root) != null) {
  		System.out.println("Error: cannot add an already existing DOB. Sorry");
  		return false;
  	}
  	
    if (isEmpty()) { // Add newRecord to an empty PatientRecordTree
      this.root = new PatientRecordNode(newRecord);
      this.size++;
      return true;
      //System.out.println("The tree was empty, so we added the root: " + root.getPatientRecord().getName());//testcode
      
    } else { // Add newRecord to an non-empty PatientRecordTree
    	//System.out.println("Tree not empty. Adding in accordance with addPatientRecordHelper()");//testcode
      addPatientRecordHelper(newRecord, root);
      this.size++;
      return true;
    }
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a PatientRecordTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current PatientRecordNode within this BST.
   * @return a String representation of all the PatientRecords stored in the sub-tree
   *         PatientRecordTree rooted at current in increasing order with respect to the patients
   *         dates of birth. Returns an empty String "" if current is null.
   */
  
  public static String toStringHelper(PatientRecordNode current) {
  	String myString = "";
  	if(current == null) return "";
  	// recursively traverses through the tree "in order" by first visiting its left child, then a node's data, then its right child
  	myString += toStringHelper(current.getLeftChild());
  	myString += (current.getPatientRecord().getName() + "(" + current.getPatientRecord().getStringDateOfBirth() + ")" + "\n");
  	myString += toStringHelper(current.getRightChild());
    return myString;
  }

  /**
   * Returns a String representation of all the PatientRecords stored within this BST in the
   * increasing order, separated by a newline "\n". For instance: 
   * "Sarah(1/2/1935)" + "\n" +
   * "George(5/27/1943)" + "\n" + 
   * "Adam(8/12/1972)" + "\n" + 
   * "Norah(11/23/1985)" + "\n" +
   * "William(6/4/1998)" + "\n" + 
   * "Nancy(9/12/2003)" + "\n" + 
   * "Sam(4/20/2019)" + "\n"
   * 
   * @return a String representation of all the PatientRecords stored within this BST sorted in an
   *         increasing order with respect to the dates of birth of the patients (i.e. from the
   *         oldest patient to the youngest patient). Returns an empty string "" if this BST is empty.
   */
  
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Search for a patient record (PatientRecord) given the date of birth as lookup key.
   * 
   * @param date a String representation of the date of birth of a patient in the format mm/dd/yyyy
   * @return the PatientRecord of the patient born on date.
   * @throws a NoSuchElementException with a descriptive error message if there is no PatientRecord
   *         found in this BST having the provided date of birth
   */
  
  public PatientRecord lookup(String date) {
  	// base case 1: tree is empty
  	if(size == 0) throw new NoSuchElementException("Error: data not found. Tree empty.");
  	// base case 2: data needed is in the root
  	if(new PatientRecord("",date).compareTo(root.getPatientRecord()) == 0) {
  		return root.getPatientRecord();
  	}
  	// otherwise, do a recursive lookup on the appropriate subtree
    PatientRecord findRecord = new PatientRecord("", date);
    PatientRecord returned = lookupHelper(findRecord, root);
    if(returned==null) throw new NoSuchElementException("Error: data not found after search.");
    return returned;
  }
  
  /**
   * Recursive helper method to lookup a PatientRecord given a reference PatientRecord with the same
   * date of birth in the subtree rooted at current
   * 
   * @param findRecord a reference to a PatientRecord target we are lookup for a match in the BST
   *        rooted at current.
   * @param current "root" of the subtree we are looking for a match to findRecord within it.
   * @return reference to the PatientRecord stored stored in this BST which matches findRecord.
   * @throws NoSuchElementException with a descriptive error message if there is no patient record
   *         whose date of birth matches date, stored in this BST.
   */
  
  private PatientRecord lookupHelper(PatientRecord findRecord, PatientRecordNode current) {
  	
  	// terminate the method if we hit the end of the tree
  	if(current == null) return null;
  	// if the record is found while traversing the tree, immediately return that instance of "current"
    if(findRecord.compareTo(current.getPatientRecord()) == 0) {
    	//System.out.println("Record found!");
    	return current.getPatientRecord();
    }
    
    // if the patient is older than the root, search the left tree
    if(findRecord.compareTo(current.getPatientRecord()) < 0) {
    	return lookupHelper(findRecord, current.getLeftChild());
    }
    
    // if the patient is younger than the root, search the right tree
    else{//(findRecord.compareTo(current.getPatientRecord()) > 0) {
    	return lookupHelper(findRecord, current.getRightChild());
    }   
  }
  
  /**
   * Computes and returns the height of this BST, counting the number of nodes (PatientRecordNodes)
   * from root to the deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  
  public int height() {
  	
  	// base case: root is null means height is 0
  	if(root == null) return 0;
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current PatientRecordNode within a PatientRecordTree
   * @return height of the subtree rooted at current, counting the number of PatientRecordNodes
   */
  
  public static int heightHelper(PatientRecordNode current) {
  	if(current == null) return 0;
    int leftHeight = heightHelper(current.getLeftChild());
    int rightHeight = heightHelper(current.getRightChild());
    if(leftHeight>rightHeight) return leftHeight+1;
    else return rightHeight+1;
  }


  /**
   * Returns the PatientRecord of the youngest patient in this BST.
   * 
   * @return the PatientRecord of the youngest patient in this BST and null if this tree is empty.
   */
  
  public PatientRecord getRecordOfYoungestPatient() {
  	if(isEmpty()) return null;
    return lookup(toString().substring(toString().lastIndexOf("(")+1, toString().lastIndexOf(")")));
  }

  /**
   * Returns the PatientRecord of the oldest patient in this BST.
   * 
   * @return the PatientRecord of the oldest patient in this BST, and null if this tree is empty.
   */
  
  public PatientRecord getRecordOfOldestPatient() {
  	if(isEmpty()) return null;
  	return lookup(toString().substring(toString().indexOf("(")+1, toString().indexOf(")")));
  }

}
