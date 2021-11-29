/**
 * Classe servant de pilote à notre robot
 */
package vision.avancee;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;

public class OurPilote extends MovePilot {
	protected RegulatedMotor pinces;
	
	public OurPilote(Chassis chassis) {
		super(chassis);
		pinces = new EV3LargeRegulatedMotor(MotorPort.D);
		pinces.setSpeed(1000);
	}
	
	public void ouvrirPinces(boolean b) {
		pinces.rotate(900, b);
	}
	
	public void fermerPinces(boolean b) {
		pinces.rotate(-900, b);
	}

}
