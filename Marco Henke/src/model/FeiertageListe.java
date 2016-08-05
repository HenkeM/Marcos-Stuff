/**
 * @filename FeiertageListe
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 25.05.2014
 * @lastModified 31.05.2014
 * @method FeiertageListe(KalenderFunktionen)
 * @method getFeiertageListe(int, int[])
 * 
 * @description Diese Klasse dient dazu, bewegliche und feste Feiertage in eine einzige HashMap einzutragen,
 * 				welche ueber getFeiertageListe(int,int[]) abgerufen werden kann.
 */
package model;

import java.util.HashMap;


public class FeiertageListe
{
	/**
	 * @Attribute KalenderFunktion kalenderFunktionen
	 */
	private KalenderFunktionen kalenderFunktionen;
	
	/**
	 * @name FeiertageListe(KalenderFunktionen)
	 * @param kf
	 * @description Konstruktor der Klasse. Uebergibt eine Referenz auf die KalenderFunktionen des Kalenders
	 * 				an das eigene Attribut dieser Klasse.
	 */
	public FeiertageListe(KalenderFunktionen kf)
	{
		this.kalenderFunktionen=kf;
	}
	
	/**
	 * @name getFeiertageListe(int, int[])
	 * @param jahr
	 * @param monatslaenge
	 * @return HashMap<String,String>
	 * @description Funktion, welche die HashMaps der festen und beweglichen Feiertage zu einer HashMap
	 * 				zusammenfuehrt und dabei Probleme mit mehrfach belegten Daten behandelt.
	 */
	public HashMap<String,String> getFeiertageListe(int jahr, int[] monatslaenge)
	{
		HashMap<String,String> feiertageListe = new HashMap<String,String>();
		//Aufrufen der Funktionen fuer bewegliche und feste Feiertage
		HashMap<String,String> festeFeiertage = new FesteFeiertage().getFesteFeiertage();
		HashMap<String,String> beweglicheFeiertage = new BeweglicheFeiertage(kalenderFunktionen).getBeweglicheFeiertage(jahr, monatslaenge);
		
		//Hinzufuegen aller festen Feiertage zur Ausgabe HashMap
		feiertageListe.putAll(festeFeiertage);
		//For-Schleife zum durchgehen alle beweglichen Feiertage
		for (String i : beweglicheFeiertage.keySet())
		{
			//IF: wenn fuer das aktuelle  Datum aus bewegliche Feiertage bereits ein Feiertag eingetragen ist
			if(feiertageListe.containsKey(i))
			{
				String neuerWert="";
				neuerWert=feiertageListe.get(i)+", "+beweglicheFeiertage.get(i);
				feiertageListe.put(i, neuerWert);
			}
			else
			{
				feiertageListe.put(i, beweglicheFeiertage.get(i));
			}
		}
		//Rueckgabe der feiertageListe
		return feiertageListe;
	}
}
