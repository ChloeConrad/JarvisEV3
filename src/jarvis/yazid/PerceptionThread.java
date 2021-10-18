package jarvis.yazid;

import vision.Jarvis;

public class PerceptionThread extends Thread {
	protected Jarvis j;
	public PerceptionThread(Jarvis j) {
		this.j=j;
		
	}
	
	public void run() {
	int i = 0;
	while(i<10) {
		System.out.println("hello !");
		i++;
	}
	}
}
