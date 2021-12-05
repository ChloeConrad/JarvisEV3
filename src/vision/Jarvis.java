package vision;


import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import sensors.ColorSensor;
import sensors.TouchSensor;
/**
 * Classe du robot servant � d�finir son comportement
 * @author JarvisTeam
 *
 */
public class Jarvis{

	private final static int PALETTROUVE=1;     
	private final static int PALETNONTROUVE=2;
	private final static int PALET=3;

	/**
	 * instance de Boussole permettant de connaitre en temps reel la direction du robot.
	 */
	private Boussole boussole;
	/**
	 * Instance de OurMotor permettant d'executer les commandes de bases permettant de bouger le robot.
	 */
	private OurMotor moteur;
	/**
	 * Instance de TouchSensor permettant de gerer le capteur de toucher
	 */
	protected TouchSensor s;
	
	/**
	 * Variables permettant d'initialiser les positions des deux competiteurs afin de determiner quelle strategie utiliser
	 * afin d'eviter de se cogner contre le robot adverse.
	 */
	private int notrePosition;
	private int enemyPosition;
	/**
	 * Etat courant du robot
	 */
	private int etat;
	
	/**
	 * Booléen étant vrai si le capteur de touché est enclanché.
	 */
	protected boolean touch;
	/**
	 * Instance de ColorSensor permettant de connaitre la couleur vu par le robot.
	 */
	ColorSensor color;

	/**
	 * Constructeur, initialise les valeurs par défauts des variables de classe
	 */
	public Jarvis() {
		touch=false;
		etat=PALETNONTROUVE;
		color = new ColorSensor();
		s= new TouchSensor(SensorPort.S3);
		moteur = new OurMotor();
		boussole = new Boussole();
	}
	
	/**
	 * Getter de notre Position
	 * @return un entier indiquant le point de départ de notre robot en début de manche
	 */
	public int getNotrePosition() {
		return notrePosition;
	}
	/**
	 * Initialise la valeur de notre position à
	 * @param notrePosition
	 */
	public void setNotrePosition(int notrePosition) {
		this.notrePosition = notrePosition;
	}
	/**
	 * Getter de la position ennemi
	 * @return un entier indiquant le point de départ du robot adverse en début de manche
	 */
	public int getEnemyPosition() {
		return enemyPosition;
	}
	/**
	 * Initialise la valeur de la position ennemi à
	 * @param enemyPosition
	 */
	public void setEnemyPosition(int enemyPosition) {
		this.enemyPosition = enemyPosition;
	}

	/**
	 * Algo simple pour mettre le premier but, il utilise les attributs notrePosition et enemyPosition pour d�terminer quel palet r�cup�rer
	 */
	public void premierBut() {
		moteur.setSpeed(3000);
		moteur.setAcceleration(500);
		switch (notrePosition) {
		case 2:
			switch(enemyPosition) {
			case 2:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			case 1:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			case 0:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			}
			break;
		case 1:
			switch(enemyPosition) {
			case 2:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			case 1:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			case 0:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			}
			break;
		case 0:
			switch(enemyPosition) {
			case 2:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			case 1:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			case 0:
				moteur.openClaw(true);
				moteur.forward(0.65);
				if(s.getTouch()==1) moteur.closeClaw(true);
				//pilote.curveTry1(100, 1000, 1000, false);
				moteur.seTourner(45,false);
				boussole.majBoussole();
				moteur.forward(0.40);
				moteur.seTourner(-45,false);
				boussole.majBoussole();
				moteur.forward(1.60);
				moteur.openClaw(false);
				etat=PALETNONTROUVE;
				break;
			}
			break;
		}
		moteur.closeClaw(true);
		moteur.backward(720);
		seTourner(90);
		moteur.setSpeed(OurMotor.DEFAULT_SPEED);
		moteur.setAcceleration(OurMotor.DEFAULT_ACCELERATION);
		touch=false;

	}


	/**
	 * Methode permetant au robot de se tourner d'un nombre donné de degres et met à jour la direction de la boussole
	 * @param degre
	 */
	public void seTourner(double degre) {
		moteur.seTourner(degre,false);
		boussole.majBoussole();
	}


