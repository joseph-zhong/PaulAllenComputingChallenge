package main.util.data;

public class Packet {
    public Point point;
    public double magnitude;
    
    public Packet(double latitude, double longitude, double magnitude) {
        this.point = new Point(latitude, longitude);
        this.magnitude = magnitude;
    }
    
    public static Packet getPacketFromData(Data data) {
        return new Packet(data.locationCoords.latitude, data.locationCoords.longitude, 10);
    }
    
    public static Packet getPacketFromData(Data data, double magnitude) {
        return new Packet(data.locationCoords.latitude, data.locationCoords.longitude, magnitude);
    }
    
    public static Packet[] getPacketFromData(Data[] data) {
        Packet[] returnPacket = new Packet[data.length];
        for(int i = 0; i < data.length; i++)
            returnPacket[i] = Packet.getPacketFromData(data[i]);
        
        return returnPacket;
    }
}
