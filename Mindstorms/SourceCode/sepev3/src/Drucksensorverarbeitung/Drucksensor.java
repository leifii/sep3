package Drucksensorverarbeitung;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Drucksensor implements IDrucksensor{
	
	public boolean druckSensor(){
		
		@SuppressWarnings("resource")
		SensorModes druckSensor= new EV3TouchSensor(SensorPort.S1);
		@SuppressWarnings("resource")
		SensorModes druckSensor2= new EV3TouchSensor(SensorPort.S2);
		SampleProvider touch= druckSensor.getMode("Touch");
		SampleProvider touch2= druckSensor2.getMode("Touch");
		float sample[]=new float [touch.sampleSize()];
		float sample2[]= new float [touch2.sampleSize()];
		touch.fetchSample(sample, 0);
		touch2.fetchSample(sample2, 0);
		
		if (sample[0]==1 || sample2[0]==1) // Wenn druckSensor den Wert 1 (aktiv) zurückgibt.
			return true;
		
		else return false;
	}
}
