import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;

public class Polygon {

	private int vboID;
	private int eboID;

	public Polygon() {

		float[] vertices = new float[] {
				// x, y, r, g, b, a
				-0.8f, +0.8f, 1, 0, 0, 1,
				+0.8f, +0.8f, 0, 1, 0, 1,
				-0.8f, -0.8f, 0, 0, 1, 1,
				+0.8f, -0.8f, 1, 1, 1, 1
				};

		// The indices that form the rectangle
		short[] indices = new short[] { 
				
				0, 1, 2, // The indices for the left
													// triangle
				1, 2, 3 // The indices for the right triangle
		};

		// Create a ShortBuffer of indices
		ShortBuffer indicesBuffer = BufferUtils.createShortBuffer(indices.length);
		indicesBuffer.put(indices).flip();

		// Create the Element Buffer object
		eboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

		// Create a FloatBuffer of vertices
		FloatBuffer interleavedBuffer = BufferUtils.createFloatBuffer(vertices.length);
		interleavedBuffer.put(vertices).flip();

		// Create a Buffer Object and upload the vertices buffer
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, interleavedBuffer, GL_STATIC_DRAW);

		// The size of float, in bytes (will be 4)
		final int sizeOfFloat = Float.SIZE / Byte.SIZE;

		// The sizes of the vertex and color components
		final int vertexSize = 2 * sizeOfFloat;
		final int colorSize = 4 * sizeOfFloat;

		// The 'stride' is the sum of the sizes of individual components
		final int stride = vertexSize + colorSize;

		// The 'offset is the number of bytes from the start of the tuple
		final long offsetPosition = 0;
		final long offsetColor = 2 * sizeOfFloat;

		// Setup pointers using 'stride' and 'offset' we calculated above
		glVertexAttribPointer(0, 2, GL_FLOAT, false, stride, offsetPosition);
		glVertexAttribPointer(1, 4, GL_FLOAT, false, stride, offsetColor);

		// Enable the vertex attribute locations
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		glBindVertexArray(0);
		// Bind to the index VBO that has all the information about the order of
		// the vertices
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void update(float input) {
		// Update vertices in the VBO, first bind the VBO
		glBindBuffer(GL_ARRAY_BUFFER, vboID);

		float[] vertices = new float[] {
				// x, y, r, g, b, a
				-input, +input, 1, 0, 0, 1,
				+input, +input, 0, 1, 0, 1,
				-input, -input, 0, 0, 1, 1,
				+input, -input, 1, 1, 1, 1
				};

		// Create a FloatBuffer of vertices
		FloatBuffer interleavedBuffer = BufferUtils.createFloatBuffer(vertices.length);
		interleavedBuffer.put(vertices).flip();

		glBufferData(GL_ARRAY_BUFFER, interleavedBuffer, GL_STATIC_DRAW);
		// Update vertices in the VBO, first bind the VBO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void dispose() {
		// Dispose the buffer object
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(vboID);

		// Dispose the element buffer object
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDeleteBuffers(eboID);
	}
}
