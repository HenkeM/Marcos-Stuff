/**
 * @filename Geburtstag
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 21.06.2014
 * @lastModified 29.06.2014
 * @method Geburtstag(Date geburtstag,String name)
 * @method getGeburtstag()
 * @method getName()
 * 
 * @description Die Klasse dient als Datentyp fuer Geburtstage. Dazu enthaelt sie das Geburtsdatum
 * 				und einen Namen.
 */
package model;

import java.util.Date;

public class Geburtstag
{
	/**
	 * @Attribute Date geburtstag
	 * @Attribute String name
	 */
	private Date geburtstag;
	private String name;
	
	/**
	 * @name Geburtstag(Date,String)
	 * @param geburtstag
	 * @param name
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter ein Geburtsdatum (Tag, Monat und Jahr)
	 * 				und einen Namen.
	 */
	public Geburtstag(Date geburtstag,String name)
	{
		this.geburtstag=geburtstag;
		this.name=name;
	}

	/**
	 * @name getGeburtstag()
	 * @return Date
	 * @description Getter fuer das Attribut geburtstag.
	 */
	public Date getGeburtstag()
	{
		return geburtstag;
	}

	/**
	 * @name getName()
	 * @return String
	 * @description Getter fuer das Attribut name.
	 */
	public String getName()
	{
		return name;
	}
}
