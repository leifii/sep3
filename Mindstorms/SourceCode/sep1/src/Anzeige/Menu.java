/**
 * @author ${Mark Thorben}
 * 
 *
 * 
 */
package Anzeige;
/* Die Klasse Menü visualisiet die Komplette Menüführung 
 * Zusätzlich initialisiert sie die Clients mit den passenden Ip-Adressen und Roboter Modi
 * Sowohl der KeyListener als der ActionListener(für die Buttons) befinden sich in dieser Klasse 
 * Jedes Menü wird über ein eigenes JPanel realisiert
 * Methoden zum erstellen der Menüs heißen "erzeuge"+ das passende Menü. Diese Methoden stehen auch alle im Interface IMenu
 * Das Spielfeld Panel (thorbensPanel) wird von der Klasse AnzeigeSpielfeld gestaltet 
 * 
 * 
 * Die Methoden "create(String a)" und "eurezugeSpielfeld" wurden 1zu1 von Thorben übernehmen .
 * Die Methode "setzeAnzeigeSpielfeld" von Thorben übernommen und verändert...veränderungen sind extra Kommentiert
 * 
 */

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import KommunikationPC.Clientinit;

import KommunikationPC.QueueHandler;
import Positionen.Position;
import PowerUps.PowerUp;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;



public class Menu extends JFrame implements IMenu , ActionListener, KeyListener  {
//	private static final long serialVersionUID = 3498199861043935813L; //WIESO??? Manchmal gibts ne Fehlermeldung das das fehlt manchmal nicht ? weiß jemand eine Lösung ?
	ImageIcon Herz=new ImageIcon("herz.gif");
	JLabel Leben1=new JLabel(Herz);
	JLabel Leben2=new JLabel(Herz);  
	JLabel Leben3=new JLabel(Herz);  
	JLabel[] ele;
	
	public int leben =3;
	public static boolean spielEnde = false;
	public static boolean pause = true ;
	public static int richtung = 0 ; 
	public boolean inputFinished = false;
	public static boolean kollidiertSepman  = false;
	public static boolean kollidiertTracer  = false;
	public static boolean kollidiertDefender=false;
	public static boolean kollidiertRandom  = false;
	
	private ImageIcon linie1=new ImageIcon("Linie.png");
    private	ImageIcon linie2=new ImageIcon("Linie2.png");
	private ImageIcon iiPowerup = new ImageIcon("Powerup.png");
	private ImageIcon iiSepman=new ImageIcon("Sepman.png");  
	private ImageIcon iiRandom=new ImageIcon("zufall.png"); 
	private ImageIcon iiGuard=new ImageIcon("Verteidiger.png");  
	private ImageIcon iiTracker=new ImageIcon("Verfolger.png"); 
	
	private String[] roboterBelegung = new String[4];
	private String[] roboterIPs = new String[4];
	private String comboBoxInhalt[] = {"Bitte Modus wählen", "SepMAN", "Verfolger", "Verteidiger", "Zufall"};
	
	private JPanel display = new JPanel();                                                //Deklaration aller Objekte und Variablen
	private JPanel startDisplay = new JPanel();
	private JPanel siegDisplay = new JPanel();
	private JPanel niederlageDisplay = new JPanel();
	private JPanel spielvorbereitungsDisplay = new JPanel();
	private JPanel thorbensPanel= new JPanel();
	private JPanel pausenDisplay = new JPanel();
	
	private JLabel lbUeberschrift = new JLabel("SEPMAN");
	private JLabel lbBeschriftung1= new JLabel("ROBOTER 1:");
	private JLabel lbBeschriftung2= new JLabel("ROBOTER 2:");
	private JLabel lbBeschriftung3= new JLabel("ROBOTER 3:");
	private JLabel lbBeschriftung4= new JLabel("ROBOTER 4:");
	private JLabel lbIp1Beschriftung = new JLabel("1. IP-Adresse");
	private JLabel lbip1Punkt1= new JLabel(".");
	private JLabel lbip1Punkt2= new JLabel(".");
	private JLabel lbip1Punkt3= new JLabel(".");
	private JLabel lbip2Beschriftung= new JLabel("2. IP-Adresse");
	private JLabel lbip2Punkt1= new JLabel(".");
	private JLabel lbip2Punkt2= new JLabel(".");
	private JLabel lbip2Punkt3= new JLabel(".");
	private JLabel lbip3Beschriftung= new JLabel("3. IP-Adresse");
	private JLabel lbip3Punkt1= new JLabel(".");
	private JLabel lbip3Punkt2= new JLabel(".");
	private JLabel lbip3Punkt3= new JLabel(".");
	private JLabel lbip4Beschriftung= new JLabel("4. IP-Adresse");
	private JLabel lbip4Punkt1= new JLabel(".");
	private JLabel lbip4Punkt2= new JLabel(".");
	private JLabel lbip4Punkt3= new JLabel(".");
	private JLabel lbHinweis1 = new JLabel("Bitte positioniere die Roboter richtig !!");
	private JLabel lbPowerUp1 = new JLabel(iiPowerup); 
	private JLabel lbPowerUp2 = new JLabel(iiPowerup); 
	private JLabel lbPowerUp3 = new JLabel(iiPowerup); 
	private JLabel lbPowerUp4 = new JLabel(iiPowerup); 
	private JLabel line2=new JLabel(linie2);
	private JLabel line1=new JLabel(linie1);
	private JLabel jSepman = new JLabel(iiSepman);                                                  
	private JLabel jRandom = new JLabel(iiRandom);                              
	private JLabel jGuard = new JLabel(iiGuard);                                             
	private JLabel jTracker = new JLabel(iiTracker); 
	
