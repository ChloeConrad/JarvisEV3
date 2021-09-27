package vision;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import sensors.*;

public class Main {

	public static void main(String[] args) {
		//r = new Robot();
		//r.stopMessage("Dist : " + r.getDist());
		//simpleGetPallet();
		//OurMotor m = new OurMotor();
		//System.out.println("dist infinite = "+US.getDist()+"\n max float value = "+Float.MAX_VALUE);
		//m.surrondings();
		//m.surrondings();
		Jarvis j = new Jarvis();
		j.setPositions();
		Button.waitForAnyPress();
		j.premierBut();
		
		Button.waitForAnyPress();
		
	}
	
	public static void simpleGetPallet(Robot r) {
		r.openClaw();
		r.moveToObstacle(0, true);
		while(r.pilot.isMoving()) {
			if(r.getTouch()>0) {
				r.pilot.stop();
			}
		}
		r.closeClaw();
		r.pilot.rotate(180);
		r.moveToObstacle(0.2);
		r.pilot.rotate(180);
	}
	
	public static float[] location(Robot r) {
		float[] t = new float[18];
		float[] distWall = new float[4];
		t = r.scan360();
		int wallNumber = 0;
		for(int i=1;i<17;i++) {
			if(t[i-1]>t[i]&&t[i+1]>t[i]) {
				distWall[wallNumber] = t[i];
				wallNumber+=1;
			}
		}
		if(wallNumber == 3) {
			return distWall;
		}
		else {
			distWall[2]=t[18];
			distWall[3]=t[0];	
			return distWall;
		}
				
	}
}
