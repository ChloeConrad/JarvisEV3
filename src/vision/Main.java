package vision;

import lejos.hardware.Button;
import lejos.hardware.Power;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import sensors.*;

public class Main {

	public static void main(String[] args) {
		// r = new Robot();
		//r.stopMessage("Dist : " + r.getDist());
		//simpleGetPallet();
		//OurMotor m = new OurMotor();
		//System.out.println("dist infinite = "+US.getDist()+"\n max float value = "+Float.MAX_VALUE);
		Jarvis j = new Jarvis();
<<<<<<< HEAD
	
		//j.getPilote().closeClaw();
		//j.getPilote().curveTry1(500, 0.5, 1000, false);
		//j.setPositions();
		//Button.waitForAnyPress();
		//j.premierBut();
=======
		j.getPilote().stopMessage("Battery Level : "+LocalEV3.ev3.getPower().getVoltage());
		j.getPilote().curveTry1(500, 0.5, 1000, false);
		//At Volt = 6.8, it starting doing random actions.
		/*j.setPositions();
		Button.waitForAnyPress();
		j.premierBut();
		*/
		Button.waitForAnyPress();
>>>>>>> c2e814974bd6136ea693cc9a21bfcea9ca974913
		
		//Button.waitForAnyPress();
		j.getPilote().closeClaw();
		j.getPilote().getClawMotor().rotate(300);
	}
	
	
}
