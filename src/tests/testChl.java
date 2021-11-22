package tests;

import lejos.hardware.Button;
import vision.Jarvis;

public class testChl {

	public static void main(String[] args) {
		
		Jarvis j = new Jarvis();
		//j.getPilote().closeClaw();
		j.getPilote().getClawMotor().rotate(-800);
		//j.checkNearestPalet();
		j.partieSimple();
		int i = (int)(Math.random()*180)+180;
		//j.getPilote().getLeftMotor().resetTachoCount();
		//j.seTourner(i);
		//j.checkNearestPalet();
		//float dist = j.getPilote().getUltraSon().getDist();
		//j.attrapePalet(dist);
		//j.vasMarquer();
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
