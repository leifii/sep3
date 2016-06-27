/**
 * @author ${Mark}
 *
 * 
 */
package Anzeige;
// Die Klasse Menü visualisiet die Komplette Menüführung 
// Zusätzlich initialisiert sie die Clients mit den passenden Ip-Adressen und Roboter Modi
//  Sowohl der KeyListener als der ActionListener(für die Buttons) befinden sich in dieser Klasse 
// Jedes Menü wird über ein eigenes JPanel realisiert
// Methoden zum erstellen der Menüs heißen "erzeuge"+ das passende Menü. Diese Methoden stehen auch alle im Interface IMenu
// Das Spielfeld Panel (thorbensPanel) wird von der Klasse AnzeigeSpielfeld gestaltet 


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import KommunikationPC.Client;

//import Spieldatenverarbeitung.ISpieldaten;

import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;



public class Menu extends JFrame implements IMenu , ActionListener{
//	private static final long serialVersionUID = 3498199861043935813L; //WIESO??? Manchmal gibts ne Fehlermeldung das das fehlt manchmal nicht ? weiß jemand eine Lösung ?
	
	public static int richtung = 0 ; 
	public boolean inputFinished = false;
	public String[] roboterBelegung = new String[4];
	public String comboBoxInhalt[] = {"Bitte Modus wählen", "SepMAN", "Verfolger", "Verteidiger", "Verpeilter"};
	public JPanel display = new JPanel();                                                //Deklaration aller Objekte und Variablen
	public JPanel startDisplay = new JPanel();
	public JPanel siegDisplay = new JPanel();
	public JPanel niederlageDisplay = new JPanel();
	public JPanel spielvorbereitungsDisplay = new JPanel();
	public JPanel thorbensPanel= new JPanel();
	public JPanel pausenDisplay = new JPanel();
	public JLabel lbUeberschrift = new JLabel("SEPMAN");
	public JLabel lbBeschriftung1= new JLabel("ROBOTER 1:");
	public JLabel lbBeschriftung2= new JLabel("ROBOTER 2:");
	public JLabel lbBeschriftung3= new JLabel("ROBOTER 3:");
	public JLabel lbBeschriftung4= new JLabel("ROBOTER 4:");
	public JLabel lbIp1Beschriftung = new JLabel("1. IP-Adresse");
	public JLabel lbip1Punkt1= new JLabel(".");
	public JLabel lbip1Punkt2= new JLabel(".");
	public JLabel lbip2Beschriftung= new JLabel("2. IP-Adresse");
	public JLabel lbip2Punkt1= new JLabel(".");
	public JLabel lbip2Punkt2= new JLabel(".");
	public JLabel lbip3Beschriftung= new JLabel("3. IP-Adresse");
	public JLabel lbip3Punkt1= new JLabel(".");
	public JLabel lbip3Punkt2= new JLabel(".");
	public JLabel lbip4Beschriftung= new JLabel("4. IP-Adresse");
	public JLabel lbip4Punkt1= new JLabel(".");
	public JLabel lbip4Punkt2= new JLabel(".");
	public JLabel lbHinweis1 = new JLabel("Bitte positioniere die Roboter richtig !!");
	public JTextField tfIp1Feld1 = new JTextField("198");
	public JTextField tfIp1Feld2 = new JTextField("162");
	public JTextField tfIp1Feld3 = new JTextField("1");
	public JTextField tfIp2Feld1 = new JTextField("198");
	public JTextField tfIp2Feld2 = new JTextField("162");
	public JTextField tfIp2Feld3 = new JTextField("2");
	public JTextField tfIp3Feld1 = new JTextField("198");
	public JTextField tfIp3Feld2 = new JTextField("162");
	public JTextField tfIp3Feld3 = new JTextField("3");
	public JTextField tfIp4Feld1 = new JTextField("198");
	public JTextField tfIp4Feld2 = new JTextField("162");
	public JTextField tfIp4Feld3 = new JTextField("4");
	public JButton btnAuswahlfenster = new JButton();
	public JButton btnStart = new JButton();
	public JButton btnEnde = new JButton();
	public JButton btnEnde2 = new JButton();
	public JButton btnZurueck = new JButton();
	public JButton btnStartfinal = new JButton();
	public JButton btnSpielfortsetzen = new JButton();
	public JButton btnNeuesSpiel = new JButton();
	public JComboBox cbAuswahl1= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl2= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl3= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl4= new JComboBox(comboBoxInhalt);

