package KommunikationPC;


import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

		public static void main(String[] args) throws UnknownHostException, IOException, EOFException {
			Socket roboter1= null;
//			Socket roboter2= null;
//			Socket roboter3= null;
//			Socket roboter4= null;
			
			try
			{
			roboter1 = new Socket("192.168.2.104", 18415);			//Terminal: ipconfig !!!!   IP-Adresse ändern!!!!
//			roboter2 = new Socket("192.168.2.104", 18415);
//			roboter3 = new Socket("192.168.2.104", 18415);
//			roboter4 = new Socket("192.168.2.104", 18415);
			System.out.println("Server gefunden!");
			
			Kommunikation com1 = new Kommunikation(roboter1);
			com1.senden();
			
			//Stream
			
			
			
			
			
			//Stream ende
			
			System.out.println("Verbindung getrennt!");
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
//			        roboter2.close(); 
//			        roboter3.close(); 
//			        roboter4.close(); 
			        } catch ( IOException e ) { }
			    }


		}
}
