import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

import javax.swing.*;

/**********************************************************************
Creates the main panel for the StopWatch GUI.

@author Ben Burger and Jarod Collier
@version 5/22/2018
*********************************************************************/

public class MainPanel extends JPanel {

	private TimerPanel timerPanel1;
	private TimerPanel timerPanel2;
	private TimerPanel timerPanel3;

	private JButton pauseButton;

	public MainPanel () {
		System.out.println("Inside MainPanel constructor");

		timerPanel1 = new TimerPanel();
		System.out.println("panel 1 made");

		timerPanel2 = new TimerPanel();
		System.out.println("panel 2 made");

		timerPanel3 = new TimerPanel();
		System.out.println("panel 3 made");

		pauseButton = new JButton("PAUSE");

		setLayout(new GridBagLayout());
		GridBagConstraints loc = new GridBagConstraints();

		ButtonListener buttonListen = new ButtonListener();
		pauseButton.addActionListener(buttonListen);
		
		// Set location for an element and add it
		loc.gridx = 0;
		loc.gridy = 0;
		loc.insets.bottom = 10;
		add(timerPanel1, loc);
		// Change component to an element of the GUI you'd like to add
		// add (/*component*/, loc);
		
		loc.gridx = 0;
		loc.gridy = 40;
		loc.insets.bottom = 10;
		add(timerPanel2, loc);
		loc.gridx = 0;
		loc.gridy = 80;
		loc.insets.bottom = 10;
		add(timerPanel3, loc);
		loc.gridx = 0;
		loc.gridy = 90;
		loc.insets.bottom = 10;
		add(pauseButton, loc);
	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {	
			
			// if (event.getSource() == /*Button created */ ) 
			if (event.getSource() == pauseButton) {
				System.out.println("press pause");
				timerPanel1.javaTimer.stop();
				timerPanel2.javaTimer.stop();
				timerPanel3.javaTimer.stop();	
				// pause all timers
			}
		}
	}
}
