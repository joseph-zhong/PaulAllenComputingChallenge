package main.util;

public class Vector2D {
    private double x;
    private double y;
    private double magnitude;
    public double getX() { return x; }
    public double getY() { return y; }
    public double getMagnitude() { return magnitude; }
    
    private void magnitude() { magnitude = Math.sqrt(x*x + y*y); }
    
    public Vector2D(double x) { this.x=x; magnitude(); }
    public Vector2D(double x, double y) { this.x=x; this.y=y; magnitude(); }
    
    public static double dotProduct(Vector2D v1, Vector2D v2) {
        return v1.getX()*v2.getX() + v1.getY()*v2.getY();
    }
    
    public static double angle(Vector2D v1, Vector2D v2) {
        return Math.acos(dotProduct(v1,v2)/(v1.getMagnitude()*v2.getMagnitude()));
    }
}
