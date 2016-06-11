package KommunikationEV3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import Drucksensorverarbeitung.IDrucksensor;
import Steuerbefehle.ISteuerbefehl;

public class KommunikationEV3 implements IKommunikation, ISteuerbefehl, IDrucksensor{

	Socket socket;
	
	public KommunikationEV3(Socket soc){
		socket = soc;
	}
	
	public void senden(){
		
		byte[] nachricht = {6,6,6,6,6,6,6,6,6};
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
			
			int length = in.readInt();		//L�nge der Nachricht lesen
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
	
	public boolean druckSensor(boolean druck){
		return druck;
	}
	
	@Override
	public void fahreVorwärts() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void MotorSuchtLinie() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drehenLinks() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drehenRechts() {
		// TODO Auto-generated method stub
		
	}
	
}
