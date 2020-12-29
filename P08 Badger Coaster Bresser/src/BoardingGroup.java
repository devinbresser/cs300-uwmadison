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

public class BoardingGroup {

	private String name;
	private int population = 0;
	private boolean vipStatus;
	public BoardingGroup(String name, int population) {
		this.name = name;
		this.population = population;
		vipStatus = false;
	}
	
	/**
	 * Returns the name of the group
	 * 
	 * @return the name of the group
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the current population of the group
	 * 
	 * @return the population of the group
	 */
	
	public int getPop() {
		return this.population;
	}

	/**
	 * Returns the VIP status of the group
	 * 
	 * @return the group's VIP status (true or false)
	 */
	
	public boolean isVipStatus() {
		return vipStatus;
	}

	/**
	 * Marks the group as VIP
	 * 
	 * @return void
	 */
	
	public void setVipStatus() {
		this.vipStatus = true;
	}
	
}
