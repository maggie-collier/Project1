import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/********************************************************************
  Creates the methods for the stopwatch.
********************************************************************/
public class StopWatch {

	/******************************************************************
	Instance variables for all stopwatch elements
	******************************************************************/
	
	// Integer variables for minutes, seconds and milliseconds
	private int minutes;
	private int seconds;
	private int milliseconds;

	// String variables for minutes, seconds and milliseconds
	private String digMin;
	private String digSec;
	private String digMil;

	// String variable for running time display
	private String timeDisplay;
	
	/******************************************************************
	Default constructor for objects in StopWatch class, takes no parameters
	******************************************************************/
	public StopWatch() {
		super();

		// Sets the stopwatch to 0
			this.minutes = 0;
			this.seconds = 0;
			this.milliseconds = 0;
	}
	
	/******************************************************************
	Stopwatch constructor that takes 3 int inputs
	******************************************************************/
	public StopWatch(int minutes, int seconds, int milliseconds) {
		super();
		stopWatchHelper(minutes, seconds, milliseconds);
	}
	
	/******************************************************************
	Stopwatch constructor that takes 2 int inputs
	******************************************************************/
	public StopWatch(int seconds, int milliseconds) {
		super();
		stopWatchHelper(seconds, milliseconds);
	}
	
	/******************************************************************
	Stopwatch constructor that takes 1 int input
	******************************************************************/
	public StopWatch(int milliseconds) {
		super();
		stopWatchHelper(milliseconds);
	}
	
	/******************************************************************
	Stopwatch constructor that takes time input in string
	******************************************************************/
	public StopWatch(String timeInput) {
		super();
		stopWatchHelper(timeInput);
	}

	/******************************************************************
	Returns the minutes for the stopwatch	  
	******************************************************************/
	public int getMinutes() {
		return this.minutes;
	}

	/******************************************************************
	Returns the seconds for the stopwatch	  
	******************************************************************/
	public int getSeconds() {
		return this.seconds;
	}

	/******************************************************************
	Returns the milliseconds for the stopwatch	  
	******************************************************************/
	public int getMilliseconds() {
		return this.milliseconds;
	}

