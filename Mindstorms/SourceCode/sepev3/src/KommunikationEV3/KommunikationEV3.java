package KommunikationEV3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;


public class KommunikationEV3 implements IKommunikation{
	
	Linienverfolgung lvfg;
	Drucksensor druck;
	Socket socket;
	byte[] nachrichtsenden= new byte[9];
	byte[] nachrichtempfangen= new byte[9];
	
	int letzterwert = 0;
	
	
	public byte[] getNachricht()
	{
		return nachrichtempfangen;
	}
	
	
	public KommunikationEV3(Socket soc, Drucksensor druck){
		socket = soc;
		lvfg=new Linienverfolgung();
		druck=new Drucksensor();
	}
	
	public void senden(){
		erzeugeByteArray();
			try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(nachrichtsenden.length);
			out.write(nachrichtsenden);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public byte[] empfangen(){
		
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
								//Lï¿½nge der Nachricht lesen
			if(in.readInt()>0){
			
				in.readFully(nachrichtempfangen, 0, nachrichtempfangen.length);	//Speicherort der Nachricht, Anfang, Ende
				

//				LCD.drawString("Nachricht", 0, 1);                                 //Displayausgabe bei Test am EV3
//				LCD.drawString("empfangen:", 0, 2);                                //
//				
//				for(int i = 0; i<nachricht.length; i++){
//					System.out.print(nachricht[i]);                                //Konsolenausgabe bei Test am PC
//					LCD.drawInt(nachricht[i], i+1, 4);
//				}
//				

				
				//nachrichtverarbeiten();
			}
			
			
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nachrichtempfangen;
		
		
	}
	public int erzeugeByteArray(){
		return 1;
	}
	
	public void nachrichtverarbeiten(){
		
		
		int wert = nachrichtempfangen[8];
		
		

		if(wert==001){
//			spielstart();
		}
		
		else if(wert==2){
//			pause();
			lvfg.stop();
		}
		
		else if(wert==003){
//			endPause();
		}
		
		
		else if (wert==4 || wert==5 || wert==7){
				
				lvfg.geradeaus(); //EV3 fährt nach dem ersten geradeaus Befehl automatisch nachdem es Knoten verlässt geradeaus.
				
				if (lvfg.aufKnoten()==true){
					if(wert==5){ 
						lvfg.drehenLinks();		//Taste Links dreht nach Links
					}
					if(wert==7){
						lvfg.drehenRechts();		//Taste Rechts dreht nach Rechts
					}
				}
		}

		else if(wert==6){
	
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

	public boolean druckSensor() {
		return druck.druckSensor(); //Boolean Wert der für später benutzt werden kann.
	}
}