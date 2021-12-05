package vision.avancee;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import maths.Point;
import vision.OurMotor;
/**
 * Classe principale de Jarvis avancée
 * @author JarvisTeam
 *
 */
public class Jarvis {
	/**
	 * Instance de la classe état permetant à Jarvis de mettre à jour son état interne
	 */
	private Etat state;
	/**
	 * Instance de WheeledChassis permettat d'instancier un Pilote de lejos
	 */
	private WheeledChassis chassis;
	/**
	 * Instance de OurPilote classe heritant de MovePilote de la bibliotheque lejos qui implemente un moveprovider ce qui permet au robot de mettre à jour sa position
	 */
	private OurPilote pilote;
	/**
	 * Instance d'un navigator de la bibliotheque lejos ce qui permet au robot d'utiliser les méthodes GoTo() et Path() à l'aide de waypoints
	 */
	private Navigator navigation;
	/**
	 * Entier symbolisant la position initiale de Jarvis 
	 */
	private int positionInit;
	/**
	 * Constructeur de Jarvis faisant appel au parametre
	 * @param idPos
	 * Qui permet d'initialiser la position initiale de Jarvis et qui instancie tous les attributs de ce dernier
	 */
	public Jarvis(int idPos){
		try {
			state= new Etat(idPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		positionInit=idPos;
		chassis=new WheeledChassis(null, idPos);
		chassis.getPoseProvider().setPose(state.getPosition());
		pilote = new OurPilote(chassis);
		navigation= new Navigator(pilote);
	}
	/**
	 * Methode deteminant la prochaine action du robot en fonction de son état interne
	 */
	public void agissements(){
		switch (state.getState()){
		case 0:
			premierBut();
		case 1:
			recherchePalet();
		case 2:
			attrapePalet();
		case 3:
			vasMarquer();
		case 4:
			reset();
		case -1:
			lejos.utility.Delay.msDelay(150);
		case -2:
			lejos.utility.Delay.msDelay(150);
			if(Button.waitForAnyPress()!=0) state.state=1;
		
		}
	}
	/**
	 * Methode incomplete permettant de remettre l'état interne du robot, elle devrait aussi pouvoir re calculer sa position actuelle pour corriger sa representation interne
	 */
	private void reset() {
		state.setState(1);
		
	}
	/**
	 * Methode permettant à jarvis d'aller marquer dans le bon But en fonction de sa position actuelle et de sa position initial.
	 */
	private void vasMarquer() {
	if (positionInit==4 || positionInit==8 ||positionInit==10) {
	 navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),270*100/9));
	 pilote.ouvrirPinces(true);
	 navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()-100));
	 pilote.rotate(180);
	 pilote.fermerPinces(false);
		}
	else {
		navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),30*100/9));
		 pilote.ouvrirPinces(true);
		 navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()-100));
		 pilote.rotate(180);
		 pilote.fermerPinces(false);
	}
	}
	/**
	 * Methode utilisant l'attribut cible de l'état de Jarvis qui lui permet d'aller chercher le palet le plus proche de sa position actuelle
	 */
	public void attrapePalet() {
		navigation.clearPath();
		navigation.addWaypoint(state.getCible());
		pilote.ouvrirPinces(true);
		navigation.followPath();
		if(state.isPalet()) {
			pilote.fermerPinces(true);
			state.setState(3);
		}
		
		
		
	}
/**
 * Methode utilisant les données de la caméra infrarouge permettant de determiner quel palet est le plus proche de Jarvis et de stocker sa position dans l'attribut cible de l'instance de la classe état 
 */
	public void recherchePalet() {
		Waypoint cible=state.getPalets(0);
		 for (int i =1;i<state.getPalets().length;i++) {
			 double j=new Point(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()).distance(new Point(state.getPalets(i).getX(),state.getPalets(i).getY()));
			 double k= new Point(cible.getX(),cible.getY()).distance(new Point(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()));
			 if (j<k) {
				 cible=state.getPalets(i);
				 
			 }
		 }
		 state.setCible(cible);
		 state.setState(2);
				
	}
/**
 * Algo simple pour mettre le premier but, il utilise l'attribut de notre position initiale pour faire son trajet de maniere deterministe
 */
	private void premierBut() {
	
		if (positionInit==4 || positionInit==8 ||positionInit==10) {
			pilote.ouvrirPinces(true);
			navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),90*100/9));
			pilote.fermerPinces(true);
			navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX()+200,270*100/9));
			pilote.ouvrirPinces(true);
			navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()-100));
			pilote.rotate(180);
		}
		else {
			pilote.ouvrirPinces(true);
			navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),210*100/9));
			pilote.fermerPinces(true);
			navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX()+200,30*100/9));
			pilote.ouvrirPinces(true);
			navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()-100));
			pilote.rotate(180);
		}
		state.setState(4);
			
			
		
	}
	/**
	 * Getter de State
	 * @return State
	 */
	public Etat getState() {
		return state;
	}

	public void setState(Etat state) {
		this.state = state;
	}

	public WheeledChassis getChassis() {
		return chassis;
	}

	public void setChassis(WheeledChassis chassis) {
		this.chassis = chassis;
	}

	public OurPilote getPilote() {
		return pilote;
	}

	public void setPilote(OurPilote pilote) {
		this.pilote = pilote;
	}

	public Navigator getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigator navigation) {
		this.navigation = navigation;
	}

	public int getPositionInit() {
		return positionInit;
	}

	public void setPositionInit(int positionInit) {
		this.positionInit = positionInit;
	}
	

}
