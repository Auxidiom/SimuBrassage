package algo.Materiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import algo.Actionneurs.Resistance;
import algo.Alertes.Alerte;
import algo.Capteurs.CapteurTemp;
import algo.Composition.*;
import ui.ihm.EcranResume;

public class Cuve {
	private HashMap<String, Ingredient> contenu = new HashMap<String, Ingredient>();
	private HashMap<String, Ingredient> contenuIn = new HashMap<String, Ingredient>(); 
	private boolean filtre = true;
	private Resistance resistance = new Resistance();
	private CapteurTemp ct = new CapteurTemp();
	private long dateDebutChauffe = 0;
	private int puissanceChauffe = 0;
	private int maxTemperature = 100;
	private Eau e;

	public Cuve(Eau e) {
		this.e = e;
	}

	public void setAccelerateur(int a) {
		e.setTempInitiale(this.recupererTemperature());
		this.ct.setAccelerateur(a);
		dateDebutChauffe = System.currentTimeMillis();
	}
	
	public long tempsPasse() {
		return this.ct.tempsPasse(dateDebutChauffe);
	}
	/**
	 * activerChauffage
	 * 
	 * Instancie la date de début de chauffe avec la date de la machine et instancie
	 * la puissance de chaffe.
	 * 
	 * @param puissanceChauffe
	 */
	public void activerChauffage() {
		dateDebutChauffe = System.currentTimeMillis();
		resistance.activer();
		this.puissanceChauffe = resistance.getPuissance();
	}
	
	public void arreterChauffage() {
		dateDebutChauffe = System.currentTimeMillis();
		resistance.eteindre();
		this.puissanceChauffe = resistance.getPuissance();
	}


	/**
	 * recupererTemperature
	 * 
	 * Fait chauffer l'eau selon le temps actuel de la machine. La température ne
	 * peut pas dépasser la température maximale ou 100°.
	 * 
	 * @return temperature
	 */
	public float recupererTemperature() {
		Eau e = (Eau) this.e;

		float t = ct.recupererTemperature(dateDebutChauffe,puissanceChauffe,e,maxTemperature);


		return t;
	}

	/**
	 * recupererTemperatureMomentT
	 * 
	 * Fait chauffer l'eau selon le temps choisis. La température ne peut pas
	 * dépasser la température maximale ou 100°.
	 * 
	 * @return temperature
	 */
	public float recupererTemperatureMomentT(long time) {

		Eau e = (Eau) this.getContenu().get("Eau");
		float t = ct.recupererTemperatureMomentT(dateDebutChauffe,puissanceChauffe,e,maxTemperature,time);
	
		return t;
	}

	/**
	 * transvaser
	 * 
	 * Met le contenu de cette cuve dans la cuve mis en paramètre de la fonction.
	 * 
	 * @param c2
	 */

	public void transvaser(Cuve c2) {
		c2.contenu = this.contenu;
		this.contenu = new HashMap<String, Ingredient>();
	}

	/**
	 * ajoutIngredient
	 * 
	 * ajoute l'ingrédient dans le Hash Map en mettant le nom de l'ingrédient en Key
	 * et l'ingrédient et Value. le filtre est en flase après.
	 * 
	 * @param i
	 */
	public void preparerIngredient(Ingredient i) {
		this.contenu.put(i.getClass().getSimpleName(), i);
		this.filtre = false;
	}
	
	public void ajouterIngredient(String i) {
		int kg = this.contenu.get(i).getKg();
		String name = this.contenu.get(i).getClass().getSimpleName();
		System.out.println("Ajout de " + kg + " kg de " + name + ".");
		Alerte a = new Alerte("Ajout de " + kg + " kg de " + name + ".");
	}
	
	public void ajouterIngredientCuve(Ingredient i) {
		contenuIn.put(i.getClass().getSimpleName(), i);
		EcranResume.addTexte("Ajout de " + i.getKg() + " kg de " + i.getClass().getSimpleName() + ".");
	}
	
	public Eau getEau() {
		Eau e = (Eau) this.getContenu().get("Eau");
		return e;
	}

	public void setMaxTemp(int temperature) {
		this.maxTemperature = temperature;
	}

	public CapteurTemp getCt() {
		return ct;
	}

	public void setCt(CapteurTemp ct) {
		this.ct = ct;
	}

	public long getDateDebutChauffe() {
		return dateDebutChauffe;
	}

	public void setDateDebutChauffe(long dateDebutChauffe) {
		this.e.setTempInitiale(this.recupererTemperature());
		this.dateDebutChauffe = dateDebutChauffe;
	
	}

	public int getPuissanceChauffe() {
		return puissanceChauffe;
	}

	public void setPuissanceChauffe(int puissanceChauffe) {
		this.puissanceChauffe = puissanceChauffe;
	}

	public int getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public HashMap<String, Ingredient> getContenu() {
		return contenu;
	}

	public void setContenu(HashMap<String, Ingredient> contenu) {
		this.contenu = contenu;
	}

	public boolean isFiltre() {
		return filtre;
	}

	public void setFiltre(boolean filtre) {
		this.filtre = filtre;
	}

	public Resistance getR() {
		return resistance;
	}

	public void setR(Resistance r) {
		this.resistance = r;
	}
	public int getPuissance() {
		return this.resistance.getPuissance();
	}
}
