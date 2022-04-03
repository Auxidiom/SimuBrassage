package algo.Composition;

public class Malt extends Ingredient {

	private Boolean concassage = false;
	
	public Malt(int poids) {
		super(poids);
	}
	
	public void Concasser() {
		concassage = true;
	}
	
}
