/**
 * @author ${user}
 *
 * 
 */

package Drucksensorverarbeitung;


import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;


public class Drucksensor implements IDrucksensor{
	SensorModes druckSensor;
	SensorModes druckSensor2;
	
	public Drucksensor(){
		druckSensor= new EV3TouchSensor(SensorPort.S1);
		druckSensor2= new EV3TouchSensor(SensorPort.S2);
	}
		
	public boolean druckSensor(){
		
		SampleProvider touch= druckSensor.getMode("Touch");		//Druck Sensor 1
		float sample[]=new float [touch.sampleSize()];
		touch.fetchSample(sample, 0);
		
		SampleProvider touch2= druckSensor2.getMode("Touch");	//Druck Sensor 2
		float sample2[]= new float [touch2.sampleSize()];
		touch2.fetchSample(sample2, 0);
		
		if (sample[0]==1 || sample2[0]==1) // Wenn druckSensor oder druckSensor2 den Wert 1 (aktiv) zurückgibt.
			return true;
		
		else return false;
	}
}