	// Konstruktor	
	public Menu() {
		super();
		initComponents();
	}
// Initialisierung des Fenster + Aufrufen des Startbildschirms
public void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(1020,720);
	setTitle("SEPman");
//	addKeyListener(this);
	setzeStartbildschirm();            
	validate();

}

// Diese Methoden erstell den Startbildschirm 
public void setzeStartbildschirm(){
	startDisplay.setLayout(null);
	startDisplay.setVisible(true);
	startDisplay.setBackground(Color.gray);
	startDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));	
	
	btnAuswahlfenster.setText("START");
	btnAuswahlfenster.setFont(new Font("Arial", Font.BOLD, 50));
	btnAuswahlfenster.setBounds(240, 200, 500, 75);
	btnAuswahlfenster.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));      // 
	btnAuswahlfenster.addActionListener(this);
	startDisplay.add(btnAuswahlfenster);
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 100));
	lbUeberschrift.setBounds(140,20 , 700, 100);
	startDisplay.add(lbUeberschrift);
    getContentPane().add(startDisplay);
}

// Methode zum erstellen des Auswahldisplays
public void setzeAuswahldisplay (){
    startDisplay.setVisible(false);
    spielvorbereitungsDisplay.setVisible(false);
	display.setVisible(true);
	display.setLayout(null);
	display.setBackground(Color.WHITE);
	display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 50));
	lbUeberschrift.setBounds(80, 6, 580	, 50);
	display.add(lbUeberschrift);
	
	btnStart.addActionListener(this);
	btnStart.setSize(300, 50);
	btnStart.setLocation(215, 95);
	btnStart.setText("Start");
	btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(btnStart);
	
	btnEnde.addActionListener(this);           
	btnEnde.setSize(300, 50);
	btnEnde.setLocation(215, 155);
	btnEnde.setText("Ende");
	btnEnde.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(btnEnde);
	
	lbBeschriftung1.setFont(new Font("Arial", Font.BOLD, 15));
	lbBeschriftung1.setBounds(215, 225, 102, 18);
	display.add(lbBeschriftung1);
	
	lbBeschriftung2.setFont(new Font("Arial", Font.BOLD, 15));
	lbBeschriftung2.setBounds(215, 261, 102, 18);
	display.add(lbBeschriftung2);
	lbBeschriftung3.setFont(new Font("Arial", Font.BOLD, 15));
	
	lbBeschriftung3.setBounds(215, 300, 102, 16);
	display.add(lbBeschriftung3);
	lbBeschriftung4.setFont(new Font("Arial", Font.BOLD, 15));
	
	lbBeschriftung4.setBounds(215, 340, 102, 16);
	display.add(lbBeschriftung4);
	
	cbAuswahl1.setSelectedIndex(0);
	cbAuswahl1.setSize(200, 30);
	cbAuswahl1.setLocation(311, 215);
	cbAuswahl1.addActionListener(this);               //ActionPerformed ab Zeile 250
	display.add(cbAuswahl1);
	
	cbAuswahl2.setSelectedIndex(0);
	cbAuswahl2.setSize(200, 30);
	cbAuswahl2.setLocation(311, 250);
	cbAuswahl2.addActionListener(this);               //ActionPerformed ab Zeile 250
	display.add(cbAuswahl2);
	
	cbAuswahl3.setSelectedIndex(0);
	cbAuswahl3.setSize(200, 30);
	cbAuswahl3.setLocation(311, 290);
	cbAuswahl3.addActionListener(this);                //ActionPerformed ab Zeile 250
	display.add(cbAuswahl3);
	
	cbAuswahl4.setSelectedIndex(0);
	cbAuswahl4.setSize(200, 30);
	cbAuswahl4.setLocation(311, 330);
	cbAuswahl4.addActionListener(this);               //ActionPerformed ab Zeile 250
	display.add(cbAuswahl4);	
	getContentPane().add(display);
}

