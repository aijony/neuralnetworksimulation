import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.ArrayList;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.*;

public class Renderer {

	private ShaderManager shaderManager;

	private int vaoID;
	private int vboID;
	private int eboID;
	
	private int indexLength;
	private boolean useIndices = false;
	private float input;
	private boolean swapcolor;

	public Renderer(Vertices vertexList) {
		shaderManager = new ShaderManager();
		shaderManager.attachAndLinkShaders();

		// Generate and bind a Vertex Array
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);

		float[] vertices = vertexList.toArray();

		short[] indices = vertexList.getIndices();
		
		
		indexLength = indices.length;
		useIndices = indexLength != 3;
		
		if(useIndices){
		
			// Create a ShortBuffer of indices
			ShortBuffer indicesBuffer = BufferUtils.createShortBuffer(indices.length);
			indicesBuffer.put(indices).flip();

			// Create the Element Buffer object
			eboID = glGenBuffers();
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
		
		}
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
		
		if(useIndices)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		


	}

	public void update(Vertices vertexList) {
		
		// Update vertices in the VBO, first bind the VBO
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		
		float [] vertices = vertexList.toArray();
		
		// Create a FloatBuffer of vertices
		FloatBuffer interleavedBuffer = BufferUtils.createFloatBuffer(vertices.length);
		interleavedBuffer.put(vertices).flip();

		glBufferData(GL_ARRAY_BUFFER, interleavedBuffer, GL_STATIC_DRAW);
		// Update vertices in the VBO, first bind the VBO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void update(){};

	public void render() {
		
		shaderManager.linkShader(true);
		// Bind the vertex array and enable our location
		glBindVertexArray(vaoID);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		// Draw a rectangle of 4 vertices, so it is 6 indices
		glDrawElements(GL_TRIANGLES, indexLength, GL_UNSIGNED_SHORT, 0);

		// Disable our location
		glBindVertexArray(0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
		if(useIndices)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		// Un-bind our program
		shaderManager.linkShader(false);
	}

	public void dispose() {
		// Dispose the program
		shaderManager.dispose();
		// Dispose the buffer object
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(vboID);

		if(useIndices){
		// Dispose the element buffer object
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDeleteBuffers(eboID);
		}
		glDeleteVertexArrays(vaoID);
	}
}
