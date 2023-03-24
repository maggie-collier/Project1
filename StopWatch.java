import java.text.DecimalFormat;

import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JOptionPane;

// import TimerPanel.ButtonListener;

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
			this.minutes = 0;
			this.seconds = 0;
			this.milliseconds = 0;
	}
	
	public StopWatch(int minutes, int seconds, int milliseconds) {
		super();
		System.out.println("inside constructor, 3V");
		stopWatchHelper(minutes, seconds, milliseconds);
	}
	
	public StopWatch(int seconds, int milliseconds) {
		super();
		System.out.println("inside constructor, 2V");
		stopWatchHelper(seconds, milliseconds);
	}
	
	public StopWatch(int milliseconds) {
		super();
		System.out.println("inside constructor, 1V");
		stopWatchHelper(milliseconds);
	}
	
	public StopWatch(String timeInput) {
		super();
		System.out.println("inside timeInput");
		stopWatchHelper(timeInput);
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

	public String toString() {
		System.out.println("inside toString");
		String outputTimeDisplay = displayTime(false);
		return outputTimeDisplay;
	}

	public void add(int timeDelay) {
		this.milliseconds += timeDelay;
	}

	public boolean checkInput(String inputMin, String inputSec, String inputMil) {
		String inputs = inputMin + inputSec + inputMil;
		boolean good = false;
		int error = 0;
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

public boolean checkInput(String timeInput) {
	System.out.println("inside checkInput");
	boolean good = false;
	int error = 0;
	int colons = 0;
	if (timeInput.isEmpty()) {
		error++;
	}
		if (!timeInput.isEmpty()) {
			for (int i = 0; i < timeInput.length(); i++) {
				if (!Character.isDigit(timeInput.charAt(i)) && timeInput.charAt(i) != ':') {
					error++;
				}
				if (timeInput.charAt(i) == '-') {
					error++;
				}
				if (timeInput.charAt(i) == ':') {
					colons++;
				}
				if (timeInput.charAt(i) == ':' && timeInput.length() == 1) {
					error++;
				}
			}
		}
		if (error == 0) {
			good = true;
		}
		// if (colons <= 2) {
		// 	good = true;
		// }
		if (!good) {
			System.out.println("check Input errors: " + error);
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
	
	int lTime = lMSmin + lMSsec + lMSmil;
	this.milliseconds = lTime;
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
			this.minutes = a;
			this.seconds = b;
			this.milliseconds = c;
			load = false;
		}

		System.out.println("inside displayTime");

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
			System.out.println(digMin);
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
	
	public void save(String fileName) {
		String savedFileName = "";
		String plswork = "";
		try {
			File savedFile = new File(fileName + ".txt");
			if (savedFile.createNewFile()) {
				savedFileName = savedFile.getName();
				plswork = "C:\\Coding\\Project1\\" + savedFileName;
				System.out.println("File created: " + savedFileName);
			}
			else {
				System.out.println("File already exists. ");
			}
		}
		catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		Path path = Paths.get(plswork);
		String str = timeDisplay;
		System.out.println(path + ", " + str);
		try {
			Files.writeString(path, str, StandardCharsets.UTF_8);
		}
		catch (IOException ex) {
			System.out.println("Invalid Path");
		}
	}
	
	public String load(String fileName) throws IOException {
		// String loadedFileName = "";
		String loadValue = "";
		try {
			String work = "C:\\Coding\\Project1\\" + fileName + ".txt";
			Path loadedText = Path.of(work);
			loadValue = Files.readString(loadedText);
			System.out.println(loadValue);
		}
		catch (NoSuchFileException ex) {
			System.out.println("Invalid File");
		}
		catch (IOException ex) {
			System.out.println("Invalid IO");
		}
		return loadValue;
	}

	private void stopWatchHelper(int minutes, int seconds, int milliseconds) {
		System.out.println("inside helper, 3V");	
		if (minutes >= 0) {
			this.minutes = minutes;
		}
		else {
			throw new IllegalArgumentException();
		}
		
		if (seconds >= 0 && seconds <= 59) {
			this.seconds = seconds;
		}
		else {
			throw new IllegalArgumentException();
		}
		
		if (milliseconds >= 0 && milliseconds <= 999) {
			this.milliseconds = milliseconds;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	private void stopWatchHelper(int seconds, int milliseconds) {
		System.out.println("inside helper, 2V");	
		
		if (seconds >= 0 && seconds <= 59) {
			this.seconds = seconds;
		}
		else {
			throw new IllegalArgumentException();
		}
		
		if (milliseconds >= 0 && milliseconds <= 999) {
			this.milliseconds = milliseconds;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	private void stopWatchHelper(int milliseconds) {
		System.out.println("inside helper, 1V");	
		
		if (milliseconds >= 0 && milliseconds <= 999) {
			this.milliseconds = milliseconds;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	private void stopWatchHelper(String timeInput) {
		System.out.println("inside helper, S");	
		
		if (checkInput(timeInput) == true) {

		// System.out.println(Integer.parseInt(timeInput) + ", " + Math.round(Integer.parseInt(timeInput)));
		// if (Integer.parseInt(timeInput) == Math.round(Integer.parseInt(timeInput))) {
			String[] arrOfTimeInput = timeInput.split(":");
			int arrLength = arrOfTimeInput.length;
			int minutes = 0;
			int seconds = 0;
			int milliseconds = 0;

			System.out.println(arrLength);
			
			if (arrLength >= 1) {
				milliseconds = Integer.parseInt(arrOfTimeInput[arrLength - 1]);
				System.out.println(milliseconds);
			}

			if (arrLength > 1) {
				seconds = Integer.parseInt(arrOfTimeInput[arrLength - 2]);
				System.out.println(seconds);
			}	
				
			if (arrLength > 2) {
				minutes = Integer.parseInt(arrOfTimeInput[arrLength - 3]);
				System.out.println(minutes);
			}
			if (minutes >= 0) {
				this.minutes = minutes;
			}
			else {
				throw new IllegalArgumentException();
			}
			
			if (seconds >= 0 && seconds <= 59) {
				this.seconds = seconds;
			}
			else {
				throw new IllegalArgumentException();
			}
			
			if (milliseconds >= 0 && milliseconds <= 999) {
				this.milliseconds = milliseconds;
			}
			else {
				throw new IllegalArgumentException();
			}
		}

		else {
			throw new IllegalArgumentException();	
			// create pop up that says error, catch exception
		}
	}

	public static void main (String[] args) {
		new StopWatch("1:23:456:");
		// System.out.println(s.toString());
	}
}

