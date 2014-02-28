package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        Data[] data = Parser.parse("phailin_tweets.csv");
        Sorter.sortByTime(data);
        for (int i=0;i<50;i++)
            System.out.println(data[i].toString());
    }
}