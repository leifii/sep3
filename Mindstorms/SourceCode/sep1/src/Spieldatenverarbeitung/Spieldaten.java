package Spieldatenverarbeitung;

public class Spieldaten implements ISpieldaten {

	int Leben = 3 ;
//	int[] Knoten = new int[36] ;	//0 = frei ; 1 = sepMan ; 2 = Verfolgung
//	int[] Kanten = new int[48];		//3 = Random ; 4 = Defense ; 5,6,7,8 = PowerUps
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


	public int getSEPMANPos(){

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
		
		return  200 ;
		
	}
	
	public int getDefenderPos(){

		int x = 0;
		int y = 0;
		
		while(y == 0 ){
			
			while(x < 37){
				
				if(Position[0][x]!= 4){
					x++;
				}
				else
					
					return x ;							//Knoten 00-36
			}
		
			y++;
			x=0;
			while (y == 1){
				
				while(x<49){
					if(Position[1][x] != 4){
						x++;
				}
					else 
						return x+100; 					//Kanten 100-148
				}
			}
		}
		
		return  200 ;
		
	}

	
	public int getTracerPos(){


		int x = 0;
		int y = 0;
		
		while(y == 0 ){
			
			while(x < 37){
				
				if(Position[0][x]!= 2){
					x++;
				}
				else
					
					return x ;							//Knoten 00-36
			}
		
			y++;
			x=0;
			while (y == 1){
				
				while(x<49){
					if(Position[1][x] != 2){
						x++;
				}
					else 
						return x+100; 					//Kanten 100-148
				}
			}
		}
		
		return  200 ;
		
	}

	
	public int getRandomPos(){

		int x = 0;
		int y = 0;
		
		while(y == 0 ){
			
			while(x < 37){
				
				if(Position[0][x]!= 3){
					x++;
				}
				else
					
					return x ;							//Knoten 00-36
			}
		
			y++;
			x=0;
			while (y == 1){
				
				while(x<49){
					if(Position[1][x] != 3){
						x++;
				}
					else 
						return x+100; 					//Kanten 100-148
				}
			}
		}
		
		return  200 ;
		
	}

	
	public int getPowerUp1(){
		return 106;
	}
	public int getPowerUp2(){
		return 17;
	}
	public int getPowerUp3(){
		return 20;
	}
	public int getPowerUp4(){
		return 31;
	}
	
	public void setSEPMANPos(int pos){
		
		if(pos < 100){
			Position[0][pos] = 1 ;
		}
		else{
			Position[1][pos] = 1 ;
			
		}
	}
	
	public void setDefenderPos(int pos){
		
		if(pos < 100){
			Position[0][pos] = 4 ;
		}
		else{
			Position[1][pos] = 4 ;
			
		}
	}
	
	public void setTracerPos(int pos){
		
		if(pos < 100){
			Position[0][pos] = 2 ;
		}
		else{
			Position[1][pos] = 2 ;
			
		}
	}
	
	
	public void setRandomPos(int pos){
		
		if(pos < 100){
			Position[0][pos] = 3 ;
		}
		else{
			Position[1][pos] = 3 ;
			
		}
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
