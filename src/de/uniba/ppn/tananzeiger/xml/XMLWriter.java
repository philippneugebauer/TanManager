package de.uniba.ppn.tananzeiger.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import de.uniba.ppn.tananzeiger.logik.TANSpeicher;

public class XMLWriter {
	public XMLWriter() {

	}

	public void schreibeTans(TANSpeicher tanListe, File file)
			throws JAXBException {

		JAXBContext context = null;
		context = JAXBContext.newInstance(TANSpeicher.class);

		Marshaller ms = null;
		ms = context.createMarshaller();

		try {
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (PropertyException e) {
			System.out
					.println("Eine Eigenschaft des XML-Lesers wurde falsch gesetzt!");
		}

		ms.marshal(tanListe, file);

	}
}
