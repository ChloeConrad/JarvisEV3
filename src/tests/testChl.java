package tests;

import lejos.hardware.Button;
import vision.Jarvis;

public class testChl {

	public static void main(String[] args) {
		
		Jarvis j = new Jarvis();
		//j.getPilote().closeClaw();
		//j.checkNearestPalet();
		//j.partieSimple();
		j.getPilote().getClawMotor().rotate(-1500);
		
		
	}

}
