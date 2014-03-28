package main.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.util.data.Data;
import main.util.data.Packet;

/** Contains static methods to export Data[] */
public class Exporter {
    
    public static void exportTweetsOverTimeCsv(Data[] data, int hoursStep, String file, boolean time) throws IOException {
        int[] tweets = exportTweetsOverTime(data,hoursStep);
        
        StringBuilder result = new StringBuilder();
        
        Date current = (Date) data[0].time.clone();
        
        int milliStep = hoursStep*3600000;
        
        DateFormat df = new SimpleDateFormat("MM/dd HH:mm");
        
        if (time)
            result.append("Time,");
        result.append("Tweets\n");
        
        for (int i=0;i<tweets.length;i++) {
            if (time)
                result.append(df.format(current)).append(",");
            current.setTime(current.getTime()+milliStep);
            result.append(tweets[i]).append("\n");
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("exports\\" + file)));
        out.print(result.toString());
        out.close();
    }
    
    public static void exportTweetsOverTimeCsv(Data[] data, int hoursStep) throws IOException {
        exportTweetsOverTimeCsv(data,hoursStep,"tweets_over_time.csv",true);
    }
    
    public static String exportTweetsOverTimeAscii(Data[] data, int hoursStep, int scale) {
        int[] tweets = exportTweetsOverTime(data,hoursStep);
        
        StringBuilder result = new StringBuilder();
        
        Date current = (Date) data[0].time.clone();
        
        int milliStep = hoursStep*3600000;
        
        DateFormat df = new SimpleDateFormat("MM/dd HH:mm");
        
        for (int i=0;i<tweets.length;i++) {
            result.append(df.format(current)).append(" - ");
            current.setTime(current.getTime()+milliStep);
            result.append(df.format(current)).append(": ");
            for (int j=0;j<tweets[i]/scale;j++)
                result.append("-");
            result.append("\n");
        }
        return result.toString();
    }
    
    public static int[] exportTweetsOverTime(Data[] data, int hoursStep) {
        data = Sorter.sortByTime(data);
        
        Date current = (Date) data[0].time.clone();
        
        int milliStep = hoursStep*3600000;
        
        int pos = -1;
        
        long duration = data[data.length-1].time.getTime()-data[0].time.getTime();
        
        int[] results = new int[(int)(duration/milliStep)+1];
        
        for (Data item : data) {
            if (!item.time.before(current)) {
                current.setTime(current.getTime()+milliStep);
                pos++;
            }
            results[pos]++;
        }
        
        return results;
    }
    
    public static Packet[] exportToPackets(Data[] data) {
        Packet[] returnPacket = new Packet[data.length];
        
        for(int i = 0; i < data.length; i++)
            returnPacket[i] = Packet.getPacketFromData(data[i]);
        
        return returnPacket;
    }
    
    public static void exportAsJson(Data[] data) {
        String tab = "    ";
        String jsonString = "[\n" + tab + "[\n" + (tab+tab);
        int timePos = 0;
        int i = 0;
        jsonString += "\'Series\', [";
        while(i < data.length) {
            jsonString += data[i].locationCoords.latitude + ", ";
            jsonString += data[i].locationCoords.longitude + ", ";
            jsonString += 10;
            if((i+1) < data.length)
                jsonString += ", "; 
            i++;
        }
        jsonString += "]\n" + tab + "]\n" + "]";   
        File jsonFile = new File("resources", "data.json");
        try { 
            PrintWriter jsonWriter = new PrintWriter(new BufferedWriter(new FileWriter(jsonFile)));
            jsonWriter.print(jsonString);
        } catch (IOException e) { System.err.println("Error: " + e); }
        
        System.out.println(jsonString);
    }
}