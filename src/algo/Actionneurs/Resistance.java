package algo.Actionneurs;

public class Resistance {
	private int p =0;
	 
	
	public void eteindre() {
		this.p = 0;
	}
	public void activer() {
		this.p = 3000;
	}
	
	public int getPuissance() {
		return p;
	}
	public void setPuissance(int p) {
		this.p = p;
	}
	public void Refroidissement() {
		this.p = 1;
	}
	
}
