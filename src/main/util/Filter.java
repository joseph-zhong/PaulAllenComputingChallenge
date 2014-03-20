package main.util;

import main.util.data.Data;
import java.util.ArrayList;
import main.util.arrays.DataArray;

public class Filter {
    public static Data[] filterGeo(Data[] set) {
        ArrayList<Data> filteredSet = new ArrayList<>();
        for(Data item : set)
            if(item.location != null)
                filteredSet.add(item);
        
        return filteredSet.toArray(new Data[filteredSet.size()]);
    }
    
    public static Data[] filterGeoLoc(Data[] set) {
        ArrayList<Data> filteredSet = new ArrayList<>();
        for(Data item : set)
            if(item.latitude != 0 || item.longitude != 0)
                filteredSet.add(item);
        
        return filteredSet.toArray(new Data[filteredSet.size()]);
    }
    
    public static Data[] filterHashtag(Data[] set, String hashtag) {
        Data[] geoData = Sorter.sortByTime(Filter.filterRetweet(set));
        DataArray array = new DataArray(set.length);
        for(Data item : geoData)
            if(item.hashtags.contains(hashtag))
                array.add(item);
        
        return array.getValues();
    }
    
    public static Data[] filterRetweet(Data[] set) {
        ArrayList<Data> filteredSet = new ArrayList<>();
        for(Data item : set)
            if(!item.isRetweet)
                filteredSet.add(item);
        
        return filteredSet.toArray(new Data[filteredSet.size()]);
    }
}
