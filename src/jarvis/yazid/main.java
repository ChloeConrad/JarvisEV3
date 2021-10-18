package jarvis.yazid;

import lejos.hardware.Button;
import vision.Jarvis;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jarvis jarvis=new Jarvis();
		PerceptionThread perception=new PerceptionThread(jarvis);
		jarvis.setPositions();
		perception.start();
		jarvis.premierBut();
		Button.waitForAnyPress();
		
		
	}

}
