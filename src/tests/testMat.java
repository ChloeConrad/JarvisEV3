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
		//Pour l'instant 1500 semble bien, mais je ne sais pas si l'acceleration est 150 ou
		//si on est sur la vitesse max, avec une acceleration croissante
		OurMotor m = new OurMotor();
		m.stopMessage("*krrr* Ready to Rock'N'Roll, Baby !");
		for(int i=1;i<9;i++) {
			//7 : 3.3 -> 5.8 (2.5)
			//8 ; 9.8 -> 12.1 (2.3)
			//9 : 15.7 --> 17.9 (2.2)
			//10 : 21.1 -> 23.4 (2.3)
			//maxed at 800 ?
			//vitesse semble cappé à 700
			m.setAcceleration(i*100);
			m.forward(0.6);
			m.stopMessage("Test "+i+" done.");
		}
		//m.curveTry1(500, 0.8, 3000, false);
		/* il part de 13,5cm mais ce n'est pas déduit
		 * 1.2 = sps 66 = 90
		 * 0.9 = 123cm = 109.5 de rayon
		 * 0.8 = 66cm = 52.5 de rayon
		 * 0.75
		 * 0.7 = 47cm = 33.5 de rayon
		 * 0.65 = 41 = 27.5 de rayon
		 */
		//Pilote p = new Pilote(new WheeledChassis( new Wheel[] {WheeledChassis.modelWheel(Motor.A, 56.0).offset(70), WheeledChassis.modelWheel(Motor.B, 56.0).offset(-70)}, WheeledChassis.TYPE_DIFFERENTIAL));
		//p.closeClaw();
		//p.getClawMotor().rotate(-500);
		//p.arc(1500, 90);
		//p.setSpeed(1000);
		//p.setAcceleration(100);
		//Pilote.forward(0.6);
	}

}
