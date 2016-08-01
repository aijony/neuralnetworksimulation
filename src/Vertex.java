
public class Vertex {
	private Color color;
	private Position position;

	public Vertex() {
		color = Color.white();
		position = new Position();
	}

	public Vertex(Color inputColor, Position inputPos) {
		setColor(inputColor);
		setPosition(inputPos);
	}

	public void setColor(Color inputColor) {
		color = inputColor;
	}

	public void setPosition(Position inputPos) {
		position = inputPos;
	}

	public float[] toArray() {
		float[] vertex = new float[] { position.getX(), position.getY(), color.getRed(), color.getBlue(),
				color.getGreen(), color.getAlpha() };
		return vertex;
	}
}
