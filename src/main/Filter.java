package main;

import java.util.ArrayList;

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
    
    public static Data[] filterRetweet(Data[] set) {
        ArrayList<Data> filteredSet = new ArrayList<>();
        for(Data item : set)
            if(!item.isRetweet)
                filteredSet.add(item);
        
        return filteredSet.toArray(new Data[filteredSet.size()]);
    }
}
