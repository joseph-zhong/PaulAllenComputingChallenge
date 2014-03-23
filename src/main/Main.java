package main;

import main.util.data.Data;
import main.util.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import main.util.data.Point;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        String[] hashtags = new String[] 
            {"phailin","cyclonephailin","odisha","india","cyclone","news",
            "andhrapradesh","phailinfury","bhubaneswar","gopalpur","nari"};
        Point[] locations = new Point[] { new Point(19.27, 84.92), new Point(18.975, 72.8258) };
        Data[] data = Parser.parse("phailin_tweets.csv");
    }
}