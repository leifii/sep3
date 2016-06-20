package KommunikationEV3;

public class Spieldatenverarbeitung  {

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
	
	public void geist1(){
		
		int start = arrayname[00];
		int ziel = arrayname[01];
		
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
		public void geist2(){
			
			int start = arrayname[02];
			int ziel = arrayname[03];
			
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
		
		
		public void geist3(){
			
			int start = arrayname[04];
			int ziel = arrayname[05];
			
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
