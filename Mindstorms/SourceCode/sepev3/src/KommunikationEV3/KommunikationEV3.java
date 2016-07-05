/**
 * @author ${Katharina Böse}
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
	
	
	Socket socket;
	byte[] nachrichtsenden= new byte[9];
	byte[] nachrichtempfangen= new byte[9];
	
	int letzterwert = 0;
	
	public KommunikationEV3(Socket soc){		//Konstruktor 
		socket = soc;
	}
	
	public void senden(byte[] message){													//senden der Nachricht
			try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(message.length);
			out.write(message);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public byte[] empfangen(){													//empfangen der Nachricht
		
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());


				in.readFully(nachrichtempfangen, 0, nachrichtempfangen.length);	//Speicherort der Nachricht, Anfang, Ende
				

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nachrichtempfangen;
		
		
	}
	
	//erzeugt das ByteArray, welches gesendet wird
	public byte[] erzeugeByteArray(int ausgangsknoten, int zielknoten, boolean sensor, boolean aktivierung, boolean powerup, boolean pause){	
		byte[] mes = new byte[9];
		
		mes[0] = (byte) ausgangsknoten;			//Ausgangsknoten und Zielknoten des Roboters
		mes[1] = (byte) zielknoten;
		
		if(sensor) mes[2] = 1;					//Drucksensorwert: 1 ist gedrückt, 0 nicht gedrückt
		else mes[2] = 0;
		
		if(aktivierung) mes[3] = 1;				//Aktivierungsstatus: 1 Roboter ist deaktiviert, 0 Roboter ist aktiviert
		else mes[3] = 0;
		
		if(powerup) mes[4] = 1;					//Powerupstatus: 1 Powerup ist aktiviert, 0 ist deaktiviert
		else mes[4] = 0;
		
		if(pause) mes[5] = 1;					//Pausestatus: 1 Pause ist aktiviert, 0 ist deaktiviert
		else mes[5] = 0;
		
		return mes;
	}
	

}