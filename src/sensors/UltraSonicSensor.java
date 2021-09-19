package sensors;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltraSonicSensor {
	
    private static EV3UltrasonicSensor USs;
    private SampleProvider USsamp;
    float [] sample;
    
    public UltraSonicSensor(Port port) {
		USs = new EV3UltrasonicSensor(port);
		USsamp = USs.getDistanceMode();
	    sample = new float[USsamp.sampleSize()];
	}
    
    public float getDist() {
       		USsamp.fetchSample(sample, 0);
       		return sample[0];
	}

}
