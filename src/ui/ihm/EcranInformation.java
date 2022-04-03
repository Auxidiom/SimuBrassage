package ui.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import algo.Composition.Epice;
import algo.Composition.Fruits;
import algo.Composition.Houblon;
import algo.Composition.Ingredient;
import algo.Composition.Levures;
import algo.Composition.Malt;
import algo.Composition.Sucre;
import ui.cd.ControleurDialogue;
import ui.cd.ControleurDonnees;
import ui.cd.I18N;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EcranInformation extends BorderPane {

	private ControleurDialogue cDialogue = null;
	// On donne un nom a cette interface pour permettre l'identification par le
	// systeme
	private static final EcransApplication nameScreen = EcransApplication.INFORMATION;

	// DEFINITION DU BORDER
	
	Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					new BorderWidths(1), new Insets(5)));
	
	
	
	Image tank = new Image ("./Ressources/Images/schema.png");
	
	
	private Label lTitre;
	private HBox hbTop;
	private HBox hbBottom;
	private Button bRetour;
	private VBox vbTank;
	//private StackPane spTank;
	ImageView ivTank;
	
	
	

	// On crée les differents composants de l'interface (Label, Button)
	private void createWidgets() throws FileNotFoundException {
		ivTank = new ImageView(tank);
		ivTank.setFitHeight(700);
		ivTank.setFitWidth(500);
		
		bRetour = new Button("Retour");
		bRetour.textProperty().bind(I18N.createStringBinding("bouton.retour"));
		bRetour.setFont(Font.font("Impact", FontWeight.BOLD, 20));
		bRetour.setTextFill(Color.web("#FFFFFF"));
		bRetour.setPrefSize(100, 50);
	
		lTitre = new Label("Information");
		lTitre.textProperty().bind(I18N.createStringBinding("label.information"));
		lTitre.setFont(Font.font("Impact", FontWeight.BOLD, 60));
		lTitre.setTextFill(Color.web("#FFFFFF"));
		lTitre.setPadding(new Insets(40, 0, 0, 0));
	}

	// On crée les differents emplacements de l'interface (HBox, VBox), cela
	// permet de structurer la page
	private void createContainers() {
		
		vbTank = new VBox();
		hbBottom = new HBox();
	
		hbTop = new HBox();
		hbTop.setSpacing(30);
		hbTop.setAlignment(Pos.CENTER);

		
	
		
		
		
	}

	// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
		
	
		
		
		
		hbTop.getChildren().add(lTitre);
		hbTop.setAlignment(Pos.CENTER);
		
		hbBottom.getChildren().add(bRetour);
		hbBottom.setAlignment(Pos.CENTER_LEFT);
		
		
		vbTank.getChildren().add(ivTank);
		vbTank.setAlignment(Pos.CENTER);
		
	
		
	}

	// On definit l'emplacement des boites dans l'interface
	private void placementContainersInPane() {

		// ON ASSIGNE A CHAQUE BOX LEUR EMPLACEMENT
		this.setTop(hbTop);
		this.setBottom(hbBottom);
		this.setCenter(vbTank);
	}

	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	
	private void configureButtons() {
		
		bRetour.setOnAction((event) -> {
            cDialogue.afficherEcran(EcransApplication.SIMULATION);
        });
		
	
		
	}

	
private static void colorButton(Button button) {
		

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
		

	public EcranInformation(ControleurDialogue cDialogue2) throws FileNotFoundException {

		cDialogue = cDialogue2;

		createWidgets();
		initBackground();
		createContainers();
		placementWidgetsInContainers();
		placementContainersInPane();
		configureButtons();
		
		colorButton(bRetour);
		

		this.setVisible(false);
		cDialogue.enregistrerEcran(nameScreen, this);

	}
	

}
