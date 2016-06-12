package Steuerbefehle ;

import Linienverfolger.ILinienverfolgung;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Steuerbefehl implements ILinienverfolgung, ISteuerbefehl{

	public void fahreVorwaerts(){
		RegulatedMotor MotorL= new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor MotorR= new EV3LargeRegulatedMotor(MotorPort.D);
		
		while (aufLinie(true) || aufKnoten(true)){ // Während der Roboter auf der Linie oder ein Knoten ist soll er Vorwärts fahren.
			MotorL.setSpeed(500);
			MotorR.setSpeed(500);
			MotorL.forward();
			MotorR.forward();
		
			if (aufKnoten(true) || aufLinie(false)) //Sobald er den nächsten Knoten erreicht hat oder nicht mehr auf Linie ist, wird nicht mehr vorwärtsgefahren (stop).
				MotorL.stop();
				MotorR.stop();
				break;
		}
		
		MotorL.close();
		MotorR.close();
	}

	public void MotorSuchtLinie(){
		RegulatedMotor MotorL= new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor MotorR= new EV3LargeRegulatedMotor(MotorPort.D);
		
		while (sucheLinie(true)){ 	//Während der Roboter nicht auf der Linie ist, soll er zurückfahren.
			MotorL.setSpeed(200);
			MotorR.setSpeed(200);
			MotorL.backward();
			MotorR.backward();
			
			if (sucheLinie(false))		//Wenn er die Linie findet, wird nicht mehr zurückgefahren (stop).
				break;
		}
		
		drehenLinks();//Anschließend wird zu 90 Grad nach links gedreht
						//bis die Linie zur Seite gefunden wird (die noch nicht gefahren wurde).Methode drehenLinks().
			
		
		fahreVorwärts(); //Anschließend wird der Befehl (Vorwärts bis zum nächsten Knoten) weitergeführt.
		MotorL.close();
		MotorR.close();
	}
	
	public void drehenLinks(){
		RegulatedMotor MotorL= new EV3LargeRegulatedMotor(MotorPort.A);
		MotorL.setSpeed(400);
		MotorL.rotateTo(90);
			
		if (aufLinie(false)){
			MotorL.rotateTo(180);;	
			}
		MotorL.close();
	}
		
	public void drehenRechts(){
		
		RegulatedMotor MotorR= new EV3LargeRegulatedMotor(MotorPort.D);
		MotorR.setSpeed(400);
		MotorR.rotateTo(90);
		
		if (aufLinie(false)){
			MotorR.rotateTo(180);	
			}
		MotorR.close();
	}
	
	@Override
	public boolean aufKnoten(boolean knoten) {
			return knoten;
	}
	
	public boolean aufLinie(boolean linie) {
			return linie;
	}

	@Override
	public boolean sucheLinie(boolean sucheLinie) {
		return sucheLinie;
	}
	public static void main (String [] args){
		//Fragen während Präsenzstunde bzgl. Implementierung...
		//Muss Methoden ausführen, sind jedoch nicht static. Lösungsvorschlag?
	}
}
