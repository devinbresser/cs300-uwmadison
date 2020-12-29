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
	* This class implements the AlphabetList functionality
	* as described in the CS300 P06 javadocs.
	*
	*/

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

public class AlphabetList implements SortedListADT<Cart> {

	private static final Cart MIN_CART = new Cart("A"); // smallest addable cart
	private static final Cart MAX_CART = new Cart("Z"); // largest addable cart
	private LinkedCart head; // head of this doubly linked list
	private LinkedCart tail; // tail of this doubly linked list
	private int size; // size of the list
	private int capacity; // capacity of the list
	
	// AlphabetList constructor when passed a capacity
	public AlphabetList(int capacity) {
		if(capacity<=0) throw new IllegalArgumentException("The capacity of this list must be a "
				+ "non-zero positive integer");
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.capacity = capacity;
	}
	
	// AlphabetList constructor when passed nothing
	public AlphabetList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.capacity = 26;
	}
	
  /**
   * Checks if the train is empty
   * 
   * @return true when the train is empty, or false otherwise
   * @see SortedListADT.isEmpty()
   */
	
	@Override
	public boolean isEmpty() {
		if(size==0) return true;
		return false;
	}
	
  /**
   * Finds the current size of the train
   * 
   * @return the current size of the train
   * @see SortedListADT.size()
   */
	
	@Override
	public int size() {
		return this.size;
	}
	
  /**
   * Finds the capacity of the train
   * 
   * @return the capacity of the train
   */
	
	public int capacity() {
		return this.capacity;
	}
	
  /**
   * Adds a specified cart to the train.
   * Only works when the cart contains a singular upper-case letter
   * and when the train isn't full.
   * 
   * @param newCart the new cart object to add
   * @return void
   * @throws IllegalStateException when the train is full
   * @throws IllegalArgumentException when the format of the cart's cargo is 
   * 		not a singular upper case letter, or if we try to add an existing cart again
   * @see SortedListADT.add()
   */
	
	@Override
	public void add(Cart newCart) {
		
		LinkedCart currentCart = head;
		String name = newCart.getCargo(); // for error handling purposes
		
		// error if the list is full
		if(size == capacity) throw new java.lang.IllegalStateException(
				"This list is full. Cannot add another cart.");
 
		// error if the inputted character is not valid
		if(name.length()!= 1 || name.equals(name.toLowerCase()) 
				|| newCart.compareTo(MIN_CART)<0 || newCart.compareTo(MAX_CART)>0) {
			throw new java.lang.IllegalArgumentException
					("Can only add carts carrying one upper-case alphabetic letter in the range A .. Z.");
		}
		
		// error if we're trying to add a cart with the same letter that already exists
		for(int i=0; i<size; i++) {
			if(newCart.getCargo().contentEquals(currentCart.getCart().getCargo())){
				throw new java.lang.IllegalArgumentException("Cannot duplicate letters or carts in this list.");
			}
			currentCart = currentCart.getNext();
		}
		
		
		// if it's the first cart we're adding, it's the head and tail
		if(size==0) {
			this.head = new LinkedCart(newCart);
			this.tail = this.head;
			size++;
			return;
		}
		
		// if it's the second cart:
		// our cart becomes the next of the head, and also the tail
		if(size==1) {
			
			// if this cart goes after the head, make it the new tail
			if(newCart.compareTo(head.getCart())>0) {			
				tail = new LinkedCart(newCart, head, null);
				head.setNext(tail);
				size++;
				return;
				
			}
			
			// otherwise flip the head and tail basically
			head = new LinkedCart(newCart, null, tail);
			tail.setPrevious(head);
			size++;
			return;
		}
		
		currentCart = head;
		// this loop finds the right place for the new cart
		while(newCart.compareTo(currentCart.getCart())>0 && currentCart.getNext() != null){
			currentCart = currentCart.getNext();
		}
		
		// if currentCart points to the head, make newCart the head
		// currentCart points to the OLD head
		if(currentCart.equals(head) && newCart.compareTo(currentCart.getCart())<0) {
			this.head = new LinkedCart(newCart, null, currentCart);
			currentCart.setPrevious(head);
			size++;
			return;
		}
			
		// if currentCart points to the tail, make newCart the tail
		// currentCart points to the OLD tail
		if(currentCart.equals(tail) && newCart.compareTo(currentCart.getCart())>0) {	
			this.tail = new LinkedCart(newCart, currentCart, null);
			currentCart.setNext(tail);
			size++;
			return;
		}
			
		// if it isn't the head or the tail just place it BEFORE currentCart
		LinkedCart johnSmith = new LinkedCart(newCart, currentCart.getPrevious(), currentCart);
		currentCart.getPrevious().setNext(johnSmith);
		currentCart.setPrevious(johnSmith);
		size++;
		return;
		
	}
	
  /**
   * Finds the index of a specified cart with cargo
   * 
   * @param findCart the cart object to look for
   * @return the index of the cart if it's found, or -1 if it is not
   * @see SortedListADT.indexOf()
   */
	
	@Override
	public int indexOf(Cart findCart) {
		LinkedCart currentCart = head;
			for(int i=0; i<size; i++) {
				if(findCart.getCargo().contentEquals(currentCart.getCart().getCargo())) {
					return i;
				}
				currentCart = currentCart.getNext();
			}
		return -1;
	}
	
  /**
   * Clears the train
   * 
   * @return void
   * @see SortedListADT.clear()
   */
	
	@Override
	public void clear() {
		// nuke the train
		head = null;
		tail = null;
		size = 0;	
	}
	
  /**
   * Gets the cart at a specified index without removing it
   * 
   * @param index the index we're looking for
   * @return the cart at that index
   * @see SortedListADT.get()
   */
	
	@Override
	public Cart get(int index) {
		LinkedCart currentCart = head;
			for(int i=0; i<index; i++) {
				currentCart = currentCart.getNext();
			}
		return currentCart.getCart();
	}

  /**
   * Removes a specified cart (by index) from the train
   * 
   * @param index the index at which we will remove the cart
   * @return the cart we just removed
   * @throws IndexOutOfBoundsException when the index passed is less than 0 or larger than the size
   * @see SortedListADT.remove()
   */
	
	@Override
	public Cart remove(int index) {
		
	 if(index<0 || index >= size) throw new java.lang.IndexOutOfBoundsException("Invalid index.");
	 
		LinkedCart currentCart = head;
		// find the cart at the index
		for(int i=0; i<index; i++) {
			currentCart = currentCart.getNext();
		}
		
		// if there's only one cart in the list, it must be removed
		if(size==1) {
			head = null;
			tail = null;
			size = 0;
			return currentCart.getCart();
		}
		
		if(currentCart.equals(head)) {
			head = currentCart.getNext();
			head.setPrevious(null);
			size--;
			return currentCart.getCart();
		}
		
		if(currentCart.equals(tail)) {
			tail = currentCart.getPrevious();
			tail.setNext(null);
			size--;
			return currentCart.getCart();
		}
		
		// remove the cart in question from existence
		currentCart.getPrevious().setNext(currentCart.getNext());
		currentCart.getNext().setPrevious(currentCart.getPrevious());
		size--;
		return currentCart.getCart();
		
	}
	
  /**
   * Prints out a string that tells us the contents of the cart 
   * (implementation provided by CS300 instructors)
   * 
   * @return the string with all the cart's information in it
   *
   */
	
	public java.lang.String toString(){
		
		String string = "This list contains " + size + " cart(s)";
		if (size == 0) {
			return string;
		}
		string += ": [ ";
		LinkedCart currentCart = head;
		while (currentCart != null) {
			string += currentCart.getCart().toString() + " ";
			currentCart = currentCart.getNext();
		}
		string += "]";
		return string;

	}
	
  /**
   * Reads the contents of the train forwards
   * 
   * @return the string which contains all of the carts' data concatenated
   */
	
	public java.lang.String readForward(){
		String myString = "";
		LinkedCart currentCart = head;
		// find the cart at the index
		for(int i=0; i<size; i++) {
			myString+=currentCart.getCart().getCargo();
			currentCart = currentCart.getNext();
		}
		return myString;
	}

	 /**
   * Reads the contents of the train backwards
   * 
   * @return the string which contains all of the carts' data concatenated backwards
   */
	
	public java.lang.String readBackward(){
		String myString = "";
		LinkedCart currentCart = tail;
		// find the cart at the index
		for(int i=size-1; i>=0; i--) {
			myString+=currentCart.getCart().getCargo();
			currentCart = currentCart.getPrevious();
		}
		return myString;
	}
}
