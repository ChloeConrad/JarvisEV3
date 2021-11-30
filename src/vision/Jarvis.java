package vision;

import java.lang.annotation.Documented;
import java.util.Arrays;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Pose;
import sensors.ColorSensor;
import sensors.TouchSensor;
import sensors.UltraSonicSensor;
/**
 * Classe du robot servant à définir son comportement
 * @author Maleus
 * @author Mat
 *
 */
public class Jarvis{

	private final static int PALETTROUVE=1;     
	private final static int PALETNONTROUVE=2;
	private final static int PALET=3;


	private Boussole boussole;
	private OurMotor moteur;
	protected TouchSensor s;
	
	private int notrePosition;
	private int enemyPosition;
	private int etat;
	
	protected boolean touch;
	ColorSensor color;

	public Jarvis() {
		
		touch=false;
		color = new ColorSensor();
		// TODO Auto-generated constructor stub
		moteur = new OurMotor();
		
		s= new TouchSensor(SensorPort.S3);
		etat=PALETNONTROUVE;
		boussole = new Boussole();

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
		moteur.setSpeed(moteur.DEFAULT_SPEED);
		moteur.setAcceleration(moteur.DEFAULT_ACCELERATION);
		touch=false;

	}


	/**
	 * Methode permetant au robot de se tourner de
	 * @param degre
	 */
	public void seTourner(double degre) {
		moteur.seTourner(degre,false);
		boussole.majBoussole();
	}


	/**
	 * Fonction permettant de trouver un palet et d'orienter Jarvis vers ce dernier. 
	 * Pour ce faire, elle fait réaliser à Jarvis un tour sur lui même en cherchant les cassures dans les distances des objets autour de lui.
	 * Si Jarvis repère une cassure, c'est qu'il a repéré soit un palet soit le robot adverse(cas à traiter)
	 * elle met à jour l'état de jarvis lorsqu'elle trouve un palet 
	 */
	public void checkNearestPalet() {
		moteur.setSpeed(100);
		moteur.setAcceleration(100);
		float val1;
		float val2;							
		moteur.seTourner(360,true);
		while(true) {
			val1 = moteur.getUltraSon().getDist();
			try {								
				Thread.sleep(5);												
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			val2 = moteur.getUltraSon().getDist();
			float f = Float.POSITIVE_INFINITY;
			if(Math.abs(val1 - val2) > 0.20 && val1!=f && val2 != f) {
				etat = PALETTROUVE;	
				boussole.majBoussole();
				moteur.getRightMotor().stop(true);
				moteur.getLeftMotor().stop();
				boussole.majBoussole();
				break;
			}
		}
		moteur.setAcceleration(moteur.DEFAULT_ACCELERATION);
		moteur.setSpeed(moteur.DEFAULT_SPEED);	
	}

	/**
	 * méthode permettant à Jarvis d'aller atttraper un palet après l'avoir repéré et s'être orienter dans sa direction grâce à checkNearestPalet
	 * Elle met à jour l'état de Jarvis lorsqu'elle a attrappé le palet
	 * @param dist : distance séparant Jarvis du palet repéré
	 *
	 */
	public void attrapePalet(float dist) {
		moteur.forward(dist+0.05,true);
		while(moteur.getLeftMotor().isMoving()) {
			this.moteur.openClaw(true);
			if(this.moteur.getUltraSon().getDist()<0.10) {
				etat = PALETNONTROUVE;
				moteur.getRightMotor().stop(true);
				moteur.getLeftMotor().stop();
				break;
			}
		}
		if(touch==true) {
			this.moteur.closeClaw(false); 
			etat = PALET;
		}
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
	 * Methode permettant de mettre Jarvis en recherche
	 */
	
	public OurMotor getPilote() {
		return this.moteur;
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
		
		etat=PALETNONTROUVE;
	}



	/**
	 * Méthode représentant un automate simple de la partie.
	 * 
	 */

	public void partieSimple() {
		moteur.getLeftMotor().resetTachoCount();
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