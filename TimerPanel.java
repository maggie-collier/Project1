
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.String;
import java.io.IOException;

/********************************************************************
Creates a stopwatch panel.
********************************************************************/
public class TimerPanel extends JPanel {
	
	/******************************************************************
	 Instance variables for all timer panel elements
	 *****************************************************************/

	// Stopwatch object
	private StopWatch stopwatch;

	// Time interval of every 10 milliseconds, java timer and listener
	private static final int TIME_DELAY = 10;
	Timer javaTimer;
	private TimerListener timerListener; 
	
	// The buttons for the GUI
	private JButton startButton;
	private JButton stopButton;
	private JButton addButton;
	private JButton subButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton resetButton;
	
	// User input fields and labels for the GUI
	private JTextField minutesField;
	private JLabel minutesLabel;		
	private JTextField secondsField;
	private JLabel secondsLabel;
	private JTextField millisecondsField;
	private JLabel millisecondsLabel;	

	// Creates the label that displays the time kept on the stopwatch for the GUI
	private JLabel timerLabel;
	
	/******************************************************************
	TimerPanel constructor
	******************************************************************/
	public TimerPanel() {

		// Instantiates stopwatch object
		stopwatch = new StopWatch();

		// Creates a listener
		timerListener = new TimerListener();

		// Creates a timer
		javaTimer = new Timer(TIME_DELAY, timerListener);
	
		// Creates buttons with text for the GUI
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		addButton = new JButton("Add");
		subButton = new JButton("Sub");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		resetButton = new JButton("Reset");

		// Creates the fields for time input from user
		minutesField = new JTextField (4);
		secondsField = new JTextField (4);
		millisecondsField = new JTextField (4);
		
		// Creates labels for the time input fields
		minutesLabel = new JLabel("(minutes)");
		secondsLabel = new JLabel("(seconds)");
		millisecondsLabel = new JLabel("  (milliseconds)  ");

		// Creates label that displays time
		timerLabel = new JLabel();
		
		// Creates a listener to know what buttons are pressed
		ButtonListener buttonListen = new ButtonListener();
		
		// Adds the buttons to the listener
		startButton.addActionListener(buttonListen);
		stopButton.addActionListener(buttonListen);
		addButton.addActionListener(buttonListen);
		subButton.addActionListener(buttonListen);
		saveButton.addActionListener(buttonListen);
		loadButton.addActionListener(buttonListen);
		resetButton.addActionListener(buttonListen);

		// Creates loc variable to arrange elements on the panel
		setLayout(new GridBagLayout());
		GridBagConstraints loc = new GridBagConstraints();

		// Makes borders black
		setBorder(BorderFactory.createLineBorder(Color.black));

		/****************************************************************
		Locations of panel elements on GUI
		****************************************************************/
    
		// Location of timer label, top center of panel
		loc.gridx = 2;
		loc.gridy = 1;
		loc.insets.bottom = 2; 
		add(timerLabel, loc);
		
		// Location of buttons in 2 columns of 3
		loc.gridx = 1;
		loc.gridy = 3;
		add(startButton, loc);
		loc.gridx = 3;
		loc.gridy = 3;
		add(stopButton, loc);
		loc.gridx = 1;
		loc.gridy = 6;
		add(addButton, loc);
		loc.gridx = 3;
		loc.gridy = 6;
		add(subButton, loc);
		loc.gridx = 1;
		loc.gridy = 9;
		add(saveButton, loc);
		loc.gridx = 3;
		loc.gridy = 9;
		add(loadButton, loc);
		
		// Location of user input box for minutes with label, centered
		loc.gridx = 2;
		loc.gridy = 3;
		add(minutesField, loc);
		loc.gridx = 2;
		loc.gridy = 4;
		add(minutesLabel, loc);
		
		// Location of user input box for seconds with label, centered, below minutes
		loc.gridx = 2;
		loc.gridy = 6;
		add(secondsField, loc);
		loc.gridx = 2;
		loc.gridy = 7;
		add(secondsLabel, loc);	
		
		// Location of user input box for milliseconds with label, centered, below seconds
		loc.gridx = 2;
		loc.gridy = 9;
		add(millisecondsField, loc);
		loc.gridx = 2;
		loc.gridy = 10;
		add(millisecondsLabel, loc);
		
		// Location of reset button, bottom center of panel
		loc.gridx = 2;
		loc.gridy = 12;
		add(resetButton, loc);

		// Sets user input boxes to default to 0
		minutesField.setText("0");
		secondsField.setText("0");
		millisecondsField.setText("0");

		// Sets timer label to default to 00:00:000
		timerLabel.setText("00:00:000");
	} 
	
