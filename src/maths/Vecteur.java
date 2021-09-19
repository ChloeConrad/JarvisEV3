package maths;

public class Vecteur {

	private Point pDepart;
	private Point pFinal;
	private double u1;
	private double u2;
	
	public Vecteur() {
		this(new Point(0,0),new Point(0,0));
		
	}
	
	public Vecteur(Point p, Point p0) {
		pDepart = new Point(p);
		pFinal = new Point(p0);
		u1 = Math.abs(p0.getX()-p.getX());
		u2 = Math.abs(p0.getY()-p.getY());
		
		
	}
	
	public Vecteur(Point p) {
		this(new Point(0,0),p);
		
		
	}
	
	public Point getpDepart() {
		return pDepart;
	}

	public void setpDepart(Point pDepart) {
		this.pDepart = pDepart;
	}

	public Point getpFinal() {
		return pFinal;
	}

	public void setpFinal(Point pFinal) {
		this.pFinal = pFinal;
	}

	public double getU1() {
		return u1;
	}

	public void setU1(double u1) {
		this.u1 = u1;
	}

	public double getU2() {
		return u2;
	}

	public void setU2(double u2) {
		this.u2 = u2;
	}
	
	
	public double calculTheta() {
		double theta = Math.atan2(u2, u1);
		return theta;
	}
	public double taille() {
		double t = pDepart.distance(pFinal);
		return t;
	}
	
	
	public double normeVecteur() {// calcul norme d'un vecteur
        return Math.abs(pDepart.distance(pFinal));
    }

    public double scalaireVecteur(Vecteur V) {//calcul produit scalaire entre ce vecteur et un vecteur v
        return this.u1*V.u1+this.u2*V.u2;
    }

    public double angleDeuxVecteurs(Vecteur V) {//calcul d'angle entre ce vecteur et un vecteur v
        return Math.acos(this.scalaireVecteur(V)/(this.normeVecteur()*V.normeVecteur()));
    }

}
