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
        //Lat-Long for Gopalpur and Mumbai, respectively
        Vector2D[] locs = new Vector2D[] { new Vector2D(84.92, 19.27), new Vector2D(72.8258, 18.975), new Vector2D(77.23, 28.61) };
        Data[] data = Parser.parse("phailin_tweets.csv");
        
        Exporter.exportTweetsOverTimeCsv(Filter.filterNearLocation(Filter.filterGeoLoc(data),locs[0]),
                1,"gopalpur.csv",true);
        Exporter.exportTweetsOverTimeCsv(Filter.filterNearLocation(Filter.filterGeoLoc(data),locs[1]),
                1,"mumbai.csv",true);
        Exporter.exportTweetsOverTimeCsv(Filter.filterNearLocation(Filter.filterGeoLoc(data),locs[2]),
                1,"new-delhi.csv",true);
    }
}