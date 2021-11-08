package tests;

import lejos.hardware.Button;
import vision.Jarvis;

public class testChl {

	public static void main(String[] args) {
		
		Jarvis j = new Jarvis();
<<<<<<< HEAD
		//j.getPilote().closeClaw();
		//j.getPilote().seTourner(360, j);
		j.partieSimple();
		//j.getPilote().closeClaw();
		System.out.println(j.getPilote().getUltraSon().getDist());
		//j.getPilote().openClaw(false);
=======
<<<<<<< Updated upstream
		//j.setPositions();
		//j.premierBut();
		j.checkNearestPalet(-40);
>>>>>>> 1b836026e25573494a7132964de624f964f6908c
		Button.waitForAnyPress();
=======
		//j.getPilote().getClawMotor().rotate(100);
		j.setPositions();
		j.premierBut();
>>>>>>> Stashed changes

	}

}
