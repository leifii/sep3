package Anweisungen ;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

public class Anweisung extends Applet implements KeyListener {
	
//	private final IAnweisung events;
//	
//	Anweisung(IAnweisung taste){
//		events=taste;
//	}
	public int richtung = 0;
	
	public void init(){
		this.addKeyListener(this);
	}
	
	public void paint(Graphics g){
		setSize(500,500);
		g.drawString("test",25,0);
	}
	
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void keyPressed(KeyEvent event){
		int code;
		
		code=event.getKeyCode();
		
		switch(code){
		case KeyEvent.VK_UP: 
			oben();
			System.out.println("oben");
			break;
			
		case KeyEvent.VK_DOWN:
			unten();
			System.out.println("unten");
			break;
			
		case KeyEvent.VK_LEFT:
			links();
			System.out.println("links");
			break;
			
		case KeyEvent.VK_RIGHT:
			rechts();
			System.out.println("rechts");
			break;
			
		case KeyEvent.VK_P:
			pause();
			break;
			
		case KeyEvent.VK_S:
			start();
			break;
			
		case KeyEvent.VK_E:
			pauseende();
			break;
		}
	}
	
	public void keyReleased(KeyEvent e){
		
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
