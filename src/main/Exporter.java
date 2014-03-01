package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Contains static methods to export Data[] */
public class Exporter {
    
    public static String exportTweetsOverTimeString(Data[] data, int hoursStep) {
        int[] tweets = exportTweetsOverTime(data,hoursStep);
        
        StringBuilder result = new StringBuilder();
        
        Date current = (Date) data[0].time.clone();
        
        int milliStep = hoursStep*3600000;
        
        DateFormat df = new SimpleDateFormat("dd HH:mm");
        
        for (int i=0;i<tweets.length;i++) {
            result.append(df.format(current)).append(" - ");
            current.setTime(current.getTime()+milliStep);
            result.append(df.format(current)).append(": ").append(tweets[i]).append(" tweets.").append("\n");
        }
        return result.toString();
    }
    
    public static String exportTweetsOverTimeAscii(Data[] data, int hoursStep, int scale) {
        int[] tweets = exportTweetsOverTime(data,hoursStep);
        
        StringBuilder result = new StringBuilder();
        
        Date current = (Date) data[0].time.clone();
        
        int milliStep = hoursStep*3600000;
        
        DateFormat df = new SimpleDateFormat("dd HH:mm");
        
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
        
        for (int i=0;i<data.length;i++) {
            if (!data[i].time.before(current)) {
                current.setTime(current.getTime()+milliStep);
                pos++;
            }
            results[pos]++;
        }
        
        return results;
    }
    
    public static void exportAsJson(Data[] data) {
        Gson gsonInit = new Gson();
        String jsonString = "[" + gsonInit.toJson(data[0]);
        for(int i = 1; i < data.length; i++) {
            jsonString += ", " + gsonInit.toJson(data[i]);
        }
        jsonString += "]";
        
        File jsonFile = new File("resources", "JsonWriter.json");
        try { 
            FileWriter jsonWriter = new FileWriter(jsonFile);
            jsonWriter.append(jsonString);
        } catch (IOException e) { System.err.println("Error: " + e); }
        
        System.out.println(jsonString);
    }
}