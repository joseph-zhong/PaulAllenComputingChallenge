package main;

import java.util.Date;

/** Contains static methods to export Data[] */
public class Exporter {
    
    public static int[] exportTweetsOverTime(Data[] data, int hoursStep) {
        data = Sorter.sortByTime(data);
        
        Date current = (Date) data[0].time.clone();
                
        int milliStep = hoursStep*3600000;
        
        int pos = 0;
        
        long duration = data[data.length-1].time.getTime()-data[0].time.getTime();
        
        int[] results = new int[(int)(duration/milliStep)+10];
        
        for (int i=0;i<data.length;i++) {
            if (data[i].time.after(current)) {
                current.setTime(current.getTime()+milliStep);
                pos++;
            }
            results[pos]++;
        }
        
        return results;
    }
    
    public static void exportAsJson(Data[] data) {
        // todo
    }
}