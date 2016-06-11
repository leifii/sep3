package KommunikationPC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Kommunikation implements IKommunikation{
	
	Socket socket;
	
	
	public Kommunikation(Socket soc){
		
		socket = soc;
		
	}
	
	public void senden(){
		byte[] nachricht = {4,5,2,3,8,9,1,0,3};
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(nachricht.length);
			out.write(nachricht);
			System.out.println("Nachricht gesendet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
