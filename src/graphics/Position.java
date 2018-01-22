package graphics;

public class Position {
	private float x;
	private float y;

	public Position() {
		setX(0.0f);
		setY(getX());
	}

	public Position(float inX, float inY) {
		set(inX, inY);
	}

	public void set(float inX, float inY) {
		setX(inX);
		setY(inY);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
