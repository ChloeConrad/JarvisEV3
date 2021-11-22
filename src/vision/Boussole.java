package vision;

public class Boussole {
	private double ourAngle;
	private final static double enButDirection = 0;
	private final static int value360 = 780; //nombre de rotations effectués lors d'une roation d'un angle de 360°
	
	public Boussole() {
		ourAngle = 0;
	}
	
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
	
	public void setOurAngle(double a) {
		ourAngle = a;
	}

	public void majBoussole() {
		int a = rotationToDegree(OurMotor.getLeftMotor().getTachoCount());
		ourAngle = (ourAngle+a)%360;
	}
}
