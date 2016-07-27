import java.util.ArrayList;
public class Army {
	private int aggressiveness;
	private int spread;
	private int resources;
	private int unitRatio;
	private ArrayList<Unit> units;
	public Army(int a, int s, int r){
		aggressiveness = a;
		spread = s;
		resources = r;
		unitRatio = (int) (Math.random() * 100);
		units = new ArrayList<Unit>();
	}
	
	
}
