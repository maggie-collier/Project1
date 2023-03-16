
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.FlavorException;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.lang.Integer;
import java.lang.String;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**********************************************************************
Creates a stop watch panel.

@author Ben Burger and Jarod Collier
@version 5/22/2018
*********************************************************************/
// class
public class TimerPanel extends JPanel {
	
	// instance variables
	private static final int TIME_DELAY = 10;
	
	private StopWatch stopwatch;

	Timer javaTimer;
	
	private TimerListener timerListener; 
	
	private JButton startButton;
	private JButton stopButton;
	private JButton addButton;
	private JButton subButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton resetButton;
	
	private JTextField minutesField;
	private JLabel minutesLabel;		
	private JTextField secondsField;
	private JLabel secondsLabel;
	private JTextField millisecondsField;
	private JLabel millisecondsLabel;	

	private JLabel timerLabel;
	
	// constructor
	public TimerPanel() {
		// Instantiate a stopwatch (object). Will need ones with different parameters
		stopwatch = new StopWatch();

		// Create a listener
		timerListener = new TimerListener();

		// Create a timer
		javaTimer = new Timer(TIME_DELAY, timerListener);
	
		// Create a button with text
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		addButton = new JButton("Add");
		subButton = new JButton("Sub");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		resetButton = new JButton("Reset");

		// Button qualities
		// startButton.setBackground(Color.green);
		// stopButton.setBackground(Color.RED);
		// resetButton.setBackground(Color.PINK);

		// Create a field for time input
		minutesField = new JTextField (4);
		secondsField = new JTextField (4);
		millisecondsField = new JTextField (4);
		
		// Create a label for the time input
		minutesLabel = new JLabel("(minutes)");
		secondsLabel = new JLabel("(seconds)");
		millisecondsLabel = new JLabel("  (milliseconds)  ");

		// Create a label for the timer
		timerLabel = new JLabel();
		
		// Create a listener to know what buttons are pressed
		ButtonListener buttonListen = new ButtonListener();
		
		// Will need to add the buttons to the listener
		startButton.addActionListener(buttonListen);
		stopButton.addActionListener(buttonListen);
		addButton.addActionListener(buttonListen);
		subButton.addActionListener(buttonListen);
		saveButton.addActionListener(buttonListen);
		loadButton.addActionListener(buttonListen);
		resetButton.addActionListener(buttonListen);

		setLayout(new GridBagLayout());
		GridBagConstraints loc = new GridBagConstraints();

		setBorder(BorderFactory.createLineBorder(Color.black));
        
		loc.gridx = 2;
		loc.gridy = 1;
		loc.insets.bottom = 2;
		// loc.anchor = GridBagConstraints.LINE_END; 
		add(timerLabel, loc);
		
		// Buttons
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
		loc.gridx = 2;
		loc.gridy = 12;
		add(resetButton, loc);

	// Time
		loc.gridx = 2;
		loc.gridy = 3;
		add(minutesField, loc);
		loc.gridx = 2;
		loc.gridy = 4;
		add(minutesLabel, loc);
		loc.gridx = 2;
		loc.gridy = 6;
		add(secondsField, loc);
		loc.gridx = 2;
		loc.gridy = 7;
		add(secondsLabel, loc);	
		loc.gridx = 2;
		loc.gridy = 9;
		add(millisecondsField, loc);
		loc.gridx = 2;
		loc.gridy = 10;
		add(millisecondsLabel, loc);
		
		minutesField.setText("0");
		secondsField.setText("0");
		millisecondsField.setText("0");
		timerLabel.setText("00:00:000");
	} 
	
	private class TimerListener implements ActionListener {
		
		public void actionPerformed (ActionEvent event) {
			
			stopwatch.add(TIME_DELAY);
			timerLabel.setText(stopwatch.displayTime(false));
			
			// timerLabel.setText(stopwatch.displayTime());
			
			
		}
	}

	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {			
			String inputMin = minutesField.getText();
			String inputSec = secondsField.getText();
			String inputMil = millisecondsField.getText();

		
			if (event.getSource() == startButton) {
				javaTimer.start();
			}

			if (event.getSource() == stopButton) {
				javaTimer.stop();
			}

			if (event.getSource() == addButton) {
				if (stopwatch.checkInput(inputMin, inputSec, inputMil)) {
					stopwatch.add(inputMin, inputSec, inputMil);
					timerLabel.setText(stopwatch.displayTime(false));
				}
				else {
					System.out.println(" bad input");
				}
				minutesField.setText("0");
				secondsField.setText("0");
				millisecondsField.setText("0");
			}
		// 

			if (event.getSource() == subButton) {
				if (stopwatch.checkInput(inputMin, inputSec, inputMil)) {
					stopwatch.sub(inputMin, inputSec, inputMil);
					timerLabel.setText(stopwatch.displayTime(false));
				}
				else {
					System.out.println(" bad input");
				}
				minutesField.setText("0");
				secondsField.setText("0");
				millisecondsField.setText("0");
			}

			if (event.getSource() == saveButton) {
				stopwatch.save();
			}

			if (event.getSource() == loadButton) {
				try {
					String loadValue = stopwatch.load();
					timerLabel.setText(loadValue);
					stopwatch.loadTime(loadValue, TIME_DELAY);
					timerLabel.setText(stopwatch.displayTime(true));
					// stopwatch.add(TIME_DELAY);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (event.getSource() == resetButton) {
				// event.getSource() == stopButton;
				timerLabel.setText("00:00:000");
				stopwatch.reset();
				minutesField.setText("0");
				secondsField.setText("0");
				millisecondsField.setText("0");
			}
		}
	}
}
