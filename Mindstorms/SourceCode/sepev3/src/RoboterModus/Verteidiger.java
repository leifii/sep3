/**
 * @author ${Mark}
 *
 * 
 */



//**********************
// Noch nicht feritg!!!
//**********************
package RoboterModus;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import Spielfeld.Planeinit;

public class Verteidiger extends IModus{
	
	private int letzterKnoten;
	private int zielposition;
	public boolean alive = true;

	public Verteidiger(int start ,Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor) {
		super(plane, lvfg, drucksensor);
		aktuelleposition = start;
		letzterKnoten = aktuelleposition ;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(pause==false && isAktiviert()){
			Bewegung();
		}
		// TODO Auto-generated method stub
		
	}
	
	

   public void Bewegung(){
			int pos = aktuelleposition;
			switch(pos){
			case 31 : 
				lvfg.geradeaus();
				letzterKnoten = 31;
			break;
			case 32 : 
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 32;
			break;
			case 22: 
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 22;
			break;
			case 23 : 
				lvfg.geradeaus();
				letzterKnoten = 23;
			break;
			case 24 :  
				lvfg.geradeaus();
				letzterKnoten = 24;
			break;
			case 25 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 25;
			break;
			case 15 :  
				lvfg.geradeaus();
				letzterKnoten = 15;
			break;
			case 05 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 05;
			break;
			case 06 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 06;
			break;
			case 16 :
				lvfg.geradeaus();
				letzterKnoten = 16;
			break;
			case 26 :
				lvfg.geradeaus();
				letzterKnoten = 26;
			break;
			case 36 :  
				lvfg.geradeaus();
				letzterKnoten = 36;
			break;
			case 46 :  
				lvfg.geradeaus();
				letzterKnoten = 46;
			break;
			case 56 :
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 56;
			break;
			case 55 :  
				lvfg.geradeaus();
				letzterKnoten = 55;
			break;
			case 54 :  
				lvfg.geradeaus();
				letzterKnoten = 54;
			break;
			case 53 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 53;
			break;
			case 43 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 43;
			break;
			case 42 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 42;
			break;
			case 52 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 52;
			break;
			case 51 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 51;
			break;
			case 41 :  
				lvfg.geradeaus();
				letzterKnoten = 41;
			break;
	   }
   }
   }

   
