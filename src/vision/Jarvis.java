package vision;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.navigation.MovePilot;
import sensors.UltraSonicSensor;
/**
 * Classe du robot servant à définir son comportement
 * @author Maleus
 * @author Mat
 *
 */
public class Jarvis{
	private final static int DEPART=0;
	private final static int BUT=1;
	private final static int RECHERCHE=2;
	private final static int PALETTROUVE=3;
	private final static int PALETNONTROUVE=4;
	private final static int PALET=4;
	private final static int NOPALET=5;
	private OurMotor pilote;
	private double[] palets;
	private int notrePosition;
	private int enemyPosition;
	private int etat;
	public Jarvis() {
		// TODO Auto-generated constructor stub
		pilote = new OurMotor();
		palets= new double[9]; //On initialise la "valeur" des 9 palets à 1 (ils ont 100% de chance d'être sur le terrain)
		for (int i = 0; i<9; i++) {
			palets[i]=1;
		}
		etat=DEPART;
	}
	public double[] getPalets() {
		return palets;
	}
	public void setPalets(double[] palets) {
		this.palets = palets;
	}
	public int getNotrePosition() {
		return notrePosition;
	}
	public void setNotrePosition(int notrePosition) {
		this.notrePosition = notrePosition;
	}
	public int getEnemyPosition() {
		return enemyPosition;
	}
	public void setEnemyPosition(int enemyPosition) {
		this.enemyPosition = enemyPosition;
	}
	/**
	 * Algo simple pour mettre le premier but, il utilise les attributs notrePosition et enemyPosition pour déterminer quel palet récupérer
	 */
	public void premierBut() {
		
	}
	/**
	 * Permet de mettre la valeur du palet 
	 * @param a à 0 lorsqu'on l'a récupéré
	 */
	public void valeurPaletZero(int a) {
	
		
	}
	/**
	 * en prenant en compte @param enemyPosition on diminue à 50% la valeur des palets de sa colonne
	 */
	public void valeurPalet50() {
		
	}
	/**
	 * Methode permetant au robot de se déplacer de 
	 * @param distance centimetre
	 */
	public void seDeplacer(double distance) {
		
	}
	/**
	 * Methode permetant au robot de se tourner de
	 * @param degre
	 */
	public void seTourner(double degre) {
		
	}
	
	/**
	 * Méthode identifiant l'objet le plus proche et se tournant dans sa direction
	 */
	public float identifyNearest() {
		float[] values = new float[10000]; 		//stock les mesures faites par le senseur
		for(int k = 0; k<10000;k++)
			values [k] = 9999999; 				//initialise les valeurs à une très grande valeur. 
												//Dans les faits les cases à cette valeur ne devraient jamais être atteinte
		int i = 0;
		pilote.that360(true);					//fait un tour sur lui même et passe a la suite sans attendre
		//System.out.println("Doing the 360");	//Pour le debug
		while(pilote.getLeftMotor().isMoving()) {			//tant que le robot est en train de bouger
			values[i] = pilote.getUltraSon().getDist();			//je stock la valeur de la distance
			i++;					
			try {								//try-catch necessaire pour faire le Thread.sleep
				Thread.sleep(5);				//Le son se deplace à 36 m/s, la distance max est 2,5m, 
												//dans la théorie on pourrait attendre 1/7 ms, dans les faits si on met juste
												//Thread.sleep(1) ça commence à faire n'importe quoi, il y a des imprecisions. à 5ms c'est bien.
			} catch (InterruptedException e) {
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
		System.out.println("I'm stuck ! c : "+valueClock+" - cc : "+valueCounterClock);
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
		//int valueToMove = (int) pilote.degreeToRotation(degToMove);	//la valeur à donner au Clockrotate pour atteindre cette valeur.
		//System.out.println("right before the if. DegToMove = "+degToMove);
		if(degToMove > 180) {						//si on a plus de 180, c'est plus rapide de tourner dans le sens des aiguilles d'une montre
			//valueToMove = pilote.getValue360()*(360-degToMove)/360; //il faut recalculer la valeur a bouger
			//pilote.ClockRotate(valueToMove,false);
			pilote.seTourner(360-degToMove);
		} else {									//sinon c'est plus rapide de tourner dans le sens inverse des aiguilles d'une montre
			//pilote.counterClockRotate(valueToMove,false);
			pilote.seTourner(-degToMove);
		}
		System.out.println("Min was : "+min+"\nMin became : "+meanMin);	//debug
		return values[meanMin];
	}
	/**
	 * Methode permettant d'attraper le Palet le plus proche
	 */
	public void attrapePalet() {
		float distancePlusProche = identifyNearest();
		/*Avancer de la distance qui separe de l'objet. 
		 * si le bouton est activé, s'arreter et fermer les pinces. 
		 * Si c'est trop tot, il y a un probleme, peut etre qu'un autre palet est devant, peut etre que l'ennemi s'est mis entre nous. 
		 * Si on est arrivé au point et rien, peut etre que l'ennemi nous l'a volé.
		 */
	}
	
	/**
	 * Methode permettant d'aller marquer un but une fois le palet attrapé
	 *
	 */
	public void vasMarquer() {
		/*
		 * Identifier la direction des buts
		 * determiner si des palets sur le chemin sont a eviter
		 * faire le chemin vers les buts, verifier regulierement la distance devant nous
		 * quand on est dans les buts, lacher.
		 */
	}
	/**
	 * Methode permettant de mettre Jarvis en recherche
	 */
	public void setEtatRecherche() {
		
	}
	
	public OurMotor getPilote() {
		return this.pilote;
	}
	public  void setPositions() {
		System.out.println("Quel est ma position? 0 Gauche 1 Bas 2 Droite");
		Button.waitForAnyPress();
		int p= Button.readButtons();
		if (p==Button.ID_LEFT) notrePosition=0;
		else if (p==Button.ID_DOWN) notrePosition=1;
		else if (p==Button.ID_RIGHT) notrePosition=2;
		System.out.println("Quel est la position adverse? 0 Gauche 1 Bas 2 Droite");
		int q= Button.readButtons();
		if (q==Button.ID_LEFT) enemyPosition=0;
		else if (q==Button.ID_DOWN) enemyPosition=1;
		else if (q==Button.ID_RIGHT) enemyPosition=2;
		System.out.println(notrePosition+" HAHA"+enemyPosition);
		Button.waitForAnyPress();
		etat=DEPART;
	}
	

}
