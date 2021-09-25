package vision;

import lejos.robotics.navigation.MovePilot;
/**
 * Classe du robot servant à définir son comportement
 * @author Maleus
 *
 */
public class Jarvis{
	private final static int DEPART=0;
	private final static int BUT=1;
	private final static int RECHERCHE=2;
	private final static int PALETTROUVE=3;
	private final static int PALETNONTROUVE=4;
	private final static int PALET=4;
	private final static int NOPALET=5;
	private OurMotor pilote;
	private double[] palets;
	private int notrePosition;
	private int enemyPosition;
	private int etat;
	public Jarvis() {
		// TODO Auto-generated constructor stub
		palets= new double[9]; //On initialise la "valeur" des 9 palets à 1 (ils ont 100% de chance d'être sur le terrain)
		for (int i = 0; i<9; i++) {
			palets[i]=1;
		}
		etat=DEPART;
	}
	public double[] getPalets() {
		return palets;
	}
	public void setPalets(double[] palets) {
		this.palets = palets;
	}
	public int getNotrePosition() {
		return notrePosition;
	}
	public void setNotrePosition(int notrePosition) {
		this.notrePosition = notrePosition;
	}
	public int getEnemyPosition() {
		return enemyPosition;
	}
	public void setEnemyPosition(int enemyPosition) {
		this.enemyPosition = enemyPosition;
	}
	/**
	 * Algo simple pour mettre le premier but, il utilise les attributs notrePosition et enemyPosition pour déterminer quel palet récupérer
	 */
	public void premierBut() {
		
	}
	/**
	 * Permet de mettre la valeur du palet 
	 * @param a à 0 lorsqu'on l'a récupéré
	 */
	public void valeurPaletZero(int a) {
	
		
	}
	/**
	 * en prenant en compte @param enemyPosition on diminue à 50% la valeur des palets de sa colonne
	 */
	public void valeurPalet50() {
		
	}
	/**
	 * Methode permetant au robot de se déplacer de 
	 * @param distance centimetre
	 */
	public void seDeplacer(double distance) {
		
	}
	/**
	 * Methode permetant au robot de se tourner de
	 * @param degre
	 */
	public void seTourner(double degre) {
		
	}
	
	/**
	 * Méthode identifiant l'objet le plus proche
	 */
	public void identifyNearest() {
		
	}
	/**
	 * Methode permettant d'attraper le Palet le plus proche
	 */
	public void attrapePalet() {
		
	}
	
	/**
	 * Methode permettant d'aller marquer un but une fois le palet attrapé
	 *
	 */
	public void vasMarquer() {
		
	}
	/**
	 * Methode permettant de mettre Jarvis en recherche
	 */
	public void setEtatRecherche() {
		
	}
	

}
