package Linienverfolger;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;

import lejos.robotics.SampleProvider;

public class Linienverfolgung implements ILinienverfolgung{
	
	public boolean aufLinie(){
		
		@SuppressWarnings("resource")
		SensorModes farbSensor= new EV3ColorSensor(SensorPort.S3);
		SampleProvider farbe= farbSensor.getMode("ColorID");
		float sample[]= new float[farbe.sampleSize()];
		farbe.fetchSample(sample,0);
		
		if (sample[0]==7){ //Wert für die Farbe Schwarz
			return true;
		}
		else return false;
		
		
	}
	public boolean aufKnoten(){
		@SuppressWarnings("resource")
		SensorModes farbSensor= new EV3ColorSensor(SensorPort.S3);
		SampleProvider farbe= farbSensor.getMode("ColorID");
		float sample[]= new float[farbe.sampleSize()];
		farbe.fetchSample(sample,0);
		
		
		if (sample[0]== 9|| sample[0]==10 || sample[0]==11){  // Werte für die Farben Grau, Hellgrau und Dunkelgrau.
			return true;
		}
		else return false;
	}
	public boolean sucheLinie(){
		@SuppressWarnings("resource")
		SensorModes farbSensor= new EV3ColorSensor(SensorPort.S3);
		SampleProvider farbe= farbSensor.getMode("ColorID");
		float sample[]= new float[farbe.sampleSize()];
		farbe.fetchSample(sample,0);
		
		if (sample[0]==6){ //Wert für die Farbe Weiss
			return true;
		}
		else return false;
	}
}
