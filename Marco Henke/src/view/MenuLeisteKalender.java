/**
 * @filename MenuLeisteKalender
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 22.06.2014
 * @lastModified 29.06.2014
 * @method MenuLeisteKalender(MenuActionListener)
 * @method getMenuDateiLaden()
 * @method getMenuDateiSpeichern()
 * @method getMenuDateiBeenden()
 * @method getMenuKalenderFeiertage()
 * @method getMenuKalenderFeiertageOhne()
 * @method getMenuKalenderWann()
 * @method getMenuLookFeelWindows()
 * @method getMenuLookFeelMetal()
 * @method getMenuLookFeelMotif()
 * @method getMenuHilfeAutor()
 * @method getMenuHilfeHelp()
 * 
 * @description Diese Klasse ist die Menuleiste der Benutzeroberflaeche.
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.MenuActionListener;

public class MenuLeisteKalender extends JMenuBar
{
	/**
	 * @Attribute JMenu menuDatei
	 * @Attribute JMenu menuKalender
	 * @Attribute JMenu menuLookFeel
	 * @Attribute JMenu menuHilfe
	 * @Attribute JMenuItem menuDateiLaden
	 * @Attribute JMenuItem menuDateiSpeichern
	 * @Attribute JMenuItem menuDateiBeenden
	 * @Attribute JMenuItem menuKalenderFeiertage
	 * @Attribute JMenuItem menuKalenderFeiertageOhne
	 * @Attribute JMenuItem menuKalenderWann
	 * @Attribute JMenuItem menuLookFeelWindows
	 * @Attribute JMenuItem menuLookFeelMetal
	 * @Attribute JMenuItem menuLookFeelMotif
	 * @Attribute JMenuItem menuHilfeAutor
	 * @Attribute JMenuItem menuHilfeHelp
	 */
	private static final long serialVersionUID = 1L;

	private MenuActionListener menuAL;
	
	private JMenu menuDatei = new JMenu("Datei");
	private JMenu menuKalender = new JMenu("Kalender");
	private JMenu menuLookFeel = new JMenu("Look & Feel");
	private JMenu menuHilfe = new JMenu("Hilfe");
	
	private JMenuItem menuDateiLaden = new JMenuItem("lade Geburtstage");
	private JMenuItem menuDateiSpeichern= new JMenuItem("speicher Kalender");
	private JMenuItem menuDateiBeenden = new JMenuItem("beenden");
	
	private JMenuItem menuKalenderFeiertage = new JMenuItem("Feiertage");
	private JMenuItem menuKalenderFeiertageOhne = new JMenuItem("Feiertage, ohne So/Sa");
	private JMenuItem menuKalenderWann = new JMenuItem("wann");
	
	private JMenuItem menuLookFeelWindows = new JMenuItem("Windows");
	private JMenuItem menuLookFeelMetal = new JMenuItem("Metal");
	private JMenuItem menuLookFeelMotif = new JMenuItem("Motif");
	
	private JMenuItem menuHilfeAutor = new JMenuItem("Autor/in");
	private JMenuItem menuHilfeHelp = new JMenuItem("Hilfe");
	
	/**
	 * @name MenuLeisteKalender(MenuActionListener)
	 * @param menuAL
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter den fuer diese Klasse zustaendigen
	 * 				MenuActionListener uebergeben.
	 */

	
	public MenuLeisteKalender(MenuActionListener menuAL) 
	{
		this.menuAL=menuAL;
		menuAL.setMenuLeiste(this);
		
		this.add(menuDatei);
		this.add(menuKalender);
		this.add(menuLookFeel);
		this.add(menuHilfe);
		
		menuDatei.add(menuDateiLaden);
		menuDatei.add(menuDateiSpeichern);
		menuDatei.add(menuDateiBeenden);
		
		menuKalender.add(menuKalenderFeiertage);
		menuKalender.add(menuKalenderFeiertageOhne);
		menuKalender.add(menuKalenderWann);
		
		menuLookFeel.add(menuLookFeelWindows);
		menuLookFeel.add(menuLookFeelMetal);
		menuLookFeel.add(menuLookFeelMotif);
		
		menuHilfe.add(menuHilfeAutor);
		menuHilfe.add(menuHilfeHelp);
		
		listenerAnbinden();

	}

	/**
	 * @name listenerAnbinden()
	 * @description Diese Methode bindet den MenuActionListener an die einzelnen MenuItems an
	 */
	private void listenerAnbinden()
	{
		//menuAL anbinden
		menuDateiLaden.addActionListener(menuAL);
		menuDateiSpeichern.addActionListener(menuAL);
		menuDateiBeenden.addActionListener(menuAL);
		menuKalenderFeiertage.addActionListener(menuAL);
		menuKalenderFeiertageOhne.addActionListener(menuAL);
		menuKalenderWann.addActionListener(menuAL);
		menuLookFeelWindows.addActionListener(menuAL);
		menuLookFeelMetal.addActionListener(menuAL);
		menuLookFeelMotif.addActionListener(menuAL);
		menuHilfeAutor.addActionListener(menuAL);
		menuHilfeHelp.addActionListener(menuAL);
	}

	//Ab hier beginnen die Getter
	/**
	 * @name getMenuDateiLaden()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuDateiLaden.
	 */
	public JMenuItem getMenuDateiLaden()
	{
		return menuDateiLaden;
	}

	/**
	 * @name getMenuDateiSpeichern()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuDateiSpeichern.
	 */
	public JMenuItem getMenuDateiSpeichern()
	{
		return menuDateiSpeichern;
	}

	/**
	 * @name getMenuDateiBeenden()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuDateiBeenden.
	 */
	public JMenuItem getMenuDateiBeenden()
	{
		return menuDateiBeenden;
	}

	/**
	 * @name getMenuKalenderFeiertage()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuKalenderFeiertage.
	 */
	public JMenuItem getMenuKalenderFeiertage()
	{
		return menuKalenderFeiertage;
	}

	/**
	 * @name getMenuKalenderFeiertageOhne()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuKalenderFeiertageOhne.
	 */
	public JMenuItem getMenuKalenderFeiertageOhne()
	{
		return menuKalenderFeiertageOhne;
	}

	/**
	 * @name getMenuKalenderWann()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuKalenderWann.
	 */
	public JMenuItem getMenuKalenderWann()
	{
		return menuKalenderWann;
	}

	/**
	 * @name getMenuLookFeelWindows()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuLookFeelWindows.
	 */
	public JMenuItem getMenuLookFeelWindows()
	{
		return menuLookFeelWindows;
	}

	/**
	 * @name getMenuLookFeelMetal()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuLookFeelMetal.
	 */
	public JMenuItem getMenuLookFeelMetal()
	{
		return menuLookFeelMetal;
	}

	/**
	 * @name getMenuLookFeelMotif()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuLookFeelMotif.
	 */
	public JMenuItem getMenuLookFeelMotif()
	{
		return menuLookFeelMotif;
	}

	/**
	 * @name getMenuHilfeAutor()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuHilfeAutor.
	 */
	public JMenuItem getMenuHilfeAutor()
	{
		return menuHilfeAutor;
	}

	/**
	 * @name getMenuHilfeHelp()
	 * @return JMenuItem
	 * @description Der Getter des Attributes menuHilfeHelp.
	 */
	public JMenuItem getMenuHilfeHelp()
	{
		return menuHilfeHelp;
	}
	
}