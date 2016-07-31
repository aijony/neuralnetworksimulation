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

		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		// 2D renders generally won't require depth testing
		glDisable(GL_DEPTH_TEST);
		// Enable blending
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		
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
		// Clear the screen
		glClear(GL_COLOR_BUFFER_BIT);

		q1.render();
		q2.render();
	}

	public void dispose() {
		// Dispose the vertex array
		glBindVertexArray(0);
		q1.dispose();
		q2.dispose();
	}
}
