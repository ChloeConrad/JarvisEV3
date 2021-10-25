package tests;

import lejos.hardware.Button;
import vision.Jarvis;

public class testChl {

	public static void main(String[] args) {
		
		Jarvis j = new Jarvis();
		//j.getPilote().closeClaw();
		//j.getPilote().seTourner(360, j);
		j.partieSimple();
		//j.getPilote().closeClaw();
		System.out.println(j.getPilote().getUltraSon().getDist());
		//j.getPilote().openClaw(false);
		Button.waitForAnyPress();

	}

}