	/******************************************************************
	Sets the amount of minutes on the stopwatch
	******************************************************************/
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/******************************************************************
	Sets the amount of seconds on the stopwatch
	******************************************************************/
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}	

	/******************************************************************
	Sets the amount of milliseconds on the stopwatch
	******************************************************************/
	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	/******************************************************************
	Converts int time to a string, returns correctly formatted time 
	display string
	******************************************************************/
	public String toString() {
		System.out.println("inside toString");
		String outputTimeDisplay = displayTime();
		return outputTimeDisplay;
	}

	/******************************************************************
	If 3 string inputs each contain only ints, checkInput returns boolean 
	"good input" is true 
	******************************************************************/
	public boolean checkInput(String inputMin, String inputSec, String inputMil) {
		String inputs = inputMin + inputSec + inputMil;

		// Boolean to mark if input is good
		boolean good = false;

		// Running tally of input issues
		int error = 0;

		// Checks if input fields are empty
		if (inputMin.isEmpty()) {

			// increment error count if field is empty
			error++;
		}
		if (inputSec.isEmpty()) {
			error++;
		}
		if (inputMil.isEmpty()) {
			error++;
		}
		if (!inputs.isEmpty()) {

			// Checks to see if non-empty input contains non-digit chars
			for (int i = 0; i < inputs.length(); i++) {
				if (!Character.isDigit(inputs.charAt(i))) {

					// increment error count if 'i' is a non-digit
					error++;
				}
				if (inputs.charAt(i) == '-') {
					error++;
				}
			}
		}

		// Identifies good input if there are no errors
		if (error == 0) {
			good = true;
		}

		// Prints out the number of errors if bad input
		if (!good) {
			System.out.println(error);
		}
		
		return good;
	}

	/******************************************************************
	If 1 string input contains only ints, checkInput returns boolean 
	"good input" is true 
	******************************************************************/
	public boolean checkInput(String timeInput) {

		// Boolean to mark if input is good
		boolean good = false;

		// Running tally of input issues
		int error = 0;

		// Running tally of : chars, as time is given in 00:00:000 format
		int colons = 0;

		// Checks if input field is empty
		if (timeInput.isEmpty()) {

			// increment error count if field is empty
			error++;
		}

		if (!timeInput.isEmpty()) {

			// Checks to see if non-empty input contains non-digit chars
			for (int i = 0; i < timeInput.length(); i++) {
				if (!Character.isDigit(timeInput.charAt(i))) {

					// increment error count if 'i' is a non-digit
					error++;
				}

				// Keep track of total colons
				if (timeInput.charAt(i) == ':') {

					// increment colon count if 'i' is ':'
					colons++;
				}

				if (timeInput.charAt(i) == '-') {
					error++;
				}

				// Checks to see if first char is :
				if (timeInput.charAt(0) == ':') {
					error++;
				}

				// Checks to see if last char is :
				if (timeInput.charAt(timeInput.length() - 1) == ':'){
					error++;
				}

				if (i < timeInput.length() - 1) {

					// Checks to see if there are two consecutive colons
					if (timeInput.charAt(i) == ':') {
						if (timeInput.charAt(i+1) == ':') {
							error++;
						}
					}
				}
			}
		}

		// Identifies good input if there are no errors and the correct number of colons
		if (error == 0 && colons <= 2) {
			good = true;
		}
		
		// Prints out the number of errors if bad input
		if (!good) {
			System.out.println("check Input errors: " + error);
		}

		return good;	
	}

	/******************************************************************
	If 1 int input contains only ints, checkInput returns boolean "good 
	input" is true 
	******************************************************************/
	public boolean checkInput(int inputMil) {
		String inputs = "" + inputMil;

		// Boolean to mark if input is good
		boolean good = false;

		// Running tally of input issues
		int error = 0;

		// Checks if input field is empty
		if (!inputs.isEmpty()) {

			// Checks to see if non-empty input contains non-digit chars
			for (int i = 0; i < inputs.length(); i++) {
				if (!Character.isDigit(inputs.charAt(i))) {

					// increment error count if 'i' is a non-digit
					error++;
				}
				if (inputs.charAt(i) == '-') {
					error++;
				}
			}
		}

		// Identifies good input if there are no errors
		if (error == 0) {
			good = true;
		}

		// Prints out the number of errors if bad input
		if (!good) {
			System.out.println(error);
		}

		return good;
	}

	/******************************************************************
	checkTime computes addition or subtraction of 3 string inputs and 
	minutes, seconds, milliseconds according to boolean "adding" 
	******************************************************************/
	public void checkTime(String inputMin, String inputSec, String inputMil, boolean adding) {

		// int value of this.minutes in milliseconds
		int msecMin = this.minutes * 60000;

		// int value of this.seconds in milliseconds
		int msecSec = this.seconds * 1000;

		// int value of input minutes in milliseconds	
		int msecInputMin = Integer.parseInt(inputMin) * 60000;

		// int value of input seconds in milliseconds
		int msecInputSec = Integer.parseInt(inputSec) * 1000;

		// int value of input milliseconds
		int msecInputMil = Integer.parseInt(inputMil);

		// total input time in milliseconds
		int mInput = msecInputMin + msecInputSec + msecInputMil;

		// total starting time in milliseconds
		int totalTime = msecMin + msecSec + this.milliseconds;

		// computed total time
		int finalTime = totalTime;

		// if adding, add total input time to total time
		if (adding) {
			finalTime += mInput;
		}
	
		// if subtracting, subtract total input time from total time
		if (!adding) {
			finalTime -= mInput;
		}

		// total time minutes
		int a = finalTime / 60000;

		// total time seconds
		int b = (finalTime - (a * 60000)) / 1000;

		// total time milliseconds
		int c = (finalTime - (a * 60000)) % 1000; 

		// sets computed values to this. time intervals
		this.minutes = a;
		this.seconds = b;
		this.milliseconds = c; 
	}

	/******************************************************************
	checkTime computes addition or subtraction of 3 int inputs and 
	minutes, seconds, milliseconds according to boolean "adding" 
	******************************************************************/
	public void checkTime(int inputMin, int inputSec, int inputMil, boolean adding) {
		
		// int value of this.minutes in milliseconds
		int msecMin = this.minutes * 60000;
		
		// int value of this.seconds in milliseconds
		int msecSec = this.seconds * 1000;

		// int value of input minutes in milliseconds	
		int msecInputMin = inputMin * 60000;

		// int value of input seconds in milliseconds
		int msecInputSec = inputSec * 1000;

		// int value of input milliseconds
		int msecInputMil = inputMil;

		// total input time in milliseconds
		int mInput = msecInputMin + msecInputSec + msecInputMil;

		// total starting time in milliseconds
		int totalTime = msecMin + msecSec + this.milliseconds;

		// computed total time
		int finalTime = totalTime;

		// if adding, add total input time to total time
		if (adding) {
			finalTime += mInput;
		}
	
		// if subtracting, subtract total input time from total time
		if (!adding) {

			// if input value is less than/equal to starting time, subtract
			if (finalTime >= mInput) {
				finalTime -= mInput;
			}

			// if input value is greater than starting time, set time to 0
			else {
				finalTime = 0;
			}
		}

		// total time minutes
		int a = finalTime / 60000;

		// total time seconds
		int b = (finalTime - (a * 60000)) / 1000;

		// total time milliseconds
		int c = (finalTime - (a * 60000)) % 1000; 


		// sets computed values to this. time intervals
		this.minutes = a;
		this.seconds = b;
		this.milliseconds = c; 
	}
	
	/******************************************************************
	Continuous addition of milliseconds to the running timer
	******************************************************************/
	public void add(int timeDelay) {
		this.milliseconds += timeDelay;
	}

	/******************************************************************
	Calls checkTime to add 3 int inputs to the timer
	******************************************************************/
	public void add(int inputMin, int inputSec, int inputMil) {
		checkTime(inputMin, inputSec, inputMil, true);
	}

	/******************************************************************
	Calls checkTime to add 3 string inputs to the timer
	******************************************************************/
	public void add(String inputMin, String inputSec, String inputMil) {
		checkTime(inputMin, inputSec, inputMil, true);
	}

	/******************************************************************
	Calls checkTime to add the minutes, seconds, milliseconds of 
	another stopwatch object to the timer
	******************************************************************/
	public void add(StopWatch stopwatchObject) {
		checkTime(stopwatchObject.minutes, stopwatchObject.seconds, stopwatchObject.milliseconds, true);
	}
	
	/******************************************************************
	Calls checkInput to verify good input, else throws illegal arg 
	exception, then calls checkTime to add 1 int milliseconds input to 
	the timer
	******************************************************************/
	public void addTime(int inputMil) {
		if (checkInput(inputMil) == true) {
			checkTime(0, 0, inputMil, true);
		}

		else {
			throw new IllegalArgumentException();	
		}
	}
	
	/******************************************************************
	Calls checkTime to subtract 3 string inputs from the timer
	******************************************************************/
	public void sub(String inputMin, String inputSec, String inputMil) {
		checkTime(inputMin, inputSec, inputMil, false);	
	}

	/******************************************************************
	Calls checkTime to subtract 3 int inputs from the timer
	******************************************************************/
	public void sub(int inputMin, int inputSec, int inputMil) {
		checkTime(inputMin, inputSec, inputMil, false);
	}

	/******************************************************************
	Calls checkInput to verify good input, else throws illegal arg 
	exception, then calls checkTime to subtract 1 int milliseconds 
	input from the timer
	******************************************************************/
	public void sub(int inputMil) {
		if (checkInput(inputMil) == true) {
			checkTime(0, 0, inputMil, false);
		}

		else {
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	Calls checkTime to subtract the minutes, seconds, milliseconds of 
	another stopwatch object from the timer
	******************************************************************/
	public void sub(StopWatch stopwatchObject) {
		checkTime(stopwatchObject.minutes, stopwatchObject.seconds, stopwatchObject.milliseconds, false);
	}

	/******************************************************************
	Parses the loadValue string for this. time intervals
	******************************************************************/
	public void loadTime(String loadValue) {
		
		// splits loadValue string into minutes, seconds, milliseconds
		String[] arrOfStr = loadValue.split(":");
		
		// int minutes value
		int lMSmin = (Integer.parseInt(arrOfStr[0]));

		// int seconds value
		int lMSsec = (Integer.parseInt(arrOfStr[1]));

		// int milliseconds value
		int lMSmil = Integer.parseInt(arrOfStr[2]);

		// sets this. time intervals to loaded values
		this.minutes = lMSmin;
		this.seconds = lMSsec;
		this.milliseconds = lMSmil;
	}

	/******************************************************************
	Sets this. time intervals to 0
	******************************************************************/	
	public void reset() {
		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = 0;
	}

	/******************************************************************
	Checks to see if another stopwatch object is equal to ours, returns 
	"pass" as true if so, catches null pointer exception
	******************************************************************/
	public boolean equals(StopWatch stopwatchObject) {
		boolean pass = false;
		try {

			// if the minutes, seconds, and milliseconds match
			if (this.minutes == stopwatchObject.minutes && this.seconds == stopwatchObject.seconds && this.milliseconds == stopwatchObject.milliseconds) {
				pass = true;
			}
		}

		catch (NullPointerException e) {
			pass = false;
		}
		
		return pass;
	}

	/******************************************************************
	Checks to see if the times of two stopwatches are equal, returns 
	"pass" as true if so
	******************************************************************/
	public static boolean equals(StopWatch s1, StopWatch s2) {
		boolean pass = false;
		if (s1.minutes == s2.minutes && s1.seconds == s2.seconds && s1.milliseconds == s2.milliseconds) {
			pass = true;
		}
		
		return pass;
	}

	/******************************************************************
	Subtracts another stopwatch object from ours, returns int result
	******************************************************************/
	public int compareTo(StopWatch stopwatchObject) {		

		// int value of this.minutes in milliseconds
		int msecMin = this.minutes * 60000;

		// int value of this.seconds in milliseconds
		int msecSec = this.seconds * 1000;

		// int value of stopwatch minutes in milliseconds	
		int msecObMin = stopwatchObject.minutes * 60000;

		// int value of stopwatch seconds in milliseconds
		int msecObSec = stopwatchObject.seconds * 1000;

		// int value of stopwatch milliseconds
		int msecObMil = stopwatchObject.milliseconds;

		// total stopwatch time in milliseconds
		int mInput = msecObMin + msecObSec + msecObMil;

		// total starting time in milliseconds
		int totalTime = msecMin + msecSec + this.milliseconds;

		// computed total time
		int finalTime = totalTime;

		// subtract total input time from total time
		finalTime -= mInput;
		
		// returns remaining time
		return finalTime;
	}

	/******************************************************************
	Increases time by 1 millisecond, returns new time
	******************************************************************/
	public String inc() {
		this.milliseconds++;

		// calls displayTime to format new time
		displayTime();

		return timeDisplay;
	}

	/******************************************************************
	Calls checkTime to decrease time by 1 millisecond, returns new time
	******************************************************************/
	public String dec() {
		checkTime(0, 0, 1, false);

		// calls displayTime to format new time
		displayTime();

		return timeDisplay;
	}

	/******************************************************************
	formats time so that minutes and seconds increment correctly, 
	returns new time in string format
	******************************************************************/
	public String displayTime() {	
		
		// sets this. time intervals to string instance variables
		digMin = "" + this.minutes;
		digSec = "" + this.seconds;
		digMil = "" + this.milliseconds;
		
		/****************************************************************
		increment seconds when milliseconds exceeds 999, set milliseconds 
		back to 0 
		****************************************************************/
		if (this.milliseconds > 999) {
			this.seconds++;
			this.milliseconds = 0;
		}
		
		/****************************************************************
		increment minutes when seconds exceeds 59, set seconds back to 0 
		****************************************************************/
		if (this.seconds > 59) {
			this.minutes++;
			this.seconds = 0;
		}

		// formatting a 0 next to single digit minute value
		if (this.minutes < 10) {
			digMin = "0" + this.minutes;
		}
		
		// formatting a 0 next to single digit second value
		if (this.seconds < 10) {
			digSec = "0" + this.seconds;
		}
		
		// formatting a 0 next to double digit millisecond value
		if (this.milliseconds < 100) {
			digMil = "0" + this.milliseconds;
		}
		
		// formatting two zeros next to single digit millisecond value
		if (this.milliseconds < 10) {
			digMil = "00" + this.milliseconds;
		}
		
		// string display of correctly formatted time
		timeDisplay = "" + digMin + ":" + digSec + ":" + digMil;
		return timeDisplay;
	}
	
	/******************************************************************
	Saves current time display to a new .txt file, takes parameter 
	fileName string, the user entry
	******************************************************************/	
	public void save(String fileName) {
		String savedFileName = "";
		String pathName = "";

		try {

			// Creates new .txt file
			File savedFile = new File(fileName + ".txt");

			// If successful file creation, save it to pathName path
			if (savedFile.createNewFile()) {
				savedFileName = savedFile.getName();
				pathName = "C:\\Coding\\Project1\\" + savedFileName;
			}

			// Guards against error of duplicate file creation
			else {
				System.out.println("File already exists. ");
			}
		}

		// Catches IO Exception and prints if error
		catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}

		// Converts string path to path object
		Path path = Paths.get(pathName);

		// Converts time int to string
		String str = toString();

		try {

			// Writes the desired time to the desired file
			Files.writeString(path, str, StandardCharsets.UTF_8);
		}
		
		// Catches IO Exception
		catch (IOException ex) {
			System.out.println("Invalid Path IO");
		}

		// Catches Null Pointer Exception
		catch (NullPointerException ex) {
			System.out.println("Invalid Path Null");	
		}
	}

	/******************************************************************
	Retrieves time from file requested by user, takes parameter 
	fileName string, and returns the requested time as a string
	******************************************************************/	
	public String load(String fileName)  {
		String loadValue = "";
		
		try {

			// Sets path of file desired by user to a string
			String work = "C:\\Coding\\Project1\\" + fileName + ".txt";

			// Sets the path in string form to a path object
			Path loadedText = Path.of(work);

			// Reads and sets the contents of the desired file to a string
			loadValue = Files.readString(loadedText);
		}
	
		// Catches IO Exception
		catch (IOException ex) {
			System.out.println("Invalid IO");
		}
		
		/****************************************************************
		Calls loadTime to set the this. time intervals to the newly 
		loaded time values
		****************************************************************/		
		loadTime(loadValue);

		return loadValue;
	}

	/******************************************************************
	Helper function that takes 3 int values and verifies usable values,
	throws Illegal Argument Exception if bad value
	******************************************************************/	
	private void stopWatchHelper(int minutes, int seconds, int milliseconds) {

		// if minutes is an integer, accept value
		if (minutes >= 0) {
			this.minutes = minutes;
		}

		// throw exception for bad minutes input
		else {
			throw new IllegalArgumentException();
		}
		
		// if seconds is an integer below 60, accept value
		if (seconds >= 0 && seconds <= 59) {
			this.seconds = seconds;
		}

		// throw exception for bad seconds input
		else {
			throw new IllegalArgumentException();
		}
		
		// if milliseconds is an integer below 1000, accept value
		if (milliseconds >= 0 && milliseconds <= 999) {
			this.milliseconds = milliseconds;
		}

		// throw exception for bad milliseconds input
		else {
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	Helper function that takes 2 int values and verifies usable values,
	throws Illegal Argument Exception if bad value
	******************************************************************/	
	private void stopWatchHelper(int seconds, int milliseconds) {
	
		// if seconds is an integer below 60, accept value
		if (seconds >= 0 && seconds <= 59) {
			this.seconds = seconds;
		}

		// throw exception for bad seconds input
		else {
			throw new IllegalArgumentException();
		}
		
		// if milliseconds is an integer below 1000, accept value
		if (milliseconds >= 0 && milliseconds <= 999) {
			this.milliseconds = milliseconds;
		}

		// throw exception for bad milliseconds input	
		else {
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	Helper function that takes 1 int value and verifies usable value, 
	throws Illegal Argument Exception if bad value
	******************************************************************/	
	private void stopWatchHelper(int milliseconds) {

		// if milliseconds is an integer below 1000, accept value
		if (milliseconds >= 0 && milliseconds <= 999) {
			this.milliseconds = milliseconds;
		}

		// throw exception for bad milliseconds input	
		else {
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	Helper function that takes 1 string value
	******************************************************************/	
	private void stopWatchHelper(String timeInput) {

		// Calls checkInput, returns true if string contains only ints
		if (checkInput(timeInput) == true) {

			// Creates string array of the minutes, seconds, milliseconds
			String[] arrOfTimeInput = timeInput.split(":");

			// Int value of array length
			int arrLength = arrOfTimeInput.length;

			// Empty ints for minutes, seconds, and milliseconds
			int minutes = 0;
			int seconds = 0;
			int milliseconds = 0;

			/**************************************************************
			If the array has 1 or more values, milliseconds is the last one
			**************************************************************/
			if (arrLength >= 1) {
				milliseconds = Integer.parseInt(arrOfTimeInput[arrLength - 1]);
			}

			/**************************************************************
			If the array has more than 1 value, seconds is the second to 
			last one 			
			**************************************************************/
			if (arrLength > 1) {
				seconds = Integer.parseInt(arrOfTimeInput[arrLength - 2]);
			}	
				
			/**************************************************************
			If the array has more than 2 values, minutes is the third to 
			last one 			
			**************************************************************/
			if (arrLength > 2) {
				minutes = Integer.parseInt(arrOfTimeInput[arrLength - 3]);
			}

			// if minutes is an integer, accept value
			if (minutes >= 0) {
				this.minutes = minutes;
			}

			// throw exception for bad minutes input
			else {
				throw new IllegalArgumentException();
			}
			
			// if seconds is an integer below 60, accept value
			if (seconds >= 0 && seconds <= 59) {
				this.seconds = seconds;
			}

			// throw exception for bad seconds input
			else {
				throw new IllegalArgumentException();
			}
			
			// if milliseconds is an integer below 1000, accept value
			if (milliseconds >= 0 && milliseconds <= 999) {
				this.milliseconds = milliseconds;
			}

			// throw exception for bad milliseconds input
			else {
				throw new IllegalArgumentException();
			}
		}

		// throw exception for bad string input
		else {
			throw new IllegalArgumentException();	
		}
	}
}

