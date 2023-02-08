import javax.swing.JFrame;
 
/**********************************************************************
Creates the StopWatch GUI.

@author Ben Burger and Jarod Collier
@version 5/22/2018
*********************************************************************/

public class StopwatchGUI
{
    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("Stopwatches");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        MainPanel panel = new MainPanel();        
        frame.getContentPane().add(panel);
        frame.setSize (800,800);
        frame.setVisible(true);
    }
}
