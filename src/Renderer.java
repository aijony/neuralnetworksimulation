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
	
    int programId;
	public Renderer(){
		
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		// 2D games generally won't require depth testing 
		glDisable(GL_DEPTH_TEST);
		// Enable blending
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		//Create the shader program. If OK, create vertex and fragment shaders
		programId = glCreateProgram();
		
		int vertShader = loadAndCompileShader("res/vertex.vert", GL_VERTEX_SHADER);
		int fragShader = loadAndCompileShader("res/fragment.frag", GL_FRAGMENT_SHADER);
		
		//Attach the compiled shaders to the program.
		glAttachShader(programId, vertShader);
		glAttachShader(programId, fragShader);
		glLinkProgram(programId);
		
		
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

	    // Create a FloatBuffer of vertices
	    FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
	    verticesBuffer.put(vertices).flip();

	    // Create a Buffer Object and upload the vertices buffer
	    vboID = glGenBuffers();
	    glBindBuffer(GL_ARRAY_BUFFER, vboID);
	    glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

	    // Point the buffer at location 0, the location we set
	    // inside the vertex shader. You can use any location
	    // but the locations should match
	    glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
	    glBindVertexArray(0);
	}
	
	private int loadAndCompileShader(String filepath, int shaderType)
    {
		//vertShader will be non zero if succefully created
		//Handle is used as a reference
		int shaderHandle = glCreateShader(shaderType);
		
		//Shader string
		String shaderCode = loadString(filepath);
 
		//Uploads shader to openGL
		glShaderSource(shaderHandle, shaderCode);
 
		//Compiles the shader
		glCompileShader(shaderHandle);
 
		// Check for errors
        if (glGetShaderi(shaderHandle, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException("Error creating vertex shader\n"
                                       + glGetShaderInfoLog(shaderHandle, glGetShaderi(shaderHandle, GL_INFO_LOG_LENGTH)));

 
		return shaderHandle;
    }
 
	/**
	 * Load a text file and return its contents as a String.
	 */
	private String loadString(String filepath)
	{
		StringBuilder shaderString = new StringBuilder();
		String line = null ;
		try
		{
		    BufferedReader reader = new BufferedReader(new FileReader(filepath));
		    while( (line = reader.readLine()) !=null )
		    {
		    	shaderString.append(line);
		    	shaderString.append('\n');
		    }
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Unable to load shader from file ["+filepath+"]", e);
		}
 
		return shaderString.toString();
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		 // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT);

        //Now link the program
      	glUseProgram(programId); 
      		

        // Bind the vertex array and enable our location
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);

        // Draw a triangle of 3 vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);

        // Disable our location
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

        // Un-bind our program
        glUseProgram(0);

       
	}
}
