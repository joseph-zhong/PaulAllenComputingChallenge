package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        Data[] data = Parser.parse("phailin_tweets.csv");
        
        System.out.println(Arrays.toString(Exporter.exportTweetsOverTime(data,5)));
    }
}