	/**
	 * Fonction permettant de trouver un palet et d'orienter Jarvis vers ce dernier. 
	 * Pour ce faire, elle fait r�aliser � Jarvis un tour sur lui m�me en cherchant les cassures dans les distances des objets autour de lui.
	 * Si Jarvis rep�re une cassure, c'est qu'il a rep�r� soit un palet soit le robot adverse(cas � traiter)
	 * elle met � jour l'�tat de jarvis lorsqu'elle trouve un palet 
	 */
	public void checkNearestPalet() {
		/**
		 * Mettre la vitesse à une faible valeur afin de rendre les mesures de distances plus precises
		 */
		moteur.setSpeed(100);
		moteur.setAcceleration(100);
		float valDistanceUS1;
		float valDistanceUS2;
		/**
		 * Faire un tour sur lui même à 360 degres, et tant que le robot tourne, faire des mesures de distances
		 */
		moteur.seTourner(360,true);
		while(true && OurMotor.getLeftMotor().isMoving()) {
			valDistanceUS1 = moteur.getUltraSon().getDist();
			try {								
				Thread.sleep(5);												
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			valDistanceUS2 = moteur.getUltraSon().getDist();
			float f = Float.POSITIVE_INFINITY;
			/**
			 * Si l'ecart entre deux distances est superieur à 20cm, on estime qu'il y a une "cassure", et donc la presence d'un objet, surement un palet
			 */
			if(Math.abs(valDistanceUS1 - valDistanceUS2) > 0.20 && valDistanceUS1!=f && valDistanceUS2 != f) {
				etat = PALETTROUVE;	
				boussole.majBoussole();
				OurMotor.getRightMotor().stop(true);
				OurMotor.getLeftMotor().stop();
				boussole.majBoussole();
				break;
			}
		}
		/**
		 * Remettre la vitesse à leurs valeurs normales
		 */
		moteur.setAcceleration(OurMotor.DEFAULT_ACCELERATION);
		moteur.setSpeed(OurMotor.DEFAULT_SPEED);	
	}

	/**
	 * méthode permettant à Jarvis d'aller atttraper un palet après l'avoir repéré et s'être orienté dans sa direction
	 * Elle met à jour l'état de Jarvis lorsqu'elle a attrappé le palet
	 * @param dist : distance séparant Jarvis du palet repéré
	 *
	 */
	public void attrapePalet(float dist) {
		/**
		 * Avance de un peu plus que la distance séparant le robot du palet, et ouvre les pinces
		 */
		moteur.forward(dist+0.05,true);
		this.moteur.openClaw(true);
		/**
		 * Tant que le robot bouge, si la distance est inferieur à 10cm, ça veux dire qu'on est fac à un mur ou un robot adverse
		 */
		while(OurMotor.getLeftMotor().isMoving()) {
			if(this.moteur.getUltraSon().getDist()<0.10) {
				etat = PALETNONTROUVE;
				OurMotor.getRightMotor().stop(true);
				OurMotor.getLeftMotor().stop();
				break;
			}
		}
		/**
		 * Si le capteur de toucher est activé, on a trouvé un palet
		 */
		if(touch==true) {
			this.moteur.closeClaw(false); 
			etat = PALET;
		}
		/**
		 * Sinon on part en arriere d'une courte distance et on se remet en cherche d'un palet
		 */
		else {
			this.moteur.closeClaw(true);
			moteur.backward(720);
			etat = PALETNONTROUVE;
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

		float dist = moteur.getUltraSon().getDist();
		moteur.forward(dist-0.25,false);


		this.moteur.openClaw(false);
		this.moteur.closeClaw(true);
		moteur.backward(720);
		etat = PALETNONTROUVE;
		touch=false;
		seTourner(90);
	}
	
	/**
	 * Getter de l'instance de OurMotor
	 * @return OurMotor moteur
	 */
	public OurMotor getPilote() {
		return this.moteur;
	}

	/**
	 * Méthode permettant d'initialiser la position de Jarvis ainsi que celle du robot adverse au début de la partie
	 */

	public  void setPositions() {
		System.out.println("Quel est ma position? 0-Gauche 1-Bas 2-Droite");
		Button.waitForAnyPress();
		int p= Button.readButtons();
		if (p==Button.ID_LEFT) notrePosition=0;
		else if (p==Button.ID_DOWN) notrePosition=1;
		else if (p==Button.ID_RIGHT) notrePosition=2;
		System.out.println("Quel est la position adverse? 0-Gauche 1-Bas 2-Droite");
		Button.waitForAnyPress();
		int q= Button.readButtons();
		if (q==Button.ID_LEFT) enemyPosition=0;
		else if (q==Button.ID_DOWN) enemyPosition=1;
		else if (q==Button.ID_RIGHT) enemyPosition=2;
		System.out.println("Je suis en position"+notrePosition+"Iron Man est en position"+enemyPosition);
		Button.waitForAnyPress();
		
		etat=PALETNONTROUVE;
	}



	/**
	 * Méthode représentant un automate simple de la partie.
	 * 
	 */
	public void partieSimple() {
		OurMotor.getLeftMotor().resetTachoCount();
		boussole.setOurAngle(0);
		boolean jeu = true;
		this.setPositions();
		this.premierBut();
		etat = PALETNONTROUVE;

		do {
			if(Button.readButtons()!=0) 
				jeu = false;
			if(etat == PALETNONTROUVE) {
				this.checkNearestPalet();

				if(etat==PALETTROUVE) {
					float dist = this.moteur.getUltraSon().getDist();
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


		}while (jeu==false);
	}
}