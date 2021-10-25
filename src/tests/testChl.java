package tests;

import lejos.hardware.Button;
import vision.Jarvis;

public class testChl {

	public static void main(String[] args) {
		
		Jarvis j = new Jarvis();
<<<<<<< Updated upstream
		//j.setPositions();
		//j.premierBut();
		j.checkNearestPalet(-40);
		Button.waitForAnyPress();
=======
		//j.getPilote().getClawMotor().rotate(100);
		j.setPositions();
		j.premierBut();
>>>>>>> Stashed changes

	}

}