	private JTextField tfIp1Feld1 = new JTextField("192");
	private JTextField tfIp1Feld2 = new JTextField("168");
	private JTextField tfIp1Feld3 = new JTextField("2");
	private JTextField tfIp1Feld4 = new JTextField("106");
	private JTextField tfIp2Feld1 = new JTextField("192");
	private JTextField tfIp2Feld2 = new JTextField("168");
	private JTextField tfIp2Feld3 = new JTextField("2");
	private JTextField tfIp2Feld4 = new JTextField("1");
	private JTextField tfIp3Feld1 = new JTextField("192");
	private JTextField tfIp3Feld2 = new JTextField("168");
	private JTextField tfIp3Feld3 = new JTextField("3");
	private JTextField tfIp3Feld4 = new JTextField("1");
	private JTextField tfIp4Feld1 = new JTextField("192");
	private JTextField tfIp4Feld2 = new JTextField("168");
	private JTextField tfIp4Feld3 = new JTextField("4");
	private JTextField tfIp4Feld4 = new JTextField("1");
	
	private JButton btnAuswahlfenster = new JButton();
	private JButton btnStart = new JButton();
	private JButton btnEnde = new JButton();
	private JButton btnEnde2 = new JButton();
	private JButton btnZurueck = new JButton();
	private JButton btnStartfinal = new JButton();
	private JButton btnSpielfortsetzen = new JButton();
	private JButton btnNeuesSpiel = new JButton();
	
	private JComboBox cbAuswahl1= new JComboBox(comboBoxInhalt);
	private JComboBox cbAuswahl2= new JComboBox(comboBoxInhalt);
	private JComboBox cbAuswahl3= new JComboBox(comboBoxInhalt);
	private JComboBox cbAuswahl4= new JComboBox(comboBoxInhalt);
	
	private int[] pos=new int[4];
	private int Sepman;
	private int Defender;
	private int Random;
	private int Tracer;
	private int Größe;
	private int Länge;
	private int Breite;
	private  Plane[] Spielbrett;
	private  Planeinit Spiel;
	 boolean[] besucht=new boolean[(Länge*Breite)*2];
	
	public static QueueHandler queue;	//Ergaenzt durch Tristan! QueueHandler, um Befehle in das ByteArray zu laden. Initialisierung in setClients()
	public static PowerUp powerUp;     //Ergaenzt durch Tristan! Objekt vom Typ "PowerUp". Initialisierung in setClients()
	static Position positions;    //Ergaenzt durch Tristan. Objekt vom  Typ "Position" um Positionen der Roboter abzurufen.
	

	/*
	 *  Konstruktor	
	 */
	public Menu() {
		super();
		initComponents();

	}
/*
 * TEIL 1 
 * Hier werden die einzelnen JPanels für das Menü erstellt. 
 * 
 * 
 * 
 * 
 * In Teil 2 laufen die Spielaktionen ab
 * 
 * 
 * 
 * 
 * 	
 */
	
/*
 *  Initialisierung des Fenster + Aufrufen des Startbildschirms*
 *  
 */
	private void initComponents() {
	
	//JFrame.MAXIMIZED_BOTH;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(1000,720);
	setTitle("SEPman");
	setzeAuswahldisplay ();
//	setzeStartbildschirm(); 
//	setzeNiederlageBildschirm();
//	setzeSiegbildschirm();
//	setzePausenBildschrim();
	validate();
}


/*
 *  Diese Methoden erstell den Startbildschirm 
 * 
 */
	private void setzeStartbildschirm(){
	startDisplay.setLayout(null);
	startDisplay.setVisible(true);
	startDisplay.setBackground(Color.gray);
	startDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));	
	
	btnAuswahlfenster.setText("START");
	btnAuswahlfenster.setFont(new Font("Arial", Font.BOLD, 50));
	btnAuswahlfenster.setBounds(240, 200, 500, 75);
	btnAuswahlfenster.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));      
	btnAuswahlfenster.addActionListener(this);
	startDisplay.add(btnAuswahlfenster);
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 100));
	lbUeberschrift.setBounds(140, 20 , 700, 100);
	startDisplay.add(lbUeberschrift);
    getContentPane().add(startDisplay);
}

