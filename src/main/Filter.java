package main;

import java.util.ArrayList;
import java.util.HashMap;

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
    
    public static Data[][] filterHashtag(Data[] set) {
        Data[] geoData = Sorter.sortByTime(Filter.filterRetweet(Filter.filterGeoLoc(set)));
        HashMap<String, Integer> hashTags = new HashMap<>();
        ArrayList<String> numHash = new ArrayList<>();
        Data[][] filteredSet = new Data[3][geoData.length];
        for(Data item : geoData) {
            for(String hashtag : item.hashtags) {
                if(!hashTags.keySet().contains(hashtag))
                    hashTags.put(hashtag, 1);
                else {
                    int count = hashTags.get(hashtag);
                    hashTags.remove(hashtag);
                    hashTags.put(hashtag, count++);
                }
            }
        }
        
        for(int i = 0; i < 3; i++)
            numHash.add((String)hashTags.keySet().toArray()[i]);
                    
        
        for(String hashtag : hashTags.keySet()) {
            if(!hashtag.isEmpty()) {
                for(int i = 0; i < numHash.size(); i++) {
                    if(hashTags.get(hashtag) > hashTags.get(numHash.get(i))) {
                        numHash.add(i, hashtag);
                        numHash.remove(3);
                    }
                }
            }
        }
        
        int setIndex = 0;
        int index = 0;
        
        for(String hashTag : numHash) {
            for(Data item : geoData) {
                if(item.hashtags.contains(hashTag)) {
                    filteredSet[setIndex][index] = item;
                    index++;
                }
            }
            setIndex++;
        }
        
        return filteredSet;
    }
    
    public static Data[] filterRetweet(Data[] set) {
        ArrayList<Data> filteredSet = new ArrayList<>();
        for(Data item : set)
            if(!item.isRetweet)
                filteredSet.add(item);
        
        return filteredSet.toArray(new Data[filteredSet.size()]);
    }
}
