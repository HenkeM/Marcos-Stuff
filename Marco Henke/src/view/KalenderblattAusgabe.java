/**
 * @filename KalenderblattAusgabe
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 19.06.2014
 * @lastModified 29.06.2014
 * @method KalenderblattAusgabe(Kalender)
 * @method getMonatsblatt(int,int,boolean,boolean)
 * @method getJahresblatt(int,boolean,boolean)
 * 
 * @description Diese Klasse realisiert das DefaultStyledDocument, in dem die Ausgabe des
 * 				Kalenders dargestellt wird.
 */
package view;

import java.util.ArrayList;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;

import model.Kalender;
import model.Konstanten;
import model.Zelle;

public class KalenderblattAusgabe extends DefaultStyledDocument
{
	/**
	 * @Attribute Kalender kalender
	 */
	private static final long serialVersionUID = 1L;
	Kalender kalender;
	
	/**
	 * @name KalenderblattAusgabe(Kalender)
	 * @param kalender
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter die Instanz der Modelklasse
	 * 				Kalender uebergeben.
	 */
	protected KalenderblattAusgabe(Kalender kalender)
	{
		this.kalender=kalender;
	}
	
	/**
	 * @name getMonatsblatt(int, int, boolean, boolean)
	 * @param jahr
	 * @param monat
	 * @param modus
	 * @param geburtstagEintragen
	 * @return KalenderblattAusgabe
	 * @description Die Methode traegt ein Monatsblatt in die Instanz dieser Klasse ein und
	 * 				gibt diese Instanz danach zurueck.
	 */
	protected KalenderblattAusgabe getMonatsblatt(int jahr, int monat, boolean modus, boolean geburtstagEintragen)
	{
		erstelleMonatsblatt(jahr, monat, modus, geburtstagEintragen);
		return this;
	}
	
	/**
	 * @name getJahresblatt(int, boolean, boolean)
	 * @param jahr
	 * @param modus
	 * @param geburtstagEintragen
	 * @return KalenderblattAusgabe
	 * @description Die Methode traegt ein Jahresblatt in die Instanz dieser Klasse ein und
	 * 				gibt diese Instanz danach zurueck.
	 */
	protected KalenderblattAusgabe getJahresblatt(int jahr, boolean modus, boolean geburtstagEintragen)
	{
		for(int i=1;i<=12;i++)
		{
			erstelleMonatsblatt(jahr, i, modus, geburtstagEintragen);
		}
		return this;
	}
	
