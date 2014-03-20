package main;

import main.util.data.Data;
import main.util.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import main.util.Filter;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        String[] hashtags = new String[] 
            {"phailin","cyclonephailin","odisha","india","cyclone","news",
            "andhrapradesh","phailinfury","bhubaneswar","gopalpur","nari"};
        Data[] data = Parser.parse("phailin_tweets.csv");
        Data[] temp;
        for(int i = 0; i < hashtags.length; i++) {
            temp = Filter.filterHashtag(data, hashtags[i]);
            System.out.println(temp.length);
        }
    }
}