package tests;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import utils.Chronos;
import jarvis.yazid.*;


public class testMat {

	public static void main(String[] args) {
		tests();
	}
	
	public static void tests(){
		//Go to 0,10
		//recup pos : 
		System.out.println("Please press a button");
		Button.waitForAnyPress();
		int offset=70;    // ecart entre les roues et le centre
        Wheel rightW = WheeledChassis.modelWheel(Motor.A, 56.0).offset(-offset); // roue de droite, 52.0 est son diametre.
        Wheel leftW = WheeledChassis.modelWheel(Motor.B, 56.0).offset(offset); // roue de gauche 
        WheeledChassis chassis = new WheeledChassis(new Wheel[]{rightW, leftW}, WheeledChassis.TYPE_DIFFERENTIAL); //chassis. Differential car le robot ne se deplace pas dans toutes les directions
        MovePilot pilot = new MovePilot(chassis);
		Navigator nav= new Navigator(pilot);
		nav.goTo(10, 0);
		System.out.println("done 10;0 - position : "+nav.getPoseProvider());
		Button.waitForAnyPress();
	}

}
