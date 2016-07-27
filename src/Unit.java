//
public enum Unit {
	SOLDIER(3, 10, 100, 5, 5),
	ARCHER(15, 7, 50, 4, 10);
	private int range;
	private int damage;
	private int health;
	private int speed;
	private int cost;
	private Point loc;
	Unit(int r, int d, int h, int s, int c) {
		range = r; damage = d; health = h; speed = s; cost = c;
	}
	public void setPos(Point position){
		 loc = position;
	}
	int getRange(){
		return range;
	}
	int getDamage(){
		return damage;
	}
	int getHealth(){
		return health;
	}
	int damage(int amount){
		health -= amount;
		return health;
	}
	int getSpeed(){
		return speed;
	}
	int getCost(){
		return cost;
	}
	
}
