/**
 * @author ${Tristan, Katharina Böse}
 *
 * 
 */
package KommunikationEV3;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import RoboterModus.*;
import Spielfeld.Planeinit;
import Spielfeld.Spielfeld;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Server{


		public static void main(String[] args) throws IOException, ClassNotFoundException {
	
			byte[] nachricht = new byte[9];
			int mode;
			ServerSocket sSocket = new ServerSocket(18415);
			LCD.drawString("Ich akzeptiere", 0, 2);              //Displayausgabe bei Test am EV3
			LCD.drawString("gerade!", 0, 3);                     //
			
			try{
			
			Socket roboter1 = sSocket.accept();
			LCD.clear();                                        
			LCD.drawString("Verbunden!", 0, 3);                 
			Delay.msDelay(3000);
			LCD.clear();

			sSocket.close();
			
			// Stream 
		
			Drucksensor drucksensor = new Drucksensor();
			KommunikationEV3 com1 = new KommunikationEV3(roboter1, drucksensor);
			Spielfeld spielfeld = new Spielfeld();
			spielfeld.create("Spielfeld.txt");
			Planeinit planes = spielfeld.getSpiel();	     
			Linienverfolgung lvfg=new Linienverfolgung();
			IModus robomode;
			
			nachricht = com1.empfangen();
			
			
			mode = nachricht[8];
			
			switch (mode){
			case 101: robomode = new Verfolger(mode, planes, lvfg, drucksensor);
			LCD.drawString("Verfolger", 0, 2);                  //Displayausgabe EV3
			break;
			case 102: robomode = new Verteidiger(mode, planes, lvfg, drucksensor);
			LCD.drawString("Verteidiger", 0, 2);                  //Displayausgabe EV3
			break;
			case 103: robomode = new Zufall(26, planes, lvfg, drucksensor);
			LCD.drawString("Zufall", 0, 2);                  //Displayausgabe EV3
			break;
			case 104: robomode = new Sepman(planes, lvfg, drucksensor);
			LCD.drawString("Sepman", 0, 2);                  //Displayausgabe EV3
			break;
			default: robomode = new Zufall(26, planes, lvfg, drucksensor);
			LCD.drawString("Zufall", 0, 2);                  //Displayausgabe EV3
			break;
			}
			//com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));
			
			while(true){
			nachricht = com1.empfangen();
			
			
			
			if(mode == 104){
				// TODO Sepman
				com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));
			}
			
			else if(mode == 103){
				// TODO Zufall
				robomode.setNachricht(nachricht);
				robomode.run();
				com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));
			}
			
			else if(mode == 102){
				// TODO Verteidigung
				com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));
			}
			
			else if(mode == 101){
				// TODO Verfolgung
				com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));
			}
			
			
			
			

			if(Button.getButtons() != 0){  	//Die Methode soll angeblich 0 zurückgeben, wenn kein button gedruckt ist
				roboter1.close();			//also kann man jetzt mit beliebigem button press den Roboter beenden
			}
			}
			// Stream ende 
		//	LCD.drawString("Habe fertig!", 0, 6);                 //Displayausgabe bei Test am EV3
		//	Delay.msDelay(5000);
			
		//	LCD.clear(6);
		//	LCD.drawString("PressAnyButton", 0, 6);
			
		//	Button.waitForAnyPress();                            //Drücke beliebigen Button zum Beenden
		//	roboter1.close();
//			roboter2.close();
//			roboter3.close();
//			roboter4.close();
			
//			System.out.println("Habe fertig!");                  //Konsolenausgabe bei Test am PC
			                                                //
			
			}
			
			 catch ( IOException e ) {
			        e.printStackTrace();
	 	} 

	
		}
}