package maths;

/**
 * <b> Point est la classe représentant un emplacement dans l'espace à partir de son abscisse et de son ordonnée.</b> <br/>
 * 
 * Un point est caractérisé par les informations suivantes : 
 * <ul>
 * <li> un abscisse, coordonnée horizontale du point
 * <li> un ordonné, coordonnée verticale du point 
 * </ul> 
 *
 */
public class Point {
	
	/**
	 * L'abscisse du point
	 */
	private double x;
	
	/**
	 * L'ordonnee du point
	 */
	private double y;
	
	/**
	 * Constructeur par défaut de Point
	 * 
	 * A la construction du point, l'abscisse et l'ordonnee sont considérés à l'origine
	 * 
	 * @see Point#x
	 * @see Point#y
	 */
	public Point() {
		this.x=0;
		this.y=0;
		
	}
	
	/**
	 * Constructeur de Point à partir de deux doubles (abscisse et ordonnee)
	 * 
	 * @param x
	 * 			L'abscisse du Point
	 * 
	 * @param y
	 * 			L'ordonnee du Point
	 * 
	 * @see Point#x
	 * @see Point#y
	 */
	public Point(double x,double y) {
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Constructeur de Point à partir d'un autre Point
	 * Le point construit prent les mêmes abscisse et ordonnee que ceux du Point passé en paramètre
	 * 
	 * @param p
	 * 			Un Point
	 * 
	 * @see Point#x
	 * @see Point#y
	 */
	public Point (Point p) {
		this.x=p.x;
		this.y=p.y;
	}
	
	/**
	 * Retourne l'abscisse du Point sous forme de double 
	 * 
	 * @return la coordonne x (abscisse) du Point
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Met à jour l'abscisse du Point
	 * 
	 * @param x
	 * 			Le nouvel abscisse du Point
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Retourne l'ordonnee du Point sous forme de double
	 * 
	 * @return la coordonne y (ordonnee) du Point
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Met à jour l'ordonnee du Point
	 * 
	 * @param y
	 * 			Le nouvel ordonnee du Point
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Calcul la distance entre ce Point et un Point placé en paramètre
	 * 
	 * @return un nombre réel (distance)
	 */
	public double distance(Point p) {
		double d;
		d= Math.sqrt((this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y));
		System.out.println(d);
		return d;
	}
}
