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
	/**
	 *OurSensors contient les getters et setters des sensors
	 *de notre robot
	 */
	

	/**Ultrasonic Sensor
	 * Initialise les attributs n�cessaires � l'utilisation du
	 * senseur � ultrasons (branch� au port S1)
	 */
	private static EV3UltrasonicSensor Us = new EV3UltrasonicSensor(SensorPort.S1);
	private SampleProvider USamp = Us.getDistanceMode();
	float [] uSample = new float[USamp.sampleSize()];
	
	

	/**
	 * Renvoie une valeur flottante de la distance la plus proche detecté par le robot.
	 * @return Renvoie la distance la plus proche detecté par le senseur en float
	 */
	public float getDist() {
		USamp.fetchSample(uSample, 0);
		return uSample[0];
	}
	
	
	

	/**
	 * 
	 * @return R�f�rence du senseur
	 */
	public EV3UltrasonicSensor getSensorU() {
		return Us;
	}


	/**Color Sensor
	 * Initialise les attributs n�cessaires � l'utilisation du
	 * senseur de couleur (branch� au port S2)
	 */
	private static EV3ColorSensor Color = new EV3ColorSensor(SensorPort.S2);
	float[]	cSample;

	/**
	 * Renvoie trois int correspondant au code RGB de la couleur d�tect�e
	 * @return Renvoie le code RGB de la couleur d�tect�e
	 */
	public Color getColor() {
		Color.fetchSample(cSample, 0);
		return new Color((int)(cSample[0] * 255), (int)(cSample[1] * 255), (int)(cSample[2] * 255));
	}



	/**Touch Sensor
	 * Initialise les attributs n�cessaires � l'utilisation du
	 * senseur de toucher (branch� au port S3)
	 */
	private EV3TouchSensor Ts = new EV3TouchSensor(SensorPort.S3);
	public SampleProvider Tsamp = Ts.getTouchMode();
	float [] tSample = new float[Tsamp.sampleSize()];

	/**
	 * Determine si le senseur de toucher est actuellement activé
	 * @return 0 si le senseur n'est pas activé, 1 sinon. 
	 */
	public float getTouch() {
		Tsamp.fetchSample(tSample, 0);
		return tSample[0];
	}


	




}