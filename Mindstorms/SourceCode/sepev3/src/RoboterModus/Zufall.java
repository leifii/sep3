package RoboterModus;


import java.util.Random;

import Linienverfolger.Linienverfolgung;



public class Zufall implements IModus{
	int aktuelleposition=26;
	int zielposition =0;
	int änderung=0;
	byte[] nachricht= new byte[9];
	Random r;
	
	
	
	public void run(){
		int zahl = r.nextInt(3);
		
		
		
		
		
		
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
