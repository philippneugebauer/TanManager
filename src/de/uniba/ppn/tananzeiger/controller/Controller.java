package de.uniba.ppn.tananzeiger.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import de.uniba.ppn.tananzeiger.gui.FileChooser;
import de.uniba.ppn.tananzeiger.gui.View;
import de.uniba.ppn.tananzeiger.model.Model;

public class Controller {
	private File file = null;
	private View anzeige = null;
	private FileChooser chooser = null;
	private Model model = null;

	public Controller() {
		this.file = getOS();
		model = new Model(file);
		anzeige = new View(this);
		model.addObserver(anzeige);
		chooser = new FileChooser();
		if (file.isFile()) {
			try {
				model.readXML();
				checkListSize();
			} catch (Exception e) {
				anzeige.showException(e.getMessage());
				anzeige.getRestlicheTanAnzahl().setText("0");
				setTANText("TANs leer");
			}
		} else {
			anzeige.getRestlicheTanAnzahl().setText("0");
			setTANText("TANs leer");
		}
	}

	public File getOS() {
		Properties system = System.getProperties();
		String os = system.toString();
		int osStart = os.indexOf("os.name=");
		int osStop = os.indexOf(",", osStart);
		os = os.substring(osStart + 8, osStop);
		String pfad = null;
		pfad = System.getProperty("user.home");
		if (os.contains(" 7")) {
			pfad = pfad + "/Documents/TanAnzeiger";
		} else {
			pfad = pfad + "/TanAnzeiger";
		}
		File file = new File(pfad);
		if (file.exists() == false) {
			file.mkdirs();
		}
		file = new File(pfad + "/Tans.xml");
		return file;
	}

	public void chooseFile() {
		File file = chooser.chooseFile(null);
		if (file != null) {
			if (file.getName().endsWith("xml")) {
				try {
					model.loadXML(file);
				} catch (Exception e) {
					anzeige.showException("Die Datei konnte nicht geladen werden!");
				}
			} else {
				try {
					model.readFile(file);
				} catch (Exception e1) {
					anzeige.showException("Die Datei konnte nicht geladen werden!");
				}
			}
			checkListSize();
			anzeige.getRestlicheTanAnzahl().setText(
					String.valueOf(model.getSize()));
		}
	}

	private void checkListSize() {
		anzeige.getRestlicheTanAnzahl()
				.setText(String.valueOf(model.getSize()));
		if (model.getSize() > 0) {
			anzeige.getTanAnzeige().setText(
					String.valueOf(model.getList().get(0)));
			if (model.getSize() == 1) {
				anzeige.showWarning("Das ist ihre letzte TAN!");
			}
		} else {
			setTANText("TANs leer");
		}
	}

	public void getNextTAN() {
		if (model.getSize() > 1) {
			try {
				löscheTan();
			} catch (JAXBException e1) {
				anzeige.showException("Die TAN konnte nicht gelöscht werden!");
			}
			anzeige.getTanAnzeige().setText(
					String.valueOf(model.getList().get(0)));
			anzeige.getRestlicheTanAnzahl().setText(
					String.valueOf(model.getSize()));
			if (model.getSize() == 1) {
				anzeige.showWarning("Das ist ihre letzte TAN!");
			}
		} else if (model.getSize() == 1) {
			try {
				löscheTan();
			} catch (JAXBException e1) {
				anzeige.showException("Die TAN konnte nicht gelöscht werden!");
			}
			anzeige.getTanAnzeige().setText("TANs leer");
			anzeige.getRestlicheTanAnzahl().setText("0");
		} else {
			anzeige.getRestlicheTanAnzahl().setText("0");
			anzeige.getTanAnzeige().setText("TANs leer");
		}
	}

	public void versteckenAufheben() {
		if (file.exists() == true && file.isHidden() == true) {
			try {
				Runtime.getRuntime().exec("attrib -H " + file.toString());
				System.out.println(file.isHidden());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void dateiVerstecken() {
		if (file.exists() == true && file.isHidden() == false) {
			try {
				Runtime.getRuntime().exec("attrib +H " + file.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("versteckt gemacht");
		}
	}

	public void speichereListe(ArrayList<String> speicher) throws JAXBException {
		model.speichereTAN(speicher);
	}

	public void löscheTan() throws JAXBException {
		model.löscheTan();
	}

	public ArrayList<String> getList() {
		return model.getList();
	}

	public void setTAN(long zahl) {
		anzeige.getTanAnzeige().setText(String.valueOf(zahl));
	}

	public void setTANText(String text) {
		anzeige.getTanAnzeige().setText(text);
	}
}
