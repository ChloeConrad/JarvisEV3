package vision;

import lejos.hardware.Button;

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
