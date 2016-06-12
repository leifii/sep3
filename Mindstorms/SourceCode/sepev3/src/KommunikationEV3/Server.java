package KommunikationEV3;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Server{


		public static void main(String[] args) throws IOException, ClassNotFoundException {
	
			
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

			KommunikationEV3 com1 = new KommunikationEV3(roboter1);
			com1.empfangen();
			com1.senden();
			
			// Stream ende 
			LCD.drawString("Habe fertig!", 0, 6);                 //Displayausgabe bei Test am EV3
			Delay.msDelay(5000);
			LCD.clear(6);
			LCD.drawString("PressAnyButton", 0, 6);
			
			Button.waitForAnyPress();                            //Drücke beliebigen Button zum Beenden
			roboter1.close();
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