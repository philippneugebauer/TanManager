package de.uniba.ppn.tananzeiger.gui;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import de.uniba.ppn.tananzeiger.controller.Controller;

public class View extends JFrame implements Observer {
	private JMenuItem einlesen = null;
	private JTextField TanAnzeigeFeld = null;
	@SuppressWarnings("unused")
	private Controller controller = null;
	private JMenuItem kopieren = new JMenuItem("Kopiere TAN");
	private JMenuItem about = null;
	private JButton nächsteTAN = null;
	private JPopupMenu popupMenu = null;
	private JMenuItem popupCopy = null;
	private JTextField restlicheTANAnzahl = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public View(final Controller controller) {
		super("Tan-Anzeiger");
		this.controller = controller;
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		initMenu();
		initLabels();
		initButtons();
		initTextFields();
		einlesen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chooseFile();
			}

		});

		kopieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit()
						.getSystemClipboard()
						.setContents(
								new StringSelection(TanAnzeigeFeld.getText()),
								null);
			}
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Autor: Philipp Neugebauer \nVersion: 1.1 \nBugs bitte an flippus@nexgo.de melden!",
								"Autor", 1);
			}
		});

		nächsteTAN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getNextTAN();
			}
		});
		popupCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit()
						.getSystemClipboard()
						.setContents(
								new StringSelection(TanAnzeigeFeld.getText()),
								null);
			}
		});

		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent popup) {
				if (popup.isPopupTrigger()) {
					popupMenu.show(popup.getComponent(), popup.getX(),
							popup.getY());
				}
			}
		});
		setVisible(true);
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(new Rectangle(0, 0, 300, 20));
		einlesen = new JMenuItem("Laden");
		JMenu menu = new JMenu("Menü");
		menuBar.add(menu);
		menu.add(einlesen);
		kopieren = new JMenuItem("Kopiere TAN");
		menu.add(kopieren);
		JMenu help = new JMenu("?");
		menuBar.add(help);
		about = new JMenuItem("Über");
		help.add(about);
		add(menuBar);
		popupMenu = new JPopupMenu();
		add(popupMenu);
		popupCopy = new JMenuItem("Kopiere TAN");
		popupMenu.add(popupCopy);
	}

	private void initButtons() {
		nächsteTAN = new JButton("Nächste TAN");
		nächsteTAN.setBounds(new Rectangle(70, 110, 150, 30));
		add(nächsteTAN);
	}

	private void initLabels() {
		JLabel TanAnzeigeLabel = new JLabel("TAN:");
		TanAnzeigeLabel.setBounds(new Rectangle(80, 52, 30, 10));
		add(TanAnzeigeLabel);
		JLabel restlicheTanLabel = new JLabel("übrige TANs:");
		restlicheTanLabel.setBounds(new Rectangle(80, 75, 80, 15));
		add(restlicheTanLabel);
	}

	private void initTextFields() {
		TanAnzeigeFeld = new JTextField();
		TanAnzeigeFeld.setHorizontalAlignment(JTextField.RIGHT);
		TanAnzeigeFeld.setBounds(new Rectangle(110, 47, 100, 20));
		TanAnzeigeFeld.setEditable(false);
		add(TanAnzeigeFeld);
		restlicheTANAnzahl = new JTextField();
		restlicheTANAnzahl.setHorizontalAlignment(JTextField.RIGHT);
		restlicheTANAnzahl.setBounds(new Rectangle(160, 72, 50, 20));
		restlicheTANAnzahl.setEditable(false);
		add(restlicheTANAnzahl);
	}

	public JTextField getTanAnzeige() {
		return TanAnzeigeFeld;
	}

	public JTextField getRestlicheTanAnzahl() {
		return restlicheTANAnzahl;
	}

	public void showWarning(String warning) {
		JOptionPane.showMessageDialog(null, warning, "Achtung",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showException(String exception) {
		JOptionPane.showMessageDialog(null, exception, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
