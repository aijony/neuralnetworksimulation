import static org.lwjgl.opengl.GL11.*;


import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

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
	
    private int vboVertID;
    private int vboColID;
    
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
		
		//TODO dispose 
		 
		// Generate and bind a Vertex Array
	    vaoID = glGenVertexArrays();
	    glBindVertexArray(vaoID);

	    // The vertices of our Triangle
	    float[] vertices = new float[]
	    {
	        +0.0f, +0.8f,    // Top coordinate
	        -0.8f, -0.8f,    // Bottom-left coordinate
	        +0.8f, -0.8f     // Bottom-right coordinate
	    };
	    
	    float[] colors = new float[]
	    {
	    	1, 0, 0, 1,
	    	0, 1, 0, 1,
	    	0, 0, 1, 1
	    };
	  

	    glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
	    // Create a FloatBuffer of vertices
	    //This must be done so openGL can work with a native type
	    FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
	    verticesBuffer.put(vertices).flip();

	    
	    vboColID = glGenBuffers();
	    glBindBuffer(GL_ARRAY_BUFFER, vboColID);
	    glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
	    
	    
	    // Create a Buffer Object and upload the vertices buffer
	    vboID = glGenBuffers();
	    //Activates buffer
	    glBindBuffer(GL_ARRAY_BUFFER, vboID);
	    //Sends buffer to GPU
	    glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

	    // Point the buffer at location 0, the location we set
	    // inside the vertex shader. You can use any location
	    // but the locations should match
	    glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
	    
	    
        // Create a FloatBuffer of colors
        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();

        // Create a Buffer Object and upload the colors buffer
        vboColID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColID);
        
        glBufferData(GL_ARRAY_BUFFER, colorsBuffer, GL_STATIC_DRAW);
        

        // Point the buffer at location 1, the location we set
        // inside the vertex shader. You can use any location
        // but the locations should match
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);

        
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

        // Draw a triangle of 3 vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);

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
        glDeleteBuffers(vboVertID);
        glDeleteBuffers(vboColID);
	}
}
