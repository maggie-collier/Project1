import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**********************************************************************
Creates the main panel for the StopWatch GUI.

@author Ben Burger and Jarod Collier
@version 5/22/2018
*********************************************************************/

public class MainPanel extends JPanel {

	public MainPanel () {
		
		setLayout(new GridBagLayout());
		GridBagConstraints loc = new GridBagConstraints();
		
		// Set location for an element and add it
		loc.gridx = 0;
		loc.gridy = 0;
		loc.insets.bottom = 40;
		
		// Change component to an element of the GUI you'd like to add
		// add (/*component*/, loc);
		
	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {	
			
			// if (event.getSource() == /*Button created */ ) {
				
			// }
		}
	}
}
