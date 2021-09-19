package vision;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;


public class OurMotor {
	static int speed = 1000;
	static int maxClaw = 800;
	static RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
    static RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    static RegulatedMotor clawMotor = new EV3LargeRegulatedMotor(MotorPort.D);
	
    public static void main(String[] args) {
    	//init
    	stopMessage("Everything is \nReady !\n Press Anywhere..");
    	initMotor();
    	
    	//main commands
    	forward(1000);
    	ClockRotate(780);
    	backward(1000);
    	
    	//stop
    	stopMessage("Done. \n Press Anywhere...");
	}
	
    //initialise le moteur
    public static void initMotor(RegulatedMotor m) {
    	m.setSpeed(speed);
    }
    public static void initMotor() {
    	initMotor(leftMotor);
    	initMotor(rightMotor);
    	initMotor(clawMotor);
    }
    
    //output message and waits for user input
    public static void stopMessage(String str) {
    	System.out.println(str);
    	Button.waitForAnyPress();
    }
    
    //avance
	public static void forward(int s) {
		leftMotor.rotate(s, true);
		rightMotor.rotate(s);
	}
	
	//recule
	public static void backward(int s) {
		leftMotor.rotate(-s, true);
		rightMotor.rotate(-s);
	}
	
	//*** Pour speed=1000, s=785 fait un tour complet (environ)
	//rotation avec les deux roues dans le sens inverse des aiguilles d'une montre
	public static void counterClockRotate(int s) {
		leftMotor.rotate(-s, true);
		rightMotor.rotate(s);
	}
	
	//rotation avec les deux roues dans le sens des aiguilles d'une montre
	public static void ClockRotate(int s) {
		leftMotor.rotate(s, true);
		rightMotor.rotate(-s);
	}
	
	//ouvre les pinces
	public static void openClaw(int c) {
		clawMotor.rotate(c);
	}
	
	//ferme les pinces
	public static void closeClaw(int c) {
		clawMotor.rotate(-c);
	}
}