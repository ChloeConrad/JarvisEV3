package vision;

import maths.Point;
import maths.Vecteur;
import sensors.OurSensors;
/**
 * 
 * @author Yazid
 * Cette classe repr�sente l'�tat interne du robot qu'il met � jour en permanence avec son thread secondaire 
 * : PerceptionThread
 *
 */
public class Etat {
	private final static int GRIS=0;
	private final static int NOIR=1;
	private final static int BLANC=2;
	private final static int JAUNE=3;
	private final static int VERT=4;
	private final static int ROUGE=5;
	private final static int BLEU=6;
	private final static int DEPART=0;
	private final static int BUT=1;
	private final static int RECHERCHE=2;
	private final static int PALETTROUVE=3;
	private final static int PALETNONTROUVE=4;
	private final static int PALET=5;
	private final static int NOPALET=6;
	
	private Vecteur position;
	
	private Boussole orientation;
	private Palet[] palets;
	private boolean etatPinces;
	private boolean aPalet;
	private boolean riskCollisio;
	private int isOnColor;
	private double sErreur;
	private int etat;
	private Point cible;
	private float nearest;
	
	
	public Etat () {
		this.position= new Vecteur();
		this.orientation= new Boussole();
		this.palets=new Palet[9];
		palets[0] = new Palet(new Point(50,90));
		palets[1] = new Palet(new Point(100,90));
		palets[2] = new Palet(new Point(150,90));
		palets[3] = new Palet(new Point(50,150));
		palets[4] = new Palet(new Point(100,150));
		palets[5] = new Palet(new Point(150,150));
		palets[6] = new Palet(new Point(50,210));
		palets[7] = new Palet(new Point(100,210));
		palets[8] = new Palet(new Point(150,210));
		
		sErreur=0;
		etatPinces=false;
		aPalet=false;
		riskCollisio=false;
		isOnColor=BLANC;
		etat=DEPART;
		cible=null;
	}


	public Vecteur getPosition() {
		return position;
	}


	public void setPosition(Vecteur position) {
		this.position = position;
	}


	public Boussole getOrientation() {
		return orientation;
	}


	public void setOrientation(Boussole orientation) {
		this.orientation = orientation;
	}


	public Palet[] getPalets() {
		return palets;
	}


	public void setPalets(Palet[] palets) {
		this.palets = palets;
	}


	public boolean isEtatPinces() {
		return etatPinces;
	}


	public void setEtatPinces(boolean etatPinces) {
		this.etatPinces = etatPinces;
	}

	public boolean isaPalet() {
		return aPalet;
	}


	public void setaPalet(boolean aPalet) {
		this.aPalet = aPalet;
	}


	public boolean isRiskCollisio() {
		return riskCollisio;
	}


	public void setRiskCollisio(boolean riskCollisio) {
		this.riskCollisio = riskCollisio;
	}


	public int getIsOnColor() {
		return isOnColor;
	}


	public void setIsOnColor(int isOnColor) {
		this.isOnColor = isOnColor;
	}


	public double getsErreur() {
		return sErreur;
	}


	public void setsErreur(double sErreur) {
		this.sErreur = sErreur;
	}


	public int getEtat() {
		return etat;
	}



	public void setEtat(int etat) {
		this.etat = etat;
	}


	public Point getCible() {
		return cible;
	}


	public void setCible(Point cible) {
		this.cible = cible;
	}
	/**
	 * Fonction permettant d'initialiser le vecteur repr�sentant la position du robot en fonction de
	 * @param a
	 * le bouton press� au startup
	 */
	public void initPos(int a) {
		switch (a) {
		case 0:
			this.position = new Vecteur(new Point(50,20),new Point(50,30));
			break;
		case 1:
			this.position = new Vecteur(new Point(100,20),new Point(100,30));
			break;
		case 2:
			this.position = new Vecteur(new Point(150,20),new Point(150,30));
			break;
		}
	}
	public void initPalets(int a) {
		switch(a) {
		case 0:
			palets[0].setProbaPresence(0.5);
			palets[3].setProbaPresence(0.5);
			palets[6].setProbaPresence(0.5);
			break;
		case 1:
			palets[1].setProbaPresence(0.5);
			palets[4].setProbaPresence(0.5);
			palets[7].setProbaPresence(0.5);
		case 2:
			palets[2].setProbaPresence(0.5);
			palets[5].setProbaPresence(0.5);
			palets[8].setProbaPresence(0.5);
		}
	}
	/**
	 * Met � jour la valeur relative du palet 
	 * @param i palet � mettre � jour
	 * @param j nouvelle valeur du palet ([0,1]
	 */
	
	public void majPalet(int i,double j) {
		 this.palets[i].setProbaPresence(j);
	}
	/**
	 *met � jour les attributs interne � l'aide des censors 
	 */
	public void majState(OurSensors s) {
	
		if (s.getTouch()==1) aPalet=true;
		if (s.getTouch()==0) aPalet=false;
		//a faire color
		nearest=s.getDist();
	}
	
	public void reset(Jarvis j) {
		
	}
	/**
	 * Methode servant a trouver la cible la plus proche
	 * calculs capilotract�
	 */
	public void seekTarget() {
		Vecteur[] cible=new Vecteur[9];
		for (int i=0;i<9;i++) {
			cible[i]=new Vecteur(position.getpDepart(),palets[i].coordonnee);
		}
		double[] envie=new double[9];
		for (int i=0;i<9;i++) {
			envie[i]=cible[i].normeVecteur()/palets[i].getProbaPresence();
		}
		int cib=0;
		for (int i=1;i<9;i++) {
			if (envie[i]<envie[cib]) cib=i;
		}
		this.cible=palets[cib].getCoordonnee();
		
	}
	/**
	 * Methode qui permet de trouver l'angle entre le robot et la cible
	 * @return angle sous forme de double
	 */
	public double nearAngle() {
		if (cible.equals(null)) throw new IllegalArgumentException();
		
		return position.angleDeuxVecteurs(new Vecteur(position.getpDepart(),cible));
	}
	/**
	 * MEthode qui permet de trouver la distance entre Jarvis et la cible
	 * @return la distance en cm dans un double
	 */
	public double nearDist() {
		return new Vecteur(position.getpDepart(),cible).normeVecteur();
	}
	/**
	 * Determine la couleur sur laquelle on est
	 */
	public void quelCouleur(Jarvis j) {
		
	}
	

}