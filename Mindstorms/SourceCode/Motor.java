package PTest;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Motor {

	public static void main(String[] args) {
		LCD.drawString("Es funktioniert!!", 0, 4);
		RegulatedMotor motor_links = new EV3MediumRegulatedMotor(MotorPort.A);
		motor_links.setSpeed(360);
		motor_links.forward();
	}

}
