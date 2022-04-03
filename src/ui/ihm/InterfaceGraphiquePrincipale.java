package ui.ihm;

import ui.cd.ControleurDialogue;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InterfaceGraphiquePrincipale extends Application {

	private StackPane root = new StackPane();
	private Node ecranCourant = null;
	static ControleurDialogue cDialogue = null;
	private Scene scene = new Scene(root);

	@Override
	public void start(Stage primaryStage) throws Exception {

		cDialogue.definirIHMPrincipale(this);

		// ETAPE 1 : Désactivez la décoration de la fenetre (on parle aussi de style de
		// celle-ci)
		primaryStage.setTitle("SimuBrassage");
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setMaximized(true);

		// ETAPE 1 : Cachez le message du plein écran
		primaryStage.setFullScreenExitHint("");

		// on ajout tous les conteneurs

		root.getChildren().add(new EcranParametre(cDialogue));
		root.getChildren().add(new EcranEmpatage(cDialogue));
		root.getChildren().add(new EcranAccueil(cDialogue));
		root.getChildren().add(new EcranCuissonMout(cDialogue));
		root.getChildren().add(new EcranFermentation(cDialogue));
		root.getChildren().add(new EcranSimulation(cDialogue));
		root.getChildren().add(new EcranResume(cDialogue));
		root.getChildren().add(new EcranInformation(cDialogue));

		cDialogue.afficherEcran(EcransApplication.ACCUEIL); // changer cette ligne si vous voulez afficher un autre
															// �cran pendant l'ETAPE 1

		// ETAPE 1 : Ajoutez la scène dans la fenêtre principale
		primaryStage.setScene(scene);

		// ETAPE 1 : Affichez la fenêtre principale
		primaryStage.show();

	}

	public static void lancement(String[] args, ControleurDialogue cd) {
		cDialogue = cd;
		InterfaceGraphiquePrincipale.launch(args);
	}

	public void afficherEcran(Node n) {
		if (ecranCourant != null)
			ecranCourant.setVisible(false);
		n.setVisible(true);
		ecranCourant = n;
	}
	
	public StackPane getRoot() {
		return root;
	}

	public Scene obtenirScene() {
		return scene;
	}
}
