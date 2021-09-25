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
    public void stopMessage(String str) {
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
		if(degres>0) {
			ClockRotate(rotation,boolCont);
		} else {
			counterClockRotate(rotation, boolCont);
		}
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
    		clawMotor.rotate(1800,true); 		//lorsqu'on ouvre la pince, pas besoin de rester immobile
    		isClawOpen = true;
    	}
    }
	
    /**
     * Fait un tour sur lui même, trouve l'objet le plus proche et tourne dans sa direction
     */
	public void surrondings() {
		float[] values = new float[10000]; 		//stock les mesures faites par le senseur
		for(int k = 0; k<10000;k++)
			values [k] = 9999999; 				//initialise les valeurs à une très grande valeur. 
												//Dans les faits les cases à cette valeur ne devraient jamais être atteinte
		int i = 0;
		this.that360(true);						//fait un tour sur lui même et passe a la suite sans attendre
		//System.out.println("Doing the 360");	//Pour le debug
		while(leftMotor.isMoving()) {			//tant que le robot est en train de bouger
			values[i] = US.getDist();			//je stock la valeur de la distance
			i++;					
			try {								//try-catch necessaire pour faire le Thread.sleep
				Thread.sleep(5);				//Le son se deplace à 36 m/s, la distance max est 2,5m, 
												//dans la théorie on pourrait attendre 1/7 ms, dans les faits si on met juste
												//Thread.sleep(1) ça commence à faire n'importe quoi, il y a des imprecisions. à 5ms c'est bien.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("Searching for min.."); //pour le debug
		int min = 0;
		for(int j = 0; j < i; j++) {			//pour j allant de 0 à la valeur max de i, donc la dernière entrée
												//du tableau utilisé,
			if(values[j] < values[min]) {		//on cherche la valeur minimal du tableau.
				min = j;
			}
		}
		//System.out.println("i : "+i+ "; min = "+min+"; value(min) = "+(values[min])); //pour le debug
		/* Pour les lignes qui suivent, 
		 * on va chercher deux minimums plus large que le minimu, parce que le fait que le robot
		 * voit avec un angle fait qu'on se retrouve avec un grand nombre de cases dans lesquelles
		 * il voit la distance minimale. Le robot tournant dans le sens des aiguilles d'une montre,
		 * la première valeur du tableau qui vaux le minimum sera à gauche du palet, et pas en plein
		 * dessus. Donc on cherche a determiner de quelle case à quelle case on voit le point le plus proche.
		 * donc on va aller voir a gauche, en arrondissant au centième, et en cherchant un écart superieur à
		 * 0.05, et pas 0.01, parce que le senseur n'est pas parfait, donc on a des points ou il voit le
		 * au dessus du mimum alors qu'il est en plein dessus. Par essai et erreur, 0.05 fonctionne le mieux. 
		 * Moins, et c'est trop sensibles aux imprecisions, plus et on a un angle de 90°.
		 * donc il cherche à gauche et a droite du palet, voit le dernier point ou il voit le palet
		 * puis fait une moyenne des numeros de case pour viser le centre.
		 */
		int valueCounterClock = min;
		int valueClock = min;
		while(Math.abs(values[valueCounterClock]-values[min]) < 0.05) {
			valueCounterClock--;
		}
		while(Math.abs(values[valueClock]-values[min]) < 0.05) {
			valueClock++;
		}
		int meanMin = (valueCounterClock+valueClock)/2; //ça c'est le centre.
		/* ------ Fin du calcul de la moyenne ------ */
		
		float deg = (float) (360.0 / (float)i); 	//le nombre de degrés qui bougent entre chaques mesurent.
		float degMoved = meanMin*deg; 				//le nombre de degré dont on a bougé avant de voir le plus proche
		int degToMove = (int) (360 - degMoved); 	//le nombre de degrés à bouger pour atteindre ce point
		int valueToMove = (int) degreeToRotation(degToMove);	//la valeur à donner au Clockrotate pour atteindre cette valeur.
		if(degToMove > 180) {						//si on a plus de 180, c'est plus rapide de tourner dans le sens des aiguilles d'une montre
			valueToMove = value360*(360-degToMove)/360; //il faut recalculer la valeur a bouger
			this.ClockRotate(valueToMove,false);
			
		} else {									//sinon c'est plus rapide de tourner dans le sens inverse des aiguilles d'une montre
			this.counterClockRotate(valueToMove,false);
		}
		//System.out.println("Min was : "+min+"\nMin became : "+meanMin);	//debug
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
}