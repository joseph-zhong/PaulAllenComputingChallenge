package main;

public class Packet {
    public double latitude;
    public double longitude;
    public double magnitude;
    
    public Packet(double latitude, double longitude, double magnitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
    }
    
    public static Packet getPacketFromData(Data data) {
        return new Packet(data.latitude, data.longitude, 10);
    }
    
    public static Packet getPacketFromData(Data data, double magnitude) {
        return new Packet(data.latitude, data.longitude, magnitude);
    }
}
