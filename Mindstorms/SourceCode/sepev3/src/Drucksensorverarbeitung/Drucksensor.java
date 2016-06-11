package Drucksensorverarbeitung;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Drucksensor implements IDrucksensor{
	
	public boolean druckSensor(boolean druck){
		@SuppressWarnings("resource")
		
		SensorModes druckSensor= new EV3TouchSensor(SensorPort.S1);
		SampleProvider touch= druckSensor.getMode("Touch");
		float sample[]=new float [touch.sampleSize()];
		touch.fetchSample(sample, 0);
		
		if (sample[0]==1) // Wenn druckSensor den Wert 1 (aktiv) zurückgibt.
			druck=true;
		
		else druck=false;
		
		return druck;
	}
	
}
