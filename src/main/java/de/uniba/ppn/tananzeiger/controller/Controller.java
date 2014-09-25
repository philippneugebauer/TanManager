package de.uniba.ppn.tananzeiger.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import de.uniba.ppn.tananzeiger.gui.FileChooser;
import de.uniba.ppn.tananzeiger.gui.View;
import de.uniba.ppn.tananzeiger.model.Model;

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Philipp Neugebauer
 */
public class Controller {
	private File file = null;
	private View view = null;
	private FileChooser chooser = null;
	private Model model = null;

	public Controller() {
		this.file = getOS();
		model = new Model(file);
		view = new View(this, model);
		model.addObserver(view);
		chooser = new FileChooser();
		if (file.isFile()) {
			try {
				model.readXml();
			} catch (JAXBException | SAXException | IOException e) {
				view.showException(e.getMessage());
			}
		}
	}

	public File getOS() {
		String os = System.getProperties().toString();
		int osStart = os.indexOf("os.name=");
		int osStop = os.indexOf(",", osStart);
		os = os.substring(osStart + 8, osStop);
		String path = System.getProperty("user.home");
		if (os.contains(" 7")) {
			path = path + File.separator + "Documents" + File.separator
					+ "TanAnzeiger";
		} else {
			path = path + File.separator + "TanAnzeiger";
		}
		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs();
		}
		file = new File(path + File.separator + "Tans.xml");
		return file;
	}

	public void chooseFile() {
		File file = chooser.chooseFile(null);
		if (file != null) {
			try {
				if (file.getName().endsWith("xml")) {
					model.loadXML(file);
				} else {
					model.readFile(file);
				}
			} catch (JAXBException | IOException | SAXException e) {
				view.showException("Die Datei konnte nicht geladen werden!");
			}
		}
	}

	public void getNextTAN() {
		try {
			model.deleteTan();
		} catch (JAXBException e) {
			view.showException("Die TAN konnte nicht gelöscht werden!");
		}
	}

}
