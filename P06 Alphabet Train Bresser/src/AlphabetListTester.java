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
 * This class implements unit test methods to check the correctness of LinkedCart and AlphabetList
 * classes defined in the CS300 Spring 2020 - P06 Alphabet Train programming assignment.
 *
 */

public class AlphabetListTester {

  /**
   * This method should test and make use of at least the LinkedCart constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedCart class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedCart() {
  	LinkedCart johnSmith = new LinkedCart(new Cart("G"), new LinkedCart(new Cart("F")), new LinkedCart(new Cart("A")));
  	// test the current, previous and next cargo
  	if(johnSmith.getCart().getCargo().equals("G")  && johnSmith.getPrevious().getCart().getCargo().equals("F") 
  			  && johnSmith.getNext().getCart().getCargo().equals("A")) return true;
    return false;
  }

  /**
   * This method checks for the correctness of both AlphabetList constructors and the instance
   * method isEmpty() when called on an empty alphabet list object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorIsEmpty() {
  	AlphabetList listA = new AlphabetList();
  	AlphabetList listB = new AlphabetList(40);
  	if(listA.capacity()==26 && listB.capacity()==40 && listA.isEmpty() && listB.isEmpty()) return true;
    return false;
  }

  /**
   * This method checks for the correctness of the AlphabetList(int) constructor when passed a
   * negative int value.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorBadInput() {
  	try{
  	AlphabetList badList = new AlphabetList(-31415926);
  	}
  	catch(Exception e) {
  		return true;
  	}
    return false;
  }


  /**
   * This method checks for the correctness of the AlphabetList.add() method when it is passed bad
   * inputs. This method must consider at least the test scenarios provided in the detailed
   * description of these javadocs. (1) Try adding a null to the list; (2) Try adding a cart which
   * carries a non upper-case alphabet letter, for instance "Hello" or "1" or "a". (3) Try adding a
   * duplicate cart to the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAddBadInput() {
  	AlphabetList chuck = new AlphabetList(99999);
  	chuck.add(new Cart("Q"));
  	int count = 0;
  	while(count<3) {
  	try {
  		chuck.add(new Cart(null));
  		chuck.add(new Cart("Q"));
  		chuck.add(new Cart("cs 300"));
  	}
  	catch(Exception e) {
  		count++;
  	}
  	}
  	
  	// only return true if all three adds caused an error
  	if(count == 3) return true;
  	
    return false;
  }

  /**
   * This method checks for the correctness of the AlphabetList.add() considering at least the test
   * scenarios provided in the detailed description of these javadocs. (1) Try adding a cart to an
   * empty list; (2) Try adding a cart which is expected to be added at the head of a non-empty
   * alphabet list; (3) Try adding a cart which is expected to be added at the end of a non-empty
   * alphabet list; (4) Try adding a cart which is expected to be added at the middle of a non-empty
   * alphabet list. For each of those scenarios, make sure that the size of the list is
   * appropriately updated after a call without errors of the add() method, and that the contents of
   * the list is as expected whatever if list is read in the forward or backward directions. You can
   * also check the correctness of AlphabetList.get(int), AlphabetList.indexOf(Cart), and
   * AlphabetList.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAdd() {
  	AlphabetList charles = new AlphabetList(42);
  	charles.add(new Cart("X")); // (1)
  	charles.add(new Cart("A")); // (2)
  	charles.add(new Cart("Z")); // (3)
  	charles.add(new Cart("G")); // (4)
  	if(charles.readForward().contentEquals("AGXZ") && charles.readBackward().contentEquals("ZXGA")) return true;
  	
    return false;
  }

  /**
   * This method checks for the correctness of the AlphabetList.remove() considering at least the
   * test scenarios provided in the detailed description of these javadocs. (1) Try removing a cart
   * from an empty list or pass a negative index to AlphabetList.remove() method; (2) Try removing a
   * cart (at position index 0) from a list which contains only one cart; (3) Try to remove a cart
   * (position index 0) from a list which contains at least 2 carts; (4) Try to remove a cart from
   * the middle of a non-empty list containing at least 3 carts; (5) Try to remove the cart at the
   * end of a list containing at least two carts. For each of those scenarios, make sure that the 
   * size of the list is appropriately updated after a call without errors of the add() method, 
   * and that the contents of the list is as expected whatever if list is read in the forward or 
   * backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListRemove() {
  	
  	AlphabetList chas = new AlphabetList (12345);
  	String myString = "";
  	chas.add(new Cart("A"));
  	chas.remove(0); // (2)
  	myString+=chas.readForward(); //  myString should still be ""
  	chas.clear();
  	
  	chas.add(new Cart("A"));
  	chas.add(new Cart("B"));
  	chas.remove(0); // (3)
  	myString+=chas.readForward(); // myString should now be "B"
  	chas.clear();
  	
  	chas.add(new Cart("A"));
  	chas.add(new Cart("B"));
  	chas.add(new Cart("C"));
  	chas.remove(1); // (4)
  	myString+=chas.readForward(); // myString should now be "BAC"
  	chas.clear();
  	
  	chas.add(new Cart("X"));
  	chas.add(new Cart("Y"));
  	chas.add(new Cart("Z"));
  	chas.remove(2); // (5)
  	myString+=chas.readForward(); // myString should now be "BACXY"
  	chas.clear();
  	
  	try {
  		chas.remove(2);
  	}
  	catch(Exception e) {
  		if(myString.contentEquals("BACXY")) return true;
  	}
  	
    return false;
  }


  /**
   * This method calls all the test methods defined and implemented in your AlphabetListTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
  	if(testLinkedCart() && testAlphabetListConstructorIsEmpty() && testAlphabetListConstructorBadInput()
  			&& testAlphabetListAddBadInput() && testAlphabetListAdd() && testAlphabetListRemove()) {
  		System.out.println("Choo choo");
  		return true;
  	}
    return false;
  }

  /**
   * Driver method defined in this AlphabetListTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
  	
  	System.out.println("testLinkedCart(): " + testLinkedCart());
  	System.out.println("testAlphabetListConstructorIsEmpty(): " + testAlphabetListConstructorIsEmpty());
  	System.out.println("testAlphabetListConstructorBadInput(): " + testAlphabetListConstructorBadInput());
  	System.out.println("testAlphabetListAddBadInput(): " + testAlphabetListAddBadInput());
  	System.out.println("testAlphabetListAdd(): " + testAlphabetListAdd());
  	System.out.println("testAlphabetListRemove(): " + testAlphabetListRemove());
  	System.out.println("runAllTests(): " + runAllTests());
  	
  	AlphabetList chuck = new AlphabetList();
  	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  	for(int i=25; i>=0; i--) {
  		chuck.add(new Cart(alphabet.substring(i, i+1).toUpperCase()));
  	}
  	System.out.println(chuck.readBackward());
  
  }
}
