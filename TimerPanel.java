
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

public class TimerPanel extends JPanel {
	
	private static final int TIME_DELAY = 10;
	
	private StopWatch stopwatch;
	private Timer javaTimer;
	private TimerListener timerListener; 
	
	private JButton startButton;
	
	private JTextField minutesField;
	
	private JLabel timerLabel;
	
	public TimerPanel () {
		
		// Instantiate a stopwatch. Will need ones with different parameters
		stopwatch = new StopWatch();

		// Create a listener
		timerListener = new TimerListener();

		// Create a timer
		javaTimer = new Timer(TIME_DELAY, timerListener);
	
		// Create a button with text
		startButton = new JButton("start");

		// Create a field for input
		minutesField = new JTextField (4);
		
		// Create a label
		timerLabel = new JLabel();
		
		// Create a listener to know what buttons are pressed
		ButtonListener buttonListen = new ButtonListener();
		
		// Will need to add the buttons to the listener
		startButton.addActionListener(buttonListen);

		setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();
        
        loc.insets.bottom = 2;
        
        loc.gridx = 1;
        loc.gridy = 0;
        // loc.anchor = GridBagConstraints.LINE_END; 
        add(timerLabel, loc);
         
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        minutesField.setText("0");
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
