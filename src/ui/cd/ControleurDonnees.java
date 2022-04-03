package ui.cd;

import java.util.Locale;

import ui.cd.ControleurDonnees.LanguesApplication;
import ui.cd.I18N;
import ui.cd.ControleurDonnees;

public class ControleurDonnees {
	
	private LanguesApplication langue;
	public static final Locale localeEN = new Locale("en", "UK");
	public static final Locale localeFR = new Locale("fr", "FR");
	public static final String urlFichiersTrad = "Ressources.Textes.messages";

	public enum EcransApplication {
		ACCUEIL, EMPATAGE, SIMULATION,  CUISSONMOUT, FERMENTATION, PARAMETRES, RESUME,INFORMATION
	}

	public ControleurDonnees.LanguesApplication obtenirLangue() {
        return this.langue;
    }
	
	public enum LanguesApplication { 
		FRANCAIS (I18N.get("bouton.francais")), 
		ANGLAIS (I18N.get("bouton.anglais"));
		
		private final String texte;
		
		LanguesApplication(String t){
			this.texte = t;
		}
	
		@Override
		public String toString() { return this.texte; }
		
	}
	
	public static Locale obtenirLocale(LanguesApplication l) {
		switch(l){
            case ANGLAIS :
                return localeEN;   
            default :
            	return localeFR;
		}
	}
	
	public static LanguesApplication obtenirLangue(String l) {
		LanguesApplication nouvelleLangue = LanguesApplication.FRANCAIS;
		if (l.equals(I18N.get("bouton.anglais"))) 
			nouvelleLangue =  LanguesApplication.ANGLAIS;
		
		return nouvelleLangue;
	}
	
}
