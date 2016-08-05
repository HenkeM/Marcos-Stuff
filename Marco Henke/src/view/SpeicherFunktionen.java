/**
 * @filename SpeicherFunktionen
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 27.06.2014
 * @lastModified 29.06.2014
 * @method SpeicherFunktionen(KalenderInterface)
 * @method kalenderSpeichernRTF(File)
 * 
 * @description Diese Klasse enthaelt alle Methoden zum speichern der Kalenderausgabe.
 */
package view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit;

import controller.KalenderInterface;

public class SpeicherFunktionen
{
	/**
	 * @Attribute Benutzeroberflaeche gui
	 */
	private Benutzeroberflaeche gui;
	
	/**
	 * @name SpeicherFunktionen(KalenderInterface)
	 * @param kalenderInterface
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter das KalenderInterface, woraus
	 * 				er sich saemmtliche MVC-Klassen holt.
	 */
	public SpeicherFunktionen(KalenderInterface kalenderInterface)
	{
		this.gui=kalenderInterface.getFenster();
	}
	
	/**
	 * @name kalenderSpeichernRTF(File)
	 * @param file
	 * @description Diese Methode realisiert das Speichern des aktuellen KalenderblattAusgabe Dokumentes
	 * 				der Benutzeroberflaeche als RTF-Datei
	 */
	public void kalenderSpeichernRTF(File file)
	{
		//initialisieren der benoetigten Variablen
		RTFEditorKit kit = new RTFEditorKit();
		BufferedOutputStream out = null;
		//Sicherstellen, dass die uebergebene Datei auch wirklich eine RTF-Datei ist.
		if(!file.getName().endsWith(".rtf"))
		{
			file = new File(file.getPath()+".rtf");
		}

		try
		{
			//Uebergeben der File an einen BufferedOutputStream
			out = new BufferedOutputStream(new FileOutputStream(file));
			//Schreiben des KalenderblattAusgabe Dokuments in die Datei
			kit.write(out, gui.getAusgabeDoc(), 0, gui.getAusgabeDoc().getLength());
			//Schließen des BufferedOutputStream
			out.close();
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (BadLocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
