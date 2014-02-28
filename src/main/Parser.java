package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** A class built around a buffered reader which can sort and stuff. */
public class Parser {
    
    private BufferedReader reader;
    private Data[] data = new Data[6671];
    
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
                                
                String[] split = line.split("\\|");
                
                Data newData = new Data();
                
                // ID
                newData.id = Integer.parseInt(split[0]);
                
                // Tweet ID
                split[1] = split[1].replace("*", "");
                newData.tweetId = Long.parseLong(split[1]);
                
                // Time
                newData.time = split[2];
                
                // User ID
                split[3] = split[3].replace("*", "");
                newData.userId = Integer.parseInt(split[3]);
                
                // Followers
                newData.followers = Integer.parseInt(split[4]);
                
                // Location
                newData.accountLocation = split[5];
                
                // Source
                newData.tweetSource = split[6];
                
                data[i] = newData;
            }
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }
    }
}