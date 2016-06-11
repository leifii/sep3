package KommunikationPC;

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
		
	}
	public int erzeugeByteArray(){
		return 1;
	}

}
