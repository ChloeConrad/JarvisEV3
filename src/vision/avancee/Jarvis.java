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

public class Jarvis {
	private Etat state;
	
	private WheeledChassis chassis;
	private OurPilote pilote;
	private Navigator navigation;
	private int positionInit;
	
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

	private void reset() {
		state.setState(1);
		
	}

	private void vasMarquer() {
	if (positionInit==4 || positionInit==8 ||positionInit==10) {
	 navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),270*100/9));
	 pilote.ouvrirPinces(true);
	 navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()-100));
	 pilote.rotate(180);
		}
	else {
		navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),30*100/9));
		 pilote.ouvrirPinces(true);
		 navigation.goTo(new Waypoint(chassis.getPoseProvider().getPose().getX(),chassis.getPoseProvider().getPose().getY()-100));
		 pilote.rotate(180);
	}
	}

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
 * 
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
