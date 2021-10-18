package maths;

/**
 * <b> Point est la classe repr�sentant un emplacement dans l'espace � partir de son abscisse et de son ordonn�e.</b> <br/>
 * 
 * Un point est caract�ris� par les informations suivantes : 
 * <ul>
 * <li> un abscisse, coordonn�e horizontale du point
 * <li> un ordonn�, coordonn�e verticale du point 
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
	 * Constructeur par d�faut de Point
	 * 
	 * A la construction du point, l'abscisse et l'ordonnee sont consid�r�s � l'origine
	 * 
	 * @see Point#x
	 * @see Point#y
	 */
	public Point() {
		this.x=0;
		this.y=0;
		
	}
	
	/**
	 * Constructeur de Point � partir de deux doubles (abscisse et ordonnee)
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
	 * Constructeur de Point � partir d'un autre Point
	 * Le point construit prent les m�mes abscisse et ordonnee que ceux du Point pass� en param�tre
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
	 * Met � jour l'abscisse du Point
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
	 * Met � jour l'ordonnee du Point
	 * 
	 * @param y
	 * 			Le nouvel ordonnee du Point
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Calcul la distance entre ce Point et un Point plac� en param�tre
	 * 
	 * @return un nombre r�el (distance)
	 */
	public double distance(Point p) {
		double d;
		d= Math.sqrt((this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y));
		System.out.println(d);
		return d;
	}
}
