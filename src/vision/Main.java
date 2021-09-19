package vision;

import lejos.robotics.SampleProvider;

public class Main {

	public static void main(String[] args) {
		//r = new Robot();
		//r.stopMessage("Dist : " + r.getDist());
		//simpleGetPallet();
		UltraSonicSensor US = new UltraSonicSensor(SensorPort.S1);
		OurMotor m = new OurMotor();
		m.surrondings(US);
		/*m.ClockRotate(360);
		
		for(int i=0; i<20; i++) {
			System.out.println("Dist : "+US.getDist());
			Button.waitForAnyPress();
		}*/
		Button.waitForAnyPress();
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
