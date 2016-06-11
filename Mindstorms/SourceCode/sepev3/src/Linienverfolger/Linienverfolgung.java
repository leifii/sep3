package Linienverfolger;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;

import lejos.robotics.SampleProvider;

public class Linienverfolgung implements ILinienverfolgung {
	
	public boolean aufLinie(boolean linie){
		
		@SuppressWarnings("resource")
		SensorModes farbSensor= new EV3ColorSensor(SensorPort.S3);
		SampleProvider farbe= farbSensor.getMode("ColorID");
		float sample[]= new float[farbe.sampleSize()];
		farbe.fetchSample(sample,0);
		
		if (sample[0]==7){ // Wert für die Farbe Schwarz
			linie=true;
		}
		else linie=false;
		
		return linie;
	}
	
	public boolean aufKnoten(boolean knoten){
		
		@SuppressWarnings("resource")
		
		SensorModes farbSensor= new EV3ColorSensor(SensorPort.S3);
		SampleProvider farbe= farbSensor.getMode("ColorID");
		float sample[]= new float[farbe.sampleSize()];
		farbe.fetchSample(sample,0);
		
		if (sample[0]== 9|| sample[0]==10 || sample[0]==11){  // Werte für die Farben Grau, Hellgrau und Dunkelgrau.
			knoten=true;
		}
		else knoten=false;
		
		return knoten;
	}
	
	public boolean sucheLinie(boolean suchelinie){
		
		@SuppressWarnings("resource")
		
		SensorModes farbSensor= new EV3ColorSensor(SensorPort.S3);
		SampleProvider farbe= farbSensor.getMode("ColorID");
		float sample[]= new float[farbe.sampleSize()];
		farbe.fetchSample(sample,0);
		
		if (sample[0]==6){ // Wert für die Farbe Weiss
			suchelinie=true;
		}
		else suchelinie=false;
		
		return suchelinie;
	}
}
