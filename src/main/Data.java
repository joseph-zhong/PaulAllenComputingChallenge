package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import main.util.Vector2D;

public class Data {
    
    public int id;
    public long tweetId;
    public Date time;
    public int userId;
    public int followers;
    public String accountLocation;
    public String tweetSource;
    public ArrayList<String> hashtags;
    public double latitude;
    public double longitude;
    public String location;
    public String url1;
    public String expandedUrl1;
    public boolean isRetweet;
    public long originalTweetId;
    public int retweets; // Not sure on this one...
    
    public Data(String line, int lineNum) throws ParseException {
        String[] split = line.split("`");
        
        // ID
        id = Integer.parseInt(split[0]);
                
        // Tweet ID
        split[1] = split[1].replace("*", "");
        tweetId = Long.parseLong(split[1]);
                
        // Time
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy kk:mm", Locale.ENGLISH);
        time = df.parse(split[2]);
                
        // User ID
        split[3] = split[3].replace("*", "");
        userId = Integer.parseInt(split[3]);
        
        // Followers
        followers = Integer.parseInt(split[4]);
        
        // Location
        accountLocation = split[5];
                
        // Source
        tweetSource = split[6];
        
        // Hashtags
        hashtags = new ArrayList(Arrays.asList(split[7].split("#")));
        for(int i = 0; i < hashtags.size(); i++) {
            if(hashtags.get(i).contains(",") || hashtags.get(i).contains(" ") || hashtags.get(i).contains("\'") || hashtags.get(i).contains("\""))
                //Probably the least efficient IF statement in existence
                hashtags.set(i, hashtags.get(i).replace(",", "").replace(" ", "").replace("\'", "").replace("\"", "").toLowerCase());
            else
                hashtags.set(i, hashtags.get(i).toLowerCase());
        }       
        hashtags.remove(0);
        
        // GPS-Lat
        latitude = !split[8].isEmpty() ? Double.parseDouble(split[8]) : latitude;
        
        // GPS-Long
        longitude = !split[9].isEmpty() ? Double.parseDouble(split[9]) : longitude;
        
        // Location
        location = !split[10].isEmpty() ? split[10] : location;
        
        // URL
        url1 = !split[11].isEmpty() ? split[11] : url1;
        
        // URL-Expanded
        expandedUrl1 = !split[12].isEmpty() ? split[12] : expandedUrl1;
        
        // Is Retweet
        isRetweet = Integer.parseInt(split[13]) == 1;
        
        // Original Tweet ID
        split[14] = split[14].replace("*", "");
        originalTweetId = Long.parseLong(split[14]);
        
        // Retweets
        split[15] = split[15].replace("*", "");
        retweets = Integer.parseInt(split[15]);
    }
    
    public boolean isWithinDistance(double distance, Vector2D referenceVector) {
        Vector2D locationVector = new Vector2D(latitude, longitude);
        double radius = 3956.6;
        return Vector2D.angle(locationVector, referenceVector) * radius <= distance;
    }
    
    @Override
    public String toString() {
        return time.toString() + " " + tweetId;
    }
}