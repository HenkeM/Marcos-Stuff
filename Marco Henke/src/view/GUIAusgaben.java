/**
 * @filename KalenderInterface
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 26.05.2014
 * @lastModified 29.06.2014
 * @method GUIAusgaben(Kalender)
 * @method erfolgWochenanfangMontag()
 * @method erfolgBeenden()
 * @method erfolgSpeichern()
 * @method fehlerMenu()
 * @method fehlerJaNeinFrage()
 * @method fehlerMonatseingabe()
 * @method fehlerJahreseingabe()
 * 
 * @description Diese Klasse enthaelt saemmtliche Ausgaben und Eingabeaufforderungen an den Benutzer.
 */
package view;

import model.Kalender;

public class GUIAusgaben
{
	/**
	 * @Attribute Kalender kalender
	 */
	Kalender kalender;
	
	/**
	 * @name GUIAusgaben(Kalender)
	 * @param kalender
	 * @description Der Konstruktor der Klasse. Bekommt die Referenz auf die Instanz von Kalender
	 * 				uebergeben und speichert sie im eigenen Attribut kalender.
	 */
	public GUIAusgaben(Kalender kalender)
	{
		this.kalender = kalender;
	}
	
	/*
	 * Ab hier beginnen die Erfolgsmeldungen
	 */
	
	/**
	 * @name erfolgWochenanfangMontag()
	 * @description Bestaetigung, dass die Einstellung geaendert wurde
	 */
	public void erfolgWochenanfangMontag()
	{
		System.out.println("Ihre Eingabe wurde gespeichert.");
	}
	
	/**
	 * @name erfolgBeenden()
	 * @description Bestaetigung, dass das Programm beendet wurde.
	 */
	public void erfolgBeenden()
	{
		System.out.println("Das Programm wurde beendet. Auf Wiedersehen.");
	}
	
	/**
	 * @name erfolgSpeichern()
	 * @description Bestaetigung, dass die Ausgabe gespeichert wurde.
	 */
	public void erfolgeSpeichern()
	{
		System.out.println("Das Kalenderblatt wurde erfolgreich gespeichert");		
	}
	
	/*
	 * Ab hier beginnen die Fehlermeldungen
	 */
	
	/**
	 * @name fehlerMenu()
	 * @description Meldung, dass die Eingabe im Menu fehlerhaft ist.
	 */
	public void fehlerMenu()
	{
		System.out.println("\nfehlerhafte Eingabe! Waehlen sie bitte eine Option aus, indem sie eine Zahl von 0-5 eingeben.\n");
	}
	
	/**
	 * @name fehlerJaNeinFrage()
	 * @description Meldung, dass die Antwort auf eine ja/nein Frage fehlerhaft ist.
	 */
	public void fehlerJaNeinFrage()
	{
		System.out.println("\nfehlerhafte Eingabe! Geben sie bitte j bzw. J fuer ja oder n bzw. N fuer nein ein.\n");
	}

	/**
	 * @name fehlerMonatseingabe()
	 * @description Meldung, dass die Eingabe keine Monatszahl ist.
	 */
	public void fehlerMonatseingabe()
	{
		System.out.println("\nfehlerhafte Eingabe! Bitte geben sie eine Zahl von 1-12 als Monat ein.");
	}	
	
	/**
	 * @name fehlerJahreseingabe()
	 * @description Meldung, dass die Eingabe keine Jahreszahl ist.
	 */
	public void fehlerJahreseingabe()
	{
		System.out.println("\nfehlerhafte Eingabe! Bitte geben sie positive Zahl als Jahr ein.");
	}
}
