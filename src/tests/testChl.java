package tests;

import lejos.hardware.Button;
import vision.Jarvis;

public class testChl {

	public static void main(String[] args) {
		Jarvis j = new Jarvis();
		j.getPilote().getClawMotor().rotate(-500);
		j.premierBut();

	}

}
