package tests;

import vision.Jarvis;
import vision.OurMotor;

/**
 * Classe de tests fonctionnels
 * @author JarvisTeam
 *
 */
public class Tests_generaux {
	public static void main(String[] args) {
		Jarvis jarvis = new Jarvis();
		OurMotor jarvisM = jarvis.getPilote();
		jarvisM.stopMessage("--- Début des tests ---\nTourner de 90° à droite");
		jarvisM.seTourner(90);
		jarvisM.stopMessage("Tourner de 90° à Gauche");
		jarvisM.seTourner(-90);
		jarvisM.stopMessage("Avancer tout droit de 50cm");
		OurMotor.forward(0.5);
		jarvisM.stopMessage("Reculer de 50cm");
		OurMotor.forward(-0.5);
		jarvisM.stopMessage("Differencier Mur - Palet par la distance (x3) ");
		for(int i=0; i<3;i++) {
			
		}
		
		
	}
}
