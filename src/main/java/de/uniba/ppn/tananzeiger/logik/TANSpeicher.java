package de.uniba.ppn.tananzeiger.logik;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Module", namespace = "http://uniba.de/ppn/tananzeiger")
public class TANSpeicher {
	private ArrayList<String> tanSpeicher = new ArrayList<String>();

	public TANSpeicher() {

	}

	public ArrayList<String> getTanSpeicher() {
		return tanSpeicher;
	}

	public void setTanSpeicher(ArrayList<String> tanSpeicher) {
		this.tanSpeicher = tanSpeicher;
	}

}
