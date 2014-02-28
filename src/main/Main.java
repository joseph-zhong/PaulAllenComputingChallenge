package main;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Data[] data = Parser.parse("phailin_tweets.csv");
    }
}