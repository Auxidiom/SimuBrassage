package algo.Actionneurs;

import algo.Alertes.Alerte;
import algo.Capteurs.CapteurTemp;
import algo.Composition.Eau;
import algo.Etapes.Brassage;
import algo.Etapes.Fermentation;
import algo.Etapes.Prechauffage;
import ui.ihm.EcranResume;
import ui.ihm.EcranSimulation;

public class ControleurTemperature {
	private Brassage b;
	private Prechauffage prech;
	private CapteurTemp c;
	private Resistance r;
	private boolean empatage = true;
	private boolean ebullition = false;
	private boolean refroidissement = false;
	private float tempAmbiante = 21;
	private long debutRefroidissement;
	private Fermentation fermentation= new Fermentation();
	private int accelerateur =1;
	private boolean premierRefroidissement = true;

	public ControleurTemperature(Brassage b2) {
		b = b2;
		c = b2.getPrech().getCE().getCt();
		r = b2.getPrech().getCE().getR();
	}
	
	public float recupTemperature() {
		return b.getPrech().recupererTemperature();
	}

	public void arreterChauff(float temp, int maxTemp) {
		r.eteindre();

	}

	public void reprendreChauff() {
		r.activer();

	}
	


	public void controleurEtape() {
		if (b.getPrech().recupererTemperature() == b.getEmp().getPalliers().get(b.getPallierActu()).getMaxTemp()
				&& empatage) {
			if (b.getDateFinPallier() != 0
					&& tempsPasse() >= b.getDateFinPallier()) {
				
				changementPallier();
			} else if (b.getDateFinPallier() != 0
					&& tempsPasse() < b.getDateFinPallier()) {

				
			}else {
				changeDatePallier();
			}

		} else if (ebullition && b.getPrech().recupererTemperature() >= b.getEbu().getMaxTemp()-1) {
	
			if (b.getDateFinPallier() != 0
					&& tempsPasse() >= b.getDateFinPallier()) {
				finEbullition();
			} else if (b.getDateFinPallier() != 0
					&& tempsPasse() < b.getDateFinPallier()) {

				
			}else {

				attenteEbullition();
			}
		} else if (refroidissement && b.getPrech().recupererTemperature() > tempAmbiante+0.1) {
			
			double t= tempsPasse();
			double coeff =(double) (long)(((30*(100-tempAmbiante))*1000));
			double expo = (double) Math.exp(- (double)t/(double)coeff);
			int temp = 100;
			
			b.getPrech().getCE().setMaxTemp((int) (tempAmbiante+(temp-tempAmbiante)*(double)expo));
		
		}else if (refroidissement && b.getPrech().recupererTemperature() >= tempAmbiante) {
			finRefroidissement();
			
		}

	}

	public void changementPallier() {
		if (b.getPallierActu() + 1 < b.getEmp().getPalliers().size()) {
			b.setPallierActu(b.getPallierActu()+1);

			empatage = true;
			b.maintenirChauffage();
			b.commencerChauffage();
		} else {
			EcranSimulation.afficherMessage("Fin de l'empatage, début de l'ébullition.");
			EcranResume.addTexte("Fin de l'empatage, début de l'ébullition.");
			Alerte a = new Alerte("Fin de l'empatage, début de l'ébullition.");
			b.commencerChauffage();
			commencerEbulltion();
			
		}

		b.setDateFinPallier(0);


	}


	public void changeDatePallier() {
			this.ebullition = false;
			b.arreterChauffage();
			long dateDebutPallier = System.currentTimeMillis();
			b.setDateDebutPallier(dateDebutPallier);
			EcranSimulation.afficherMessage("Pallier : " + b.getPallierActu() + " Temperature Atteinte : " + b.getPrech().recupererTemperature()
					+ " Veuillez laissez reposer " + b.getEmp().getPalliers().get(b.getPallierActu()).getDuree() + " min.");
			EcranResume.addTexte("Pallier : " + b.getPallierActu() + " Temperature Atteinte : " + b.getPrech().recupererTemperature()
					+ " Veuillez laissez reposer " + b.getEmp().getPalliers().get(b.getPallierActu()).getDuree() + " min.");
			long dateFinPallier = (long) (b.getEmp().getPalliers().get(b.getPallierActu()).getDuree() * 60 * 1000);
			b.setDateFinPallier(dateFinPallier);
			

	}

	public void commencerEbulltion() {
		ebullition = true;
		empatage = false;
		b.getPrech().maintenirChauffage(b.getEbu().getMaxTemp());


	}

