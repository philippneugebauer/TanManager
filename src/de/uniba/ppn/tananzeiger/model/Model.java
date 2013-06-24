package de.uniba.ppn.tananzeiger.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.xml.bind.JAXBException;

import de.uniba.ppn.tananzeiger.logik.TANSpeicher;
import de.uniba.ppn.tananzeiger.logik.TextReader;
import de.uniba.ppn.tananzeiger.xml.XMLReader;
import de.uniba.ppn.tananzeiger.xml.XMLWriter;

public class Model extends Observable {
	private TANSpeicher liste = null;
	private ArrayList<String> speicher = new ArrayList<String>();
	private File file = null;
	private XMLWriter writer = null;
	private TextReader reader = null;
	private XMLReader XMLreader = null;

	public Model(File file) {
		this.file = file;
		liste = new TANSpeicher();
		writer = new XMLWriter();
		reader = new TextReader();
		XMLreader = new XMLReader();
	}

	public ArrayList<String> getList() {
		return speicher;
	}

	public void speichereTAN(ArrayList<String> loadSpeicher) {
		liste.setTanSpeicher(loadSpeicher);
		this.speicher = loadSpeicher;
	}

	public void löscheTan() throws JAXBException {
		speicher.remove(0);
		schreibeXML();
	}

	public void readXML() throws Exception {
		try {
			this.liste = XMLreader.leseTAN(file);
			this.speicher = liste.getTanSpeicher();
		} catch (Exception e) {
			throw new Exception("Die Datei konnte nicht geladen werden!");
		}
	}

	public void loadXML(File file) throws Exception {
		try {
			this.liste = XMLreader.leseTAN(file);
			this.speicher = liste.getTanSpeicher();
			schreibeXML();
		} catch (Exception e) {
			throw new Exception("Die Datei konnte nicht geladen werden!");
		}
	}

	public void schreibeXML() throws JAXBException {
		writer.schreibeTans(liste, file);
	}

	public int getSize() {
		return speicher.size();
	}

	public void readFile(File file) throws IOException, JAXBException {
		this.speicher = reader.readFile(file);
		liste.setTanSpeicher(speicher);
		schreibeXML();
	}
}
