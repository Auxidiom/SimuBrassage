package ui.ihm;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import algo.Etapes.Pallier;
import ui.cd.ControleurDialogue;
import ui.cd.I18N;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import ui.ihm.EcranParametre;

public class EcranFermentation extends BorderPane {

	private ControleurDialogue cDialogue = null;
	private static final EcransApplication nomEcran = EcransApplication.FERMENTATION;
	
	private Label lTitre;
	private Button bSuivant;
	private Button bRetour;
	
	private VBox vbCenter;
	private VBox vbPallier;
	private Button bAddPallier;
	private HBox hbTop;
	private BorderPane bpBottom;
	
	private HBox hbTemperature;
	private Label lTemperature;
	private TextField tfTemperature;
	private Label lUnite;
	
	private String unite = "C";
	
	
	// On crÃƒÆ’Ã‚Â©e les differents composants de l'interface (Label, Button)
	private void createWidgets() {
			lTitre = new Label("Fermentation");
			lTitre.setFont(Font.font("Impact", FontWeight.BOLD, 60));
			lTitre.setTextFill(Color.web("#FFFFFF"));
			lTitre.setPadding(new Insets(40, 0, 0, 0));
			
	
			
			
			bSuivant = new Button("Suivant");
			bSuivant.textProperty().bind(I18N.createStringBinding("bouton.suivant"));
			bSuivant.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			bSuivant.setTextFill(Color.web("#FFFFFF"));
			bSuivant.setPrefSize(100, 50);
		
			bRetour = new Button("Retour");
			bRetour.textProperty().bind(I18N.createStringBinding("bouton.retour"));
			bRetour.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			bRetour.setTextFill(Color.web("#FFFFFF"));
			bRetour.setPrefSize(100, 50);
		
			
			bAddPallier = new Button ("+");
			bAddPallier.setPrefSize(80, 80);
			bAddPallier.setFont(Font.font(30));
			bAddPallier.setStyle("-fx-background-color: #292421;");
			bAddPallier.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	bAddPallier.setStyle("-fx-background-color: #231f1c;");
	            }
	        });
			bAddPallier.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	bAddPallier.setStyle("-fx-background-color: #292421;");
	            }
	        });
			bAddPallier.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	bAddPallier.setStyle("-fx-background-color: #161311;");
	            }
	        });
			bAddPallier.setTextFill(Color.web("#FFFFFF"));
			
			
			lTemperature = new Label("    temperature: ");
			lTemperature.setTextFill(Color.web("#FFFFFF"));
			lTemperature.setFont(Font.font(20));
			tfTemperature = new TextField();
			lUnite = new Label(" °"+ unite +"    ");
			lUnite.setTextFill(Color.web("#FFFFFF"));
			lUnite.setFont(Font.font(20));
			
	}
	
		// On crÃƒÆ’Ã‚Â©e les differents emplacements de l'interface (HBox, VBox), cela
		// permet de structurer la page
	private void createContainers() {
		vbPallier = new VBox();
		vbPallier.setSpacing(15);
		vbPallier.setMaxWidth(870);
		vbPallier.setAlignment(Pos.CENTER);
		vbPallier.setPadding(new Insets(0, 0, 150, 0));
		
		vbCenter = new VBox();
		vbCenter.setSpacing(30);
		vbCenter.setMaxWidth(870);
		vbCenter.setAlignment(Pos.BOTTOM_CENTER);
		vbCenter.setPadding(new Insets(0, 0, 90, 0));
			
		hbTop = new HBox();
		hbTop.setSpacing(30);
		hbTop.setAlignment(Pos.CENTER);
		
		
		bpBottom = new BorderPane();
		bpBottom.setPadding(new Insets(0, 10, 10, 0));

	
			
		hbTemperature = new HBox();
		hbTemperature.setStyle("-fx-background-color: #292421;");
		hbTemperature.getChildren().addAll(lTemperature, tfTemperature, lUnite);
		hbTemperature.setAlignment(Pos.CENTER);
		hbTemperature.setMaxWidth(350);
			
	}
		
		// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
		vbCenter.getChildren().addAll(vbPallier, hbTemperature);
		
		vbPallier.getChildren().add(bAddPallier);
		vbPallier.setAlignment(Pos.CENTER);
			
		hbTop.getChildren().addAll(lTitre);
		hbTop.setAlignment(Pos.CENTER);
			
		bpBottom.setLeft(bRetour);
		bpBottom.setRight(bSuivant);

	}
		
	// On definit l'emplacement des boites dans l'interface
	private void placementContainersInPane() {
		this.setTop(hbTop);
		this.setCenter(vbCenter);
		this.setBottom(bpBottom);
	}

	
	public void addPallier() {
		if (vbPallier.getChildren().size()<6) {
			GridPane box = new GridPane();
			box.setAlignment(Pos.CENTER);
			box.setGridLinesVisible(true);
			box.setStyle("-fx-background-color: #1f1b17;");
			
			RowConstraints contrainteLigne = new RowConstraints(50);
			box.getRowConstraints().add(contrainteLigne);
			
			Label lPallier = new Label("Pallier " + (vbPallier.getChildren().size()));
			lPallier.setTextFill(Color.web("#FFFFFF"));
			lPallier.setFont(Font.font(20));
			lPallier.setPadding(new Insets(10, 0, 10, 0));
			
			HBox hbTempsChauffe = new HBox();
			hbTempsChauffe.setAlignment(Pos.CENTER);
			hbTempsChauffe.setSpacing(5);
			Label lTDC = new Label("    temps de chauffe: ");
			lTDC.setTextFill(Color.web("#FFFFFF"));
			lTDC.setFont(Font.font(20));
			TextField textField1 = new TextField();
			Label lMinutes = new Label(" jours     ");
			lMinutes.setTextFill(Color.web("#FFFFFF"));
			lMinutes.setFont(Font.font(20));
			hbTempsChauffe.getChildren().addAll(lTDC, textField1, lMinutes);
			
			Button remove = new Button("x");
			remove.setFont(Font.font(15));
			remove.setTextFill(Color.web("#FFFFFF"));
			colorButton2(remove);
			
			remove.setOnAction((event) -> {
				vbPallier.getChildren().remove(box);
				
				for (int i=0; i < vbPallier.getChildren().size()-1 ; i++) {
					GridPane pallier = (GridPane) vbPallier.getChildren().get(i);
					Label numPallier = (Label)pallier.getChildren().get(1);
					numPallier.setText("Pallier " + (i+1));
					
				}
				
			});
		
			box.add(lPallier, 0, 0);
			box.add(hbTempsChauffe, 1, 0);
			box.add(remove, 2, 0);
			
			Button button = (Button)vbPallier.getChildren().get(vbPallier.getChildren().size()-1);
			vbPallier.getChildren().remove(vbPallier.getChildren().size()-1);
			vbPallier.getChildren().addAll(box, button);

			System.out.println("----");
		}
	}
	
	
	 private void configureButtons() {
	        
	        bSuivant.setOnAction((event) -> {
	            ArrayList<Pallier> etapes = new ArrayList<Pallier>();
	            for(int i = 0; i < vbPallier.getChildren().size() - 1; i++) {
	                GridPane v = (GridPane) vbPallier.getChildren().get(i);
	                HBox h = (HBox) v.getChildren().get(2);
	                TextField TF = (TextField) h.getChildren().get(1);
	                String TexteTemperature = tfTemperature.getText();
	                float temperature;
	                if (unite.equals("F"))  {
	                	temperature = (Float.parseFloat(TexteTemperature)-32);
	                	temperature /= 1.8;
	                } else {
	                	temperature = Float.parseFloat(TexteTemperature);
	                }
	                	
	                etapes.add(new Pallier((int)temperature, Integer.parseInt(TF.getText()) * 24 * 60));
	            }
	            cDialogue.creerFermentation();
	            cDialogue.ajouterPallierFermentation(etapes);
	            if(cDialogue.recupEmpatage() == "fcb") {
	                cDialogue.demarrageBrassage();
	            } else if(cDialogue.recupEmpatage() == "ang") {
	                cDialogue.demarrageBrassage();
	            }
	            else {
	                cDialogue.demarrageBrassage();
	            }
	            EcranSimulation.addButton(cDialogue.getIngredients());
	            cDialogue.afficherEcran(EcransApplication.SIMULATION);
	            
	            
	    
	        });
	    
	        
	        bRetour.setOnAction((event) -> {
	            cDialogue.afficherEcran(EcransApplication.ACCUEIL);
	        });
	        
	        
	        colorButton(bSuivant);
	        colorButton(bRetour);
	        
	        
	        bAddPallier.setOnAction((event) -> {
	            addPallier();
	        });
	    }
	
private void colorButton(Button button) {
		

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

private void colorButton2(Button button) {
	

	button.setStyle("-fx-background-color: #FF0000;");
	
	button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
        	button.setStyle("-fx-background-color: #FF0000;");
        }
    });

	button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
        	button.setStyle("-fx-background-color: #FF0000;");
        }
    });
	
	button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
        	button.setStyle("-fx-background-color: #FF0000;");
        }
    });
    
}

	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	private void changeUnit(String unite) {
        this.unite = unite;
        lUnite.setText(" °"+unite+"    ");
    }
	
	
		
	public EcranFermentation(ControleurDialogue cd) {

		cDialogue = cd;

		createWidgets();
		createContainers();
		placementWidgetsInContainers();
		placementContainersInPane();
		configureButtons();
		initBackground();
		
		addPallier();

		this.setVisible(false);
		cDialogue.enregistrerEcran(nomEcran, this);

	}
}
