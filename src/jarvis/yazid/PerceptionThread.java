package jarvis.yazid;

import vision.Jarvis;

public class PerceptionThread extends Thread {
	protected Jarvis j;
	public PerceptionThread(Jarvis j) {
		this.j=j;
		
	}
	
	public void run() {
	
	System.out.println("Je suis un con!");
	}
}
