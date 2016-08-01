
public class Color {
	private float red, green, blue, alpha;

	protected Color(float r, float g, float b, float a) {
		set(r, g, b, a);
	}

	protected void set(float r, float g, float b, float a) {
		setRed(r);
		setGreen(g);
		setBlue(b);
		setAlpha(a);
	}

	public static Color red() {
		return new Color(1.0f, 0.0f, 0.0f, 1.0f);
	}

	public static Color green() {
		return new Color(0.0f, 1.0f, 0.0f, 1.0f);
	}

	public static Color blue() {
		return new Color(0.0f, 0.0f, 1.0f, 1.0f);
	}

	public static Color white() {
		return new Color(0.0f, 0.0f, 0.0f, 1.0f);
	}

	public static Color black() {
		return new Color(1.0f, 1.0f, 1.0f, 1.0f);
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
}
