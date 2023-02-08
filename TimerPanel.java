
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.Integer;
import java.lang.String;
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
	private Timer javaTimer;
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
		
		// Instantiate a stopwatch. Will need ones with different parameters
		stopwatch = new StopWatch();

		// Create a listener
		timerListener = new TimerListener();

		// Create a timer
		javaTimer = new Timer(TIME_DELAY, timerListener);
	
		// Create a button with text
		startButton = new JButton("start");
		stopButton = new JButton("stop");
		addButton = new JButton("add");
		subButton = new JButton("sub");
		saveButton = new JButton("save");
		loadButton = new JButton("load");
		resetButton = new JButton("reset");

		// Create a field for time input
		minutesField = new JTextField (4);
		secondsField = new JTextField (4);
		millisecondsField = new JTextField (4);
		
		// Create a label for the time input
		minutesLabel = new JLabel("minutes");
		secondsLabel = new JLabel("seconds");
		millisecondsLabel = new JLabel("  milliseconds  ");

		// Create a label for the timer
		timerLabel = new JLabel();
		
		// Create a listener to know what buttons are pressed
		ButtonListener buttonListen = new ButtonListener();
		
		// Will need to add the buttons to the listener
		startButton.addActionListener(buttonListen);

		setLayout(new GridBagLayout());
		GridBagConstraints loc = new GridBagConstraints();
        
		loc.insets.bottom = 2;
		
		loc.gridx = 2;
		loc.gridy = 1;
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
		

		setBorder(BorderFactory.createLineBorder(Color.black));
		
		minutesField.setText("0");
		secondsField.setText("0");
		millisecondsField.setText("0");

		timerLabel.setText("timer label");
	} 
	
	private class TimerListener implements ActionListener {
		
		public void actionPerformed (ActionEvent event) {
			
			// stopwatch.add(TIME_DELAY);
			// timerLabel.setText(stopwatch.toString());
		}
	}

	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {			
			
			int minutes, seconds, milliseconds;
			
			if (event.getSource() == startButton) {
				javaTimer.start();
			}
			
		}
	}
}
