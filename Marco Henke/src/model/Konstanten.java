/**
 * @filename Konstanten
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 15.05.2014
 * @last 29.06.2014
 * 
 * @description Diese Klasse dient als Sammlung fuer fast alle Konstanten, die vom Programm
 * 				benoetigt werden.
 */
package model;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Konstanten
{
	public static final String ZELLENFILLERSYMBOL=" ";
	public static final String HEADERFILLERSYMBOL="*";
	public static final String FEIERTAGSSYMBOL="*";
	public static final String[] MONATSNAME = {null,"Januar","Februar","Maerz","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
	public static final String[] WOCHENTAGEKURZ = {"So","Mo","Di","Mi","Do","Fr","Sa"};
	public static final int[][] MONATSLAENGE = {{0,31,28,31,30,31,30,31,31,30,31,30,31},{0,31,29,31,30,31,30,31,31,30,31,30,31}};
	
	public static final SimpleAttributeSet getStandardSet()
	{
		SimpleAttributeSet standardSet = new SimpleAttributeSet();
		StyleConstants.setFontFamily(standardSet, "Courier New");
		return standardSet;
	}
	
	public static final SimpleAttributeSet getFeiertagSet()
	{
		SimpleAttributeSet feiertagSet = new SimpleAttributeSet();
		StyleConstants.setForeground(feiertagSet, Color.MAGENTA);
		StyleConstants.setFontFamily(feiertagSet, "Courier New");
		return feiertagSet;
	}
	
	public static final SimpleAttributeSet getGeburtstagSet()
	{
		SimpleAttributeSet geburtstagSet = new SimpleAttributeSet();
		StyleConstants.setForeground(geburtstagSet, Color.BLUE);
		StyleConstants.setFontFamily(geburtstagSet, "Courier New");
		return geburtstagSet;
	}
}
