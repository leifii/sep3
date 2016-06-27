/**
 * @author ${user}
 *
 * 
 */
package KommunikationPC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Anzeige.Menu;

public class Kommunikation implements IKommunikation{	
	
	static Socket socket;
	byte[] nachricht= new byte[9];	
	public Kommunikation(Socket soc){	
		socket = soc;	
	}

	
	
	public static void senden(byte[] toSend){
		
		try {
			socket.getOutputStream().write(toSend);
			
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
				
				nachrichtVerarbeiten(nachricht);
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static byte[] erzeugeByteArray(){
			byte[] msg    = new byte[9];
			int[] aktPos  = new int[3];
			int[] zkftPos = new int[3];
			aktPos = Positionen.Position.getAktuellePos();
			zkftPos= Positionen.Position.getZielPos();
			
			msg[0] = (byte) aktPos[2];     //Position "Tracker"
			msg[1] = (byte) zkftPos[2];
			
			msg[2] = (byte) aktPos[3];     //Position "Defender"
			msg[3] = (byte) zkftPos[3];
			
			msg[4] = (byte) aktPos[1];     //Position "Random"
			msg[5] = (byte) zkftPos[1];
			
			msg[6] = (byte) aktPos[0];     //Position "Sepman"
			msg[7] = (byte) zkftPos[0];
			
			
			msg[8] = (byte) Anzeige.Menu.richtung;
					
			return msg;
	}
	
	
	
	public  void nachrichtVerarbeiten(byte[] whatever){
		
		for(int i=0; i<whatever.length; i++){
			if(i==0){
//				setAusgangsKnoten(whatever[i]);           Ausgangsknoten des Roboters. Die Nummer des Knotens, von dem der Roboter kommt.
			}
			else if(i==1){
//				setZielKnoten(whatever[i]);               Zielknoten des Roboters. Die Nummers des Knotens, zu dem der Roboter geht.
			}
			else if(i==2){
				if(whatever[i]==1){
					Kollisionen.Kollision.kollision();    // Wird aufgerufen, wenn der Drucksensor aktiviert ist, es also zu einer Kollision kam.
				}
			}
			else if(i==3){								//Aktivierungsstatus des Roboters. 0 = Inaktiv; 1 = Aktiv
				if(whatever[i]==0){
//					doSmthWhileInactive();                Verarbeitung der Mitteilung, dass Roboter inaktiv ist.                 
				}
				else{
//					doSmthWhileActive();                  Verarbeitung der Mitteilung, dass Roboter aktiv ist.
				}
			}
			else if(i==4){								//PowerUp-Status des Sepman. 0 = Inaktiv; 1 = Aktiv
				if(whatever[i]==0){
//					powerUpInaktiv();                     Verarbeitung der Mitteilung, dass PowerUp-Status inaktiv ist.
				}
				else{
//					powerUpAktiv();                       Verarbeitung der Mitteilung, dass PowerUp-Status aktiv iss.
				}
			}
			else if(i==5){                              //Pausenstatus des Spiels. 0 = nicht pausiert; 1 = pausiert            
				if(whatever[i]==0){
//					spielNichtPausiert();
				}
				else{
//					spielPausiert();
				}
			}
			
		}
		
	}

	@Override
	public byte[] getNachricht() {
		// TODO Auto-generated method stub
		return null;
	}

}
