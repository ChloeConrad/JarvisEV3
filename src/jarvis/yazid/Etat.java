/**
 * Nouvelle classe état ayant pour but de prendre partie de l'outil de détection des palets en IR
 */
package jarvis.yazid;

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
	
	private Waypoint cible;//Destination
	private boolean aPalet;
	private Pose position; 
	private OurSensors senseurs;
	int state;
	private Waypoint[] buts=new Waypoint[3];
	private Waypoint[] palets=new Waypoint[9];
	private int port = 8888;
	private byte[] buffer = new byte[2048];
	private DatagramPacket packet;
	private DatagramSocket dsocket;
	private float dist;
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
	
	public Etat(int a) throws IOException {
		switch(a) {
		 case 4:
			 position = new Pose(100,30,90);
		 case 8:
			 position = new Pose(150,30,90);
		 case 10:
			 position = new Pose(50,30,90);
		 case 14:
			 position = new Pose(100,270,270);
		 case 18:
			 position = new Pose(150,270,270);
		 case 20:
			 position = new Pose(50,270,270);
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
        	this.palets[i]=new Waypoint(x,y);
        	
        }
     

         packet.setLength(buffer.length);
         state=INIT;
	}
	
	public Waypoint getCible() {
		return cible;
	}

	public void setCible(Waypoint cible) {
		this.cible = cible;
	}

	public boolean isPalet() {
		return aPalet;
	}

	public void setPalet(boolean palet) {
		this.aPalet = palet;
	}

	public Pose getPosition() {
		return position;
	}

	public void setPosition(Pose position) {
		this.position = position;
	}

	public OurSensors getSenseurs() {
		return senseurs;
	}

	public void setSenseurs(OurSensors senseurs) {
		this.senseurs = senseurs;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Waypoint getPalets(int i) {
		return palets[i];
	}
	public Waypoint[] getPalets() {
		return palets;
	}

	public void setPalets(Waypoint[] palets) {
		this.palets = palets;
	}

	public void majPalets() {
		String msg = new String(buffer, 0, packet.getLength());
        
        String[] palets = msg.split("\n");
        
        for (int i = 0; i < palets.length; i++) 
        {
        	String[] coord = palets[i].split(";");
        	int x = Integer.parseInt(coord[1]);
        	int y = Integer.parseInt(coord[2]);
        	this.palets[i]=new Waypoint(x,y);
        	
        }
     
        packet.setLength(buffer.length);
	}
	public void majState() {
		if (dist<30 && position.getHeading()>180) {
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
				state=APALET;
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
	
}
