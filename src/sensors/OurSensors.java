package sensors;

import java.awt.Color;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class OurSensors {

	/**Ultrasonic Sensor
	 * Initialise les attributs nécessaires à l'utilisation du
	 * senseur à ultrasons (branché au port S1)
	 */
	private static EV3UltrasonicSensor Us = new EV3UltrasonicSensor(SensorPort.S1);
	private SampleProvider USamp = Us.getDistanceMode();
	float [] uSample = new float[USamp.sampleSize()];

	/**
	 * Renvoie une valeur flottante de la distance la plus proche detectÃ© par le robot.
	 * @return Renvoie la distance la plus proche detectÃ© par le senseur en float
	 */
	public float getDist() {
		USamp.fetchSample(uSample, 0);
		return uSample[0];
	}

	/**
	 * 
	 * @return Référence du senseur
	 */
	public EV3UltrasonicSensor getSensorU() {
		return Us;
	}


	/**Color Sensor
	 * Initialise les attributs nécessaires à l'utilisation du
	 * senseur de couleur (branché au port S2)
	 */
	private static EV3ColorSensor Color = new EV3ColorSensor(SensorPort.S2);
	float[]	cSample;

	/**
	 * Renvoie trois int correspondant au code RGB de la couleur détectée
	 * @return Renvoie le code RGB de la couleur détectée
	 */
	public Color getColor() {
		Color.fetchSample(cSample, 0);
		return new Color((int)(cSample[0] * 255), (int)(cSample[1] * 255), (int)(cSample[2] * 255));
	}



	/**Touch Sensor
	 * Initialise les attributs nécessaires à l'utilisation du
	 * senseur de toucher (branché au port S3)
	 */
	private EV3TouchSensor Ts = new EV3TouchSensor(SensorPort.S3);
	public SampleProvider Tsamp = Ts.getTouchMode();
	float [] tSample = new float[Tsamp.sampleSize()];

	/**
	 * Determine si le senseur de toucher est actuellement activÃ©
	 * @return 0 si le senseur n'est pas activÃ©, 1 sinon. 
	 */
	public float getTouch() {
		Tsamp.fetchSample(tSample, 0);
		return tSample[0];
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}




}