package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
/**
 * Permet de gerer les actions d'un EV3 color sensor
 * @author yasiz
 *
 */
public class ColorSensor {
	private EV3ColorSensor color;
    public SampleProvider Tsamp;
    float [] sample;


	public ColorSensor(Port s2) {
		color = new EV3ColorSensor(s2);
		Tsamp = color.getColorIDMode();
	    sample = new float[Tsamp.sampleSize()];
	}
}
