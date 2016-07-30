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

    private ShaderManager shaderManager;
    
    private Polygon polygon;
    
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
	    
	    polygon = new Polygon();

	}
	
	public void update()
	{
		
		 sp = sp+0.002f;
		     if(sp > 1.0f)
		     {
		         sp = 0.0f;
		         swapcolor = !swapcolor;
		     }
		     
		     polygon.update(sp);

	}
	
	public void render()
	{
		 // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT);

        shaderManager.linkShader(true);
        
        

        // Bind the vertex array and enable our location
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        
        
        // Draw a rectangle of 4 vertices, so it is 6 indices
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_SHORT, 0);

        // Disable our location
        glBindVertexArray(0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        // Un-bind our program
        shaderManager.linkShader(false);
       
	}

	public void dispose() {
		 // Dispose the program
        shaderManager.dispose();

        // Dispose the vertex array
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoID);

        polygon.dispose();

	}
}
