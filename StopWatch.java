import java.text.DecimalFormat;

import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**********************************************************************
  Creates the methods for our StopWatch.

  @author Jarod Collier and Ben Burger
  @version 5/11/2018
 *********************************************************************/

public class StopWatch {

	// Use this space to create instance variables that will keep track of each timer's values

	/******************************************************************
	  Default constructor for objects in StopWatch class 
	  that sets the StopWatch to zero
	 *****************************************************************/
	public StopWatch() {
		super();

		System.out.println("Inside constructor");
		// Set what happens when you create an default stopwatch class
		// Suggestion: Maybe set your instance variables to a good default?
		// I removed others, but it's obvious you'll need a "minutes" variable,
		// so we will set it to 0 when you create a new StopWatch object.

		// minutes = 0;
	}


	// The following two functions are called "getters" and "setters"
	// You should probably read a little bit about them. The tldr is that
	// it's good to generally have functions that will retrive your object's 
	// instance variables or set the values instead of you directly setting them 
	// yourself. This can be useful if when you set it, you want it to do anything else
	// like check for incorrect values, bad user input, or if the user input type isn't how 
	// you'd like to store the values


	/******************************************************************
	  Returns the minutes for the stopwatch	  
	  @param none
	  @return minutes integer from stopwatch
	 *****************************************************************/
	// public int getMinutes() {
	// 	return minutes;
	// }

	/******************************************************************
	  Sets the amount of minutes on the stopwatch

	  @param minutes to be set for the StopWatch
	  @return none
	 *****************************************************************/
	// public void setMinutes(int minutes) {
	// 	this.minutes = minutes;
	// }


		// TODO: DELETE ME LATER
		// You can use this main funciton for testing if you'd like. This might be easier
		// if you don't have the GUI set up yet and you'd like to play around.
    public static void main (String[] args) {
			StopWatch stopwatch = new StopWatch();

		}
}
