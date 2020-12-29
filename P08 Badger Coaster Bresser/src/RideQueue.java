//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P08 Badger Coaster - Devin Bresser
// Files:   BoardingGroup.java, RideQueue.java, ThemeParkApp.java
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


public class RideQueue implements QueueADT<BoardingGroup> {
	
	// CHANGE THESE BACK TO PRIVATE testcode **
	private BGNode front;
	private BGNode back;
	private int capacity; // max capacity of queue
	private int numOfPeople;
	private int numOfGroups;
	
	public RideQueue(int capacity) {
		this.capacity = capacity;
		front = null;
		back = null;
		numOfPeople = 0;
		numOfGroups = 0;
	}

	/**
	 * Determines whether the queue is empty
	 * 
	 * @return true when the queue is empty, and false otherwise
	 */
	
	public boolean isEmpty() {
		if(numOfPeople == 0 && numOfGroups == 0) return true;
		return false;
	}

	/**
	 * Returns the size of the queue in BGNodes
	 * 
	 * @return the current number of BGNodes in the queue
	 */
	
	@Override
	public int size() {
		return numOfGroups;
	}

	/**
	 * Evacuates the queue
	 * 
	 * @return void
	 */
	
	@Override
	public void clear() {
		this.numOfPeople = 0;
		this.numOfGroups = 0;
		this.front = null;
		this.back = null;
		
	}

	/**
	 * Determines whether the queue is empty
	 * 
	 * @return true when the queue is empty, and false otherwise
	 */
	
	@Override
	public BoardingGroup peek() {
		if(isEmpty()) throw new java.util.NoSuchElementException("Queue is empty. Cannot peek");
		return front.getGroup();
	}
	
	/**
	 * Adds a group to the back of the queue.
	 * 
	 * @param newGroup the group we're going to add
	 * @return void
	 */
	
	@Override
	public void enqueue(BoardingGroup newGroup) {
		if(newGroup.getPop()>capacity-numOfPeople) {
			throw new java.lang.IllegalStateException("Group too big");
		}
		

		
		// when the first group is added, it's both the front and the back
		if(numOfGroups==0) {
			this.front = new BGNode(newGroup);
			this.back = front;
			numOfGroups++;
			numOfPeople += back.getGroup().getPop();
			return;
		}
		
		// if we're dealing with a VIP group we move it to the front instead
		if(newGroup.isVipStatus()) {
			BGNode myNode = new BGNode(newGroup,front);		
			front = myNode;
			numOfGroups++;
			numOfPeople += front.getGroup().getPop();
			return;
		}
		// non VIP groups after the first must be added exclusively to the back
		// the previous back's next is set to the new BGNode (to be the new back)
		BGNode myNode = new BGNode(newGroup);		
		back.setNext(myNode);
		back = myNode;
		numOfGroups++;
		numOfPeople += back.getGroup().getPop();
		return;
		
	}
	
	/**
	 * Removes and returns the group at the front of the queue
	 * 
	 * @return the removed group formerly at the front of the queue
	 */
	
	@Override
	public BoardingGroup dequeue() {
		if(numOfGroups == 1) {
			BGNode myNode = front; //collect the current front
			clear();
			return myNode.getGroup();
		}
		BGNode myNode = front; //collect the current front
		front = myNode.getNext();
		numOfGroups--;
		return myNode.getGroup();
	}
	
	//Returns a string representation of this RideQueue.
	public String toString() {
		String s = "Number of People in Queue: " + numOfPeople + "\n";
		s += "Number of Groups in Queue: " + numOfGroups + "\n";
		s += "Group Names in Queue: ";
		BGNode current = front;
		while (current != null) {
			String groupName = current.getGroup().getName();
					s += groupName + " ";
			current = current.getNext();
		}
		return s;
	}
	
}
