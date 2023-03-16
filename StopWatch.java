import java.text.DecimalFormat;

import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

/**********************************************************************
  Creates the methods for our StopWatch.

  @author Jarod Collier and Ben Burger
  @version 5/11/2018
 *********************************************************************/

public class StopWatch {

	// Use this space to create instance variables that will keep track of each timer's values
	private int minutes;
	private int seconds;
	private int milliseconds;
	private String digMin;
	private String digSec;
	private String digMil;
	private String timeDisplay;
	
	
	/******************************************************************
	  Default constructor for objects in StopWatch class 
	  that sets the StopWatch to zero
	 *****************************************************************/
	public StopWatch() {
		super();

		// Set what happens when you create a default stopwatch class
		// Suggestion: Maybe set your instance variables to a good default?
		// I removed others, but it's obvious you'll need a "minutes" variable,
		// so we will set it to 0 when you create a new StopWatch object.

		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = 0;

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
	public int getMinutes() {
		return this.minutes;
	}

	public int getSeconds() {
		return this.seconds;
	}

	public int getMilliseconds() {
		return this.milliseconds;
	}

	/******************************************************************
	  Sets the amount of minutes on the stopwatch

	  @param minutes to be set for the StopWatch
	  @return none
	 *****************************************************************/
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}	

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public void add(int timeDelay) {
		this.milliseconds += timeDelay;
	}

	public boolean checkInput(String inputMin, String inputSec, String inputMil) {
		String inputs = inputMin + inputSec + inputMil;
		boolean good = false;
		int error = 0;
		System.out.println("checking input");
		if (inputMin.isEmpty()) {
			error++;
		}
		if (inputSec.isEmpty()) {
			error++;
		}
		if (inputMil.isEmpty()) {
			error++;
		}
		if (!inputs.isEmpty()) {
			for (int i = 0; i < inputs.length(); i++) {
				if (!Character.isDigit(inputs.charAt(i))) {
					error++;
				}
				if (inputs.charAt(i) == '-') {
					error++;
				}
			}
		}
		if (error == 0) {
			good = true;
		}
		if (!good) {
			System.out.print(error);
		}
		return good;
	}


	public void checkTime(String inputMin, String inputSec, String inputMil, boolean adding) {
		int msecMin = this.minutes * 60000;
		int msecSec = this.seconds * 1000;

		int msecInputMin = Integer.parseInt(inputMin) * 60000;
		int msecInputSec = Integer.parseInt(inputSec) * 1000;
		int msecInputMil = Integer.parseInt(inputMil);
		int mInput = msecInputMin + msecInputSec + msecInputMil;

		int totalTime = msecMin + msecSec + this.milliseconds;
		int finalTime = totalTime;

		if (adding) {
			finalTime += mInput;
		}
	
		if (!adding) {
			finalTime -= mInput;
		}

		int a = finalTime / 60000;
		int b = (finalTime - (a * 60000)) / 1000;
		int c = (finalTime - (a * 60000)) % 1000; 

		this.minutes = a;
		this.seconds = b;
		this.milliseconds = c; 
	}

	public void add(String inputMin, String inputSec, String inputMil) {
		checkTime(inputMin, inputSec, inputMil, true);
	}
	
	public void sub(String inputMin, String inputSec, String inputMil) {
		checkTime(inputMin, inputSec, inputMil, false);	
	}
	public void loadTime(String loadValue, int timeDelay) {
		
		String[] arrOfStr = loadValue.split(":");

		int lMSmin = (Integer.parseInt(arrOfStr[0]) * 60000);
		int lMSsec = (Integer.parseInt(arrOfStr[1]) * 1000);
		int lMSmil = Integer.parseInt(arrOfStr[2]);

		System.out.println("min in ms: " + lMSmin);
		System.out.println("sec in ms: " + lMSsec);
		System.out.println("mil: " + lMSmil);
		
		int lTime = lMSmin + lMSsec + lMSmil;
		System.out.println("total ms: " + lTime);
		this.milliseconds = lTime;
		System.out.println("loadTime this.milliseconds: " + this.milliseconds);
	}

	public void reset() {
		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = 0;
	}

	public String displayTime(boolean load) {	
		
		if (load) {
			int a = this.milliseconds / 60000;
			int b = (this.milliseconds - (a * 60000)) / 1000;
			int c = (this.milliseconds - (a * 60000)) % 1000; 
			System.out.println("c");
			System.out.println(c);
			this.minutes = a;
			this.seconds = b;
			this.milliseconds = c;
			System.out.println("t.m: " + this.milliseconds + " c: " + c);
			load = false;
		}

		digMin = "" + this.minutes;
		digSec = "" + this.seconds;
		digMil = "" + this.milliseconds;
		
		if (this.milliseconds > 999) {
			this.seconds++;
			this.milliseconds = 0;
		}

		if (this.seconds > 59) {
			this.minutes++;
			this.seconds = 0;
		}

		if (this.minutes < 10) {
			digMin = "0" + this.minutes;
		}

		if (this.seconds < 10) {
			digSec = "0" + this.seconds;
		}

		if (this.milliseconds < 100) {
			digMil = "0" + this.milliseconds;
		}

		if (this.milliseconds < 10) {
			digMil = "00" + this.milliseconds;
		}

		timeDisplay = "" + digMin + ":" + digSec + ":" + digMil;
		return timeDisplay;
	}

	public void save() {
		Path path = Paths.get("C:\\Coding\\Project1\\Project1Text.txt");
		String str = timeDisplay;
		try {
			Files.writeString(path, str, StandardCharsets.UTF_8);
		}
		catch (IOException ex) {
			System.out.println("Invalid Path");
		}
	}

	public String load() throws IOException {
		Path Project1Text = Path.of("C:\\Coding\\Project1\\Project1Text.txt");
		String loadValue = Files.readString(Project1Text);
		System.out.println(loadValue);
		return loadValue;
		// loadTime(loadValue, milliseconds);
	}

	// TODO: DELETE ME LATER
	// You can use this main function for testing if you'd like. This might be easier
	// if you don't have the GUI set up yet and you'd like to play around.
	public static void main (String[] args) {
		StopWatch stopwatch = new StopWatch();
	}


}
