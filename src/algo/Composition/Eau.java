package algo.Composition;

public class Eau extends Ingredient {
	private float temp=21;
	private int volume;
	private int densite=997;	//kg.m-�
	private int capaciteCalo=4180; //J. kg-1
	private float tempInitial = 21;
	
	public float getTemperature() {
		return temp;
	}

	public int getDensite() {
		return densite;
	}

	public void setDensite(int densite) {
		this.densite = densite;
	}

	public int getCapaciteCalo() {
		return capaciteCalo;
	}

	public void setCapaciteCalo(int capaciteCalo) {
		this.capaciteCalo = capaciteCalo;
	}

	public int getVolume() {
		return volume;
	}

	public Eau(int v) {
		super(v);
		volume=v;
	}
	
	public void setTemperature(float t) {
		temp=t;
	}
	
	public void setVolume(int v) {
		volume = v;
	}
	
	public float getTempInitiale() {
		return this.tempInitial;
	}
	public void setTempInitiale(float t) {
		 this.tempInitial = t;
	}
}
