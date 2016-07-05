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

		//Das Spielfeld wird gesetzt
		int[] knoten ={1, 2, 3, 4, 5, 6, 11, 12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 26, 31, 32, 33, 34, 35, 36, 41, 42, 43, 44, 45, 46, 51, 52, 53, 54, 55, 56};
		boolean[] norden ={false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
		boolean[] sueden ={true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false};
		boolean[] westen ={false, true, true, true, false, true, false, true, false, false, true, false, false, false, true, true, true, true, false, true, true, true, true, false, false, false, true, false, false, true, false, false, true, true, true, true};
		boolean[] osten = {true, true, true, false, true, false, true, false, false, true, false, false, false, true, true, true, true, false, true, true, true, true, false, false, false, true, false, false, true, false, true, true, false, true, true, false};
		boolean[] powerup={false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false};
		//Ende Spielfeld

		ServerSocket sSocket = new ServerSocket(18415);		 // Es wird ein Socket aufgemacht 
		LCD.drawString("Ich akzeptiere", 0, 2);              //Displayausgabe am EV3
		LCD.drawString("gerade!", 0, 3);                     

		try{

			Socket roboter = sSocket.accept();			//Verbindung wird aufgebaut mit dem Client
			LCD.clear();                                        
			LCD.drawString("Verbunden!", 0, 3);                 
			Delay.msDelay(3000);
			LCD.clear();

			sSocket.close();

			//Initialisierung

			Drucksensor drucksensor = new Drucksensor();
			KommunikationEV3 com1 = new KommunikationEV3(roboter, drucksensor);

			Planeinit planes = new Planeinit(knoten, norden, sueden, osten, westen, powerup);

			Linienverfolgung lvfg=new Linienverfolgung();
			IModus robomode;

			//Ende Initialisierung

			// Stream 

			//Die erste Nachricht wird empfangen
			nachricht = com1.empfangen();

			//Der Robotermodus wird in der ersten Nachricht festgelegt und den Robotern zugewiesen
			mode = nachricht[8];

			switch (mode){

			case 101: robomode = new Verfolger(3, planes, lvfg, drucksensor);
			LCD.drawString("Verfolger", 0, 2);                  //Displayausgabe EV3
			break;

			case 102: robomode = new Verteidiger(31, planes, lvfg, drucksensor);
			LCD.drawString("Verteidiger", 0, 2);                 //Displayausgabe EV3
			break;

			case 103: robomode = new Zufall(26, planes, lvfg, drucksensor);
			LCD.drawString("Zufall", 0, 2);                  //Displayausgabe EV3
			break;

			case 104: robomode = new Sepman(54, planes, lvfg, drucksensor);
			LCD.drawString("Sepman", 0, 2);                  //Displayausgabe EV3
			break;

			default: robomode = new Zufall(26, planes, lvfg, drucksensor);
			LCD.drawString("Zufall", 0, 2);                  //Displayausgabe EV3
			break;

			}


			//Es werden nun alle Nachrichten empfangen und dem Roboter zugewiesen
			while(true){
				nachricht = com1.empfangen();
				
				String s = "";
				for(int i = 0; i<nachricht.length; i++){
					s = s+nachricht[i]+"";
				}
				
				LCD.drawString(s, 0, 4);


				// Sepman

				if(mode == 104){

					robomode.setNachricht(nachricht);
					robomode.runRobo();
					com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));

				}

				// Zufall

				else if(mode == 103){

					robomode.setNachricht(nachricht);
					robomode.runRobo();
					com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));

				}

				// Verteidigung
				else if(mode == 102){

					robomode.setNachricht(nachricht);
					robomode.runRobo();
					com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));

				}

				// Verfolgung
				else if(mode == 101){

					robomode.setNachricht(nachricht);
					robomode.runRobo();
					com1.senden(com1.erzeugeByteArray(robomode.getPos(), robomode.getZielKnoten(), robomode.getDrucksensor(), robomode.isAktiviert(), robomode.isPowerup(), robomode.isPause()));

				}

				
				if(Button.getButtons() != 0){  	//Die Verbindung wird geschlossen+#
					
					roboter.close();			
				}
			}
			// Stream ende 

		}

		catch ( IOException e ) {
			e.printStackTrace();
		} 


	}
}