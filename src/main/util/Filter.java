package main.util;

import java.util.ArrayList;
import main.math.Vector2D;
import main.util.data.Data;

public class Filter {
    
    /** @return Elements with geolocation data (coordinates). */
    public static Data[] filterGeoLoc(Data[] data) {
        ArrayList<Data> filteredData = new ArrayList<Data>();
        filteredData.add(data[0]);
        
        for(Data item : data)
            if(item.locationCoords != null)
                filteredData.add(item);
        
        filteredData.add(data[data.length-1]);
        return filteredData.toArray(new Data[0]);
    }
    
    /** @return Elements with specified hashtag. */
    public static Data[] filterHashtag(Data[] data, String hashtag) {
        ArrayList<Data> filteredData = new ArrayList<Data>(data.length+1);
        filteredData.add(data[0]);
        
        for(Data item : data)
            if(item.hashtags.contains(hashtag))
                filteredData.add(item);
        
        filteredData.add(data[data.length-1]);
        return filteredData.toArray(new Data[0]);
    }
    
    /** @return Elements that aren't retweets. */
    public static Data[] filterRetweet(Data[] data) {
        ArrayList<Data> filteredData = new ArrayList<Data>();
        filteredData.add(data[0]);
        
        for(Data item : data)
            if(!item.isRetweet)
                filteredData.add(item);
        
        filteredData.add(data[data.length-1]);
        return filteredData.toArray(new Data[0]);
    }
    
    /** @return Elements within a hundred miles of the point. */
    public static Data[] filterNearLocation(Data[] data, Vector2D point) {
        ArrayList<Data> filteredData = new ArrayList<Data>(data.length+1);
        filteredData.add(data[0]);
        
        for(Data item : data)
            if(item.isWithinDistance(point,100.0))
                filteredData.add(item);
        
        filteredData.add(data[data.length-1]);
        return filteredData.toArray(new Data[0]);
    }
}
