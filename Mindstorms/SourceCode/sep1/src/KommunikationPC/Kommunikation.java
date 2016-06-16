package KommunikationPC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Anweisungen.Anweisung;


public class Kommunikation implements IKommunikation{
	
	Socket socket;
	byte[] nachricht= new byte[9];
	
	
	public Kommunikation(Socket soc){
		
		socket = soc;
		
	}
	
	public void senden(){
		erzeugeByteArray();
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
	
	
	
	public byte[] erzeugeByteArray(){
		
	
	
	if(Anweisung.richtung ==4){
		
		nachricht[8]=4;
	}
	
	else if(Anweisung.richtung ==6){
		
		nachricht[8]=6;
	}
	
	else if(Anweisung.richtung ==5){
		
		nachricht[8]=5;
	}
	
	else if(Anweisung.richtung ==7){
		
		nachricht[8]=7;
	}
	
	else if(Anweisung.richtung ==2){
		
		nachricht[8]=2;
	}
	 
	 
	
	
	nachricht[0]=00;
	nachricht[1]=01;
	nachricht[2]=02;
	nachricht[3]=03;
	nachricht[4]=04;
	nachricht[5]=05;
	nachricht[6]=06;
	nachricht[7]=07;
	//nachricht[8]=(byte) wert;
	
		
		return nachricht;
	}

}
