package vision.avancee;

import lejos.hardware.Button;

public class PerceptionThread extends Thread {
	protected Jarvis j;
	public PerceptionThread(Jarvis j) {
		this.j=j;
		
	}
	
	public void run() {
	
	while(!j.getState().boutonPresse) {
		
		j.getState().majPalets();
		j.getState().majState();
	}
	
	}
}
