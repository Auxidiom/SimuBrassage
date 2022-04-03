package algo.Etapes;

import java.util.HashMap;

import algo.Actionneurs.ControleurTemperature;
import algo.Alertes.Alerte;
import algo.Composition.*;
import algo.Materiel.Cuve;

public class Brassage extends Etape {

	private Prechauffage prech;
	private Empatage emp;
	private Ebullition ebu = new Ebullition();
	private Refroidissement ref = new Refroidissement();
	private Eau eau;
	private int pallierActu = 0;
	private long dateDebutPallier = 0;
	private long dateFinPallier = 0;
	private boolean tempAtteinte = true;
	private boolean ebullition = false;
	private boolean refroidissement = false;
	private long coeffRefroidissment;
	private ControleurTemperature ct;
	private boolean enCours= true;
	private Cuve CE;
	
	
	/**
	 * Brassage
	 * 
	 * Constructeur du brassage avec l'eau et l'empatage en paramètre.
	 * 
	 * @param e
	 * @param emp
	 */

	public Brassage(Eau e, Empatage emp, Boolean virtual) {
		this.eau = e;
		this.CE = new Cuve(e);
		prech = new Prechauffage(e, CE);
		this.emp = emp;
		if(virtual) {
			ct = new ControleurTemperature(this);
		}
		else {
			// Capteur réel
		}
		
	}

	/**
	 * ajouterIngredient
	 * 
	 * Va chercher la fonction pour ajouter un ingrédient jusqu'à la cuve. Puis
	 * affiche un message pour prouver que cela a marché.
	 * 
	 * @param i
	 */
	public void preparerIngredient(Ingredient i) {
		prech.getCE().preparerIngredient(i);
		System.out.println("Préparation de " + i.getKg() + " kg de " + i.getClass().getSimpleName() + ".");
		Alerte a = new Alerte("Préparation de " + i.getKg() + " kg de " + i.getClass().getSimpleName() + ".");
	}

	public void ajouterIngredient(String i) {
		prech.getCE().ajouterIngredient(i);		
	
	}
	/**
	 * commencerChauffage
	 * 
	 * Va chercher la fonction pour commencer le chauffage jusqu'à la cuve.Puis
	 * affiche un message pour prouver que cela a marché.
	 * 
	 * @param puissance
	 */
	public void commencerChauffage() {
		prech.prechauffer();
		//System.out.println("Début du préchauffage.");
		//Alerte a = new Alerte("Début du préchauffage.");
	}
	
	public void arreterChauffage() {
		prech.arreterPrechauffage();
	}
	
	public void creerFermentation() {
		this.ct.creerFermentation();
	}

	public boolean isSimulationTermine() {
		return this.ct.getFermentation().getCf().isEnCours();
	}
	/**
	 * maintenirChauffage
	 * 
	 * Va chercher la fonction pour maintenir le chauffage jusqu'à la cuve.Puis
	 * affiche un message pour prouver que cela a marché. La température est
	 * maintenue selon le pallier actuel.
	 * 
	 */

	public void maintenirChauffage() {
		int temp = this.emp.getPalliers().get(pallierActu).getMaxTemp();

		prech.maintenirChauffage(temp);

	}

	/**
	 * recupererTemperature
	 * 
	 * Va chercher la fonction pour récupérer la température jusqu'à la cuve. Des
	 * variables de type booléen indique à quelle étape on se trouve. C'est cette
	 * fonction qui va déclencher l'enchainement des différentes étapes. Pour le
	 * refroidissment, la température va baisser proportionellement au temps.
	 * 
	 * 
	 * @return temperature
	 */
	public float recupererTemperature() {
			ct.controleurEtape();

		return prech.recupererTemperature();
	}

	public void setAccelerateur(int a) {
		this.prech.setAccelerateur(a);
	}
	public long tempsPasse() {
		return this.prech.tempsPasse();
	}

	// Getters et setters

	public Prechauffage getPrech() {
		return prech;
	}

	public void setPrech(Prechauffage prech) {
		this.prech = prech;
	}

	public Empatage getEmp() {
		return emp;
	}

	public void setEmp(Empatage emp) {
		this.emp = emp;
	}

	public Ebullition getEbu() {
		return ebu;
	}

	public void setEbu(Ebullition ebu) {
		this.ebu = ebu;
	}

	public Refroidissement getRef() {
		return ref;
	}

	public void setRef(Refroidissement ref) {
		this.ref = ref;
	}

	public Eau getEau() {
		return eau;
	}

	public void setEau(Eau eau) {
		this.eau = eau;
	}

	public int getPallierActu() {
		return pallierActu;
	}

	public void setPallierActu(int pallierActu) {
		this.pallierActu = pallierActu;
	}

	public long getDateDebutPallier() {
		return dateDebutPallier;
	}

	public void setDateDebutPallier(long dateDebutPallier) {
		this.dateDebutPallier = dateDebutPallier;
		prech.getCE().setDateDebutChauffe(dateDebutPallier);
	}

	public long getDateFinPallier() {
		return dateFinPallier;
	}

	public void setDateFinPallier(long dateFinPallier) {
		this.dateFinPallier = dateFinPallier;
	}

	public boolean isTempAtteinte() {
		return tempAtteinte;
	}

	public void setTempAtteinte(boolean tempAtteinte) {
		this.tempAtteinte = tempAtteinte;
	}

	public boolean isEbullition() {
		return ebullition;
	}

	public void setEbullition(boolean ebullition) {
		this.ebullition = ebullition;
	}

	public boolean isRefroidissement() {
		return refroidissement;
	}

	public void setRefroidissement(boolean refroidissement) {
		this.refroidissement = refroidissement;
	}

	public long getCoeffRefroidissment() {
		return coeffRefroidissment;
	}

	public void setCoeffRefroidissment(long coeffRefroidissment) {
		this.coeffRefroidissment = coeffRefroidissment;
	}

	public boolean isEnCours() {
		return enCours;
	}

	public void setEnCours(boolean fini) {
		this.enCours = fini;
	}

	public ControleurTemperature getControleurTemperature() {
		return ct;
	}

	public void setControleurTemperature(ControleurTemperature ct) {
		this.ct = ct;
	}
	
	public HashMap getIngredients() {
		return CE.getContenu();
	}
	public int getPuissance() {
		return this.prech.getPuissance();
	}
	

	
}
