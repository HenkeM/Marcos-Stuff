/**
 * @filename KalenderRefreshActionListener
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 22.06.2014
 * @lastModified 29.06.2014
 * @method KalenderRefreshActionListener(KalenderInterface kalenderInterface)
 * @method actionPerformed(ActionEvent e)
 * @method refresh();
 * 
 * @description Diese Klasse ist ein ActionListener, der die Ausgabe des Kalenders in der Benutzeroberflaeche
 * 				erneuert wann immer actionPerformed(ActionEvent) aufgerufen wird. Außerdem stellt er mit
 * 				der Methode refresh() eine manuelle Moeglichkeit zum erneuern der Ausgabe zur Verfuegung.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Kalender;

public class KalenderRefreshActionListener implements ActionListener
{
	/**
	 * @Attribute Benutzeroberflaeche gui
	 * @Attribute Kalender kalender
	 */
	private Kalender kalender;
	private KalenderInterface ki;
	
	/**
	 * @name KalenderRefreshActionListener(KalenderInterface)
	 * @param kalenderInterface : 	Schnittstelle fuer alle anderen MVC Instanzen.
	 * @description Der Konstruktor der Klasse. Holt sich alle Benoetigten Attribute aus dem KalenderInterface.
	 */
	public KalenderRefreshActionListener(KalenderInterface kalenderInterface)
	{
		this.kalender=kalenderInterface.getKalender();
		this.ki=kalenderInterface;
	}

	/**
	 * @name actionPerformed(ActionEvent)
	 * @param e : 	Vom Observable uebergebenes Event.
	 * @description Die Implementierung der Methode actionPerformed(Event) vom der Superclass ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		refresh();
	}
	
	/**
	 * @name refresh()
	 * @description Diese Methode erneuert die Ausgabe der Benutzeroberflaeche.
	 */
	public void refresh()
	{	
		if(ki.getFenster().getRadioButtonOptionen()[0].isSelected())
		{
			kalender.setWochenanfangMontag(ki.getFenster().getWochenanfangMontagCheckBox().isSelected());
			int jahr=(int) ki.getFenster().getJahrComboBox().getSelectedItem();
			int monat=ki.getFenster().getMonatComboBox().getSelectedIndex()+1;
			boolean geburtstagEintragen=ki.getFenster().getGeburtstageCheckBox().isSelected();
			ki.getFenster().aendereAusgabeJahr(jahr, false, geburtstagEintragen);
			ki.getFenster().wechselBild(monat);
		}
		if(ki.getFenster().getRadioButtonOptionen()[1].isSelected())
		{
			kalender.setWochenanfangMontag(ki.getFenster().getWochenanfangMontagCheckBox().isSelected());
			int jahr=(int) ki.getFenster().getJahrComboBox().getSelectedItem();
			int monat=ki.getFenster().getMonatComboBox().getSelectedIndex()+1;
			boolean geburtstagEintragen=ki.getFenster().getGeburtstageCheckBox().isSelected();
			ki.getFenster().aendereAusgabeMonat(jahr, monat, false, geburtstagEintragen);
			ki.getFenster().wechselBild(monat);
		}
		if(ki.getFenster().getRadioButtonOptionen()[2].isSelected())
		{
			kalender.setWochenanfangMontag(ki.getFenster().getWochenanfangMontagCheckBox().isSelected());
			int jahr=(int) ki.getFenster().getJahrComboBox().getSelectedItem();
			int monat=ki.getFenster().getMonatComboBox().getSelectedIndex()+1;
			boolean geburtstagEintragen=ki.getFenster().getGeburtstageCheckBox().isSelected();
			ki.getFenster().aendereAusgabeJahr(jahr, true, geburtstagEintragen);
			ki.getFenster().wechselBild(monat);
		}
		if(ki.getFenster().getRadioButtonOptionen()[3].isSelected())
		{
			kalender.setWochenanfangMontag(ki.getFenster().getWochenanfangMontagCheckBox().isSelected());
			int jahr=(int) ki.getFenster().getJahrComboBox().getSelectedItem();
			int monat=ki.getFenster().getMonatComboBox().getSelectedIndex()+1;
			boolean geburtstagEintragen=ki.getFenster().getGeburtstageCheckBox().isSelected();
			ki.getFenster().aendereAusgabeMonat(jahr, monat, true, geburtstagEintragen);
			ki.getFenster().wechselBild(monat);
		}
	}
}
