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
import de.uniba.ppn.tananzeiger.model.Model;

public class View extends JFrame implements Observer {
	private JMenuItem readMenu = null;
	private JTextField tanShowField = null;
	@SuppressWarnings("unused")
	private Controller controller = null;
	private Model model;
	private JMenuItem copyTanButton = new JMenuItem("Kopiere TAN");
	private JMenuItem about = null;
	private JButton nextTanButton = null;
	private JPopupMenu popupMenu = null;
	private JMenuItem popupCopy = null;
	private JTextField remainingTanCountField = null;

	private static final long serialVersionUID = 1L;

	public View(final Controller controller, Model model) {
		super("Tan-Anzeiger");
		this.controller = controller;
		this.model = model;
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		initMenu();
		initLabels();
		initButtons();
		initTextFields();
		readMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.chooseFile();
			}

		});

		copyTanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit()
						.getSystemClipboard()
						.setContents(
								new StringSelection(tanShowField.getText()),
								null);
			}
		});

		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Autor: Philipp Neugebauer \nVersion: 1.1 \nBugs bitte an flippus@nexgo.de melden!",
								"Autor", 1);
			}
		});

		nextTanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getNextTAN();
			}
		});
		popupCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit()
						.getSystemClipboard()
						.setContents(
								new StringSelection(tanShowField.getText()),
								null);
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
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
		readMenu = new JMenuItem("Laden");
		JMenu menu = new JMenu("Menü");
		menuBar.add(menu);
		menu.add(readMenu);
		copyTanButton = new JMenuItem("Kopiere TAN");
		menu.add(copyTanButton);
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
		nextTanButton = new JButton("Nächste TAN");
		nextTanButton.setBounds(new Rectangle(70, 110, 150, 30));
		add(nextTanButton);
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
		tanShowField = new JTextField("TANs leer");
		tanShowField.setHorizontalAlignment(JTextField.RIGHT);
		tanShowField.setBounds(new Rectangle(110, 47, 100, 20));
		tanShowField.setEditable(false);
		add(tanShowField);
		remainingTanCountField = new JTextField("0");
		remainingTanCountField.setHorizontalAlignment(JTextField.RIGHT);
		remainingTanCountField.setBounds(new Rectangle(160, 72, 50, 20));
		remainingTanCountField.setEditable(false);
		add(remainingTanCountField);
	}

	private void showWarning(String warning) {
		JOptionPane.showMessageDialog(null, warning, "Achtung",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showException(String exception) {
		JOptionPane.showMessageDialog(null, exception, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void update(Observable o, Object arg) {
		remainingTanCountField.setText(String.valueOf(model.getSize()));
		if (model.getSize() > 0) {
			tanShowField.setText(String.valueOf(model.getList().get(0)));
			if (model.getSize() == 1) {
				showWarning("Das ist ihre letzte TAN!");
			}
		} else {
			tanShowField.setText("TANs leer");
		}
	}
}
