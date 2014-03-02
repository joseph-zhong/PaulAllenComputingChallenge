package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Contains static methods to export Data[] */
public class Exporter {
    
    public static void exportTweetsOverTimeCsv(Data[] data, int hoursStep) throws IOException {
        int[] tweets = exportTweetsOverTime(data,hoursStep);
        
        StringBuilder result = new StringBuilder();
        
        Date current = (Date) data[0].time.clone();
        
        int milliStep = hoursStep*3600000;
        
        DateFormat df = new SimpleDateFormat("MM/dd HH:mm");
        
        result.append("Time,Tweets\n");
        
        for (int i=0;i<tweets.length;i++) {
            result.append(df.format(current)).append(",");
            current.setTime(current.getTime()+milliStep);
            result.append(tweets[i]).append("\n");
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tweets_over_time.csv")));
        out.print(result.toString());
        out.close();
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
        Data[] geoData = Sorter.sortByTime(Filter.filterGeoLoc(data));
        int[] timeData = exportTweetsOverTime(geoData, 1);
        Date current = (Date)geoData[0].time.clone();
        current.setTime(current.getTime() + 3600000);
        
        String jsonString = "[";
        int timePos = 0;
        for(int i = 0; i < geoData.length; i++) {
            jsonString += geoData[i].latitude + ", ";
            jsonString += geoData[i].longitude + ", ";
            if(geoData[i].time.before(current))
                jsonString += timeData[timePos];
            else {
                timePos++;
                current.setTime(current.getTime() + 3600000);
                jsonString += timeData[timePos];
            }
            if(i < geoData.length - 1)
                jsonString += ", ";
        }
        jsonString += "]";
        
        File jsonFile = new File("resources", "JsonData.json");
        try { 
            FileWriter jsonWriter = new FileWriter(jsonFile);
            jsonWriter.append(jsonString);
        } catch (IOException e) { System.err.println("Error: " + e); }
        
        System.out.println(jsonString);
    }
}