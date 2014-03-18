package main.render;

import java.nio.FloatBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Renderer {
    
    private FloatBuffer vertices;
    private FloatBuffer colorVertices;
    
    public Renderer() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.create();
        } catch (LWJGLException e) {
            System.out.println("Error creating window: " + e);
        }
    }
    
    public void addPoint(float lat, float lng, int size, float r, float g, float b) {
        float phi = (float)((90-lat)*Math.PI/180);
        float theta = (float)((180-lng)*Math.PI/180);

        float x = (float)(200*Math.sin(phi)*Math.cos(theta));
        float y = (float)(200*Math.cos(phi));
        float z = (float)(200*Math.sin(phi)*Math.sin(theta));
        
        putPoint(x,y,z,r,g,b);
    }
    
    private void putPoint(float x, float y, float z, float r, float g, float b) {
        vertices.put(x);
        vertices.put(y);
        vertices.put(z);
        colorVertices.put(r);
        colorVertices.put(g);
        colorVertices.put(b);
    }
    
    public void init3DGL() {
        //GL11.glEnable(GL11.GL_TEXTURE_2D); // Allows 2D textures.
        GL11.glShadeModel(GL11.GL_SMOOTH); // Smoother textures.
        GL11.glClearColor(0.4f,0.6f,1.0f,0.0f); // BG color. 6698FF
        GL11.glClearDepth(1.0); // Buffer depth, allows objects to draw over things behind them.
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Depth testing (see above).
        GL11.glDepthFunc(GL11.GL_LEQUAL); // Type of depth testing.
        
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        
        GL11.glMatrixMode(GL11.GL_PROJECTION); // Sets matrix mode to displaying pixels.
        GL11.glLoadIdentity(); // Loads the above matrix mode.
        
        // Sets default perspective location.                       Render Distances: Min   Max
        GLU.gluPerspective(45.0f,(float)Display.getWidth()/(float)Display.getHeight(),0.1f,300.0f);
        
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Sets the matrix to displaying objects.
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT,GL11.GL_NICEST); // Something unimportant for quality.
        GL11.glEnable(GL11.GL_CULL_FACE);
    }
}
