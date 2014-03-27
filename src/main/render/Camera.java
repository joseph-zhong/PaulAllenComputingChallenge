package main.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Camera {

    private static Vector position = new Vector();
    private static Vector oldPosition = new Vector();
    private static Vector rotation = new Vector();
    private static Vector oldRotation = new Vector();
    private static final float speed = 0.045f;
    
    public static float getCamX() { return position.getX(); }
    public static float getCamY() { return position.getY(); }
    public static float getCamZ() { return position.getZ()+2; }
    
    public static float getDistance(float x, float y, float z) {
        return new Vector(x-position.getX(),y-position.getY(),z-position.getZ()).getMagnitude();
    }
    
    public static float getFacingHoriz() {
        return (270+rotation.getZ())%360;
    }
    
    public static float getRelativeDirectionHoriz(int x, int y) {
        return (float) (getFacingHoriz() - Math.atan2(position.getY()-y,position.getX()-x));
    }
    
    public static float getFacingVert() {
        return -rotation.getX();
    }
    
    public static void init() {
        Mouse.setGrabbed(true);
    }
    
    public static boolean hasNotMoved() {
        return oldPosition.equals(position) && rotation.equals(oldRotation);
    }
    
    public static String update(int delta) {
        oldPosition.set(position);
        oldRotation.set(rotation);
        updateRotation(delta);
        updatePosition(delta);
        updatePerspective();
        return position.toString();
    }

    public static void updatePosition(int delta) {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.setX(position.getX()-(float)(Math.sin(-rotation.getZ()*Math.PI/180)*speed*delta));
            position.setY(position.getY()-(float)(Math.cos(-rotation.getZ()*Math.PI/180)*speed*delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.setX(position.getX()+(float)(Math.sin(-rotation.getZ()*Math.PI/180)*speed*delta));
            position.setY(position.getY()+(float)(Math.cos(-rotation.getZ()*Math.PI/180)*speed*delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.setX(position.getX()+(float)(Math.sin((-rotation.getZ()-90)*Math.PI/180)*speed*delta));
            position.setY(position.getY()+(float)(Math.cos((-rotation.getZ()-90)*Math.PI/180)*speed*delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.setX(position.getX()+(float)(Math.sin((-rotation.getZ()+90)*Math.PI/180)*speed*delta));
            position.setY(position.getY()+(float)(Math.cos((-rotation.getZ()+90)*Math.PI/180)*speed*delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            position.setZ(position.getZ()+speed*delta);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            position.setZ(position.getZ()-speed*delta);
    }

    public static void updateRotation(int delta) {
        //Mouse Input for looking around...
        if (Mouse.isGrabbed()) {
            float mouseDX = Mouse.getDX()*0.128f;
            float mouseDY = Mouse.getDY()*0.128f;
            
            if (rotation.getZ()+mouseDX>=360)
                rotation.setZ(rotation.getZ()+mouseDX-360);
            else if (rotation.getZ()+mouseDX<0)
                rotation.setZ(rotation.getZ()+mouseDX+360);
            else
                rotation.setZ(rotation.getZ()+mouseDX);
            
            if (rotation.getX()-mouseDY>=-89&&rotation.getX()-mouseDY<=89)
                rotation.setX(rotation.getX()-mouseDY);
            else if (rotation.getX()-mouseDY<-89)
                rotation.setX(-89);
            else if (rotation.getX()-mouseDY>89)
                rotation.setX(89);
        }
    }
    
    public static void updatePerspective() {
        GL11.glRotatef(rotation.getX(),1,0,0);
        GL11.glRotatef(rotation.getY(),0,0,1);
        GL11.glRotatef(rotation.getZ(),0,1,0);
        GL11.glTranslatef(-position.getX(),-position.getZ(),-position.getY());
    }
}