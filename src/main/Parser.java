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
                
                Data newData = new Data(line,i);
                
                data[i] = newData;
            }
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }
    }
}