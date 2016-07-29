import static org.lwjgl.opengl.GL11.*;


import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.rmi.CORBA.Util;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.BufferedReader;
import java.io.FileReader;
 
import org.lwjgl.opengl.GL11;

import org.lwjgl.opengl.GL20;
 
public class Renderer {
	
    private int vaoID;
    private int vboID;
	
    
    private int eboID;

    private ShaderManager shaderManager;
    
    private float sp;
    private boolean swapcolor;
	public Renderer(){
		
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
		
		shaderManager = new ShaderManager();
		shaderManager.attachAndLinkShaders();
		 
		// Generate and bind a Vertex Array
	    vaoID = glGenVertexArrays();
	    glBindVertexArray(vaoID);


	    float[] vertices = new float[]
	    {
	    	 // x,    y,     r, g, b, a
	    	 -0.8f, +0.8f,   1, 0, 0, 1,
	    	 +0.8f, +0.8f,   0, 1, 0, 1,
	    	 -0.8f, -0.8f,   0, 0, 1, 1,
	    	 +0.8f, -0.8f,   1, 1, 1, 1
	    };
	    
        // The indices that form the rectangle
        short[] indices = new short[]
        {
            0, 1, 2,  // The indices for the left triangle
            1, 2, 3   // The indices for the right triangle
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
        final int colorSize  = 4 * sizeOfFloat;

        // The 'stride' is the sum of the sizes of individual components
        final int stride = vertexSize + colorSize;

        // The 'offset is the number of bytes from the start of the tuple
        final long offsetPosition = 0;
        final long offsetColor    = 2 * sizeOfFloat;

        // Setup pointers using 'stride' and 'offset' we calculated above
        glVertexAttribPointer(0, 2, GL_FLOAT, false, stride, offsetPosition);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, stride, offsetColor);
        
	    
	    
	    
        

        
        

        // Enable the vertex attribute locations
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        
	    glBindVertexArray(0);
	}
	
	public void update()
	{
		 sp = sp+0.001f;
		     if(sp > 1.0f)
		     {
		         sp = 0.0f;
		         swapcolor = !swapcolor;
		     }

	}
	
	public void render()
	{
		 // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT);

        shaderManager.linkShader(true);
        
        

        // Bind the vertex array and enable our location
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);


        // Draw a rectangle of 4 vertices, so it is 6 indices
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_SHORT, 0);

        // Disable our location
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

        // Un-bind our program
        shaderManager.linkShader(false);
       
	}

	public void dispose() {
		 // Dispose the program
        shaderManager.dispose();

        // Dispose the vertex array
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoID);

        // Dispose the buffer object
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboID);
        
        // Dispose the element buffer object
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDeleteBuffers(eboID);

	}
}
