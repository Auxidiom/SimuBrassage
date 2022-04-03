package Etapes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import algo.Composition.*;
import algo.Etapes.*;
import algo.Materiel.*;

public class TestFermentation {
	
	@Test
	public void fermentationTest() throws InterruptedException {
		Pallier p1 = new Pallier(21, 0.1);
		Pallier p2 = new Pallier(25, 0.1);
		Pallier p3 = new Pallier(23, 0.1);
		
		ArrayList<Pallier> ps = new ArrayList<>();
		ps.add(p1);
		ps.add(p2);
		ps.add(p3);
		
		Eau e = new Eau(2);
		Cuve c = new Cuve(e);
		
		Fermentation f= new Fermentation(c,ps,true);

		System.out.println(f.recupererTemperature());
	

		while(f.getCf().isEnCours()) {
			System.out.println(f.recupererTemperature());
			Thread.sleep(1000);
	
		}
		assertEquals(f.recupererTemperature(),(float)23,1);
		
		
	}
	
	

}
