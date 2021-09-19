package vision;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class TouchSensor {
	
	private EV3TouchSensor Ts;
    public SampleProvider Tsamp;
    float [] sample;
    
    public TouchSensor(Port port) {
		Ts = new EV3TouchSensor(port);
		Tsamp = Ts.getTouchMode();
	    sample = new float[Tsamp.sampleSize()];
	}
    
    public float getDist() {
       		Tsamp.fetchSample(sample, 0);
       		return sample[0];
	}

}
