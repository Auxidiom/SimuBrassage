package algo.Etapes;

import algo.Composition.*;
import algo.Materiel.*;

public class Prechauffage extends Etape{
	
	private Cuve CE;
	
	public Prechauffage(Eau e, Cuve CE) {
		this.CE = CE;
	}
	
	public void prechauffer() {
		CE.activerChauffage();
	}
	
	public void arreterPrechauffage() {
		CE.arreterChauffage();
	}
	
	public void maintenirChauffage(int temperature) {
		CE.setMaxTemperature(temperature);
	}
	
	public float recupererTemperature() {
		return CE.recupererTemperature();
	}

	public Cuve getCE() {
		return CE;
	}

	public void setCE(Cuve cE) {
		CE = cE;
	}
	public void setAccelerateur(int a) {
		this.CE.setAccelerateur(a);
	}
	public long tempsPasse() {
		return this.CE.tempsPasse();
	}
	public int getPuissance() {
		return this.CE.getPuissance();
	}
}