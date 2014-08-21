package de.uniba.ppn.tananzeiger.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import de.uniba.ppn.tananzeiger.logik.TANSpeicher;

public class XMLWriter {

	private Marshaller marshaller;

	{
		try {
			JAXBContext context = JAXBContext.newInstance(TANSpeicher.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (JAXBException e) {
			// ignore
		}
	}

	public void writeTanXml(TANSpeicher tanListe, File file)
			throws JAXBException {
		marshaller.marshal(tanListe, file);
	}
}
