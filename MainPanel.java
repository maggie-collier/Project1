import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/********************************************************************
Creates the main panel for the StopWatch GUI.
********************************************************************/
public class MainPanel extends JPanel {

	// Creates 3 separate timer panels on the main panel
	private static TimerPanel timerPanel1;
	private static TimerPanel timerPanel2;
	private static TimerPanel timerPanel3;

	// Creates a universal pause button on the main panel
	private JButton pauseButton;

	/******************************************************************
	Constructor for the main panel
	******************************************************************/
	public MainPanel () {
		timerPanel1 = new TimerPanel();
		timerPanel2 = new TimerPanel();
		timerPanel3 = new TimerPanel();

		// Creates a button with text
		pauseButton = new JButton("PAUSE");

		// Creates loc variable to arrange elements on the panel
		setLayout(new GridBagLayout());
		GridBagConstraints loc = new GridBagConstraints();

		// Creates listener to know if pause button is pressed
		ButtonListener buttonListen = new ButtonListener();
		pauseButton.addActionListener(buttonListen);
		
		timerPanel1.setBackground(Color.LIGHT_GRAY);
		timerPanel2.setBackground(Color.LIGHT_GRAY);
		timerPanel3.setBackground(Color.LIGHT_GRAY);

		// Setting location for first timer panel
		loc.gridx = 0;
		loc.gridy = 0;
		loc.insets.bottom = 10;
		add(timerPanel1, loc);
		
		// Setting location for second timer panel beneath first
		loc.gridx = 0;
		loc.gridy = 40;
		loc.insets.bottom = 10;
		add(timerPanel2, loc);

		// Setting location for third timer panel beneath second
		loc.gridx = 0;
		loc.gridy = 80;
		loc.insets.bottom = 10;
		add(timerPanel3, loc);

		// Setting location for pause button beneath third timer panel
		loc.gridx = 0;
		loc.gridy = 90;
		loc.insets.bottom = 10;
		add(pauseButton, loc);
	}
	
	/******************************************************************
	Suspend function to pause all timers without use of the GUI
	******************************************************************/
	public static void suspend(boolean paused) {
		if (paused == true) {
			timerPanel1.javaTimer.stop();
			timerPanel2.javaTimer.stop();
			timerPanel3.javaTimer.stop();	
		}
	}

	// Creates listener for when pause button is clicked
	private class ButtonListener implements ActionListener {
		
	/******************************************************************
	Captures button click on the GUI
	******************************************************************/
		public void actionPerformed(ActionEvent event) {
	
			// Pause all timers when pause button is clicked
			if (event.getSource() == pauseButton) {
				timerPanel1.javaTimer.stop();
				timerPanel2.javaTimer.stop();
				timerPanel3.javaTimer.stop();	
			}
		}
	}
}
