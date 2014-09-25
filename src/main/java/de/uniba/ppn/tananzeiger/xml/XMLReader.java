package de.uniba.ppn.tananzeiger.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.SAXException;

import de.uniba.ppn.tananzeiger.logik.TANSpeicher;

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Philipp Neugebauer
 */
public class XMLReader {

	XMLValidator validator;
	Unmarshaller unmarshaller;

	{
		validator = new XMLValidator();
		try {
			JAXBContext context = JAXBContext.newInstance(TANSpeicher.class);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			// ignore
		}
	}

	public TANSpeicher readTanXml(File file) throws JAXBException,
	SAXException, IOException {
		TANSpeicher tanList = (TANSpeicher) unmarshaller.unmarshal(file);
		validator.validateXmlFile(file);

		return tanList;
	}
}
