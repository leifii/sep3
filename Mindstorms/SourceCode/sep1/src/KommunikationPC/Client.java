/**
 * @author ${user}
 *
 * 
 */

package KommunikationPC;


import java.awt.EventQueue;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.utility.Delay;

/////@Author 
///////Tristan


public class Client implements Runnable {
	
	String ip;
	String modus;
	int port;
	
	
	public Client (String ip, String modus, int port){
		
		this.ip    = ip;
		this.modus = modus;
		this.port  = port;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			Socket client = new Socket(ip, port);
			Kommunikation kom = new Kommunikation(client);
			
			if(modus == "Tracer"){
				
				byte[] tracer = {0, 0, 0, 0, 0, 0, 0, 0, 101};
				kom.senden(tracer);
			}
			
			if(modus == "Defender"){
				
				byte[] defender = {0, 0, 0, 0, 0, 0, 0, 0, 102};
				kom.senden(defender);
				
			}
			
			if(modus == "Random"){
				
				byte[] random = {0, 0, 0, 0, 0, 0, 0, 0, 103};
				kom.senden(random);
				
			}
			
			if(modus == "Sepman"){
				
				byte[] sepman = {0, 0, 0, 0, 0, 0, 0, 0, 104};
				kom.senden(sepman);
				
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		  }
		
		while(true){
			Kommunikation.senden(Kommunikation.erzeugeByteArray());
			
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}		
}
