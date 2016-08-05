/**
 * @filename KalenderFileFilter
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 27.06.2014
 * @lastModified 29.06.2014
 * @method KalenderFileFilter(String,String)
 * @method accept(File)
 * @method getDescription()
 * 
 * @description Diese Klasse erweitert die Klasse FileFilter zur Erstellung eigener FileFilter
 * 				fuer die JFileChooser.
 */
package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class KalenderFileFilter extends FileFilter
{
	/**
	 * @Attribute endung
	 * @Attribute bezeichnung
	 */
	private String endung;
	private String bezeichnung;
	
	/**
	 * @name KalenderFileFilter(String, String)
	 * @param endung
	 * @param bezeichnung
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter die Endung des Dateityps und
	 * 				die volle Bezeichnung fuer diesen.
	 */
	public KalenderFileFilter(String endung, String bezeichnung)
	{
		this.endung=endung;
		this.bezeichnung=bezeichnung;
	}
	
	/**
	 * @name accept(File)
	 * @description Diese Methode realisiert die Implementation der gleichnamigen Methode von FileFilter
	 */
	@Override
	public boolean accept(File file)
	{
		// TODO Auto-generated method stub
		if (file.isDirectory()) 
		{
            return true;
        }
        return file.getName().endsWith(endung);
	}

	/**
	 * @name getDescription()
	 * @description Diese Methode realisiert die Implementation der gleichnamigen Methode von FileFilter
	 */
	@Override
	public String getDescription()
	{
		String ausgabe="";
		ausgabe = bezeichnung+(" ("+endung+")");
		return ausgabe;
	}
}
