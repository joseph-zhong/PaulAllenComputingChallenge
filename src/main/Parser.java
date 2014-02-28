package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/** A class built around a buffered reader which can sort and stuff. */
public class Parser {
    
    private BufferedReader reader;
    
    public Parser(String ref) {
        try {
            reader = new BufferedReader(new FileReader(ref));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }
    }
}