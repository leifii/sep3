/**
 * @author ${Sami}
 *
 * 
 */
package Linienverfolger;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class Linienverfolgung implements ILinienverfolgung {
	static RegulatedMotor MotorL;
	static RegulatedMotor MotorR;
	SensorModes lightSensor;
	SampleProvider light;
	

    float[] sample;
    float light_aktuell;
    float weiss_wertmin=0.34F;
    										//Lichtwerte Weiss (außerhalb Linie). AN ORIGINAL-SPIELFELD ANPASSEN (Wert durch Lejos Tools am EV3 ablesen)
    										//0.5 (war 0.25F)  0.55,0.65
    
    public Linienverfolgung(){
    	MotorL= new EV3LargeRegulatedMotor(MotorPort.A);
    	MotorR= new EV3LargeRegulatedMotor(MotorPort.D);
    	lightSensor= new EV3ColorSensor(SensorPort.S3);
    	light= lightSensor.getMode("Red");
    	sample = new float[light.sampleSize()];
    }
  
    
    public void geradeaus(){
		light.fetchSample(sample,0);
		light_aktuell=sample[0];
		
		if(light_aktuell>weiss_wertmin){        //wenn Farbe weiß, dann Schritt nach rechts
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
			Button.LEDPattern(1);
			MotorR.stop();
			MotorL.stop();
			return true;
		}
		else {
			Button.LEDPattern(0);
			return false;
		}
}
	
	public void drehenLinks(){		//Wenn man sich auf den Knoten für Links entscheidet.
		MotorR.resetTachoCount();
		while(MotorR.getTachoCount()<=240){
			MotorL.backward();
			MotorR.forward();
		}
	}
	
	public void drehenRechts(){		//Wenn man sich auf den Knoten für Rechts entscheidet.
		MotorL.resetTachoCount();
		while(MotorL.getTachoCount()<=240){
			MotorR.backward();
			MotorL.forward();
		}
		
		//MotorR.stop();
		//MotorL.setSpeed(400);
		//MotorL.forward();	
		
	}
	
	public void motorPush(){		//Wenn man sich auf den Knoten für Geradeaus entscheidet.
		
		MotorL.setSpeed(200);
	    MotorR.setSpeed(200);
	    MotorL.forward();
	    MotorR.forward();
	    try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void stop(){
		MotorL.stop();
		MotorR.stop();
	}

public static void init_motoren(int speed){
    MotorL.setSpeed(speed);
    MotorR.setSpeed(speed);
}
}