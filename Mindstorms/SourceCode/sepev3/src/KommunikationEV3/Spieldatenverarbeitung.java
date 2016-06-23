package KommunikationEV3;

public class Spieldatenverarbeitung  {
	
	
	public void sepman(byte[] com_array){
		
		int start = com_array[06];
		int ziel = com_array[07];
		
		if(ziel-start == 1){
		IBewegung.goEast();
		}
		else if(ziel-start == -1){
		IBewegung.goWest();
		}
		else if(ziel-start == 10){
		IBewegung.goSouth();	
		}
		else if(ziel-start == -10){
		IBewegung.goNorth();	
		}
	}
	
	public void geist1(byte[] com_array){
		
		int start = com_array[00];
		int ziel = com_array[01];
		
		if(ziel-start == 1){
		IBewegung.goEast();
		}
		else if(ziel-start == -1){
		IBewegung.goWest();
		}
		else if(ziel-start == 10){
		IBewegung.goSouth();	
		}
		else if(ziel-start == -10){
		IBewegung.goNorth();	
		}
	}
	public void geist2(byte[] com_array){
			
		int start = com_array[02];
		int ziel = com_array[03];
			
		if(ziel-start == 1){
		IBewegung.goEast();
		}
		else if(ziel-start == -1){
		IBewegung.goWest();
		}
		else if(ziel-start == 10){
		IBewegung.goSouth();	
		}
		else if(ziel-start == -10){
		IBewegung.goNorth();	
	    	}
		}
		
		
	public void geist3(byte[] com_array){
			
		int start = com_array[04];
		int ziel = com_array[05];
			
		if(ziel-start == 1){
		IBewegung.goEast();
		}
		else if(ziel-start == -1){
		IBewegung.goWest();
		}
		else if(ziel-start == 10){
		IBewegung.goSouth();	
		}
		else if(ziel-start == -10){
		IBewegung.goNorth();	
			}
		}
}
