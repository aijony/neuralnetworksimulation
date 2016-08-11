package simulation;

public class Updater extends Thread{
	private boolean update = true;
	public void run(){
		while(update){
		ActorManager.updateAll();
		}
	}
	public void exit(){
		update = false;
	}
	
}
