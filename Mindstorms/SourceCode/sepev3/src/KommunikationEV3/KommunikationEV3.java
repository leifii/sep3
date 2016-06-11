package KommunikationEV3;

import java.net.Socket;

public class KommunikationEV3 implements IKommunikation{

	Socket socket;
	
	public KommunikationEV3(Socket soc){
		socket = soc;
	}
	
	public void senden(){
		
	}
	public void empfangen(){
		
	}
	public int erzeugeByteArray(){
		return 1;
	}
}
