package tests;

import lejos.hardware.Button;
import vision.Jarvis;

public class testChl {

	public static void main(String[] args) {
		
		Jarvis j = new Jarvis();
		//j.getPilote().closeClaw();
		//j.getPilote().getClawMotor().rotate(-500);
		j.checkNearestPalet();
		//j.partieSimple();
		//j.seTourner(20);
		//j.checkNearestPalet();
		//j.attrapePalet(j.getPilote().getUltraSon().getDist());
		//Button.waitForAnyPress();
		//j.vasMarquer();
		//j.getPilote().that360(true);
		//j.setPositions();
		//j.premierBut();
		//j.getPilote().seTourner(90, true);
		//System.out.println(j.getPilote().getUltraSon().getDist());
		Button.waitForAnyPress();
		
	}

}
