/**
 * @filename Zelle
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 05.06.2014
 * @lastModified 29.06.2014
 * @method Zelle(String,String)
 * @method getInhalt()
 * @method setInhalt(String)
 * @method getMonatszahl()
 * @method setMonatszahl(String)
 * @method getFeiertagsname()
 * @method setFeiertagsname(String)
 * @method getGeburtstag()
 * @method addGeburtstag(String)
 * @method getGeburtstagAlter()
 * @method addGeburtstagAlter(int)
 * 
 * @description Diese Klasse dient fuer den Datentyp Zelle. Dieser Datentyp enthaelt saemtliche
 * 				Informationen ueber eine Zelle des Monatsblattes, die vom View benoetigt werden
 * 				um die Zelle darzustellen.
 */
package model;

import java.util.ArrayList;

public class Zelle
{
	/**
	 * @Attribute String inhalt
	 * @Attribute String monatszahl
	 * @Attribute String feiertagsname
	 * @Attribute ArrayList<String> geburtstagskind
	 * @Attribute ArrayList<Integer> geburtstagAlter
	 */
	private String inhalt;
	private String monatszahl;
	private String feiertagsname=new String();
	private ArrayList<String> geburtstagskind = new ArrayList<String>();
	private ArrayList<Integer> geburtstagAlter = new ArrayList<Integer>();
	
	/**
	 * @name Zelle(String, String)
	 * @param inhalt
	 * @param monatszahl
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter einen Inhalt und eine Monatszahl
	 * 				uebergeben.
	 */
	public Zelle(String inhalt, String monatszahl)
	{
		this.inhalt=inhalt;
		this.monatszahl=monatszahl;
	}
	
	//Ab hier beginnen die Getter und Setter der Klasse
	/**
	 * @name getInhalt()
	 * @return String
	 * @description Getter des Attributes Inhalt.
	 */
	public String getInhalt()
	{
		return inhalt;
	}
	/**
	 * @name setInhalt(String)
	 * @param inhalt
	 * @description Setter des Attributes Inhalt.
	 */
	public void setInhalt(String inhalt)
	{
		this.inhalt = inhalt;
	}
	
	/**
	 * @name getMonatszahl
	 * @return String
	 * @description Getter des Attributes Monatszahl.
	 */
	public String getMonatszahl()
	{
		return monatszahl;
	}

	/**
	 * @name setMonatszahl(String)
	 * @param monatszahl
	 * @description Setter des Attributes Monatszahl.
	 */
	public void setMonatszahl(String monatszahl)
	{
		this.monatszahl = monatszahl;
	}
	
	/**
	 * @name getFeiertagsname()
	 * @return String
	 * @description Getter des Attributes feiertagsname.
	 */
	public String getFeiertagsname()
	{
		return feiertagsname;
	}
	
	/**
	 * @name setFeiertagsname(String)
	 * @param feiertagsname
	 * @description Setter des Attributes feiertagsname.
	 */
	public void setFeiertagsname(String feiertagsname)
	{
		this.feiertagsname = feiertagsname;
	}
	
	/**
	 * @name getGeburtstag()
	 * @return ArrayList<String>
	 * @description Getter des Attributes Geburtstag.
	 */
	public ArrayList<String> getGeburtstag()
	{
		return geburtstagskind;
	}
	
	/**
	 * @name addGeburtstag(String)
	 * @param geburtstagskind
	 * @description fuegt der Liste geburtstagskind den Parameter hinzu.
	 */
	public void addGeburtstag(String geburtstagskind)
	{
		this.geburtstagskind.add(geburtstagskind);
	}
	/**
	 * @name getGeburtstagAlter()
	 * @return ArrayList<String>
	 * @description Getter des Attributes GeburtstagAlter.
	 */
	public ArrayList<Integer> getGeburtstagAlter()
	{
		return geburtstagAlter;
	}
	
	/**
	 * @name addGeburtstagAlter(int)
	 * @param geburtstagAlter
	 * @description fuegt der Liste geburtstagAlter den Parameter hinzu.
	 */
	public void addGeburtstagAlter(int geburtstagAlter)
	{
		this.geburtstagAlter.add(geburtstagAlter);
	}
}
