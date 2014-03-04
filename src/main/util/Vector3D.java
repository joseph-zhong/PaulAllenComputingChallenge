package main.util;

public class Vector3D {
    private double x;
    private double y;
    private double z;
    private double magnitude;
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public double getMagnitude() { return magnitude; }
    
    private void magnitude() { magnitude = Math.sqrt(x*x + y*y + z*z); }
    
    public Vector3D(double x) { this.x=x; magnitude(); }
    public Vector3D(double x, double y) { this.x=x; this.y=y; magnitude(); }
    public Vector3D(double x, double y, double z) { this.x=x; this.y=y; this.z=z; magnitude(); }
    
    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.getX()*v2.getX() + v1.getY()*v2.getY() + v1.getZ()*v2.getZ();
    }
    
    public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        return new Vector3D(
                v1.getX()*v2.getZ() - v1.getZ()*v2.getX(),
                v1.getY()*v2.getZ() - v1.getZ()*v2.getY(),
                v1.getX()*v2.getY() - v1.getY()*v2.getX()
        );
    }
    
    public static double tripleScalar(Vector3D v1, Vector3D v2, Vector3D v3) {
        return dotProduct(v1, crossProduct(v2, v3));
    }
    
    public static double angle(Vector3D v1, Vector3D v2) {
        return Math.acos(dotProduct(v1,v2)/(v1.getMagnitude()*v2.getMagnitude()));
    }
}
