/**
 * @filename ZusatzWindow
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco henke
 * @since 27.06.2014
 * @lastModified 29.06.2014
 * @method ZusatzWindow(Kalender)
 * @method zeigeFeiertage(int,boolean)
 * @method zeigeWann(int,int,String)
 * 
 * @description Diese Klasse ist als Fenster fuer alle zusaetzlichen Ausgaben auserhalb der
 * 				Benutzeroberflaeche.
 */
package view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Kalender;

public class ZusatzWindow extends JFrame
{

	/**
	 * @Attribute Kalender kalender
	 * @Attribute Container cpane
	 * @Attribute JTextArea textArea
	 * @Attribute JScrollPane scrollPane
	 */
	private static final long serialVersionUID = 1L;
	
	private Kalender kalender;
	
	private Container cpane = this.getContentPane();
	
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane= new JScrollPane(textArea);
	
	/**
	 * @name ZusatzWindow(Kalender
	 * @param kalender
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter die Instanz der Modellklasse
	 * 				Kalender
	 */
	public ZusatzWindow(Kalender kalender)
	{
		this.kalender=kalender;
		grundeinstellungenFenster();
	}

	/**
	 * @name grundeinstellungenFenster()
	 * @description Diese Methode setzt die Grundeinstellungen fuer das Fenster
	 */
	private void grundeinstellungenFenster()
	{
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollPane.setPreferredSize(new Dimension(600,500));
		textArea.setEditable(false);
		this.setVisible(true);
		this.setResizable(true);
	}
	
	/**
	 * @name zeigeFeiertage(int,boolean)
	 * @param jahr
	 * @param ohneWE
	 * @description Diese Methode kuemmert sich um die Darstellung der Feiertage in einem Jahr.
	 * 				Der Parameter ohneWE bestimmt, ob die Ausgabe ohne Feiertage an einem WE ist
	 * 				oder mit.
	 */
	public void zeigeFeiertage(int jahr,boolean ohneWE)
	{
		JPanel panel = new JPanel();
		ArrayList<String> feiertageListe =kalender.getSortierteFeiertageListe(jahr, ohneWE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(scrollPane);
		cpane.add(panel);
		
		if(ohneWE)
		{
			textArea.append("  Im Jahr "+jahr+" fallen die Feiertage auf folgende Werktage (ohne Wochenende):\n\n");
		}
		else
		{
			textArea.append("  Im Jahr "+jahr+" fallen die Feiertage auf folgende Tage:\n\n");
		}
		for(int i=0;i<feiertageListe.size();i++)
		{
			textArea.append("  "+feiertageListe.get(i)+"\n");
		}
		this.pack();
	}
	
	/**
	 * @name zeigeWann(int,int,String)
	 * @param anfangsjahr
	 * @param endjahr
	 * @param feiertag
	 * @description Diese Methode kuemmert sich um die Ausgabe aller Daten eines Feiertages in einem
	 * 				Jahresintervall.
	 */
	public void zeigeWann(int anfangsjahr, int endjahr, String feiertag)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(scrollPane);
		cpane.add(panel);
		
		textArea.append("  Der Feiertag "+feiertag+" findet zwischen den Jahren "+anfangsjahr+" und "+endjahr+" an folgenden Tagen statt:\n\n");
		for(int i=anfangsjahr;i<=endjahr;i++)
		{
			String datum = kalender.getDatumEinzelnerFeiertag(i, feiertag);
			textArea.append("  "+i+":    "+datum+i+"\n");
		}
		this.pack();
	}
}
