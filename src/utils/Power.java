package utils;
import lejos.hardware.ev3.LocalEV3;
public class Power {
	public float getVolts() {
		float volts = LocalEV3.ev3.getPower().getVoltage();
		return volts;
	}
}