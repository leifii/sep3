 package Spieldatenverarbeitung;

public class Spieldaten implements ISpieldaten {
	
	int Leben = 3 ;
	int[] Modus = new int[4]; // Hinzugefügt von Mark
	
	public void setLifes(int lifes){
		
		if (lifes < 0) 
			System.out.println("Bitte einen Wert zwischen 0 und 3 angeben.");
		
		else if (lifes > 3)
			System.out.println("Bitte einen Wert zwischen 0 und 3 angeben.");
			
		else Leben = lifes;
		
		}
	
	public int getLifes(){
		
		return Leben;
		
		
	}
	
	
	public int[][] SEPMANPos(){
		
		
		
		return 
	}
	public int[][] defenderPos(){
		
				
	}
	public int[][] tracerPos(){
		
		
		
		
	}
	public int[][] randomPos(){
		
		
		
	}
	public int[][] powerUp1(){
		
		
		
		
	}
	public int[][] powerUp2(){
		
		
		
	}
	public int[][] powerUp3(){
		
		
		
	}
	public int[] gefahreneKanten(){
		
		
		
		return 
	}
	public boolean kanteGefahren(int kante){
		
		
		
		return true;
		
	}
    // Hinzugefügt von Mark
	@Override
	public String[] getRoboterbelegung() {
		// TODO Auto-generated method stub
		return null;
	}
	// Hinzugefügt von Mark
	@Override
	public void setRoboterbelegung(int pos, int wert) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
