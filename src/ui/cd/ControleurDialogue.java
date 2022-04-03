package ui.cd;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

import algo.Composition.Ingredient;
import algo.Etapes.EmpatagePerso;
import algo.Etapes.Pallier;
import algo.Programme.Application;
import ui.cd.ControleurDonnees.EcransApplication;
import ui.ihm.EcranResume;
import ui.ihm.EcranSimulation;
import ui.ihm.InterfaceGraphiquePrincipale;
import javafx.scene.Node;

public class ControleurDialogue {

	private InterfaceGraphiquePrincipale ihmPrincipale = null;
	private EnumMap<EcransApplication, Node> listeEcrans = new EnumMap<>(EcransApplication.class);
	private Application app;
	private String emp = new String();
	private EmpatagePerso empPerso;

	public ControleurDialogue() {
		app = new Application(this);
	}

	public void definirIHMPrincipale(InterfaceGraphiquePrincipale ihm) {
		ihmPrincipale = ihm;
	}

	public void enregistrerEcran(EcransApplication s, Node n) {
		listeEcrans.put(s, n);

	}

	public void afficherEcran(EcransApplication s) {
		if (listeEcrans.containsKey(s))
			ihmPrincipale.afficherEcran(listeEcrans.get(s));
	}
	
	public void enregistrerEmpatage(String nom) {
		this.emp = nom;
	}
	
	public String recupEmpatage() {
		return this.emp;
	}
	
	public void brassageFrancoBelge(String eau, String malt) {
		app.brassageFrancoBelge(eau, malt);
	}
	public void brassagePerso(String eau, String malt,EmpatagePerso etapes) {
		app.brassagePerso(eau, malt,etapes);
	}
	public void demarrageBrassage() {
		app.demarrageBrassage();
	}
	
	public void brassageAnglais(String eau, String malt) {
		app.brassageAnglais(eau, malt);
	}
	
	public void actualiserTemperature(String temperature) {
		EcranSimulation.actualizeTemp(temperature);
	}
	
	public void activerChauffage() {
		app.activerChauffage();
	}

	public void actualiserTimer(String timer) {
		EcranSimulation.actualiserTimer(timer);
	}
	
	public void maintenirChauffage() {
		app.maintenirChauffage();
	}
	
	public void ajouterIngredient(HashMap HM) throws ClassNotFoundException {
		app.ajoutIngredient(HM);
	}
	
	public HashMap getIngredients() {
		return app.getIngredients();
	}
	
	public void changerVitesse(String value) {
		int valueInt = Integer.parseInt(value);
		app.changerVitesse(valueInt);
	}
	
	public void ajouterPallierFermentation(ArrayList<Pallier> etapes) {
		app.ajouterPalliersFermentation(etapes);
	}
	
	public void creerFermentation() {
		app.creerFermentation();
	}
	public void obtenirPuissance(int p) {
		EcranSimulation.actualiserPuissance(p);
	}

	public EmpatagePerso getEmpPerso() {
		return empPerso;
	}

	public void setEmpPerso(EmpatagePerso empPerso) {
		this.empPerso = empPerso;
	}
	
	public void ajouterIngredientCuve(Ingredient i) {
		app.ajouterIngredientCuve(i);
	}
	
	public void ajouterTexteResume(String texte) {
		EcranResume.addTexte(texte);
	}
	
}
