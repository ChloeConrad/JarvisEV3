/**
 * Classe ayant pour objectif de déterminer l'état interne du robot en fonction des retours de ses différents
 * senseurs et du detecteur infrarouge situé au dessus du terrain cette classe throw des <b>IOExceptions</B>
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
	 * Attribut determinant le palet cible actuel sous forme de <i> Waypoint</i> qui sera ensuite utilisé
	 * par @class Navigator pour determiner le prochain point de l'espace de jeu ou se rendre
	 * @author JarvisTeam
	 */
	private Waypoint cible;
	/**
	 * Boolean se mettant a jour en fonction de la présence ou non d'un palet dans les pinces du robot
	 */
	private boolean aPalet;
	/**
	 * Redondance de la position du robot avec le @interface PoseProvider de la @class Chassis
	 */
	private Pose position;
	/**
	 * Boolean servant à determiner si un bouton est pressé durant la partie pour mettre le programme en pause
	 */
	private boolean boutonPresse;
	/**
	 *  Instance de la @class OurSensors permettant d'intialiser les senseurs du robot
	 */
	private OurSensors senseurs;
	/**
	 * Représentation sous la forme d'entier de l'état du robot
	 */
	int state;
	/**
	 * Tableau de @class Waypoints contenant des references aux positions des palets sur le terrain fournis par 
	 * le capteur Infrarouge
	 */
	private Waypoint[] palets=new Waypoint[9];
	/**
	 * Ensemble d'attributs nécessaire à la communication avec le serveur ou se trouve les données Infrarouge 
	 * de la position des palets sur le terrain
	 */
	private int port = 8888;
	private byte[] buffer = new byte[2048];
	private DatagramPacket packet;
	private DatagramSocket dsocket;
	/**
	 * Attribut servant à récupérer les données du capteur UltraSon sous forme de float et ainsi d'éviter les collisions
	 */
	private float dist;
	/**
	 * Methode static permettant d'initialiser la position du robot sur le terrain
	 * @return un entier
	 * ayant pour valeurs 
	 * {@value 4,8,10,14,18,20}
	 * en fonction de la position sur le terrain (Haut ou bas en ordonnée, puis gauche, central ou droite en abscisse)
	 */
	public static int init() {
		System.out.println("Bonjour, je m'appel Jarvis, êtes vous à droite ou à gauche du terrain?");
		if(Button.waitForAnyPress()==Button.ID_LEFT) {
			System.out.println("Vous êtes à gauche du terrain? Veuillez confirmer par n'importe quelle touche");
			Button.waitForAnyPress();
			System.out.println("Etes vous a gauche ? au centre ou à droite?");
			return Button.waitForAnyPress();
		}
		else if(Button.waitForAnyPress()==Button.ID_RIGHT) {
			System.out.println("Vous êtes à gauche du terrain? Veuillez confirmer par n'importe quelle touche");
			Button.waitForAnyPress();
			System.out.println("Etes vous a gauche ? au centre ou à droite?");
			return Button.waitForAnyPress()+10;
		}
		return 0;
		
	}
	/**
	 * Constructeur de la classe Etat prennant 
	 * @param a
	 * en parametre qui correspond à l'entier retourné par la @method static init() pour initialiser la position initiale du robot  
	 * @throws IOException
	 * initialise l'ensemble des attributs de la classe avec les valeurs initiale récupéré  par les capteurs lorsque cela est necessaire
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
	 * Methode permettant de récuperer
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
	 * Methode permettant de récuperer
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
	 * Methode permettant de récuperer
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
	 * Methode permettant de récuperer 
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
	 * Methode permettant de récuperer la valeur numérique correspondant à
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
	 * Methode permettant de récuperer un @class Waypoint dans le tableau palets en fonction de 
	 * @param i
	 * 
	 * @return palets[i]
	 */
	public Waypoint getPalets(int i) {
		return palets[i];
	}
	/**
	 * Methode permettant de récuperer l'ensemble des @class Waypoint correspondant aux coordonnées des palets sur le terrain
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
	 * Methode utilisant les données de la caméra infrarouge pour mettre a jour les données positionelles des palets 
	 * les modifications sur les coordonnées x et y servent à rendre les @class Waypoint lisible par le @class lejos.robotics.navigation.Navigator
	 * de même les modifications sur l'abscisse de chaque point sont due au fait que les coordonnées renvoyé ont une abscisse inversé par rapport à un repére
	 * orthonormé classique 
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
	
	public void setDist() {
		dist=senseurs.getDist();
	}
	
	public void setaPalet(boolean b) {
		aPalet=b;
	}
	
}
