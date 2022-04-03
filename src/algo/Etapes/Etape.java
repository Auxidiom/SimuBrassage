package algo.Etapes;


public class Etape {

	double duree;//min
	long dateDebut;
	
	public Etape() {
		dateDebut = System.currentTimeMillis();
	}


	public long temps() {
		return System.currentTimeMillis() - dateDebut;
	}


	
	public double getDuree() {
		return duree;
	}



	public void setDuree(double duree) {
		this.duree = duree;
	}



	public long getDateDebut() {
		return dateDebut;
	}



	public void setDateDebut(long dateDebut) {
		this.dateDebut = dateDebut;
	}



}
