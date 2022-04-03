package algo.Actionneurs;

import algo.Alertes.Alerte;
import algo.Capteurs.*;
import algo.Etapes.*;
import algo.Materiel.*;
import ui.ihm.EcranResume;
import ui.ihm.EcranSimulation;

/**
 * @author pr000460
 *
 */
public class ControlleurFermentation {

	private Fermentation f;
	private Cuve cuve;
	private Resistance resistance;
	private boolean debutchauffe =true;
	private boolean debutrefroidissement =true;
	private boolean enCours = true;
	private float tempAmbiante = 21;
	private long debutRefroidissement;
	private long coeffRefroidissment;
	private int accelerateur =1;
	public ControlleurFermentation(Fermentation f2) {
		f = f2;
		cuve = f2.getCuve();
		resistance = f2.getCuve().getR();

	}
	
	public long tempsPasse() {
		return this.f.tempsPasse();
	}

	public void controleurEtape() {
		
		if (cuve.recupererTemperature() < this.temperaturePallier()&& debutchauffe) {
			chauffageFermentation();

		}else if (cuve.recupererTemperature() > this.temperaturePallier()&& debutrefroidissement ) {
			
			this.debutRefroidissement();
			

		} else if (cuve.recupererTemperature() > this.temperaturePallier()) {
			
			
			double t=tempsPasse();
			double coeff =(double)(this.getCoeffRefroidissment());
			int temp = 0;
			if(f.getPallierActu()>0) {
			 temp = f.getEtapes().get(f.getPallierActu()-1).getMaxTemp();}
			else {
				 temp = (int) tempAmbiante;
			}
		
			
			cuve.setMaxTemp((int) (tempAmbiante+(temp-tempAmbiante)*Math.exp(-t/coeff)));
			System.out.println(coeff);

		} else if (cuve.recupererTemperature() == this.temperaturePallier()) {
			if (f.getDateFinPallier() != 0
					&& tempsPasse() >= f.getDateFinPallier()) {

				changementPallier();
			} else if (f.getDateFinPallier() != 0
					&& tempsPasse() < f.getDateFinPallier()) {

				
			}else {
				
				changeDatePallier();
			}
		}
		

	}

	public int temperaturePallier() {
		int temperature = f.getEtapes().get(f.getPallierActu()).getMaxTemp();
		return temperature;
	}

	public long dureePallier() {
		double duree = f.getEtapes().get(f.getPallierActu()).getDuree();
		long temps = (long) (duree * 60 * 1000);

		return temps;
	}

	public void chauffageFermentation() {
		this.debutchauffe = false;
		System.out.println("Chauffage jusqu'à " + temperaturePallier() + "° pour l'étape " + f.getPallierActu()
				+ " de la fermentation.");
		EcranResume.addTexte("Chauffage jusqu'à " + temperaturePallier() + "° pour l'étape " + f.getPallierActu()
		+ " de la fermentation.");
		Alerte a = new Alerte("Chauffage jusqu'à " + temperaturePallier() + "° pour l'étape " + f.getPallierActu()
		+ " de la fermentation.");
		f.maintenirChauffage();
		f.commencerChauffage();


	}

	public void maintenirFermentation() {

		cuve.arreterChauffage();

	}


	public void changementPallier() {
		if (f.getPallierActu() + 1 < f.getEtapes().size()) {
			this.debutchauffe = true;
			debutrefroidissement = true;
			f.setPallierActu(f.getPallierActu() + 1);

			
		} else {
				enCours = false;
			EcranSimulation.afficherMessage("Fin de la fermentation.");
			EcranResume.addTexte("Fin de la fermentation.");
			EcranSimulation.finFermentation();
			Alerte a = new Alerte("Fin de la fermentation.");
		}
	
		f.setDateFinPallier(0);

	}
	
	public void changeDatePallier() {
		f.maintenirChauffage();
		f.setDateDebutPallier(System.currentTimeMillis());
		EcranSimulation.afficherMessage("Etape : " + f.getPallierActu() + " Temperature Atteinte : "
				+ f.getCuve().recupererTemperature() + " Veuillez laissez reposer "
				+ f.getEtapes().get(f.getPallierActu()).getDuree() / 60 + " heures.");
		EcranResume.addTexte("Etape : " + f.getPallierActu() + " Temperature Atteinte : "
				+ f.getCuve().recupererTemperature() + " Veuillez laissez reposer "
				+ f.getEtapes().get(f.getPallierActu()).getDuree() / 60 + " heures.");
		Alerte a = new Alerte("Etape : " + f.getPallierActu() + " Temperature Atteinte : "
				+ f.getCuve().recupererTemperature() + " Veuillez laissez reposer "
				+ f.getEtapes().get(f.getPallierActu()).getDuree() + " min.");
		long dateFinPallier = (long) (f.getEtapes().get(f.getPallierActu()).getDuree() * 60 * 1000);
		f.setDateFinPallier(dateFinPallier);

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
		this.resistance.Refroidissement();
		debutRefroidissement=System.currentTimeMillis();
		f.setDateDebutPallier(debutRefroidissement);
		this.debutrefroidissement = false;
		long coeffRefroidissment = (long) (((30*(100-tempAmbiante))*1000));
		this.setCoeffRefroidissment(coeffRefroidissment);
		System.out.println("Debut pallier refroidissement la temperature a atteindre est : " + this.temperaturePallier());
		EcranResume.addTexte("Debut pallier refroidissement la temperature a atteindre est : " + this.temperaturePallier());
		Alerte a = new Alerte("Debut pallier refroidissement la temperature a atteindre est : " + this.temperaturePallier());


	}

	
	

	public int getAccelerateur() {
		return accelerateur;
	}

	public void setAccelerateur(int accelerateur) {
		this.accelerateur = accelerateur;
	}

	public Fermentation getF() {
		return f;
	}

	public void setF(Fermentation f) {
		this.f = f;
	}

	public Cuve getCuve() {
		return cuve;
	}

	public void setCuve(Cuve cuve) {
		this.cuve = cuve;
	}

	public Resistance getResistance() {
		return resistance;
	}

	public void setResistance(Resistance resistance) {
		this.resistance = resistance;
	}

	public boolean isDebutchauffe() {
		return debutchauffe;
	}

	public void setDebutchauffe(boolean debutchauffe) {
		this.debutchauffe = debutchauffe;
	}

	public boolean isDebutrefroidissement() {
		return debutrefroidissement;
	}

	public void setDebutrefroidissement(boolean debutrefroidissement) {
		this.debutrefroidissement = debutrefroidissement;
	}

	public boolean isEnCours() {
		return enCours;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
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

	public long getCoeffRefroidissment() {
		return coeffRefroidissment;
	}

	public void setCoeffRefroidissment(long coeffRefroidissment) {
		this.coeffRefroidissment = coeffRefroidissment;
	}
	
	

}