/*
 *  Methode zum erstellen des Auswahldisplays
 * 
 */
	private void setzeAuswahldisplay (){
    startDisplay.setVisible(false);
    spielvorbereitungsDisplay.setVisible(false);
    siegDisplay.setVisible(false);
	display.setVisible(true);
	display.setLayout(null);
	display.setBackground(Color.WHITE);
	display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 100));
	lbUeberschrift.setBounds(140, 20 , 700, 100);
	display.add(lbUeberschrift);
	
	btnStart.addActionListener(this);
	btnStart.setBounds(240, 140, 500, 75);
	btnStart.setText("Start");
	btnStart.setFont(new Font("Arial", Font.BOLD, 50));
	btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnStart.setBackground(Color.WHITE);
	display.add(btnStart);
	
	btnEnde.addActionListener(this);           
	btnEnde.setBounds(240, 235, 500, 75);
	btnEnde.setFont(new Font("Arial", Font.BOLD, 50));
	btnEnde.setText("Ende");
	btnEnde.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnEnde.setBackground(Color.white);
	display.add(btnEnde);
	
	lbBeschriftung1.setFont(new Font("Arial", Font.BOLD, 17));
	lbBeschriftung1.setBounds(50, 350, 110, 20);
	display.add(lbBeschriftung1);
	
	lbBeschriftung2.setFont(new Font("Arial", Font.BOLD, 17));
	lbBeschriftung2.setBounds(50, 390, 110, 20);
	display.add(lbBeschriftung2);
	lbBeschriftung3.setFont(new Font("Arial", Font.BOLD, 17));
	
	lbBeschriftung3.setBounds(50, 430, 110, 20);
	display.add(lbBeschriftung3);
	lbBeschriftung4.setFont(new Font("Arial", Font.BOLD, 17));
	
	lbBeschriftung4.setBounds(50, 470, 110, 20);
	display.add(lbBeschriftung4);
	
	cbAuswahl1.setSelectedIndex(0);
    cbAuswahl1.setBounds(170, 350, 200, 30);
	cbAuswahl1.addActionListener(this);               
	display.add(cbAuswahl1);
	
	cbAuswahl2.setSelectedIndex(0);
	cbAuswahl2.setBounds(170, 390, 200, 30);
	cbAuswahl2.addActionListener(this);               
	display.add(cbAuswahl2);
	
	cbAuswahl3.setSelectedIndex(0);
	cbAuswahl3.setBounds(170, 430, 200, 30);
	cbAuswahl3.addActionListener(this);                
	display.add(cbAuswahl3);
	
	cbAuswahl4.setSelectedIndex(0);
	cbAuswahl4.setBounds(170, 470, 200, 30);
	cbAuswahl4.addActionListener(this);               
	display.add(cbAuswahl4);
	
/*
 * 	1.Roboter IP-Auswahl Darstellung
 */
	lbIp1Beschriftung.setBounds(440, 350, 140, 20);
	lbIp1Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbIp1Beschriftung);
	
	lbip1Punkt1.setBounds(610, 350, 10, 20);
	lbip1Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip1Punkt1);	
	
    lbip1Punkt2.setBounds(660, 350, 10, 20);
    lbip1Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
    display.add(lbip1Punkt2);
    
	lbip1Punkt3.setBounds(710, 350, 10, 20);
	lbip1Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip1Punkt3);
    
	tfIp1Feld1.setBounds(570, 350, 40, 20);
	tfIp1Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld1);	
	
	tfIp1Feld2.setBounds(620, 350, 40, 20);
	tfIp1Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld2);	
	
	tfIp1Feld3.setBounds(670, 350, 40, 20);
	tfIp1Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld3);
	
	tfIp1Feld4.setBounds(720, 350, 40, 20);
	tfIp1Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld4);
    
/*
 *     2. Roboter Ip Auswahl Darstellung
 */
	lbip2Beschriftung.setBounds(440, 390, 140, 20);
	lbip2Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Beschriftung);	
	
	lbip2Punkt1.setBounds(610, 390, 10, 20);
	lbip2Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Punkt1);	
	
	lbip2Punkt2.setBounds(660, 390, 10, 20);
	lbip2Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Punkt2);	
	
	lbip2Punkt3.setBounds(710, 390, 10, 20);
	lbip2Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Punkt3);
	
	tfIp2Feld1.setBounds(570, 390, 40, 20);
	tfIp2Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));	
	display.add(tfIp2Feld1);	
	
    tfIp2Feld2.setBounds(620, 390, 40, 20);
	tfIp2Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    display.add(tfIp2Feld2);	
    
	tfIp2Feld3.setBounds(670, 390, 40, 20);
	tfIp2Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp2Feld3);
	
	tfIp2Feld4.setBounds(720, 390, 40, 20);
	tfIp2Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp2Feld4);
	
/*
 * 	   3.Roboter IP Auswahl Darstellung
 */
	
	lbip3Beschriftung.setBounds(440, 430, 140, 20);
	lbip3Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Beschriftung);	
	
	lbip3Punkt1.setBounds(610, 430, 10, 20);
	lbip3Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Punkt1);	
	
	lbip3Punkt2.setBounds(660, 430, 10, 20);
	lbip3Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Punkt2);	
	
	lbip3Punkt3.setBounds(710, 430, 10, 20);
	lbip3Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Punkt3);
	
	tfIp3Feld1.setBounds(570, 430, 40, 20);
	tfIp3Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld1);	
	
	tfIp3Feld2.setBounds(620, 430, 40, 20);
	tfIp3Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld2);	
	
	tfIp3Feld3.setBounds(670, 430, 40, 20);
	tfIp3Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld3);
	
	tfIp3Feld4.setBounds(720, 430, 40, 20);
	tfIp3Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld4);
	
/*
 * 	4. Roboter IP Auswahl Darstellung
 */
	
	lbip4Beschriftung.setBounds(440, 470, 140, 20);
	lbip4Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Beschriftung);	
	
	lbip4Punkt1.setBounds(610, 470, 10, 20);
	lbip4Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Punkt1);	
	
	lbip4Punkt2.setBounds(660, 470, 10, 20);
	lbip4Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Punkt2);	
	
	lbip4Punkt3.setBounds(710, 470, 10, 20);
	lbip4Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Punkt3);
	
	tfIp4Feld1.setBounds(570, 470, 40, 20);
	tfIp4Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld1);	
	
	tfIp4Feld2.setBounds(620, 470, 40, 20);
	tfIp4Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld2);	
	
	tfIp4Feld3.setBounds(670, 470, 40, 20);
	tfIp4Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld3);
	
	tfIp4Feld4.setBounds(720, 470, 40, 20);
	tfIp4Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld4);
	getContentPane().add(display);
}

