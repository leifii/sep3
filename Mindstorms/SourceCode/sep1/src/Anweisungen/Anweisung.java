package Anweisungen ;


import java.awt.BorderLayout;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Anweisung  {
	

	public static int richtung = 0;
	JTextArea inputText;
	JTextArea feedbackText;

	
public Anweisung(){
		
		JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("TestTestTest");
		guiFrame.setSize(700, 200);
		
		guiFrame.setLocationRelativeTo(null);
		
		
		feedbackText = new JTextArea();
		JScrollPane scrollText = new JScrollPane(feedbackText);
		
		inputText = new JTextArea();
		inputText.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getKeyCode() == KeyEvent.VK_UP){
				feedbackText.append("You pressed UP, KeyCode is " + " "+ e.getKeyCode() + "\n");
			    oben();
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					feedbackText.append("You pressed LEFT, KeyCode is " + " "+ e.getKeyCode() + "\n");
					
					links();
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					feedbackText.append("You pressed DOWN, KeyCode is " + " "+ e.getKeyCode() + "\n");
					
						unten();
				
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					feedbackText.append("You pressed RIGHT, KeyCode is " + " "+ e.getKeyCode() + "\n");
					
					rechts();
				
				}
				else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					feedbackText.append("You");
					
					
				}
				else{
					feedbackText.append("something else pressed \n");
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				feedbackText.append("Key released: " + e.getKeyChar() + "\n");
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		guiFrame.add(inputText, BorderLayout.NORTH);
		guiFrame.add(scrollText, BorderLayout.CENTER);
		guiFrame.setVisible(true);
		
	}
	
	// Methoden für Rückgabewerte
	
	public boolean oben(){
		richtung = 1;
		return true;
	}
	
	public boolean unten(){
		richtung = 2;
		return true;
	}
	
	public boolean links(){
		richtung = 3;
		return true;
	}
	
	public boolean rechts(){
		richtung = 4;
		return true;
	}
	
	public boolean spielstart(){
		return true;
	}
	
	public boolean pause(){
		return true;
	}
	
	public boolean pauseende(){
		return true;
	}

} 
