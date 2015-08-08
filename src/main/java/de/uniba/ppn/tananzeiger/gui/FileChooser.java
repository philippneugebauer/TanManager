package de.uniba.ppn.tananzeiger.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Philipp Neugebauer
 */
public class FileChooser extends JFileChooser {
    private static final long serialVersionUID = 1L;

    public FileChooser() {
        super();
        this.setMultiSelectionEnabled(false);
        this.setAcceptAllFileFilterUsed(true);
    }

    public File chooseFile(JMenuItem load) {
        setDialogTitle("Bitte wählen Sie eine Datei aus");
        if (showOpenDialog(load) == APPROVE_OPTION) {
            return getSelectedFile();
        }
        return null;
    }
}
