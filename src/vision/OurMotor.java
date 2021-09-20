package vision;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import sensors.UltraSonicSensor;

public class OurMotor {
	static int speed = 100;
	static int maxClaw = 800;
	static RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
    static RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    static RegulatedMotor clawMotor = new EV3LargeRegulatedMotor(MotorPort.D);
	
    public OurMotor() {
    	initMotor();
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
		forward(s, false);
	}

	public static void forward(int s, boolean b) {
		leftMotor.rotate(s, true);
		rightMotor.rotate(s, b);
	}
	
	//recule
	public static void backward(int s) {
		backward(s, false);
	}

	public static void backward(int s, boolean b) {
		leftMotor.rotate(-s, true);
		rightMotor.rotate(-s, b);
	}
	
	//*** Pour speed=1000, s=785 fait un tour complet (environ)
	//rotation avec les deux roues dans le sens inverse des aiguilles d'une montre
	public void counterClockRotate(int s) {
		leftMotor.rotate(-s, true);
		rightMotor.rotate(s);
	}
	
	//rotation avec les deux roues dans le sens des aiguilles d'une montre
	public void ClockRotate(int s) {
		leftMotor.rotate(s, true);
		rightMotor.rotate(-s);
	}
	
	public void monoWheel180(String s,boolean b) {
		int radius = 778;
		switch(s) {
		case "left" :
			leftMotor.rotate(radius,b);
			break;
		case "right" :
			rightMotor.rotate(radius,b);
			break;
		default :
			break;
		}
	}
	
	public void monoWheel360(String s,boolean b) {
		int radius = 1556;
		switch(s) {
		case "left" :
			leftMotor.rotate(radius,b);
			break;
		case "right" :
			rightMotor.rotate(radius,b);
			break;
		default :
			break;
		}
	}

	public void that360() {
		leftMotor.rotate(778,true);
		rightMotor.rotate(-778,true);
	}

	public void closeClaw() {
    	clawMotor.rotate(-1800);
    }
    public void openClaw() {
    	clawMotor.rotate(1800,true); //lorsqu'on ouvre la pince, pas besoin de rester immobile
    }
	
	public void surrondings(UltraSonicSensor US) {
		int[] values = new int[180];
		int i = 0;
		this.that360();
		while(leftMotor.isMoving()) {
			values[i] = (int)(US.getDist()*1000);
			i++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int min = 0;
		for(int j = 0; j < i; j++) {
			if(values[j] < values[min]) {
				min = j;
			}
		}
		System.out.println("i : "+i+ "; min = "+min+"; value(min) = "+(((float)values[min])/1000));
		float deg = (float) (360.0 / (float)i);
		float degMoved = min*deg;
		int degToMove = (int) (360 - degMoved);
		int valueToMove = 778*degToMove/360;
		if(valueToMove > 180) {
			this.ClockRotate(valueToMove);
		} else {
			this.counterClockRotate(valueToMove);
		}
		
	}
}