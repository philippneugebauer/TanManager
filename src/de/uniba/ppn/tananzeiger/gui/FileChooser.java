package de.uniba.ppn.tananzeiger.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

public class FileChooser extends JFileChooser {
	private static final long serialVersionUID = 1L;
	int choice = 0;
	JFileChooser fileChooser = new JFileChooser();
	File toLoad = null;

	public FileChooser() {
	}

	public File chooseFile(JMenuItem load) {
		choice = fileChooser.showOpenDialog(load);
		fileChooser.setDialogTitle("Bitte wählen Sie eine Datei aus");

		if (choice == JFileChooser.APPROVE_OPTION) {
			toLoad = fileChooser.getSelectedFile();
		}

		return toLoad;

	}

}
