	package ui.simuBrassage;

import ui.cd.ControleurDialogue;
import ui.ihm.InterfaceGraphiquePrincipale;

public class Lanceur {

	public static void main(String[] args) {
		ControleurDialogue cd = new ControleurDialogue();
		InterfaceGraphiquePrincipale.lancement(args, cd);

	}

}
