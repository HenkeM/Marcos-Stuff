/**
 * @filename Benutzeroberflaeche
 * @java jdk1.7.0_17
 * @compilerversion 7.0.170.2
 * @author Marco Henke
 * @since 05.06.2014
 * @lastModified 29.06.2014
 * @method Benutzeroberflaeche(KalenderInterface,int)
 * @method aendereAusgabeMonat(int,int,boolean,boolean)
 * @method aendereAusgabeJahr(int,boolean,boolean)
 * @method wechselBild(int)
 * @method initialisiereZusatzWindow()
 * @method getJahrComboBox()
 * @method getMonatComboBox()
 * @method getGeburtstageCheckBox()
 * @method getWochenanfangMontagCheckBox()
 * @method getRadioButtonOptionen()
 * @method getAusgabe()
 * @method getAusgabeDoc()
 * @method getZusatzWindow()
 * 
 * @description Diese Klasse dient fuer den Datentyp Zelle. Dieser Datentyp enthaelt saemtliche
 * 				Informationen ueber eine Zelle des Monatsblattes, die vom View benoetigt werden
 * 				um die Zelle darzustellen.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Calendar;

import model.Kalender;
import model.Konstanten;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import controller.KalenderInterface;
import controller.KalenderRefreshActionListener;
import controller.MenuActionListener;
import view.ZusatzWindow;

public class Benutzeroberflaeche extends JFrame
{
	/**
	 * @Attribute Kalender kalender
	 * @Attribute Container cpane
	 * @Attribute MenuActionListener menuAL
	 * @Attribute KalenderRefreshActionListener
	 * @Attribute KalenderblattAusgabe ausgabeDoc
	 * @Attribute ZusatzWindow zusatzWindow
	 * @Attribute JComboBox<Integer> jahrComboBox
	 * @Attribute JComboBox<String> monatComboBox
	 * @Attribute JLabel bild
	 * @Attribute JCheckBox geburtstageCheckBox
	 * @Attribute JCheckBox wochenanfangMontagCheckBox
	 * @Attribute JRadioButton[] radioButtonOptionen
	 * @Attribute JTextPane ausgabe
	 * */
	
	private static final long serialVersionUID = 1L;
	private Kalender kalender;
	private Container cpane;
	private MenuActionListener menuAL;
	private KalenderRefreshActionListener refreshAL;
	private KalenderblattAusgabe ausgabeDoc;
	private ZusatzWindow zusatzWindow;
	
	private JComboBox<Integer> jahrComboBox=new JComboBox<Integer>();
	private JComboBox<String> monatComboBox=new JComboBox<String>();
	
	private JLabel bild = new JLabel();
	
	private JCheckBox geburtstageCheckBox=new JCheckBox();
	private JCheckBox wochenanfangMontagCheckBox=new JCheckBox();
	
	private JRadioButton[] radioButtonOptionen = new JRadioButton[4];

	private JTextPane ausgabe = new JTextPane();
	
	/**
	 * @name Benutzeroberflaeche(KalenderInterface, int)
	 * @param kalenderInterface
	 * @param jahresIntervall
	 * @description Der Konstruktor der Klasse. Bekommt als Parameter ein Jahresintervall fuer die
	 * 				Auswahl an Jahren im Kalender und das KalenderInterface, aus dem sich
	 * 				die Klasse alle benoetigten MVC Instanzen holt.
	 */
	public Benutzeroberflaeche(KalenderInterface kalenderInterface, int jahresIntervall)
	{
		this.kalender=kalenderInterface.getKalender();
		this.menuAL=kalenderInterface.getMenuAL();
		this.refreshAL=kalenderInterface.getRefreshAL();
		grundeinstellungenFenster();
		initialisiereWidgets(jahresIntervall);
		bindeWidgetsEin();
		setStarteinstellungen();
		bindeActionListenerEin();
		this.pack();
	}

	/**
	 * @name grundeinstellungenFenster()
	 * @description Diese Methode setzt die Grundeinstellungen fuer das Fenster
	 */
	private void grundeinstellungenFenster()
	{
		this.setTitle("Kalender");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * @name initialisiereWidgets(int)
	 * @param jahresIntervall
	 * @description Diese Methode initialisiert die Widgets des Fensters mit ihren Anfangswerten
	 * 				und Inhalten.
	 */
	private void initialisiereWidgets(int jahresIntervall)
	{
		ButtonGroup radioButtonOptionenGroup=new ButtonGroup();
		
		//fuellen der ComboBoxen
		for(int i=1;i<Konstanten.MONATSNAME.length;i++)
		{
			monatComboBox.addItem(Konstanten.MONATSNAME[i]);
		}
		int jahr=Calendar.getInstance().get(Calendar.YEAR);
		for(int i=jahr-jahresIntervall;i<jahr+jahresIntervall;i++)
		{
			jahrComboBox.addItem(i);
		}
		//benennen und gruppieren der Radiobuttons
		for(int i=0;i<radioButtonOptionen.length;i++)
		{
			radioButtonOptionen[i]=new JRadioButton();
			radioButtonOptionenGroup.add(radioButtonOptionen[i]);
		}
		radioButtonOptionen[0].setText("Kalender fuer das ganze Jahr");
		radioButtonOptionen[1].setText("Monatsblatt");
		radioButtonOptionen[2].setText("Jahreskalender mit Feiertagen");
		radioButtonOptionen[3].setText("Monatsblatt mit Feiertagen");
		//benennen der CheckBoxen
		geburtstageCheckBox.setText("Geburtstage anzeigen?");
		wochenanfangMontagCheckBox.setText("Woche mit Montag beginnen?");
		//konfigurieren des Ausgabefeldes
		ausgabe.setMargin(new Insets(5,5,5,5));
		ausgabe.setEditable(false);
		ausgabe.setBackground(Color.WHITE);
		ausgabe.setFont(new Font("Courier New", Font.PLAIN, 12));
		//ausgabe.setContentType("rtf");
		//konfiguriere Labe fuer das Bild
		bild.setPreferredSize(new Dimension(320,180));
	}
	
	/**
	 * @name bindeWidgetsEin()
	 * @description Diese Methode bindet die Widgets in das Fenster ein.
	 */
	private void bindeWidgetsEin()
	{
		//einbinden der MenuLeisteKalender
		this.setJMenuBar(new MenuLeisteKalender(menuAL));
		cpane=this.getContentPane();
		cpane.setLayout(new BorderLayout());
		//einbinden der einzelnen Panels in die cpane
		cpane.add(jahrMonatEingabe(), BorderLayout.WEST);
		cpane.add(optionen(), BorderLayout.CENTER);
		cpane.add(ausgabefeld(), BorderLayout.SOUTH);
	}
	
	/**
	 * @name jahrMonatEingabe()
	 * @return JPanel
	 * @description Zusammenbauen des Panels fuer die Jahr- und Monatseingabe
	 */
	private JPanel jahrMonatEingabe()
	{
		//initialisieren des JPanels
		JPanel panel = new JPanel();
		JLabel jahrLabel=new JLabel("Jahr:");
		JLabel monatLabel=new JLabel("Monat:");
		GridBagConstraints constr = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//einbinden der Widgets
		constr.gridy=10;
		constr.gridwidth=GridBagConstraints.RELATIVE;
		panel.add(monatLabel,constr);
		constr.gridwidth=GridBagConstraints.REMAINDER;
		panel.add(jahrLabel,constr);
		
		constr.gridy=20;
		constr.gridwidth=GridBagConstraints.RELATIVE;
		panel.add(monatComboBox,constr);
		constr.gridwidth=GridBagConstraints.REMAINDER;
		panel.add(jahrComboBox,constr);
		
		constr.gridy=30;
		panel.add(bild,constr);
		
		return panel;
	}
	
	/**
	 * @name optionen()
	 * @return JPanel
	 * @description Zusammenbauen des Panels fuer die Optionen
	 */
	private JPanel optionen()
	{
		//initialisieren des JPanels
		JPanel panel = new JPanel();
		JLabel optionenLabel = new JLabel("Kalenderausgabe:");
		GridBagConstraints constr = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//einbinden der Widgets
		constr.anchor=GridBagConstraints.WEST;
		constr.gridy=10;
		panel.add(optionenLabel,constr);
		for(int i=0;i<radioButtonOptionen.length;i++)
		{
			constr.gridy+=10;
			panel.add(radioButtonOptionen[i],constr);
		}
		constr.gridy+=10;
		panel.add(geburtstageCheckBox,constr);
		constr.gridy+=10;
		panel.add(wochenanfangMontagCheckBox,constr);
		
		return panel;
	}
	
	/**
	 * @name ausgabefeld()
	 * @return JPanel
	 * @description Zusammenbauen des Panels fuer die Ausgabe
	 */
	private JPanel ausgabefeld()
	{
		//initialisieren des JPanel
		JPanel panel = new JPanel();
		JScrollPane ausgabeScroll = new JScrollPane(ausgabe);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		ausgabeScroll.setPreferredSize(new Dimension(400,300));
		//einbinden der Widgets
		panel.add(ausgabeScroll);
		
		return panel;
	}

	/**
	 * @name bindeActionListenerEin()
	 * @description Diese Methode bindet saemmtliche ActionListener an die Widgets ein.
	 */
	private void bindeActionListenerEin()
	{
		//refreshAL anbinden
		jahrComboBox.addActionListener(refreshAL);
		monatComboBox.addActionListener(refreshAL);
		geburtstageCheckBox.addActionListener(refreshAL);
		wochenanfangMontagCheckBox.addActionListener(refreshAL);
		for(int i=0;i<radioButtonOptionen.length;i++)
		{
			radioButtonOptionen[i].addActionListener(refreshAL);
		}
	}
	
	/**
	 * @name setStarteinstellungen()
	 * @description Diese Methode setzt die Einstellungen beim Start des Fensters.
	 */
	private void setStarteinstellungen()
	{
		Calendar cal= Calendar.getInstance();
		//setze Startwerte
		monatComboBox.setSelectedItem(Konstanten.MONATSNAME[cal.get(Calendar.MONTH)+1]);
		jahrComboBox.setSelectedItem(cal.get(Calendar.YEAR));
		radioButtonOptionen[1].setSelected(true);
		wochenanfangMontagCheckBox.setSelected(true);
	}
	
	/**
	 * @name aendereAusgabeMonat(int, int, boolean, boolean)
	 * @param jahr
	 * @param monat
	 * @param modus
	 * @param geburtstagEintragen
	 * @description Diese Methode kuemmert sich um das aendern der Ausgabe in ein Monatsblatt
	 */
	public void aendereAusgabeMonat(int jahr, int monat, boolean modus, boolean geburtstagEintragen)
	{
		ausgabe.removeAll();
		ausgabeDoc=new KalenderblattAusgabe(kalender).getMonatsblatt(jahr, monat, modus, geburtstagEintragen);
		ausgabe.setDocument(ausgabeDoc);
	}
	
	/**
	 * @name aendereAusgabeJahr(int, boolean, boolean)
	 * @param jahr
	 * @param modus
	 * @param geburtstagEintragen
	 * @description Diese Methode kuemmert sich um das aendern der Ausgabe in einen Jahreskalender
	 */
	public void aendereAusgabeJahr(int jahr, boolean modus, boolean geburtstagEintragen)
	{
		ausgabe.removeAll();
		ausgabeDoc=new KalenderblattAusgabe(kalender).getJahresblatt(jahr, modus, geburtstagEintragen);
		ausgabe.setDocument(ausgabeDoc);
	}
	
	/**
	 * @name wechselBild(int)
	 * @param monat
	 * @description Diese Methode kuemmert sich um das Auswechseln des Bildes zum Monat
	 */
	public void wechselBild(int monat)
	{
		ImageIcon picture = new ImageIcon(".\\bilder\\"+monat+".jpg");
		int breite=bild.getPreferredSize().width;
		int hoehe=bild.getPreferredSize().height;
		picture.setImage(picture.getImage().getScaledInstance(breite, hoehe, Image.SCALE_DEFAULT));
		bild.setIcon(picture);
	}
	
	
	
	
	
	/**
	 * @name initialisiereZusatzWindow()
	 * @description Diese Methode startet ein neues ZusatzWindow.
	 */
	public void initialisiereZusatzWindow()
	{
		zusatzWindow=new ZusatzWindow(kalender);
	}
	
	//Ab hier beginnen die Getter
	/**
	 * @name getJahrComboBox()
	 * @return JComboBox<Integer>
	 * @description Getter des Attributes jahrComboBox.
	 */
	public JComboBox<Integer> getJahrComboBox()
	{
		return jahrComboBox;
	}

	/**
	 * @name getMonatComboBox()
	 * @return JComboBox<String>
	 * @description Getter des Attributes monatComboBox.
	 */
	public JComboBox<String> getMonatComboBox()
	{
		return monatComboBox;
	}

	/**
	 * @name getGeburtstageCheckBox()
	 * @return JCheckBox
	 * @description Getter des Attributes geburtstageCheckBox.
	 */
	public JCheckBox getGeburtstageCheckBox()
	{
		return geburtstageCheckBox;
	}

	/**
	 * @name getWochenanfangMontagCheckBox
	 * @return JCheckBox
	 * @description Getter des Attributes wochenanfangMontagCheckBox.
	 */
	public JCheckBox getWochenanfangMontagCheckBox()
	{
		return wochenanfangMontagCheckBox;
	}

	/**
	 * @name getRadioButtonOptionen()
	 * @return JRadioButton[]
	 * @description Getter des Attributes radioButtonOptionen.
	 */
	public JRadioButton[] getRadioButtonOptionen()
	{
		return radioButtonOptionen;
	}

	/**
	 * @name getAusgabe()
	 * @return JTextPane
	 * @description Getter des Attributes ausgabe.
	 */
	public JTextPane getAusgabe()
	{
		return ausgabe;
	}

	/**
	 * @name getAusgabeDoc()
	 * @return KalenderblattAusgabe
	 * @description Getter des Attributes ausgabeDoc
	 */
	public KalenderblattAusgabe getAusgabeDoc()
	{
		return ausgabeDoc;
	}

	/**
	 * @name getZusatzWindow()
	 * @return ZusatzWindow
	 * @description Getter des Attributes zusatzWindow
	 */
	public ZusatzWindow getZusatzWindow()
	{
		return zusatzWindow;
	}
}
