package jarvis.yazid;

import java.io.IOException;

import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;

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
		// TODO Auto-generated method stub
		
	}

	private void vasMarquer() {
		// TODO Auto-generated method stub
		
	}

	private void attrapePalet() {
		navigation.clearPath();
		navigation.addWaypoint(state.getCible());
		navigation.followPath();
		
		// TODO Auto-generated method stub
		
	}

	private void recherchePalet() {
		
		// TODO Auto-generated method stub
		
	}

	private void premierBut() {
		// TODO Auto-generated method stub
		
	}
	

}
