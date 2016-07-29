package tut.src.com.shc.tutorials.lwjgl;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * @author Sri Harsha Chilakapati
 */
public class Game
{
    private static long    windowID;
    private static boolean running;

    

    public Game()
    {
        if (glfwInit() != true)
        {
            System.err.println("Error initializing GLFW");
            System.exit(1);
        }

       
        windowID = glfwCreateWindow(640, 480, "My GLFW Window", NULL, NULL);

        if (windowID == NULL)
        {
            System.err.println("Error creating a window");
            System.exit(1);
        }

        glfwMakeContextCurrent(windowID);
        GL.createCapabilities();

        glfwSwapInterval(1);
    }

    public void init()
    {
    }

    public void update(float delta)
    {
    }

    public void render(float delta)
    {
    }

    public void dispose()
    {
    }

    public void start()
    {
        float now, last, delta;

        last = 0;

      
        // Initialise the Game
        init();

        running = true;

        // Loop continuously and render and update
        while (running && glfwWindowShouldClose(windowID) != true)
        {
            // Get the time
            now = (float) glfwGetTime();
            delta = now - last;
            last = now;

            // Update and render
            update(delta);
            render(delta);

            // Poll the events and swap the buffers
            glfwPollEvents();
            glfwSwapBuffers(windowID);
        }

        // Dispose the game
        dispose();

     

        // Destroy the window
        glfwDestroyWindow(windowID);
        glfwTerminate();

        System.exit(0);
    }

    public void end()
    {
        running = false;
    }


    public static long getWindowID()
    {
        return windowID;
    }

    public static void main(String[] args)
    {
        new Game().start();
    }
}
