package main.render;

public class Vector {
    
    private float x;
    private float y;
    private float z;
    
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setZ(float z) { this.z = z; }
    
    public Vector() {
        this(0,0,0);
    }
    
    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector add(Vector other) {
        return new Vector(this.x+other.x,this.y+other.y,this.z+other.z);
    }

    public void set(Vector other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other==null)
            return false;
        if (!other.getClass().isInstance(this))
            return false;
        Vector otherVector = (Vector) other;
        return otherVector.x==x && otherVector.y==y && otherVector.z==z;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Float.floatToIntBits(this.x);
        hash = 61 * hash + Float.floatToIntBits(this.y);
        hash = 61 * hash + Float.floatToIntBits(this.z);
        return hash;
    }
    
    @Override
    public String toString() {
        return "[" + (int)x + ", " + (int)y + ", " + (int)z + "]";
    }

    public float getMagnitude() {
        return (float) Math.sqrt(x*x+y*y+z*z);
    }
}