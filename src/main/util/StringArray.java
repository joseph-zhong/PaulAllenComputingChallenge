package main.util;

public class StringArray {
    private String[] x;
    private int pos;
    
    public StringArray(int size) {
        x = new String[size];
    }
    
    public void add(String value) {
        x[pos] = value;
        pos++;
    }
    
    public void clear() { x = new String[x.length]; }
    public int size() { return x.length; }
    public int index() { return pos; }
    
    public String[] getValues() { 
        String[] returnValues = new String[pos];
        System.arraycopy(x, 0, returnValues, 0, pos);
        return returnValues;
    }
    public String get(int index) { return x[index]; }
    
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
