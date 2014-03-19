package main.util;

import main.Data;

public class DataArray {
    private Data[] x;
    private int pos;
    
    public DataArray(int size) {
        x = new Data[size];
    }
    
    public void add(Data value) {
        x[pos] = value;
        pos++;
    }
    
    public void clear() { x = new Data[x.length]; }
    public int size() { return x.length; }
    public int index() { return pos; }
    
    public Data[] getValues() { 
        Data[] returnValues = new Data[pos];
        System.arraycopy(x, 0, returnValues, 0, pos);
        return returnValues;
    }
    public Data get(int index) { return x[index]; }
    
    @Override
    public String toString() {
        String returnData = "";
        
        for(int i = 0; i < pos; i++) {
            returnData += x[i];
            if(i < pos - 1)
                returnData += ", ";
        }
        
        return returnData;
    }
}
