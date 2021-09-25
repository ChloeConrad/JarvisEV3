package vision;

public class Test {

	public static float[] location(float[] t1) {
		float[] t = new float[18];
		float[] distWall = new float[4];
		t = t1;
		int wallNumber = 0;
		for(int i=1;i<17;i++) {
			if(t[i-1]>t[i]&&t[i+1]>t[i]) {
				distWall[wallNumber] = t[i];
				wallNumber+=1;
			}
		}
		if(wallNumber == 3) {
			System.out.println(t);	
			return t;
		}
		else {
			distWall[2]=t[18];
			distWall[3]=t[0];
			System.out.println(t);	
			return t;
		}
		
	}
	

	public static void main(String[] args) {
		//float[] t = new float[]{4,2,1,3,4,3,1,2,3,4,2,1,2,3,4,2,1,3};
		//location(t);
		
		//Test of the curve in a non-lejos environnement
		int speedFirstWheel = 100;
		double multiplicatorSecondWheel = 0.2;
		int rotation = 1000;
		System.out.println("rightMotor.setSpeed("+(int) (speedFirstWheel*multiplicatorSecondWheel)+");");
		//double durationCorrector = 1/multiplicatorSecondWheel;
		System.out.println("leftMotor.rotate("+(int) (rotation)+", true);"+"\nrightMotor.rotate("+(int)(rotation*multiplicatorSecondWheel)+", boolCont);");
	}

}
