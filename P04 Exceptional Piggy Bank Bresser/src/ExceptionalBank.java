import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.io.*;

/**
 * This class implements an expanded version of elastic bank application
 * 
 */
public class ExceptionalBank {
  private Coin[] coins; // array which stores all coins held in this elastic bank
  private int size; // size of this elastic bank
  private int expansionsLeft; // number of expansions left for this elastic bank
  private static Random rand = new Random(100); // random integers generator

  /**
   * Creates a new elastic bank object with a given initial capacity
   * 
   * @param initialCapacity initial capacity of this elastic bank
   * @throws IllegalArgumentException when initialCapacity is negative or zero.
   */
  public ExceptionalBank(int initialCapacity) throws IllegalArgumentException {
	if(initialCapacity <= 0) throw new IllegalArgumentException
		("WARNING! The initial capacity of a bank must be a non-zero positive integer.");
    coins = new Coin[initialCapacity];
    this.expansionsLeft = 2;
  }

  /**
   * Creates a new elastic bank object with an initial capacity equal to 10
   */
  public ExceptionalBank() {
    this(10);
  }

  /**
   * Returns the capacity of this elastic bank
   * 
   * @return the capacity of this elastic bank
   */
  public int capacity() {
    return coins.length;
  }

  /**
   * Returns the expansions left for this elastic bank
   * 
   * @return the expansions left for this elastic bank
   */
  public int getExpansions() {
    return this.expansionsLeft;
  }

  /**
   * Returns the number of coins held in this elastic bank
   * 
   * @return the size of this elastic bank
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Returns the value in cents of coins held in this elastic bank
   * 
   * @return the balance of this elastic bank
   */
  public int getBalance() {
    int balance = 0;
    // add the value of each coin held in this bank to balance, then return it
    for (int i = 0; i < size; i++) {
      balance += coins[i].value();
    }
    return balance;
  }

  /**
   * Returns the number of coins with a specific coinName held in this bank. The coin name
   * comparison is case insensitive
   * 
   * @param coinName name of a coin
   * @return the count of coins having the provided coinName, held in this bank
   */
  public int getSpecificCoinCount(String coinName) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (coins[i].name().equalsIgnoreCase(coinName))
        count++;
    }
    return count;
  }

  /**
   * Returns a string representation of all the coins held in this elastic bank. Each coin is
   * represented by the pair "(name, value)", and the string representation should contain all of
   * these pairs in one space-separated line. For example: "(PENNY, 1) (QUARTER, 25) (PENNY, 1)
   * (DIME, 10) (NICKEL, 5)"
   * 
   * @return a String representation of the contents of the bank.
   */
  public String getCoins() {
    String contents = "";
    // traverse the coins oversize array and add each coin's string representation to the string to
    // be returned
    for (int i = 0; i < size; i++) {
      contents += "(" + coins[i].name() + ", " + coins[i].value() + ")";
      if (i < size - 1)
        contents += " ";
    }
    return contents;
  }
  
  



  /**
   * Returns a summary of this bank contents
   * 
   * @return an empty string if this bank is empty, and a string representation of the summary of
   *         this bank otherwise. The summary of the bank is a set of lines. Each line is formatted
   *         as follows "coin_name:coin_count"
   */
  public String getSummary() {
    String summary = "";
    Coin[] values = Coin.values();
    // traverse this bank contents and update its summary
    for (int i = 0; i < values.length; i++) {
      String name = values[i].name();
      int count = getSpecificCoinCount(name);
      if (count != 0) {
        summary += name + ":" + count + "\n";
      }
    }
    return summary.trim(); // remove whitespace (spaces, new lines etc.) from the beginning and end
                           // of summary and return the resulting string

  }

  /**
   * Removes and returns a coin at a random position from this elastic bank
   * 
   * @return the removed coin or null if this bank is empty
   * @throws NoSuchElementException when the bank is empty, can't remove a coin
   */
  public Coin removeCoin() throws NoSuchElementException {
	  
    if(size == 0)
    throw new NoSuchElementException("WARNING! This bank is empty. Unable to remove a coin.");
    
    int randPosition = rand.nextInt(size); // get a random position from 0 .. size-1
    Coin removedCoin = coins[randPosition]; // store the coin to be removed
    // The order of the coins within this bank (a piggy bank) is not important
    // So, move the coin at the end of the coins array to the random position
    // and set that last element to null.
    coins[randPosition] = coins[size - 1];
    coins[size - 1] = null;
    size--; // update size
    return removedCoin;
  }

  /**
   * Removes all the coins from this elastic bank
   */
  public void empty() {
    // set all the non-null references within the coins array to null
    for (int i = 0; i < size; i++) {
      coins[i] = null;
    }
    // set the size of this bank to 0
    size = 0;
  }

  /**
   * adds a Coin to the bank and adjusts the capacity of coins if necessary and possible
   * 
   * @param c coin to be added to this elastic bank
   * @throws IllegalArgumentException when Coin c is a null reference
   */
  public void addCoin(Coin c) throws IllegalArgumentException{	  
		
    if(c==null) throw new IllegalArgumentException("WARNING! You cannot add a null reference to this bank.");
	  
    // check if this bank is full
    if (size == coins.length) {
	  // check whether there are expansions left
	  if (this.expansionsLeft > 0) {
		// expand the capacity of this elastic bank by 10
		coins = Arrays.copyOf(coins, coins.length + 10);
		this.expansionsLeft--;
	} else { // no expansions left
	// empty this elastic bank
	empty();
	}
  }
  
  // add c at the end of this bank
  coins[size] = c;
  size++;

  }
 
}
