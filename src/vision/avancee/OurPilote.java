/**
 * Classe servant de pilote � notre robot heritant de la classe MovePilote de Lejos et lui ajoutant la possibilité de controller les pinces
 */
package vision.avancee;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;

public class OurPilote extends MovePilot {
	/**
	 * Moteur controlant les pinces
	 */
	protected RegulatedMotor pinces;
	
	public OurPilote(Chassis chassis) {
		super(chassis);
		pinces = new EV3LargeRegulatedMotor(MotorPort.D);
		pinces.setSpeed(1000);
	}
	/**
	 * Methode pour ouvrir les pinces si le boolean
	 * @param b
	 * est TRUE alors elle s'ouvriront tout en executant la suite du code si il est False la suite du code ne s'execute qu'une fois l'ouverture terminé
	 */
	public void ouvrirPinces(boolean b) {
		pinces.rotate(900, b);
	}
	/**
	 * Methode pour fermer les pinces si le boolean
	 * @param b
	 * est TRUE alors elle se fermeront tout en executant la suite du code si il est False la suite du code ne s'execute qu'une fois la fermeture terminé
	 
	 */
	public void fermerPinces(boolean b) {
		pinces.rotate(-900, b);
	}

}
