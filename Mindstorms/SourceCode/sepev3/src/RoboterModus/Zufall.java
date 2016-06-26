package RoboterModus;


import Linienverfolger.Linienverfolgung;
import KommunikationEV3.IKommunikation;
import KommunikationEV3.KommunikationEV3;


public class Zufall implements IZufall{
	int aktuelleposition=26;
	int zielposition =0;
	int änderung=0;
	byte[] nachricht= new byte[9];
	
	
	
	
	
	
	
	
	
	
	
	public int getPosG3(){
		
		return aktuelleposition;
	}
	
	
	public int getDestG3(){
		
		return zielposition;
		
	}
	
	
	
	
	public void setNachricht(byte[] mes){
		
		nachricht = mes;
		
	}
	
	

}
