package ui.ihm;

import java.io.FileNotFoundException;

import ui.cd.ControleurDialogue;
import ui.cd.I18N;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EcranAccueil extends BorderPane {

	private ControleurDialogue cDialogue = null;
	private static final EcransApplication nomEcran = EcransApplication.ACCUEIL;
	
	private Label lTitre;
	private Button bQuitter;
	private Button bFraBe;
	private Button bAnglaise;
	private Button bPerso;
	private Button bParametre;
	
	private VBox vbCenter;
	private VBox vbTop;
	private VBox vbBottom;
	
	// On crÃƒÂ©e les differents composants de l'interface (Label, Button)
	private void createWidgets() {
			lTitre = new Label("SimuBrassage");
			lTitre.setFont(Font.font("Impact", FontWeight.BOLD, 60));
			lTitre.setTextFill(Color.web("#FFFFFF"));
			lTitre.setPadding(new Insets(40, 0, 0, 0));
			
			
			bFraBe = new Button("FRANCO-BELGE");
			bFraBe.textProperty().bind(I18N.createStringBinding("bouton.franco-belge"));
			bFraBe.setPrefSize(280, 70);
			bFraBe.setTextFill(Color.web("#FFFFFF"));
			bFraBe.setFont(Font.font("Impact", FontWeight.BOLD, 20));
	
			
			bAnglaise = new Button("ANGLAISE");
			bAnglaise.textProperty().bind(I18N.createStringBinding("bouton.anglaise"));
			bAnglaise.setPrefSize(280, 70);
			bAnglaise.setTextFill(Color.web("#FFFFFF"));
			bAnglaise.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			
			bPerso = new Button("PERSONNALISÉ");
			bPerso.textProperty().bind(I18N.createStringBinding("bouton.personnalise"));
			bPerso.setPrefSize(280, 70);
			bPerso.setTextFill(Color.web("#FFFFFF"));
			bPerso.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			
			bQuitter = new Button("Quitter");
			bQuitter.textProperty().bind(I18N.createStringBinding("bouton.quitter"));
			bQuitter.setPrefSize(200, 50);
			bQuitter.setTextFill(Color.web("#FFFFFF"));
			bQuitter.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			
			bParametre = new Button("Paramètres");
			bParametre.textProperty().bind(I18N.createStringBinding("bouton.parametres"));
			bParametre.setPrefSize(200, 50);
			bParametre.setTextFill(Color.web("#FFFFFF"));
			bParametre.setFont(Font.font("Impact", FontWeight.BOLD, 20));
	}

		// On crÃƒÂ©e les differents emplacements de l'interface (HBox, VBox), cela
		// permet de structurer la page
	private void createContainers() {
			vbCenter = new VBox();
			vbCenter.setSpacing(50);
			
			vbTop = new VBox();
			
			vbBottom = new VBox();
			vbBottom.setPadding(new Insets(0, 10, 10, 0));
		}
		
		// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
			vbCenter.getChildren().addAll(bFraBe, bAnglaise, bPerso,bParametre);
			vbCenter.setAlignment(Pos.CENTER);
			
			vbTop.getChildren().add(lTitre);
			vbTop.setAlignment(Pos.CENTER);
			
			vbBottom.getChildren().add(bQuitter);
			vbBottom.setAlignment(Pos.CENTER_RIGHT);
		}
		
	// On definit l'emplacement des boites dans l'interface
	private void placementContainersInPane() {
		this.setTop(vbTop);
		this.setCenter(vbCenter);
		this.setBottom(vbBottom);
	}
	
	private void coulorButton(Button button) {
		

		button.setStyle("-fx-background-color: #d0ac64;");
		
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #d0ac64;");
            }
        });

		button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #d0ac64;");
            }
        });
		
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #d0ac64;");
            }
        });
        
	}
	private void coulorButton2(Button button) {
		

		button.setStyle("-fx-background-color: #c7a27c;");
		
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #c7a27c;");
            }
        });

		button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #c7a27c;");
            }
        });
		
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #c7a27c;");
            }
        });
        
	}
	
	private void configureButtons() {
		
		bQuitter.setOnAction((event) -> {
			Platform.exit();
		});
		
		coulorButton(bFraBe);
		coulorButton(bAnglaise);
		coulorButton(bPerso);
		coulorButton(bQuitter);
		coulorButton2(bParametre);
		
		
	}
	
	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	
		
	public EcranAccueil(ControleurDialogue cd) {

		cDialogue = cd;

		
		
		createWidgets();
		createContainers();
		placementWidgetsInContainers();
		placementContainersInPane();
		configureButtons();
		initBackground();
		
		
		bFraBe.setOnAction((event) -> {
			cDialogue.enregistrerEmpatage("fcb");
			cDialogue.afficherEcran(EcransApplication.CUISSONMOUT);
		});
		
		bAnglaise.setOnAction((event) -> {
			cDialogue.enregistrerEmpatage("ang");
			cDialogue.afficherEcran(EcransApplication.CUISSONMOUT);
		});
		
		bPerso.setOnAction((event) -> {
			cDialogue.afficherEcran(EcransApplication.EMPATAGE);
		});
		
		bParametre.setOnAction((event) -> {
			cDialogue.afficherEcran(EcransApplication.PARAMETRES);
		});
		
		

		this.setVisible(false);
		cDialogue.enregistrerEcran(nomEcran, this);

	}

}
