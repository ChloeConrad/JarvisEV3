package maths;

/**
 * <b>Vecteur est la classe repr�sentant un segment dans l'espace, cens� donner son orientation au robot.</b> <br/>
 * Un vecteur est caract�ris� par les informations suivantes : 
 * 
 * <ul>
 * <li> un point de d�part
 * <li> un point d'arriv�
 * <li> une composante horizontale 
 * <li> une composante verticale 
 * </ul>
 * 
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
	 * @return le point de d�part du vecteur
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
	 * Retourne le point final
	 * 
	 * @return le point final du vecteur
	 */
	public Point getpFinal() {
		return pFinal;
	}

	/**
	 * Met � jour le point final
	 * 
	 * @param pFinal
	 * 			le nouveau point final
	 */
	public void setpFinal(Point pFinal) {
		this.pFinal = pFinal;
	}

	/**
	 * Retourne la composante horizontale
	 * 
	 * @return la composante horizontale du vecteur 
	 */
	public double getU1() {
		return u1;
	}

	/**
	 * Met � jour la composante horizontale
	 * 
	 * @param u1
	 * 			La nouvelle composante horizontale 
	 */
	public void setU1(double u1) {
		this.u1 = u1;
	}

	/**
	 * Retourne la composante verticale
	 * 
	 * @return la composante verticale du vecteur 
	 */
	public double getU2() {
		return u2;
	}

	/**
	 * Met � jour la composante vertical
	 * 
	 * @param u2
	 * 			La nouvelle composante verticale 
	 */
	public void setU2(double u2) {
		this.u2 = u2;
	}

	/**
	 * Calcul Theta � partir des composantes verticale et horizontale du vecteur
	 * 
	 * @return un nombre r�el (th�ta)
	 * @see u1
	 * @see u2
	 */
	public double calculTheta() {
		double theta = Math.atan2(u2, u1);
		return theta;
	}

	/**
	 * Calcul la taille du vecteur
	 * 
	 * @see Point#distance
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
	 * Calcul le produit scalaire entre ce vecteur et un vecteur V plac� en param�tre
	 * 
	 * @return un nombre r�el (scalaire)
	 */
	public double scalaireVecteur(Vecteur V) {
		return this.u1*V.u1+this.u2*V.u2;
	}

	/**
	 * Calcul l'angle entre ce vecteur et un vecteur V plac� en param�tre
	 * 
	 * @return un nombre r�el (angle)
	 */
	public double angleDeuxVecteurs(Vecteur V) {
		return Math.acos(this.scalaireVecteur(V)/(this.normeVecteur()*V.normeVecteur()));
	}

}
