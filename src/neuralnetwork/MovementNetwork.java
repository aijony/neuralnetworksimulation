package neuralnetwork;

import simulation.*;
import utilities.IOInteraction;

public class MovementNetwork extends ActorNetwork{
	public MovementNetwork(int[] npl, int indexParam, double learnRate) {
		super(npl, indexParam, learnRate);
	}

	protected void setInput(){
		int projectileIndex = IOInteraction.getProjectileIndex
				(ActorManager.exportDatum(index),  ActorManager.exportData());
		if(projectileIndex != -1){
			input = IOInteraction.getInput(ActorManager.exportDatum(projectileIndex));
		}
		else 
			input = new double[4];
	}
	
	protected void calculateSuccess(){
		//Network learn (back propagate)
		
		success = !ActorManager.exportDatum(index).hasBeenHit;
	}
	
	protected void awaitUpdate(){
		try {
			
			ActorManager.getActor(index).waitMovementUpdate.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void setOutput(){
		ActorManager.getActor(index).initializeMovement(outputPoint);
		
	}
	
	protected boolean checkReady() {
		System.out.println( ActorManager.getActor(index).isReady());
		return ActorManager.getActor(index).isReady();
	}
}
