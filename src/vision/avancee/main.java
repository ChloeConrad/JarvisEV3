package vision.avancee;

import lejos.hardware.Button;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a =Etat.init();
		Jarvis j=new Jarvis(a);
		PerceptionThread t1=new PerceptionThread(j);
		t1.run();
		do {
			j.agissements();
		}while(j.getState().getState()!=-2);
		
		
		
	}

}
