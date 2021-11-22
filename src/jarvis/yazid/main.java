package jarvis.yazid;

import lejos.hardware.Button;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a =Etat.init();
		Jarvis j=new Jarvis(a);
		j.recherchePalet();
		j.attrapePalet();
		
		
		
	}

}
