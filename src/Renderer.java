import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Renderer{
	

	//Window handler for GLFW
	private long windowID;

	/*
	 * Initializes GLFW and setups all window/GLFW properties 
	*/
	private void init(int width, int height, String title) {
		

		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		
		 // Initialize GLFW. Most GLFW functions will not work before doing this.
			if ( !glfwInit() )
				throw new IllegalStateException("GLFW did not initialize");

		//Window hints are essentially settings
		//TODO Configure
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
			
		//Create configured window
		windowID = glfwCreateWindow(width, height, title, NULL, NULL);
		if ( windowID == NULL )
		    throw new RuntimeException("GLFW window was not created"); 
		
		
		//TODO Input Callback
		
		//Sets resolution to monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(
		    windowID,
		    (vidmode.width() - width) / 2,
		    (vidmode.height() - height) / 2
		);
		
		glfwMakeContextCurrent(windowID);
		glfwSwapInterval(1);
		glfwShowWindow(windowID);
		
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
	}
	
	private void update()
	{
		
	}
	
	private void render()
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
	
	

	  private void loop() {
		  	
			while (glfwWindowShouldClose(windowID) == false) {
				

				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				update();
				render();
				glfwSwapBuffers(windowID);

				glfwPollEvents();
			}
		}
	  
	  public void run() {
		    

		    try {
		        init(300, 300, "jason");
		        loop();
		        glfwDestroyWindow(windowID);
		    } finally {
		        glfwTerminate();
		    }
		}
	  
	  public static void main(String[] args) {
		   new Renderer().run();
		}
	  
}