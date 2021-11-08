package vision;

import java.lang.annotation.Documented;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.navigation.MovePilot;
import sensors.TouchSensor;
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
	private final static int PALET=5;

	//private final static int NOPALET=5;
	
	private Boussole boussole;
	private OurMotor pilote;
	private TouchSensor s;
	private double[] palets;
	private int notrePosition;
	private int enemyPosition;
	private int etat;
	private Etat cogito;
	
	public Jarvis() {
		// TODO Auto-generated constructor stub
		pilote = new OurMotor();
		palets= new double[9]; //On initialise la "valeur" des 9 palets à 1 (ils ont 100% de chance d'être sur le terrain)
		for (int i = 0; i<9; i++) {
			palets[i]=1;
		}
		s= new TouchSensor(SensorPort.S3);
		etat=PALETNONTROUVE;
		boussole = new Boussole();
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
	
	public Etat getEtat() {
		return cogito;
	}
	/**
	 * Algo simple pour mettre le premier but, il utilise les attributs notrePosition et enemyPosition pour déterminer quel palet récupérer
	 */
	public void premierBut() {
		switch (notrePosition) {
		case 0:
			switch(enemyPosition) {
			case 0:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			case 1:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			case 2:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			}
			break;
		case 1:
			switch(enemyPosition) {
			case 0:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			case 1:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			case 2:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			}
			break;
		case 2:
			switch(enemyPosition) {
			case 0:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			case 1:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			case 2:
				pilote.openClaw(true);
				OurMotor.forward(0.65);
				if(s.getTouch()==1) pilote.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				pilote.seTourner(45,false,this);
				OurMotor.forward(0.40);
				pilote.seTourner(-45,false,this);
				OurMotor.forward(1.60);
				//ici mettre la condition qu'on avance tant qu'on est pas sur la ligne
				pilote.openClaw(false);
				etat=BUT;
				break;
			}
			break;
		}
		pilote.closeClaw(true);
		pilote.backward(720);
		
	}
	
	
	/**
	 * Methode permetant au robot de se tourner de
	 * @param degre
	 */
	public void seTourner(double degre) {
		pilote.seTourner(degre,false,this);
		boussole.majBoussole(degre);
	}
	
	
	/**
	 * Fonction permettant de trouver un palet et d'orienter Jarvis vers ce dernier. 
	 * Pour ce faire, elle fait réaliser à Jarvis un tour sur lui même en cherchant les cassures dans les distances des objets autour de lui.
	 * Si Jarvis repère une cassure, c'est qu'il a repéré soit un palet soit le robot adverse(cas à traiter)
	 * elle met à jour l'état de jarvis lorsqu'elle trouve un palet 
	 */
	public void checkNearestPalet() {
		
		pilote.setSpeed(100);
		pilote.setAcceleration(100);
		
		//float[] values = new float[10000]; 	
		float val1;
		float val2;
		/*for(int k = 0; k<10000;k++)
			values [k] = 9999999; 				
		*/										
		//int i = 0;
		pilote.that360(true);
		while(pilote.getLeftMotor().isMoving()) {
			//values[i]= pilote.getUltraSon().getDist();			
			//i++;
			val1 = pilote.getUltraSon().getDist();
			//System.out.println(values[i]);
			try {								
				Thread.sleep(5);												
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			val2 = pilote.getUltraSon().getDist();
			
			/*if(Math.abs(values[i]-values[i-1]) > 0.1 && Math.abs(values[i]-cogito.nearDist())<0.1) {
				b = true;	
			}*/
			
			float f = Float.POSITIVE_INFINITY;
			if(Math.abs(val1 - val2) > 0.1 && val1!=f && val2 != f) {
				System.out.print("caca");
				etat = PALETTROUVE;	
				pilote.seTourner(1, true, this);
			}
	
		}
		pilote.setAcceleration(pilote.DEFAULT_ACCELERATION);
		pilote.setSpeed(pilote.DEFAULT_SPEED);
	}
	
	/**
	 * méthode permettant à Jarvis d'aller atttraper un palet après l'avoir repéré et s'être orienter dans sa direction grâce à checkNearestPalet
	 * Elle met à jour l'état de Jarvis lorsqu'elle a attrappé le palet
	 * @param dist : distance séparant Jarvis du palet repéré
	 *
	 */
	public void attrapePalet(float dist) {
		this.pilote.openClaw(true);
		OurMotor.forward(dist);
		if(s.getTouch()==1) {
			this.pilote.closeClaw(true); 
			etat = PALET;
		}
	}
	
	/**
	 * Methode permettant d'aller marquer un but une fois le palet attrapé
	 * Elle met à jour l'état de Jarvis lorsque le but a été marqué 
	 */
	
	public void vasMarquer() {
		double angle = boussole.getOurAngle();
		if(angle>180)
			seTourner((360-angle));
		else 
			seTourner(-angle);
		OurMotor.forward(10000,true);
		while(pilote.getLeftMotor().isMoving()) {
			float dist = this.pilote.getUltraSon().getDist();
			if(dist<0.3) {
				OurMotor.forward(0,true);
				this.pilote.openClaw(false);
				this.pilote.closeClaw(true);
				OurMotor.backward(720);
				etat = PALETNONTROUVE;
				
			}
			try {								
				Thread.sleep(5);																		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	/**
	 * Methode permettant de mettre Jarvis en recherche
	 */
	public void setEtatRecherche() {
		etat=RECHERCHE;
	}
	
	public OurMotor getPilote() {
		return this.pilote;
	}
	
	/**
	 * Méthode permettant d'initialiser la position de Jarvis ainsi que celle du robot adverse au début de la partie
	 */
	
	public  void setPositions() {
		System.out.println("Quel est ma position? 0 Gauche 1 Bas 2 Droite");
		Button.waitForAnyPress();
		int p= Button.readButtons();
		if (p==Button.ID_LEFT) notrePosition=0;
		else if (p==Button.ID_DOWN) notrePosition=1;
		else if (p==Button.ID_RIGHT) notrePosition=2;
		System.out.println("Quel est la position adverse? 0 Gauche 1 Bas 2 Droite");
		Button.waitForAnyPress();
		int q= Button.readButtons();
		if (q==Button.ID_LEFT) enemyPosition=0;
		else if (q==Button.ID_DOWN) enemyPosition=1;
		else if (q==Button.ID_RIGHT) enemyPosition=2;
		System.out.println("Je suis en position"+notrePosition+"Iron Man est en position"+enemyPosition);
		Button.waitForAnyPress();
		etat=DEPART;
	}
	

	
	/**
	 * Méthode représentant un automate simple de la partie.
	 * 
	 */
	
	public void partieSimple() {
		boolean jeu = true;
		this.setPositions();
		this.premierBut();
		etat = PALETNONTROUVE;
		do {
			if(Button.readButtons()!=0) 
				jeu = false;
			if(etat == PALETNONTROUVE) {
				this.checkNearestPalet();
				System.out.println("Stopped searching !");
				if(etat==PALETTROUVE) {
					float dist = this.pilote.getUltraSon().getDist();
					if(dist>0.32) {
						attrapePalet(dist);
						if(etat == PALETTROUVE)
							etat = PALETNONTROUVE;
					}
					else {
						etat = PALETNONTROUVE;
					}
				}
				
			}
			else if(etat == PALET) {
				vasMarquer();
			}
		}while(jeu);
	}
	
	////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	//POUBELLE
	////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	
	public void recherchePalet(float[] valeurs) {
	}
	
	//fonction plus utilisée ??
		/**
		 * Permet de mettre la valeur du palet 
		 * @param a à 0 lorsqu'on l'a récupéré
		 */
		public void valeurPaletZero(int a) {
		
			
		}
		
		
		//fonction plus utilisée ??
		/**
		 * en prenant en compte @param enemyPosition on diminue à 50% la valeur des palets de sa colonne
		 */
		public void valeurPalet50() {
			
		}
		
		
		//fonction plus utilisée ??
		/**
		 * Methode permetant au robot de se déplacer de 
		 * @param distance centimetre
		 */
		public void seDeplacer(double distance) {
			
		}
		
	//fonction plus utilisée ??
		/**
		 * Fait tourner jarvis sur lui meme et prends une mesure de la distance toutes les 5ms
		 * @return un tableau de float contenant les valeurs mesurés
		 */
		public float[] regarderAutour() {
			pilote.setSpeed(100);
			pilote.setAcceleration(100);
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
			pilote.setAcceleration(pilote.DEFAULT_ACCELERATION);
			pilote.setSpeed(pilote.DEFAULT_SPEED);
			
			return values;
			
			/* ------ Fin du calcul de la moyenne ------ */
			/*
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
			return values;*/
		}
		
		//fonction plus utilisée ??
		/**
		 * Trouve le minimum d'un tableau, renvoie l'indice
		 * @param values un tableau de flottants
		 * @return l'indice du minimum en moyenne
		 */
		public int trouverMinimum(float values[]) {

			//System.out.println("Searching for min.."); //pour le debug
			int min = 0;
			for(int j = 0; j < values.length; j++) {			//pour j allant de 0 à la valeur max de i, donc la dernière entrée
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
			//System.out.println("I'm stuck ! c : "+valueClock+" - cc : "+valueCounterClock);
			while(Math.abs(values[valueCounterClock]-values[min]) < 0.05) {
				valueCounterClock--;
			}
			while(Math.abs(values[valueClock]-values[min]) < 0.05) {
				valueClock++;
			}
			int meanMin = (valueCounterClock+valueClock)/2; //ça c'est le centre.
			return meanMin;
		}
		
		//fonction plus utilisée ??
		public int[] trouverCassures(float [] values) {
			int[] temp = new int[200];
			int j = 0;
			for(int i = 1; i<values.length; i++) {
				if(Math.abs(values[i]-values[i-1]) > 0.1) {
					temp[j] = i;
					j++;
				}
			}
			int [] ret = new int[j];
			for(int i = 0; i<j; i++) {
				ret[i] = temp[i];
			}
			return ret;
		}
		
		//fonction plus utilisée ??
		public boolean checkNearestPalet(double nearAngle) {
			boolean b = false;
			pilote.seTourner((int)nearAngle, true, this);
			pilote.setSpeed(100);
			pilote.setAcceleration(100);
			float[] values = new float[10000]; 		
			for(int k = 0; k<10000;k++)
				values [k] = 9999999; 				//initialise les valeurs à une très grande valeur. 
													//Dans les faits les cases à cette valeur ne devraient jamais être atteinte
			int i = 0;
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
				
				/*if(Math.abs(values[i]-values[i-1]) > 0.1 && Math.abs(values[i]-cogito.nearDist())<0.1) {
					b = true;	
				}*/
				if(Math.abs(values[i]-values[i-1]) > 0.1) {
					b = true;	
					pilote.seTourner(0, true, this);
				}
				break;
			}
			pilote.setAcceleration(pilote.DEFAULT_ACCELERATION);
			pilote.setSpeed(pilote.DEFAULT_SPEED);
			System.out.println(b);
			return b;	
		}
		
}