package scenarios;

import neuralnetwork.SimulationInteraction;
import simulation.ActorManager;

public class Updater extends Thread{
	private boolean update = true;
	private SimulationInteraction simulation;
	public Updater(){
		 simulation = new SimulationInteraction();	
	}
	public void run(){
		
		simulation.run();
		ActorManager.updateAll();
		
	}
	public void exit(){
		update = false;
	}
	
}
