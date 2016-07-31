import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {



	private Quadrilateral q1, q2;

	private float sp;
	private boolean swapcolor;

	public Renderer() {

		q1 = new Quadrilateral();
		q2 = new Quadrilateral();

	}

	public void update() {

		sp = sp + 0.002f;
		if (sp > 1.0f) {
			sp = 0.0f;
			swapcolor = !swapcolor;
		}

		q1.update(sp);
		q2.update(0.4f);
	}

	public void render() {

		q1.render();
		q2.render();
	}

	public void dispose() {
		
		q1.dispose();
		q2.dispose();
	}
}
