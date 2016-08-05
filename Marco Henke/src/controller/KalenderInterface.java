/**
 * @filename KalenderInterface
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 26.05.2014
 * @lastModified 29.06.2014
 * @method KalenderInterface(int zellenbreite, boolean wochenanfangMontag)
 * @method start()
 * 
 * @description Diese Klasse startet das Programm bzw. initialisiert Model, View und weitere Controller.
 * 				Sie ermoglicht diesen Klassen außerdem ueber Getter den Zugriff untereinander.
 */

package controller;

import model.*;
import view.*;

public class KalenderInterface
{
	/**
	 * @Attribute Kalender kalender
	 * @Attribute Benutzeroberflaeche fenster
	 * @Attribute MenuActionListener menuAL
	 * @Attribute KalenderRefreshActionListener refreshAL
	 * @Attribute SpeicherFunktionen speicherFunktionen
	 */
	private Kalender kalender;
	private Benutzeroberflaeche fenster;
	private MenuActionListener menuAL;
	private KalenderRefreshActionListener refreshAL;
	private SpeicherFunktionen speicherFunktionen;
	
	/**
	 * @name KalenerInterface(int, boolean)
	 * @param zellenbreite		 :	Abstand zwischen den einzelnen Zellen bzw. Spalten der Monatsblaetter.
	 * 								Beeinflusst die gesamte Breite der Monatsblaetter
	 * @param jahresIntervall	 :	Beeinflusst das in der Benutzeroberfläche auswaehlbare Intervall an
	 * 								darstellbaren Jahren in Abhaengigkeit vom aktuellen Jahr
	 * @param wochenanfangMontag : 	Starteinstellung, ob die Wochen der Monatsblaetter mit Monatg (true) oder
	 * 								Sonntag (false) anfangen sollen
	 * @description Der Konstruktor der Klasse. Initialisiert alle weiteren MVC Instanzen.
	 */
	public KalenderInterface(int zellenbreite, int jahresIntervall, boolean wochenanfangMontag)
	{
		this.kalender= new Kalender(zellenbreite,wochenanfangMontag);
		this.refreshAL=new KalenderRefreshActionListener(this);
		this.menuAL=new MenuActionListener(this);
		this.fenster = new Benutzeroberflaeche(this, jahresIntervall);
		this.speicherFunktionen=new SpeicherFunktionen(this);
		refreshAL.refresh();
	}

	//Ab hier beginnen die Getter.
	/**
	 * @description Getter fuer die Instanz von SpeicherFunktionen
	 * @return SpeicherFunktionen
	 */
	public SpeicherFunktionen getSpeicherFunktionen()
	{
		return speicherFunktionen;
	}

	/**
	 * @description Getter fuer die Instanz von Kalender
	 * @return Kalender
	 */
	public Kalender getKalender()
	{
		return kalender;
	}

	/**
	 * @description Getter fuer die Instanz von Benutzeroberflaeche
	 * @return Benutzeroberflaeche
	 */
	public Benutzeroberflaeche getFenster()
	{
		return fenster;
	}

	/**
	 * @description Getter fuer die Instanz von MenuActionListener
	 * @return MenuActionListener
	 */
	public MenuActionListener getMenuAL()
	{
		return menuAL;
	}

	/**
	 * @description Getter fuer die Instanz von KalenderRefreshActionListener
	 * @return KalenderRefreshActionListener
	 */
	public KalenderRefreshActionListener getRefreshAL()
	{
		return refreshAL;
	}
}
