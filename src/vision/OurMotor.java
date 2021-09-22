package vision;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import sensors.UltraSonicSensor;

public class OurMotor {
	static int speed = 100;
	static int maxClaw = 800;
	static int value360 = 780;
	static RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
    static RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    static RegulatedMotor clawMotor = new EV3LargeRegulatedMotor(MotorPort.D);
    UltraSonicSensor US = new UltraSonicSensor(SensorPort.S1);
    
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
		this.counterClockRotate(s, false);
	}
	public void counterClockRotate(int s, boolean b) {
		leftMotor.rotate(-s, true);
		rightMotor.rotate(s,b);
	}
	
	//rotation avec les deux roues dans le sens des aiguilles d'une montre
	public void ClockRotate(int s) {
		this.ClockRotate(s,false);
	}
	
	public void ClockRotate(int s, boolean b) {
		leftMotor.rotate(s, true);
		rightMotor.rotate(-s, b);
	}
	
	public void monoWheel180(String s,boolean b) {
		int radius = value360;
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
		leftMotor.rotate(value360,true);
		rightMotor.rotate(-value360,true);
	}

	public void closeClaw() {
    	clawMotor.rotate(-1800);
    }
    public void openClaw() {
    	clawMotor.rotate(1800,true); //lorsqu'on ouvre la pince, pas besoin de rester immobile
    }
	
    /* Probleme a resoudre : l'imprecision fait que la valeure minimal est a plusieurs endroits.
     * Solution proposé : une fois que j'ai un min, je balaie la zone sur x degres pour estimer
     * le debut et la fin du minimum et faire une moyenne de l'endroit ou aller.  */
	public void surrondings() {
		float[] values = new float[10000];
		for(int k = 0; k<10000;k++)
			values [k] = 9999999;
		int i = 0;
		this.that360();
		System.out.println("Doing the 360");
		while(leftMotor.isMoving()) {
			values[i] = US.getDist();
			i++;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Searching for min..");
		int min = 0;
		for(int j = 0; j < i; j++) {
			if(values[j] < values[min]) {
				min = j;
			}
		}
		System.out.println("Calculating the angle..");
		System.out.println("i : "+i+ "; min = "+min+"; value(min) = "+(values[min]));
		
		/* Search the scope of the values */
		int valueCounterClock = min;
		int valueClock = min;
		while(Math.abs(values[valueCounterClock]-values[min]) < 0.05) {
			valueCounterClock--;
		}
		while(Math.abs(values[valueClock]-values[min]) < 0.04) {
			valueClock++;
		}
		int meanMin = (valueCounterClock+valueClock)/2;
		
		float deg = (float) (360.0 / (float)i);
		float degMoved = meanMin*deg; //deg moved until saw that near thing
		int degToMove = (int) (360 - degMoved); //deg to move to reach it
		int valueToMove = value360*degToMove/360;
		if(degToMove > 180) {
			valueToMove = value360*(360-degToMove)/360;
			this.ClockRotate(valueToMove);
			
		} else {
			this.counterClockRotate(valueToMove);
		}
		System.out.println("Min was : "+min+"\nMin became : "+meanMin);
		//this.balaie(values[min]);
	}
	
	//This function is completely useless, considering the fact that i can do it entirely with code.
	public void balaie(float min) {
		//En théorie je suis déja à l'entrée gauche du point le plus court.
		//J'ai besoin de l'entree droite.
		//les valeurs sont de la forme 0,345
		//tant que je vois des valeurs arrondis au centieme qui sont similaire, je tourne. 
		//je bouge donc de 45°
		int mint = (int)(min*100);
		int valueToMove = value360*(90)/360;
		float[] values = new float[3000];
		for(int k = 0; k<3000;k++)
			values[k] = 9999999;
		int i = 0;
		//this.ClockRotate(valueToMove, true);
		int temp = (int)(US.getDist()*100);
		while(Math.abs(temp-mint) <= 4) {
			temp = (int)(US.getDist()*100);
			this.counterClockRotate(value360/360);
		}
		this.ClockRotate(value360*(2)/360);
		/*
		while(leftMotor.isMoving()) {
			//values[i] = US.getDist();
			int temp = (int)(US.getDist()*100);
			i++;
			if (Math.abs(temp-mint) > 3) {
				System.out.println("Mint = "+mint+" current = "+temp);
				leftMotor.stop();
				rightMotor.stop();
				break;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		for(int j=0; j<i;j++) {
			if(values[j]*100 != mint) {
				leftMotor.stop();
				rightMotor.stop();
				break;
			}
		}*/
		System.out.println("*beep* *boop*");
	}
	
	public int howManyDist() {
		int iterator = 0;
		this.that360();
		while(leftMotor.isMoving()) {
			US.getDist();
			iterator++;
		}
		return iterator;
	}
}