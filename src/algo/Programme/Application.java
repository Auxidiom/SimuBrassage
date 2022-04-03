package algo.Programme;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import algo.Actionneurs.ControleurTemperature;
import algo.Composition.*;
import algo.Etapes.*;
import algo.Materiel.*;
import ui.cd.ControleurDialogue;
import ui.ihm.EcranResume;

public class Application {

	
	private Brassage b;
	private Boolean chauffage = false;
	ControleurDialogue cDialogue;
	private int seconds = 0;
	private ArrayList<String> ingredients = new ArrayList<String>();
	int vitesse = 1;
	
	public Application(ControleurDialogue cDialogue) {
		this.cDialogue = cDialogue;
	}
	
	
	public void brassageFrancoBelge(String eau, String malt) {
		Eau e = new Eau(Integer.parseInt(eau));
		e.setTemperature(21);
		EmpatageFrancoBelge efb = new EmpatageFrancoBelge();
		this.b = new Brassage(e, efb, true);
		b.maintenirChauffage();
		b.preparerIngredient(new Malt(Integer.parseInt(malt)));
	}
	public void brassagePerso(String eau, String malt,EmpatagePerso etapes) {
		Eau e = new Eau(Integer.parseInt(eau));
		e.setTemperature(21);
		this.b = new Brassage(e, etapes, true);
		b.maintenirChauffage();
		b.preparerIngredient(new Malt(Integer.parseInt(malt)));
	}
	public void demarrageBrassage() {
		new Thread(new ApplicationLoop(b, this)).start();
	}
	
	public void brassageAnglais(String eau, String malt) {
		Eau e = new Eau(Integer.parseInt(eau));
		e.setTemperature(21);
		EmpatageAnglais en = new EmpatageAnglais();
		this.b = new Brassage(e, en, true);
			
		b.preparerIngredient(new Malt(Integer.parseInt(malt)));
	}
	
	public void ajoutIngredient(HashMap HM) throws ClassNotFoundException {
		Iterator iterator = HM.entrySet().iterator();
        while (iterator.hasNext()) {
             Map.Entry me2 = (Map.Entry) iterator.next();
             String Ingredient = (String) me2.getKey();
             String valueString = (String) me2.getValue();
             int value = Integer.parseInt(valueString);
             switch(Ingredient) {
             	case "Epice":
             		b.preparerIngredient(new Epice(value));
             		break;
             	case "Fruits":
             		b.preparerIngredient(new Fruits(value));
             		break;
             	case "Houblon":
             		b.preparerIngredient(new Houblon(value));
             		break;
             	case "Levures":
             		b.preparerIngredient(new Levures(value));
             		break;
             	case "Malt":
             		b.preparerIngredient(new Malt(value));
             		break;
             	case "Sucre":
             		b.preparerIngredient(new Sucre(value));
             		break;
             }
             
        } 

		
		
	}
	
	public void maintenirChauffage(){
		b.maintenirChauffage();
	}
	
	
	public void actualiserTemperature() {
		if(b.isEnCours()) {
		cDialogue.actualiserTemperature(Float.toString(b.recupererTemperature()));}
			else if (b.getControleurTemperature().getFermentation().getCf().isEnCours()) {
				Fermentation f = b.getControleurTemperature().getFermentation();
				cDialogue.actualiserTemperature(Float.toString(f.recupererTemperature()));
					}
	}
	
	public void activerChauffage() {
		if(this.chauffage) {
			b.arreterChauffage();
			this.chauffage = false;
		}
		else {
			b.commencerChauffage();
			this.chauffage = true;
		}
	}
	
	public void actualiserTimer() {
            String timer = null;
            String minutesString = null;
            String secondsString = null;
            String hoursString =null;
            String daysString =null;
            
            seconds = seconds + this.vitesse;
            int minutes = (seconds / 60)%60;
            int hours = (seconds /3600)%24;
            int days = seconds /(3600*24);
            int s = seconds % 60;
            
            daysString = days + "d ";
            
            if(minutes < 10) {
                minutesString = "0" + minutes;
            } else {
                minutesString = Integer.toString(minutes);
            }
            
            if(hours < 10) {
                hoursString = "0" + hours;
            } else {
                hoursString = Integer.toString(hours);
            }
            
            if(s < 10) {
                secondsString = "0" + s;
            }
            else {
                secondsString = Integer.toString(s);
            }
            
            timer = daysString + hoursString + ":"+minutesString + ":" + secondsString;
            
            cDialogue.actualiserTimer(timer);
            
    }
	
	public void ajouterIngredientCuve(Ingredient i) {
		b.getPrech().getCE().ajouterIngredientCuve(i);
	}
	
	
	public HashMap getIngredients() {
		return b.getIngredients();
	}
	
	public void changerVitesse(int value) {
		b.setAccelerateur(value);
		this.vitesse = value;
	}
	
	public void ajouterPalliersFermentation(ArrayList<Pallier> etapes) {
		for(Pallier etape : etapes) {
			b.getControleurTemperature().getFermentation().addEtape(etape);
		}
	}
	
	public void creerFermentation() {
		b.getControleurTemperature().creerFermentation();
	}
		
	public void obtenirPuissance() {
		cDialogue.obtenirPuissance(b.getPuissance());
	}
	
	public void ajouterTexteResume(String texte) {
		cDialogue.ajouterTexteResume(texte);
	}
}
	
		
