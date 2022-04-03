package algo.Etapes;

public class Refroidissement extends Etape{

	private int tempMin;
	
	public Refroidissement() {
		super.setDuree(30.0);
		tempMin = 25;
	}
	
	public Refroidissement(int t,double d) {
		super.setDuree(d);
		tempMin = t;
	}

	public int getTempMin() {
		return tempMin;
	}

	public void setTempMin(int tempMin) {
		this.tempMin = tempMin;
	}
	
	
}
