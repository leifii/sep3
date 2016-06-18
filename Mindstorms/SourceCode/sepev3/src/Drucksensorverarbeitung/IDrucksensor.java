package Drucksensorverarbeitung;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;

public interface IDrucksensor {
	SensorModes druckSensor= new EV3TouchSensor(SensorPort.S1);
	SensorModes druckSensor2= new EV3TouchSensor(SensorPort.S2);
	
	public boolean druckSensor();
}
