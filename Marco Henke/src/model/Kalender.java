
/**
 * @filename Kalender
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 15.05.2014
 * @lastModified 28.06.2014
 * @method Kalender(int, boolean)
 * @method setWochenanfangMontag(boolean)
 * @method getZellenbreite()
 * @method getMonatsname()
 * @method getWochenanfangMontag()
 * @method geburtstageEinlesen(File)
 * @method getMonatsblatt(int, int, boolean,boolean) 
 * @method getSortierteFeiertageListe(int, boolean)
 * @method getFeiertageListe(int)
 * @method getDatumEinzelnerFeiertag(int, String)
 * 
 * @description Diese Klasse ist das Kernstueck des Kalenders. Sie enthaelt Funktionen zum berechnen der
 * 				Monatsblaetter und des Jahreskalenders, sowie verschiedene Moeglichkeiten zur Ausgabe
 * 				der Feiertage.
 */
package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;


public class Kalender
{
	/**
	 * @Attribute int zellenbreite
	 * @Attribute String[] monatsname
	 * @Attribute String[] wochentageKurz
	 * @Attribute int[][] monatslaenge
	 * @Attribute boolean wochenanfangMonat
	 * @Attribute KalenderFunktionen kalenderFunktionen
	 * @Attribute ArrayList<Geburtstag> geburtstage
	 */
	private int zellenbreite;
	private final String[] monatsname;
	private final String[] wochentageKurz;
	private final int[][] monatslaenge;
	private boolean wochenanfangMontag;
	private KalenderFunktionen kalenderFunktionen= new KalenderFunktionen();
	private ArrayList<Geburtstag> geburtstage = new ArrayList<Geburtstag>();
	
	/**
	 * @name Kalender(int, boolean)
	 * @param zellenbreite
	 * @param wochenanfangMontag
	 * @description Konstruktor der Klasse. Holt sich wichtige Rahmeninformationen aus den Konstanten
	 * 				und setzt Standardwerte.
	 */
	public Kalender(int zellenbreite, boolean wochenanfangMontag)
	{
		this.monatsname=Konstanten.MONATSNAME;
		this.wochentageKurz=Konstanten.WOCHENTAGEKURZ;
		this.monatslaenge=Konstanten.MONATSLAENGE;
		this.wochenanfangMontag=wochenanfangMontag;
		this.zellenbreite=zellenbreite;
	}
	
	/**
	 * @name setWochenanfangMontag(boolean)
	 * @param wochenanfangMontag
	 * @description Setter fuer das Attribut wochenanfangMontag
	 */
	public void setWochenanfangMontag(boolean wochenanfangMontag)
	{
		this.wochenanfangMontag=wochenanfangMontag;
	}
	
	/**
	 * @name getZellenbreite()
	 * @return int
	 * @description Getter fuer das Attribut zellenbreite.
	 */
	public int getZellenbreite()
	{
		return zellenbreite;
	}
	
	/**
	 * @name getMonatsname
	 * @return String[]
	 * @description Getter für das Attribut Monatsname.
	 */
	public String[] getMonatsname()
	{
		return monatsname;
	}
	
	/**
	 * @name getWochenanfangMontag()
	 * @description Getter fuer das Attribut wochenanfangMontag
	 */
	public boolean getWochenanfangMontag()
	{
		return wochenanfangMontag;
	}
	
