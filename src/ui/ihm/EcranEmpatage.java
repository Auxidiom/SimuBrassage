package ui.ihm;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import algo.Etapes.EmpatagePerso;
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

public class EcranEmpatage extends BorderPane {

	private ControleurDialogue cDialogue = null;
	private static final EcransApplication nomEcran = EcransApplication.EMPATAGE;
	
	private Label lTitre;

	private Button bSuivant;
	private Button bRetour;
	
	private VBox vbCenter;
	private VBox vbPallier;
	private Button bAddPallier;
	private HBox hbTop;
	private BorderPane bpBottom;
	
	private String unite = "C";
	
	// On crÃƒÂ©e les differents composants de l'interface (Label, Button)
	private void createWidgets() {
			lTitre = new Label("Empatage");
			lTitre.textProperty().bind(I18N.createStringBinding("label.empatage"));
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
			
	}

		// On crÃƒÂ©e les differents emplacements de l'interface (HBox, VBox), cela
		// permet de structurer la page
	private void createContainers() {
			vbCenter = new VBox();
			vbCenter.setSpacing(15);
			vbCenter.setMaxWidth(900);
			
			hbTop = new HBox();
			hbTop.setSpacing(30);
			hbTop.setAlignment(Pos.CENTER);
			
			bpBottom = new BorderPane();
			bpBottom.setPadding(new Insets(0, 10, 10, 10));

		
		    
		}
		
		// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
			vbCenter.setAlignment(Pos.BOTTOM_CENTER);
			vbCenter.getChildren().add(bAddPallier);
			vbCenter.setTranslateY(-200);
			
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
	if (vbCenter.getChildren().size()<7) {
		GridPane box = new GridPane();
		box.setAlignment(Pos.CENTER);
		box.setGridLinesVisible(true);
		box.setStyle("-fx-background-color: #1f1b17;");
		
		RowConstraints contrainteLigne = new RowConstraints(50);
		box.getRowConstraints().add(contrainteLigne);
		
		Label lPallier = new Label("Pallier " + (vbCenter.getChildren().size()));
		lPallier.textProperty().bind(I18N.createStringBinding("label.palier-numero"));
		lPallier.setTextFill(Color.web("#FFFFFF"));
		lPallier.setFont(Font.font(20));
		lPallier.setPadding(new Insets(10, 0, 10, 0));
		
		HBox hbTempsChauffe = new HBox();
		hbTempsChauffe.setAlignment(Pos.CENTER);
		hbTempsChauffe.setSpacing(5);
		Label lTDC = new Label("temps de chauffe: ");
		lTDC.textProperty().bind(I18N.createStringBinding("label.temps-de-chauffe"));
		lTDC.setTextFill(Color.web("#FFFFFF"));
		lTDC.setFont(Font.font(20));
		TextField textField1 = new TextField();
		Label lMinutes = new Label(" minutes     ");
		lMinutes.textProperty().bind(I18N.createStringBinding("label.unite"));
		lMinutes.setTextFill(Color.web("#FFFFFF"));
		lMinutes.setFont(Font.font(20));
		hbTempsChauffe.getChildren().addAll(lTDC, textField1, lMinutes);
		
		HBox hbTemperature = new HBox();
		hbTemperature.setAlignment(Pos.CENTER);
		hbTemperature.setSpacing(5);
		Label lTemperature = new Label("    temperature: ");
		lTemperature.textProperty().bind(I18N.createStringBinding("label.temperature"));
		lTemperature.setTextFill(Color.web("#FFFFFF"));
		lTemperature.setFont(Font.font(20));
		TextField textField2 = new TextField();
		Label lDegres = new Label(" °"+unite+"    ");
		lDegres.setTextFill(Color.web("#FFFFFF"));
		lDegres.setFont(Font.font(20));
		hbTemperature.getChildren().addAll(lTemperature, textField2, lDegres);
		
		Button remove = new Button("x");
		remove.setPrefSize(35, 35);
		remove.setTextFill(Color.web("#FFFFFF"));
		remove.setFont(Font.font("Impact", FontWeight.BOLD, 10));
		remove.setFont(Font.font(15));
		colorButton(remove);
		remove.setOnAction((event) -> {
			vbCenter.getChildren().remove(box);
			
			for (int i=0; i < vbCenter.getChildren().size()-1 ; i++) {
				GridPane pallier = (GridPane) vbCenter.getChildren().get(i);
				Label numPallier = (Label)pallier.getChildren().get(1);
				numPallier.setText("Pallier " + (i+1));
				
			}
			
		});
		
		box.add(lPallier, 0, 0);
		box.add(hbTempsChauffe, 1, 0);
		box.add(hbTemperature, 2, 0);
		box.add(remove, 3, 0);
		
		Button button = (Button)vbCenter.getChildren().get(vbCenter.getChildren().size()-1);
		vbCenter.getChildren().remove(vbCenter.getChildren().size()-1);
		vbCenter.getChildren().addAll(box, button);
		}
	}
	
	private void configureButtons() {
		
		  bSuivant.setOnAction((event) -> {
	            ArrayList<Pallier> etapes = new ArrayList<Pallier>();
	            for(int i = 0; i < vbCenter.getChildren().size() - 1; i++) {
	                GridPane v = (GridPane) vbCenter.getChildren().get(i);
	                HBox h = (HBox) v.getChildren().get(2);
	                TextField TF = (TextField) h.getChildren().get(1);
	                HBox h2 = (HBox) v.getChildren().get(3);
	                TextField TF2= (TextField) h2.getChildren().get(1);
	                
	                String TexteTemperature = TF2.getText();
	                float temperature;
	                if (unite.equals("F"))  {
	                	temperature = (Float.parseFloat(TexteTemperature)-32);
	                	temperature /= 1.8;
	                } else {
	                	temperature = Float.parseFloat(TexteTemperature);
	                }
	                etapes.add(new Pallier((int)temperature, Integer.parseInt(TF.getText())));
	            }
	            
	            	EmpatagePerso empPerso = new EmpatagePerso(etapes);
	            	cDialogue.setEmpPerso(empPerso);
	            	
	            	
	            
	          	    cDialogue.afficherEcran(EcransApplication.CUISSONMOUT);
	            
	            
	    
	        });
		bRetour.setOnAction((event) -> {
			cDialogue.afficherEcran(EcransApplication.ACCUEIL);
		});
	
		colorButton2(bSuivant);
		colorButton2(bRetour);
		
		
		
		bAddPallier.setOnAction((event) -> {
			addPallier();
		});
	}
	
	private void colorButton(Button button) {
		

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
	private void colorButton2(Button button) {
		

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
	
	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	private void changeUnit(String unite) {
        this.unite = unite;
        for (int i=0; i<vbCenter.getChildren().size()-1; i++) {
            GridPane GP = (GridPane)vbCenter.getChildren().get(i);
            HBox HB = (HBox)GP.getChildren().get(3);
            Label L = (Label) HB.getChildren().get(2);

            L.setText(" °"+unite+"    ");

        }
    }
	
		
	public EcranEmpatage(ControleurDialogue cd) {

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
