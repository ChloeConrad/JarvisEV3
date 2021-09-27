package sensors;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * Permet de gerer les actions d'un EV3UltrasonicSensor.
 * @author mat
 * @version 0.1
 */
public class UltraSonicSensor {
	
    private static EV3UltrasonicSensor USs;
    private SampleProvider USsamp;
    float [] sample;
    /**
     * Initialise le senseur à ultrason
     * @param port SensorPort dans lequel le senseur à ultrason est branché (S1 à S4)
     */
    public UltraSonicSensor(Port port) {
		USs = new EV3UltrasonicSensor(port);
		USsamp = USs.getDistanceMode();
	    sample = new float[USsamp.sampleSize()];
	}
    /**
     * Renvoie une valeur flottante de la distance la plus proche detecté par le robot.
     * @return Renvoie la distance la plus proche detecté par le senseur en float
     */
    public float getDist() {
       		USsamp.fetchSample(sample, 0);
       		return sample[0];
	}

}
