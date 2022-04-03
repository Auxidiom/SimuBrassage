package Etapes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algo.Composition.*;
import algo.Etapes.Brassage;
import algo.Etapes.Ebullition;
import algo.Etapes.EmpatagePerso;
import algo.Etapes.Fermentation;
import algo.Etapes.Pallier;
import algo.Etapes.Refroidissement;
import algo.Materiel.*;

public class TestAccelerationTemps {
	
	/*@Test
	public void accelerationTempsCuve() throws InterruptedException {
		//sans accélération
		Eau e = new Eau(2);
		Cuve c = new Cuve(e);
		c.activerChauffage();
		Thread.sleep(10000);
		System.out.println(c.recupererTemperature());
		
		//avec accélération
		Eau e1 = new Eau(2);
		Cuve c1 = new Cuve(e1);
		c1.getCt().setAccelerateur(2);
		c1.activerChauffage();
		Thread.sleep(5000);
		System.out.println(c1.recupererTemperature());
		assertEquals(e.getTemperature(),e1.getTemperature(),1);
		
	}
	@Test
	public void accelerationTempsBrassage() throws InterruptedException {
		Pallier p1 = new Pallier(21, 1);
		Pallier p2 = new Pallier(25, 0.1);

		ArrayList<Pallier> ps = new ArrayList<>();
		ps.add(p1);
		ps.add(p2);
		EmpatagePerso emp = new EmpatagePerso(ps);
		Ebullition ebu = new Ebullition(30, 0.2);
		Refroidissement r = new Refroidissement(20,0.2);
		Eau e = new Eau(20);
		e.setTemperature(21);

		Brassage b = new Brassage(e, emp,true);
		b.setEbu(ebu);
		b.setRef(r);
		b.commencerChauffage();
		Thread.sleep(100);
		System.out.println(b.tempsPasse());
		b.maintenirChauffage();
		System.out.println(b.recupererTemperature());
		b.setAccelerateur(2);
		Thread.sleep(30000);
		System.out.println(b.recupererTemperature());
		assertEquals(1,b.getPallierActu());
		
	}
	
	 @Test

	 public void accelerationTempsFermentation() throws InterruptedException {
	 
		 
			Pallier p1 = new Pallier(21, 1);
			Pallier p2 = new Pallier(25, 0.1);
			Pallier p3 = new Pallier(23, 0.1);
			
			ArrayList<Pallier> ps = new ArrayList<>();
			ps.add(p1);
			ps.add(p2);
			ps.add(p3);
			
			Eau e = new Eau(2);
			e.setTemperature(21);
			Cuve c = new Cuve(e);
			
			Fermentation f= new Fermentation(c,ps,true);
			System.out.println(f.recupererTemperature());
			f.setAccelerateur(2);
			Thread.sleep(30000);
			System.out.println(f.recupererTemperature());
			assertEquals(1,f.getPallierActu());
		 
	 }*/
	@Test
	public void accelerateurTempsBrassageFermentation()throws InterruptedException {
		Pallier p1 = new Pallier(21, 1);
		Pallier p2 = new Pallier(25, 0.1);

		ArrayList<Pallier> ps = new ArrayList<>();
		ps.add(p1);
		ps.add(p2);
		EmpatagePerso emp = new EmpatagePerso(ps);
		Ebullition ebu = new Ebullition(100, 0.2);
		Refroidissement r = new Refroidissement(21,0.2);
		Eau e = new Eau(20);
		e.setTemperature(21);

		Brassage b = new Brassage(e, emp,true);
		b.setEbu(ebu);
		b.setRef(r);
		b.commencerChauffage();
		Thread.sleep(100);
		b.creerFermentation();
		System.out.println(b.tempsPasse());
		b.maintenirChauffage();
		Pallier pf1 = new Pallier(21, 1);
		Pallier pf2 = new Pallier(25, 0.1);
		Pallier pf3 = new Pallier(23, 0.1);
		
		ArrayList<Pallier> fps = new ArrayList<>();
		fps.add(pf1);
		fps.add(pf2);
		fps.add(pf3);

		
		Fermentation f= new Fermentation(b.getPrech().getCE(),fps,true);

		b.getControleurTemperature().setFermentation(f);
	
		b.setAccelerateur(100);
		
		while(b.isEnCours()) {
			Thread.sleep(1000);
			System.out.println(b.recupererTemperature());
			
		}
	
		b.getControleurTemperature().getFermentation().setEtapes(fps);
		while(f.getCf().isEnCours()) {
			Thread.sleep(1000);
				System.out.println(f.recupererTemperature());
			
		}
	
	
	}
}
