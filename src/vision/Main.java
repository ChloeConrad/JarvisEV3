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
		//j.identifyNearest();
<<<<<<< HEAD
		//j.getPilote().curveTry1(500, 0.75, 500, false);
		//j.getPilote().curveTry1(500, 1.1, 3500, false);
		OurMotor.forward(0.60);
		// 1 - 0,476;
		// 2 - 0,468
		//OurMotor.forward(1000);
=======
		//j.getPilote().curveTry1(500, 0.75, 2000, false);
>>>>>>> d2b826e91f65a4ab09d3476ab53e27ed751694ab
		//m.stopMessage("That's a stop !");
		//System.out.println("I did "+m.howManyDist(US)+" measures of distance");
		/*m.ClockRotate(360);
		
		for(int i=0; i<20; i++) {
			System.out.println("Dist : "+US.getDist());
			Button.waitForAnyPress();
		}*/
		j.setPositions();
		
		
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
