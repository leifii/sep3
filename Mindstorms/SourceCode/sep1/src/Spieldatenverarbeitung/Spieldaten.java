package Spieldatenverarbeitung;

public class Spieldaten implements ISpieldaten {

	int Leben = 3 ;
	int[] Knoten = new int[36] ;	//0 = frei ; 1 = sepMan ; 2 = Verfolgung
	int[] Kanten = new int[48];		//3 = Random ; 4 = Defense ; 5,6,7,8 = PowerUps
	int[][] Position = new int[2][48];	// 0 = Knoten ; 1 = Kanten
	int[] Modus = new int[4]; // Hinzugefügt von Mark
	
	
	void startbelegung(){
		
/*			int x = 0;
			while(x < 37){	
			Knoten[x] = 0 ;
			x++;	}
			
		Knoten[3] = 2;
		Knoten[6] = 5;
		Knoten[17] = 6;
		Knoten[18] = 3;
		Knoten[19] = 4;
		Knoten[20] = 7;
		Knoten[31] = 8;
		Knoten[34] = 1;
		
		int y = 0;
		while(y<49){
			Kanten[y] = 0;
		}
			*/
		
		int x = 0;
		while(x < 37){	
		Position[0][x] = 0 ;
		x++;	}
		
	Position[0][3] = 2;
	Position[0][6] = 5;
	Position[0][17] = 6;
	Position[0][18] = 3;
	Position[0][19] = 4;
	Position[0][20] = 7;
	Position[0][31] = 8;
	Position[0][34] = 1;
	
	int y = 0;
	while(y<49){
		Position[1][y] = 0;
	}
	
			
		
	}

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


	public int SEPMANPos(){

		int x = 0;
		int y = 0;
		
		while(y == 0 ){
			
			while(x < 37){
				
				if(Position[0][x]!= 1){
					x++;
				}
				else
					
					return x ;							//Knoten 00-36
			}
		
			y++;
			x=0;
			while (y == 1){
				
				while(x<49){
					if(Position[1][x] != 1){
						x++;
				}
					else 
						return x+100; 					//Kanten 100-148
				}
			}
	
		
		
		}
		
		

		return x;
	}
	public int defenderPos(){
		int a =0;

		return a;

	}
	public int tracerPos(){

		int a =0;

		return a;


	}
	public int randomPos(){
		int a =0;

		return a;


	}
	public int powerUp1(){
		int a =0;

		return a;



	}
	public int powerUp2(){

		int a =0;

		return a;

	}
	public int powerUp3(){

		int a =0;

		return a;
		
		
	}
	
	public int powerUp4(){

		int a =0;

		return a;
		
		
	}
	
	public int[] gefahreneKanten(){

		int a[] =null;

		return a;


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
