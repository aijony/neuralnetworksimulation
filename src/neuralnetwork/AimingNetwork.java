package neuralnetwork;

import simulation.*;
import utilities.IOInteraction;

public class AimingNetwork extends ActorNetwork{
	public AimingNetwork(int[] npl, int indexParam, double learnRate) {
		super(npl, indexParam, learnRate);
	}

	protected void setInput(){
		int enemyIndex = IOInteraction.getEnemyIndex(ActorManager.exportDatum(index));
		input = IOInteraction.getInput(ActorManager.exportDatum(enemyIndex));
	}

	protected void calculateSuccess(){
		//Network learn (back propagate)
		success = ActorManager.exportDatum(index).successfulHit;
	}
	
	protected void awaitUpdate(){
		
		try {
			
			((Unit) (ActorManager.getActor(index))).waitReload.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void setOutput(){
		((Unit) (ActorManager.getActor(index))).fire(outputPoint);
	}

	
	protected boolean checkReady() {
		return true; // ActorManager.getActor(index).canFire;
	}
}
