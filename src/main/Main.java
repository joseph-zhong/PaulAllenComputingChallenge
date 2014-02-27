package main;

import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        Data[] data = Parser.parse("phailin_tweets.csv");
    }
}