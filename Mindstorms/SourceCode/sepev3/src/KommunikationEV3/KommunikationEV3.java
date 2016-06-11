package KommunikationEV3;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class KommunikationEV3 implements IKommunikation{

	Socket socket;
	
	public KommunikationEV3(Socket soc){
		socket = soc;
	}
	
	public void senden(){
		
	}
	public void empfangen(){
		
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			int length = in.readInt();		//Länge der Nachricht lesen
			if(length>0){
				byte[] nachricht = new byte[length];
				in.readFully(nachricht, 0, nachricht.length);	//Speicherort der Nachricht, Anfang, Ende
				
				System.out.println("Nachricht empfangen");
				
				for(int i = 0; i<nachricht.length; i++){
					System.out.print(nachricht[i]);
				}
				System.out.println("");
			}
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public int erzeugeByteArray(){
		return 1;
	}
}
