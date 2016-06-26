package RoboterModus;


import Linienverfolger.Linienverfolgung;
import KommunikationEV3.IKommunikation;
import KommunikationEV3.KommunikationEV3;


public class Zufall implements IModus{
	int aktuelleposition=26;
	int zielposition =0;
	int änderung=0;
	byte[] nachricht= new byte[9];
	
	
	
	public void geistZufall(){
		
		
		
		
	}
	

	public int getPos(){
		
		return aktuelleposition;
	}
	
	
	public int getDest(){
		
		return zielposition;
		
	}
	
	
	
	
	public void setNachricht(byte[] mes){
		
		nachricht = mes;
		
	}
	
	

}
