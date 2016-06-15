package KommunikationEV3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;




import Drucksensorverarbeitung.Drucksensor;
import Drucksensorverarbeitung.IDrucksensor;
import Steuerbefehle.ISteuerbefehl;
import Steuerbefehle.Steuerbefehl;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class KommunikationEV3 implements IKommunikation{

	Socket socket;
	byte[] nachricht= new byte[9];
	Steuerbefehl steuerbefehl;
	Drucksensor drucksensor;
	
	public KommunikationEV3(Socket soc, Steuerbefehl steuerbefehl, Drucksensor drucksensor){
		socket = soc;
		this.steuerbefehl = steuerbefehl;
		this.drucksensor = drucksensor;
	}
	
	public void senden(){
		
			try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(nachricht.length);
			out.write(nachricht);
//		    System.out.println("Nachricht gesendet");                              //Konsolenausgabe bei Test am PC
//		    LCD.drawString("Nachricht", 0, 2);                                     //Displayausgabe bei Test am EV3
//		    LCD.drawString("gesendet!", 0, 3);                                     //
//		    Delay.msDelay(2000);                                                   //
//		    LCD.clear();                                                           //
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void empfangen(){
		
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			int length = in.readInt();		//L�nge der Nachricht lesen
			if(length>0){
				in.readFully(nachricht, 0, nachricht.length);	//Speicherort der Nachricht, Anfang, Ende
				
//				System.out.println("Nachricht empfangen");                         //Konsolenausgabe bei Test am PC
				LCD.drawString("Nachricht", 0, 1);                                 //Displayausgabe bei Test am EV3
				LCD.drawString("empfangen:", 0, 2);                                //
				
				for(int i = 0; i<nachricht.length; i++){
//					System.out.print(nachricht[i]);                                //Konsolenausgabe bei Test am PC
					LCD.drawInt(nachricht[i], i+1, 4);
				}
//				System.out.println("");
				Delay.msDelay(5000);
			}
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public int erzeugeByteArray(){
		return 1;
	}
	
	
	public void nachrichtverarbeiten(){
		
		int wert = nachricht[8];
		
		if(wert==004){
			steuerbefehl.fahreVorwaerts();
		}
		
		else if(wert==005){
			steuerbefehl.drehenLinks();
		}

		else if(wert==007){
			steuerbefehl.drehenRechts();
		}

		else if(wert==006){
	
		}
		
	
	}

	@Override
	public boolean druckSensor(boolean druck) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}