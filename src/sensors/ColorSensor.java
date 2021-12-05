package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

/**
 * ColorSensor est la classe gérant le capteur couleur de la brique EV3.
 * Il permet notament de retourner une couleur perçu.
 */
public class ColorSensor {

	/**
	 * Le capteur couleur dont dépend les données recueilli par le robot. Par défaut, il est affecté au capteur S2, celui de notre robot.
	 * @see ColorSensor#ColorSensor()
	 * @see ColorSensor#ColorSensor(port)
	 */
	private static EV3ColorSensor colorSensor;
	private SampleProvider CSamp;


	/**
	 * Le tableau stockant les échantillons de mesures faites par le capteur couleur.
	 */
	float[] sampleC;


	/**
	 * Constructeur de ColorSensor. Assigne le port rentré au mesure du capteur couleur. 
	 * @param  
	 * @param port auquel le capteur couleur est branché.
	 */
	public ColorSensor() {
		colorSensor = new EV3ColorSensor(SensorPort.S4);
		colorSensor.setFloodlight(true);
		colorSensor.setFloodlight(6);
		CSamp=colorSensor.getRGBMode();
	}

	
	
	/**
	 * Retourne le capteur couleur de la classe. 
	 * @return colorSensor (capteur couleur affilié) 
	 */
	public EV3ColorSensor getSensor() {
		return colorSensor;
	}
	/**
	 * Rettourne la couleur lu par le capteur couleur sous forme de string
	 * 
	 */
	public String getColorID() {
		return couleur(colorSensor.getColorID());
	}
	
	
	/**
	 * Convertit le code couleur en chaîne de caractère
	 * @param color
	 * @return une chaîne de caractère contenant le nom de la couleur (en minuscule), "none" si la couleur ne correspond pas à celles existantes. 
	 */
	public String couleur(int color) {
		switch(color) {

		case Color.NONE:return "none";

		case Color.BLACK:return "noir";

		case Color.BLUE:return "bleu";
		
		case Color.BROWN:return "jaune";

		case Color.CYAN:return "bleu";

		case Color.DARK_GRAY:return "gris";

		case Color.GRAY:return "gris";
		
		case Color.GREEN: return "vert";

		case Color.LIGHT_GRAY:return "gris";

		case Color.MAGENTA:return "rouge";

		case Color.ORANGE:return "orange";

		case Color.PINK:return "rose";

		case Color.RED:return "rouge";

		case Color.WHITE:return "blanc";

		case Color.YELLOW:return "jaune";
		}
		return"";
	}
	public boolean getRGB() {
		
		float[] sample = new float[CSamp.sampleSize()] ;
		CSamp.fetchSample(sample, 0);
		if ((sample[0]>0.255f) && (sample[0]<0.265f) && (sample[1]>0.260f) && (sample[1]<0.270f) && (sample[2]>0.195f) && (sample[2]<0.205f))return true;
		else return false;
	}
}