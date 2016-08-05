package simulation;

public class Projectile extends Actor{
	private int damage;
	private int targetUnitIndex;
	
	public Projectile(Unit origin){
		super(origin.getPos(), origin.getOrientation(), .1, "Projectile", .05, 4);
		damage = origin.getDamage();
		if (this.getOrientation() == origin.getOrientation()){
			System.out.println("Projectile is facing the correct direction");
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		targetUnitIndex = origin.getTargetUnitIndex();
		moveTo();
	}
	public void moveTo(){
		xMove = getSpeed()*Math.cos(this.getOrientation());
		yMove = getSpeed()*Math.sin(this.getOrientation());
		stepTo(xMove, yMove);
		findPositions();
		setReady(outOfBounds());
		System.out.println("Projectile " + getIndex() + " is stepping");
	}
	public boolean outOfBounds(){
		double x = getPos().getX();
		double y = getPos().getY();
		return (x > 1 || x < -1 || y > 1 || y < -1);
	}
//	public boolean collision(){
//		xDiff = getPos() - actors.get(targetUnitIndex).
//		if (actors.get(targetUnitIndex).getPos().
//	}
	public boolean isProjectile(){return true;}
}
