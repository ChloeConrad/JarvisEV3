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
	private static int speed;
	private static int acceleration;
	public static int DEFAULT_SPEED = 500;
	public static int DEFAULT_ACCELERATION = 100;
	private static int value360 = 780;
	public static int distFor1000 = 0;
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
	
    private static void initMotor() {
    	speed = DEFAULT_SPEED;
    	acceleration = DEFAULT_ACCELERATION;
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
    	m.setAcceleration(acceleration);
    }
    
    /**
     * Ecrit un message sur l'écran du robot puis interromps l'execution dans l'attente d'une pression de bouton
     * @param str String contenant le message à écrire
     */
    public void stopMessage(String str) {
    	System.out.println(str);
    	Button.waitForAnyPress();
    }
    /**
     * Effectue un mouvement vers l'avant.
     * @param rotation Le nombre de degrés de rotation de roues à effectuer
     */
	public static void forward(double metres) {
		/*
		 * pour 1000rot = 0,476
		 * donc x = metres
		 */
		int rotation = (int)(metres*1000/0.476);
		forward(rotation, false);
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
	 * Se tourne en degres
	 * @param degres le nombre de degres à tourner
	 */
	public void seTourner(double degres) {
		seTourner(degres, false);
	}
	/**
	 * Se tourne en degres
	 * @param degres le nombre de degres à tourner
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void seTourner(double degres, boolean boolCont) {
		int rotation = degreeToRotation(degres);
		ClockRotate(rotation,boolCont);
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
		//if(this.isClawOpen) {
			clawMotor.rotate(-1800);
		//	isClawOpen = false;
		//}
    }
	
	/**
	 * si b == true  : permet de fermer la pince et passe � l'action suivante apr�s avoir commencer la fermeture
	 * si b == false : permet de fermer la pince puis passe � l'action suivante
	 * @param b
	 */
	public void closeClaw(boolean b) {
		clawMotor.rotate(-1800,b);
	}
	
	/**
	 * si b == true  : permet d'ouvrir la pince et passe � l'action suivante apr�s avoir commencer la fermeture
	 * si b == false : permet d'ouvrir la pince puis passe � l'action suivante
	 * @param b
	 */
    public void openClaw(boolean b) {
		clawMotor.rotate(1800,b);
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
	/**
	 * Convertit des degres en nombre de rotations. 
	 * @param degres Le nombre de degres dont on veux bouger
	 * @return la valeur a donner à rotate pour bouger de ces degres. 
	 */
	public int degreeToRotation(double degres) {
		return (int) (value360*degres/360);
	}
	/**
	 * Essai de methode pour avancer de manière incurvée.
	 * @param speedOfFirstWheel La vitesse de la roue gauche.
	 * @param multiplicatorOfSecondWheel Le multiplicateur de vitesse pour la roue droite
	 * @param rotation Le nombre de degré de rotation de roue à effectuer.
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void curveTry1(int speedOfFirstWheel, double multiplicatorOfSecondWheel, int rotation, boolean boolCont) {
		setAcceleration(500);
		leftMotor.setSpeed(speedOfFirstWheel);
		rightMotor.setSpeed((int) (speedOfFirstWheel*multiplicatorOfSecondWheel));
		//Si la vitesse est differente, ils risquent de s'arreter de tourner a des moments differents.
		//Il faut donc multiplier les rotations de la roue modifié par le multiplicateur
		leftMotor.rotate(rotation, true); //le multiplicateur est a droite, donc on l'applique a gauche
		rightMotor.rotate((int) (rotation*multiplicatorOfSecondWheel), boolCont);
	}
	/**
	 * @return Renvoie le moteur gauche.
	 */
	public RegulatedMotor getLeftMotor() {
		return leftMotor;
	}
	/**
	 * @return Renvoie le moteur droit.
	 */
	public RegulatedMotor getRightMotor() {
		return rightMotor;
	}
	/**
	 * @return Renvoie le moteur de la pince.
	 */
	public RegulatedMotor getClawMotor() {
		return clawMotor;
	}
	public int getValue360() {
		return value360;
	}
	public UltraSonicSensor getUltraSon() {
		return OurMotor.US;
	}
	/**
	 * Mesure la distance parcouru a vitesse 100, acceleration 50 et rotate 1000
	 */
	@SuppressWarnings("unused") //Stays here for testing reasons
	private void measureSpeed() {
		//On 5 tries, mean distance for speed = 100 and acc = 100 for rotate = 1000 
		//is 0,476
		setSpeed(500);
		setAcceleration(100);
		float startDist = US.getDist();
		OurMotor.forward(1000);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		float stopDist = US.getDist();
		float advance = startDist-stopDist;
		System.out.println("For rotate 1000 I move "+advance+" meters");
	}
	/**
	 * renvoie la valeure de la variable de vitesse qui est
	 * @return la dernière vitesse commune aux deux roues
	 */
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int spe) {
		setLeftS(spe);
		setRightS(spe);
		speed=spe;
	}
	public void setLeftS(int spe) {
		leftMotor.setSpeed(spe);
	}
	public void setRightS(int spe) {
		rightMotor.setSpeed(spe);
	}
	public void setAcceleration(int acc) {
		setLeftA(acc);
		setRightA(acc);
		acceleration = acc;
	}
	public void setLeftA(int acc) {
		leftMotor.setAcceleration(acc);
	}
	public void setRightA(int acc) {
		rightMotor.setAcceleration(acc);
	}
}