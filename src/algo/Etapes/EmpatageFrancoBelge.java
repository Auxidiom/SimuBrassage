package algo.Etapes;

public class EmpatageFrancoBelge  extends Empatage{

	public EmpatageFrancoBelge() {
		super();
		Pallier p1 = new Pallier(54,12);
		Pallier p2 = new Pallier(63,30);
		Pallier p3 = new Pallier(72,40);
		Pallier p4 = new Pallier(78,10);
		
		this.getPalliers().add(p1);
		this.getPalliers().add(p2);
		this.getPalliers().add(p3);
		this.getPalliers().add(p4);
	}

}
