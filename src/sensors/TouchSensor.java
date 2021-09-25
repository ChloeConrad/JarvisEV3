package sensors;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
/**
 * Permet de gerer les actions d'un EV3TouchSensor
 * @author mat
 * @version 0.1
 *
 */
public class TouchSensor {
	private EV3TouchSensor Ts;
    public SampleProvider Tsamp;
    float [] sample;
    /**
     * Initialise l'EV3TouchSensor.
     * @param port SensorPort dans lequel est branché le senseur de toucher(S1-4).
     */
    public TouchSensor(Port port) {
		Ts = new EV3TouchSensor(port);
		Tsamp = Ts.getTouchMode();
	    sample = new float[Tsamp.sampleSize()];
	}
    /**
     * Determine si le senseur de toucher est actuellement activé
     * @return 0 si le senseur n'est pas activé, 1 sinon. 
     */
    public float getTouch() {
       		Tsamp.fetchSample(sample, 0);
       		return sample[0];
	}

}