	/**
	 * @name erstelleMonatsblatt(int, int, boolean, boolean)
	 * @param jahr
	 * @param monat
	 * @param modus
	 * @param geburtstagEintragen
	 * @description Diese Methode kuemmert sich um das formatieren und eintragen eines Monatsblattes
	 * 				in die Instanz dieser Klasse.
	 */
	private void erstelleMonatsblatt(int jahr, int monat, boolean modus, boolean geburtstagEintragen)
	{
		//initialisieren aller benoetigten Variablen
		ArrayList<Zelle> monatsblattDaten= kalender.getMonatsblatt(jahr, monat, modus, geburtstagEintragen);
		ArrayList<String> aktuelleFeiertage = new ArrayList<String>();
		ArrayList<String> aktuelleGeburtstage = new ArrayList<String>();

		int wochentagezaehler =0;
		
		try
		{
			//einfuegen des Monatsheaders
			int offset = this.getLength();
			this.insertString(offset,(getKopfzeileMonatsblatt(jahr,monat)+"\n"),Konstanten.getStandardSet());
			//einfuegen aller Zellen des Monatsblattes
			for(int i=0;i<monatsblattDaten.size();i++)
			{
				fuelleZelle(monatsblattDaten.get(i),aktuelleFeiertage,aktuelleGeburtstage);
				wochentagezaehler++;
				if(wochentagezaehler==7)
				{
					wochentagezaehler=0;
					offset = this.getLength();
					this.insertString(offset,"\n",Konstanten.getStandardSet());
				}
			}
			offset = this.getLength();
			this.insertString(offset,("\n\n"),Konstanten.getStandardSet());
			//einfuegen aller aktuellen Feiertage
			if(modus)
			{
				benachrichtigungenEinfuegen(aktuelleFeiertage,Konstanten.getFeiertagSet());
			}
			offset = this.getLength();
			this.insertString(offset, "\n\n", Konstanten.getStandardSet());
			//einfuegen aller Geburtstage
			if(geburtstagEintragen)
			{
				benachrichtigungenEinfuegen(aktuelleGeburtstage,Konstanten.getGeburtstagSet());
			}
			offset = this.getLength();
			this.insertString(offset, "\n\n", Konstanten.getStandardSet());
		} 
		catch (BadLocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @name benachrichtigungenEinfuegen(ArrayList<String>,SimpleAttributeSet)
	 * @param eingabeliste
	 * @param set
	 * @throws BadLocationException
	 * @description Diese Methode kuemmert sich um das Eintragen aller Strings aus der uebergebenen
	 * 				ArrayList<String> mit dem uebergebenen SimpleAttributeSet.
	 */
	private void benachrichtigungenEinfuegen(ArrayList<String>eingabeliste,SimpleAttributeSet set) throws BadLocationException
	{
		for(int i=0;i<eingabeliste.size();i++)
		{
			int offset = this.getLength();
			this.insertString(offset,(eingabeliste.get(i)+"\n"),set);
		}
	}
	
	/**
	 * @name fuelleZelle(Zelle, ArrayList<String>, ArrayList<String>)
	 * @param zelle
	 * @param aktuelleFeiertage
	 * @param aktuelleGeburtstage
	 * @throws BadLocationException
	 * @description Diese Methode formatiert eine einzelne Zelle, traegt sie in diese Instanz der
	 * 				Klasse ein und extrahiert zusaetzliche Informationen, die in die beiden uebergebenen
	 * 				ArrayList<String> eingetragen werden.
	 */
	private void fuelleZelle(Zelle zelle, ArrayList<String> aktuelleFeiertage, ArrayList<String> aktuelleGeburtstage) throws BadLocationException
	{
		//berechnen der Menge an Leerzeichen in der Zelle.
		int filler;
		if(!zelle.getFeiertagsname().isEmpty())
		{
			filler=((kalender.getZellenbreite()-zelle.getInhalt().length())/2)-1;
		}
		else
		{
			filler=((kalender.getZellenbreite()-zelle.getInhalt().length())/2);
		}
		//einfuegen der Leerzeichen am Zellenanfang
		for(int i=0;i<filler;i++)
		{
			int offset = this.getLength();
			this.insertString(offset, " ", Konstanten.getStandardSet());
		}
		//einfuegen des Inhaltes der Zelle
		if(!zelle.getFeiertagsname().isEmpty())
		{
			int offset = this.getLength();
			this.insertString(offset, Konstanten.FEIERTAGSSYMBOL, Konstanten.getFeiertagSet());
			fuelleZelleGeburtstage(zelle,Konstanten.getFeiertagSet(),aktuelleGeburtstage);
			offset = this.getLength();
			this.insertString(offset, Konstanten.FEIERTAGSSYMBOL, Konstanten.getFeiertagSet());
			filler=filler+2+zelle.getInhalt().length();
			aktuelleFeiertage.add(zelle.getInhalt()+". : "+zelle.getFeiertagsname());
		}
		else
		{
			fuelleZelleGeburtstage(zelle,Konstanten.getStandardSet(),aktuelleGeburtstage);
			filler=filler+zelle.getInhalt().length();
		}
		//einfuegen von Leerzeichen bis die Zelle voll ist
		while(filler<kalender.getZellenbreite())
		{
			int offset = this.getLength();
			this.insertString(offset, " ", Konstanten.getStandardSet());
			filler++;
		}
	}
	
	/**
	 * @name fuelleZelleGeburtstage(Zelle, SimpleAttributeSet, ArrayList<String>)
	 * @param zelle
	 * @param sonstigesSet
	 * @param aktuelleGeburtstage
	 * @throws BadLocationException
	 * @description Diese Methode kuemmert sich um das Formatieren von Geburtstagen fuer die Ausgabe.
	 */
	private void fuelleZelleGeburtstage(Zelle zelle, SimpleAttributeSet sonstigesSet, ArrayList<String> aktuelleGeburtstage) throws BadLocationException
	{
		//einfaerbung fuer Geburtstag
		if(!zelle.getGeburtstag().isEmpty())
		{
			int offset = this.getLength();
			this.insertString(offset, zelle.getInhalt(), Konstanten.getGeburtstagSet());
			for(int i=0;i<zelle.getGeburtstag().size();i++)
			{
				aktuelleGeburtstage.add(zelle.getGeburtstag().get(i)+" feiert am "+zelle.getInhalt()+"."+zelle.getMonatszahl()+"."+" den "+zelle.getGeburtstagAlter().get(i)+". Geburtstag.");
			}
		}
		else
		{
			int offset = this.getLength();
			this.insertString(offset, zelle.getInhalt(), sonstigesSet);
		}
	}
	
	/**
	* @name getKopfzeileMonatsblatt
	* @param jahr
	* @param monat
	* @return String
	* @description	Funktion, um die Kopfzeile eines Monatsblattes auszugeben.
	*				Dieses erfodert die Uebergabe des Monats als Integer und
	*				liefert die Kopfzeile komplett als String zurueck.
	*
	* 				************ Mai 2014 ************
	* 
	*/ 
	private String getKopfzeileMonatsblatt(int jahr, int monat)
	{
		//Initialisierung der lokalen Variablen der Methode 
		String header="";
		int breite=7*kalender.getZellenbreite();
		String name= kalender.getMonatsname()[monat];
		name = " "+name+" "+jahr+" ";
		int filling = (breite-name.length())/2;	//bestimmen der Position der Ueberschrift
		
		//erstellen des Headers
		for(int i=0;i<filling;i++)
		{
			header = header+"*";
		}
		header = header+name;
		while(header.length()<breite)
		{
			header = header+"*";
		}
		//Rueckgabe des Headers als String
		return header;
	}
}
