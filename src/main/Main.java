package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import main.math.Vector2D;
import main.util.Exporter;
import main.util.Filter;
import main.util.Parser;
import main.util.data.Data;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        
        String[] hashtags = new String[] 
            {"cyclonephailin","odisha","india","cyclone","news",
            "andhrapradesh","phailinfury","bhubaneswar","gopalpur","nari"};
        
        Vector2D gopalpurLoc = new Vector2D(84.92, 19.27);
        Vector2D mumbaiLoc = new Vector2D(72.8258, 18.975);
        Vector2D newDelhiLoc = new Vector2D(77.23, 28.61);
        
        Data[] data = Parser.parse("phailin_tweets.csv");
        
        Exporter.exportTweetsOverTimeCsv(Filter.filterNearLocation(Filter.filterGeoLoc(data),gopalpurLoc),
                1,"gopalpur.csv",true);
        Exporter.exportTweetsOverTimeCsv(Filter.filterNearLocation(Filter.filterGeoLoc(data),mumbaiLoc),
                1,"mumbai.csv",true);
        Exporter.exportTweetsOverTimeCsv(Filter.filterNearLocation(Filter.filterGeoLoc(data),newDelhiLoc),
                1,"new-delhi.csv",true);
    }
}