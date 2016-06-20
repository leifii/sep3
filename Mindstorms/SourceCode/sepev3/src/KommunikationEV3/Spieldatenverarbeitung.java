package KommunikationEV3;

public class Spieldatenverarbeitung implements {

	public Spieldatenverarbeitung(int k){
	}
	byte[] arrayname = KommunikationEV3.getNachricht() ;
	
	
	public void sepman(){
		
		int start = arrayname[06];
		int ziel = arrayname[07];
		
		if(ziel-start == 1){
		//	goEast();
		}
		else if(ziel-start == -1){
		//	goWest();
		}
		else if(ziel-start == 10){
		//	goSouth();	
		}
		else if(ziel-start == -10){
		//	goNorth();	
		}
	}
	
	
	
}
