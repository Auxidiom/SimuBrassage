package cuve;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

import algo.Composition.*;
import algo.Materiel.*;

class TestsCuves {
	
		@Test
		public void expo() {
			double n1=12004;
			double n2 =6771428;
			double t =-n1/n2;
			System.out.println(t);
			System.out.println(Math.exp(t));
		}
	
		@Test
		public void initierCuve() {
			Eau e = new Eau(10);
			Cuve CE = new Cuve(e);
			CE.activerChauffage();
			assertEquals(CE.getPuissanceChauffe(), 1300);
			assertEquals(CE.recupererTemperature(), 21);
	
		}
		
		@Test
		public void chauffage() {
			Eau e = new Eau(10);
			Cuve CE = new Cuve(e);
			CE.activerChauffage();
		
			assertEquals(CE.recupererTemperatureMomentT(System.currentTimeMillis() + 60000), 22, 0.1);
			assertEquals(CE.recupererTemperatureMomentT(System.currentTimeMillis() + 60000 * 5), 31, 0.1);
			assertEquals(CE.recupererTemperatureMomentT(System.currentTimeMillis() + 60000 * 20), 68, 0.1);
			assertEquals(CE.recupererTemperatureMomentT(System.currentTimeMillis() + 60000 * 60), 100, 0.1);
		}
		
		@Test
		public void regulerTemperature() {
			Eau e = new Eau(10);
			Cuve CE = new Cuve(e);
			CE.activerChauffage();
			CE.setMaxTemperature(60);
			assertEquals(CE.recupererTemperatureMomentT(System.currentTimeMillis() + 60000 * 60), 60, 0.1);
		}
		
	}


