/**
 * @filename BeweglicheFeiertage
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 25.05.2014
 * @lastModified 31.05.2014
 * @method BeweglicheFeiertage(KalenderFunktionen)
 * @method getBeweglicheFeiertage(int,int[])
 * 
 * @description Diese Klasse dient als Sammlung fuer alle noetigen Funktionen zur berechnung der
 * 				beweglichen Feiertage des Kalenders, die ueber getBeweglicheFeiertage(int,int) ausgelesen
 * 				werden können.
 */
package model;

import java.util.HashMap;


public class BeweglicheFeiertage
{
	/**
	 * @Attribute KalenderFunktionen kalenderFunktionen;
	 */
	private KalenderFunktionen kalenderFunktionen;
	
	/**
	 * @name BeweglicheFeiertage(KalenderFunktionen)
	 * @param kf
	 * @description Konstruktor der Klasse. Uebergibt eine Referenz auf die KalenderFunktionen des Kalenders
	 * 				an das eigene Attribut dieser Klasse.
	 */
	public BeweglicheFeiertage(KalenderFunktionen kf)
	{
		this.kalenderFunktionen=kf;
	}
	
	/**
	 * @name getBeweglicheFeiertage(int, int[])
	 * @param jahr
	 * @param monatslaenge
	 * @return HashMap<String,String>
	 * @description Funktion, die alle Funktionen zur Berechnung der beweglichen Feiertage aufruft und
	 * 				diese ein eine HashMap<String,String> zusammenfuegt und diese zurueck gibt.
	 */
	public HashMap<String,String> getBeweglicheFeiertage(int jahr, int[] monatslaenge)
	{
		HashMap<String,String> beweglicheFeiertage = new HashMap<String,String>();
		osternabhaengigeFeiertage(jahr, monatslaenge, beweglicheFeiertage);
		weichnachtsabhaengigeFeiertage(jahr, monatslaenge, beweglicheFeiertage);
		muttertagBerechnen(jahr, monatslaenge, beweglicheFeiertage);
		
		return beweglicheFeiertage;
	}
	