	/**
	 * attenteEbullition
	 * 
	 * Instancie une variable de début d'ébulltion à la date actuelle de la
	 * machine et la date de fin de pallier. Fait ensuite un affichage.
	 * 
	 */
	public void attenteEbullition() {
		b.setDateDebutPallier(System.currentTimeMillis());

		long dateFinPallier = (long) (b.getEbu().getDuree() * 60 * 1000);
		b.setDateFinPallier(dateFinPallier);
		EcranSimulation.afficherMessage("Arrivée à " + b.getEbu().getMaxTemp() + "° attendre " + b.getEbu().getDuree()
				+ " min avant de refroidir.");
		EcranResume.addTexte("Arrivée à " + b.getEbu().getMaxTemp() + "° attendre " + b.getEbu().getDuree()
				+ " min avant de refroidir.");
		Alerte a = new Alerte("Arrivée à " + b.getEbu().getMaxTemp() + "° attendre " + b.getEbu().getDuree()
				+ " min avant de refroidir.");
		
	}

	/**
	 * finEbullition
	 * 
	 * Fait un afffichage de fin d'ébullition et appelle le début du
	 * refroidissement.
	 * 
	 */
	public void finEbullition() {
		EcranSimulation.afficherMessage("Fin de l'ébulltion début du refroidissement.");
		EcranResume.addTexte("Fin de l'ébulltion début du refroidissement.");
		Alerte a = new Alerte("Fin de l'ébulltion début du refroidissement.");
		this.debutRefroidissement();
		
		b.setDateFinPallier(0);

	}

	/**
	 * debutRefroidissement
	 * 
	 * Instancie la variable du début du refroidissement avec la date de la
	 * machine. Arrete l'ébulltion et met a true la variable de refroidissement.
	 * Créer un coefficient de refroidissement et finis par un affichage.
	 * 
	 */
	public void debutRefroidissement() {
		this.b.getPrech().getCE().getR().Refroidissement();
		debutRefroidissement=System.currentTimeMillis();
		b.setDateDebutPallier(debutRefroidissement);
		refroidissement = true;
		ebullition = false;
		long coeffRefroidissment = (long) (((30*(100-tempAmbiante))*1000));
		b.setCoeffRefroidissment(coeffRefroidissment);
		if(premierRefroidissement) {
			EcranSimulation.afficherMessage("Debut pallier refroidissement la temperature a atteindre est : " + tempAmbiante);
			EcranResume.addTexte("Debut pallier refroidissement la temperature a atteindre est : " + tempAmbiante);
			Alerte a = new Alerte("Debut pallier refroidissement la temperature a atteindre est : " + tempAmbiante);
			premierRefroidissement = false;
		}
	


	}

	/**
	 * FinRefroidissement
	 * 
	 * Arrete le refroidissement et affiche un message.
	 * 
	 */
	public void finRefroidissement() {
		EcranSimulation.afficherMessage("Refroidissment terminé. La fermentation peut commencer.");
		EcranResume.addTexte("Refroidissment terminé. La fermentation peut commencer.");
		Alerte a = new Alerte("Refroidissment terminé. La fermentation peut commencer.");
		refroidissement = false;
		finBrassage();
	}
	
	public void finBrassage() {
		
		//creerFermentation();
		b.setEnCours(false);
		
	}
	
	public void creerFermentation() {
		fermentation.creer(this.b.getPrech().getCE());
	}
	public long tempsPasse() {
		return this.b.tempsPasse();
	}

	public Brassage getB() {
		return b;
	}

	public void setB(Brassage b) {
		this.b = b;
	}

	public Prechauffage getPrech() {
		return prech;
	}

	public void setPrech(Prechauffage prech) {
		this.prech = prech;
	}

	public CapteurTemp getC() {
		return c;
	}

	public void setC(CapteurTemp c) {
		this.c = c;
	}

	public Resistance getR() {
		return r;
	}

	public void setR(Resistance r) {
		this.r = r;
	}

	public boolean isEmpatage() {
		return empatage;
	}

	public void setEmpatage(boolean empatage) {
		this.empatage = empatage;
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

	public float getTempAmbiante() {
		return tempAmbiante;
	}

	public void setTempAmbiante(float tempAmbiante) {
		this.tempAmbiante = tempAmbiante;
	}

	public long getDebutRefroidissement() {
		return debutRefroidissement;
	}

	public void setDebutRefroidissement(long debutRefroidissement) {
		this.debutRefroidissement = debutRefroidissement;
	}

	public Fermentation getFermentation() {
		return fermentation;
	}

	public void setFermentation(Fermentation fermentation) {
		this.fermentation = fermentation;
	}

	public int getAccelerateur() {
		return accelerateur;
	}

	public void setAccelerateur(int accelerateur) {
		this.accelerateur = accelerateur;
	}
	
	

	

}