package vision;

import lejos.hardware.Button;
import lejos.hardware.Power;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import sensors.*;

public class Main {

	public static void main(String[] args) {
		Jarvis j = new Jarvis();
		float[] values = j.regarderAutour();
		int m = j.trouverMinimum(values);
		
		int[] cassures = j.trouverCassures(values);
		for(int i=0; i<cassures.length;i++)
			j.getPilote().stopMessage(""+cassures[i]+" - "+(float)(360.0/values.length)*cassures[i]);
	}
	
	
}
