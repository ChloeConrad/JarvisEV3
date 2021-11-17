package utils;
import lejos.hardware.ev3.LocalEV3;

/**
 * Gestion de la batterie pour determiner si le niveau est trop bas pour continuer. A niveau trop faible, Jarvis fait n'importe quoi.
 * @author JarvisTeam
 *
 */
public class Power {
	/**
	 * Renvoie le voltage de la batterie de la boite EV3.
	 * @return un flottant du voltage.
	 */
	public static float getVolts() {
		return LocalEV3.ev3.getPower().getVoltage();
	}
}