	// Creates a listener to keep time
	private class TimerListener implements ActionListener {
		
		/****************************************************************
		Receives action events of timer
		****************************************************************/
		public void actionPerformed(ActionEvent event) {
			
			// Adds time every 10 milliseconds
			stopwatch.add(TIME_DELAY);

			// Displays running total time on the stopwatch
			timerLabel.setText(stopwatch.displayTime());
		}
	}

	// Creates a listener for when GUI buttons are clicked
	public class ButtonListener implements ActionListener {
	
		/******************************************************************
		Captures button clicks on the GUI
		******************************************************************/
		public void actionPerformed(ActionEvent event) {			

			// Gets the user input and sets it to a string
			String inputMin = minutesField.getText();
			String inputSec = secondsField.getText();
			String inputMil = millisecondsField.getText();

			// Starts the timer when start button is pressed
			if (event.getSource() == startButton) {
				javaTimer.start();
			}

			// Stops the timer when stop button is pressed
			if (event.getSource() == stopButton) {
				javaTimer.stop();
			}

			// Adds good user input to the running timer when add button is pressed
			if (event.getSource() == addButton) {
				if (stopwatch.checkInput(inputMin, inputSec, inputMil)) {
					stopwatch.add(inputMin, inputSec, inputMil);
					timerLabel.setText(stopwatch.displayTime());
				}

				// Guards against bad user input
				else {
					System.out.println(" bad input");
				}

				// Sets user input fields back to 0 after use
				minutesField.setText("0");
				secondsField.setText("0");
				millisecondsField.setText("0");
			}

			// Subtracts good user input from the running timer when sub button is pressed
			if (event.getSource() == subButton) {
				if (stopwatch.checkInput(inputMin, inputSec, inputMil)) {
					stopwatch.sub(inputMin, inputSec, inputMil);
					timerLabel.setText(stopwatch.displayTime());
				}

				// Guards against bad user input
				else {
					System.out.println(" bad input");
				}

				// Sets user input fields back to 0 after use
				minutesField.setText("0");
				secondsField.setText("0");
				millisecondsField.setText("0");
			}

			/**************************************************************
			When save button is pressed, pop up prompts user to enter file
			name
			**************************************************************/
			if (event.getSource() == saveButton) {
				String fileName = JOptionPane.showInputDialog("Enter a file name to save your time.");

				// Saves current time to a file by the name the user entered in pop up
				stopwatch.save(fileName);
			}

			/**************************************************************
			When load button is pressed, pop up prompts user to enter file
			name to retrieve saved time
			**************************************************************/
			if (event.getSource() == loadButton) {
				String fileName = JOptionPane.showInputDialog("Enter the file name to load your time.");

					// Parses contents of file name provided by user, sets to string
					String loadValue = stopwatch.load(fileName);

					// Sets the displayed time to the loaded time from saved file
					timerLabel.setText(loadValue);

					// Sets the timer delay to the new number of milliseconds, so it does not start counting from 0
					timerLabel.setText(stopwatch.displayTime());
			}

			// When reset button is pressed, set displayed time to 00:00:000
			if (event.getSource() == resetButton) {
				timerLabel.setText("00:00:000");

				// Resets the timer delay to 0
				stopwatch.reset();

				// Resets all user input fields to 0
				minutesField.setText("0");
				secondsField.setText("0");
				millisecondsField.setText("0");
			}
		}
	}
}
