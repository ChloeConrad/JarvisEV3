package vision;

import java.io.File;
import lejos.hardware.Sound;

/**
 * @deprecated
 * @author JarvisTeam
 *
 */
public class SonTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sound.getVolume();
		File test=new File("/Users/33645/Downloads/Test.wav");
		Sound.playSample(test, 50);
	}
}


