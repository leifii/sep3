/**
 * @author ${user}
 *
 * 
 */
package Linienverfolger;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public interface ILinienverfolgung {
	  RegulatedMotor MotorL= new EV3LargeRegulatedMotor(MotorPort.A);
	    RegulatedMotor MotorR= new EV3LargeRegulatedMotor(MotorPort.D);
	
	    public boolean aufKnoten();		//True oder False muss an dem PC durch "IKommunikation" übertragen werden.
}
