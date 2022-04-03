package ui.ihm;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

import com.sun.prism.Image;

import ui.cd.ControleurDialogue;
import ui.cd.I18N;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
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

public class EcranResume extends BorderPane {

	private ControleurDialogue cDialogue = null;
	private static final EcransApplication nomEcran = EcransApplication.RESUME;
	
	
	Border border = new Border(new BorderStroke(Color.BEIGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
			new BorderWidths(1), new Insets(5)));
	
	private Label lTitre;
	
	private Button bRetour;
	
	private static VBox vbCenter;
	private VBox vbTop;
	private VBox vbBottom;

	// On crÃƒÂ©e les differents composants de l'interface (Label, Button)
	private void createWidgets() {
			lTitre = new Label("Résumé");
			lTitre.setFont(Font.font("Impact", FontWeight.BOLD, 60));
			lTitre.setTextFill(Color.web("#FFFFFF"));
			lTitre.setPadding(new Insets(40, 0, 0, 0));
			


			
			bRetour = new Button("Quitter");
			bRetour.setPrefSize(200, 50);
			bRetour.setTextFill(Color.web("#FFFFFF"));
			bRetour.setFont(Font.font("Impact", FontWeight.BOLD, 20));

			

	}

		// On crÃƒÂ©e les differents emplacements de l'interface (HBox, VBox), cela
		// permet de structurer la page
	private void createContainers() {
			vbCenter = new VBox();
			
			vbTop = new VBox();
			
			vbBottom = new VBox();
			vbBottom.setPadding(new Insets(0, 10, 10, 0));
		}
		
		// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
			
			vbCenter.getChildren().addAll();
			vbCenter.setAlignment(Pos.CENTER);
			vbCenter.setSpacing(10);
			
			vbTop.getChildren().add(lTitre);
			vbTop.setAlignment(Pos.CENTER);
			
			vbBottom.getChildren().add(bRetour);
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
	
	public static void addTexte(String texte) {
		Label lTexte = new Label(EcranSimulation.getTimer() + " : "+ texte);
		lTexte.setFont(Font.font(15));
		lTexte.setTextFill(Color.web("#FFFFFF"));
		lTexte.setPadding(new Insets(10, 0, 0, 0));
		
		vbCenter.getChildren().add(lTexte);
		
	}

	
	private void configureButtons() {
		
		bRetour.setOnAction((event) -> {
			cDialogue.afficherEcran(EcransApplication.ACCUEIL);
		});
		
		coulorButton(bRetour);
		
		  
		
	}
	
	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	
		
	public EcranResume(ControleurDialogue cd) {

		cDialogue = cd;

		
		
		createWidgets();
		createContainers();
		placementWidgetsInContainers();
		placementContainersInPane();
		configureButtons();
		initBackground();


		
		

		this.setVisible(false);
		cDialogue.enregistrerEcran(nomEcran, this);

	}
	
	public void definirLangue(Labeled n) {
		List<Locale> Locales = I18N.getSupportedLocales();
		
		I18N.setLocale(Locales.get(Integer.valueOf(n.getId())));
	}

}
