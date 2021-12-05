package vision.avancee;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a =Etat.init();
		Jarvis j=new Jarvis(a);
		PerceptionThread t1=new PerceptionThread(j);
		t1.start();
		do {
			j.agissements();
		}while(j.getState().getState()!=-2);
	}

}
