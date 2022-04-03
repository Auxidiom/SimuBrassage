package algo.Etapes;

public class Ebullition extends Etape{
	

	private int maxTemp = 100;

	
	public Ebullition() {
		super.setDuree(80);
	}
	
	public Ebullition(int t, double d) {
		maxTemp = t;
		super.setDuree(d);
	}
	public int getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	
	
	

}
