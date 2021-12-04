/**
 * Classe ayant pour objectif de d�terminer l'�tat interne du robot en fonction des retours de ses diff�rents
 * senseurs et du detecteur infrarouge situ� au dessus du terrain cette classe throw des <b>IOExceptions</B>
 */
package vision.avancee;
//100 = 9 cm
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import lejos.hardware.Button;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import sensors.OurSensors;

public class Etat {
	
	private final static int INIT=0;
	private final static int RECHERCHE=1;
	private final static int PALETTROUVE=2;
	private final static int APALET=3;
	private final static int BUT=4;
	private final static int COLLISION=-1;
	private final static int PAUSE=-2;
	/**
	 * Attribut determinant le palet cible actuel sous forme de <i> Waypoint</i> qui sera ensuite utilis�
	 * par @class Navigator pour determiner le prochain point de l'espace de jeu ou se rendre
	 * @author JarvisTeam
	 */
	private Waypoint cible;
	/**
	 * Boolean se mettant a jour en fonction de la pr�sence ou non d'un palet dans les pinces du robot
	 */
	private boolean aPalet;
	/**
	 * Redondance de la position du robot avec le @interface PoseProvider de la @class Chassis
	 */
	private Pose position;
	/**
	 * Boolean servant � determiner si un bouton est press� durant la partie pour mettre le programme en pause
	 */
	protected boolean boutonPresse;
	/**
	 *  Instance de la @class OurSensors permettant d'intialiser les senseurs du robot
	 */
	private OurSensors senseurs;
	/**
	 * Repr�sentation sous la forme d'entier de l'�tat du robot
	 */
	int state;
	/**
	 * Tableau de @class Waypoints contenant des references aux positions des palets sur le terrain fournis par 
	 * le capteur Infrarouge
	 */
	private Waypoint[] palets=new Waypoint[9];
	/**
	 * Ensemble d'attributs n�cessaire � la communication avec le serveur ou se trouve les donn�es Infrarouge 
	 * de la position des palets sur le terrain
	 */
	private int port = 8888;
	private byte[] buffer = new byte[2048];
	private DatagramPacket packet;
	private DatagramSocket dsocket;
	/**
	 * Attribut servant � r�cup�rer les donn�es du capteur UltraSon sous forme de float et ainsi d'�viter les collisions
	 */
	private float dist;
	/**
	 * Methode static permettant d'initialiser la position du robot sur le terrain
	 * @return un entier
	 * ayant pour valeurs 
	 * {@value 4,8,10,14,18,20}
	 * en fonction de la position sur le terrain (Haut ou bas en ordonn�e, puis gauche, central ou droite en abscisse)
	 */
	public static int init() {
		System.out.println("Bonjour, je m'appel Jarvis, �tes vous � droite ou � gauche du terrain?");
		if(Button.waitForAnyPress()==Button.ID_LEFT) {
			System.out.println("Vous �tes � gauche du terrain? Veuillez confirmer par n'importe quelle touche");
			Button.waitForAnyPress();
			System.out.println("Etes vous a gauche ? au centre ou � droite?");
			return Button.waitForAnyPress();
		}
		else if(Button.waitForAnyPress()==Button.ID_RIGHT) {
			System.out.println("Vous �tes � gauche du terrain? Veuillez confirmer par n'importe quelle touche");
			Button.waitForAnyPress();
			System.out.println("Etes vous a gauche ? au centre ou � droite?");
			return Button.waitForAnyPress()+10;
		}
		return 0;
		
	}
	/**
	 * Constructeur de la classe Etat prennant 
	 * @param a
	 * en parametre qui correspond � l'entier retourn� par la @method static init() pour initialiser la position initiale du robot  
	 * @throws IOException
	 * initialise l'ensemble des attributs de la classe avec les valeurs initiale r�cup�r�  par les capteurs lorsque cela est necessaire
	 *
	 */
	public Etat(int a) throws IOException {
		switch(a) {
		 case 4:
			 position = new Pose(100*100/9,30*100/9,90);
		 case 8:
			 position = new Pose(150*100/9,30*100/9,90);
		 case 10:
			 position = new Pose(50*100/9,30*100/9,90);
		 case 14:
			 position = new Pose(100*100/9,270*100/9,270);
		 case 18:
			 position = new Pose(150*100/9,270*100/9,270);
		 case 20:
			 position = new Pose(50*100/9,270*100/9,270);
		}
		
		
		senseurs= new OurSensors();
		packet= new DatagramPacket(buffer, buffer.length);
		dsocket.receive(packet);
		String msg = new String(buffer, 0, packet.getLength());
              
        String[] palets = msg.split("\n");
        
        for (int i = 0; i < palets.length; i++) 
        {
        	String[] coord = palets[i].split(";");
        	int x = Integer.parseInt(coord[1]);
        	int y = Integer.parseInt(coord[2]);
        	x=300-x;
        	x=x*100/9;
        	y=y*100/9;
        	this.palets[i]=new Waypoint(x,y);
        	
        }
     

         packet.setLength(buffer.length);
         state=INIT;
         boutonPresse=false;
	}
	/**
	 * Methode permettant de r�cuperer
	 * @return cible
	 */
	public Waypoint getCible() {
		return cible;
	}
	/**
	 * Methode permettant de set l'attribut cible en fonction de
	 * @param cible
	 */
	public void setCible(Waypoint cible) {
		this.cible = cible;
	}
	/**
	 * Methode permettant de r�cuperer
	 * @return aPalet
	 */
	public boolean isPalet() {
		return aPalet;
	}
	/**
	 * Methode permettant de set l'attribut aPalet en fonction de
	 * @param palet
	 */
	public void setPalet(boolean palet) {
		this.aPalet = palet;
	}
	/**
	 * Methode permettant de r�cuperer
	 * @return position
	 */
	public Pose getPosition() {
		return position;
	}
	/**
	 * Methode permettant de set position en fonction de
	 * @param position
	 */
	public void setPosition(Pose position) {
		this.position = position;
	}
	/**
	 * Methode permettant de r�cuperer 
	 * @return senseurs
	 */
	public OurSensors getSenseurs() {
		return senseurs;
	}
	/**
	 * Methode permettant de set senseurs en fonction de 
	 * @param senseurs
	 */
	public void setSenseurs(OurSensors senseurs) {
		this.senseurs = senseurs;
	}
	/**
	 * Methode permettant de r�cuperer la valeur num�rique correspondant �
	 * @return state
	 */
	public int getState() {
		return state;
	}
	/**
	 * Methode permettant de set state en fonction de
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * Methode permettant de r�cuperer un @class Waypoint dans le tableau palets en fonction de 
	 * @param i
	 * 
	 * @return palets[i]
	 */
	public Waypoint getPalets(int i) {
		return palets[i];
	}
	/**
	 * Methode permettant de r�cuperer l'ensemble des @class Waypoint correspondant aux coordonn�es des palets sur le terrain
	 * @return palets
	 */
	public Waypoint[] getPalets() {
		return palets;
	}
	/**
	 * Methode permettant de remplacer l'ensemble des @class Waypoint correspondant aux palets par un autre tableau de @class Waypoint
	 * @param palets
	 */
	public void setPalets(Waypoint[] palets) {
		this.palets = palets;
	}
	/**
	 * Methode utilisant les donn�es de la cam�ra infrarouge pour mettre a jour les donn�es positionelles des palets 
	 * les modifications sur les coordonn�es x et y servent � rendre les @class Waypoint lisible par le @class lejos.robotics.navigation.Navigator
	 * de m�me les modifications sur l'abscisse de chaque point sont due au fait que les coordonn�es renvoy� ont une abscisse invers� par rapport � un rep�re
	 * orthonorm� classique 
	 */
	public void majPalets() {
		String msg = new String(buffer, 0, packet.getLength());
        
        String[] palets = msg.split("\n");
        
        for (int i = 0; i < palets.length; i++) 
        {
        	String[] coord = palets[i].split(";");
        	int x = Integer.parseInt(coord[1]);
        	int y = Integer.parseInt(coord[2]);
        	x=300-x;
        	x=x*100/9;
        	y=y*100/9;
        	this.palets[i]=new Waypoint(x,y);
        	
        }
     
        packet.setLength(buffer.length);
	}
	/**
	 * Methode principale servant à mettre à jour l'état interne du robot en fonction de des données transmises par les différents capteurs et boutons du robot
	 */
	public void majState() {
		this.majPalets();
		dist=senseurs.getDist();
		if(Button.waitForAnyPress()!=0) {
			boutonPresse=true;
			state=PAUSE;
		}
		if (dist<30 ) {
			state=COLLISION;
			return;
		}
		if(state==APALET) {
			if (senseurs.getCouleur()=="blanc") {
				state=BUT;
				return;
			}
			else return;
		}
		else if (state==BUT) {
			if(!senseurs.getTouch()) {
				state=RECHERCHE;
				return;
			}
			else return;
		}
		else if(state==PALETTROUVE) {
			if(senseurs.getTouch()) {
				aPalet=true;
				return;
			}
		}
		else if (state==RECHERCHE) {
			if(aPalet) {
				state=PALETTROUVE;
				return;
			}	
		
		}
		else if (state==COLLISION) {
			if(senseurs.getDistance()>0.30) {
				if (senseurs.getTouch()) state=APALET;
			}
		}
	}
	/**
	 * Methode permetant de stocker la distance devant le robot dans l'attribut dist
	 */
	public void setDist() {
		dist=senseurs.getDist();
	}
	/**
	 * Methode permettant de set l'attribut aPalet à l'aide du boolean 
	 * @param b
	 */
	public void setaPalet(boolean b) {
		aPalet=b;
	}
	
}
