import static org.lwjgl.opengl.GL11.*;


import javax.rmi.CORBA.Util;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
 
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
 
public class Renderer {
	
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
		int programId = glCreateProgram();
		
		int vertShader = loadAndCompileShader("res/vertex.vert", GL_VERTEX_SHADER);
		int fragShader = loadAndCompileShader("res/fragment.frag", GL_FRAGMENT_SHADER);
		
		//Attach the compiled shaders to the program.
		glAttachShader(programId, vertShader);
		glAttachShader(programId, fragShader);
		 
		//Now link the program
		glLinkProgram(programId);
 
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
 
		//Acquire compilation status
		int shaderStatus = glGetShaderi(shaderHandle, GL_COMPILE_STATUS);
 
		//Check whether compilation was successful
		if( shaderStatus == GL_FALSE)
		{
			throw new IllegalStateException("Shader did not compile ["+filepath+"]. Reason: " + glGetShaderInfoLog(shaderHandle, 1000));
		}
 
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
		
		//Out of date render technique that will be shortly updated.
		glBegin(GL_QUADS);
		{
			glColor3f(0.0f, 1.0f, 0.0f);

			glVertex3f(-0.5f, -0.5f, 0.0f);
			glVertex3f(0.5f, -0.5f, 0.0f);
			glVertex3f(0.5f, 0.5f, 0.0f);
			glVertex3f(-0.5f, 0.5f, 0.0f);
		}
		glEnd();
	}
}
