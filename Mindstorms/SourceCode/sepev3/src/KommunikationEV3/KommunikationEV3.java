/**
 * @author ${user}
 *
 * 
 */
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
	}
	
	public void senden(byte[] message){
			try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(message.length);
			out.write(message);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public byte[] empfangen(){
		
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());


				in.readFully(nachrichtempfangen, 0, nachrichtempfangen.length);	//Speicherort der Nachricht, Anfang, Ende
				

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nachrichtempfangen;
		
		
	}
	public byte[] erzeugeByteArray(int ausgangsknoten, int zielknoten, boolean sensor, boolean aktivierung, boolean powerup, boolean pause){
		byte[] mes = new byte[9];
		
		mes[0] = (byte) ausgangsknoten;
		mes[1] = (byte) zielknoten;
		
		if(sensor) mes[2] = 1;
		else mes[2] = 0;
		
		if(aktivierung) mes[3] = 1;
		else mes[3] = 0;
		
		if(powerup) mes[4] = 1;
		else mes[4] = 0;
		
		if(pause) mes[5] = 1;
		else mes[5] = 0;
		
		return mes;
	}
	


	public boolean druckSensor() {
		return druck.druckSensor(); //Boolean Wert der für später benutzt werden kann.
	}


}