package de.uniba.ppn.tananzeiger.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidierung {
	public XMLValidierung() {

	}

	public void validiereXMLDatei(File file) throws SAXException, IOException {

		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		Schema schema = null;
		schema = sf.newSchema(XMLValidierung.class
				.getResource("schema/schema1.xsd"));
		/*
		 * setzt das generierte Schema als Überprüfungsschema fest
		 */
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(file));

	}
}
