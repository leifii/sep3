package KommunikationEV3;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{


		public static void main(String[] args) throws IOException, ClassNotFoundException {
	
			
			ServerSocket sSocket = new ServerSocket(18415);
			System.out.println("Ich akzeptiere gerade!");
			
			
			try{
			
			Socket roboter1 = sSocket.accept();
			System.out.println("Robter1 verbunden!");
//			Socket roboter2 = sSocket.accept();
//			System.out.println("Robter2 verbunden!");
//			Socket roboter3 = sSocket.accept();
//			System.out.println("Robter3 verbunden!");
//			Socket roboter4 = sSocket.accept();
//			System.out.println("Robter4 verbunden!");
			sSocket.close();
			
			// Stream 

			KommunikationEV3 com1 = new KommunikationEV3(roboter1);
			com1.empfangen();
			com1.senden();
			
			// Stream ende 
			
			roboter1.close();
//			roboter2.close();
//			roboter3.close();
//			roboter4.close();
			
			System.out.println("Habe fertig!");
			}
			
			 catch ( IOException e ) {
			        e.printStackTrace();
	 	} 

	
		}
}