package vision;

public class Boussole {
	
	private double ourAngle;
	private final static double enButDirection = 0;
	private final static int value360 = 780; //nombre de rotations effectués lors d'une roation d'un angle de 360°
	
	public Boussole() {
		ourAngle = 0;
	}
	/**
	 *
	 * @return l'angle en base 360 d'orientation de jarvis pas rapport à l'axe des ordonnées.
	 */
	public double getOurAngle() {
		return ourAngle;
	}
	
	/**
	 * Convertit les nombres de ratations en degres. 
	 * @param rotation Le nombre de rotation que l'on veut convertir
	 * @return le résultat de la conversion en degrés  
	 */
	public int rotationToDegree(double rotation) {
		return (int)((rotation*360/value360)%360);
	}
	/**
	 * permet de défir l'attribut ourAngle
	 * @param a est la valeur que l'on veut donner à l'attribut ourAngle
	 */
	public void setOurAngle(double a) {
		ourAngle = a;
	}
	/**
	 * Permet de mettre à jour la valeur de ourAngle en récuperant la valeur du tachometre du moteur gauche de Jarvis
	 */
	public void majBoussole() {
		int a = rotationToDegree(OurMotor.getLeftMotor().getTachoCount()); 
		
		if(a+ourAngle<0) {
			ourAngle = ourAngle+a+360;
		}
		else ourAngle = (ourAngle+a)%360;
		OurMotor.getLeftMotor().resetTachoCount();
	}
}
