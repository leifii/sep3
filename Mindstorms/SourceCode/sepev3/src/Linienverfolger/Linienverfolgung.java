/**
 * @author ${user}
 *
 * 
 */
package Linienverfolger;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Linienverfolgung implements ILinienverfolgung {
	SensorModes lightSensor= new EV3ColorSensor(SensorPort.S3);
	SampleProvider light= lightSensor.getMode("Red");

    float[] sample = new float[light.sampleSize()];
    float light_aktuell;
    float weiss_wert=0.34F;					//Lichtwerte Weiss (außerhalb Linie). AN ORIGINAL-SPIELFELD ANPASSEN (Wert durch Lejos Tools am EV3 ablesen)
    										//0.5
  
    
    public void geradeaus(){
		light.fetchSample(sample,0);
		light_aktuell=sample[0];
		
		if(light_aktuell>weiss_wert){        //wenn Farbe weiß, dann Schritt nach rechts
            LCD.drawString("Suche Knoten L", 1, 3);
           MotorR.stop();
           MotorL.setSpeed(100);
           MotorL.forward();
            }
		
		else if (aufKnoten()==true){
			MotorR.stop();
			MotorL.stop();
		}
         else {                        //wenn nicht weiß oder Knoten, dann Schritt nach links
            	LCD.drawString("Suche Knoten R", 1, 3);
            	MotorR.setSpeed(100);
            	MotorR.forward();
            	MotorL.stop();
            }
	}
	
	public boolean aufKnoten(){
		if (light_aktuell>0.13 && light_aktuell<0.21){ // Lichtwerte eines Knotens. AN ORIGINAL-SPIELFELD ANPASSEN (Wert durch Lejos Tools am EV3 ablesen)
			LCD.drawString("<- or ->", 1, 3);			//Wert könnte funktionieren (nachprüfen)
			MotorR.stop();
			MotorL.stop();
			return true;
		}
		else return false;
}
	
	public void drehenLinks(){
		MotorR.resetTachoCount();
		while(MotorR.getTachoCount()<=240){
			MotorL.backward();
			MotorR.forward();
		}
	}
	
	public void drehenRechts(){		
		MotorL.resetTachoCount();
		while(MotorL.getTachoCount()<=240){
			MotorR.backward();
			MotorL.forward();
		}
		
		//MotorR.stop();
		//MotorL.setSpeed(400);
		//MotorL.forward();	
		
	}
	
	public void stop(){
		MotorL.stop();
		MotorR.stop();
	}
	
	public void geschwindigkeit(int speed){
	       MotorL.setSpeed(speed);
	       MotorR.setSpeed(speed);
	}
}
