/**
 * Nouvelle classe état ayant pour but de prendre partie de l'outil de détection des palets en IR
 */
package jarvis.yazid;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
	
	Waypoint cible;
	boolean palet;
	Pose position; 
	OurSensors senseurs;
	int state;
	Waypoint[] palets=new Waypoint[9];
	int port = 8888;
	byte[] buffer = new byte[2048];
	DatagramPacket packet;
	DatagramSocket dsocket;
	
	public Etat() throws IOException {
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
			if(palet) {
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
}
