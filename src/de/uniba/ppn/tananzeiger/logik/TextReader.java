package de.uniba.ppn.tananzeiger.logik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextReader {
	BufferedReader reader = null;
	ArrayList<String> speicher = new ArrayList<String>();

	public TextReader() {
	}

	public ArrayList<String> readFile(File file) throws IOException {
		speicher.clear();
		reader = new BufferedReader(new FileReader(file));
		String zeilenSpeicher = null;
		while ((zeilenSpeicher = reader.readLine()) != null) {
			boolean isZahl = false;
			try {
				zeilenSpeicher = zeilenSpeicher.trim();
				Long.parseLong(zeilenSpeicher);
				isZahl = true;
			} catch (NumberFormatException e) {
				isZahl = false;
			} finally {
				if (isZahl == true) {
					speicher.add(zeilenSpeicher);
				}
			}
		}
		reader.close();
		return speicher;
	}
}