	/**
	 * @name geburtstageEinlesen(File)
	 * @param file
	 * @return boolean
	 * @description Diese Methode realisiert das Einlesen der Geburtstage aus einer als Parameter
	 * 				uebergebenen CSV-Datei. Gibt TRUE wieder, wenn das Einlesen erfolgreich war,
	 * 				ansonsten FALSE.
	 */
	public boolean geburtstageEinlesen(File file)
	{
		//Loeschen der alten Feiertage
		geburtstage.clear();
		try
		{
			//uebergeben der File in einen BufferedReader und einlesen der ersten Zeile
			FileReader fileReader=new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String eingabe=reader.readLine();
			//solange noch Zeilen eingelesen werden konnten
			while(!(eingabe==null))
			{
				String[] tokens = eingabe.split(";");
				Date geburtsdatum=new SimpleDateFormat("dd.MM.yyyy").parse(tokens[0]);
				String name =tokens[1]+" "+tokens[2];
				geburtstage.add(new Geburtstag(geburtsdatum, name));
				eingabe=reader.readLine();	
			}
			//schließen der BufferedReaders und Ausgabe der Erfolgsmeldung
			reader.close();
			return true;
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @name konvertiereZahl(int)
	 * @param zahl
	 * @return String
	 * @description Konvertiert eine Zahl in eine zweistellige Zahl mit vorgefuehrter 0 als String.
	 * 				Dient zur einheitlichen Darstellung im Kalender.
	 */
	private String konvertiereZahl(int zahl)
	{
		String ergebnis;
		if(zahl<10)
		{
			ergebnis="0"+zahl;
		}
		else
		{
			ergebnis=""+zahl;
		}
		return ergebnis;
	}
	/**
	 * @name fuelleZelle(int,int)
	 * @param tag
	 * @param monat 
	 * @return Zelle
	 * @description Funktion, die eine Zelle eines Monatsblattes formatiert. Der Inhalt der Zelle
	 * 				wird als Parameter uebergeben.
	 */
	private Zelle fuelleZelle(int tag, int monat)
	{
		
		Zelle zelle= new Zelle(konvertiereZahl(tag),konvertiereZahl(monat));
		return zelle;
	}
	
	/**
	 * @name private Zelle fuelleZelleZusatz(int, int, int,HashMap<String,String>, boolean, boolean)
	 * @param tag
	 * @param monat
	 * @param jahr
	 * @param feiertageListe
	 * @param modus
	 * @param geburtstagEintragen
	 * @return String
	 * @description Funktion, welche die funktionalitaet von fuelleZelle(int,int) um die Behandlung von
	 * 				Sondertagen wie Feiertage oder Geburtstage erweitert. Dafuer wird die Zelle erstellt
	 * 				und anschließend an die einzelnen Methoden zum eintragen der Sondertage weitergegeben.
	 */
	private Zelle fuelleZelleZusatz(int tag, int monat, int jahr,HashMap<String,String> feiertageListe, boolean modus, boolean geburtstagEintragen)
	{
		//erstellen der normalen Zelle
		Zelle zelle = new Zelle(konvertiereZahl(tag),konvertiereZahl(monat));
		//eintragen der Feiertage wenn TRUE
		if(modus)
		{
			setZelleFeiertag(zelle,monat,feiertageListe);
		}
		//eintragen der Geburtstage wenn TRUE
		if(geburtstagEintragen)
		{
			setZelleGeburtstage(zelle,tag,monat,jahr);
		}
		
		//Rueckgabe der gefuellten Zelle
		return zelle;
	}

	/**
	 * @name setZelleGeburtstage(Zelle, int, int, int)
	 * @param zelle
	 * @param tag
	 * @param monat
	 * @param jahr
	 * @description Ermittelt, ob das aktuelle Datum dieser Zelle ein bekannter Geburtstag ist. Wenn
	 * 				es so ist wird der Name des Geburtstages in die Zelle eingetragen.
	 */
	private void setZelleGeburtstage(Zelle zelle, int tag, int monat, int jahr)
	{
		//holen eines Calendar fuer das aktuelleDatum der Zelle
		Calendar aktuellesDatum=Calendar.getInstance();
		aktuellesDatum.set(jahr, monat, tag);
		//for-Schleife fuer alle Geburtstage
		for(int i=0;i<geburtstage.size();i++)
		{
			//holen eines Calendar fuer dieses Geburtsdatum aus der Liste
			Calendar geburtsdatum=Calendar.getInstance();
			geburtsdatum.setTime(geburtstage.get(i).getGeburtstag());
			//Wenn Tag und Monat der Zelle mit dem Geburtstag aus der Liste uebereinstimmen
			if((aktuellesDatum.get(Calendar.DAY_OF_MONTH)==geburtsdatum.get(Calendar.DAY_OF_MONTH)
			&&(aktuellesDatum.get(Calendar.MONTH)==geburtsdatum.get(Calendar.MONTH)+1)))
			{
				//berechnen des Alters
				int alter = aktuellesDatum.get(Calendar.YEAR)-geburtsdatum.get(Calendar.YEAR);
				//eintragen der Werte in die Zelle
				zelle.addGeburtstag(geburtstage.get(i).getName());
				zelle.addGeburtstagAlter(alter);
			}
		}
	}

	/**
	 * @name setZelleFeiertag(Zelle,int,HashMap<String, String>)
	 * @param zelle
	 * @param monat
	 * @param feiertageListe
	 * @description Ermittelt, ob das aktuelle Datum dieser Zelle ein bekannter Feiertag ist. Wenn
	 * 				es so ist wird der Name des Feiertages in die Zelle eingetragen.
	 */
	private void setZelleFeiertag(Zelle zelle,int monat,HashMap<String, String> feiertageListe)
	{
		//umwandeln des Datums in das Key-Format der feiertageListe
		String datum=zelle.getInhalt()+"."+zelle.getMonatszahl()+".";
		
		//If: wenn das aktuelle Datum ein Feiertag ist
		if(feiertageListe.containsKey(datum))
		{
			zelle.setFeiertagsname(feiertageListe.get(datum));
		}
	}

	/**
	* @name getMonatsblatt(int, int, boolean, boolean) 
	* @param jahr
	* @param monat
	* @param modus
	* @param geburtstagEintragen
	* @return ArrayList<Zelle>
	* @description Erzeugt ein Monatsblatt im Kalender. Dieses wird Zellenweose
	* 			   in eine ArrayList<Zelle> abgelegt.
	* 

	So   Mo   Di   Mi   Do   Fr   Sa 

	          01   02   03   04   05 

	06   07   08   09   10   11   12 

	13   14   15   16   17   18   19 

	20   21   22   23   24   25   26 

	27   28   29   30 

	* 
	*/
	public ArrayList<Zelle> getMonatsblatt(int jahr, int monat, boolean modus, boolean geburtstagEintragen) 
	{
		//Initialisierung der lokalen Variablen der Methode
		int schaltjahr;
		ArrayList<Zelle> data = new ArrayList<Zelle>();
		int tagesnummer;
		int start;
		String monatszahl;
		
		//Formatieren der Monatszahl in einen String fuer das Erstellen der Zellen.
		monatszahl=konvertiereZahl(monat);
		
		HashMap<String,String> feiertageListe = new HashMap<String,String>();
		
		//Fuegt dem Monatsblatt den Wochentageheader hinzu
		data.addAll(getWochentageHeader(monat));
		
		//Anzahl der Tage des Monats bestimmen inkl. Schaltjahr pruefen
		if(kalenderFunktionen.istSchaltjahr(jahr))
		{
			schaltjahr=1;
		}
		else
		{
			
			schaltjahr=0;
		}
		int AnzahlTageMonat = monatslaenge[schaltjahr][monat];
		
		//ermitteln einer Liste aller Feiertage
		if(modus)
		{
			feiertageListe = new FeiertageListe(kalenderFunktionen).getFeiertageListe(jahr, monatslaenge[schaltjahr]);
		}
		
		//Start für den Anfangstag im Kalenderblatt finden
		tagesnummer = kalenderFunktionen.tagesnummer(1, monat, jahr);
		start = kalenderFunktionen.wochentag_im_jahr(jahr, tagesnummer);
		
		//Wenn das Monatsblatt mit Montag beginnen soll
		if(wochenanfangMontag)
		{
			if(start==0)
			{
				start=7;
			}
			//Hinzufuegen der "leeren" Tage am Anfang des Monatsblattes
			for(int filler=1;filler<start;filler++)
			{
				data.add(new Zelle("",monatszahl));
			}
		}
		//Wenn das Monatsblatt mit Sonntag beginnen soll
		else
		{
			//Hinzufuegen der "leeren" Tage am Anfang des Monatsblattes
			for(int filler=0;filler<start;filler++)
			{
				data.add(new Zelle("",monatszahl));
			}
		}
		
		//Hinzufuegen saemmtlicher Tage ins Monatsblatt
		for(int i=1;i<=AnzahlTageMonat;i++)
		{
			if(modus||geburtstagEintragen)
			{
				data.add(fuelleZelleZusatz(i, monat, jahr,feiertageListe,modus,geburtstagEintragen));
			}
			else
			{
				data.add(fuelleZelle(i,monat));
			}
		}
		//Rueckgabe des Monatsblattes als ArrayList<Zelle>
		return data;
	}
	
	/**
	 * @name getWochentageHeader(int monat)
	 * @return ArrayList<Zelle
	 * @description Funktion, die den Wochentageheader fuer das Monatsblatt erstellt und als ArrayList<Zell> zurueck gibt.
	 */
	private ArrayList<Zelle> getWochentageHeader(int monat)
	{
		//Initialisierung der lokalen Variablen der Methode 
		ArrayList<Zelle> header=new ArrayList<Zelle>();

		
		//erstellen des Headers
		for(int i=0;i<7;i++)
		{
			if(wochenanfangMontag)
			{
				if(i==6)
				{
					header.add(new Zelle(wochentageKurz[0],konvertiereZahl(monat)));
				}
				else
				{
					header.add(new Zelle(wochentageKurz[i+1],konvertiereZahl(monat)));
				}
			}
			else
			{
				header.add(new Zelle(wochentageKurz[i],konvertiereZahl(monat)));
			}
		}
		//Rueckgabe des Headers als ArrayList<Zelle>
		return header;
	}

	/**
	 * @name ArrayList<String> getSortierteFeiertageListe(int, boolean)
	 * @param jahr
	 * @param ohneWE
	 * @return ArrayList<String>
	 * @description Diese Methode gibt eine sortierte Liste der Namen aller Feiertage in einem Jahr
	 * 				zurueck. Ist ohneWE=TRUE werden dabei keine Feiertage ausgegeben, die an einem
	 * 				Wochenende liegen.
	 */
	public ArrayList<String> getSortierteFeiertageListe(int jahr, boolean ohneWE)
	{
		//initialisieren alle benoetigten Variablen
		int[] monatslaenge;
		ArrayList<Date> alleFeiertagsDaten=new ArrayList<Date>();
		ArrayList<String> ergebnis=new ArrayList<String>();
		SimpleDateFormat dateConvert =new SimpleDateFormat("dd.MM.");
		//raussuchen des richtigen Arrays an Monatstagen
		if(kalenderFunktionen.istSchaltjahr(jahr))
		{
			monatslaenge=this.monatslaenge[1];
		}
		else
		{
			monatslaenge=this.monatslaenge[0];
		}
		//berechnen der feiertageListe
		HashMap<String,String> aktuelleFeiertageListe = new FeiertageListe(kalenderFunktionen).getFeiertageListe(jahr, monatslaenge);
		//durchgehen aller Feiertage der HashMap und einlesen aller Keys in eine Liste von Date
		//zur Sortierung.
		for (String i : aktuelleFeiertageListe.keySet())
		{
			//zerlegen der Keys
			String[] tokens = i.split("\\.");
			int tag=Integer.parseInt(tokens[0]);
			int monat=Integer.parseInt(tokens[1]);
			try
			{
				//IF, wenn Feiertage an einem WE rausgefiltert werden sollen.
				if(ohneWE)
				{
					int wochentag=kalenderFunktionen.wochentag_im_jahr(jahr, kalenderFunktionen.tagesnummer(tag, monat, jahr));
			
					if(!(wochentag==0||wochentag==6))
					{
						alleFeiertagsDaten.add(dateConvert.parse(i));
					}
				}
				else
				{
					alleFeiertagsDaten.add(dateConvert.parse(i));
				}
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//sortieren der Daten der Feiertage
		Collections.sort(alleFeiertagsDaten);
		//for-Schleife zum durchgehen aller sortierten Daten
		for(int i=0;i<alleFeiertagsDaten.size();i++)
		{
			//einlesen aller Feiertagsnamen in nach Datum sortierter Reihenfolge in die Ergebnisliste
			String key = dateConvert.format(alleFeiertagsDaten.get(i));
			ergebnis.add(key+" : "+aktuelleFeiertageListe.get(key));
		}
		//Rueckgabe der Liste mit sortierten Feiertagsnamen.
		return ergebnis;
	}
	
	/**
	 * @name getFeiertageListe(int)
	 * @param jahr
	 * @return HashMap<String,String>
	 * @description Diese Methode dient als Schnittstelle zur Ausgabe der Methode
					getFeiertageListe(int, int[])
	 */
	public HashMap<String,String> getFeiertageListe(int jahr)
	{
		//initialisieren der benoetigten Variablen
		int[] monatslaenge;
		HashMap<String,String> feiertageListe;
		//raussuchen des richtigen Arrays an Monatstagen
		if(kalenderFunktionen.istSchaltjahr(jahr))
		{
			monatslaenge=this.monatslaenge[1];
		}
		else
		{
			monatslaenge=this.monatslaenge[0];
		}
		//berechnen und Rueckgabe der feiertageListe
		feiertageListe=new FeiertageListe(kalenderFunktionen).getFeiertageListe(jahr, monatslaenge);
		return feiertageListe;
	}
	
	/**
	 * @name getDatumEinzelnerFeiertag(int,String)
	 * @param jahr
	 * @param feiertag
	 * @return String
	 * @description Diese Methode gibt das Datum eines uebergebenen Feiertages in einem Jahr aus.
	 */
	public String getDatumEinzelnerFeiertag(int jahr,String feiertag)
	{
		int[] monatslaenge;
		//raussuchen des richtigen Arrays an Monatstagen
		if(kalenderFunktionen.istSchaltjahr(jahr))
		{
			monatslaenge=this.monatslaenge[1];
		}
		else
		{
			monatslaenge=this.monatslaenge[0];
		}
		//berechnen der feiertageListe
		HashMap<String,String> feiertageListe=new FeiertageListe(kalenderFunktionen).getFeiertageListe(jahr, monatslaenge);
		String ergebnis="";
		//Durchsuchen der feiertageListe nach dem uebergebenen Feiertag
		for (String i : feiertageListe.keySet())
		{
			if(feiertageListe.get(i).contains(feiertag))
			{
				ergebnis=i;
			}
		}
		//Rueckgabe des Datums des Feiertages.
		return ergebnis;
	}
}
