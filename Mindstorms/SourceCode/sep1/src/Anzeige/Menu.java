package Anzeige;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import Spieldatenverarbeitung.ISpieldaten;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;




public class Menu extends JFrame implements IMenu, ISpieldaten{
	
	boolean inputFINISHED = false;
	private JPanel display = new JPanel();
	public JLabel lbUeberschrift = new JLabel("SEPMAN");
	public JLabel lbBeschriftung1= new JLabel("ROBOTER 1:");
	public JLabel lbBeschriftung2= new JLabel("ROBOTER 2:");
	public JLabel lbBeschriftung3= new JLabel("ROBOTER 3:");
	public JLabel lbBeschriftung4= new JLabel("ROBOTER 4:");
	public JButton btnStart = new JButton();
	public JButton btnEnde = new JButton();
	public JComboBox cbAuswahl1= new JComboBox();
	public JComboBox cbAuswahl2= new JComboBox();
	public JComboBox cbAuswahl3= new JComboBox();
	public JComboBox cbAuswahl4= new JComboBox();
	
	public Menu() {
		initComponents();
	}

public void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(new BorderLayout());
	setSize(500,500);
	setTitle("SEPman");
	setResizable(false);
	setzeStartbildschirm();
	display.setVisible(true);
	display.setBackground(Color.WHITE);
	display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
}

public void setzeStartbildschirm (){
	display.setLayout(null);
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 50));
	lbUeberschrift.setBounds(20, 6, 592, 51);
	display.add(lbUeberschrift);
	
	btnStart.setSize(210, 50);
	btnStart.setLocation(215, 95);
	
	btnStart.setText("Start");
	display.add(btnStart);
	btnEnde.setSize(210, 50);
	btnEnde.setLocation(215, 155);
	
	btnEnde.setText("Ende");
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
	cbAuswahl1.setSize(114, 50);
	cbAuswahl1.setLocation(311, 210);
	
	display.add(cbAuswahl1);
	cbAuswahl2.setSize(114, 50);
	cbAuswahl2.setLocation(311, 246);
	display.add(cbAuswahl2);
	cbAuswahl3.setSize(114, 50);
	cbAuswahl3.setLocation(311, 284);
	display.add(cbAuswahl3);
	cbAuswahl4.setSize(114, 50);
	cbAuswahl4.setLocation(311, 324);
	display.add(cbAuswahl4);
}
public void setzeSiegbildschirm(){
	
}

public void setzeNiederlageBildschirm(){
	
}

public void setzePausenBildschrim(){
	
}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName()); //Hiermit wird erreicht das das Programm unter 
                                                                        //jedem Betriebsystem genau so aussieht wie es 
					 													//der Anwender von nativen Anwendungen kennt

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
