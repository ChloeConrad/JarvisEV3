package maths;

public class Vecteur {
	/**
	 * Segment permettant au robot de se donner une direction dans l'espace
	 * depuis un point de départ et un point d'arrivé
	 */

	/**
	 * Le point de départ du vecteur
	 */
	private Point pDepart;
	
	/**
	 * Le point d'arrivé du vecteur
	 */
	private Point pFinal;
	
	/**
	 * Composante horizontale du vecteur
	 */
	private double u1;
	
	/**
	 * Composante vertical du vecteur 
	 */
	private double u2;
	
	/**
	 * Constructeur par défaut Vecteur
	 * <p>
	 * A la construction d'un Vecteur par défaut, ce dernier est nul
	 * 
	 * @see Point 
	 */
	public Vecteur() {
		this(new Point(0,0),new Point(0,0));
		
	}
	
	/**
	 * Constructeur Vecteur à partir de deux points
	 * <p>
	 * 
	 * 
	 * @param p
	 * 			Le premier point du Vecteur
	 * 
	 * @param p0
	 * 			Le second point du vecteur 
	 * 
	 * @see Vecteur#pDepart
	 * @see Vecteur#pFinal
	 * @see Vecteur#u1
	 * @see Vecteur#u2
	 */
	public Vecteur(Point p, Point p0) {
		pDepart = new Point(p);
		pFinal = new Point(p0);
		u1 = Math.abs(p0.getX()-p.getX());
		u2 = Math.abs(p0.getY()-p.getY());
		
		
	}
	
	/**
	 * Constructeur Vecteur à partir d'un point d'arrivée
	 * <p>
	 * 
	 * @param p
	 * 			Le premier point du vecteur 
	 * 
	 * 
	 */
	public Vecteur(Point p) {
		this(new Point(0,0),p);
		
		
	}
	
	/**
	 * 
	 */
	public Point getpDepart() {
		return pDepart;
	}

	
	/**
	 * 
	 */
	public void setpDepart(Point pDepart) {
		this.pDepart = pDepart;
	}

	/**
	 * 
	 */
	public Point getpFinal() {
		return pFinal;
	}

	/**
	 * 
	 */
	public void setpFinal(Point pFinal) {
		this.pFinal = pFinal;
	}

	/**
	 * 
	 */
	public double getU1() {
		return u1;
	}

	/**
	 * 
	 */
	public void setU1(double u1) {
		this.u1 = u1;
	}

	/**
	 * 
	 */
	public double getU2() {
		return u2;
	}

	/**
	 * 
	 */
	public void setU2(double u2) {
		this.u2 = u2;
	}
	
	/**
	 * 
	 */
	public double calculTheta() {
		double theta = Math.atan2(u2, u1);
		return theta;
	}
	
	/**
	 * 
	 */
	public double taille() {
		double t = pDepart.distance(pFinal);
		return t;
	}
	
	/**
	 * Calcul la norme du vecteur, défini par les coordonnées (pDepart, pFinal)
	 * 
	 * @see {@link Point#distance}
	 */
	public double normeVecteur() {// calcul norme d'un vecteur
        return Math.abs(pDepart.distance(pFinal));
    }

	/**
	 * 
	 */
    public double scalaireVecteur(Vecteur V) {//calcul produit scalaire entre ce vecteur et un vecteur v
        return this.u1*V.u1+this.u2*V.u2;
    }

    /**
     * 
     */
    public double angleDeuxVecteurs(Vecteur V) {//calcul d'angle entre ce vecteur et un vecteur v
        return Math.acos(this.scalaireVecteur(V)/(this.normeVecteur()*V.normeVecteur()));
    }

}
