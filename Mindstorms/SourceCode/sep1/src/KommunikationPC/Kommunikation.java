package KommunikationPC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Anzeige.Menu;

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
			
			int length = in.readInt();		//L�nge der Nachricht lesen
			if(length>0){
				in.readFully(nachricht, 0, nachricht.length);	//Speicherort der Nachricht, Anfang, Ende
				
				System.out.println("Nachricht empfangen");
				
				for(int i = 0; i<nachricht.length; i++){
					System.out.print(nachricht[i]);
				}
				System.out.println("");
				
				nachrichtVerarbeiten(nachricht);
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] erzeugeByteArray(){
		for(int i = 0; i < nachricht.length-1; i++){
			if(i == 0){
//				nachricht[0] = getPosG1();
			}
			else if(i==1){
//				nachricht[1] = getDestG1();
			}
			else if(i==2){
//				nachricht[2] = getPosG2();
			}
			else if(i==3){
//				nachricht[3] = getDestG2();
			}
			else if(i==4){
//				nachricht[4] = getPosG3();
			}
			else if(i==5){
//				nachricht[5] = getPosSepman();
			}
			else if(i==6){
//				nachricht[6] = getDestSepman();
			}
		}
	if(Modus.richtung ==4){
		
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
	
	public static void nachrichtVerarbeiten(byte[] whatever){
		
		for(int i=0; i<whatever.length; i++){
			if(i==0){
//				setAusgangsKnoten(whatever[i]);           Ausgangsknoten des Roboters. Die Nummer des Knotens, von dem der Roboter kommt.
			}
			else if(i==1){
//				setZielKnoten(whatever[i]);               Zielknoten des Roboters. Die Nummers des Knotens, zu dem der Roboter geht.
			}
			else if(i==2){
				if(whatever[i]==1){
					Kollisionen.Kollision.kollision();    // Wird aufgerufen, wenn der Drucksensor aktiviert ist, es also zu einer Kollision kam.
				}
			}
			else if(i==3){								//Aktivierungsstatus des Roboters. 0 = Inaktiv; 1 = Aktiv
				if(whatever[i]==0){
//					doSmthWhileInactive();                Verarbeitung der Mitteilung, dass Roboter inaktiv ist.                 
				}
				else{
//					doSmthWhileActive();                  Verarbeitung der Mitteilung, dass Roboter aktiv ist.
				}
			}
			else if(i==4){								//PowerUp-Status des Sepman. 0 = Inaktiv; 1 = Aktiv
				if(whatever[i]==0){
//					powerUpInaktiv();                     Verarbeitung der Mitteilung, dass PowerUp-Status inaktiv ist.
				}
				else{
//					powerUpAktiv();                       Verarbeitung der Mitteilung, dass PowerUp-Status aktiv iss.
				}
			}
			else if(i==5){                              //Pausenstatus des Spiels. 0 = nicht pausiert; 1 = pausiert            
				if(whatever[i]==0){
//					spielNichtPausiert();
				}
				else{
//					spielPausiert();
				}
			}
			
		}
		
	}

}
