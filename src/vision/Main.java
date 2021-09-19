package vision;

import lejos.robotics.SampleProvider;

public class Main {

	public static void main(String[] args) {
		Robot r = new Robot();
		//r.stopMessage("Dist : " + r.getDist());
		simpleGetPallet(r);
		

	}
	
	public static void simpleGetPallet(Robot r) {
		// Attention ! Le sens des pallets compte, la partie arrondie doit etre en haut!
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
