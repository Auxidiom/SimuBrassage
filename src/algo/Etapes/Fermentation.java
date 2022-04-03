package algo.Etapes;

import java.util.ArrayList;

import algo.Actionneurs.*;
import algo.Alertes.Alerte;
import algo.Composition.*;
import algo.Materiel.*;

public class Fermentation extends Etape{

	private Cuve cuve;
	private Eau eau;
	private long dateDebutPallier = 0;
	private long dateFinPallier = 0;
	private ControlleurFermentation cf;
	private ArrayList<Pallier> etapes;
	private int pallierActu;
	
	public Fermentation(){
	}
	
	public Fermentation(boolean virtuel){
		etapes = new ArrayList<Pallier>();
		if(virtuel) {
		cf = new ControlleurFermentation(this);}
	}
	
	public Fermentation(Cuve c,ArrayList<Pallier> p,boolean virtuel){
		this.cuve = c;
		etapes = p;
		if(virtuel) {
			cf = new ControlleurFermentation(this);}
		}
	
	public void creer(Cuve c) {
		etapes = new ArrayList<Pallier>();
		this.setCuve(c);
		cf = new ControlleurFermentation(this);
		
	}
	
	/**
	 * ajouterIngredient
	 * 
	 * Va chercher la fonction pour ajouter un ingrédient jusqu'à la cuve. Puis
	 * affiche un message pour prouver que cela a marché.
	 * 
	 * @param i
	 */
	public void ajouterIngredient(Ingredient i) {
		cuve.preparerIngredient(i);
		System.out.println("Ajout de " + i.getKg() + " kg de " + i.getClass().getSimpleName() + ".");
		Alerte a = new Alerte("Ajout de " + i.getKg() + " kg de " + i.getClass().getSimpleName() + ".");
	}
	public void ajouterIngredient(String i) {
		this.cuve.ajouterIngredient(i);		
	
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
		cuve.activerChauffage();;
		System.out.println("Début du préchauffage.");
		Alerte a = new Alerte("Début du préchauffage.");
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
		int temp = this.etapes.get(pallierActu).getMaxTemp();

		cuve.setMaxTemp(temp);

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
			cf.controleurEtape();

		return cuve.recupererTemperature();
	}	
	
	
	
	public void arreterChauffage() {
		cuve.arreterChauffage();
	}
	
	public long tempsPasse() {
		return this.cuve.tempsPasse();
	}

	
	public void setAccelerateur(int a) {
		this.cuve.setAccelerateur(a);
	}
	
	public int getPallierActu() {
		return pallierActu;
	}

	public void setPallierActu(int pallierActu) {
		this.pallierActu = pallierActu;
	}

	public Cuve getCuve() {
		return cuve;
	}

	public void setCuve(Cuve cuve) {
		this.cuve = cuve;
	}

	public Eau getEau() {
		return eau;
	}

	public void setEau(Eau eau) {
		this.eau = eau;
	}

	public long getDateDebutPallier() {
		return dateDebutPallier;
	}

	public void setDateDebutPallier(long dateDebutPallier) {
		this.dateDebutPallier = dateDebutPallier;
		cuve.setDateDebutChauffe(dateDebutPallier);
	}

	public long getDateFinPallier() {
		return dateFinPallier;
	}

	public void setDateFinPallier(long dateFinPallier) {
		this.dateFinPallier = dateFinPallier;
	}

	public ControlleurFermentation getCf() {
		return cf;
	}

	public void setCf(ControlleurFermentation cf) {
		this.cf = cf;
	}

	public ArrayList<Pallier> getEtapes() {
		return etapes;
	}

	public void setEtapes(ArrayList<Pallier> etapes) {
		this.etapes = etapes;
	}
	
	public void addEtape(Pallier e) {
		etapes.add(e);
		System.out.println("Ajout d'une étape de " + e.getDuree() + "minutes, une température de "+ e.getMaxTemp());
	}
	
	
	
	

}