// Methode zum erstellen des Spielvorbereitungsdisplay
public void setzeSpielvorbereitungsdisplay(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setVisible(false);
	niederlageDisplay.setVisible(false);
	
	spielvorbereitungsDisplay.setLayout(null);
	spielvorbereitungsDisplay.setVisible(true);
	spielvorbereitungsDisplay.setBackground(Color.WHITE);
	spielvorbereitungsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	btnZurueck.addActionListener(this);
	spielvorbereitungsDisplay.add(btnZurueck);
	btnZurueck.setBounds(200, 400, 240, 50);
	btnZurueck.setText("Zurück");
	btnZurueck.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	spielvorbereitungsDisplay.add(btnStartfinal);
	btnStartfinal.setBounds(200, 340, 240, 50);
	btnStartfinal.setText("Roboter positioniert ,Spiel starten !!");
	btnStartfinal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	btnStartfinal.addActionListener(this);
	
	
	spielvorbereitungsDisplay.add(lbHinweis1);
	lbHinweis1.setBounds(100, 100, 300, 100);
	lbHinweis1.setFont(new Font("Arial", Font.BOLD, 15));
	lbHinweis1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

	
	getContentPane().add(spielvorbereitungsDisplay);
}


//Methode zum erstellen des Siegdisplay
public void setzeSiegbildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setLayout(null);
	siegDisplay.setVisible(true);
	siegDisplay.setBackground(Color.GREEN);
	siegDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
    getContentPane().add(siegDisplay);
    
	spielvorbereitungsDisplay.add(	btnEnde2 );
	btnEnde2.setBounds(200, 340, 240, 50);
	btnEnde2.setText("Spiel Beenden");
	btnEnde2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	btnEnde2.addActionListener(this); 
	
	spielvorbereitungsDisplay.add(	btnNeuesSpiel );
	btnNeuesSpiel.setBounds(200, 340, 240, 50);
	btnNeuesSpiel.setText("Spiel Beenden");
	btnNeuesSpiel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	btnNeuesSpiel.addActionListener(this);
}

