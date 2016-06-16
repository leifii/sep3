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
    float weiss_wert=0.34F;					//Lichtwerte Weiss (außerhalb Linie). AN SPIELFELD ANPASSEN.
    
	public void geradeaus(){
		light.fetchSample(sample,0);
    	
		light_aktuell=sample[0];
		
		if(light_aktuell>weiss_wert){        //wenn Farbe weiß, dann Schritt nach rechts
            LCD.drawString("Suche Knoten L", 1, 3);
            MotorR.stop();
            MotorL.forward();
            }
            
         else {                        //wenn nicht weiß oder Knoten, dann Schritt nach links
            	LCD.drawString("Suche Knoten R", 1, 3);
            	MotorR.forward();
            	MotorL.stop();
            }
	}
	
	public boolean aufKnoten(){
		if (light_aktuell>0.13 && light_aktuell<0.21){ // Lichtwerte eines Knotens. AN SPIELFELD ANPASSEN.
			LCD.drawString("<- or ->", 1, 3);
			
			MotorR.stop();
			MotorL.stop();
			
			return true;
	}
		else return false;
		
}}
