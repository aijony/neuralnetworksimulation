import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Graphics{
	
	private Display display;
	

	public void run() {
		display = new Display(300, 300, "jason");
		display.loop();
	}
		
	  
	public static void main(String[] args) {
		new Graphics().run();
	}
	  
}