package de.uniba.ppn.tananzeiger.logik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Philipp Neugebauer
 */
public class TextReader {
    BufferedReader reader;
    ArrayList<String> textStorage = new ArrayList<String>();

    public ArrayList<String> readFile(File file) throws IOException {
        textStorage.clear();
        reader = new BufferedReader(new FileReader(file));
        String lineStorage;
        while ((lineStorage = reader.readLine()) != null) {
            boolean isNumber = false;
            try {
                lineStorage = lineStorage.trim();
                Long.parseLong(lineStorage);
                isNumber = true;
            } catch (NumberFormatException e) {
                isNumber = false;
            } finally {
                if (isNumber == true) {
                    textStorage.add(lineStorage);
                }
            }
        }
        reader.close();
        return textStorage;
    }
}
