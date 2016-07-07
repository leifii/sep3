/**
 * @author ${Tristan}
 *
 * 
 */
package KommunikationPC;

import java.io.IOException;
import java.net.Socket;
import Anzeige.Menu;
import Positionen.Position;
import PowerUps.PowerUp;

public class Kommunikation implements IKommunikation{	
	
	Socket socket;
	int clientNr;
	String modus;
	static byte[] nachricht= new byte[9];	
	QueueHandler queue;
	Position pos;
	static byte[] msg    = new byte[9];
	static PowerUp powerUp;
	
	
	public Kommunikation(Socket soc, int clientNr, QueueHandler q, Position p, String mod){	
		this.socket   = soc;	
		this.clientNr = clientNr;
		this.queue    = q;
		this.pos      = p;
		this.modus    = mod;
	}

	
	
	public void senden(byte[] toSend){                  //Methode ermöglicht das Schreiben von Daten auf einen Stream.
		
		try {
			socket.getOutputStream().write(toSend);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	public void empfangen(){	                        //Methode ermöglicht das Empfangen bzw. Lesen von Daten asu einem Stream
		try {
			socket.getInputStream().read(nachricht);
			nachrichtVerarbeiten(nachricht);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public  byte[] erzeugeByteArray(){            //Methode erzeugt das vom PC an die Roboter zu sendende ByteArray
			
			msg[0] = (byte) pos.getPosTracer();
			msg[1] = (byte) pos.getDestTracer();
			msg[2] = (byte) pos.getPosDefender();
			msg[3] = (byte) pos.getDestDefender();
			msg[4] = (byte) pos.getPosRandom();
			msg[5] = (byte) pos.getDestRandom();
			msg[6] = (byte) pos.getPosSepman();
			msg[7] = (byte) pos.getDestSepman();
			msg[8] = queue.getNextQueued(clientNr);
					
			return msg;
	}
	
	
	
	public  void nachrichtVerarbeiten(byte[] msg){            //Methode wertet das vom Roboter empfangene ByteArray aus
		
		powerUp = Anzeige.Menu.powerUp.getPowerUp();
		if(modus=="Sepman"){
			for(int i=0;i<msg.length;i++){
				if(i==0){
					pos.setPosSepman(msg[0]);
					//pos.pushPosArray();
					if(msg[0]==06){                 
						powerUp.aktivierePowerUp(06);                //aktiviert PowerUp
					}
					if(msg[0]==25){
						powerUp.aktivierePowerUp(25);
					}
					if(msg[0]==32){
						powerUp.aktivierePowerUp(32);
					}
					if(msg[0]==51){
						powerUp.aktivierePowerUp(51);
						
					}
				}
				if(i==1){
					pos.setDestSepman(msg[1]);
				}
				if(i==2){
					if(msg[2]==1){
						Menu.kollidiertSepman = true;
					}
				}
				if(i==3){
					if(msg[3]==0){
						//roboter inaktiv!
					}
					else{
						//roboter aktiv!
					}
				}
				if(i==4){
					if(msg[4]==0){
						//powerup inaktiv!
					}
					else{
						//powerup aktiv!
					}
				}
				if(i==5){
					if(msg[5]==0){
						//Spiel nicht pausiert
					}
					else{
						//Spiel pausiert
					}
				}
			}
			
		}
		if(modus=="Tracer"){
			for(int i=0;i<msg.length;i++){
				if(i==0){
					pos.setPosTracer(msg[0]);
				}
				if(i==1){
					pos.setDestTracer(msg[1]);
				}
				if(i==2){
					if(msg[2]==1){
						Menu.kollidiertTracer = true;
					}
				}
				if(i==3){
					if(msg[3]==0){
						//roboter inaktiv!
					}
					else{
						//roboter aktiv!
					}
				}
				if(i==4){
					if(msg[4]==0){
						//powerup inaktiv!
					}
					else{
						//powerup aktiv!
					}
				}
				if(i==5){
					if(msg[5]==0){
						//Spiel nicht pausiert
					}
					else{
						//Spiel pausiert
					}
				}
			}
			
		}
		if(modus=="Defender"){
			for(int i=0;i<msg.length;i++){
				if(i==0){
					pos.setPosDefender(msg[0]);
				}
				if(i==1){
					pos.setDestDefender(msg[1]);
				}
				if(i==2){
					if(msg[2]==1){
						Menu.kollidiertDefender = true;
						
					}
				}
				if(i==3){
					if(msg[3]==0){
						//roboter inaktiv!
					}
					else{
						//roboter aktiv!
					}
				}
				if(i==4){
					if(msg[4]==0){
						//powerup inaktiv!
					}
					else{
						//powerup aktiv!
					}
				}
				if(i==5){
					if(msg[5]==0){
						//Spiel nicht pausiert
					}
					else{
						//Spiel pausiert
					}
				}
			}
			
		}
		if(modus=="Random"){
			for(int i=0;i<msg.length;i++){
				if(i==0){
					pos.setPosRandom(msg[0]);
				}
				if(i==1){
					pos.setDestRandom(msg[1]);
				}
				if(i==2){
					if(msg[2]==1){
						Menu.kollidiertRandom = true;
					
					}
				}
				if(i==3){
					if(msg[3]==0){
						//roboter inaktiv!
					}
					else{
						//roboter aktiv!
					}
				}
				if(i==4){
					if(msg[4]==0){
						//powerup inaktiv!
					}
					else{
						//powerup aktiv!
					}
				}
				if(i==5){
					if(msg[5]==0){
						//Spiel nicht pausiert
					}
					else{
						//Spiel pausiert
					}
				}
			}
			
		}
		
	}

//	@Override
//	public byte[] getNachricht() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
