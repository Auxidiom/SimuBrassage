package algo.Etapes;

public class Pallier {

	private int maxTemp;
	private double duree; // minutes

	public Pallier(int temp, double m) {
		maxTemp = temp;
		duree = m;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(long duree) {
		this.duree = duree;
	}

}
