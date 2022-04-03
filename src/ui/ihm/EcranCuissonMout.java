package ui.ihm;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import ui.cd.ControleurDialogue;
import ui.cd.I18N;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EcranCuissonMout extends BorderPane {

	private ControleurDialogue cDialogue = null;
	private static final EcransApplication nomEcran = EcransApplication.CUISSONMOUT;
	private HashMap<String, TextField> input = new HashMap<String, TextField>();
	
	Border border = new Border(new BorderStroke(Color.BEIGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
			new BorderWidths(1), new Insets(5)));
	
	private VBox vbIngredientsObl;
	private Label lIngredientsObl;
	
	private VBox vbSeparation;
	
	private HBox hbIngredients;
	private HBox hbIngredientsValue;
	private FlowPane fpBoutonsAjouts;
	private VBox vbAjouts;
	
	private Label lTitre;
	private Button bSuivant;
	private Button bRetour;
	
	private HBox hbCenter;
	private VBox vbTop;
	private BorderPane bpBotton;
	
	private Boolean fruitsBtn;
	
	private ArrayList<String> buttonsIngredients = new ArrayList<String>();
	
	// On crÃƒÂ©e les differents composants de l'interface (Label, Button)
	private void createWidgets() {
		lIngredientsObl = new Label("Ingredients obligatoires : ");
		lIngredientsObl.textProperty().bind(I18N.createStringBinding("label.ingredients-obligatoires"));
		lIngredientsObl.setTextFill(Color.web("#FFFFFF"));
		lIngredientsObl.setFont(Font.font("Impact", FontWeight.BOLD, 20));
		
		lTitre = new Label("Cuisson du moût");
		lTitre.textProperty().bind(I18N.createStringBinding("label.cuisson-du-mout"));
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
		
		
			
	}

		// On crée  les differents emplacements de l'interface (HBox, VBox), cela
		// permet de structurer la page
	private void createContainers() {
		vbIngredientsObl = new VBox();
		vbIngredientsObl.setAlignment(Pos.CENTER);
		vbIngredientsObl.setSpacing(20);
		
		hbIngredients = new HBox();
		hbIngredients.setAlignment(Pos.CENTER);
		hbIngredients.setSpacing(10);
		hbIngredients.setMaxHeight(200);
		hbIngredientsValue = new HBox();
		hbIngredientsValue.setAlignment(Pos.CENTER);
		hbIngredientsValue.setSpacing(10);
		//hbIngredientsValue.setMaxHeight(200);
		fpBoutonsAjouts = new FlowPane(15,15);
		fpBoutonsAjouts.setPrefWidth(200);
		fpBoutonsAjouts.setAlignment(Pos.CENTER);
		fpBoutonsAjouts.setStyle("-fx-background-color: #38322b;");
		vbAjouts = new VBox();
		vbAjouts.setSpacing(20);
		vbAjouts.setAlignment(Pos.CENTER);
		vbAjouts.setPrefWidth(300);
		vbAjouts.setMaxHeight(0);
		vbAjouts.setStyle("-fx-background-color: #38322b;");
		
		vbSeparation = new VBox();
		vbSeparation.setBorder(border);
		vbSeparation.setMaxHeight(400);
		
		
		hbCenter = new HBox();
		hbCenter.setSpacing(70);
		hbCenter.setAlignment(Pos.CENTER);
		hbCenter.setSpacing(200);
			
		vbTop = new VBox();
		vbTop.setAlignment(Pos.CENTER);
		

		bpBotton = new BorderPane();
		bpBotton.setPadding(new Insets(0, 10, 10, 10));

	}
		
		// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
		vbIngredientsObl.getChildren().add(lIngredientsObl);
		
		hbIngredients.getChildren().addAll(fpBoutonsAjouts);
		
		hbIngredientsValue.getChildren().addAll(vbAjouts);
		
		hbCenter.getChildren().addAll(vbIngredientsObl, vbSeparation, hbIngredients, hbIngredientsValue);
		
		vbTop.getChildren().add(lTitre);
		
		bpBotton.setLeft(bRetour);
		bpBotton.setRight(bSuivant);

	}
		
	// On definit l'emplacement des boites dans l'interface
	private void placementContainersInPane() {
		this.setTop(vbTop);
		this.setCenter(hbCenter);
		this.setBottom(bpBotton);
	}
	
	public void addIngredientObl(String ingredient, String unite) {
		GridPane box = new GridPane();
		box.setAlignment(Pos.CENTER);
		box.setGridLinesVisible(true);
		box.setStyle("-fx-background-color: #1f1b17;");
		
		RowConstraints contrainteLigne = new RowConstraints(50);
		box.getRowConstraints().add(contrainteLigne);
		
		Label lIngredient = new Label(ingredient);
		lIngredient.setTextFill(Color.web("#1f1b17"));
		lIngredient.setFont(Font.font(20));
		
		HBox hbQuantite = new HBox();
		hbQuantite.setAlignment(Pos.CENTER);
		hbQuantite.setSpacing(5);
		TextField textField = new TextField();
		textField.setText("0");
		Label lUnite = new Label(unite + "   ");
		lUnite.setTextFill(Color.web("#FFFFFF"));
		lUnite.setFont(Font.font(20));
		hbQuantite.getChildren().addAll(textField, lUnite);
		
		input.put(ingredient, textField);
		
		box.add(lIngredient, 0, 0);
		box.add(hbQuantite, 1, 0);
		
		vbIngredientsObl.getChildren().add(box);
	}
	
	public void addBoutonAjout(String ingredient) {
		String id = ingredient;
		Button bouton = new Button("ajouter \n" + ingredient);
		bouton.setFont(Font.font("Arial", 11));
		bouton.setTextFill(Color.web("#000000"));
		bouton.setPrefSize(60,60);
		colorButton(bouton);
		
		fpBoutonsAjouts.getChildren().add(bouton);
		
		bouton.setOnAction((event) -> {
					this.buttonsIngredients.add(ingredient);
					addAjout(ingredient);
		
			
		});
	}
	
	public void addAjout(String ingredient) {
		int remove = -1;
		for (int i=0; i < vbAjouts.getChildren().size() ; i++) {
			GridPane ajout = (GridPane) vbAjouts.getChildren().get(i);
			Label nomAjout = (Label)ajout.getChildren().get(1);
			if (nomAjout.getText() == ingredient ) 
				remove = i;
		}
		
		if (remove == -1) {
			GridPane box = new GridPane();
			box.setAlignment(Pos.CENTER);
			box.setGridLinesVisible(true);
			box.setStyle("-fx-background-color: #1f1b17;");
			box.setPrefWidth(10);
			
			RowConstraints contrainteLigne = new RowConstraints(50);
			box.getRowConstraints().add(contrainteLigne);
			ColumnConstraints contrainteColonne = new ColumnConstraints(90,90,90,Priority.NEVER,HPos.CENTER,true);
			box.getColumnConstraints().add(contrainteColonne);
			
			Label lIngredient = new Label(ingredient);
			lIngredient.setTextFill(Color.web("#FFFFFF"));
			lIngredient.setFont(Font.font(20));
			
			HBox hbQuantite = new HBox();
			hbQuantite.setSpacing(5);
			hbQuantite.setAlignment(Pos.CENTER);
			TextField textField = new TextField();
			textField.setText("0");
			Label lUnite = new Label("kilos   ");
			lUnite.textProperty().bind(I18N.createStringBinding("label.kilo"));
			lUnite.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			lUnite.setTextFill(Color.web("#FFFFFF"));
			lUnite.setFont(Font.font(15));
			hbQuantite.getChildren().addAll(textField, lUnite);
			
			box.add(lIngredient, 0, 0);
			box.add(hbQuantite, 1, 0);
			
			if (vbAjouts.getChildren().size() == 0 ) vbAjouts.setMaxHeight(20);
			vbAjouts.setMaxHeight(vbAjouts.getMaxHeight() + 70);
			
			// this.hmIgredients.put(vbAjouts.getChildren().size(), ingredient);
			
			vbAjouts.getChildren().add(box);
		} else {
			vbAjouts.getChildren().remove(remove);
			this.buttonsIngredients.remove(remove);
			vbAjouts.setMaxHeight(vbAjouts.getMaxHeight() - 70);
			if (vbAjouts.getChildren().size() == 0 ) vbAjouts.setMaxHeight(0);
		}
		
		
		
		
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
	/*
private void colorButton2(ToggleButton bSuivant2) {
		

		bSuivant2.setStyle("-fx-background-color: #e1e1e1;");
		
		bSuivant2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	bSuivant2.setStyle("-fx-background-color: #f5f5f5;");
            }
        });

		bSuivant2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	bSuivant2.setStyle("-fx-background-color: #e1e1e1;");
            }
        });
		
		bSuivant2.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	bSuivant2.setStyle("-fx-background-color: #d2d2d2;");
            }
        });
}*/
        
	private HashMap getIngredients() {
		HashMap<String, String> ingredientsValues = new HashMap<String, String>();
		for(int i = 0; i < this.vbAjouts.getChildren().size(); i++) {
			GridPane box = (GridPane) this.vbAjouts.getChildren().get(i);
			Label ingredient = (Label) box.getChildren().get(1);
			String ingredientString = ingredient.getText();
			HBox n = (HBox) box.getChildren().get(2);
			TextField TF = (TextField) n.getChildren().get(0);
			String value = TF.getText();
			ingredientsValues.put(ingredientString, value);
		}
		return ingredientsValues;
		
	}
	
	private void configureButtons() {
		
		bSuivant.setOnAction((event) -> {
			if(cDialogue.recupEmpatage() == "fcb") {
				String eau = input.get("Eau").getText();
			
				String malt = input.get("Malt").getText();
			
				cDialogue.brassageFrancoBelge(eau, malt);
			} else if(cDialogue.recupEmpatage() == "ang") {
				String eau = input.get("Eau").getText();
				
				String malt = input.get("Malt").getText();
			
				cDialogue.brassageAnglais(eau, malt);
			}
			else {
				
				String eau = input.get("Eau").getText();
				
				String malt = input.get("Malt").getText();
			
				cDialogue.brassagePerso(eau, malt,cDialogue.getEmpPerso());
				
			}
			try {
				cDialogue.ajouterIngredient(getIngredients());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			cDialogue.afficherEcran(EcransApplication.FERMENTATION);
		});
		
		bRetour.setOnAction((event) -> {
			cDialogue.afficherEcran(EcransApplication.ACCUEIL);
		});
	
		colorButton(bSuivant);
		colorButton(bRetour);
		
		
	}
	
	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	
	
		
	public EcranCuissonMout(ControleurDialogue cd) {

		cDialogue = cd;

		createWidgets();
		createContainers();
		placementWidgetsInContainers();
		placementContainersInPane();
		configureButtons();
		initBackground();
		
		addIngredientObl("Eau", "Eau");
		
		addIngredientObl("Malt", "Kilos");
		
		
		addBoutonAjout("Fruits");
		addBoutonAjout("Epice");
		addBoutonAjout("Sucre");
		addBoutonAjout("Houblon");
		addBoutonAjout("Levures");
		addBoutonAjout("Algues");

		this.setVisible(false);
		cDialogue.enregistrerEcran(nomEcran, this);

	}

}