	/**
	 * @name osternabhaengigeFeiertage(int, int[], HashMap<String,String>)
	 * @param jahr
	 * @param monatslaenge
	 * @param beweglicheFeiertage
	 * @description Prozedur, die alle osternabhaengigen Feiertage in der 
	 * 				HashMap<String,String> beweglicheFeiertage eintraegt, welche ihr als Parameter uebergeben
	 * 				wurde.
	 */
	private void osternabhaengigeFeiertage(int jahr, int[] monatslaenge, HashMap<String,String> beweglicheFeiertage)
	{
		//Berechnen des Ostersonntag
		int ostersonntag = kalenderFunktionen.ostersonntag(jahr);
		//Eintragen aller osternabhaengigen Feiertage ueber die Prozedur erstelleFeiertageAusTagen(int,int[],HashMap<String,String>)
		erstelleFeiertagAusTagen(ostersonntag-48, monatslaenge, "Rosenmontag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag-47, monatslaenge, "Faschingsdienstag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag-46, monatslaenge, "Aschermittwoch", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag-3, monatslaenge, "Gruendonnerstag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag-7, monatslaenge, "Palmsonntag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag-2, monatslaenge, "Karfreitag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag, monatslaenge, "Ostersonntag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag+1, monatslaenge, "Ostermontag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag+39, monatslaenge, "Christi Himmelfahrt", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag+49, monatslaenge, "Pfingstsonntag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag+50, monatslaenge, "Pfingstmontag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(ostersonntag+60, monatslaenge, "Frohleichnam", beweglicheFeiertage);	
	}
	
	/**
	 * @name weihnachtsabhaengigeFeiertage(int, int[], HashMap<String,String>)
	 * @param jahr
	 * @param monatslaenge
	 * @param beweglicheFeiertage
	 * @description Prozedur, die alle weihnachtsabhaengigen Feiertage in der 
	 * 				HashMap<String,String> beweglicheFeiertage eintraegt, welche ihr als Parameter uebergeben
	 * 				wurde.
	 */
	private void weichnachtsabhaengigeFeiertage(int jahr, int[] monatslaenge, HashMap<String,String> beweglicheFeiertage)
	{
		//Bestimmen der Tageszahl von Weihnachten
		int weihnachtsTageszahl = kalenderFunktionen.tagesnummer(25, 12, jahr);
		//Bestimmen des 4.Advent
		int weihnachtsWochentag = kalenderFunktionen.wochentag_im_jahr(jahr, weihnachtsTageszahl);
		if(weihnachtsWochentag==0)
		{
			weihnachtsWochentag=7;
		}
		int vierterAdvent = weihnachtsTageszahl-weihnachtsWochentag;
		
		//Eintragen aller weihnachtsabhaengigen Feiertage ueber die Prozedur 
		//erstelleFeiertageAusTagen(int,int[],HashMap<String,String>)
		erstelleFeiertagAusTagen(vierterAdvent-35, monatslaenge, "Volkstrauertag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(vierterAdvent-32, monatslaenge, "Buß- und Bettag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(vierterAdvent-28, monatslaenge, "Totensonntag", beweglicheFeiertage);
		erstelleFeiertagAusTagen(vierterAdvent-21, monatslaenge, "1.Advent", beweglicheFeiertage);
		erstelleFeiertagAusTagen(vierterAdvent-14, monatslaenge, "2.Advent", beweglicheFeiertage);
		erstelleFeiertagAusTagen(vierterAdvent-7, monatslaenge, "3.Advent", beweglicheFeiertage);
		erstelleFeiertagAusTagen(vierterAdvent, monatslaenge, "4.Advent", beweglicheFeiertage);	
	}
	
	/**
	 * @name muttertagBerechnen(int, int[], HashMap<String,String>)
	 * @param jahr
	 * @param monatslaenge
	 * @param beweglicheFeiertage
	 * @description Prozedur, die den Muttertag berechnet und diesen in die 
	 * 				HashMap<String,String>beweglicheFeiertage eintraegt, welche ihr als Parameter uebergeben
	 * 				wurde.
	 */
	private void muttertagBerechnen(int jahr, int[] monatslaenge, HashMap<String,String> beweglicheFeiertage)
	{
		int tag=15;
		String datum="";
		//Bestimme Wochentag vom 1.Mai
		int ersterMaiWochentag = kalenderFunktionen.wochentag_im_jahr(jahr, kalenderFunktionen.tagesnummer(1, 5, jahr));
		if(ersterMaiWochentag==0)
		{
			ersterMaiWochentag=7;
		}
		tag=15-ersterMaiWochentag;
		
		//Korrektur  fuer Pfingstsonntag = Muttertag
		int pfingstsonntagTageszahl = kalenderFunktionen.ostersonntag(jahr)+49;
		int muttertagTageszahl = kalenderFunktionen.tagesnummer(tag, 5, jahr);
		if(muttertagTageszahl == pfingstsonntagTageszahl)
		{
			tag=tag-7;
		}
		
		if(tag<10)
		{
			datum=datum+"0"+tag+"."+"05.";
		}
		else
		{
			datum=datum+tag+"."+"05.";
		}
		beweglicheFeiertage.put(datum, "Muttertag");
	}
	
	/**
	 * @name erstelleFeiertagAusTagen(int, int[], String, HashMap<String,String>)
	 * @param datumInTagen
	 * @param monatslaenge
	 * @param name
	 * @param beweglicheFeiertage
	 * @description Prozedur, die eine Tageszahl in ein Datum umrechnet und dieses zusammen mit einem Namen
	 * 				in die HashMap<String,String> beweglicheFeiertage eintraegt, welche ihr als Paramteter
	 * 				uebergeben wurde.
	 */
	private void erstelleFeiertagAusTagen(int datumInTagen, int[] monatslaenge, String name, HashMap<String,String> beweglicheFeiertage)
	{
		int tag=datumInTagen;
		int monat=1;
		String datum="";
		//while-Schleife zum berechnen der Monatszahl
		while(tag>monatslaenge[monat])
		{
			tag=tag-monatslaenge[monat];
			monat++;
		}
		//Verschachtelte IF-Verzweigungen zum formatieren des Datums in ein bestimmtes String Format
		if(tag<10)
		{
			if(monat<10)
			{
				datum=datum+"0"+tag+"."+"0"+monat+".";		
			}
			else
			{
				datum=datum+"0"+tag+"."+monat+".";	
			}
		}
		else
		{
			if(monat<10)
			{
				datum=datum+tag+"."+"0"+monat+".";	
			}
			else
			{
				datum=datum+tag+"."+monat+".";	
			}
		}
		//Eintragen des Datums mit dem dazugehörigen Namen in beweglicheFeiertage
		beweglicheFeiertage.put(datum, name);
	}
	
}
