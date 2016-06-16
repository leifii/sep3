package Steuerbefehle ;

import Linienverfolger.ILinienverfolgung;
import Linienverfolger.Linienverfolgung;
import lejos.hardware.Button;

	
public class Steuerbefehl implements ISteuerbefehl{ //ISteuerbefehl nicht nötig. Steuerbefehl bekommt, gibt nicht
	private static Linienverfolgung lvfg;
	
	static int i=0;
	
	public Steuerbefehl(){
		super();
		lvfg=new Linienverfolgung();
	}
	
	public static void main (String [] args) throws InterruptedException{
		Steuerbefehl stb = new Steuerbefehl();
		
		while (i<1){
			init_motoren(150);
			
				stb.fahreGeradeaus();		
			
			if (lvfg.aufKnoten()==true){
				
				if (Button.RIGHT.isDown()){					//in "KommunikationEV3" empfangen, jeweils die Anweisungswerte übernehmen. 
					ILinienverfolgung.MotorR.rotateTo(45);
					
				}
				if (Button.LEFT.isDown()){
					ILinienverfolgung.MotorL.rotateTo(45);
					
				}
				if (Button.DOWN.isDown()){
					ILinienverfolgung.MotorR.rotateTo(180);
				}
				if(Button.ESCAPE.isDown()){ 
					i=1;
				}
			}
			if(Button.ESCAPE.isDown()){ 
					i=1;
			}
	}
	}
	
		
	public static void init_motoren(int speed){
        ILinienverfolgung.MotorL.setSpeed(speed);
        ILinienverfolgung.MotorR.setSpeed(speed);
	}
	
	public void fahreGeradeaus(){
		lvfg.geradeaus();	// Während der Roboter auf der Linie ist soll er Vorwärts fahren (Methode aus Klasse "Linienverfolung")
	}
	
	public void drehenLinks(){	//Drehen nur auf Knoten möglich. Befehl zur Ausfuhrung in Main Methode, später Klasse "Anweisung".
		ILinienverfolgung.MotorR.forward();
		
	}
		
	public void drehenRechts(){
		ILinienverfolgung.MotorL.forward();
	}
	
	public void rotieren(){
			ILinienverfolgung.MotorL.backward();
			ILinienverfolgung.MotorR.forward();
	}
	
	public boolean aufKnoten(){
		if (lvfg.aufKnoten()==true)
			return true;
		
		else return false;}
}

