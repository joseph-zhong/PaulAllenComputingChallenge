package main.util.arrays;

public class FloatArray {
    private float[] x;
    private int pos;
    
    public FloatArray(int size) {
        x = new float[size];
    }
    
    public FloatArray(float[] x) {
        this.x = x;
    }
    
    public void add(int value) {
        x[pos] = (float) value;
        pos++;
    }
    public void add(float value) {
        x[pos] = value;
        pos++;
    }
    public void add(double value) {
        x[pos] = (float) value;
        pos++;
    }
    
    public void clear() { x = new float[x.length]; }
    public int size() { return x.length; }
    public int index() { return pos; }
    
    public float[] getValues() {
        float[] returnValues = new float[pos];
        System.arraycopy(x, 0, returnValues, 0, pos);
        return returnValues;
    }
    public float get(int index) { return x[index]; }
    
    @Override
    public String toString() {
        String returnString = "";
        
        for(int i = 0; i < pos; i++) {
            returnString += x[i];
            if(i < pos - 1)
                returnString += ", ";
        }
        
        return returnString;
    }
}
