package algo.Etapes;

import java.util.ArrayList;

import algo.Materiel.*;

public abstract class Empatage{


	private ArrayList<Pallier> palliers = new ArrayList<>();
	
	public Empatage() {
		
	}

	public ArrayList<Pallier> getPalliers() {
		return palliers;
	}

	public void setPalliers(ArrayList<Pallier> palliers) {
		this.palliers = palliers;
	}
	

	
	
	
}
