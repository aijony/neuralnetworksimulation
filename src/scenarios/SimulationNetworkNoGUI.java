package scenarios;
import neuralnetwork.SimulationInteraction;
import simulation.ActorManager;
public class SimulationNetworkNoGUI {

	public static void main(String[] args) {
		ActorManager.initialize(false);
		new SimulationInteraction().run();
	}

}
