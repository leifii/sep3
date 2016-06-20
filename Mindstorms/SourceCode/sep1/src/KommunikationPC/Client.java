package KommunikationPC;


import java.awt.EventQueue;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.utility.Delay;
import Anweisungen.Anweisung;


public class Client {
	
	
	public Client ()
	{
		Socket roboter1= null;
//		Socket roboter2= null;
//		Socket roboter3= null;
//		Socket roboter4= null;
		
		try
		{
		roboter1 = new Socket("192.168.2.107", 18415);			//IP-Adresse �ndern (IP vom Brick)!!!!
//		roboter2 = new Socket("192.168.2.109", 18415);
//		roboter3 = new Socket("192.168.2.109", 18415);
//		roboter4 = new Socket("192.168.2.109", 18415);
		System.out.println("Server gefunden!");
		
		
		
		//Stream
		//Anweisung keylistener = new Anweisung();
		Kommunikation com1 = new Kommunikation(roboter1);
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				new Anweisung();
			}
		});
		
		
		
		while(true){
		com1.senden();
		com1.empfangen();
		Delay.msDelay(10);
		}
		
		
		//Stream ende
		
	//	System.out.println("Verbindung getrennt!");
		}
		
		catch(UnknownHostException e ) {
		      e.printStackTrace();
	    }
	    catch ( IOException e ) {
	      e.printStackTrace();
	    }
		finally {
		      if ( roboter1 != null )
		        try { 
		        roboter1.close(); 
//		        roboter2.close(); 
//		        roboter3.close(); 
//		        roboter4.close(); 
		        } catch ( IOException e ) { }
		    }
	}

		

		
}
