package vision;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import sensors.UltraSonicSensor;

/** Créé une interface sur le modèle de MovePilot permettant de réaliser divers mouvements et methodes plus complexes d'orientation du robot.
 * @author JarvisTeam
 */
public class OurMotor {
	/**
	 * Vitesse actuelle du robot
	 */
	private static int speed;
	/**
	 * Acceleration actuelle du robot
	 */
	private static int acceleration;
	/**
	 * Vitesse par défaut du robot ( = vitesse maximale)
	 */
	public static int DEFAULT_SPEED = 1000;
	/**
	 * Acceleration par défaut du robot
	 */
	public static int DEFAULT_ACCELERATION = 500;
	/**
	 * Nombre de rotations simultanées necessaire pour que le robot fasse un tour sur lui meme
	 * Cette valeur a été mesuré par essais et erreurs
	 */
	private static int value360 = 780;
	/**
	 * Le nombre de rotation à donner au moteur des pinces pour les ouvrir ou les fermer.
	 */
	private static int valeurPinces = 900;
	/**
	 * Booléen permettant de savoir si les pinces sont ouvertes ou fermés, afin d'eviter de casser les pinces
	 * Faux par defaut car lorsqu'on commence, les pinces sont fermés.
	 */
	private boolean areClawsOpen = false;
	
	/**
	 * Instances de RegulatedMotor permettant de controler les trois moteurs, deux roues et les pinces
	 */
	private static RegulatedMotor leftMotor;
    private static RegulatedMotor rightMotor;
    private static RegulatedMotor clawMotor;
    /**
     * Instance de UltraSonicSensor permettant de mesure la distance vu par le capteur à UltraSons
     */
    private static UltraSonicSensor US;
   
    /**
     * Initialise OurMotor, les moteurs et senseurs utilisés. 
     */
    public OurMotor() {
    	speed = DEFAULT_SPEED;
    	acceleration = DEFAULT_ACCELERATION;
    	leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        clawMotor = new EV3LargeRegulatedMotor(MotorPort.D);
    	initMotor(leftMotor);
    	initMotor(rightMotor);
    	clawMotor.setSpeed(1000);
    	US = new UltraSonicSensor(SensorPort.S1);
    }
    
    /**
     * Initialise le moteur donné en entrée avec les valeurs par défaut.
     * @param m, moteur à initialiser
     */
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
     * @param le nombre de metre à parcourir
     */
	public void forward(double metres) {
		/**
		 * 1000 rotations permettent de se deplacer de 0.476 metres
		 */
		int rotation = (int)(metres*1000/0.476);
		forward(rotation, false);
	}
	
	/**
	 * Effectue un mouvement vers l'avant.
	 * @param le nombre de mètre a parcourir
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void forward(double metres, boolean b) {
		int rotation = (int)(metres*1000/0.476);
		forward(rotation, b);
	}
    /**
     * Effectue un mouvement vers l'avant.
     * @param rotation Le nombre de degrés de rotation de roues à effectuer
     */
	public void forward(int rotation) {
		forward(rotation, false);
	}
	/**
	 * Effectue un mouvement vers l'avant.
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void forward(int rotation, boolean boolCont) {
		leftMotor.rotate(rotation, true);
		rightMotor.rotate(rotation, boolCont);
	}
	
	/**
	 * Effectue un mouvement vers l'arrière
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 */
	public void backward(int rotation) {
		backward(rotation, false);
	}
	/**
	 * Effectue un mouvement vers l'arrière
	 * @param rotation Le nombre de degrés de rotation de roues à effectuer
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void backward(int rotation, boolean boolCont) {
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
		leftMotor.resetTachoCount();
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
	 * Effectue une rotation d'exactement 360°
	 * @param boolCont Si true, le robot ira en avant puis passera immediatement à la tache suivante
	 */
	public void that360(boolean boolCont) {
		seTourner(360,boolCont);
	}
	/**
	 * Ferme la pince
	 */
	public void closeClaw() {
		
			clawMotor.rotate(-valeurPinces);
    }
	
	/**
	 * Si les pinces ne sont pas deja fermés, les ferme.
	 * si b == true  : permet de fermer la pince et passe � l'action suivante apr�s avoir commencer la fermeture
	 * si b == false : permet de fermer la pince puis passe � l'action suivante
	 * @param b
	 */
	public void closeClaw(boolean b) {
    	if(areClawsOpen == true) {
			clawMotor.rotate(-valeurPinces,b);
			areClawsOpen = false;
    	}
	}
	
	/**
	 * Si les pinces ne sont pas ouvertes, les ouvres.
	 * si b == true  : permet d'ouvrir la pince et passe � l'action suivante apr�s avoir commencer la fermeture
	 * si b == false : permet d'ouvrir la pince puis passe � l'action suivante
	 * @param b
	 */
    public void openClaw(boolean b) {
    	if(areClawsOpen == false) {
			clawMotor.rotate(valeurPinces,b);
			areClawsOpen = true;
    	}
	}
    /**
     * Ouvre les pinces sans prendre en compte areClawsOpen, necessaire lors de bugs.
     */
    public void ForceOpen() {
    	clawMotor.rotate(valeurPinces);
    }
    /**
     * Ferme les pinces sans prendre en compte areClawsOpen, necessaire lors de bugs.
     */
    public void ForceClose() {
    	clawMotor.rotate(-valeurPinces);
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
	public static RegulatedMotor getLeftMotor() {
		return leftMotor;
	}
	/**
	 * @return Renvoie le moteur droit.
	 */
	public static RegulatedMotor getRightMotor() {
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
	 * Cette methode n'est plus utilisé, mais dans le plan initial, on faisait une manipulation en début de round pour que le robot calcule seul
	 * les variables de sa vitesse, les rotations a tourner pour avancer de x metres etc.
	 * On la garde pour la partie historique, meme si elle n'est plus utilisés.
	 */
	@SuppressWarnings("unused")
	private void measureSpeed() {
		//On 5 tries, mean distance for speed = 100 and acc = 100 for rotate = 1000 
		//is 0,476
		setSpeed(500);
		setAcceleration(100);
		float startDist = US.getDist();
		this.forward(1000);
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
	/**
	 * Modifie la vitesse du robot
	 * @param spe la nouvelle vitesse
	 */
	public void setSpeed(int spe) {
		setLeftS(spe);
		setRightS(spe);
		speed=spe;
	}
	/**
	 * met à jour la vitesse de la roue gauche
	 * @param spe la nouvelle vitesse
	 */
	public void setLeftS(int spe) {
		leftMotor.setSpeed(spe);
	}
	/**
	 * met à jour la vitesse de la roue droite
	 * @param spe la nouvelle vitesse
	 */
	public void setRightS(int spe) {
		rightMotor.setSpeed(spe);
	}
	/**
	 * Met à jour l'acceleration du robot
	 * @param acc la nouvelle acceleration
	 */
	public void setAcceleration(int acc) {
		setLeftA(acc);
		setRightA(acc);
		acceleration = acc;
	}
	/**
	 * Met à jour l'acceleration de la rouge gauche du robot
	 * @param acc la nouvelle acceleration
	 */
	public void setLeftA(int acc) {
		leftMotor.setAcceleration(acc);
	}
	/**
	 * Met à jour l'acceleration de la rouge droite du robot
	 * @param acc la nouvelle acceleration
	 */
	public void setRightA(int acc) {
		rightMotor.setAcceleration(acc);
	}
}