package vision;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import sensors.UltraSonicSensor;

/** Créé une interface sur le modèle de MovePilot permettant de réaliser divers mouvements et methodes plus complexes d'orientation du robot.
 * @author mat
 * @version 0.1
 */
public class OurMotor {
	private static int speed = 100;
	private static int value360 = 780;
	private boolean isClawOpen = false;
	
	private static RegulatedMotor leftMotor;
    private static RegulatedMotor rightMotor;
    private static RegulatedMotor clawMotor;
    private static UltraSonicSensor US;
    
    /**
     * Initialise OurMotor, les moteurs et senseurs utilisés. 
     */
    public OurMotor() {
    	initMotor();
    }
	
    //initialise le moteur
    private static void initMotor() {
    	leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        clawMotor = new EV3LargeRegulatedMotor(MotorPort.D);
    	initMotor(leftMotor);
    	initMotor(rightMotor);
    	initMotor(clawMotor);
    	US = new UltraSonicSensor(SensorPort.S1);
    }
    private static void initMotor(RegulatedMotor m) {
    	m.setSpeed(speed);
    }
    
    /**
     * Ecrit un message sur l'écran du robot puis interromps l'execution dans l'attente d'une pression de bouton
     * @param str String contenant le message à écrire
     */
    public static void stopMessage(String str) {
    	System.out.println(str);
    	Button.waitForAnyPress();
    }
    
    /**
     * Effectue un mouvement vers l'avant.
     * @param rotation Le nombre de degrés de rotation de roues à effectuer
     */
	public static void forward(int rotation) {
		forward(rotation, false);
	}
	/**
	 * Effectue un mouvement vers l'avant.
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public static void forward(int rotation, boolean boolCont) {
		leftMotor.rotate(rotation, true);
		rightMotor.rotate(rotation, boolCont);
	}
	
	/**
	 * Effectue un mouvement vers l'arrière
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 */
	public static void backward(int rotation) {
		backward(rotation, false);
	}
	/**
	 * Effectue un mouvement vers l'arrière
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public static void backward(int rotation, boolean boolCont) {
		leftMotor.rotate(-rotation, true);
		rightMotor.rotate(-rotation, boolCont);
	}
	
	/**
	 * Effectue une rotation sur lui même dans le sens inverse des aiguilles d'une montre
	 * @param rotation Le nombre de degrés de rotation à effectuer par chaque roues
	 */
	public void counterClockRotate(int rotation) {
		this.counterClockRotate(rotation, false);
	}
	/**
	 * Effectue une rotation sur lui même dans le sens inverse des aiguilles d'une montre
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void counterClockRotate(int rotation, boolean boolCont) {
		leftMotor.rotate(-rotation, true);
		rightMotor.rotate(rotation,boolCont);
	}
	/**
	 * Effectue une rotation sur lui même dans le sens des aiguilles d'une montre
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 */
	public void ClockRotate(int rotation) {
		this.ClockRotate(rotation,false);
	}
	/**
	 * Effectue une rotation sur lui même dans le sens des aiguilles d'une montre
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void ClockRotate(int rotation, boolean boolCont) {
		leftMotor.rotate(rotation, true);
		rightMotor.rotate(-rotation, boolCont);
	}
	/**
	 * Effectue une rotation sur lui même de 180° en ne bougeant qu'une seule roue
	 * @param direction String contenant "left" ou "right" selon la roue a utiliser
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 * @deprecated Utilisez plutôt ClockRotate ou that360.
	 */
	public void monoWheel180(String wheel,boolean boolCont) {
		int radius = value360;
		switch(wheel) {
		case "left" :
			leftMotor.rotate(radius,boolCont);
			break;
		case "right" :
			rightMotor.rotate(radius,boolCont);
			break;
		default :
			break;
		}
	}
	/**
	 * Effectue une rotation sur lui même de 360° en ne bougeant qu'une seule roue
	 * @param direction String contenant "left" ou "right" selon la roue a utiliser
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 * @deprecated Utilisez plutôt ClockRotate ou that360.
	 */
	public void monoWheel360(String direction,boolean boolCont) {
		int radius = value360*2;
		switch(direction) {
		case "left" :
			leftMotor.rotate(radius,boolCont);
			break;
		case "right" :
			rightMotor.rotate(radius,boolCont);
			break;
		default :
			break;
		}
	}
	/**
	 * Effectue une rotation d'exactement 360°
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void that360(boolean boolCont) {
		leftMotor.rotate(value360,true);
		rightMotor.rotate(-value360,boolCont);
	}
	/**
	 * Ferme la pince
	 */
	public void closeClaw() {
		if(this.isClawOpen) {
			clawMotor.rotate(-1800);
			isClawOpen = false;
		}
    }
	/**
	 * Ouvre la pince
	 */
    public void openClaw() {
    	if(!this.isClawOpen) {
    		clawMotor.rotate(1800,true); //lorsqu'on ouvre la pince, pas besoin de rester immobile
    		isClawOpen = true;
    	}
    }
	
    /**
     * Fait un tour sur lui même, trouve l'objet le plus proche et tourne dans sa direction
     */
	public void surrondings() {
		float[] values = new float[10000];
		for(int k = 0; k<10000;k++)
			values [k] = 9999999;
		int i = 0;
		this.that360(true);
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
	}
	
	/**
	 * Fait un tour sur lui même et mesure continuellement les distances face à lui
	 * @return Renvoie un integer contenant le nombre de distance qu'a pris le robot pendant un tour.
	 */
	public int howManyDist() {
		int iterator = 0;
		this.that360(true);
		while(leftMotor.isMoving()) {
			US.getDist();
			iterator++;
		}
		return iterator;
	}
}