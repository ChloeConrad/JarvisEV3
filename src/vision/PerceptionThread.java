package vision;

import lejos.hardware.Button;
/**
 * Classe permettant de détecter un contact avec un palet à tout moment dans la partie
 * @author JarvisTeam
 *
 */
public class PerceptionThread extends Thread {
	protected Jarvis j;
	public PerceptionThread(Jarvis j) {
		this.j=j;
		
	}
	
	public void run() {
	
	while(true) {
		
		
		if(j.s.getTouch()==1) j.touch=true;
		System.out.println(j.touch);
	}
	
	}
}