/*
 * 
 *  Methode zum erstellen des Spielvorbereitungsdisplay
 */
 private void setzeSpielvorbereitungsdisplay(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setVisible(false);
	niederlageDisplay.setVisible(false);
	
	spielvorbereitungsDisplay.setLayout(null);
	spielvorbereitungsDisplay.setVisible(true);
	spielvorbereitungsDisplay.setBackground(Color.WHITE);
	spielvorbereitungsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
	btnZurueck.addActionListener(this);
	spielvorbereitungsDisplay.add(btnZurueck);
	btnZurueck.setBounds(200, 435, 500, 75);
	btnZurueck.setFont(new Font("Arial", Font.BOLD, 50));
	btnZurueck.setText("Zurück");
	btnZurueck.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	spielvorbereitungsDisplay.add(btnStartfinal);
	btnStartfinal.setBounds(200, 340, 500, 75);
	btnStartfinal.setFont(new Font("Arial", Font.BOLD, 50));
	btnStartfinal.setText("Spiel starten !!");
	btnStartfinal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnStartfinal.addActionListener(this);
	
	spielvorbereitungsDisplay.add(lbHinweis1);
	lbHinweis1.setBounds(40, 100, 400, 100);
	lbHinweis1.setFont(new Font("Arial", Font.BOLD, 20));
	lbHinweis1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

	getContentPane().add(spielvorbereitungsDisplay);
}


/*
 * Methode zum erstellen des Siegdisplay
 * 
 */
 private void setzeSiegbildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setLayout(null);
	siegDisplay.setVisible(true);
	siegDisplay.setBackground(Color.GREEN);
	siegDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
    getContentPane().add(siegDisplay);
    
	spielvorbereitungsDisplay.add(	btnEnde2 );
	btnEnde2.setBounds(120, 370, 330, 100);
	btnEnde2.setText("Spiel Beenden");
	btnEnde2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnEnde2.setBackground(Color.WHITE);
	btnEnde2.addActionListener(this); 
	siegDisplay.add(btnEnde2);
	
	spielvorbereitungsDisplay.add(	btnNeuesSpiel );
	btnNeuesSpiel.setBounds(550, 370, 330, 100);
	btnNeuesSpiel.setText("Neues Spiel");
	btnNeuesSpiel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnNeuesSpiel.setBackground(Color.WHITE);
	btnNeuesSpiel.addActionListener(this);
	siegDisplay.add(btnNeuesSpiel);
}

/*
 * Methode zum erstellen des Niederlagedisplays(non-Javadoc)
 * 
 */
 private void setzeNiederlageBildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	niederlageDisplay.setLayout(null);
	niederlageDisplay.setVisible(true);
	niederlageDisplay.setBackground(Color.RED);
	niederlageDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
	niederlageDisplay.add(	btnEnde2 );
	btnEnde2.setBounds(120, 370, 330, 100);
	btnEnde2.setText("Spiel Beenden");
	btnEnde2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnEnde2.addActionListener(this); 
	
	niederlageDisplay.add(	btnNeuesSpiel );
	btnNeuesSpiel.setBounds(0, 370, 330, 100);
	btnNeuesSpiel.setText("Neues Spiel");
	btnNeuesSpiel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnNeuesSpiel.addActionListener(this);

    getContentPane().add(niederlageDisplay);

}
/*
 * Methode zum erstellen des Pausendisplays
 *
 */
 private void setzePausenBildschrim(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	thorbensPanel.setVisible(false);
	pausenDisplay.setLayout(null);
	pausenDisplay.setVisible(true);
	pausenDisplay.setBackground(Color.LIGHT_GRAY);
	pausenDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	pausenDisplay.add(lbHinweis1);
	lbHinweis1.setBounds(100, 100, 300, 100);
	lbHinweis1.setFont(new Font("Arial", Font.BOLD, 15));
	lbHinweis1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	pausenDisplay.add(btnSpielfortsetzen );
	btnSpielfortsetzen.setBounds(200, 340, 600, 200);
	btnSpielfortsetzen.setText("Spiel fortsetzen !!");
	btnSpielfortsetzen.setFont(new Font("Arial", Font.BOLD, 55));
	btnSpielfortsetzen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnSpielfortsetzen.setForeground(Color.GREEN);
	btnSpielfortsetzen.addActionListener(this); 
	
    getContentPane().add(pausenDisplay);
	
}

 private void setzeAnzeigeSpielfeld () throws IOException{
	create("Spielfeld.txt");
	setBounds(100, 100, (Länge+1)*150, (Breite+1)*150);
	 
	thorbensPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	thorbensPanel.setLayout(null);
	
	 ele=erzeugeSpielfeld();

	/*
	 * Hinzufügen des Sepmanns und der Geister
	 */

	jSepman.setBounds(ele[Sepman].getX() , ele[Sepman].getY(), 65, 65);   //Mark
	thorbensPanel.add(jSepman);                                              //Mark

	jRandom.setBounds(ele[Random].getX() , ele[Random].getY(), 60, 55);   //Mark
	thorbensPanel.add(jRandom);                                              //Mark
	
	jGuard.setBounds(ele[Defender].getX() , ele[Defender].getY(), 60, 55);   //Mark
	thorbensPanel.add(jGuard);                                                 //Mark
	   
	jTracker.setBounds(ele[Tracer].getX() , ele[Tracer].getY(), 60, 55);   //Mark
	thorbensPanel.add(jTracker);                                               //Mark
	
	/*
	 * Hinzufügen der PowerUps
	 */
	
	  
	lbPowerUp1.setBounds(ele[5].getX()+5, ele[5].getY()+4, 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp1);                // Hinzugefügt von Mark

	lbPowerUp2.setBounds(ele[16].getX()+5, ele[16].getY()+4, 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp2);                // Hinzugefügt von Mark

	lbPowerUp3.setBounds(ele[19].getX()+5, ele[19].getY()+4, 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp3);                // Hinzugefügt von Mark
		
	lbPowerUp4.setBounds(ele[30].getX()+5, ele[30].getY()+4, 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp4);                // Hinzugefügt von Mark
	
	
	Leben1.setSize(50, 50);
	Leben1.setLocation(10, 10);
	thorbensPanel.add(Leben1);
	
	     
	Leben2.setSize(50, 50);
	Leben2.setLocation(60, 10);
	thorbensPanel.add(Leben2);
	
	
	Leben3.setSize(50, 50);
	Leben3.setLocation(110, 10);
	thorbensPanel.add(Leben3);
	

	
	for(int z=0;z<Größe;z++)
	{
		if(Spielbrett[z].getNord()==true)
		{
			line2=new JLabel(linie2);
			line2.setLocation(ele[z].getX()+10, ele[z].getY()-95); // Überarbeitet von Mark
			line2.setSize(50, 100);
			thorbensPanel.add(line2);				
		}
		if(Spielbrett[z].getOst()==true)
		{
			line1=new JLabel(linie1);
			line1.setLocation(ele[z].getX()+60, ele[z].getY()); // Überarbeitet von Mark
			line1.setSize(100, 50);
			thorbensPanel.add(line1);
		}		
	}
	
}

