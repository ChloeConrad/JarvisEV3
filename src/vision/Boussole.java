package vision;

/**
 * Classe boussole permettant de mettre à jour et connaitre la direction du robot
 * @author JarvisTeam
 *
 */
public class Boussole {
	
	private double ourAngle;
	private final static double enButDirection = 0;
	private final static int value360 = 780; //nombre de rotations effectu�s lors d'une roation d'un angle de 360�
	
	public Boussole() {
		ourAngle = 0;
	}
	/**
	 *
	 * @return l'angle en base 360 d'orientation de jarvis pas rapport � l'axe des ordonn�es.
	 */
	public double getOurAngle() {
		return ourAngle;
	}
	
	/**
	 * Convertit les nombres de rotations en degres. 
	 * @param rotation Le nombre de rotation que l'on veut convertir
	 * @return le r�sultat de la conversion en degr�s  
	 */
	public int rotationToDegree(double rotation) {
		return (int)((rotation*360/value360)%360);
	}
	/**
	 * permet de d�fir l'attribut ourAngle
	 * @param a est la valeur que l'on veut donner � l'attribut ourAngle
	 */
	public void setOurAngle(double a) {
		ourAngle = a;
	}
	/**
	 * Permet de mettre � jour la valeur de ourAngle en r�cuperant la valeur du tachometre du moteur gauche de Jarvis
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
