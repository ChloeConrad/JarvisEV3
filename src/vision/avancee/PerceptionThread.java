package vision.avancee;
/**
 * Thread permettat la mise à jour de l'état interne du robot en parrallele à ses actions
 */
import lejos.hardware.Button;

public class PerceptionThread extends Thread {
	protected Jarvis j;
	public PerceptionThread(Jarvis j) {
		this.j=j;
		
	}
	/**
	 * Run() du thrad mettant à jour l'état interne du robot en boucle tant qu'un bouton n'as pas été pressé pour mettre celui ci en pause
	 */
	public void run() {
	
	while(!j.getState().boutonPresse) {
		
		j.getState().majPalets();
		j.getState().majState();
	}
	
	}
}
