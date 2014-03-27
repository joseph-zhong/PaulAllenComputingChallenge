package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import main.util.Exporter;
import main.util.Filter;
import main.util.Parser;
import main.util.data.Data;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        //List of top 11 hashtags including #phailin, top ten excluding
        String[] hashtags = new String[] 
            {"cyclonephailin","odisha","india","cyclone","news",
            "andhrapradesh","phailinfury","bhubaneswar","gopalpur","nari"};
        //Lat-Long for Gopalpur and Mumbai, respectively
        //Point[] locations = new Point[] { new Point(19.27, 84.92), new Point(18.975, 72.8258) };
        Data[] data = Parser.parse("phailin_tweets.csv");
        Exporter.exportTweetsOverTimeCsv(Filter.filterHashtag(data,"phailin"),1,
                    0 + "_" + "phailin" + ".csv",true);
        for (int i=0;i<hashtags.length;i++) {
            Exporter.exportTweetsOverTimeCsv(Filter.filterHashtag(data,hashtags[i]),1,
                    (i+1) + "_" + hashtags[i] + ".csv",false);
        }
    }
}