package start;

import java.util.Random;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.KalenderInterface;


/**
 * Beschreibung: Diese Klasse enthaelt die main() Methode des Programmes, initialisiert den Controller
 * KalenderInterface mit den benoetigten Parametern und ruft die start() Methode auf, um das Programm
 * zu starten.
 * 
 * @author Marco Henke
 * @since
 * @last 28.06.2014
 *
 */
public class Start
{
	public static void main(String[] args)
	{
		KalenderInterface kalenderInterface = new KalenderInterface(6, 200,true);
	/*	JTextField xField = new JTextField(5);
		xField.setText("5");
	    JTextField yField = new JTextField(5);
	    yField.setText("10");

	      JPanel myPanel = new JPanel();
	      myPanel.add(new JLabel("x:"));
	      myPanel.add(xField);
	      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	      myPanel.add(new JLabel("y:"));
	      myPanel.add(yField);

	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	         System.out.println("x value: " + xField.getText());
	         System.out.println("y value: " + yField.getText());
	      }*/
	}

}
