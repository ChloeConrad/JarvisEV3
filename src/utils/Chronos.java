package utils;

/**
 * Gestion du temps afin de comparer des temps d'executions de methodes.
 * @author JarvisTeam
 *
 */
public class Chronos {
	long start = System.currentTimeMillis();
	long end = System.currentTimeMillis();
	/**
	 * @return la durée entre start et end, en millisecondes
	 */
	public long getDuration() {
		return end-start;
	}
	/**
	 * Débute le chronomètre
	 */
	public void start() {
		start = System.currentTimeMillis();
	}
	/**
	 * Met en pause le chronomètre.
	 */
	public void end() {
		end = System.currentTimeMillis();
	}
}
