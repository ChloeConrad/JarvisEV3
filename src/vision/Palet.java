package vision;

import maths.Point;

/**
 * Gestion d'un palet, de ses coordonnées, de sa probabilité d'être à un endroit précis.
 * @author JarvisTeam
 * @deprecated Car en utilisant la caméra du prof on a pas vraiment besoin de probabilité.
 *	
 */
public class Palet {
	
	Point coordonnee;
	double probaPresence;
	
	/**
	 * Initialise un palet avec une probabilité d'être au bon endroit maximale et aux coordonnées
	 * @param coords 
	 */
	public Palet(Point coords) {
		coordonnee=coords;
		probaPresence=1;
	}
	/**
	 * Renvoie le Point indiquant les coordonnees du palet
	 * @return coordonnee
	 */
	public Point getCoordonnee() {
		return coordonnee;
	}
	
	/**
	 * Met à jours les
	 * @param coordonnee du palet
	 */
	public void setCoordonnee(Point coordonnee) {
		this.coordonnee = coordonnee;
	}
	
	/**
	 * Renvoie un double representant la probabilité du palet d'être à son point de départ
	 * @return probaPresence la probabilité du palet d'être à ses coordonnées, entre 0 et 1.
	 */
	public double getProbaPresence() {
		return probaPresence;
	}

	/**
	 * Met à jour la probabilité de palet de se trouver aux coordonnées coordonnee.
	 * @param probaPresence la probabilité, entre 0 et 1.
	 */
	public void setProbaPresence(double probaPresence) {
		this.probaPresence = probaPresence;
	}

}
