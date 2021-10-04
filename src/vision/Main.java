package vision;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import sensors.*;
import lejos.*;

public class Main {

	public static void main(String[] args) {
		// r = new Robot();
		//r.stopMessage("Dist : " + r.getDist());
		//simpleGetPallet();
		//OurMotor m = new OurMotor();
		//System.out.println("dist infinite = "+US.getDist()+"\n max float value = "+Float.MAX_VALUE);
		Jarvis j = new Jarvis();
	
		//j.getPilote().closeClaw();
		//j.getPilote().curveTry1(500, 0.5, 1000, false);
		//j.setPositions();
		//Button.waitForAnyPress();
		//j.premierBut();
		
		//Button.waitForAnyPress();
		j.getPilote().closeClaw();
		j.getPilote().getClawMotor().rotate(300);
	}
	
	
}
