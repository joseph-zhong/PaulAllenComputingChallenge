package main.render;

import java.nio.FloatBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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
    
    public void renderGlobe() {
        
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
}