/*
 * TEIL 2 
 * Hier laufen alle "Aktionen ab" 
 *   - Key/Action Listener
 *   - Schreiben in die Modi/IP Arrays
 *   - Eingabe Überprüfung
 *   - Spielfeld Steuerung
 *   - Main Methode
 *   - Initialisierung der Clients
 */
/*
 *  Überprüfung ob die Roboterauswahl vollständig ist und keine Modi doppelt ausgewählt wurden , funktioniert noch nicht korrekt
 */
 private boolean eingabeKorrekt() {
	boolean check = true;
	int k = 0;
	for (int i = 0; i< 4; i++){
		if( roboterBelegung[i]== null ){
			check = false;
		}
	}
	for (int i = 0; i < roboterBelegung.length-1; ++i){
		  for (int j = i+1; j < roboterBelegung.length; ++j){
		    if (roboterBelegung[i] == roboterBelegung[j]){      // Abfrage ob kein Modus doppelt ausgewählt wurde 
		    	check = false;
		    }
		  }
	}
	return  check;
}

/*
 *  Action Listener
 * 
 */
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource()== cbAuswahl1){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		modiSchreiben(0,msg);
	}
	
	else if (e.getSource()== cbAuswahl2){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		modiSchreiben(1,msg);	
	}
	
	else if (e.getSource()== cbAuswahl3){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		modiSchreiben(2,msg);
	}
	
	else if (e.getSource()== cbAuswahl4){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
        modiSchreiben(3,msg);

		}					
	else if (e.getSource()== btnEnde ){
		dispose();
	}
	else if (e.getSource()== btnEnde2 ){
		dispose();
	}
	else if (e.getSource() == btnAuswahlfenster){
		setzeAuswahldisplay(); 
	}
	else if( e.getSource()	== btnStart){
		if(eingabeKorrekt()){
		setzeSpielvorbereitungsdisplay();
		}
		else{
			lbUeberschrift.setText("FEHLERHAFTE EINGABE");
			lbUeberschrift.setForeground(Color.RED);
			lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 45));
		}
	}
	else if(e.getSource()== btnZurueck){
		setzeAuswahldisplay();  
	}
	else if(e.getSource() ==btnStartfinal){
		try {
			ipsSchreiben();                                                                   
			setClients();
			spielfeld();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	else if(e.getSource() ==btnSpielfortsetzen ){
		try {
			spielfeld();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	else if (e.getSource()== btnNeuesSpiel){
		setzeSpielvorbereitungsdisplay();
	}
}

private void modiSchreiben (int i ,String auswahl){
	switch(auswahl){
	case "SepMAN" : roboterBelegung[i] = "Sepman";
	break;
	case "Verfolger" : roboterBelegung[i] = "Tracer";
	break;
	case "Verteidiger" : roboterBelegung[i] = "Defender";
	break;
	case "Zufall" : roboterBelegung[i] = "Random";
	break;
	}
}

/*
*IP`S Aus den Textfeldern in das Array "roboterip" schreiben
*/
private void ipsSchreiben(){
	roboterIPs[0] =tfIp1Feld1.getText() + "." + tfIp1Feld2.getText() +"." +  tfIp1Feld3.getText()+ "."+ tfIp1Feld4.getText();
	roboterIPs[1] =tfIp2Feld1.getText() + "." + tfIp2Feld2.getText() +"." +  tfIp2Feld3.getText()+ "."+ tfIp2Feld4.getText();
	roboterIPs[2] =tfIp3Feld1.getText() + "." + tfIp3Feld2.getText() +"." +  tfIp3Feld3.getText()+ "."+ tfIp3Feld4.getText();
	roboterIPs[3] =tfIp4Feld1.getText() + "." + tfIp4Feld2.getText() +"." +  tfIp4Feld3.getText()+ "."+ tfIp4Feld4.getText();
			
}

/*
 * Anzeige des Spielfelds
 * KeyListener für das Spielfed
 * Hilsmethoden um das Spielfeld anzuzeigen von Thorben : 
 * "create"
 * "erzeugeSpielfeld"
 * 
 */

private void spielfeld() throws IOException{
	this.spielvorbereitungsDisplay.setVisible(false);
	this.pausenDisplay.setVisible(false);
	queue.spielStart();  //queue.addToQueue((byte)001);
	queue.anfangPause(); //queue.addToQueue((byte)003);
	pause = false; 
	this.spielEnde=false;
	queue.aktiviereGeist(1);  //queue.addToQueue((byte)021); 
	queue.aktiviereGeist(2);  //queue.addToQueue((byte)022);
	queue.aktiviereGeist(3);  //queue.addToQueue((byte)023);
	//queue.addToQueue((byte)024); 
	setzeAnzeigeSpielfeld();
	add(thorbensPanel);
	thorbensPanel.setVisible(true);
	thorbensPanel.setBackground(Color.white);
	setSize(1000, 1000);
	thorbensPanel.addKeyListener(this);
    thorbensPanel.setFocusable(true);	
}

public void keyPressed(KeyEvent l) {
	// TODO Auto-generated method stub
	if(l.getKeyCode() == KeyEvent.VK_UP){
	     richtung(4);
	     System.out.println(richtung);
	       
	     queue.norden();//queue.addToQueue((byte) 4);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.
		}
		else if(l.getKeyCode() == KeyEvent.VK_LEFT){
			richtung(5);
			System.out.println(richtung);
			
			queue.osten();//queue.addToQueue((byte) 5);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.
 
		}
		
		else if(l.getKeyCode() == KeyEvent.VK_DOWN){
			richtung(6);
			System.out.println(richtung);
			
			queue.sueden();//queue.addToQueue((byte) 6);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.
 
		}
		
		else if(l.getKeyCode() == KeyEvent.VK_RIGHT){
			richtung(7);
			System.out.println(richtung);
			
			queue.westen();//queue.addToQueue((byte) 7);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.

		}

		else if(l.getKeyCode() == KeyEvent.VK_SPACE){
			richtung(2);
			System.out.println(richtung); 
	
		}	
		else if (l.getKeyCode()== KeyEvent.VK_X){
			queue.addToQueue((byte) 300);
		}
}
/*
 * 
 * wird nicht gentutz
 */
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
/*
 * Wird nicht genutzt
 * 
 */
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


/*
 * Main Mehtoden 
 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu1 = new Menu();
					menu1.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
/* Methoden für Belgung der Richtungsvariable
 * 
*/
	private void richtung(int i){
		switch(i){
		case 4 : richtung =4;
		break;	
		case 6 :richtung =6;
		break;		
		case 5 :richtung =5;
		break;		
		case 7 :richtung =7;
		break;		
		case 2 :richtung =2;
		break;
		}
	}
/*
 *  
 */
@Override
public  int getTaste() {
	// TODO Auto-generated method stub
		return richtung;
	}

	/*
	 * Starten der Kommunikation	
	 */
private void setClients() throws IOException{
	
     Clientinit client1 = new  Clientinit();
     client1.clientInit(this.roboterIPs[0], this.roboterBelegung[0], 18415,1);
     //client1.clientInit("localhost", "Sepman", 13339, 1);
     queue = client1.queueHandler.getQueueHandler(); ////Ergaenzt durch Tristan! Fuegt Referenz zum QueueHandler hinzu.
     powerUp = new PowerUp();                        ////Ergaenzt durch Tristan! Erzeugt ein neues Objekt des Typs PowerUp
     powerUp.setPowerUp(powerUp);                    ////Ergaenzt durch Tristan! Ermoeglicht Zugriff auf Menu.powerUp aus anderen Klassen!
     positions = client1.pos.getPositionen();        ////Ergaenzt durch Tristan! Referenz auf Position in Clientinit
}
/*
 * Abfrage der 4 Kollisionsvariabeln solange das Spiel läuft
 */
public void kollisionserkennung(){

	while(pause == false){
		if ( kollidiertSepman ==true ){
			if (leben>1){
			setzePausenBildschrim();
			queue.anfangPause();//queue.addToQueue((byte)002);
			leben -=1;
			}
			else{
				setzeNiederlageBildschirm();
				queue.spielEnde();//queue.addToQueue((byte)127);
			}
		}
		else if( kollidiertTracer ==true){
			if (leben>1){
				setzePausenBildschrim();
				queue.anfangPause();//queue.addToQueue((byte)002);
				leben -=1;
				}
				else{
					setzeNiederlageBildschirm();
					queue.spielEnde();//queue.addToQueue((byte)127);
				}
		}
		else if (kollidiertDefender == true){
			if (leben>1){
				setzePausenBildschrim();
				queue.anfangPause();//queue.addToQueue((byte)002);
				leben -=1;
				}
				else{
					setzeNiederlageBildschirm();
					queue.spielEnde();//queue.addToQueue((byte)127);
				}
		}
		else if(kollidiertRandom ==true)
			if (leben>1){
				setzePausenBildschrim();
				queue.anfangPause();//queue.addToQueue((byte)002);
				leben -=1;
				}
				else{
					setzeNiederlageBildschirm();
					queue.spielEnde();//queue.addToQueue((byte)127);
				}
	}
}

/*
 * Kollision
 * 
 */
public void kollision () {
	if(leben >1){
		setzePausenBildschrim();
		queue.anfangPause();//queue.addToQueue((byte)002);
		
		leben -=1;
	}
	else{
		setzeNiederlageBildschirm();
		queue.deaktiviereGeist(1);//queue.addToQueue((byte)011);
		queue.deaktiviereGeist(2);//queue.addToQueue((byte)012);
		queue.deaktiviereGeist(3);//queue.addToQueue((byte)013);
		//queue.addToQueue((byte)014);
		queue.spielEnde();//queue.addToQueue((byte)127);
		this.spielEnde=true;
	}
	
}


private int convertKnoten(int i){
	switch(i){
	case 01 : return 1;
	case 02 : return 2;
	case 03 : return 3;
	case 04 : return 4;
	case 05 : return 5 ;
	case 06 : return 6;
	case 11 : return 7;
	case 12 : return 8;	
	case 13 : return 9;	
	case 14 : return 10;	
	case 15 : return 11;
	case 16 : return 12;
	case 21 : return 13 ;
	case 22 : return 14 ;
	case 23 : return 15;
	case 24 : return 16 ;
	case 25 : return 17;
	case 26 : return 18 ;
	case 31 : return 19;
	case 32 : return 20 ;
	case 33 : return 21 ;
	case 34 : return 22 ;
	case 35 : return 23 ;
	case 36 : return 24 ;
	case 41 : return 25;
	case 42 : return 26 ;
	case 43 : return 27;
	case 44 : return 28;		
	case 45 : return 29;		
	case 46 : return 30 ;		
	case 51 : return 31 ;	
	case 52 : return 32 ;		
	case 53 : return 33;	
	case 54 : return 24;
	case 55 : return 35 ;
	case 56 : return 36 ;
	}
	return 0;
	
}

public void Positionstracking (){
	
	
	this.Sepman   = positions.getPosSepman();
 
	this.Tracer   = positions.getPosTracer();
	this.Defender = positions.getPosDefender();
	this.Random   = positions.getPosRandom();	

}
 
private JLabel[] erzeugeSpielfeld()
{
	int zahl=1;
	int i=0;
	int k=0;
	ImageIcon iiKnoten = new ImageIcon("Knoten.png"); // Hinzugefügt von Mark 
	JLabel[] ele=new JLabel[Größe];
	for(JLabel j:ele)
	{
			if(k>=Breite)
			{
				k=0;
				i++;
			}
				j=new JLabel(iiKnoten); // Hinzugefügt von Mark 
				j.setBounds(100+k*150,100+i*150,65,65);
				thorbensPanel.add(j);
			zahl++;
			ele[zahl-2]=j;
			k++;	
	}
	return ele;
}
private void create(String a) throws IOException
{
	FileReader fr = new FileReader(a);
    BufferedReader br = new BufferedReader(fr);
    String hilfs = null;
    int[] name=new int[1];
    boolean[] Nord=new boolean[1];
    boolean[] Süd=new boolean[1];
    boolean[] West=new boolean[1];
    boolean[] Ost=new boolean[1];
    boolean[] power=new boolean[1];
    
    
    
    int u=0;
    hilfs=br.readLine();
	while(hilfs.contains("end")!=true){
    hilfs=br.readLine();
    if(hilfs.contains("#")==true)
    		{
    	
    		}
    else if(hilfs.contains("x")==true)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs2=ch[1];
    	char hilfs3=ch[3];
    	char hilfs4=ch[4];
    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs2);
    	String s2=String.valueOf(hilfs3)+String.valueOf(hilfs4);
    	Breite=Integer.parseInt(s1);
    	Länge=Integer.parseInt(s2);
    }
    else if(hilfs.length()<11 && hilfs.contains("end")==false&&hilfs.length()>=2)
    {
    	Größe= Integer.parseInt(hilfs);
    	name=new int[Größe];
    	Nord=new boolean[Größe];
 	    Süd=new boolean[Größe];
 	    West=new boolean[Größe];
 	    Ost=new boolean[Größe];
 	    power=new boolean[Größe];
    	
    }
    else if(hilfs.length()==11)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs2=ch[2];
    	char hilfs3=ch[4];
    	char hilfs4=ch[6];
    	char hilfs5=ch[8];
    	char hilfs6=ch[10];
    	String s=String.valueOf(hilfs1);
    	name[u]=Integer.parseInt(s);
    	String y="y";
    	
    	if(String.valueOf(hilfs2).contains(y)==true)
    		Nord[u]=true;
    	else
    		Nord[u]=false;
    	
    	if(String.valueOf(hilfs3).contains(y)==true)
    		Süd[u]=true;
    	else
    		Süd[u]=false;
    	
    	if(String.valueOf(hilfs5).contains(y)==true)
    		Ost[u]=true;
    	else
    		Ost[u]=false;
    	
    	if(String.valueOf(hilfs4).contains(y)==true)
    		West[u]=true;
    	else
    		West[u]=false;
    	
    	if(String.valueOf(hilfs6).contains(y)==true)
    		power[u]=true;
    	else
    		power[u]=false;
    	
    	u++;
    	//name[u]=Integ
    }
    else if(hilfs.length()==12)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs11=ch[1];
    	char hilfs2=ch[3];
    	char hilfs3=ch[5];
    	char hilfs4=ch[7];
    	char hilfs5=ch[9];
    	char hilfs6=ch[11];
    	String y="y";
    	String s=String.valueOf(hilfs1)+String.valueOf(hilfs11);
    	name[u]=Integer.parseInt(s);
    	
    	if(String.valueOf(hilfs2).contains(y)==true)
    		Nord[u]=true;
    	else
    		Nord[u]=false;
    	if(String.valueOf(hilfs3).contains(y)==true)
    		Süd[u]=true;
    	else
    		Süd[u]=false;
    	if(String.valueOf(hilfs5).contains(y)==true)
    		Ost[u]=true;
    	else
    		Ost[u]=false;
    	if(String.valueOf(hilfs4).contains(y)==true)
    		West[u]=true;
    	else
    		West[u]=false;
    	if(String.valueOf(hilfs6).contains(y)==true)
    		power[u]=true;
    	else
    		power[u]=false;
    	
    	u++;
    }
    else if(hilfs.length()==14)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs11=ch[1];
    	char hilfs2=ch[3];
    	char hilfs22=ch[4];
    	char hilfs3=ch[6];
    	char hilfs33=ch[7];
    	char hilfs4=ch[9];
    	char hilfs44=ch[10];
    	
    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs11);
    	Defender=Integer.parseInt(s1);
    	pos[3]=Defender;
    	String s2=String.valueOf(hilfs2)+String.valueOf(hilfs22);
    	Sepman=Integer.parseInt(s2);
    	pos[0]=Sepman;
    	String s3=String.valueOf(hilfs3)+String.valueOf(hilfs33);
    	Tracer=Integer.parseInt(s3);
    	pos[2]=Tracer;
    	String s4=String.valueOf(hilfs4)+String.valueOf(hilfs44);
    	Random=Integer.parseInt(s4);
    	pos[1]=Random;
    }
	}
	
	Spiel=new Planeinit(name,Nord,Süd,Ost,West,power,pos);//so ists richtig
	Spielbrett=Spiel.getSpiel();
    br.close();
}

