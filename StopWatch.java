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
	Converts int time to a string, formatted correctly for the time display
	******************************************************************/
	public String toString() {
		System.out.println("inside toString");
		String outputTimeDisplay = displayTime(false);
		return outputTimeDisplay;
	}

	/******************************************************************
	Checks that 3 string inputs contain only ints
	******************************************************************/
	public boolean checkInput(String inputMin, String inputSec, String inputMil) {
		String inputs = inputMin + inputSec + inputMil;

		// Boolean to mark if input is good
		boolean good = false;

		// Running tally of input issues
		int error = 0;

		// Checks if input fields are empty
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

			// Checks to see if non-empty input contains non-digit chars
			for (int i = 0; i < inputs.length(); i++) {
				if (!Character.isDigit(inputs.charAt(i))) {
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
	Checks that 1 string input contains only ints
	******************************************************************/
	public boolean checkInput(String timeInput) {
		boolean good = false;
		int error = 0;

		// Running tally of : chars, as time is given in 00:00:000 format
		int colons = 0;

		// Checks if input field is empty
		if (timeInput.isEmpty()) {
			error++;
		}

		if (!timeInput.isEmpty()) {

			// Checks to see if non-empty input contains non-digit chars
			for (int i = 0; i < timeInput.length(); i++) {
				if (!Character.isDigit(timeInput.charAt(i))) {
					error++;
				}

				// Keep track of total colons
				if (timeInput.charAt(i) == ':') {
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

	public boolean checkInput(int inputMil) {
		String inputs = "" + inputMil;
		boolean good = false;
		int error = 0;
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
			System.out.println(error);
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

	public void checkTime(int inputMin, int inputSec, int inputMil, boolean adding) {
		int msecMin = this.minutes * 60000;
		int msecSec = this.seconds * 1000;

		int msecInputMin = inputMin * 60000;
		int msecInputSec = inputSec * 1000;
		int msecInputMil = inputMil;
		int mInput = msecInputMin + msecInputSec + msecInputMil;

		int totalTime = msecMin + msecSec + this.milliseconds;
		int finalTime = totalTime;

		if (adding) {
			finalTime += mInput;
		}
	
		if (!adding) {
			if (finalTime >= mInput) {
				finalTime -= mInput;
			}
			else {
				finalTime = 0;
			}
		}

		int a = finalTime / 60000;
		int b = (finalTime - (a * 60000)) / 1000;
		int c = (finalTime - (a * 60000)) % 1000; 

		this.minutes = a;
		this.seconds = b;
		this.milliseconds = c; 
	}
		
	public void add(int timeDelay) {
		this.milliseconds += timeDelay;
	}

	public void add(int inputMin, int inputSec, int inputMil) {
		checkTime(inputMin, inputSec, inputMil, true);
	}

	public void add(String inputMin, String inputSec, String inputMil) {
		checkTime(inputMin, inputSec, inputMil, true);
	}

	public void add(StopWatch stopwatchObject) {
		checkTime(stopwatchObject.minutes, stopwatchObject.seconds, stopwatchObject.milliseconds, true);
	}
	
	public void addTime(int inputMil) {
		if (checkInput(inputMil) == true) {
			checkTime(0, 0, inputMil, true);
		}

		else {
			throw new IllegalArgumentException();	
		}
	}
	
	public void sub(String inputMin, String inputSec, String inputMil) {
		checkTime(inputMin, inputSec, inputMil, false);	
	}

	public void sub(int inputMin, int inputSec, int inputMil) {
		checkTime(inputMin, inputSec, inputMil, false);
	}

	public void sub(int inputMil) {
		System.out.println("inside sub");
		if (checkInput(inputMil) == true) {
			checkTime(0, 0, inputMil, false);
		}

		else {
			throw new IllegalArgumentException();
		}
	}

	public void sub(StopWatch stopwatchObject) {
		checkTime(stopwatchObject.minutes, stopwatchObject.seconds, stopwatchObject.milliseconds, false);
	}

	public void loadTime(String loadValue) {
		
		String[] arrOfStr = loadValue.split(":");
		
		int lMSmin = (Integer.parseInt(arrOfStr[0]));
		int lMSsec = (Integer.parseInt(arrOfStr[1]));
		int lMSmil = Integer.parseInt(arrOfStr[2]);
		
		this.minutes = lMSmin;
		this.seconds = lMSsec;
		this.milliseconds = lMSmil;
	}

	public void reset() {
		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = 0;
	}

	public boolean equals(StopWatch stopwatchObject) {
		boolean pass = false;
		try {
			if (this.minutes == stopwatchObject.minutes && this.seconds == stopwatchObject.seconds && this.milliseconds == stopwatchObject.milliseconds) {
				pass = true;
			}
		}

		catch (NullPointerException e) {
			pass = false;
		}
		return pass;
	}

	public static boolean equals(StopWatch s1, StopWatch s2) {
		boolean pass = false;
		if (s1.minutes == s2.minutes && s1.seconds == s2.seconds && s1.milliseconds == s2.milliseconds) {
			pass = true;
		}
		return pass;
	}

	public int compareTo(StopWatch stopwatchObject) {		
		int msecMin = this.minutes * 60000;
		int msecSec = this.seconds * 1000;

		int msecObMin = stopwatchObject.minutes * 60000;
		int msecObSec = stopwatchObject.seconds * 1000;
		int msecObMil = stopwatchObject.milliseconds;
		int mInput = msecObMin + msecObSec + msecObMil;

		int totalTime = msecMin + msecSec + this.milliseconds;
		int finalTime = totalTime;

		finalTime -= mInput;
		
		return finalTime;
	}

	public String inc() {
		this.milliseconds++;
		displayTime(false);
		return timeDisplay;
	}

	public String dec() {
		checkTime(0, 0, 1, false);
		displayTime(false);
		return timeDisplay;
	}

	public String displayTime(boolean load) {	
		if (load) {
			int a = this.milliseconds / 60000;
			int b = (this.milliseconds - (a * 60000)) / 1000;
			int c = (this.milliseconds - (a * 60000)) % 1000; 
			this.minutes = a;
			System.out.println("dT this.min " + this.minutes);
			this.seconds = b;
			System.out.println("dT this.sec " + this.seconds);
			this.milliseconds = c;
			System.out.println("dT this.mil " + this.milliseconds);
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
	
	public void save(String fileName) {
		String savedFileName = "";
		String plswork = "";

		try {
			File savedFile = new File(fileName + ".txt");
			if (savedFile.createNewFile()) {
				savedFileName = savedFile.getName();
				plswork = "C:\\Coding\\Project1\\" + savedFileName;
				System.out.println(plswork);
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
		String str = toString();
		System.out.println(str);

		try {
			Files.writeString(path, str, StandardCharsets.UTF_8);
		}
		
		catch (IOException ex) {
			System.out.println("Invalid Path IO");
		}

		catch (NullPointerException ex) {
			System.out.println("Invalid Path Null");	
		}
	}
	
	public String load(String fileName)  {
	// public String load(String fileName) throws IOException {
		// String loadedFileName = "";
		String loadValue = "";
		try {
			String work = "C:\\Coding\\Project1\\" + fileName + ".txt";
			Path loadedText = Path.of(work);
			loadValue = Files.readString(loadedText);
		}
		// catch (NoSuchFileException ex) {
		// 	System.out.println("Invalid File");
		// }
		catch (IOException ex) {
			System.out.println("Invalid IO");
		}
		
		loadTime(loadValue);

		return loadValue;
	}

	private void stopWatchHelper(int minutes, int seconds, int milliseconds) {
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
		if (milliseconds >= 0 && milliseconds <= 999) {
			this.milliseconds = milliseconds;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	private void stopWatchHelper(String timeInput) {
		if (checkInput(timeInput) == true) {

			String[] arrOfTimeInput = timeInput.split(":");
			int arrLength = arrOfTimeInput.length;
			int minutes = 0;
			int seconds = 0;
			int milliseconds = 0;

			if (arrLength >= 1) {
				milliseconds = Integer.parseInt(arrOfTimeInput[arrLength - 1]);
			}

			if (arrLength > 1) {
				seconds = Integer.parseInt(arrOfTimeInput[arrLength - 2]);
			}	
				
			if (arrLength > 2) {
				minutes = Integer.parseInt(arrOfTimeInput[arrLength - 3]);
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
			// JOptionPane.showInternalMessageDialog("Error: Illegal Argument Exception.");
		}
	}

	public static void main (String[] args) {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);

		s1.save("file1");
		s1 = new StopWatch (); 
		try {
			s1.load("file1");
			s1.equals(s2);
		}
		catch (IOException e) {}
	}
}

