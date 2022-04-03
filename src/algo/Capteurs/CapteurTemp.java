package algo.Capteurs;

import algo.Composition.*;

public class CapteurTemp {
	private int accelerateur=1;
	public float prelevementTemp(float i) {
		return i;
	}

	public long tempsPasse(long debut) {
		long temps = System.currentTimeMillis() - debut;
		temps = temps * accelerateur;
		return temps;
	}
	
	public float recupererTemperature(long debut, int puissance, Eau e,int maxT) {
		if(puissance == 0) {
			e.setTempInitiale(e.getTemperature());
			return e.getTemperature();
		}
		else {
			float t = ((tempsPasse(debut) / 1000) * puissance ) / (e.getVolume() * 4186) + e.getTempInitiale();
			
			if (t >= maxT) {
				t = maxT;
			}
			e.setTemperature(t);
			return t;
		}
	}
	
	public float recupererTemperatureMomentT(long debut, int puissance, Eau e,int maxT,long moment) {
		if(puissance == 0) {
			e.setTempInitiale(e.getTemperature());
			return e.getTemperature();
		}else {
		float t = ((moment - debut) / 1000) * puissance / (e.getVolume() * 4186) + e.getTempInitiale(); 
		if (t >= maxT) {
			t = maxT;
		}
		e.setTemperature(t);
		return t;
		}
	}

	public int getAccelerateur() {
		return accelerateur;
	}

	public void setAccelerateur(int accelerateur) {
		this.accelerateur = accelerateur;
	}
	
	
	
}
