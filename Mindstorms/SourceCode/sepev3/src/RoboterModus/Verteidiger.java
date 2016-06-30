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
		// TODO Auto-generated method stub
		
	}
	
	

   public void Bewegung(){
	   while(alive ==true){
			int pos = aktuelleposition;
			switch(pos){
			case 31 : 
				lvfg.geradeaus();
			break;
			case 32 : 
				lvfg.drehenLinks();
				lvfg.geradeaus();
			break;
			case 22: 
				lvfg.drehenRechts();
				lvfg.geradeaus();
			break;
			case 23 : 
				lvfg.geradeaus();
			break;
			case 24 :  
				lvfg.geradeaus();
			break;
			case 25 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
			break;
			case 15 :  
				lvfg.geradeaus();
			break;
			case 05 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
			break;
			case 06 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
			break;
			case 16 :
				lvfg.geradeaus();
			break;
			case 26 :
				lvfg.geradeaus();
			break;
			case 36 :  
				lvfg.geradeaus();
			break;
			case 46 :  
				lvfg.geradeaus();
			break;
			case 56 :
				lvfg.drehenRechts();
				lvfg.geradeaus();
			break;
			case 55 :  
				lvfg.geradeaus();
			break;
			case 54 :  
				lvfg.geradeaus();
			break;
			case 53 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
			break;
			case 43 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
			break;
			case 42 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
			break;
			case 52 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
			break;
			case 51 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
			break;
			case 41 :  
				lvfg.geradeaus();
			break;
	   }
   }
   }

   }
