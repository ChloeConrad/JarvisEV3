package vision;

import maths.Point;

public class Palet {
	
	Point coordonnee;
	double probaPresence;
	
	public Palet(Point x) {
		coordonnee=x;
		probaPresence=1;// TODO Auto-generated constructor stub
	}

	public Point getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(Point coordonnee) {
		this.coordonnee = coordonnee;
	}

	public double getProbaPresence() {
		return probaPresence;
	}

	public void setProbaPresence(double probaPresence) {
		this.probaPresence = probaPresence;
	}

}
