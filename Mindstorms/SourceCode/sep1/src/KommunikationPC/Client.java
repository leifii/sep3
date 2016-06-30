/**
 * @author ${Tristan}
 *
 * 
 */

package KommunikationPC;
import java.io.IOException;
import java.net.Socket;



public class Client implements Runnable {                  //Klasse Client implementiert Runnable, um mehrer Instanzen simultan laufen zu lassen
														   //Pro Clientobjekt ein Thread, der alle 10ms ein ByteArray verschickt
	String ip;
	String modus;
	int port;
	int clientNr;
	QueueHandler queue;
	
	
	public Client (String ip, String modus, int port, int clientNr, QueueHandler q){     //Konstruktor - neues Objekt vom Typ Client. 
		
		this.ip    = ip;
		this.modus = modus;
		this.port  = port;
		this.clientNr = clientNr;
		this.queue = q;
		
	}

//	@Override   ====>Overridden 
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			Socket client = new Socket(ip, port);           		 //Initialisierung eines Objekts des Typs Socket
			Kommunikation kom = new Kommunikation(client, clientNr, queue);  		 //Initialisierung eines Objekts des Typs Kommunikation
			
			if(modus == "Tracer"){                                   //Erstes an den Roboter gesendetes ByteArray, setzt entsprechenden Modus
				
				byte[] tracer = {0, 0, 0, 0, 0, 0, 0, 0, 101};
				kom.senden(tracer);
				Thread.sleep(10);
			}
			
			if(modus == "Defender"){								 //Erstes an den Roboter gesendetes ByteArray, setzt entsprechenden Modus
				
				byte[] defender = {0, 0, 0, 0, 0, 0, 0, 0, 102};
				kom.senden(defender);
				Thread.sleep(10);
				
			}
			
			if(modus == "Random"){									 //Erstes an den Roboter gesendetes ByteArray, setzt entsprechenden Modus
				
				byte[] random = {0, 0, 0, 0, 0, 0, 0, 0, 103};
				kom.senden(random);
				Thread.sleep(10);
				
			}
			
			if(modus == "Sepman"){									 //Erstes an den Roboter gesendetes ByteArray, setzt entsprechenden Modus
				
				byte[] sepman = {0, 0, 0, 0, 0, 0, 0, 0, 104};
				kom.senden(sepman);
				Thread.sleep(10);
				
			}
			while(true){                                              //Sendet alle 10ms ein ByteArray mit den aktuellen Positionen sämtlicher
																	  //Roboter, ihren Zielpositionen und den Spielinformationen und
				                                                      //empfängt das "Antwort"-ByteArray.
				
				kom.senden(kom.erzeugeByteArray());
				
				while(client.getInputStream().available() > 0){
					kom.empfangen();
				}
				try{
					Thread.sleep(10);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
		  }
		 catch(InterruptedException e){
			 e.printStackTrace();
		 }
		}		
	}
