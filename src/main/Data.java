package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private int id;
    private long tweetId;
    private String time;
    private int userId;
    private int followers;
    private String accountLocation;
    private String tweetSource;
    private ArrayList<String> hashtags;
    private double latitude;
    private double longitude;
    private String location;
    private String url1;
    private String expandedUrl1;
    private boolean isRetweet;
    private long originalTweetId;
    private int retweets; // Not sure on this one...
    
    public Data(String line, int lineNum) {
        String[] split = line.split("`");
        
        // ID
        id = Integer.parseInt(split[0]);
                
        // Tweet ID
        split[1] = split[1].replace("*", "");
        tweetId = Long.parseLong(split[1]);
                
        // Time
        time = split[2];
                
        // User ID
        split[3] = split[3].replace("*", "");
        userId = Integer.parseInt(split[3]);
        
        // Followers
        followers = Integer.parseInt(split[4]);
        
        // Location
        accountLocation = split[5];
                
        // Source
        tweetSource = split[6];
        
        //Hashtags
        hashtags = new ArrayList(Arrays.asList(split[7].split(" ")));
        
        //GPS-Lat
        latitude = !split[8].isEmpty() ? Double.parseDouble(split[8]) : latitude;
        
        //GPS-Long
        longitude = !split[9].isEmpty() ? Double.parseDouble(split[9]) : longitude;
        
        //Location
        location = !split[10].isEmpty() ? split[10] : location;
        
        //URL
        url1 = !split[11].isEmpty() ? split[11] : url1;
        
        //URL-Expanded
        expandedUrl1 = !split[12].isEmpty() ? split[12] : expandedUrl1;
        
        //Is Retweet
        isRetweet = Integer.parseInt(split[13]) == 1;
        
        //Original Tweet ID
        split[14] = split[14].replace("*", "");
        originalTweetId = Long.parseLong(split[14]);
        
        //Retweets
        split[15] = split[15].replace("*", "");
        retweets = Integer.parseInt(split[15]);
    }
}