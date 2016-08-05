
/**
 * @filename FesteFeiertage
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 24.05.2014
 * @lastModified 31.05.2014
 * @method FesteFeiertage()
 * @method getFesteFeiertage()
 * 
 * @description Diese Klasse dient als Sammlung fuer Konstante HashMap<String,String> festeFeiertage,
 * 				welche ueber getFesteFeiertage() ausgelesen werden kann.
 */

package model;

import java.util.HashMap;

public class FesteFeiertage
{
	/**
	 * @Attribute HashMap<String,String> festeFeiertage
	 */
	private static final HashMap<String,String> festeFeiertage = new HashMap<String,String>();

	/**
	 * @name FesteFeiertage()
	 * @description Konstruktor der Klasse. Traegt bei aufruf saemmtliche festen Feiertage in die Konstante
	 * 				festeFeiertage ein.
	 */
	public FesteFeiertage()
	{
		festeFeiertage.put("01.01.", "Neujahrstag");
		festeFeiertage.put("06.01.", "Heilige Drei Könige");
		festeFeiertage.put("14.02.", "Valentinstag");
		festeFeiertage.put("08.03.", "Frauentag");
		festeFeiertage.put("01.05.", "Maifeiertag, Tag der Arbeit");
		festeFeiertage.put("15.08.", "Maria Himmelfahrt");
		festeFeiertage.put("03.10.", "Tag der Deutschen Einheit");
		festeFeiertage.put("31.10.", "Halloween, Reformationstag");
		festeFeiertage.put("01.11.", "Allerheiligen");
		festeFeiertage.put("02.11.", "Allerseelen");
		festeFeiertage.put("11.11.", "Martinstag");
		festeFeiertage.put("06.12.", "Nikolaustag");
		festeFeiertage.put("24.12.", "Heiligabend");
		festeFeiertage.put("25.12.", "1.Weihnachtsfeiertag");
		festeFeiertage.put("26.12.", "2.Weihnachtsfeiertag");
		festeFeiertage.put("31.12.", "Silvester");
	}
	
	/**
	 * @name getFesteFeiertage()
	 * @return HashMaß<String,String>
	 * @description Getter des Attributes festeFeiertage.
	 */
	public HashMap<String,String> getFesteFeiertage()
	{
		return festeFeiertage;
	}
}
