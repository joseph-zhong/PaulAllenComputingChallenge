package main;

import java.util.ArrayList;

public class Filter {
    public static Data[] filter(Data[] set) {
        ArrayList<Data> filteredSet = new ArrayList<Data>();
        for(Data item : set)
            if(!item.isRetweet && item.location != null)
                filteredSet.add(item);
        
        return filteredSet.toArray(new Data[filteredSet.size()]);
    }
}
