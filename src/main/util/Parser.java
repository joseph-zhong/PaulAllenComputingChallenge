package main.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import main.util.data.Data;

public class Parser {
    
    /** Reads data from a text file. */
    public static Data[] parse(String ref) throws FileNotFoundException, ParseException, IOException {
        BufferedReader reader;
        Data[] data = new Data[6671];

        reader = new BufferedReader(new FileReader(ref));
        
        reader.readLine(); // Header.
        String line = null;
        for (int i=0;i<6671;i++) {
            line = reader.readLine();
            data[i] = new Data(line);
        }
        return data;
    }
}