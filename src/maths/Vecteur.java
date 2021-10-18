package maths;

/**
 * Segment permettant au robot de se donner une direction dans l'espace
 * depuis un point de d�part et un point d'arriv�
 */
public class Vecteur {


	/**
	 * Le point de d�part du vecteur
	 */
	private Point pDepart;

	/**
	 * Le point d'arriv� du vecteur
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
	 * Constructeur par d�faut Vecteur <br/>
	 * A la construction d'un Vecteur par d�faut, ce dernier est nul
	 * 
	 * @see Point 
	 */
	public Vecteur() {
		this(new Point(0,0),new Point(0,0));

	}

	/**
	 * Constructeur Vecteur � partir de deux points
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
	 * Constructeur Vecteur � partir d'un point d'arriv�e
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
	 * Retourne le point de d�part 
	 * 
	 * @return le point de d�part
	 */
	public Point getpDepart() {
		return pDepart;
	}


	/**
	 * Met � jour le point de d�part
	 * 
	 * @param pDepart
	 * 			le nouveau point de d�part
	 * 
	 */
	public void setpDepart(Point pDepart) {
		this.pDepart = pDepart;
	}

	/**
	 * Retourne le point de final
	 * 
	 * @return le point final 
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
	 * Calcul la norme du vecteur, d�fini par les coordonn�es (pDepart, pFinal)
	 * 
	 * @see Point#distance
	 */
	public double normeVecteur() {
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
