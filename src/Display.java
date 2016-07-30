import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

public class Display {

	private Renderer renderer;
	// Window handler for GLFW
	private long windowID;

	/*
	 * Initializes GLFW and setups all window/GLFW properties
	 */
	public Display(int width, int height, String title) {

		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit())
			throw new IllegalStateException("GLFW did not initialize");

		// Window hints are essentially settings
		// TODO Configure
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		// Create configured window
		windowID = glfwCreateWindow(width, height, title, NULL, NULL);
		if (windowID == NULL)
			throw new RuntimeException("GLFW window was not created");

		// TODO Input Callback

		// Sets resolution to monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(windowID, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

		glfwMakeContextCurrent(windowID);
		glfwSwapInterval(1);
		glfwShowWindow(windowID);

		renderer = new Renderer();
	}

	public void loop() {

		while (glfwWindowShouldClose(windowID) == false) {

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			renderer.update();
			renderer.render();

			glfwPollEvents();
			glfwSwapBuffers(windowID);

		}
		renderer.dispose();
		terminate();
	}

	public void terminate() {

		glfwDestroyWindow(windowID);
		glfwTerminate();
	}
}
