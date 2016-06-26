package KommunikationEV3;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Drucksensorverarbeitung.Drucksensor;
import RoboterModus.IModus;
import RoboterModus.Zufall;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Server{


		public static void main(String[] args) throws IOException, ClassNotFoundException {
	
			byte[] nachricht = new byte[9];
			int mode;
			ServerSocket sSocket = new ServerSocket(18415);
//		    System.out.println("Ich akzeptiere gerade!");        //Konsolenausgabe bei Test am PC
			LCD.drawString("Ich akzeptiere", 0, 2);              //Displayausgabe bei Test am EV3
			LCD.drawString("gerade!", 0, 3);                     //
			
			try{
			
			Socket roboter1 = sSocket.accept();
//			System.out.println("Robter1 verbunden!");           //Konsolenausgabe bei Test am PC
			LCD.clear();                                        //
			LCD.drawString("Roboter 1", 0, 2);                  //Displayausgabe bei Test am EV3
			LCD.drawString("verbunden!", 0, 3);                 //
			Delay.msDelay(3000);
			LCD.clear();
//			Socket roboter2 = sSocket.accept();
//			System.out.println("Robter2 verbunden!");           //Konsolenausgabe bei Test am PC
//			Socket roboter3 = sSocket.accept();
//			System.out.println("Robter3 verbunden!");           //Konsolenausgabe bei Test am PC
//			Socket roboter4 = sSocket.accept();
//			System.out.println("Robter4 verbunden!");           //Konsolenausgabe bei Test am PC
			sSocket.close();
			
			// Stream 
		
			Drucksensor drucksensor = new Drucksensor();
			KommunikationEV3 com1 = new KommunikationEV3(roboter1, drucksensor);
			Spieldatenverarbeitung sdv = new Spieldatenverarbeitung();
			IModus geistmode;
			
			nachricht = com1.empfangen();
			com1.senden();
			
			mode = nachricht[8];
			
			switch (mode){
			case 101: geistmode = new Verfolger();
			case 103: geistmode = new Zufall(); 
			break;
			}
			
			while(true){
			nachricht = com1.empfangen();
			
			
			
			if(mode == 104){
				// TODO Sepman
				com1.senden();
			}
			
			else if(mode == 103){
				// TODO Zufall
				
				com1.senden();
			}
			
			else if(mode == 102){
				// TODO Verteidigung
				com1.senden();
			}
			
			else if(mode == 101){
				// TODO Verfolgung
				com1.senden();
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