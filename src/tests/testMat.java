package tests;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import vision.*;


public class testMat {

	public static void main(String[] args) {
		test11oct();
	}
	
	public static void test11oct(){
		//Aujourd'hui, test des mouvements circulaires.
		OurMotor m = new OurMotor();
		m.stopMessage("*krrr* Ready to Rock'N'Roll, Baby !");
		//m.curveTry1(500, 0.8, 3000, false);
		/* il part de 13,5cm mais ce n'est pas déduit
		 * 1.2 = sps 66 = 90
		 * 0.9 = 123cm = 109.5 de rayon
		 * 0.8 = 66cm = 52.5 de rayon
		 * 0.75
		 * 0.7 = 47cm = 33.5 de rauyon
		 * 0.65 = 41 = 27.5 de rayon
		 */
		Pilote p = new Pilote(new WheeledChassis( new Wheel[] {WheeledChassis.modelWheel(Motor.A, 56.0).offset(70), WheeledChassis.modelWheel(Motor.B, 56.0).offset(-70)}, WheeledChassis.TYPE_DIFFERENTIAL));
		p.arcForward(100);
		
	}

}
