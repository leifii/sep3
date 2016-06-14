package Anweisungen ;

import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

public class Anweisung implements KeyListener {
	
	private final IAnweisung events;
	
	Anweisung(IAnweisung taste){
		events=taste;
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void keyPressed(KeyEvent event){
		int code;
		
		code=event.getKeyCode();
		
		switch(code){
		case KeyEvent.VK_UP: 
			events.oben();
			break;
			
		case KeyEvent.VK_DOWN:
			events.unten();
			break;
			
		case KeyEvent.VK_LEFT:
			events.links();
			break;
			
		case KeyEvent.VK_RIGHT:
			events.rechts();
			break;
			
		case KeyEvent.VK_A:
			events.stop();
			break;
			
		case KeyEvent.VK_S:
			events.start();
			break;
			
		case KeyEvent.VK_E:
			events.verlassen();
			break;
		}
	}
	
	public void keyReleased(KeyEvent e){
		
	}


} 
