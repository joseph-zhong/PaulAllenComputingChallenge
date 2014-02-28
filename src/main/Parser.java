package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** A class built around a buffered reader which can sort and stuff. */
public class Parser {
    
    private BufferedReader reader;
    private Data[] data = new Data[6726];
    
    public Parser(String ref) {
        try {
            reader = new BufferedReader(new FileReader(ref));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }
        
        try {
            reader.readLine(); // Header.
            String line = null;
            for (int i=0;i<6671;i++) {
                line = reader.readLine();
                                
                String[] split = line.split("|");
                
                Data newData = new Data();
                
                System.out.println(split[0]);
                newData.tweetId = Long.parseLong(split[0]);
                newData.time = split[1];
                newData.userId = Integer.parseInt(split[2]);
                newData.followers = Integer.parseInt(split[3]);
                newData.accountLocation = split[4];
                newData.tweetSource = split[5];
                
                
                
                data[i] = newData;
            }
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }
    }
}