package ia2;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import maths.Point;
import lejos.hardware.port.*;
import lejos.hardware.Button;
import lejos.hardware.ev3.*;


public class Helloworld {
	
	public static void main (String[] args) {
	Point p1 = new Point(0,0);
	Point p2 = new Point(0,4.2);
	p1.distance(p2);
		
		
		/*Port port = LocalEV3.get().getPort("S2");
	EV3ColorSensor s = new EV3ColorSensor(port);	
	
	SensorMode a = s.getRGBMode();
	float[] sample = new float[a.sampleSize()];
	a.fetchSample(sample, 0);
	float x = sample[0];
	System.out.println(x);
	
	Button.waitForAnyPress();*/

	}
}
