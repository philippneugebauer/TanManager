package de.uniba.ppn.tananzeiger.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.SAXException;

import de.uniba.ppn.tananzeiger.logik.TANSpeicher;

public class XMLReader {
	XMLValidierung validierer = null;

	public XMLReader() {
		validierer = new XMLValidierung();
	}

	public TANSpeicher leseTAN(File file) throws JAXBException,
			SAXException, IOException {

		JAXBContext context = null;
		context = JAXBContext.newInstance(TANSpeicher.class);
		Unmarshaller um = null;
		um = context.createUnmarshaller();

		TANSpeicher tanListe = null;
		tanListe = (TANSpeicher) um.unmarshal(file);

		validierer.validiereXMLDatei(file);

		return tanListe;
	}
}