//Methode zum erstellen des Niederlagedisplays
public void setzeNiederlageBildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setLayout(null);
	siegDisplay.setVisible(true);
	siegDisplay.setBackground(Color.RED);
	siegDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	spielvorbereitungsDisplay.add(	btnEnde2 );
	btnEnde2.setBounds(200, 340, 240, 50);
	btnEnde2.setText("Spiel Beenden");
	btnEnde2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	btnEnde2.addActionListener(this); 
	
	spielvorbereitungsDisplay.add(	btnNeuesSpiel );
	btnNeuesSpiel.setBounds(200, 340, 240, 50);
	btnNeuesSpiel.setText("Spiel Beenden");
	btnNeuesSpiel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	btnNeuesSpiel.addActionListener(this);

    getContentPane().add(niederlageDisplay);

}
//Methode zum erstellen des Pausendisplays
public void setzePausenBildschrim(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setLayout(null);
	siegDisplay.setVisible(true);
	siegDisplay.setBackground(Color.GRAY);
	siegDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	spielvorbereitungsDisplay.add(lbHinweis1);
	lbHinweis1.setBounds(100, 100, 300, 100);
	lbHinweis1.setFont(new Font("Arial", Font.BOLD, 15));
	lbHinweis1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	spielvorbereitungsDisplay.add(btnSpielfortsetzen );
	btnSpielfortsetzen.setBounds(200, 340, 240, 50);
	btnSpielfortsetzen.setText("Roboter positioniert ,Spiel fortsetzen !!");
	btnSpielfortsetzen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	btnSpielfortsetzen.addActionListener(this); 
	
    getContentPane().add(niederlageDisplay);
	
}
// Überprüfung ob die Roboterauswahl vollständig ist und keine Modi doppelt ausgewählt wurden , funktioniert noch nicht korrekt
public boolean eingabeKorrekt() {
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

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource()== cbAuswahl1){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
//		System.out.println(msg);		
		switch(msg){
		case "SepMAN" : roboterBelegung[0] = "1";
		break;
		case "Verfolger" : roboterBelegung[0] = "2";
		break;
		case "Modus 3" : roboterBelegung[0] = "3";
		break;
		case "Modus 4" : roboterBelegung[0] = "4";
		break;
		}
	}
	else if (e.getSource()== cbAuswahl2){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		switch(msg){
		case "SepMAN" : roboterBelegung[1] = "1";
		break;
		case "Verfolger" : roboterBelegung[1] = "2";
		break;
		case "Verteidiger" : roboterBelegung[1] = "3";
		break;
		case "Verpeilter" : roboterBelegung[1] = "4";
		break;
		}	

	}
	else if (e.getSource()== cbAuswahl3){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		switch(msg){
		case "SepMAN" : roboterBelegung[2] = "1";
		break;
		case "Verfolger" : roboterBelegung[2] = "2";
		break;
		case "Verteidiger" : roboterBelegung[2] = "3";
		break;
		case "Verpeilter" : roboterBelegung[2] = "4";
		break;
		}	
	}
	else if (e.getSource()== cbAuswahl4){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		switch(msg){
		case "SepMAN" : roboterBelegung[3] = "1";
		break;
		case "Verfolger" : roboterBelegung[3] = "2";
		break;
		case "Verteidiger" : roboterBelegung[3] = "3";
		break;
		case "Verpeilter" : roboterBelegung[3] = "4";
		break;
		}				
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
			AnzeigeSpielfeldinit rr= new AnzeigeSpielfeldinit();
			thorbensPanel =rr.Spielfeldinit("Spielfeld.txt", thorbensPanel);
			thorbensPanel.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent l) {
					// TODO Auto-generated method stub
					if(l.getKeyCode() == KeyEvent.VK_UP){
					     oben();
					     System.out.println(richtung);
						}
						else if(l.getKeyCode() == KeyEvent.VK_LEFT){
							links();
							System.out.println(richtung);
						}
						
						else if(l.getKeyCode() == KeyEvent.VK_DOWN){
							unten();
							System.out.println(richtung);
						}
						
						else if(l.getKeyCode() == KeyEvent.VK_RIGHT){
							rechts();
							System.out.println(richtung);
						}
		
						else if(l.getKeyCode() == KeyEvent.VK_SPACE){
							pause();
							System.out.println(richtung); 
						}	
				}
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					// wird nicht genutzt
				}
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					// wird nicht genutzt
				}
			});
            thorbensPanel.setFocusable(true);
			this.spielvorbereitungsDisplay.setVisible(false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	else if(e.getSource() ==btnSpielfortsetzen ){
		// Spiel fortsetzen 
	}
	else if (e.getSource()== btnNeuesSpiel){
		setzeAuswahldisplay();
	}
}
//Main Mehtoden 
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
	// Set-Methode um das Array welches die Roboterbelegung speichert zu manipulieren. Bedarf für diese Methode muss noch geklärt werden
	public void setRoboterBelegung(String[] roboterBelegung) {
		this.roboterBelegung = roboterBelegung;
	}
	
	// gibt ein Array mit der Modi-Belgung der Roboter wieder 
	@Override
	public String[] getRoboterbelegung() {
		// TODO Auto-generated method stub
		return roboterBelegung;
	}
	// Methoden für Belgung der Richtungsvariable
	
		public void oben(){
			richtung = 4;
		}
		
		public void unten(){
			richtung = 6;
		}
		
		public void links(){
			richtung = 5;
		}
		
		public void rechts(){
			richtung = 7;
		}
		
		public void pause(){
			richtung = 2;	 
		}
		@Override
		public  int getTaste() {
			// TODO Auto-generated method stub
			return richtung;
		}
		
public void setClients(){
//	Client clint1 = new Client(123456, this.roboterBelegung[0], 18415);
//	Client clint1 = new Client(123456, this.roboterBelegung[1], 18415);
//	Client clint1 = new Client(123456, this.roboterBelegung[2], 18415);
//	Client clint1 = new Client(123456, this.roboterBelegung[3], 18415);	
}
}