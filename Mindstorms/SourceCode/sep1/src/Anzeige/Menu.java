package Anzeige;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class Menu extends JFrame implements IMenu{
	
	boolean inputFINISHED = false;
	public 	String comboBoxInhalt[] = {"Modus1", "Modus2", "Modus3", "Modus4"};
	private JPanel display = new JPanel();
	private JPanel startDisplay = new JPanel();
	private JPanel siegDisplay = new JPanel();
	private JPanel niederlageDisplay = new JPanel();
	private JPanel spielvorbereitungsDisplay = new JPanel();
	public JLabel lbUeberschrift = new JLabel("SEPMAN");
	public JLabel lbBeschriftung1= new JLabel("ROBOTER 1:");
	public JLabel lbBeschriftung2= new JLabel("ROBOTER 2:");
	public JLabel lbBeschriftung3= new JLabel("ROBOTER 3:");
	public JLabel lbBeschriftung4= new JLabel("ROBOTER 4:");
	public JButton btnAuswahlfenster = new JButton();
	public JButton btnStart = new JButton();
	public JButton btnEnde = new JButton();
	public JComboBox cbAuswahl1= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl2= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl3= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl4= new JComboBox(comboBoxInhalt);
	
	public Menu() {
		super();
		initComponents();
	}

public void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(600,500);
	setTitle("SEPman");
	setzeStartbildschirm();
//	setzeAuswahldisplay();

	
	validate();

}
private void setzeStartbildschirm(){
	startDisplay.setLayout(null);
	startDisplay.setVisible(true);
	startDisplay.setBackground(Color.cyan);
	startDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));	
	btnAuswahlfenster.setText("START");
	btnAuswahlfenster.setBounds(210, 200, 200, 50);
	startDisplay.add(btnAuswahlfenster);
	btnAuswahlfenster.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setzeAuswahldisplay();
		}
	});
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 70));
	lbUeberschrift.setBounds(20,20 , 580, 70);
	startDisplay.add(lbUeberschrift);
	getContentPane().add(startDisplay);
}

private void setzeAuswahldisplay (){
    startDisplay.setVisible(false);
	display.setVisible(true);
	display.setLayout(null);
	display.setBackground(Color.WHITE);
	display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 50));
	lbUeberschrift.setBounds(20, 6, 580	, 50);
	display.add(lbUeberschrift);
	btnStart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setzeSpielvorbereitungsdisplay();
		}
	});
	
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
	
	getContentPane().add(display);
}

private void setzeSpielvorbereitungsdisplay(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setVisible(false);
	niederlageDisplay.setVisible(false);
	
	spielvorbereitungsDisplay.setLayout(null);
	spielvorbereitungsDisplay.setVisible(true);
	spielvorbereitungsDisplay.setBackground(Color.WHITE);
	spielvorbereitungsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	getContentPane().add(spielvorbereitungsDisplay);
}

private void setzeSiegbildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setLayout(null);
	siegDisplay.setVisible(true);
	siegDisplay.setBackground(Color.GRAY);
	siegDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
    getContentPane().add(siegDisplay);
}

private void setzeNiederlageBildschirm(){
	
}

private void setzePausenBildschrim(){
	
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
