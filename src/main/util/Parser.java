package main.util;

import main.util.data.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import main.util.arrays.DataArray;

/** A class built around a buffered reader which can sort and stuff. */
public class Parser {
        
    public static Data[] parse(String ref) throws FileNotFoundException, ParseException, IOException {
        BufferedReader reader;
        DataArray data = new DataArray(10000);

        reader = new BufferedReader(new FileReader(ref));
        
        reader.readLine(); // Header.
        String line = null;
        int i = 0;
        while(true) {
            line = reader.readLine();
            try { data.add(new Data(line,i)); }
            catch(NullPointerException e) { break; }
            i++;
        }
        return data.getValues();
    }
}