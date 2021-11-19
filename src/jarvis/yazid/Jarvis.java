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
	
	public Jarvis(int idPos){
		try {
			state= new Etat(idPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pilote= new OurPilote(null);
	}
	
	public void agissements(){
		
	}
	

}
