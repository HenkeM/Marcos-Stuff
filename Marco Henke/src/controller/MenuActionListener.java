/**
 * @filename MenuActionListener
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 22.06.2014
 * @lastModified 29.06.2014
 * @method MenuActionListener(KalenderInterface kalenderInterface)
 * @method setMenuLeiste(MenuLeisteKalender menuLeiste)
 * @method actionPerformed(ActionEvent e)
 * 
 * @description Diese Klasse ist ein ActionListener, der die Ausgabe des Kalenders in der Benutzeroberflaeche
 * 				erneuert wann immer actionPerformed(ActionEvent) aufgerufen wird. Außerdem stellt er mit
 * 				der Methode refresh() eine manuelle Moeglichkeit zum erneuern der Ausgabe zur Verfuegung.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Kalender;

import view.KalenderFileFilter;
import view.MenuLeisteKalender;

public class MenuActionListener implements ActionListener
{
	/**
	 * @Attribute MenuLeisteKalender menuLeiste
	 * @Attribtue KalenderRefreshActionListener refreshAL
	 * @Attribute Benutzeroberflaeche gui
	 * @Attribute Kalender kalender
	 * @Attribute SpeicherFunktionen speicherFunktionen
	 */
	private MenuLeisteKalender menuLeiste;
	private KalenderRefreshActionListener refreshAL;
	private Kalender kalender;
	private KalenderInterface ki;
	
	/**
	 * @name MenuActionListener(KalenderInterface)
	 * @param kalenderInterface : 	Schnittstelle fuer alle anderen MVC Instanzen.
	 * @description Der Konstruktor der Klasse. Holt sich alle Benoetigten Attribute aus dem KalenderInterface.
	 */
	public MenuActionListener(KalenderInterface kalenderInterface)
	{
		this.kalender=kalenderInterface.getKalender();
		this.refreshAL=kalenderInterface.getRefreshAL();
		this.ki=kalenderInterface;
	}
	
	/**
	 * @name setMenuLeiste(MenuLeisteKalender)
	 * @param menuLeiste : 	Die zu beobachtende MenuLeiste
	 * @description Setter fuer das Attribut menuLeiste.
	 */
	public void setMenuLeiste(MenuLeisteKalender menuLeiste)
	{
		this.menuLeiste=menuLeiste;
	}
	
	/**
	 * @name actionPerformed(ActionEvent)
	 * @param e : 	Vom Observable uebergebenes Event.
	 * @description Die Implementierung der Methode actionPerformed(Event) vom der Superclass ActionListener.
	 */
	
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		//Holt das Objekt, dass den Listener aufgerufen hat.
		Object eingabe = event.getSource();
		//wenn die Option zum Kalender speichern gewaehlt wurde
		if(eingabe==menuLeiste.getMenuDateiSpeichern())
		{
			//initialisieren von File und FileChooser
			File file = null;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setFileFilter(new KalenderFileFilter(".rtf","Rich Text Format"));
			//auswaehlen der Datei
			int auswahl = fileChooser.showSaveDialog(ki.getFenster());
			file = fileChooser.getSelectedFile();
			if(auswahl==JFileChooser.APPROVE_OPTION)
			{
				//beschreiben der ausgewaehlten Datei mit den Kalenderdaten.
				ki.getSpeicherFunktionen().kalenderSpeichernRTF(file);
			}
		}
		//wenn die Option zum Kalender laden gewaehlt wurde
		if(eingabe==menuLeiste.getMenuDateiLaden())
		{
			//initialisieren von File und FileChooser
			File file = null;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setFileFilter(new KalenderFileFilter(".csv","Comma-separated values"));
			//einlesen der Datei
			int auswahl = fileChooser.showOpenDialog(ki.getFenster());
			file = fileChooser.getSelectedFile();
			if(auswahl==JFileChooser.APPROVE_OPTION)
			{
				//Aufruf der Methode zum verarbeiten der Datei
				kalender.geburtstageEinlesen(file);
				refreshAL.refresh();
			}
		}
		//wenn die Option zum beenden gewaehlt wurde
		if(eingabe==menuLeiste.getMenuDateiBeenden())
		{
			ki.getFenster().dispose();
		}
		//wenn die Option zum darstellen der Feiertage des Jahres gewaehlt wurde
		if(eingabe==menuLeiste.getMenuKalenderFeiertage())
		{
			ki.getFenster().initialisiereZusatzWindow();
			int jahr=(int) ki.getFenster().getJahrComboBox().getSelectedItem();
			ki.getFenster().getZusatzWindow().zeigeFeiertage(jahr, false);
		}
		//wenn die Option zum darstellen der Feiertage des Jahres ohne WE gewaehlt wurde
		if(eingabe==menuLeiste.getMenuKalenderFeiertageOhne())
		{
			ki.getFenster().initialisiereZusatzWindow();
			int jahr=(int) ki.getFenster().getJahrComboBox().getSelectedItem();
			ki.getFenster().getZusatzWindow().zeigeFeiertage(jahr, true);
		}
		//wenn die Option zum darstellen der Daten eines Feiertages in einem Jahresintervall
		//gewaehlt wurde
		if(eingabe==menuLeiste.getMenuKalenderWann())
		{
			methodeMenuKalenderWann();			
		}
		//wenn die Option zum aendern des Look&Feel auf "Windows" gewaehlt wurde
		if(eingabe==menuLeiste.getMenuLookFeelWindows())
		{
			changeLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		//wenn die Option zum aendern des Look&Feel auf "Metal" gewaehlt wurde
		if(eingabe==menuLeiste.getMenuLookFeelMetal())
		{
			String newLookAndFeel=UIManager.getCrossPlatformLookAndFeelClassName();
			changeLookAndFeel(newLookAndFeel);
		}
		//wenn die Option zum aendern des Look&Feel auf "Motif" gewaehlt wurde
		if(eingabe==menuLeiste.getMenuLookFeelMotif())
		{
			changeLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		}
		//wenn die Option zum darstellen der Softwareinformationen gewaehlt wurde
		if(eingabe==menuLeiste.getMenuHilfeAutor())
		{
			methodeMenuHilfeAuthor();
		}
		//wenn die Option zum darstellen des Handbuchs gewaehlt wurde
		if(eingabe==menuLeiste.getMenuHilfeHelp())
		{
			methodeMenuHilfeHilfe();
		}
	}
	
	/**
	 * @name methodeMenuHilfeAuthor()
	 * @description Methode zum Darstellen der Softwareinformationen
	 */
	private void methodeMenuHilfeAuthor()
	{
		JOptionPane.showMessageDialog(null, "Author : Marco Henke\n" +
											"Fertiggestellt am : 29.06.2014\n" +
											"Version : 1.0");
	}

	/**
	 * @name methodeMenuHilfeHilfe()
	 * @description Methode zum Darstellen des Handbuchs
	 */
	private void methodeMenuHilfeHilfe()
	{
		JOptionPane.showMessageDialog(null, "Menuoptionen:\n\n" +
											"unter Menu:\n\n" +
											"lade Geburtstage          - ermöglicht das Laden einer CSV-Datei mit Geburtstagen,\n" +
											"                                             die danach im Kalender dargestellt werden können.\n" +
											"                                             Erneutes Laden überschreibt die vorherige Datei.\n" +
											"speicher Kalender        - Ermöglicht das Speichern der aktuellen Ausgabe in einer\n" +
											"                                             RTF-Datei.\n" +
											"beenden                          - Schließt das Programm.\n\n" +
											"unter Kalender:\n" +
											"Feiertage                         - Darstellung aller Feiertage des aktuell ausgewählten Jahres\n" +
											"                                             in einem neuen Fenster.\n" +
											"Feiertage, ohne Sa/SO - Darstellung aller Feiertage des aktuell ausgewählten Jahres\n" +
											"                                             ohne Sa/So in einem neuen Fenster.\n" +
											"Wann                                - Öffnet ein Dialogfenster an dessen Ende alle Daten des gewünschten\n" +
											"                                             Feiertages im ausgewählten Jahresintervall in einem neuem Fenster\n" +
											"                                             ausgegeben werden.\n\n" +
											"unter Look & Feel:\n" +
											"Windows                         - ändert das Design des Kalenders zu \"Windows\".\n" +
											"Metal                                - ändert das Design des Kalenders zu \"Metal\".\n" +
											"Motif                                 - ändert das Design des Kalenders zu \"Motif\".\n\n" +
											"unter Hilfe:\n" +
											"Author/in                         - öffnet ein Dialogfenster mit den Namen des Authors, Fertigstellungsdatu\n" +
											"                                             der Software und dessen Versionsnummer.\n" +
											"Hilfe                                  - Öffnet dieses Handbuch in einem neuen Fenster.");
	}

	/**
	 * @name methodeMenuKalenderWann()
	 * @description Methode zum durchfuehren aller Eingabeaufforderungen ueber um JOptionPanes
	 * 				zum berechnen der Methode zeigeWann(int, int, String) des ZusatzWindow;
	 */
	private void methodeMenuKalenderWann()
	{
		//initialisieren aller benoetigten, lokalen Variablen
		HashMap<String,String> feiertageListe=kalender.getFeiertageListe(0);
		int erstesJahr=0;
		int zweitesJahr=0;
		String feiertag="";
		boolean errorErstesJahr=true;
		boolean errorZweitesJahr=true;
		boolean errorFeiertag=true;
		boolean abbruch=false;
		
		//Eingabeaufforderung fuer das erste Jahr
		String usereingabe=JOptionPane.showInputDialog("Zur Berechnung aller Daten eines Feiertages wird ein Interval von Jahren benoetigt.\n" +
														"Geben sie bitte das erste Jahr ein.");
		//while-error-try-Schleife fuer das ersteJahr
		while(errorErstesJahr)
		{
			//wenn der Button "Abbruch" geklickt wurde
			if(usereingabe==null)
			{
				errorErstesJahr=false;
				errorZweitesJahr=false;
				errorFeiertag=false;
				abbruch=true;
				break;
			}
			try
			{
				//einlesen und ueberpruefen, ob die Eingabe eine Ganzzahl ist.
				erstesJahr=Integer.parseInt(usereingabe);
				//ueberprueft, ob die Eingabe eine positive Zahl ist.
				if(erstesJahr<0)
				{
					throw new Exception();
				}
				else
				{
					errorErstesJahr=false;
				}
			}
			//erneute Abfrage bei fehlerhafter Eingabe.
			catch(Exception e)
			{
				usereingabe=JOptionPane.showInputDialog("Ihre Eingabe war fehlerhaft.\n" +
														"Geben sie bitte nur Jahreszahlen ab dem Jahr 0 oder spaeter ein.");
			}
		}
		//ueberspringen bei Abbruch
		if(!abbruch)
		{
			//Eingabeaufforderung fuer das zweite Jahr
			usereingabe=JOptionPane.showInputDialog("Geben sie bitte das zweite Jahr ein.");
		}
		//while-error-try-Schleife fuer das zweite Jahr
		while(errorZweitesJahr)
		{
			//wenn der Button "Abbruch" geklickt wurde
			if(usereingabe==null)
			{
				errorZweitesJahr=false;
				errorFeiertag=false;
				abbruch=true;
				break;
			}
			try
			{
				//einlesen und ueberpruefen, ob die Eingabe eine Ganzzahl ist.
				zweitesJahr=Integer.parseInt(usereingabe);
				//ueberprueft, ob die Eingabe eine positive Zahl ist.
				if(zweitesJahr<0)
				{
					throw new Exception();
				}
				else
				{
					errorZweitesJahr=false;
				}
			}
			//erneute Abfrage bei fehlerhafter Eingabe.
			catch(Exception e)
			{
				usereingabe=JOptionPane.showInputDialog("Ihre Eingabe war fehlerhaft.\n" +
														"Geben sie bitte nur Jahreszahlen ab dem Jahr 0 oder spaeter ein.");
			}
		}
		//ueberspringen bei Abbruch
		if(!abbruch)
		{
			//Eingabeaufforderung fuer das erste Jahr
			usereingabe=JOptionPane.showInputDialog("Geben sie bitte den Namen des Feiertages ein.\n" +
													"Der Name muss eindeutig als ein Name im Kalender identifizierbar sein.\n" +
													"Die Eingabe unterscheided Groß-/Kleinschreibung." +
													"Bei Problemen schauen sie bitte in einem Kalenderblatt mit Feiertagen nach.");
		}
		//while-error-try-Schleife fuer den Feiertag
		while(errorFeiertag)
		{
			//wenn der Button "Abbruch" geklickt wurde
			if(usereingabe==null)
			{
				errorFeiertag=false;
				abbruch=true;
				break;
			}
			try
			{
				int funde=0;
				//ueberprueft fuer alle bekannten Feiertage, ob die Eingabe einem Feiertagsnamen
				//aehnlich ist
				for (String i: feiertageListe.keySet())
				{
					if(feiertageListe.get(i).contains(usereingabe))
					{
						feiertag=feiertageListe.get(i);
						funde++;
					}
				}
				//Wird false, wenn gar kein oder mehr als ein auf die Eingabe passender Feiertag 
				//gefunden wurde.
				if(funde==1)
				{
					errorFeiertag=false;
				}
				else
				{
					throw new Exception();
				}
			}
			//erneute Abfrage bei fehlerhafter Eingabe.
			catch(Exception e)
			{
				usereingabe=JOptionPane.showInputDialog("Ihre Eingabe konnte nicht eindeutig zugeordnet werden.\n" +
														"Geben sie einen eindeutigen Feiertagsnamen ein.\n" +
														"Achten sie auf Groß-/Kleinschreibung.");
			}
		}
		//ueberspringen bei Abbruch
		if(!abbruch)
		{
			//sortieren der Jahreseingaben
			if(erstesJahr>zweitesJahr)
			{
				int buffer=erstesJahr;
				erstesJahr=zweitesJahr;
				zweitesJahr=buffer;
			}
			//Aufrufen der Methoden zur Darstellung der Ausgabe
			ki.getFenster().initialisiereZusatzWindow();
			ki.getFenster().getZusatzWindow().zeigeWann(erstesJahr, zweitesJahr, feiertag);
		}
	}

	/**
	 * @name changeLookAndFeel(String)
	 * @param newLookAndFeel
	 * @description Methode zum wechseln des Look&Feel
	 */
	private void changeLookAndFeel(String newLookAndFeel)
	{
		try
		{
			UIManager.setLookAndFeel(newLookAndFeel);
			SwingUtilities.updateComponentTreeUI(ki.getFenster());
			ki.getFenster().pack();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
