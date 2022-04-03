package Etapes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algo.Composition.*;
import algo.Etapes.*;
import algo.Etapes.Pallier;

public class TestBrassage {

	@Test
	public void testComplet() throws InterruptedException {

		Pallier p1 = new Pallier(21, 0.1);
		Pallier p2 = new Pallier(25, 0.1);

		ArrayList<Pallier> ps = new ArrayList<>();
		ps.add(p1);
		ps.add(p2);
		EmpatagePerso emp = new EmpatagePerso(ps);
		Ebullition ebu = new Ebullition(30, 0.2);
		Refroidissement r = new Refroidissement(21,0.2);
		Eau e = new Eau(20);
		e.setTemperature(80);

		Brassage b = new Brassage(e, emp,true);
		b.getPrech().getCE().getR().setPuissance(454545);;
		b.setEbu(ebu);
		b.setRef(r);
		b.commencerChauffage();
		b.maintenirChauffage();
		System.out.println(b.recupererTemperature());

		Thread.sleep(6000);
		b.recupererTemperature();
		Malt m = new Malt(100);
		b.preparerIngredient(m);
		b.ajouterIngredient("Malt");
		Thread.sleep(10000);
		System.out.println(b.recupererTemperature());
		Thread.sleep(20000);
		System.out.println(b.recupererTemperature());
		Thread.sleep(10000);
		System.out.println(b.recupererTemperature());
		Thread.sleep(10000);
		Sucre s =new Sucre(100);
		b.preparerIngredient(s);
		b.ajouterIngredient("Sucre");
		Thread.sleep(20000);
		b.getPrech().getCE().getEau().setTemperature(100);
		System.out.println(b.recupererTemperature());
		int i = 0;
		while(i<20) {
		Thread.sleep(1000);	
		System.out.println(b.recupererTemperature());
		i++;
		}
	}

}
