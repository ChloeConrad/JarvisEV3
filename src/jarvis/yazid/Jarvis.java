package jarvis.yazid;

import java.io.IOException;

import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import maths.Point;

public class Jarvis {
	private Etat state;
	//private OurPilote pilote;
	private WheeledChassis chassis;
	private MovePilot pilote;
	private Navigator navigation;
	
	public Jarvis(int idPos){
		try {
			state= new Etat(idPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chassis=new WheeledChassis(null, idPos);
		chassis.getPoseProvider().setPose(state.getPosition());
		pilote = new MovePilot(chassis);
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
			reset();
		
		}
	}

	private void reset() {
		
		
	}

	private void vasMarquer() {
		// TODO Auto-generated method stub
		
	}

	public void attrapePalet() {
		navigation.clearPath();
		navigation.addWaypoint(state.getCible());
		navigation.followPath();
		
		// TODO Auto-generated method stub
		
	}

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
		// TODO Auto-generated method stub
		
	}
	

}
