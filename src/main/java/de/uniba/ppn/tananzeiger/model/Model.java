package de.uniba.ppn.tananzeiger.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import de.uniba.ppn.tananzeiger.logik.TANSpeicher;
import de.uniba.ppn.tananzeiger.logik.TextReader;
import de.uniba.ppn.tananzeiger.xml.XMLReader;
import de.uniba.ppn.tananzeiger.xml.XMLWriter;

public class Model extends Observable {
	private TANSpeicher tanList;
	private File file;
	private XMLWriter writer;
	private TextReader reader;
	private XMLReader xmlReader;

	public Model(File file) {
		this.file = file;
		tanList = new TANSpeicher();
		writer = new XMLWriter();
		reader = new TextReader();
		xmlReader = new XMLReader();
	}

	public ArrayList<String> getList() {
		return tanList.getTanSpeicher();
	}

	public void deleteTan() throws JAXBException {
		getList().remove(0);
		this.setChanged();
		this.notifyObservers();
		writeXml();
	}

	public void readXml() throws JAXBException, SAXException, IOException {
		this.tanList = xmlReader.readTanXml(file);
		this.setChanged();
		this.notifyObservers();
	}

	public void loadXML(File file) throws JAXBException, SAXException,
			IOException {
		readXml();
		writeXml();
	}

	public void writeXml() throws JAXBException {
		writer.writeTanXml(tanList, file);
	}

	public int getSize() {
		return getList().size();
	}

	public void readFile(File file) throws IOException, JAXBException {
		tanList.setTanSpeicher(reader.readFile(file));
		this.setChanged();
		this.notifyObservers();
		writeXml();
	}
}
