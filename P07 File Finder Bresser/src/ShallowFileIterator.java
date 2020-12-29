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

import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Arrays;

@SuppressWarnings("unused")

public class ShallowFileIterator implements java.util.Iterator<File> {

	private File[] folderContents;
	private int nextIndex = 0;
	
	// constructor initializes the sorted files array
	public ShallowFileIterator(java.io.File file) throws FileNotFoundException {
		if(!file.exists()) throw new FileNotFoundException("File not found");
		folderContents = file.listFiles();
		Arrays.sort(folderContents);
	}

  /**
   * Checks if there is a next file in the filesystem
   * 
   * @return false if we're at the end, true otherwise
   * 
   */
	
	@Override
	public boolean hasNext() {
		// if we're at the last index, return false
		if(nextIndex == folderContents.length) return false;
		return true;
	}
	
  /**
   * Updates the sequence to the next file in the filesystem
   * 
   * @return the next file in the sequence
   * @throws NoSuchElementException when there is no hasNext
   */
	
	@Override
	public File next() {
		// throw an exception if there is no hasNext
		if(!hasNext()) throw new NoSuchElementException("No next element");
		
		// otherwise, retrieve the next file
		File myFile = folderContents[nextIndex];
		nextIndex++;
		return myFile;
	}
	
}
