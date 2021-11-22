package sensors;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 *OurSensors contient les getters et setters des sensors
 *de notre robot
 */
public class OurSensors {
	
	/**
	 * Le capteur couleur dont dépend les données recueilli par le robot. Par défaut, il est affecté au capteur S2, celui de notre robot.**/
	private static ColorSensor Color = new ColorSensor(SensorPort.S2);
	
	
	public String getColor(){
		return Color.getColorID();
	}

	/**Ultrasonic Sensor
	 * Initialise les attributs nï¿½cessaires ï¿½ l'utilisation du
	 * senseur ï¿½ ultrasons (branchï¿½ au port S1)
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
	 * @return Rï¿½fï¿½rence du senseur
	 */
	public EV3UltrasonicSensor getSensorU() {
		return Us;
	}



	/**Touch Sensor
	 * Initialise les attributs nï¿½cessaires ï¿½ l'utilisation du
	 * senseur de toucher (branchï¿½ au port S3)
	 */
	private EV3TouchSensor Ts = new EV3TouchSensor(SensorPort.S3);
	public SampleProvider Tsamp = Ts.getTouchMode();
	float [] tSample = new float[Tsamp.sampleSize()];

	/**
	 * Determine si le senseur de toucher est actuellement activÃ©
	 * @return 0 si le senseur n'est pas activÃ©, 1 sinon. 
	 */
	public boolean getTouch() {
		Tsamp.fetchSample(tSample, 0);
		return tSample[0]>0;
	}

}