/*
 * Ab  hier stammt alles von Thorben
 */
	public void setBesucht(int k)
	{
		besucht[k]=true;
	}
	public boolean getBesucht(int k)
	{
		return besucht[k];
	}




	public void abfahren(int anfang, int ende)
	{
		int zähler=0;
		ImageIcon grüneKanteHorizontal;
		ImageIcon grüneKanteVertikal;
		grüneKanteHorizontal= new ImageIcon("grüneKanteHorizontal.png");
		grüneKanteVertikal= new ImageIcon("grüneKanteVertikal.png");
		JLabel hKante = new JLabel(grüneKanteHorizontal);
		JLabel vKante = new JLabel(grüneKanteVertikal);
		hKante.setBounds(0, 0, 100, 100);
		vKante.setBounds(0, 0, 100, 100);
		
		//färbt gefahrene Knoten um
		if(anfang==ende)
		{}
		else if(getBesucht(anfang+ende)==false)
		{
			setBesucht(anfang+ende);
			zähler++;
			int i=1;
			 while(anfang>=Breite*i)
			 {
				 i++;
				 anfang=anfang-Breite;
				 ende=ende-Breite;
			 }
			
		
		 if(anfang+1==ende)
		{
			 
			 			
			hKante.setLocation(anfang*150+75, 150*i);
			add(hKante);
			//set rechte kante grün
			
		}
		else if(anfang-Breite==ende)
		{
			
				vKante.setLocation(anfang*150, 150*i-75);
				add(vKante);
				
		}
		else if(anfang+Breite==ende)
		{
			vKante.setLocation(anfang*150, 150*i+75);
			add(vKante);
			//set untere Kante grün

		}
		else if(anfang-1==ende)
		{
			hKante.setLocation(anfang*150-75, 150*i);
			add(hKante);
			//set linke Kante grün
			
		}
	}
		if(zähler==36)
		{
			thorbensPanel.setVisible(false);
			siegDisplay.setVisible(true);
		}
	}
	public void anzeigeLeben(int Leben)//eingabre der Verbleibenden Leben.
	{
		if(Leben==2)
		{
			Leben3.setVisible(false);
		}
		if(Leben==1)
		{
			Leben2.setVisible(false);
		}
	}
	public void castPositionen()
	{
		int aktuell[]=Spiel.getRoboter();//Positionen der Roboter! 0:SEPMAN 1:Random 2: Tracker 3:Defender 
		Sepman=aktuell[0];
		Random=aktuell[1];
		Tracer=aktuell[2];
		Defender=aktuell[3];
		jSepman.setLocation(ele[Sepman].getX(), ele[Sepman].getY());
		jSepman.setLocation(ele[Sepman].getX(), ele[Sepman].getY());
		jSepman.setLocation(ele[Sepman].getX(), ele[Sepman].getY());
		jSepman.setLocation(ele[Sepman].getX(), ele[Sepman].getY());
		jSepman.setLocation(ele[Sepman].getX(), ele[Sepman].getY());
	}
	
		
	
	
}