package Anzeige;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import Spieldatenverarbeitung.ISpieldaten;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Menu extends JFrame implements IMenu, ISpieldaten{
	public Menu() {
		getContentPane().setBackground(new Color(204, 102, 102));
		getContentPane().setLayout(null);
		String comboBoxListe[] = {"Modus1", "Modus2", "Modus3", "Modus4"};
		
		JLabel lblNewLabel = new JLabel("SAPMAN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Zapfino", Font.BOLD, 49));
		lblNewLabel.setBounds(6, 6, 606, 95);
		getContentPane().add(lblNewLabel);
		
		JButton btnNeuesSpielStarten = new JButton("Neues Spiel starten (N)");
		btnNeuesSpielStarten.setBounds(180, 87, 200, 30);
		getContentPane().add(btnNeuesSpielStarten);
		
		JButton btnSpielBeendenb = new JButton("Spiel Beenden (B)");
		btnSpielBeendenb.setBounds(180, 113, 200, 30);
		getContentPane().add(btnSpielBeendenb);
		
		JLabel lblGeist = new JLabel("Geist 1");
		lblGeist.setBounds(180, 155, 52, 20);
		getContentPane().add(lblGeist);
		
		JLabel lblGeist_1 = new JLabel("Geist 2");
		lblGeist_1.setBounds(180, 195, 52, 20);
		getContentPane().add(lblGeist_1);
		
		JLabel label_1 = new JLabel("Geist 1");
		label_1.setBounds(180, 235, 52, 20);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Geist 1");
		label_2.setBounds(180, 275, 52, 20);
		getContentPane().add(label_2);
		
		JComboBox comboBox = new JComboBox(comboBoxListe);
		comboBox.setBounds(230, 150, 150, 30);
		
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(comboBoxListe);
		comboBox_1.setBounds(230, 190, 150, 30);
		getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox(comboBoxListe);
		comboBox_2.setBounds(230, 230, 150, 30);
		getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox(comboBoxListe);
		comboBox_3.setBounds(230, 270, 150, 30);
		getContentPane().add(comboBox_3);
	}

	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
