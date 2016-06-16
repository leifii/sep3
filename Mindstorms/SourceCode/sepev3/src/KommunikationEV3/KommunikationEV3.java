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
		erzeugeByteArray();
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
					System.out.print(nachricht[i]);                                //Konsolenausgabe bei Test am PC
					LCD.drawInt(nachricht[i], i+1, 4);
				}
				
//				System.out.println("");
				//Delay.msDelay(5000);
				
				nachrichtverarbeiten();
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
		
		
		
		if(wert==001){
//			spielstart();
		}
		
		else if(wert==002){
//			pause();
		}
		
		else if(wert==003){
//			endPause();
		}
		
		else if(wert==004){
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
		
		else if(wert==011 || wert==012 || wert==013){
			if(wert==011){
//				deaktiviereGeist1();
			}
			else if(wert==012){
//				deaktiviereGeist2();
			}
			else if(wert==013){
//				deaktiviereGeist3();
			}
		}
		
		else if(wert==021 || wert==022 || wert==023){
			if(wert==021){
//				aktiviereGeist1();
			}
			else if(wert==022){
//				aktiviereGeist2();
			}
			else if(wert==023){
//				aktiviereGeist3();
			}
		}
		
		else if(wert==100){
//			powerUpEnd();
		}
		
		else if(wert==101){
//			geistVerfolgung();
		}
		
		else if(wert==102){
//			geistVerteidigung();
		}
		
		else if(wert==103){
//			geistZufall();
		}
		
		else if(wert==104){
//			sepman();
		}
		
		else if(wert==127){
//			spielende();
		}
		
	
	}

	@Override
	public boolean druckSensor(boolean druck) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}