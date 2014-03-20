package main.util.arrays;

import main.util.data.Packet;

public class PacketArray {
    private Packet[] x;
    private int pos;
    
    public PacketArray(int size) {
        x = new Packet[size];
    }
    
    public PacketArray(Packet[] x) {
        this.x = x;
    }
    
    public void add(Packet value) {
        x[pos] = value;
        pos++;
    }
    
    public void clear() { x = new Packet[x.length]; }
    public int size() { return x.length; }
    public int index() { return pos; }
    
    public Packet[] getValues() { 
        Packet[] returnValues = new Packet[pos];
        System.arraycopy(x, 0, returnValues, 0, pos);
        return returnValues;
    }
    public Packet get(int index) { return x[index]; }
    
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
