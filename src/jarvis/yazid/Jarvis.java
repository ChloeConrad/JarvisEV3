package jarvis.yazid;

import java.io.IOException;

public class Jarvis {
	private Etat state;
	private OurPilote pilote;
	
	Jarvis(int idPos){
		try {
			state= new Etat(idPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pilote= new OurPilote(null);
	}
	
	public void agissements(int etat){
		
	}

}
