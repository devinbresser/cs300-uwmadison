//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P06 Alphabet Train Devin Bresser
// Files:   AlphabetList.java, AlphabetListTester.java, LinkedCart.java
// Course:  CS300 Spring 2020 Mouna Kacem
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

/**
 	* This class implements the LinkedCart functionality
	* as described in the CS300 P06 javadocs.
	*
	*/

public class LinkedCart {

	private final Cart CART; // data field of this linked Cart
	private LinkedCart previous; // reference to previous cart
	private LinkedCart next; // reference to next cart
	
	// LinkedCart constructor when passed just a cart object
	public LinkedCart(Cart cart) {
		this.CART = cart;
		previous = null;
		next = null;
	}
	
	// LinkedCart constructor when passed a previous and next LinkedCart
	public LinkedCart(Cart cart, LinkedCart previous, LinkedCart next) {
		this.CART = cart;
		this.previous = previous;
		this.next = next;
	}
	
	 /**
   * Grabs the cart object that the LinkedCart is holding
   * 
   * @return the cart object that the LinkedCart is holding
   */
	
	public Cart getCart() {
		return this.CART;
	}

	 /**
  * Grabs the previous cart object of this LinkedCart
  * 
  * @return the previous cart object of this LinkedCart
  */
	
	public LinkedCart getPrevious() {
		return this.previous;
	}
	
	 /**
	  * Sets the previous cart object of this LinkedCart
	  * 
	  * @param previous the cart to set as the previous
	  * @return void
	  */
	
	public void setPrevious(LinkedCart previous) {
		this.previous = previous;
	}

	 /**
	  * Grabs the next cart object of this LinkedCart
	  * 
	  * @return the next cart object of this LinkedCart
	  */
	
	public LinkedCart getNext() {
		return this.next;
	}
	
	 /**
	  * Sets the next cart object of this LinkedCart
	  * 
	  * @param next the cart to set as the next
	  * @return void
	  */
	
	public void setNext(LinkedCart next) {
		this.next = next;
	}

}
