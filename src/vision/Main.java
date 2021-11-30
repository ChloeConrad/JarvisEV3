package vision;
import lejos.hardware.Button;
import sensors.ColorSensor;
import vision.Jarvis;
import vision.PerceptionThread;

public class Main {

	public static void main(String[] args) {
		Jarvis j = new Jarvis();
		PerceptionThread p=new PerceptionThread(j);
		p.start();
		j.partieSimple();
		Button.waitForAnyPress();
		
	}

}

